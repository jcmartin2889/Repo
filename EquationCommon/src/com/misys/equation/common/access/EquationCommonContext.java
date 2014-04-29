package com.misys.equation.common.access;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.management.ObjectName;
import javax.resource.spi.security.PasswordCredential;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.security.auth.ProfileTokenCredential;
import com.ibm.websphere.management.Session;
import com.ibm.websphere.management.configservice.ConfigService;
import com.ibm.websphere.management.configservice.ConfigServiceFactory;
import com.ibm.websphere.management.configservice.ConfigServiceHelper;
import com.ibm.wsspi.security.auth.callback.Constants;
import com.ibm.wsspi.security.auth.callback.WSMappingCallbackHandlerFactory;
import com.misys.equation.common.cluster.BreakMessageCommand;
import com.misys.equation.common.cluster.ClusterService;
import com.misys.equation.common.cluster.IClusterService;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IGPX1RecordDaoImp;
import com.misys.equation.common.dao.beans.ACNRecordDataModel;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.dao.beans.GPX1RecordDataModel;
import com.misys.equation.common.dao.beans.HBXRecordDataModel;
import com.misys.equation.common.globalprocessing.GlobalSessionHandler;
import com.misys.equation.common.globalprocessing.SystemStatusManagerMonitor;
import com.misys.equation.common.globalprocessing.audit.GlobalAuditingManager;
import com.misys.equation.common.internal.eapi.core.EQActionErrorException;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.encryption.EQBinConverter;
import com.misys.equation.common.internal.eapi.encryption.EQBlowfish;
import com.misys.equation.common.userexit.UserExit;
import com.misys.equation.common.userexit.ValidationUserExitMessage;
import com.misys.equation.common.utilities.AS400Toolbox;
import com.misys.equation.common.utilities.BankFusionCheck;
import com.misys.equation.common.utilities.EquationControl;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.FunctionClassLoader;
import com.misys.equation.common.utilities.MeridianCheck;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.ServiceComposerCheck;
import com.misys.equation.common.utilities.ThreadData;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.dataaccess.connectionpooling.impl.JndiConnectionPool;

/**
 * This is a singleton, holding state for the Desktop and EQ4
 */
public class EquationCommonContext
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationCommonContext.java 17823 2014-01-30 09:23:00Z perkinj1 $";

	/** The maximum number of messages a user exit can return to Equation. I.E. Needs to fit in two digits */
	private static final int MAX_VALIDATION_MESSAGES = 99;
	/** A plain text password */
	public static final String PASSWORD_TYPE_TEXT_PLAIN = "0";
	/** An text password encrypted using the exchanged keys */
	public static final String PASSWORD_TYPE_TEXT_ENCRYPTED = "1";
	/** A profile token */
	public static final String PASSWORD_TYPE_PROFILETOKEN_PLAIN = "2";
	/** An encrypted profile token */
	public static final String PASSWORD_TYPE_PROFILETOKEN_ENCRYPTED = "3";
	/** A profile token in Base 64 (i.e. obtained from BankFusion) */
	public static final String PASSWORD_TYPE_PROFILETOKEN_BASE64 = "4";
	/** A padded Unicode text password and encrypted using the exchanged keys */
	public static final String PASSWORD_TYPE_TEXT_PADDED_ENCRYPTED = "5";

	// Context keys
	public static final String SESSION_INDENTIFIER_KEY = "sessionIdentifier";
	public static final String LOGIN_KEY = "equationLogin";
	public static final String USER_KEY = "equationUser";

	// Session type
	public static final int SESSION_FULL_DESKTOP = 0;
	public static final int SESSION_CLASSIC_DESKTOP = 1;
	public static final int SESSION_DIRECT_TRANS_DESKTOP = 2;
	public static final int SESSION_API_MODE = 3; // API mode
	public static final int SESSION_CHILD_PLAYBUTTON = 4; // linked transaction via desktop (play button)
	public static final int SESSION_OTHER_MODE = 5; // created from getEqSession()
	public static final int SESSION_LINK_MODE = 6; // part of a bigger transaction
	public static final int SESSION_RECOVERY = 7; // part of a bigger transaction
	public static final int SESSION_EXT_INPUT = 8; // part of a bigger transaction
	public static final int SESSION_CHILD_MODE = 9; // linked transaction via desktop (option)
	public static final int SESSION_LINK_MODE_RECOV = 10; // part of a bigger transaction during recovery
	public static final int SESSION_LINK_MODE_EXT = 11; // part of a bigger transaction during ext input
	public static final int SESSION_CHILD_DRILLDOWN = 12; // linked transaction via desktop (play button drill down)
	public static final int SESSION_CHILD_DRILLDOWN_SAMEWINDOW = 13; // linked transaction via desktop (same window drill down)
	public static final int SESSION_CHILD_DRILLDOWN_SAMEWINDOW_EDIT = 14; // linked transaction via desktop (same window drill down)
	/** A session initiated from BankFusion (BFTC, UXP or BF WebService) */
	public static final int SESSION_BANKFUSION = 15;
	/** A session initiated from an Equation IBM i program */
	public static final int SESSION_SRVWEB = 16;
	// User Interface type
	public static final int UI_FULL_DESKTOP = 0;
	public static final int UI_UXP = 1;

	/** Break message severity levels */
	public static final String MESSAGE_SEVERITY_INFORMATION = "INFORMATION";
	public static final String MESSAGE_SEVERITY_WARNING = "WARNING";
	public static final String MESSAGE_SEVERITY_ERROR = "ERROR";

	/** Application Server Info Constants */
	public static final String BFEQ_HOST_NAME = "hostName";
	public static final String BFEQ_SOAP_PORT = "SOAP_CONNECTOR_ADDRESS";

	/** The KSM message to be returned to indicate a fatal error during a default user exit webservice call */
	private static final String DEFAULT_USEREXIT_KSM = "KSM7375";
	/** The KSM message to be returned to indicate a fatal error during a validate user exit webservice call */
	private static final String VALIDATE_USEREXIT_KSM = "KSM7376";

	private static final String CONFIGURATION_PROPERTY_FILE = "equationConfiguration";
	private static final String CONFIGURATION_SERVER_PROPS = "bfeq.server.properties";

	/** The plug-in version */
	public static final String PLUGIN_VERSION = "3.1.2";

	private static Hashtable<String, EquationLogin> equationLogins = new Hashtable<String, EquationLogin>();
	private static Hashtable<String, EquationSystem> equationSystems = new Hashtable<String, EquationSystem>();
	private static Hashtable<String, EquationUser> equationUsers = new Hashtable<String, EquationUser>();
	private static Hashtable<String, Properties> equationProperties = new Hashtable<String, Properties>();
	private static Hashtable<String, byte[]> equationBFKeys = new Hashtable<String, byte[]>();

	private final EquationConfigurationPropertiesBean equationPropertiesBean;
	private final Properties equationDesktopLanguageResources = new Properties();
	private final boolean equationDesktopLanguageResourcesLoaded = false;
	private boolean equationDesktopLanguageResourcesNotFound = false; // assume we will find them

	/** WebFacing application running */
	private static boolean webFacing = false;

	private static boolean meridianInstalled = false;

	private static boolean bankFusionInstalled = false;

	private static boolean serviceComposerInstalled = false;

	private boolean xaUsed = false;

	private static String[] ccsidRTLMap;

	private static EquationCommonContext singletonCommonContext;

	/** Create a thread pool for running tasks concurrently (such as logging in to all units global unit groups simultaneously) */
	private final ExecutorService executors = Executors.newCachedThreadPool();

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EquationCommonContext.class);

	// This is the system monitor task that checks units ability.
	private SystemStatusManagerMonitor systemStatusManagerMonitor;
	private GlobalSessionHandler globalSessionHandler;

	private boolean useJNDI = false;

	/** Determine whether LRP is enabled or not */
	private boolean lrpEnabled = false;

	/** Configuration property that indicates whether BF users are upper or lower case */
	public static final String BANKFUSION_USER_CASE_PROPERTY = "bf.user.case";

	/** Property value to indicate upper case */
	public static final String CASE_UPPER = "upper";

	/** Configuration property that indicates whether BF business date should be changed */
	public static final String BANKFUSION_UPDATE_BUSINESS_DATE_PROPERTY = "bf.date.updateBusinessDate";

	/** URL is built with either system property or file available to the runtime */
	private static URL propertiesURL = getPropertyFileInUrlFormat();

	/** Cluster service instance */
	private IClusterService clusterService;

	/** Function layer Callback */
	private IFunctionLayerCallback functionLayerCallback;

	/**
	 * Private Constructor
	 * 
	 * Only this class is allowed to create the singleton instance
	 */
	private EquationCommonContext()
	{
		equationPropertiesBean = EquationConfigurationPropertiesBean.getInstance();
		// Need to find out some basic info about the machine I am running on...
		if (LOG.isInfoEnabled())
		{
			Enumeration<Object> systemProperties = System.getProperties().keys();
			StringBuilder systemPropertiesString = new StringBuilder();
			while (systemProperties.hasMoreElements())
			{
				systemPropertiesString.append((String) systemProperties.nextElement() + "!:!");
			}
			String[] systemPropertiesStrings = systemPropertiesString.toString().split("!:!");
			Arrays.sort(systemPropertiesStrings);
			for (String systemProperty : systemPropertiesStrings)
			{
				String systemPropertyValue = System.getProperties().getProperty(systemProperty);
				LOG.info(systemProperty + "=" + systemPropertyValue);
			}
		}
		setMeridianInstalled();
		setBankFusionInstalled();
		setServiceComposerInstalled();
		setXAUsed();
		setJNDIAvailability();
		setLRPEnabled();
		setApplicationServerProperties();
	}

	private static URL getPropertyFileInUrlFormat()
	{

		URL resourceURL = null;
		String equationConfiguration = "equationConfiguration";
		String equationConfigurationProp = "equationConfiguration.properties";
		String systemPropertyValue = null;

		if (propertiesURL != null)
		{
			return propertiesURL;
		}
		// The property is named after the property file.
		systemPropertyValue = System.getProperty(equationConfiguration);

		if (systemPropertyValue != null && systemPropertyValue.length() > 0)
		{
			try
			{
				resourceURL = new URL(new StringBuilder("file:").append(
								systemPropertyValue.replace("\\", "/") + "/" + equationConfigurationProp).toString());
			}
			catch (Exception exception)
			{
				LOG.error(exception);
			}
		}
		else
		{
			systemPropertyValue = System.getProperty(equationConfigurationProp);
			if (systemPropertyValue != null && systemPropertyValue.length() > 0)
			{
				try
				{
					resourceURL = new URL(new StringBuilder("file:").append(systemPropertyValue.replace("\\", "/")).toString());
				}
				catch (Exception exception)
				{
					LOG.error(exception);
				}
			}
			else
			{
				resourceURL = Thread.currentThread().getContextClassLoader().getResource(equationConfigurationProp);
			}
		}

		propertiesURL = resourceURL;

		return propertiesURL;

	}

	/**
	 * Set whether connections are available from an application server pool
	 */
	private void setJNDIAvailability()
	{
		this.useJNDI = JndiConnectionPool.checkJndiAvailable();
	}

	/**
	 * @return the status of the JNDI connection pool
	 */
	public boolean isJNDI()
	{
		return useJNDI;
	}

	/**
	 * Get the singleton context
	 * 
	 * @equation.external
	 */
	public static synchronized EquationCommonContext getContext()
	{
		// Create our one and only instance of this class
		if (singletonCommonContext == null)
		{
			singletonCommonContext = new EquationCommonContext();
		}
		return singletonCommonContext;
	}

	/**
	 * Protected against cloning
	 */
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}

	/**
	 * Authenticate whether the use can sign-on to the unit
	 * 
	 * @param systemId
	 * @param unitId
	 * @param userId
	 * @param password
	 * @param passwordType
	 * @return a flag to determine if authenticated
	 * @throws EQActionErrorException
	 * @equation.external
	 */
	public boolean authenticate(String systemId, String unitId, String userId, String password, String passwordType)
					throws EQActionErrorException
	{
		boolean isValid = false;
		AS400 eqAuthenticationAS400 = null;
		try
		{
			eqAuthenticationAS400 = AS400Toolbox.getAS400(systemId);
			eqAuthenticationAS400.setGuiAvailable(false);

			if (passwordType.equals(EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN))
			{
				LOG.debug("User authentication via password: " + "[" + systemId + "][" + userId + "][" + unitId + "]");
				eqAuthenticationAS400.setUserId(userId);
				eqAuthenticationAS400.setPassword(password);
			}
			else if (passwordType.equals(EquationCommonContext.PASSWORD_TYPE_TEXT_ENCRYPTED))
			{
				LOG.debug("User authentication via token: " + "[" + systemId + "][" + userId + "][" + unitId + "]");
				byte[] tokenBytes = Toolbox.cvtHexStringToBytes(password);

				String pwd = String.valueOf(tokenBytes);
				eqAuthenticationAS400.setUserId(userId);
				eqAuthenticationAS400.setPassword(pwd);

			}
			else if (isUnencryptedProfileTokenPassword(passwordType))
			{
				// Log information
				LOG.debug("User authentication via token: " + "[" + systemId + "][" + userId + "][" + unitId + "]");
				if (passwordType.equals(EquationCommonContext.PASSWORD_TYPE_PROFILETOKEN_PLAIN))
				{
					LOG.debug("User authentication via token: " + "[" + systemId + "][" + userId + "][" + unitId + "]");
				}
				else if (passwordType.equals(EquationCommonContext.PASSWORD_TYPE_PROFILETOKEN_BASE64))
				{
					LOG.info("User authentication via base64 : token [" + password + "]");
				}

				byte[] tokenBytes = Toolbox.cvtPwdToAS400TokenBytes(password, passwordType);
				ProfileTokenCredential token = new ProfileTokenCredential(AS400Toolbox.getAS400(eqAuthenticationAS400), tokenBytes,
								ProfileTokenCredential.TYPE_SINGLE_USE, 3600);
				// Add the profile token to the AS400 and cross our fingers
				eqAuthenticationAS400.setProfileToken(token);
			}

			// This will connect to the iSeries to test that the supplied credentials are valid
			eqAuthenticationAS400.connectService(AS400.SIGNON);

			// If we get here then we are safe to set to valid
			isValid = true;

			// pop the system into a hash if we haven't already got it
			synchronized (equationSystems)
			{
				if (isValid && !equationSystems.containsKey(systemId))
				{
					if (!passwordType.equals(EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN))
					{
						throw new IllegalArgumentException("System must be initialised before RPG access is available");
					}
					else
					{
						EquationSystem system = new EquationSystem(systemId, userId, password);
						equationSystems.put(systemId, system);
					}
				}
			}
			return isValid;
		}
		catch (IOException e)
		{
			throw new EQActionErrorException("Context.authenticate: failed", e, "error.invalid.login");
		}
		catch (AS400SecurityException e)
		{
			throw new EQActionErrorException("Context.authenticate: failed", e, "error.invalid.message", Toolbox
							.getExceptionMessage(e));
		}
		catch (EQException e)
		{
			throw new EQActionErrorException("Context.authenticate: failed", e, "error.invalid.login");
		}
		catch (PropertyVetoException e)
		{
			throw new EQActionErrorException("Context.authenticate: failed", e, "error.invalid.message", Toolbox
							.getExceptionMessage(e));
		}
		finally
		{
			try
			{
				if (eqAuthenticationAS400 != null)
				{
					// Disconnect. our work is done
					eqAuthenticationAS400.disconnectAllServices();
				}
			}
			catch (Exception e)
			{
				// log any disconnect errors, but continue
				LOG.error(e);
			}
		}
	}

	/**
	 * Change the password given a system, user ID and new and old passwords
	 * 
	 * @param systemId
	 * @param userId
	 * @param oldpassword
	 * @param newpassword
	 * @return true if the operation succeeded, otherwise false
	 * @throws EQActionErrorException
	 * @equation.external
	 */
	public boolean changePassword(String systemId, String userId, String oldpassword, String newpassword)
					throws EQActionErrorException
	{
		boolean isValid = true;
		// We need a whole new AS400 for this job...
		AS400 eqAuthenticationAS400 = null;
		eqAuthenticationAS400 = AS400Toolbox.getAS400(systemId, userId, oldpassword);
		try
		{
			isValid = eqAuthenticationAS400.authenticate(userId, oldpassword);
			eqAuthenticationAS400.changePassword(oldpassword, newpassword);
			return isValid;
		}
		catch (IOException e)
		{
			LOG.error(e);
			throw new EQActionErrorException("Context.authenticate: failed", e, Toolbox.getExceptionMessage(e));
		}
		catch (AS400SecurityException e)
		{
			if (e.getReturnCode() == AS400SecurityException.PASSWORD_EXPIRED)
			{
				try
				{
					eqAuthenticationAS400.changePassword(oldpassword, newpassword);
					return isValid;
				}
				catch (Exception e2)
				{
					LOG.error(e2);
					throw new EQActionErrorException("Context.authenticate: failed", e2, Toolbox.getExceptionMessage(e2));
				}
			}
			else
			{
				LOG.error(e);
				throw new EQActionErrorException("Context.authenticate: failed", e, Toolbox.getExceptionMessage(e));
			}
		}
		finally
		{
			if (eqAuthenticationAS400 != null)
			{
				eqAuthenticationAS400.disconnectAllServices();
			}
		}
	}

	/**
	 * This method will return an <code>EquationStandardSession </code>. If the session identifier is not supplied or a session does
	 * not exist for the supplied session identifier then a new EquationStandardSession will be returned. If a session already
	 * exists for the supplied session identifier then the already existing EquationStandardSession instance will be returned. XA
	 * will not be used.
	 * 
	 * @param system
	 *            this is the current system.
	 * @param unitId
	 *            this is the current unitId.
	 * @param userId
	 *            this is the current userId.
	 * @param password
	 *            this is the current password.
	 * @param sessionIdentifier
	 *            this is the identifier that will be used used to identify the EquationStandardSession.
	 * @param ipAddress
	 *            this is the IP address that will be used on Equation Journal files
	 * @param passwordType
	 *            this is the type of type of password supplied
	 * @param sessionType
	 *            this is the type of session to be created
	 * @return a String of the session identifier assigned
	 * @throws EQException
	 * @equation.external
	 */
	public String getEqSession(String systemId, String unitId, String userId, String password, String sessionIdentifier,
					String ipAddress, String passwordType, int sessionType) throws EQException
	{
		return getEqSession(systemId, unitId, userId, password, sessionIdentifier, ipAddress, passwordType, sessionType, false,
						null);
	}
	/**
	 * This method will return an <code>EquationStandardSession </code>. If the session identifier is not supplied or a session does
	 * not exist for the supplied session identifier then a new EquationStandardSession will be returned. If a session already
	 * exists for the supplied session identifier then the already existing EquationStandardSession instance will be returned. XA
	 * will not be used.
	 * 
	 * @param system
	 *            this is the current system.
	 * @param unitId
	 *            this is the current unitId.
	 * @param userId
	 *            this is the current userId.
	 * @param password
	 *            this is the current password.
	 * @param sessionIdentifier
	 *            this is the identifier that will be used used to identify the EquationStandardSession.
	 * @param ipAddress
	 *            this is the IP address that will be used on Equation Journal files
	 * @param passwordType
	 *            this is the type of type of password supplied
	 * @param sessionType
	 *            this is the type of session to be created
	 * @param equationIseriesProfile
	 *            - the iSeries/Equation user profile
	 * @return a String of the session identifier assigned
	 * @throws EQException
	 * @equation.external
	 */
	public String getEqSession(String systemId, String unitId, String userId, String password, String sessionIdentifier,
					String ipAddress, String passwordType, int sessionType, String equationIseriesProfile) throws EQException
	{
		return getEqSession(systemId, unitId, userId, password, sessionIdentifier, ipAddress, passwordType, sessionType, false,
						equationIseriesProfile);
	}

	/**
	 * This method will return an <code>EquationStandardSession </code>. If the session identifier is not supplied or a session does
	 * not exist for the supplied session identifier then a new EquationStandardSession will be returned. If a session already
	 * exists for the supplied session identifier then the already existing EquationStandardSession instance will be returned.
	 * 
	 * @param system
	 *            this is the current system.
	 * @param unitId
	 *            this is the current unitId.
	 * @param userId
	 *            this is the current userId.
	 * @param password
	 *            this is the current password.
	 * @param sessionIdentifier
	 *            this is the identifier that will be used used to identify the EquationStandardSession.
	 * @param ipAddress
	 *            this is the IP address that will be used on Equation Journal files
	 * @param passwordType
	 *            this is the type of type of password supplied
	 * @param sessionType
	 *            this is the type of session to be created
	 * @param xaMode
	 *            set to true if XA is to be used
	 * @return a String of the session identifier assigned
	 * @throws EQException
	 * @equation.external
	 */
	public String getEqSession(String systemId, String unitId, String userId, String password, String sessionIdentifier,
					String ipAddress, String passwordType, int sessionType, boolean xaMode) throws EQException
	{
		return getEqSession(systemId, unitId, userId, password, sessionIdentifier, ipAddress, passwordType, sessionType, xaMode,
						null);
	}
	/**
	 * This method will return an <code>EquationStandardSession </code>. If the session identifier is not supplied or a session does
	 * not exist for the supplied session identifier then a new EquationStandardSession will be returned. If a session already
	 * exists for the supplied session identifier then the already existing EquationStandardSession instance will be returned.
	 * 
	 * @param system
	 *            this is the current system.
	 * @param unitId
	 *            this is the current unitId.
	 * @param userId
	 *            this is the current userId.
	 * @param password
	 *            this is the current password.
	 * @param sessionIdentifier
	 *            this is the identifier that will be used used to identify the EquationStandardSession.
	 * @param ipAddress
	 *            this is the IP address that will be used on Equation Journal files
	 * @param passwordType
	 *            this is the type of type of password supplied
	 * @param sessionType
	 *            this is the type of session to be created
	 * @param xaMode
	 *            set to true if XA is to be used
	 * @param equationIseriesProfile
	 *            - the iSeries/Equation user profile
	 * @return a String of the session identifier assigned
	 * @throws EQException
	 * @equation.external
	 */
	public String getEqSession(String systemId, String unitId, String userId, String password, String sessionIdentifier,
					String ipAddress, String passwordType, int sessionType, boolean xaMode, String equationIseriesProfile)
					throws EQException
	{
		// initialise method variables
		systemId = systemId.trim().toUpperCase();
		unitId = unitId.trim().toUpperCase();
		userId = userId.trim().toUpperCase();

		// Do not modify the password if a BankFusion base64 token
		if (!EquationCommonContext.PASSWORD_TYPE_PROFILETOKEN_BASE64.equals(passwordType))
		{
			password = password.trim().toUpperCase();
		}

		// do a quick authentication...
		if (authenticate(systemId, unitId, userId, password, passwordType))
		{
			// Generate a new session id if not passed
			if (sessionIdentifier == null || sessionIdentifier.trim().equals(""))
			{
				sessionIdentifier = generateSessionID();
			}

			sessionIdentifier = getEqSessionInternal(systemId, unitId, userId, password, passwordType, sessionIdentifier,
							ipAddress, sessionType, xaMode, equationIseriesProfile);
		}
		else
		{
			LOG.error("Invalid Credentials");
			sessionIdentifier = null;
		}
		// return the session id
		return sessionIdentifier;
	}

	/**
	 * Initialise the environment, authenticates the credentials and then instantiates a system and unit and stores then in this
	 * singleton
	 * 
	 * @param systemId
	 * @param unitId
	 * @param userId
	 * @param password
	 * @param passwordType
	 * @return a flag to determine if initialisation was successful
	 * @throws EQException
	 */
	public boolean initialiseUnitEnvironment(String systemId, String unitId, String userId, String password, String passwordType)
					throws EQException
	{
		// initialise method variables
		boolean initialised = false;
		systemId = systemId.trim().toUpperCase();
		unitId = unitId.trim().toUpperCase();
		userId = userId.trim().toUpperCase();
		password = password.trim().toUpperCase();

		// do a quick authentication and proceed to store the system and unit...
		if (authenticate(systemId, unitId, userId, password, passwordType))
		{
			EquationSystem system = getEquationSystem(systemId);
			EquationUnit unit = system.getUnit(unitId, userId, password);
			unit.refresh();
			initialised = true;
		}
		else
		{
			LOG.error("Invalid Credentials");
		}
		// return the session id
		return initialised;
	}

	/**
	 * Create an EquationStandardSession and return its session identifier.
	 * 
	 * @param systemId
	 * @param unitId
	 * @param userId
	 * @param password
	 * @param sessionIdentifier
	 * @param ipAddress
	 * @param sessionType
	 * @param xaMode
	 * @param equationIseriesProfile
	 *            - the iSeries/Equation user profile
	 * @return a String of the session ID assigned
	 * @throws EQException
	 */
	private String getEqSessionInternal(String systemId, String unitId, String userId, String password, String passwordType,
					String sessionIdentifier, String ipAddress, int sessionType, boolean xaMode, String equationIseriesProfile)
					throws EQException
	{
		LOG.info("Creating new session id = [" + sessionIdentifier + "] for user [" + userId + "]" + " iSeries id [ "
						+ equationIseriesProfile + "]");
		try
		{
			// Pop the session identifier onto the thread
			ThreadData.set(SESSION_INDENTIFIER_KEY, sessionIdentifier);
			EquationLogin login;
			EquationSystem system = getEquationSystem(systemId);
			EquationUnit unit;
			EquationUser user;
			// If we already have a valid session with the same login details why bother going any further?
			if (sessionValid(sessionIdentifier))
			{
				// get the existing login details and see if changed
				EquationLogin existinglogin = equationLogins.get(sessionIdentifier);
				if (existinglogin.getUserId().equals(userId) && existinglogin.getPassword().equals(password)
								&& existinglogin.getUnitId().equals(unitId) && existinglogin.getSystem().equals(systemId))
				{
					if (equationIseriesProfile != null
									&& getEquationUser(sessionIdentifier).getUserId().equalsIgnoreCase(equationIseriesProfile))
					{
						return sessionIdentifier;
					}
				}
			}
			// Pop the login into a hash and also store on the session
			login = new EquationLogin(userId, password, unitId, systemId, ipAddress, sessionIdentifier);
			login.setSessionType(sessionType);
			login.setPasswordType(passwordType);
			equationLogins.put(sessionIdentifier, login);
			ThreadData.set(LOGIN_KEY, login);

			// Can't do anything that needs a connection whenever using the CLASSIC mode.
			if (sessionType != SESSION_CLASSIC_DESKTOP)
			{
				// Initialise the unit (if it isn't already)
				unit = system.getUnit(unitId, userId, password);

				// Initialise the user
				user = new EquationUser(unit, userId, password, xaMode, equationIseriesProfile);
				user.getSession().setSessionIdentifier(sessionIdentifier);
				equationUsers.put(sessionIdentifier, user);
				ThreadData.set(USER_KEY, user);

				// if this is a global processing desktop environment, create the user sessions for global processing
				if (equationPropertiesBean.isGlobalProcessingGoodToGo())
				{
					createGlobalProcessingEquationUsers(sessionIdentifier);
				}

				// Additional login processing:
				logOnSession(sessionIdentifier, sessionType);

				LOG.info("session id = [" + sessionIdentifier + "] for user [" + userId + "] initialised");
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			if (sessionIdentifier != null)
			{
				logOffSession(sessionIdentifier);
				equationLogins.remove(sessionIdentifier);
				equationUsers.remove(sessionIdentifier);
				// sessionIdentifier = null; //comment based on merging 16 June 2011
			}
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		return sessionIdentifier;
	}
	/**
	 * This method will return an <code>EquationStandardSession </code>.
	 * 
	 * @param dataSourceName
	 *            - the data source name
	 * @param sessionIdentifier
	 *            this is the identifier that will be used used to identify the EquationStandardSession.
	 * @param sessionType
	 *            this is the type of session to be created
	 * @param xaMode
	 *            set to true if XA is to be used
	 * @param equationIseriesProfile
	 *            - the iSeries/Equation user profile
	 * @return a String of the session identifier assigned
	 * 
	 * @throws EQException
	 */
	public String getEqSession(String dataSourceName, String sessionIdentifier, int sessionType, boolean xaMode,
					String equationIseriesProfile) throws EQException
	{

		// example of jndi name - jdbc/EQ-SLOUGH1-CAS-XA
		String[] parts = dataSourceName.split("-");
		String systemId = parts[1];
		String unitId = parts[2];

		// Generate a new session id if not passed
		if (sessionIdentifier == null || sessionIdentifier.trim().equals(""))
		{
			sessionIdentifier = generateSessionID();
		}

		LOG.info("Creating new session id = [" + sessionIdentifier + "] for datasource [" + dataSourceName + "]");
		try
		{
			// Pop the session identifier onto the thread
			ThreadData.set(SESSION_INDENTIFIER_KEY, sessionIdentifier);
			EquationLogin login;
			EquationSystem system = getEquationSystem(systemId);
			EquationUnit unit;
			EquationUser user;

			// Pop the login into a hash and also store on the session
			login = new EquationLogin("", "", unitId, systemId, "", sessionIdentifier);
			login.setSessionType(sessionType);
			login.setPasswordType("");
			equationLogins.put(sessionIdentifier, login);
			ThreadData.set(LOGIN_KEY, login);

			// Initialise the unit (if it isn't already) - think has to be setup at system startup using
			// equationConfiguration.properties
			unit = system.getUnit(unitId);
			unit.initialiseConnectionPool(dataSourceName);

			// Initialise the user
			if (equationIseriesProfile != null && unit.getUserTemplate(equationIseriesProfile) != null)
			{
				// User created from the template to avoid lookups on the iSeries
				user = new EquationUser(unit, dataSourceName, xaMode, equationIseriesProfile, unit
								.getUserTemplate(equationIseriesProfile));
			}
			else
			{
				user = new EquationUser(unit, dataSourceName, xaMode, equationIseriesProfile);
				if (equationIseriesProfile != null)
				{
					// TODO Is a clone required? Session id is not important
					unit.addEquationUserToUserTemplates(equationIseriesProfile, user);
				}
			}

			user.getSession().setSessionIdentifier(sessionIdentifier);
			equationUsers.put(sessionIdentifier, user);
			ThreadData.set(USER_KEY, user);

			// if this is a global processing desktop environment, create the user sessions for global processing
			if (equationPropertiesBean.isGlobalProcessingGoodToGo())
			{
				createGlobalProcessingEquationUsers(sessionIdentifier);
			}
			LOG.info("session id = [" + sessionIdentifier + "] for datasource [" + dataSourceName + "] initialised");

		}
		catch (Exception e)
		{
			LOG.error(e);
			logOffSessionUserPool(sessionIdentifier);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		return sessionIdentifier;
	}
	/**
	 * Cleanup handles to pooled session.
	 * 
	 * @param sessionIdentifier
	 *            - this is the identifier that will be used used to identify the EquationStandardSession.
	 */
	public void logOffSessionUserPool(String sessionIdentifier)
	{
		if (sessionIdentifier != null)
		{
			equationLogins.remove(sessionIdentifier);
			equationUsers.remove(sessionIdentifier);
		}
	}
	/**
	 * Retrieve an Equation unit given a session id
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 * @return the EquationUnit if it exists or null
	 * @throws EQException
	 * @equation.external
	 */
	public EquationUnit getEquationUnit(String sessionIdentifier) throws EQException
	{
		if (sessionValid(sessionIdentifier))
		{
			EquationLogin eqLogin = equationLogins.get(sessionIdentifier);
			EquationSystem eqSystem = equationSystems.get(eqLogin.getSystem());
			return eqSystem.getUnit(eqLogin.getUnitId());
		}
		else
		{
			return null;
		}
	}

	/**
	 * Retrieve an Equation unit from the system
	 * 
	 * @param systemId
	 *            - the system id
	 * @param unitId
	 *            - the unit id
	 * 
	 * @throws EQException
	 */
	public EquationUnit getEquationUnit(String systemId, String unitId) throws EQException
	{
		EquationSystem eqSystem = equationSystems.get(systemId);
		if (eqSystem != null)
		{
			return eqSystem.getUnit(unitId);
		}
		return null;
	}

	/**
	 * Remove an Equation unit identified by the session id
	 * 
	 * @param sessionIdentifier
	 *            - the session id of the unit to be removed
	 * @return true if unit has been removed
	 */
	public boolean removeEquationUnit(String sessionIdentifier)
	{
		if (sessionValid(sessionIdentifier))
		{
			EquationLogin eqLogin = equationLogins.get(sessionIdentifier);
			removeEquationUnit(eqLogin.getSystem(), eqLogin.getSystem());
			return true;
		}
		return false;
	}

	/**
	 * Remove an Equation unit from the system
	 * 
	 * This will call back up through the Function and UI layers if applicable to remove user sessions in those layers before
	 * actually removing the unit
	 * 
	 * @param systemId
	 *            - the system id
	 * @param unitId
	 *            - the unit id
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public boolean removeEquationUnit(String systemId, String unitId)
	{
		EquationSystem eqSystem = equationSystems.get(systemId);
		if (eqSystem != null)
		{
			// Call back to the EquationFunction layer to clean up user sessions
			if (functionLayerCallback != null)
			{
				functionLayerCallback.removeAllUsersOfUnit(systemId, unitId);
			}
			// Remove any sessions not already removed by other layers
			removeAllUsersOfUnit(systemId, unitId);

			eqSystem.removeUnit(unitId);
			return true;
		}
		return false;
	}

	/**
	 * Removes user sessions for the specified unit
	 * 
	 * @param systemId
	 * @param unitId
	 */
	private void removeAllUsersOfUnit(String systemId, String unitId)
	{
		for (Entry<String, EquationUser> equationUserEntry : singletonCommonContext.getEqUsers().entrySet())
		{
			EquationUser equationUser = equationUserEntry.getValue();
			if (equationUser != null && equationUser.getSession() != null)
			{
				String userSystemId = equationUser.getSession().getSystemId();
				String userUnitId = equationUser.getSession().getUnitId();
				if (userUnitId.equalsIgnoreCase(unitId) && userSystemId.equalsIgnoreCase(systemId))
				{
					singletonCommonContext.logOffSession(equationUser.getSession().getSessionIdentifier());
				}
			}
		}
	}

	/**
	 * Return the Equation system
	 * 
	 * @param systemId
	 *            - the Equation system id
	 * 
	 * @return the Equation system
	 * @equation.external
	 */
	public EquationSystem getEquationSystem(String systemId)
	{
		return equationSystems.get(systemId);
	}

	/**
	 * Remove an Equation system
	 * 
	 * @param systemId
	 *            - the Equation system id
	 * 
	 * @return the deleted Equation system
	 */
	public EquationSystem removeEquationSystem(String systemId)
	{
		return equationSystems.remove(systemId);
	}

	/**
	 * Add an Equation system
	 * 
	 * @param system
	 *            - the Equation system
	 */
	protected void addEquationSystem(EquationSystem system)
	{
		// pop the system into a hash if we haven't already got it
		synchronized (equationSystems)
		{
			if (!equationSystems.containsKey(system.getSystemId()))
			{
				equationSystems.put(system.getSystemId(), system);
			}
		}
	}

	/**
	 * Return an Equation user identified by the session id
	 * 
	 * @param sessionIdentifier
	 *            - the session id the Equation user
	 * 
	 * @return the Equation user
	 * @equation.external
	 */
	public EquationUser getEquationUser(String sessionIdentifier)
	{
		if (sessionValid(sessionIdentifier))
		{
			return equationUsers.get(sessionIdentifier);
		}
		else
		{
			return null;
		}
	}

	/**
	 * Get the equation user for a given unit and system. If the unit or the system are null the unit is assumed to be the home
	 * unit.
	 * 
	 * @param system
	 *            - the ID of the Equation system
	 * @param unit
	 *            - the unit mnemonic
	 * @param sessionIdentifier
	 *            - the session ID
	 * @return an Equation user for the passed unit and system
	 * @equation.external
	 */
	public EquationUser getEquationUser(String system, String unit, String sessionIdentifier)
	{
		EquationUser user = null;
		String sessionKey;
		if (sessionValid(sessionIdentifier))
		{
			try
			{
				EquationUnit passedUnit = getEquationUnit(sessionIdentifier);
				// Test if the default session is the for the unit specified
				if (unit == null || system == null
								|| (system.equals(passedUnit.getSystem().getSystemId()) && unit.equals(passedUnit.getUnitId())))
				{
					sessionKey = sessionIdentifier;
				}
				else
				{
					sessionKey = system + ":" + unit + ":" + sessionIdentifier;
				}
				user = equationUsers.get(sessionKey);
			}
			catch (EQException e)
			{
				LOG.error(e);
			}
		}
		return user;
	}

	/**
	 * Gets an <code>EquationLogin</code> given a session identifier
	 * 
	 * @param sessionIdentifier
	 *            - the session ID
	 * @return an <code>EquationLogin</code> given a session identifier
	 */
	public EquationLogin getEquationLogin(String sessionIdentifier)
	{
		if (sessionValid(sessionIdentifier))
		{
			return equationLogins.get(sessionIdentifier);
		}
		else
		{
			return null;
		}
	}

	/**
	 * Get an <code>EquationData</code> object
	 * 
	 * @param sessionIdentifier
	 * @param service
	 * @param decode
	 * @param key
	 * @param blankAllowed
	 * @param newCode
	 * @return an <code>EquationData</code> object
	 * @throws EQException
	 * @equation.external
	 */
	public EquationData getEquationData(String sessionIdentifier, String service, String decode, String key, String blankAllowed,
					String newCode) throws EQException
	{
		if (sessionValid(sessionIdentifier))
		{
			return new EquationData(getEquationUser(sessionIdentifier).getSession(), service, decode, key, blankAllowed, newCode);
		}
		else
		{
			return null;
		}
	}

	/**
	 * Get an IDataList
	 * 
	 * @param unitId
	 * @param sessionIdentifier
	 * @param service
	 * @param decode
	 * @param security
	 * @param query
	 * @param start
	 * @param direction
	 * @param maxResults
	 * @return an IDataList
	 * @throws EQException
	 */
	public IDataList getEquationDataList(String unitId, String sessionIdentifier, String service, String decode, String security,
					String query, String start, int direction, int maxResults) throws EQException, UnitNotAvailableException
	{
		// First check we have a valid session
		if (!sessionValid(sessionIdentifier))
		{
			return null;
		}
		// get the class name of the EquationDataList
		String className = EquationDataList.class.getName();
		try
		{
			EquationUser user = getEquationUser(sessionIdentifier);
			EquationUnit unit = user.getEquationUnit();
			// Check for non-standard PVs (assumes PV definition in the "home" unit)
			if (unit.getPVMetaData(service).getDecodeMetaData(decode).getpFile().equals("JAVA"))
			{
				// Some data lists have special java wrapper programs that just append the service e.g. EquationDataListHGR01R
				className = className + service;
			}
			Class<?> dataListClass = Class.forName(className);
			if (dataListClass != null)
			{
				IDataList dataList = (IDataList) dataListClass.newInstance();
				EquationUser processingUser = getEquationUser(unit.getEquationSystem().getSystemId(), unitId, sessionIdentifier);
				if (processingUser == null)
				{
					throw new UnitNotAvailableException(unit.getEquationSystem().getSystemId(), unitId);
				}
				dataList.initialise(processingUser, service, decode, security, query, start, direction, maxResults);
				return dataList;
			}
		}
		catch (ClassNotFoundException ex)
		{
			throw new EQException("Data list class not found: " + className + "-" + Toolbox.getExceptionMessage(ex), ex);
		}
		catch (IllegalAccessException ex)
		{
			throw new EQException("Data list class not found: " + className + "-" + Toolbox.getExceptionMessage(ex), ex);
		}
		catch (InstantiationException ex)
		{
			throw new EQException("Data list class not found: " + className + "-" + Toolbox.getExceptionMessage(ex), ex);
		}
		return null;
	}
	/**
	 * Get an IDataList
	 * 
	 * @param unitId
	 * @param sessionIdentifier
	 * @param service
	 * @param decode
	 * @param security
	 * @param query
	 * @param start
	 * @param direction
	 * @param maxResults
	 * @return an IDataList
	 * @throws EQException
	 */
	public IDataList getEquationDataSQLPagingList(String unitId, String sessionIdentifier, String service, String decode,
					String security, String query, String start, int direction, int maxResults) throws EQException,
					UnitNotAvailableException
	{
		// First check we have a valid session
		if (!sessionValid(sessionIdentifier))
		{
			return null;
		}
		// get the class name of the EquationDataList
		String className = EquationDataSQLPagingList.class.getName();
		try
		{
			EquationUser user = getEquationUser(sessionIdentifier);
			EquationUnit unit = user.getEquationUnit();
			// Check for non-standard PVs (assumes PV definition in the "home" unit)
			if (unit.getPVMetaData(service).getDecodeMetaData(decode).getpFile().equals("JAVA"))
			{
				// Some data lists have special java wrapper programs that just append the service e.g. EquationDataListHGR01R
				className = className + service;
			}
			Class<?> dataListClass = Class.forName(className);
			if (dataListClass != null)
			{
				IDataList dataList = (IDataList) dataListClass.newInstance();
				EquationUser processingUser = getEquationUser(unit.getEquationSystem().getSystemId(), unitId, sessionIdentifier);
				if (processingUser == null)
				{
					throw new UnitNotAvailableException(unit.getEquationSystem().getSystemId(), unitId);
				}
				dataList.initialise(processingUser, service, decode, security, query, start, direction, maxResults);
				return dataList;
			}
		}
		catch (ClassNotFoundException ex)
		{
			throw new EQException("Data list class not found: " + className + "-" + Toolbox.getExceptionMessage(ex), ex);
		}
		catch (IllegalAccessException ex)
		{
			throw new EQException("Data list class not found: " + className + "-" + Toolbox.getExceptionMessage(ex), ex);
		}
		catch (InstantiationException ex)
		{
			throw new EQException("Data list class not found: " + className + "-" + Toolbox.getExceptionMessage(ex), ex);
		}
		return null;
	}
	/**
	 * Checks the list of <code>EquationLogin</code>s to see if the session ID specified exists
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 * @return true if the session ID exists, otherwise false
	 * @equation.external
	 */
	public boolean sessionValid(String sessionIdentifier)
	{
		// May need to check the connection is still valid
		if (sessionIdentifier == null || sessionIdentifier.trim().equals(""))
		{
			return false;
		}
		return equationLogins.containsKey(sessionIdentifier);
	}

	/**
	 * Return the logger
	 * 
	 * @return the logger
	 * @equation.external
	 */
	public EquationLogger getLOG()
	{
		return LOG;
	}

	/**
	 * Invokes user exit Java class to perform user exit processing
	 * 
	 * @param sessionIdentifier
	 *            A session identifier previous obtained by the getEqSession method
	 * @param program
	 *            Name of the RPG validation program (e.g. "H60FVR")
	 * @param xfct
	 *            Function mode (A/M/D)
	 * @param xprmt
	 *            Prompt mode (Y/N) Should never be called with 'Y'
	 * @param xscrn
	 *            Screen number
	 * @param xmode
	 *            User Exit Mode ('D'efault or 'V'alidate)
	 * @param dsexit
	 *            A String representation of the screen data structure, converted from EBCDIC to UTF-8.
	 * 
	 * @return A return buffer in the following format: First two characters are the number of messages (in character format). Then
	 *         follows the relevant number of message items, each 39 characters long. Finally, the returned data structure is
	 *         appended
	 */
	@SuppressWarnings("unchecked")
	public String getEqUserExit(String sessionIdentifier, String program, String xfct, String xprmt, String xscrn, String xmode,
					String dsexit)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Entering getEqUserExit [" + program + "], Function mode = [" + xfct + "], Screen Number = [" + xscrn
							+ "], Mode = [" + xmode + "]");
			LOG.debug("DS buffer = [" + dsexit + "]");
		}

		// The response buffer
		StringBuffer result = new StringBuffer();
		try
		{
			EquationUnit unit = getEquationUnit(sessionIdentifier);

			if (unit != null)
			{
				// Get a session
				EquationStandardSession standardSession = getEquationUser(sessionIdentifier).getSession();

				// First attempt to lookup the qualified class name from the program name
				String className = unit.getValidationUserExitClasses().get(program);
				if (className == null)
				{
					ACNRecordDataModel acn = new ACNRecordDataModel();
					acn.setLibrary(unit.getKFILLibrary());
					acn.setProgram(program);
					ACNRecordDataModel record = new DaoFactory().getACNDao(standardSession, acn).getACNRecord();
					if (record != null)
					{
						className = record.getClassName();
						unit.getValidationUserExitClasses().put(program, className);
					}
				}

				// If the class name is still null, no definition for this program:
				if (className == null)
				{
					throw new RuntimeException("No ACNPF record exists for program [" + program + "]");
				}

				// Try to get a screen data structure definition from cache
				EquationScreenDataStructure screenDS = unit.getEquationUserExitDSs().get(program);

				// Get the current RPG class loader from the unit
				FunctionClassLoader loader = unit.getRpgUserExitClassLoader();

				// Try to get the class bytes from the classloader cache
				Class userExitClass = loader.getLoadedClass(className);

				// Now see if we need to get a session:
				if (userExitClass == null || screenDS == null)
				{
					if (userExitClass == null)
					{

						userExitClass = loader.loadClass(standardSession, "", program, "", GAZRecordDataModel.TYP_RPGUSEREXIT);
					}

					if (screenDS == null)
					{
						// Creating a new EquationScreenDataStructure involves reading the DS definition from the iSeries
						screenDS = new EquationScreenDataStructure(program, standardSession);
						unit.getEquationUserExitDSs().put(program, screenDS);
					}
				}

				// Set the screen data structure information into the EquationScreenDataStructure instance
				screenDS.setSerialisation(dsexit);

				// This creates a new instance of the user exit class per call.
				UserExit userExitHelper = (UserExit) userExitClass.newInstance();

				// The user exit should store the passed instance of the EquationScreenDataStructure
				userExitHelper.initialize(screenDS);

				// The user exit should perform the validation/defaulting processing in this method
				userExitHelper.execute(xfct, xscrn, xmode);

				// Finally, get the collection of messages that the user exit has accumulated
				List<ValidationUserExitMessage> messages = userExitHelper.getMessages();

				/*
				 * Now build a return buffer in the following format:
				 * 
				 * First two characters are the number of messages (in character format). Then follows the relevant number of
				 * message items, each 39 characters long. Finally, the returned data structure is appended
				 */
				int messageCount = messages == null ? 0 : messages.size();
				// Can only process two digits => 99 messages
				if (messageCount > MAX_VALIDATION_MESSAGES)
				{
					throw new ArrayIndexOutOfBoundsException("Number of messages exceeds limit of " + MAX_VALIDATION_MESSAGES);
				}

				// Add the string representation of the message count to the buffer:
				String countString = Toolbox.leftZeroPad(messageCount, 2);
				result.append(countString);
				if (messages != null)
				{
					// Add all the individual message buffers:
					for (ValidationUserExitMessage message : messages)
					{
						result.append(message.getBuffer());
					}
				}
				// Finally, add the returned Screen Field DS, right trimmed to minimize network traffic
				result.append(Toolbox.trimr(screenDS.getSerialisation()));
			}
		}
		catch (Exception e)
		{
			// If any exceptions occur, log the details and return a KSM
			LOG.error(e);
			result = new StringBuffer("01"); // One message...
			ValidationUserExitMessage message = new ValidationUserExitMessage("D".equals(xmode) ? DEFAULT_USEREXIT_KSM
							: VALIDATE_USEREXIT_KSM, 0);
			message.setParameter1(program);
			result.append(message.getBuffer());
			// Also, return the supplied ZL buffer
			if (dsexit != null)
			{
				result.append(dsexit);
			}
		}
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Returned buffer (messages and DS buffer) is [" + result.toString() + "]");
		}
		return result.toString();
	}

	/**
	 * Clears validation user exit information from cache.
	 * 
	 * This is called from the User Exit Deployment to ensure that in a development scenario, the new class information is re-read
	 * from the GAZPF
	 * 
	 * @param systemId
	 *            The name of the system
	 * @param unitId
	 *            The name of the unit
	 * @param className
	 *            The full class name (including package)
	 */
	public void invalidateValidationUserExit(String systemId, String unitId, String program, String className)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info("invalidateClass method called to invalidate class[" + className + "] for system [" + systemId + "], unit ["
							+ unitId + "]");
		}
		EquationSystem eqSystem = equationSystems.get(systemId);
		if (eqSystem != null)
		{
			try
			{
				// TODO: The getEqUnit method will try to add a unit if one does not exist
				// This behaviour needs to be changed.
				EquationUnit unit = eqSystem.getUnit(unitId);

				if (unit != null)
				{
					// First try to invalidate the program/className cache entry:
					unit.getValidationUserExitClasses().remove(program);

					FunctionClassLoader loader = unit.getRpgUserExitClassLoader();
					if (loader.getLoadedClass(className) != null)
					{
						// Remove any RPG user exit class from the classloader cache by creating a new classloader:
						unit.createNewRpgUserExitClassLoader();
						if (LOG.isInfoEnabled())
						{
							LOG.info("New FunctionClassLoader instance created to invalidate previously loaded class [" + className
											+ "].");
						}
					}
					else
					{
						if (LOG.isDebugEnabled())
						{
							LOG.debug("Class [" + className
											+ "] not loaded by current classloader, a new classloader is not required.");
						}
					}
				}
			}
			catch (EQException e)
			{
				LOG.error("invalidate - getEqUnit: ", e);
			}
		}
		else
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info("System [" + systemId + "] not found");
			}
		}
	}

	/**
	 * Decrypts a password
	 * 
	 * Decrypts a password using Blowfish.
	 * 
	 * @param id
	 *            the session ID supplied from Equation. This is used to identify the Blowfish key.
	 * 
	 * @param encPassword
	 *            The encrypted password
	 * 
	 * @return The password in plain text after being decrypted.
	 */
	public String decryptPassword(String id, String encPassword)
	{
		// Get the relevant Blowfish key
		// (only used once so remove from hashtable)
		byte[] bfKey = equationBFKeys.remove(id);
		if (bfKey == null)
		{
			LOG.error("key not found");
			return "";
		}

		// Convert password to byte array
		int numBytes = encPassword.length() / 2;
		byte[] passwordBytes = new byte[numBytes];
		EQBinConverter.hexStrToBytes(encPassword, passwordBytes, 0, 0, numBytes);

		// Create Blowfish object using the correct key and decrypt the password
		EQBlowfish cipher = new EQBlowfish(bfKey);
		char[] password = cipher.decryptBytesToString(passwordBytes);

		try
		{
			return new String(password).trim().substring(0, 64);
		}
		catch (Exception e)
		{
			LOG.error("password length incorrect");
			return "";
		}
	}

	/**
	 * Exchange partial Blowfish encryption keys
	 * 
	 * Receive the partial key from Equation. Generate and return the partial key from here. Set the full encryption key in
	 * Blowfish.
	 * 
	 * @param id
	 *            the session ID supplied from Equation. This will be used to identify the Blowfish key in the subsequent call to
	 *            getEqSession().
	 * @param key
	 *            the partial key supplied from Equation in 'hex string' format. It is expected to be 32 characters long
	 *            representing a 16 byte key.
	 * 
	 * @return The newly generated partial key in 'hex string' format
	 */
	public String exchangeEncryptionKey(String id, String key)
	{
		if (key.length() != 32)
		{
			LOG.error("key length is not 32");
			return "";
		}

		// Convert key to byte array
		byte[] inKey = new byte[16];
		EQBinConverter.hexStrToBytes(key, inKey, 0, 0, 16);
		key = "";

		// create our half of the key
		byte[] partialKey = new byte[16];
		EQBlowfish.generateKey(partialKey);
		String partialKeyHexStr = EQBinConverter.bytesToHexStr(partialKey);

		// create the full key
		byte[] fullKey = new byte[32];
		System.arraycopy(partialKey, 0, fullKey, 0, 16);
		System.arraycopy(inKey, 0, fullKey, 16, 16);

		// Store the full key
		equationBFKeys.put(id, fullKey);

		// overwrite the key data
		EQBlowfish.generateKey(inKey);
		EQBlowfish.generateKey(partialKey);

		return partialKeyHexStr;
	}

	/**
	 * Generate a session identifier
	 * 
	 * generate 32 random bytes and convert them to hex string format
	 * 
	 * @return the session id
	 */
	private String generateSessionID()
	{
		byte[] id = new byte[32];
		EQBlowfish.generateKey(id);

		return EQBinConverter.bytesToHexStr(id);
	}

	/**
	 * Processing to perform initialisation
	 * 
	 * Currently this only involves updating the GH record for certain session types
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param sessionType
	 *            type of session
	 */
	private void logOnSession(String sessionIdentifier, int sessionType)
	{
		try
		{
			String ghApplication = null;
			if (sessionType == SESSION_FULL_DESKTOP)
			{
				ghApplication = "EQD";
			}
			if (sessionType == SESSION_BANKFUSION)
			{
				ghApplication = "BF";
			}
			if (sessionType == SESSION_SRVWEB)
			{
				ghApplication = "SRVWEB";
			}
			if (ghApplication != null)
			{
				// Is there an EqLogin?
				EquationLogin eqLogin = EquationCommonContext.getContext().getEquationLogin(sessionIdentifier);
				String tcpIp = "";
				if (eqLogin != null)
				{
					tcpIp = eqLogin.getIpAddress();
				}

				// Modify the GH record added by W96HMC. Also set the type to "EQD"
				// for the Q14 List of active jobs enquiry (KMENUL opt 7, F6)
				EquationUser eqUser = getEquationUser(sessionIdentifier);
				EquationStandardSession session = eqUser.getSession();
				session.updateGH("M", "", "", "Y", ghApplication, tcpIp);
			}
		}
		catch (EQException e)
		{
			LOG.error(e);
		}
	}

	/**
	 * Log off an Equation User
	 * 
	 * @param user
	 *            - the user to log off
	 */
	private void logOffEquationUser(EquationUser user)
	{
		String sessionId = null;
		try
		{
			if (user == null)
			{
				return;
			}

			// retrieve a session
			EquationStandardSession session = user.getSession();

			if (session == null)
			{
				return;
			}
			sessionId = session.getSessionIdentifier();

			LOG.info("EquationCommonContext.logOffEquationUser - Logging off Equation user " + user.getUserId());
			if (equationPropertiesBean.isGlobalProcessingGoodToGo())
			{
				GlobalAuditingManager globalAuditingManager = new GlobalAuditingManager(session);
				globalAuditingManager.auditLogoff();
			}

			// connection still active?
			if (session.getConnectionWrapper() != null && !session.getConnectionWrapper().isClosed()
							&& !session.getConnectionWrapper().isXA())
			{
				// Remove the GH record
				session.updateGH("D", "", "", "", "", "");
			}

			EquationUnit unit = user.getEquationUnit();
			// TODO If this is a user pooled session then just return to the pool
			EquationControl.logOffFromEquation(user.getSession().getConnection(), unit.getEquationSystem().getSystemId(), unit
							.getUnitId());

			// Log off
			user.logOffUser();
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
		finally
		{
			if (sessionId != null)
			{
				removeSession(sessionId);
			}
		}
	}

	/**
	 * Processing to perform clean up when session has ended
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @equation.external
	 */
	public void logOffSession(String sessionIdentifier)
	{
		LOG.info("EquationCommonContext.logOffSession - Logging off session");

		// Must log off any GP users first - then the primary user
		GlobalSessionHandler globalSessionHandler = EquationCommonContext.getContext().getGlobalSessionHandler();

		if (globalSessionHandler != null)
		{
			List<EquationUser> gpUsers = globalSessionHandler.getGlobalEquationUsers(sessionIdentifier);

			for (EquationUser user : gpUsers)
			{
				logOffEquationUser(user);
			}
		}

		EquationUser primaryUser = getEquationUser(sessionIdentifier);
		logOffEquationUser(primaryUser);
	}

	/**
	 * Remove a given session ID from the equation logins and equation users maps
	 * 
	 * @param sessionIdentifier
	 *            - the ID to remove
	 */
	public void removeSession(String sessionIdentifier)
	{
		// remove from cache
		if (equationLogins.containsKey(sessionIdentifier))
		{
			equationLogins.remove(sessionIdentifier);
		}

		if (equationUsers.containsKey(sessionIdentifier))
		{
			equationUsers.remove(sessionIdentifier);
		}
	}

	/**
	 * Get an enumeration of all the session identifiers
	 * 
	 * @return an enumeration of all the session identifiers
	 */
	public Enumeration<String> getSessionIdentifiers()
	{
		return equationLogins.keys();
	}

	/**
	 * Indicates whether Service Composer is installed.
	 * 
	 * @return true if Service Composer is installed
	 */
	public static boolean isServiceComposerInstalled()
	{
		return serviceComposerInstalled;
	}

	/**
	 * Sets whether Service Composer is installed.
	 */
	private static void setServiceComposerInstalled()
	{
		serviceComposerInstalled = false;
		try
		{
			serviceComposerInstalled = ServiceComposerCheck.isServiceComposerInstalled();
		}
		catch (Exception e)
		{
			// This situation is expected when the Service Composer application is not installed,
			// so no error handling is required.
		}
		catch (Throwable t)
		{
			// This situation is expected when the Service Composer application is not installed,
			// so no error handling is required.
		}
		if (LOG.isInfoEnabled())
		{
			LOG.info("isServiceComposerInstalled - " + serviceComposerInstalled);
		}
	}
	/**
	 * Indicates whether Meridian is installed.
	 * 
	 * @return true if Meridian is installed
	 */
	public static boolean isMeridianInstalled()
	{
		return meridianInstalled;
	}
	/**
	 * Set whether Meridian is installed
	 */
	private static void setMeridianInstalled()
	{
		meridianInstalled = false;
		try
		{
			meridianInstalled = MeridianCheck.isMeridianInstalled();
		}
		catch (Exception e)
		{
			// This situation is expected when Meridian application is not installed,
			// so no error handling is required.
		}
		catch (Throwable e)
		{
			// This situation is expected when Meridian application is not installed,
			// so no error handling is required.
		}
		if (LOG.isInfoEnabled())
		{
			LOG.info("setMeridianInstalled - " + meridianInstalled);
		}
	}
	/**
	 * Indicates whether BankFusion is installed.
	 * 
	 * @return true if BankFusion is installed
	 */
	public static boolean isBankFusionInstalled()
	{
		return bankFusionInstalled;
	}

	/**
	 * Set whether BankFusion is installed
	 */
	private static void setBankFusionInstalled()
	{
		bankFusionInstalled = false;
		// If Meridian is installed we don't want to run with BankFusion
		if (isMeridianInstalled())
		{
			return;
		}
		try
		{
			bankFusionInstalled = BankFusionCheck.isBankFusionInstalled();
		}
		catch (Exception e)
		{
			// This situation is expected when BankFusion application is not installed,
			// so no error handling is required.
		}
		catch (Throwable e)
		{
			// This situation is expected when BankFusion application is not installed,
			// so no error handling is required.
		}
		if (LOG.isInfoEnabled())
		{
			LOG.info("setBankFusionInstalled - " + bankFusionInstalled);
		}
	}

	/**
	 * Indicates whether XA is to be used.
	 * 
	 * @return true if XA is to be used
	 */
	public boolean isXAUsed()
	{
		return xaUsed;
	}

	/**
	 * Sets whether XA is to be used.
	 */
	private void setXAUsed()
	{
		xaUsed = false;
		String xa = getConfigProperty("eq.xa.supported");
		if (xa.trim().equals("true"))
		{
			xaUsed = true;
		}
	}
	/**
	 * Sets whether XA is to be used.
	 */
	public void setXAUsed(boolean xaUsed)
	{

		this.xaUsed = xaUsed;

	}
	/**
	 * Sets whether XA is to be used.
	 */
	public String getDefaultDataSourceName()
	{
		return getConfigProperty("eq.admin.defaultJndiName");
	}
	/**
	 * Determine if WebFacing is installed
	 * 
	 * @return true if WebFacing is installed
	 */
	public static boolean isWebFacing()
	{
		return webFacing;
	}

	/**
	 * Set whether WebFacing is installed
	 * 
	 * @param webFacing
	 *            - true if WebFacing is installed
	 */
	public static void setWebFacing(boolean webFacing)
	{
		EquationCommonContext.webFacing = webFacing;
	}

	/**
	 * Determine whether the session is part of a bigger transaction (linked transaction)
	 * 
	 * @param sessionType
	 *            - the session type
	 * 
	 * @return true if the session is part of a bigger transaction
	 */
	public static boolean isLinkedSession(int sessionType)
	{
		return sessionType == SESSION_LINK_MODE || sessionType == SESSION_LINK_MODE_EXT || sessionType == SESSION_LINK_MODE_RECOV;
	}

	/**
	 * Determine whether the session is a desktop session
	 * 
	 * @param sessionType
	 *            - the session type
	 * 
	 * @return true if the session is a desktop session
	 * @equation.external
	 */
	public static boolean isDesktopSession(int sessionType)
	{
		return sessionType == SESSION_DIRECT_TRANS_DESKTOP || sessionType == SESSION_FULL_DESKTOP
						|| sessionType == SESSION_CHILD_DRILLDOWN_SAMEWINDOW || isChildDesktopSession(sessionType);
	}

	/**
	 * Determine whether the session is a child-desktop session
	 * 
	 * @param sessionType
	 *            - the session type
	 * 
	 * @return true if the session is a child-desktop session
	 * @equation.external
	 */
	public static boolean isChildDesktopSession(int sessionType)
	{
		return sessionType == SESSION_CHILD_MODE || sessionType == SESSION_CHILD_PLAYBUTTON
						|| sessionType == SESSION_CHILD_DRILLDOWN || sessionType == SESSION_CHILD_DRILLDOWN_SAMEWINDOW
						|| sessionType == SESSION_CHILD_DRILLDOWN_SAMEWINDOW_EDIT;
	}

	/**
	 * Determine whether the session is a child-desktop session (new modal window)
	 * 
	 * @param sessionType
	 *            - the session type
	 * 
	 * @return true if the session is a child-desktop session
	 * @equation.external
	 */
	public static boolean isChildDesktopSessionPopup(int sessionType)
	{
		return sessionType == SESSION_CHILD_MODE || sessionType == SESSION_CHILD_PLAYBUTTON
						|| sessionType == SESSION_CHILD_DRILLDOWN;
	}

	/**
	 * Determine whether the session is a child-desktop session and keys should always be protected
	 * 
	 * @param sessionType
	 *            - the session type
	 * 
	 * @return true if the session is a child-desktop session
	 * @equation.external
	 */
	public static boolean isChildDesktopSessionKeyProtect(int sessionType)
	{
		return sessionType == SESSION_CHILD_PLAYBUTTON || sessionType == SESSION_CHILD_DRILLDOWN
						|| sessionType == SESSION_CHILD_DRILLDOWN_SAMEWINDOW;
	}

	/**
	 * Determine whether the session is an recovery session
	 * 
	 * @param sessionType
	 *            - the session type
	 * 
	 * @return true if the session is a desktop session
	 * @equation.external
	 */
	public static boolean isRecoverySession(int sessionType)
	{
		return sessionType == SESSION_RECOVERY || sessionType == SESSION_LINK_MODE_RECOV;
	}

	/**
	 * Determine whether the session is an external input session
	 * 
	 * @param sessionType
	 *            - the session type
	 * 
	 * @return true if the session is a desktop session
	 * @equation.external
	 */
	public static boolean isExtInpSession(int sessionType)
	{
		return sessionType == SESSION_EXT_INPUT || sessionType == SESSION_LINK_MODE_EXT;
	}

	/**
	 * Determine whether the session is a non-browser mode session
	 * 
	 * @param sessionType
	 *            = the session type
	 * 
	 * @return true if the session is a browser session
	 * @equation.external
	 */
	public static boolean isNonBrowserMode(int sessionType)
	{
		return sessionType == SESSION_API_MODE || sessionType == SESSION_OTHER_MODE || sessionType == SESSION_EXT_INPUT
						|| sessionType == SESSION_LINK_MODE_EXT || sessionType == SESSION_RECOVERY
						|| sessionType == SESSION_LINK_MODE_RECOV;
	}

	/**
	 * Determine whether the password type is an unencrypted or base64 profile token
	 * 
	 * @param passwordType
	 *            the passwordType to test
	 * 
	 * @return true if the password is an un-encrypted profile token
	 */
	private static boolean isUnencryptedProfileTokenPassword(String passwordType)
	{
		return PASSWORD_TYPE_PROFILETOKEN_BASE64.equals(passwordType) || PASSWORD_TYPE_PROFILETOKEN_PLAIN.equals(passwordType);
	}

	/**
	 * This method will create a new <code>EquationStandardSession </code> using the passed parameters.
	 * 
	 * @param system
	 *            this is the current system.
	 * @param unitId
	 *            this is the current unitId.
	 * @param userId
	 *            this is the current userId .
	 * @param password
	 *            this is the current password.
	 * @return a new <code>EquationStandardSession </code> using the passed parameters.
	 * @equation.external
	 */
	public EquationStandardSession getEquationStandardSession(String system, String unitId, String userId, String password)
	{
		EquationStandardSession session = null;
		EquationUnit unit = null;
		try
		{
			if (getEquationSystem(system) == null)
			{
				authenticate(system, unitId, userId, password, PASSWORD_TYPE_TEXT_PLAIN);
				unit = getEquationSystem(system).getUnit(unitId);
				session = unit.getEquationSessionPool().getEquationStandardSession();
			}
			else
			{
				// Without global processing we just use the current session
				unit = getEquationSystem(system).getUnit(unitId);
				session = unit.getEquationSessionPool().getEquationStandardSession();
			}
		}
		catch (Exception exception)
		{
			// TODO Should this throw an EQException?
			if (LOG.isErrorEnabled())
			{
				LOG.error("There was an error trying to create a new equation session.");
			}
		}
		return session;
	}

	/**
	 * Get the results of a query given a session ID and PV data
	 * 
	 * @param sessionIdentifier
	 * @param service
	 * @param decode
	 * @param security
	 * @param primaryTable
	 * @param serviceKey
	 * @param query
	 * @param start
	 * @param direction
	 * @param maxResults
	 * @return a String of the results of the query.
	 * @throws EQException
	 */
	public String getQueryResults(String sessionIdentifier, String service, String decode, String security, String primaryTable,
					String serviceKey, String query, String start, int direction, int maxResults) throws EQException
	{
		EquationPVMetaData equationPVMetaData = getEquationUnit(sessionIdentifier).getPVMetaData(service);

		// get a session either from the GP or from the user session
		List<EquationStandardSession> sessions = new ArrayList<EquationStandardSession>();

		// NOTE: If this is not a GP PV or no GP, then make sure it ignore global processing.
		// NOTE: This has not been tested with GP and must be tested with a GP env
		// NOTE: This comment must be removed once tested
		if (globalSessionHandler != null || equationPVMetaData.isGlobalConsolidatedPrompt())
		{
			sessions = getGlobalProcessingEquationStandardSessions(sessionIdentifier);
		}
		else
		{
			sessions.add(getEquationUser(sessionIdentifier).getSession());
		}

		// Get us a consolidator...
		EquationConsolidator consolidator = new EquationConsolidator(sessions, equationPVMetaData);

		// Derive the SQL info from the PV meta data
		List<EquationPVFieldMetaData> equationPVFieldMetaDataList = equationPVMetaData.getFieldMetaData();
		List<String> selectFieldsA = new ArrayList<String>();
		for (EquationPVFieldMetaData equationPVFieldMetaData : equationPVFieldMetaDataList)
		{
			if (!equationPVFieldMetaData.getDb().trim().equals(""))
			{
				selectFieldsA.add(equationPVFieldMetaData.getDb());
			}
		}

		String[] keyFields = Toolbox.getStringArray(equationPVMetaData.getDecodeMetaData(decode).getKeyFields());
		String fromData = equationPVMetaData.getDecodeMetaData(decode).getSqlFrom();
		String fromPart = "";
		if (!fromData.trim().equals(""))
		{
			fromPart = SQLToolbox.FROM + equationPVMetaData.getDecodeMetaData(decode).getSqlFrom();
		}
		String wherePart = equationPVMetaData.getDecodeMetaData(decode).getSqlWhere();

		// Create us a consolidated table...
		String selectPart = equationPVMetaData.getDecodeMetaData(decode).getSqlSelect();
		EquationConsolidatedTable table = null;

		int outputBytesSize = equationPVMetaData.isGlobalConsolidatedPrompt() ? 31 : 23;

		if (selectPart == null || selectPart.equals(""))
		{
			String[] selectFields = new String[selectFieldsA.size()];
			String selectPart2 = Arrays.toString(selectFieldsA.toArray(selectFields));
			String selectPart3 = "SELECT " + selectPart2.substring(1, selectPart2.length() - 1) + " ";
			table = new EquationConsolidatedTable(selectPart3, keyFields, fromPart, wherePart, consolidator, outputBytesSize,
							equationPVMetaData.isGlobalConsolidatedPrompt());
		}
		else
		{
			table = new EquationConsolidatedTable(selectPart, keyFields, fromPart, wherePart, consolidator, outputBytesSize,
							equationPVMetaData.isGlobalConsolidatedPrompt());
		}

		// Execute the query, it'll return a long string with delimiters in it
		String results = consolidator.executeConsolidatedTableQuery(table, query, start, direction, maxResults);

		return results;
	}

	/**
	 * Get a property from the EquationProperties file given a key
	 * 
	 * @param key
	 *            - the key
	 * @return the property requested, or null.
	 * @equation.external
	 */
	public Properties getProperties(String key)
	{
		Properties properties = null;
		if (equationProperties.containsKey(key))
		{
			properties = equationProperties.get(key);
		}
		else
		{
			// Need to check if we can load the properties file for the given key name
			InputStream propertiesIS = Thread.currentThread().getContextClassLoader().getResourceAsStream(key + ".properties");
			if (propertiesIS != null)
			{
				try
				{
					properties = new Properties();
					properties.load(propertiesIS);
					equationProperties.put(key, properties);
				}
				catch (IOException e)
				{
					LOG.error("Cannot Load Properties", e);
				}
			}
		}
		return properties;
	}

	/**
	 * Get a property from the EquationConfigurationProperties file given a key
	 * 
	 * @param key
	 *            - the key
	 * @return a String of the the property requested, or null.
	 * @equation.external
	 */
	public static String getConfigProperty(String key)
	{
		Properties properties = getConfigProperties();

		String property = properties != null ? properties.getProperty(key, "") : "";
		return property.trim() != null ? property : "";
	}

	/**
	 * Get a property from the EquationConfigurationProperties file
	 * 
	 * @param key
	 *            - the key
	 * @param defaultValue
	 *            The default value to return if the property did not exist, or was blank
	 * 
	 * @return a String which will be the requested property value, or the supplied default value
	 */
	public static String getConfigProperty(String key, String defaultValue)
	{
		Properties properties = getConfigProperties();
		String property = properties != null ? properties.getProperty(key, defaultValue) : defaultValue;
		// If the property exists, but is blank, then default the value:
		if (property.length() == 0)
		{
			property = defaultValue;
		}
		return property.trim();
	}

	/**
	 * Get the minimum fields given an option ID
	 * 
	 * @param optionId
	 *            - the option ID
	 * @return a String[] of the minimum fields
	 */
	public String[] getMinimumFields(String optionId)
	{
		String fields = getProperties("minimumFields").getProperty(optionId);
		if (fields == null)
		{
			return null;
		}
		else if (fields.contains(","))
		{
			String[] fieldsArray = fields.split(",");
			Arrays.sort(fieldsArray);
			return fieldsArray;
		}
		else
		{
			return new String[] { fields };
		}
	}

	/**
	 * This method will populate the <code>EquationConfigurationPropertiesBean</code> <br>
	 * bean; using the predefined data in GPX4 table.
	 */
	public synchronized void loadEquationConfigurationPropertiesBean()
	{
		// if the bean has already been initialised; this method will not be executed.
		if (equationPropertiesBean.isInitialised())
		{
			return;
		}

		DaoFactory daoFactory = new DaoFactory();
		IGPX1RecordDaoImp dao = daoFactory.getGPX1DaoIMP(new GPX1RecordDataModel());
		List<AbsRecord> resultDataModels = null;
		int currentIndex = 0;
		String[] systemIds = null;
		String[] unitIds = null;
		String[] unitTypes = null;
		String[] unitDescriptions = null;
		resultDataModels = dao.getUnitsAndSystems();

		if (!resultDataModels.isEmpty())
		{
			systemIds = new String[resultDataModels.size()];
			unitIds = new String[resultDataModels.size()];
			unitTypes = new String[resultDataModels.size()];
			unitDescriptions = new String[resultDataModels.size()];

			for (AbsRecord absRecord : resultDataModels)
			{
				if (absRecord instanceof GPX1RecordDataModel)
				{
					GPX1RecordDataModel gPX1RecordDataModel = (GPX1RecordDataModel) absRecord;

					systemIds[currentIndex] = gPX1RecordDataModel.getSystemName();
					unitIds[currentIndex] = gPX1RecordDataModel.getUnitMnemonic();
					unitTypes[currentIndex] = gPX1RecordDataModel.getUnitType();
					unitDescriptions[currentIndex] = gPX1RecordDataModel.getUnitDescription();
					currentIndex++;
				}
			}
		}
		else
		{
			LOG.error("There is not any GP user defined in the GP table.");
			// Don't throw an error; this happens during initial setup when GPX is empty, MC must be able to proceed despite
			// this so that user can populate GPX table via MC!
		}

		equationPropertiesBean.setGlobalProcessingGoodToGo(true);
		equationPropertiesBean.initialise(systemIds != null ? systemIds : new String[0], unitIds != null ? unitIds : new String[0],
						unitTypes != null ? unitTypes : new String[0], unitDescriptions != null ? unitDescriptions : new String[0]);
	}

	/**
	 * This method will return a List of <code>EquationStandardSession</code> based for given user derived from the session
	 * identifier and the values of the <code>globalprocessing.properties</code>.
	 * 
	 * @return a List of <code>EquationStandardSession</code>
	 * @equation.external
	 */
	public List<EquationStandardSession> getGlobalProcessingEquationStandardSessions(String sessionIdentifier)
	{
		if (globalSessionHandler == null)
		{
			createGlobalSessionHandler();
		}
		return globalSessionHandler.getAllAvailableGlobalProcessingEquationStandardSessions(sessionIdentifier);
	}

	/**
	 * This method create an instance of EquationUser for each unit the user is authorised to derived from the session identifier
	 * 
	 * @throws EQException
	 *             if there any problem.
	 */
	private void createGlobalProcessingEquationUsers(String sessionIdentifier) throws EQException
	{
		// home session
		EquationStandardSession homeSession = getEquationUser(sessionIdentifier).getSession();
		EquationLogin existinglogin = getEquationLogin(sessionIdentifier);

		// contains tasks that are being executed concurrently (all of which should complete)
		final List<Future<Boolean>> parallelTasks = new ArrayList<Future<Boolean>>();

		// need to see if we have a global processing implemented and
		// home unit has GP installed and the home unit is part of the group...
		if (equationPropertiesBean.isGlobalProcessingGoodToGo() && homeSession.getUnit().hasGlobalProcessing()
						&& equationPropertiesBean.containsUnit(homeSession.getSystemId(), homeSession.getUnitId()))
		{
			for (UnitPropertiesBean unit : equationPropertiesBean.getUnitsList())
			{
				String systemId = unit.getSystemId();
				String unitId = unit.getUnitId();

				if (existinglogin.getUserId().equals(existinglogin.getUserId())
								&& existinglogin.getPassword().equals(existinglogin.getPassword())
								&& existinglogin.getUnitId().equals(unitId) && existinglogin.getSystem().equals(systemId))
				{
					// We are going to put the home user with an additional key back onto the hash...
					String sessionKey = unit.getSystemId() + ":" + unit.getUnitId() + ":" + sessionIdentifier;
					equationUsers.put(sessionKey, getEquationUser(sessionIdentifier));
				}
				else
				{
					parallelTasks.add(executors.submit(new GlobalProcessingLogonUserAction(systemId, unitId, sessionIdentifier,
									existinglogin)));
				}
			}
		}

		// wait for all 'parellelTasks' to complete
		for (Future<Boolean> task : parallelTasks)
		{
			try
			{
				task.get();
			}
			catch (InterruptedException ie)
			{
				// server being shut down?
				throw new EQException(ie);
			}
			catch (ExecutionException ee)
			{
				if (ee.getCause() instanceof EQException)
				{
					// simply re-throw
					throw (EQException) ee.getCause();
				}
				else
				{
					// must wrap...
					throw new EQException(ee);
				}
			}
		}
	}

	/**
	 * This method will check if the Global processing is installed. it will be true only if the current unit has the GP enhancement
	 * installed and the global pool connection is set.
	 * 
	 * @param sessionIdentifier
	 *            this is the home session identifier
	 */
	public boolean checkIfGPIsInstalled(String sessionIdentifier)
	{
		boolean result = false;
		EquationUser equationUser = getEquationUser(sessionIdentifier);

		// No Equation user
		if (equationUser == null)
		{
			return false;
		}

		// Get the session
		EquationStandardSession homeSession = equationUser.getSession();

		try
		{
			result = (homeSession.getUnit().hasGlobalProcessing() && equationPropertiesBean.isGlobalProcessingGoodToGo());
		}
		catch (Exception exception)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(exception);
			}
		}
		return result;
	}

	/**
	 * Reinitialise secondary sessions for GP users when a unit is newly available
	 * 
	 * @param system
	 *            - the system containing the unit which has become available
	 * @param unit
	 *            - the unit which has become available
	 */
	public void reinitialiseGPUserSessions(String system, String unit)
	{
		Map<String, EquationLogin> userLogins = new HashMap<String, EquationLogin>();

		// Build up a Map of user keys with existing Logins so we don't have to synchronise over the user
		// hashtable while logins are going on
		synchronized (equationUsers.entrySet())
		{
			for (Entry<String, EquationUser> userEntry : equationUsers.entrySet())
			{
				if (userEntry.getValue() != null)
				{
					if (userEntry.getValue().getSession() != null)
					{
						// Get existing logon
						EquationLogin existinglogin = getEquationLogin(userEntry.getValue().getSession().getSessionIdentifier());
						userLogins.put(userEntry.getKey(), existinglogin);
					}
				}
			}
		}

		for (Entry<String, EquationLogin> userEntry : userLogins.entrySet())
		{
			if (userEntry.getKey().contains(":"))
			{
				String[] sessionKey = userEntry.getKey().split(":");
				EquationLogin existingLogin = userEntry.getValue();

				// Check session ID exists with units for all keys in the bean
				for (UnitPropertiesBean sessiondes : EquationConfigurationPropertiesBean.getInstance().getUnitsList())
				{
					String unitID = sessiondes.getUnitId();
					String systemID = sessiondes.getSystemId();
					if (!(sessionKey[0].equals(systemID) && sessionKey[1].equals(unitID)))
					{
						// This is not the system and unit for the session in question.
						// do the users contain a key for this?
						if (!equationUsers.containsKey(systemID + ":" + unitID + ":" + sessionKey[2]))
						{
							initialiseGPUser(systemID, unitID, existingLogin);
						}
					}
				}
			}
		}
	}

	/**
	 * Initialise an individual GP user session given an existing session and the system/unit to create it for
	 * 
	 * @param systemID
	 *            - the system ID
	 * @param unitID
	 *            - the unit ID
	 * @param existingUserLogin
	 *            - the existing user login
	 */
	private void initialiseGPUser(String systemID, String unitID, EquationLogin existingUserLogin)
	{
		try
		{
			GlobalProcessingLogonUserAction logon = new GlobalProcessingLogonUserAction(systemID, unitID, existingUserLogin
							.getSessionId(), existingUserLogin);
			Boolean result = logon.call();

			if (!result)
			{
				LOG.error("Error initialising new GP User session for " + systemID + "/" + unitID + " for user: "
								+ existingUserLogin.getUserId());
			}
		}
		catch (EQException e)
		{
			LOG.error("Error initialising new GP User session for " + systemID + "/" + unitID + " for user: "
							+ existingUserLogin.getUserId());
		}
	}

	/**
	 * Return the language properties from the Equation Desktop, if EquationCommon is being used independently of the Equation
	 * Desktop then the resources will not be loaded and this will return an empty Properties object. The
	 * <code>getLanguageResource</code> method will always return a <code>null</code> value.
	 * 
	 * @return the language properties
	 */
	public Properties getEquationDesktopLanguageResources()
	{
		try
		{
			if (equationDesktopLanguageResourcesLoaded)
			{
				return equationDesktopLanguageResources;
			}
			else
			{
				if (!equationDesktopLanguageResourcesNotFound)
				{
					InputStream propertiesIS = Thread.currentThread().getContextClassLoader().getResourceAsStream(
									"/com/misys/equation/ui/resources/ApplicationResources.properties");
					if (propertiesIS != null)
					{
						equationDesktopLanguageResources.load(propertiesIS);
					}
					else
					{
						equationDesktopLanguageResourcesNotFound = true;
					}
				}
			}
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		return equationDesktopLanguageResources;
	}

	/**
	 * Return the language resource for the EquationDesktop
	 * 
	 * @param eqUser
	 *            - the Equation user
	 * @param resourceId
	 *            - the resource Id
	 * 
	 * @return the language resource
	 */
	public String getLanguageResource(EquationUser eqUser, String resourceId)
	{
		// retrieve based on user's language
		String langid = eqUser.getLanguageId();
		String str = getEquationDesktopLanguageResources().getProperty(langid + "." + resourceId);

		// not found, then load default language
		if (str == null)
		{
			langid = EquationUser.DEF_LANG;
			str = getEquationDesktopLanguageResources().getProperty(langid + "." + resourceId);
		}

		// check HBXRecords
		if (str == null)
		{
			// Get the hbx records
			EquationRecords records = eqUser.getEquationUnit().getRecords();
			String owner = HBXRecordDataModel.MISYS_OWNED_TEXT;
			String keyType = HBXRecordDataModel.HBX_LABEL_PREFIX;

			// check User Language First
			langid = eqUser.getLanguageId();
			if (records.getHBXRecords().containsKey(owner + langid + keyType + resourceId))
			{
				HBXRecordDataModel hbxRecord = records.getHBXRecords().get(owner + langid + keyType + resourceId);
				str = hbxRecord.getText();
			}
			else
			{
				// Check Default Language
				langid = EquationUser.DEF_LANG;

				if (records.getHBXRecords().containsKey(owner + langid + keyType + resourceId))
				{
					HBXRecordDataModel hbxRecord = records.getHBXRecords().get(owner + langid + keyType + resourceId);
					str = hbxRecord.getText();
				}
			}
		}

		return str;
	}

	/**
	 * Get the map of Equation users stored on the context
	 * 
	 * @return the map of Equation users stored on the context
	 * @equation.external
	 */
	public Map<String, EquationUser> getEqUsers()
	{
		return equationUsers;
	}

	/**
	 * Get the map of Equation logins stored on the context
	 * 
	 * @return the map of Equation logins stored on the context
	 */
	public Map<String, EquationLogin> getEqLogins()
	{
		return equationLogins;
	}
	/**
	 * Performs common layer startup processing
	 */
	public void startup()
	{
		String nodeId = EquationCommonContext.getConfigProperty("eq.cluster.node.id");
		if (Toolbox.stringNotBlank(nodeId))
		{
			clusterService = new ClusterService(nodeId);
			clusterService.start();
		}
	}

	/**
	 * Performs common layer shutdown processing
	 */
	public void shutdown()
	{
		if (clusterService != null)
		{
			clusterService.stop();
		}
		this.executors.shutdown();
	}

	/**
	 * Returns an ExecutorService thread pool. This thread pool should be shut down when no longer needed.
	 * 
	 * @return The ExecutorService thread pool.
	 */
	public ExecutorService getExecutors()
	{
		return executors;
	}

	/**
	 * This method will run the system monitor task for global processing.
	 */
	public void runSystemStatusManagerMonitor()
	{
		systemStatusManagerMonitor = SystemStatusManagerMonitor.getInstance();
		systemStatusManagerMonitor.monitorSystems();
	}

	/**
	 * This method will run the system monitor task for 3rd party applications.
	 * 
	 * @param unitIds
	 *            array of units to monitor
	 * @param systemIds
	 *            array of systems the units reside on
	 * @param userNames
	 *            array of user profiles to log onto the systems with
	 * @param passwords
	 *            array of passwords for the users
	 * @param sleepTime
	 *            integer value in milliseconds for the monitor thread to sleep between polling
	 * @throws EQException
	 *             thrown when the arrays passed in do not have the same number of elements
	 * @equation.external
	 */
	public void runSystemStatusManagerMonitor(String[] unitIds, String[] systemIds, String[] userNames, String[] passwords,
					int sleepTime) throws EQException
	{
		// match the array lengths against each other and if not the same throw exception back to caller
		if (!(unitIds.length == systemIds.length ? systemIds.length == userNames.length ? userNames.length == passwords.length ? true
						: false
						: false
						: false))
		{
			throw new EQException("Unit, System, User and Password Array sizes must all match");
		}
		else
		{
			// otherwise set up the monitor with the passed parameters
			systemStatusManagerMonitor = SystemStatusManagerMonitor
							.getInstance(unitIds, systemIds, userNames, passwords, sleepTime);
			systemStatusManagerMonitor.monitorSystems();
		}
	}
	/**
	 * This method will only create the GlobalSessionHandler instance used to handle GP session.
	 */
	public void createGlobalSessionHandler()
	{
		globalSessionHandler = new GlobalSessionHandler();
		globalSessionHandler.setEquationUsers(equationUsers);
	}

	/**
	 * This method will validate all system units properties.
	 * 
	 * @return true is the properties were defined correctly.
	 */
	public boolean validateSystemsAndUnits()
	{
		String[] unitIds = EquationCommonContext.getConfigProperty("eq.units").split(",");
		String[] systemIds = EquationCommonContext.getConfigProperty("eq.systems").split(",");
		String[] userIds = EquationCommonContext.getConfigProperty("eq.admin.users").split(",");
		String[] passwords = EquationCommonContext.getConfigProperty("eq.admin.passwords").split(",");

		Map<String, String[]> aliasDetails = EquationCommonContext.getAdminAlias();
		if (aliasDetails != null)
		{
			userIds = aliasDetails.get("Users");
			passwords = aliasDetails.get("Passwords");
		}

		for (int index = 0; index < unitIds.length && !unitIds[index].equals(""); index++)
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info("Initialising unit: [" + unitIds[index] + "]");
			}
			if (systemIds.length <= index || systemIds[index].equals(""))
			{
				LOG.error("Cannot initialise unit " + unitIds[index] + " due to unspecified system id");
				return false;
			}
			if (userIds.length <= index || userIds[index].equals(""))
			{
				LOG.error("Cannot initialise unit " + unitIds[index] + " due to unspecified user id");
				return false;
			}
			if (passwords.length <= index || passwords[index].equals(""))
			{
				LOG.error("Cannot initialise unit " + unitIds[index] + " due to unspecified password");
				return false;
			}
		}
		return true;
	}

	/**
	 * Get the global session handler
	 * 
	 * @return the global session handler
	 */
	public GlobalSessionHandler getGlobalSessionHandler()
	{
		return globalSessionHandler;
	}

	/**
	 * Returns the current set of logins for a user
	 * 
	 * @param userId
	 *            - The user for whom to return the set of logins
	 * 
	 * @return the current set of logins for a user
	 */
	public List<EquationLogin> getEquationLogins(String userId)
	{
		List<EquationLogin> result = new ArrayList<EquationLogin>();

		final Enumeration<EquationLogin> equationLoginsEnum = equationLogins.elements();

		while (equationLoginsEnum.hasMoreElements())
		{
			EquationLogin equationLogin = equationLoginsEnum.nextElement();
			if (equationLogin.getUserId().equalsIgnoreCase(userId))
			{
				result.add(equationLogin);
			}
		}
		return result;
	}

	/**
	 * Gets the formatted plug-in version
	 * 
	 * @return the formatted plug-in version in nnn.nnn.nnn format with zero fill
	 * @equation.external
	 */
	public static String getPluginVersionFormatted()
	{

		String formattedVersionPart1 = formatVersionPart(PLUGIN_VERSION.substring(0, PLUGIN_VERSION.indexOf('.')));
		String formattedVersionPart2 = formatVersionPart(PLUGIN_VERSION.substring(PLUGIN_VERSION.indexOf('.') + 1, PLUGIN_VERSION
						.lastIndexOf('.')));
		String formattedVersionPart3 = formatVersionPart(PLUGIN_VERSION.substring(PLUGIN_VERSION.lastIndexOf('.') + 1,
						PLUGIN_VERSION.length()));

		String formattedVersion = formattedVersionPart1 + "." + formattedVersionPart2 + "." + formattedVersionPart3;
		return formattedVersion;
	}
	/**
	 * This method formats the plug-in version
	 * 
	 * @param versionPart
	 *            - one of the 3 numeric parts of the plug-in version
	 * 
	 * @return the formatted plug-in version padded with zeroes, e.g. "23" will become "023"
	 */
	private static String formatVersionPart(String versionPart)
	{
		// if version part has 1 digit, put 2 leading zeroes
		if (versionPart.length() == 1)
		{
			versionPart = "00" + versionPart;
		}
		// if version part has 2 digits, put 1 leading zero
		else if (versionPart.length() == 2)
		{
			versionPart = "0" + versionPart;
		}

		return versionPart;
	}

	/**
	 * Set list of CCSID that supports RTL. This will be retrieved from the configuration property file. This is needed in order to
	 * do special processing for RTL languages.<br>
	 * 
	 * Before the introduction of this property, a user can only input RTL if the Equation language is RTL and the field is defined
	 * as RTL in the Equation Service.<br>
	 * 
	 * With this, a user can straight away type in RTL language on a non-RTL field or non-RTL Equation language as long as the
	 * database supports that CCSID
	 * 
	 */
	private static void setRtlCcsid()
	{
		String ccsidConfig = getConfigProperty("eq.ccsid.rtl");
		if (ccsidConfig.trim().length() == 0)
		{
			ccsidRTLMap = new String[0];
			return;
		}
		ccsidRTLMap = ccsidConfig.split(",");
		for (int i = 0; i < ccsidRTLMap.length; i++)
		{
			ccsidRTLMap[i] = ccsidRTLMap[i].trim();
		}
	}

	/**
	 * Set list of CCSID that supports RTL. This will be retrieved from the configuration property file
	 * 
	 */
	public static boolean isRtlCcsid(int ccsid)
	{
		// setup when has not been setup
		if (ccsidRTLMap == null)
		{
			setRtlCcsid();
		}

		// no data, return
		if (ccsidRTLMap.length <= 0)
		{
			return false;
		}

		// search for it
		String c2 = String.valueOf(ccsid);
		for (String c : ccsidRTLMap)
		{
			if (c2.equals(c))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Set a property in the equation properties for the key given
	 * 
	 * @param key
	 *            - the key
	 * @param value
	 *            - the value to store
	 */
	public static void setConfigProperty(String key, String value)
	{
		Properties properties = null;
		if (equationProperties.containsKey(CONFIGURATION_PROPERTY_FILE))
		{
			properties = equationProperties.get(CONFIGURATION_PROPERTY_FILE);
		}
		else
		{
			try
			{
				// Need to check if we can load the properties file for the given key name
				InputStream propertiesIS = getPropertyFileInUrlFormat().openStream();

				if (propertiesIS != null)
				{
					properties = new Properties();

					properties.load(propertiesIS);

					for (Entry<Object, Object> propertyName : properties.entrySet())
					{
						LOG.info("Equation Configuration Property name [" + propertyName.getKey() + "], value ["
										+ properties.get(propertyName.getKey()) + "]");
					}

					equationProperties.put(CONFIGURATION_PROPERTY_FILE, properties);
				}
			}
			catch (Exception e)
			{
				LOG.error(e);
			}
		}

		if (properties != null)
		{
			properties.setProperty(key, value);
		}
	}

	/**
	 * Determine if LRP is enabled or disabled
	 */
	public void setLRPEnabled()
	{
		this.lrpEnabled = false;

		String wps = getConfigProperty("eq.enableLRP");
		if (wps.trim().equals("true"))
		{
			this.lrpEnabled = true;
		}
	}

	/**
	 * Check if LRP processing is needed
	 * 
	 * @return true if Bank Fusion is installed and LRP processing is enabled
	 */
	public boolean isLRPProcessing()
	{
		return (isBankFusionInstalled() && lrpEnabled);
	}

	/**
	 * Refresh cache
	 * 
	 * @param sessionIdentifier
	 *            - the session id
	 */
	public void refreshCache(String sessionIdentifier) throws EQException
	{
		EquationUnit equationUnit = getEquationUnit(sessionIdentifier);
		equationUnit.refreshCache();
	}
	/**
	 * Retrieves the Properties object from the equationConfiguration.properties file
	 * 
	 * @return Properties object.
	 */
	public static Properties getConfigProperties()
	{
		Properties properties = null;
		if (equationProperties.containsKey(CONFIGURATION_PROPERTY_FILE))
		{
			properties = equationProperties.get(CONFIGURATION_PROPERTY_FILE);
		}
		else
		{
			// Need to check if we can load the properties file for the given key name

			URL resourceURL = getPropertyFileInUrlFormat();

			properties = new Properties();
			try
			{

				InputStream propertiesIS = resourceURL.openStream();

				properties.load(propertiesIS);
				for (Entry<Object, Object> propertyName : properties.entrySet())
				{
					LOG.info("Equation Configuration Property name [" + propertyName.getKey() + "], value ["
									+ properties.get(propertyName.getKey()) + "]");
				}

				equationProperties.put(CONFIGURATION_PROPERTY_FILE, properties);
			}
			catch (IOException e)
			{
				LOG.error("Cannot Load Properties", e);
			}
		}

		return properties;
	}
	/**
	 * Retrieves the Administration users and related passwords from WAS J2C Alias configuration
	 * 
	 * @return Map containing string array of users keyed by "Users" and string array of passwords keyed by "Passwords"
	 */
	public static Map<String, String[]> getAdminAlias()
	{
		Map<String, String[]> results = null;

		String[] alias = EquationCommonContext.getConfigProperty("eq.admin.aliases").split(",");
		String server = EquationCommonContext.getConfigProperty("eq.application.server");

		if (alias != null && alias[0].trim().length() > 0 && server != null && server.trim().equals("WAS"))
		{

			String[] userIds = new String[alias.length];
			String[] passwords = new String[alias.length];

			CallbackHandler callbackHandler;
			try
			{
				for (int i = 0; i < alias.length && !alias[i].equals(""); i++)
				{

					if (LOG.isInfoEnabled())
					{
						LOG.info("Retrieving administrator alias: [" + alias[i] + "]");
					}
					Map<String, String> map = new HashMap<String, String>();
					map.put(Constants.MAPPING_ALIAS, alias[i]);

					callbackHandler = WSMappingCallbackHandlerFactory.getInstance().getCallbackHandler(map, null);
					LoginContext loginContext = new LoginContext("DefaultPrincipalMapping", callbackHandler);
					loginContext.login();
					Subject subject = loginContext.getSubject();
					Set<?> credentials = subject.getPrivateCredentials();
					PasswordCredential passwordCredential = (PasswordCredential) credentials.iterator().next();
					userIds[i] = passwordCredential.getUserName();
					passwords[i] = new String(passwordCredential.getPassword());

				}
				if (userIds.length > 0)
				{
					results = new HashMap<String, String[]>();
					results.put("Users", userIds);
					results.put("Passwords", passwords);
				}
			}
			catch (Throwable e)
			{
				LOG.error("Cannot Load Administrator Alias", e);
			}
		}
		return results;
	}
	/**
	 * Retrieves the User Synchronisation user and related password from WAS J2C Alias configuration
	 * 
	 * @return Map containing user string keyed by "User" and password string keyed by "Password"
	 */
	public static Map<String, String> getSyncAlias()
	{
		Map<String, String> results = null;

		String alias = EquationCommonContext.getConfigProperty("bf.sync.alias");
		String server = EquationCommonContext.getConfigProperty("eq.application.server");

		if (alias != null && alias.trim().length() > 0 && server != null && server.trim().equals("WAS"))
		{
			CallbackHandler callbackHandler;
			try
			{
				if (LOG.isInfoEnabled())
				{
					LOG.info("Retrieving user synchroniser alias: [" + alias + "]");
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put(Constants.MAPPING_ALIAS, alias);

				callbackHandler = WSMappingCallbackHandlerFactory.getInstance().getCallbackHandler(map, null);
				LoginContext loginContext = new LoginContext("DefaultPrincipalMapping", callbackHandler);
				loginContext.login();
				Subject subject = loginContext.getSubject();
				Set<?> credentials = subject.getPrivateCredentials();
				PasswordCredential passwordCredential = (PasswordCredential) credentials.iterator().next();

				results = new HashMap<String, String>();
				results.put("User", passwordCredential.getUserName());
				results.put("Password", new String(passwordCredential.getPassword()));

			}
			catch (Throwable e)
			{
				LOG.error("Cannot Load User Synchroniser Alias", e);
			}
		}
		return results;
	}
	/**
	 * Set Application Server Info needed by EquationDesktop Only WebSphere server info implemented at the moment with only host
	 * name and SOAP port being kept.
	 * 
	 */
	private void setApplicationServerProperties()
	{
		if (Toolbox.isAWebSphereApplicationServer() && this.useJNDI)
		{
			Properties prop = new Properties();
			Session session = null;
			ConfigService cs = null;
			ObjectName[] serverIndexObjectNames = null;
			ObjectName[] namedEndPointsObjectNames = null;

			try
			{
				session = new Session();
				cs = ConfigServiceFactory.getConfigService();

				serverIndexObjectNames = cs.resolve(session, "ServerIndex=");
				String hostName = (String) cs.getAttribute(session, serverIndexObjectNames[0], BFEQ_HOST_NAME);
				prop.put(BFEQ_HOST_NAME, hostName);
				namedEndPointsObjectNames = cs.queryConfigObjects(session, serverIndexObjectNames[0], ConfigServiceHelper
								.createObjectName(null, "NamedEndPoint"), null);
				for (int i = 0; i < namedEndPointsObjectNames.length; ++i)
				{

					String str = (String) cs.getAttribute(session, namedEndPointsObjectNames[i], "endPointName");
					if (str.equals(BFEQ_SOAP_PORT))
					{
						Integer portInt = (Integer) cs.getAttribute(session,
										(cs.queryConfigObjects(session, namedEndPointsObjectNames[i], ConfigServiceHelper
														.createObjectName(null, "EndPoint"), null)[0]), "port");
						prop.put(BFEQ_SOAP_PORT, portInt.toString());
						break;
					}
				}
			}
			catch (Exception e)
			{
				LOG.error("Exception encountered while trying to fetch WebSpehere Application Server information.", e);
			}
			finally
			{
				if (namedEndPointsObjectNames != null)
				{
					namedEndPointsObjectNames = null;
				}
				if (serverIndexObjectNames != null)
				{
					serverIndexObjectNames = null;
				}
				if (cs != null)
				{
					cs = null;
				}
				if (session != null)
				{
					session = null;
				}
			}
			equationProperties.put(CONFIGURATION_SERVER_PROPS, prop);
		}
	}

	/**
	 * Get a property from the Application Server Property given a key
	 * 
	 * @param key
	 *            - the key
	 * @return a String of the the property requested, or null.
	 * @equation.external
	 */
	public static String getAppServerProperty(String key)
	{
		Properties properties = getAppServerProperties();
		String property = properties != null ? properties.getProperty(key, "") : "";
		return property.trim() != null ? property : "";
	}

	/**
	 * Retrieves the Properties object from the Application Server Property
	 * 
	 * @return Properties object.
	 */
	private static Properties getAppServerProperties()
	{
		Properties properties = null;
		if (equationProperties.containsKey(CONFIGURATION_SERVER_PROPS))
		{
			properties = equationProperties.get(CONFIGURATION_SERVER_PROPS);
		}
		return properties;
	}
	/**
	 * Determine if it is using CAS authentication
	 * 
	 * @return true if it is using CAS authentication
	 */
	public static boolean isCASAuthentication()
	{
		return EquationCommonContext.getConfigProperty("bf.authentication.provider").equals("CAS");
	}

	/**
	 * Return the Javascript logging
	 * 
	 * @return the Javascript logging
	 */
	public static int getJSLogging()
	{
		String logging = EquationCommonContext.getConfigProperty("eq.js.logging");
		return Toolbox.parseInt(logging, 0);
	}

	/**
	 * Return the Javascript logging notification
	 * 
	 * @return the Javascript logging notification
	 */
	public static int getJSNotification()
	{
		String logging = EquationCommonContext.getConfigProperty("eq.js.notification");
		return Toolbox.parseInt(logging, 10);
	}

	/**
	 * Return the DC server
	 * 
	 * @return the DC server
	 */
	public static String getDCServer()
	{
		String dcServer = EquationCommonContext.getConfigProperty("eq.offline.dcserver").trim();
		return dcServer;
	}

	/**
	 * Adds a message to the collection of session messages to be delivered to the client.
	 * 
	 * This method is cluster-aware
	 * 
	 * Date and time will be added to the message.
	 * 
	 * @param sendersSessionIdentifier
	 *            - the senders session identifier. If this is a valid session id, the user name will be added to the message, and
	 *            the message will not be sent to this session.
	 * @param severity
	 *            - message severity (INFORMATION, WARNING, ERROR)
	 * @param message
	 *            - the message to be delivered
	 */
	public void addSessionMessage(String sendersSessionIdentifier, String severity, String message)
	{
		String senderName = " ";
		if (sendersSessionIdentifier != null)
		{
			EquationUser senderEquationUser = getEquationUser(sendersSessionIdentifier);
			senderName = senderEquationUser.getUserName();
		}
		// Use the BreakMessageCommand which is cluster aware:
		new BreakMessageCommand().perform(sendersSessionIdentifier, senderName, severity, message);
	}

	/**
	 * Add a message to the collection of messages to be delivered to the client.
	 * 
	 * This is called on nodes in
	 * 
	 * Date and time will be added to the message.
	 * 
	 * @param sessionId
	 *            - the senders session identifier. If this is a valid session id, the message will not be added for this session.
	 * @param sendersName
	 *            - the sender's name
	 * @param severity
	 *            - message severity (INFORMATION, WARNING, ERROR)
	 * @param message
	 *            - the message to be delivered
	 */
	public void addSessionMessageLocal(String sessionId, String sendersName, String severity, String message)
	{
		final Enumeration<EquationUser> equationUsersEnum = equationUsers.elements();
		EquationUser equationUser = null;
		EquationStandardSession session = null;

		while (equationUsersEnum.hasMoreElements())
		{
			equationUser = equationUsersEnum.nextElement();
			session = equationUser.getSession();
			if (sessionId == null || !sessionId.trim().equals(session.getSessionIdentifier()))
			{
				if (session.getBreakMessages() == null)
				{
					session.setBreakMessages(new ArrayList<String>());
				}
				String formattedMessage = severity + Toolbox.COLON_DELIMITER + sendersName + Toolbox.COLON_DELIMITER
								+ Toolbox.getCurrentDateTimeInUserFormat(equationUser) + Toolbox.COLON_DELIMITER + message;
				session.getBreakMessages().add(formattedMessage);
			}
		}
	}

	/**
	 * Returns the Cluster Service instance
	 * 
	 * @return the Cluster Service instance (or null if not in a cluster)
	 */
	public IClusterService getClusterService()
	{
		return clusterService;
	}

	/**
	 * Sets the Callback to the EquationFunctionLayer
	 * 
	 * This is called when the EquationFunctionContext class is initializing
	 * 
	 * @param functionLayerCallback
	 */
	public void setFunctionLayerCallback(IFunctionLayerCallback functionLayerCallback)
	{
		this.functionLayerCallback = functionLayerCallback;
	}
}
