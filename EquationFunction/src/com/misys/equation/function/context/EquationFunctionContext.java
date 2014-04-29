package com.misys.equation.function.context;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.misys.equation.bankfusion.lrp.bean.TaskDetail;
import com.misys.equation.bankfusion.lrp.engine.ITaskEngine;
import com.misys.equation.bankfusion.lrp.engine.TaskEngineToolbox;
import com.misys.equation.bankfusion.tools.BFToolbox;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationLogin;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.IFunctionLayerCallback;
import com.misys.equation.common.cluster.RemoveUnitCommand;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IOCRecordDao;
import com.misys.equation.common.dao.beans.OCRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQRuntimeException;
import com.misys.equation.common.utilities.EqDataToolbox;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.RepeatingDataManager;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.runtime.FunctionBankFusion;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionHandlerData;
import com.misys.equation.function.runtime.FunctionHandlerTable;
import com.misys.equation.function.runtime.ScreenSet;
import com.misys.equation.function.tools.XMLToolbox;

public class EquationFunctionContext implements IFunctionLayerCallback
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationFunctionContext.java 17823 2014-01-30 09:23:00Z perkinj1 $";
	private static EquationFunctionContext singletonFunctionContext;
	private static EquationCommonContext singletonCommonContext;

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EquationCommonContext.class);

	// Function handler table
	private static Hashtable<String, FunctionHandlerTable> functionHandlers = new Hashtable<String, FunctionHandlerTable>();

	/** Map of BFEQ session IDs to BankFusion user locators */
	private static Hashtable<String, String> sessionToUserLocator = new Hashtable<String, String>();

	/** Map of BankFusion user locators to BFEQ session IDs */
	private static Hashtable<String, String> userLocatorToSession = new Hashtable<String, String>();

	/** Reference Service id */
	public static final String MISYS_REFERENCE_SERVICE_ID = "CTS";

	private static final String DEFAULT_TOKEN_CHECK = "*NOPWDCHK";
	/** Maximum length of a password */
	public static final int MAX_PASSWORD_LENGTH = 50;
	/** Number of characters used to denote the length of a password */
	public static final int PASSWORD_LENGTH_CHARS = 5;

	/**
	 * This will contain the active task list currently being viewed in a desktop .. added by Rhose for LRP
	 */
	private static Hashtable<String, String> activeTaskList = new Hashtable<String, String>();
	/** Map of BFEQ session IDs to Login user (which could be a BankFusion user Id) */
	private static Hashtable<String, String> sessionToLoginUserId = new Hashtable<String, String>();
	/** Map of BFEQ session IDs to Login user (which could be a BankFusion user Id) */
	private static Hashtable<String, String> sessionToEquationIseriesProfile = new Hashtable<String, String>();

	// Update BankFusion Business Date
	private boolean updateBankFusionBusinessDate = true;
	// First logon through Desktop
	private boolean firstLogon = true;
	/** Cached value of the eq.credentials property */
	private Boolean equationAuthentication;
	/** Use CAS common profile for WebFacing access */
	private Boolean useCasCommonProfileForWebFacing;
	/** UI layer Callback */
	private IUILayerCallback uiLayerCallback;

	/*
	 * Constructor
	 */
	private EquationFunctionContext()
	{
		// ain't nobody creating one of me, except me!
	}

	/**
	 * Get the singleton context
	 * 
	 * @return the singleton context
	 */
	public static synchronized EquationFunctionContext getContext()
	{
		// Create our one and only instance of this class
		if (singletonFunctionContext == null)
		{
			// Check if BankFusion is consistent with EQ4 and BF properties are as expected
			if (EquationCommonContext.isBankFusionInstalled())
			{
				FunctionBankFusion.checkBankFusionProperties();

			}
			singletonCommonContext = EquationCommonContext.getContext();
			singletonFunctionContext = new EquationFunctionContext();
			singletonCommonContext.setFunctionLayerCallback(singletonFunctionContext);
			singletonFunctionContext.updateBankFusionBusinessDate();
		}
		return singletonFunctionContext;
	}

	/**
	 * Get a session.
	 * 
	 * This is called when BF credentials have been supplied
	 * 
	 * @param systemId
	 *            - the system id
	 * @param unitId
	 *            - the unit id
	 * @param credentials
	 *            a BFEQCredentials instance
	 * @param ipAddress
	 *            - the IP address
	 * @param sessionType
	 *            type of session
	 * @param equationIseriesProfile
	 *            - the iSeries/Equation user profile
	 * 
	 * @return the session ID
	 * 
	 * @throws EQException
	 */
	public String getEqSession(String systemId, String unitId, BFEQCredentials credentials, String ipAddress, int sessionType,
					String equationIseriesProfile) throws EQException
	{

		if (equationIseriesProfile == null)
		{
			equationIseriesProfile = "";
		}
		EquationUnit equationUnit = null;
		String userLocator = credentials.getUserLocator();
		String sessionIdentifier = credentials.getSessionIdentifier(); // Returned session Id

		// If we already have a valid session with the same login details why bother going any further?
		if (singletonCommonContext.sessionValid(sessionIdentifier))
		{
			// Get the existing login details and see if changed
			EquationLogin existinglogin = singletonCommonContext.getEquationLogin(sessionIdentifier);

			if (existinglogin.getUnitId().equalsIgnoreCase(unitId) && existinglogin.getSystem().equalsIgnoreCase(systemId)
							&& credentials.getUserId().equalsIgnoreCase(sessionToLoginUserId.get(sessionIdentifier))
							&& equationIseriesProfile.equalsIgnoreCase(sessionToEquationIseriesProfile.get(sessionIdentifier)))
			{
				LOG.debug("Session " + "[" + sessionIdentifier + "]" + " already exists.  Use existing session");
				return sessionIdentifier;
			}
			else
			{
				LOG.debug("Session " + "[" + sessionIdentifier + "]"
								+ " already exists.  But login details differ from existing session.  Getting a new connection");
			}
		}

		try
		{
			// Primary login can be Equation or BankFusion:
			if (isEquationAuthentication() || sessionType == EquationCommonContext.SESSION_CLASSIC_DESKTOP)
			{
				// Primary login will be to Equation
				String equationUser = credentials.getUserId();
				String equationPassword = credentials.getPassword();
				String passwordType = credentials.getPasswordType();

				// If coming in from BankFusion, the userLocator will be non-null:
				if (userLocator != null)
				{
					equationUnit = getEquationUnit(systemId, unitId);
					// Already logged into BankFusion, now log into Equation
					equationUser = equationUser.toUpperCase();
					equationPassword = getProfileToken64(equationUnit, equationUser);
					passwordType = EquationCommonContext.PASSWORD_TYPE_PROFILETOKEN_BASE64;
				}

				// Log-in user in the EquationCommonContext level
				sessionIdentifier = singletonCommonContext.getEqSession(systemId, unitId, equationUser, equationPassword,
								sessionIdentifier, ipAddress, passwordType, sessionType, equationIseriesProfile);

				// Classic login - no session stored anywhere
				if (sessionType == EquationCommonContext.SESSION_CLASSIC_DESKTOP)
				{
					return sessionIdentifier;
				}

				if (sessionIdentifier != null)
				{
					if (EquationCommonContext.isBankFusionInstalled() && userLocator == null)
					{
						// Also log into BankFusion
						String bfPassword = credentials.getPassword();
						String bfPasswordType = credentials.getPasswordType();
						if (EquationCommonContext.PASSWORD_TYPE_PROFILETOKEN_PLAIN.equals(credentials.getPasswordType())
										&& isEquationAuthentication()
										&& "CAS".equals(EquationCommonContext.getConfigProperty("bf.authentication.provider")))
						{
							// Special processing if using CAS and the Equation authentication handler:
							// The profile token is passed using base64 but is padded longer than
							// the maximum password length so that it can be recognised by the
							// CAS authentication handler as containing a profile token. This also
							// requires supplying the length of the actual base64 string as a prefix.
							// The password therefore looks like this:
							// "00044base64base64upto44chars....AAAAAAAAAAAA"
							byte[] tokenBytes = Toolbox.cvtPwdToAS400TokenBytes(bfPassword, bfPasswordType);
							String base64 = Toolbox.byteArrayToBase64String(tokenBytes);

							String lengthString = Toolbox.padAtFront(Integer.toString(base64.length()), "0", PASSWORD_LENGTH_CHARS);
							// Pad to longer than than the maximum password length
							char[] rightPadding = new char[(MAX_PASSWORD_LENGTH + 1) - (base64.length() + lengthString.length())];
							Arrays.fill(rightPadding, 'A');
							bfPassword = lengthString + base64 + new String(rightPadding);
							// The following password type will not attempt to modify the password:
							bfPasswordType = EquationCommonContext.PASSWORD_TYPE_PROFILETOKEN_BASE64;
						}
						userLocator = BFToolbox.login(credentials.getUserId(), bfPassword, bfPasswordType);
					}
				}
			}
			else
			{
				// Check that the unit has been initialised:
				equationUnit = getEquationUnit(systemId, unitId);
				// When using BankFusion credentials, first login to BF:
				// We may already be logged in to BankFusion
				if (EquationCommonContext.isBankFusionInstalled())
				{
					if (userLocator == null)
					{
						// Login to BankFusion for primary authentication
						userLocator = BFToolbox.login(credentials.getUserId(), credentials.getPassword(), credentials
										.getPasswordType());
						// Should sessionIdentifier be set equal to user locator?
					}
					else
					{
						// BF has logged in first. EQ Session identifier must equal BF user locator for UXP
						sessionIdentifier = userLocator;
					}
				}
				// Logged into BankFusion first, now log into Equation
				String iSeriesUser = getiSeriesUserForBFUser(equationUnit, credentials.getUserId());
				String passwordType = EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN;
				String password = null;
				// The IBM Profile Token API fails if attempting to get a profile token for
				// the same user. Need to use the stored credentials
				if (!equationUnit.getAdministrator().equals(iSeriesUser))
				{
					password = getProfileToken64(equationUnit, iSeriesUser);
					passwordType = EquationCommonContext.PASSWORD_TYPE_PROFILETOKEN_BASE64;
				}
				else
				{
					String[] unitCredentials = getConfiguredUnitCredentials(systemId, unitId);
					if (unitCredentials == null)
					{
						throw new EQException("Unit must be pre-initialised when using CAS credentials");
					}
					password = unitCredentials[1];
				}

				// Log-in user in the EquationCommonContext level
				sessionIdentifier = singletonCommonContext.getEqSession(systemId, unitId, iSeriesUser, password, sessionIdentifier,
								ipAddress, passwordType, sessionType, equationIseriesProfile);
			}

			// Logged into EQ and BF (if installed)
			if (sessionIdentifier != null)
			{
				if (equationUnit == null)
				{
					equationUnit = getEquationUnit(systemId, unitId);
				}

				FunctionHandlerTable functionHandlerTable = addFunctionHandlerTable(sessionIdentifier, sessionType);

				// Automatic refresh of cache when processing date has changed
				EquationUser equationUser = functionHandlerTable.getFunctionHandler().getFhd().getEquationUser();
				boolean processingDateChange = equationUnit.isProcessingDateChange(equationUser.getSession());
				if (processingDateChange)
				{
					refreshCache(sessionIdentifier);
				}
				if (EquationCommonContext.isBankFusionInstalled())
				{
					updateBFProcessingDate(processingDateChange, equationUnit, userLocator);
					sessionToUserLocator.put(sessionIdentifier, userLocator);
					userLocatorToSession.put(userLocator, sessionIdentifier);
				}
				sessionToLoginUserId.put(sessionIdentifier, credentials.getUserId());
				sessionToEquationIseriesProfile.put(sessionIdentifier, equationIseriesProfile);
			}
		}
		catch (Exception e)
		{
			// Tidy up the session
			logOffSessionInternal(sessionIdentifier, credentials.getUserLocator() == null);

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
	 * Get a session.
	 * 
	 * This is called when BF credentials have been supplied
	 * 
	 * @param systemId
	 *            - the system id
	 * @param unitId
	 *            - the unit id
	 * @param credentials
	 *            a BFEQCredentials instance
	 * @param ipAddress
	 *            - the IP address
	 * @param sessionType
	 *            type of session
	 * @param equationIseriesProfile
	 *            - the iSeries/Equation user profile
	 * 
	 * @return the session ID
	 * 
	 * @throws EQException
	 */
	public String getEqSessionNoBankFusion(String systemId, String unitId, BFEQCredentials credentials, String ipAddress,
					int sessionType, String equationIseriesProfile) throws EQException
	{
		if (equationIseriesProfile == null)
		{
			equationIseriesProfile = "";
		}

		EquationUnit equationUnit = null;

		String sessionIdentifier = credentials.getSessionIdentifier(); // Returned session Id

		// If we already have a valid session with the same login details why bother going any further?
		if (singletonCommonContext.sessionValid(sessionIdentifier))
		{
			// Get the existing login details and see if changed
			EquationLogin existinglogin = singletonCommonContext.getEquationLogin(sessionIdentifier);

			if (existinglogin.getUnitId().equalsIgnoreCase(unitId) && existinglogin.getSystem().equalsIgnoreCase(systemId)
							&& credentials.getUserId().equalsIgnoreCase(sessionToLoginUserId.get(sessionIdentifier))
							&& equationIseriesProfile.equalsIgnoreCase(sessionToEquationIseriesProfile.get(sessionIdentifier)))
			{
				LOG.debug("Session " + "[" + sessionIdentifier + "]" + " already exists.  Use existing session");
				return sessionIdentifier;
			}
			else
			{
				LOG.debug("Session " + "[" + sessionIdentifier + "]"
								+ " already exists.  But login details differ from existing session.  Getting a new connection");
			}
		}

		try
		{
			// Primary login can be Equation or BankFusion:
			if (isEquationAuthentication() || sessionType == EquationCommonContext.SESSION_CLASSIC_DESKTOP)
			{
				// Primary login will be to Equation
				String equationUser = credentials.getUserId();
				String equationPassword = credentials.getPassword();
				String passwordType = credentials.getPasswordType();

				// Log-in user in the EquationCommonContext level
				sessionIdentifier = singletonCommonContext.getEqSession(systemId, unitId, equationUser, equationPassword,
								sessionIdentifier, ipAddress, passwordType, sessionType, equationIseriesProfile);

				// Classic login - no session stored anywhere
				if (sessionType == EquationCommonContext.SESSION_CLASSIC_DESKTOP)
				{
					return sessionIdentifier;
				}

			}
			else
			{
				// Check that the unit has been initialised:
				equationUnit = getEquationUnit(systemId, unitId);
				// log into Equation
				String iSeriesUser = getiSeriesUserForBFUser(equationUnit, credentials.getUserId());
				String passwordType = EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN;
				String password = null;
				// The IBM Profile Token API fails if attempting to get a profile token for
				// the same user. Need to use the stored credentials
				if (!equationUnit.getAdministrator().equals(iSeriesUser))
				{
					password = getProfileToken64(equationUnit, iSeriesUser);
					passwordType = EquationCommonContext.PASSWORD_TYPE_PROFILETOKEN_BASE64;
				}
				else
				{
					String[] unitCredentials = getConfiguredUnitCredentials(systemId, unitId);
					if (unitCredentials == null)
					{
						throw new EQException("Unit must be pre-initialised when using CAS credentials");
					}
					password = unitCredentials[1];
				}

				// Log-in user in the EquationCommonContext level
				sessionIdentifier = singletonCommonContext.getEqSession(systemId, unitId, iSeriesUser, password, sessionIdentifier,
								ipAddress, passwordType, sessionType, equationIseriesProfile);
			}

			// Logged into EQ
			if (sessionIdentifier != null)
			{
				if (equationUnit == null)
				{
					equationUnit = getEquationUnit(systemId, unitId);
				}

				FunctionHandlerTable functionHandlerTable = addFunctionHandlerTable(sessionIdentifier, sessionType);

				// Automatic refresh of cache when processing date has changed
				EquationUser equationUser = functionHandlerTable.getFunctionHandler().getFhd().getEquationUser();
				boolean processingDateChange = equationUnit.isProcessingDateChange(equationUser.getSession());
				if (processingDateChange)
				{
					refreshCache(sessionIdentifier);
				}

				sessionToLoginUserId.put(sessionIdentifier, credentials.getUserId());
				sessionToEquationIseriesProfile.put(sessionIdentifier, equationIseriesProfile);
			}
		}
		catch (Exception e)
		{
			// Tidy up the session
			logOffSessionInternal(sessionIdentifier, credentials.getUserLocator() == null);

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
	 * Get a session.
	 * 
	 * This is called when BF credentials have been supplied
	 * 
	 * @param dataSourceName
	 *            - the data source name
	 * @param internalSessionType
	 *            - the internal sessionType
	 * @param sessionIdentifier
	 *            - the session identifier
	 * @param xaMode
	 *            - the xa mode
	 * @param equationIseriesProfile
	 *            - the Equation iseries profile
	 * @return the session ID
	 * 
	 * @throws EQException
	 */
	public String getEqSession(String dataSourceName, int internalSessionType, String sessionIdentifier, boolean xaMode,
					String equationIseriesProfile) throws EQException
	{
		try
		{
			// Log-in user in the EquationCommonContext level
			sessionIdentifier = singletonCommonContext.getEqSession(dataSourceName, sessionIdentifier, internalSessionType, xaMode,
							equationIseriesProfile);

			// Logged into EQ
			if (sessionIdentifier != null)
			{

				addFunctionHandlerTable(sessionIdentifier, internalSessionType);

				// DO NOT CHECK PROCESSING DATE when using pooled connections. A separate pooling job should be checking for process

				sessionToLoginUserId.put(sessionIdentifier, dataSourceName);
			}
		}
		catch (Exception e)
		{
			// TODO: There is too much cleanup going on for a pooled connection.
			// Tidy up the session
			logOffSessionInternal(sessionIdentifier, false);

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
	 * Validates that the existing session details match
	 * 
	 * @param systemId
	 * @param unitId
	 * @param credentials
	 * @return boolean indicating whether the session details match
	 */
	public boolean existingSessionMatches(String systemId, String unitId, BFEQCredentials credentials, String equationIseriesProfile)
	{
		if (equationIseriesProfile == null)
		{
			equationIseriesProfile = "";
		}

		boolean isValid = false;
		String sessionIdentifier = credentials.getSessionIdentifier(); // Returned session Id

		// If we already have a valid session with the same login details why bother going any further?
		if (singletonCommonContext.sessionValid(sessionIdentifier))
		{
			// Get the existing login details and see if changed
			EquationLogin existinglogin = singletonCommonContext.getEquationLogin(sessionIdentifier);

			if (existinglogin.getUnitId().equalsIgnoreCase(unitId) && existinglogin.getSystem().equalsIgnoreCase(systemId)
							&& credentials.getUserId().equalsIgnoreCase(sessionToLoginUserId.get(sessionIdentifier))
							&& equationIseriesProfile.equalsIgnoreCase(sessionToEquationIseriesProfile.get(sessionIdentifier)))
			{
				if (LOG.isDebugEnabled())
				{
					LOG.debug("Session " + "[" + sessionIdentifier + "]" + " already exists and matches.  Use existing session");
				}
				isValid = true;
			}
			else
			{
				LOG.warn("Session " + "[" + sessionIdentifier + "]"
								+ " already exists.  But login details differ from existing session.");
				LOG.warn(LanguageResources.getFormattedString("EquationFunctionContext.Match.SystemId", new String[] {
								existinglogin.getSystem(), systemId }));
				LOG.warn(LanguageResources.getFormattedString("EquationFunctionContext.Match.UnitId", new String[] {
								existinglogin.getUnitId(), unitId }));
				LOG.warn(LanguageResources.getFormattedString("EquationFunctionContext.Match.UserId", new String[] {
								credentials.getUserId(), sessionToLoginUserId.get(sessionIdentifier) }));
				LOG.warn(LanguageResources.getFormattedString("EquationFunctionContext.Match.ISeriesId", new String[] {
								equationIseriesProfile, sessionToEquationIseriesProfile.get(sessionIdentifier) }));
			}
		}
		return isValid;
	}
	/**
	 * Closes BankFusion and Equation sessions as appropriate and clears any session related information.
	 * 
	 * Note that logging off of the BankFusion session should not be attempted if either:
	 * <ul>
	 * <li>A login attempt initiated from BF is being rolled back</li>
	 * <li>The logoff is called from BF logout notification handler</li>
	 * </ul>
	 * 
	 * @see #logOffSession(String)
	 * 
	 * @param sessionIdentifier
	 *            The Equation session id to close
	 * @param logoffBankFusion
	 *            Whether to attempt logoff of the BankFusion session
	 */
	private void logOffSessionInternal(String sessionIdentifier, boolean logoffBankFusion)
	{
		String userLocator = null;
		if (sessionIdentifier != null)
		{
			userLocator = sessionToUserLocator.get(sessionIdentifier);
			sessionToLoginUserId.remove(sessionIdentifier);
			if (sessionToEquationIseriesProfile.contains(sessionIdentifier))
			{
				sessionToEquationIseriesProfile.remove(sessionIdentifier);
			}

			functionHandlers.remove(sessionIdentifier);
		}
		if (userLocator != null)
		{
			userLocatorToSession.remove(userLocator);
			sessionToUserLocator.remove(sessionIdentifier);
		}
		boolean isUXP = false;
		// A failed Desktop login gets here and getEquationLogin will be null. A failed UXP login does not get here.
		if (EquationCommonContext.getContext().getEquationLogin(sessionIdentifier) != null)
		{
			isUXP = EquationCommonContext.getContext().getEquationLogin(sessionIdentifier).chkUXPUserInterface();
		}
		// if in UXP environment, BankFusion is in charge. It is valid to use UXP without Equation.
		if (logoffBankFusion && !isUXP)
		{
			FunctionBankFusion.logoff(userLocator);
		}

		singletonCommonContext.logOffSession(sessionIdentifier);
	}

	/**
	 * Create a new FunctionHandlerTable for a new session
	 * 
	 * @param sessionIdentifier
	 *            the new session id
	 * @param sessionType
	 *            type of session
	 * @return the new FunctionHandlerTable
	 */
	private FunctionHandlerTable addFunctionHandlerTable(String sessionIdentifier, int sessionType)
	{
		FunctionHandlerTable functionHandlerTable = new FunctionHandlerTable(sessionIdentifier, singletonCommonContext
						.getEquationUser(sessionIdentifier), sessionType);
		functionHandlers.put(sessionIdentifier, functionHandlerTable);
		LOG.debug("FunctionHandler created for session " + "[" + sessionIdentifier + "]");

		if (sessionIdentifier.length() > 4)
		{
			if (sessionIdentifier.startsWith("TGT-"))
			{
				// This is a CAS Ticket Granting Ticket - assume the format is TGT-index-50_long_random_id-cas
				int pos = sessionIdentifier.indexOf('-', 4);
				if (pos + 5 > sessionIdentifier.length() || pos == -1)
				{
					pos = 3;
				}
				functionHandlerTable.getFunctionHandler().getFhd().getFunctionInfo().setWorkStationId(
								sessionIdentifier.substring(pos + 1, pos + 5).toUpperCase());

			}
			else
			{
				functionHandlerTable.getFunctionHandler().getFhd().getFunctionInfo().setWorkStationId(
								sessionIdentifier.substring(0, 4).toUpperCase());
			}
		}
		else
		{
			functionHandlerTable.getFunctionHandler().getFhd().getFunctionInfo().setWorkStationId(sessionIdentifier.toUpperCase());
		}
		return functionHandlerTable;
	}

	/**
	 * Return the FunctionHandler of the session
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 * 
	 * @return the function handler of the session id
	 */
	public FunctionHandler getFunctionHandler(String sessionIdentifier)
	{
		if (singletonCommonContext.sessionValid(sessionIdentifier))
		{
			return functionHandlers.get(sessionIdentifier).getFunctionHandler();
		}
		else
		{
			return null;
		}
	}

	/**
	 * Determine whether function handler exists for the session
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 * 
	 * @return true if function handler exists for the session
	 */
	public boolean functionHandlerExists(String sessionIdentifier)
	{
		return functionHandlers.containsKey(sessionIdentifier);
	}

	/**
	 * Return the child function handler of the session id
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 * @param id
	 *            - the function handler id
	 * 
	 * @return the child function handler of the session id
	 */
	public FunctionHandler getFunctionHandler(String sessionIdentifier, String id)
	{
		if (singletonCommonContext.sessionValid(sessionIdentifier))
		{
			return functionHandlers.get(sessionIdentifier).getFunctionHandler(id);
		}
		else
		{
			return null;
		}
	}

	/**
	 * Return the function handler table
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 * 
	 * @return the function handler table
	 */
	public FunctionHandlerTable getFunctionHandlerTable(String sessionIdentifier)
	{
		return functionHandlers.get(sessionIdentifier);
	}

	/**
	 * Add a child Function Handler to the session
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param name
	 *            - unique name of the child Function Handler
	 * @param childType
	 *            - the child type
	 * @param rowIndex
	 *            - the repeating data group + row index (when drilling down repeating group)
	 */
	public String addChildFunctionHandler(String sessionIdentifier, String name, String optionId, String context, String childType,
					String rowIndex)
	{
		String error = "00";
		FunctionHandlerTable functionHandlerTable = functionHandlers.get(sessionIdentifier);
		FunctionHandler functionHandler = null;
		try
		{
			// retrieve the main function handler
			FunctionHandler parentFh = functionHandlerTable.getFunctionHandler().getFhd().rtvLastDrillDownChild();
			FunctionHandlerData parentFhd;
			if (parentFh == null)
			{
				parentFh = functionHandlerTable.getFunctionHandler();
				parentFhd = parentFh.getFhd();
			}
			else
			{
				parentFhd = parentFh.getFhd();
			}
			ScreenSet screenSet = parentFhd.getScreenSetHandler().rtvScrnSetCurrent();

			// determine child type
			int child = EquationCommonContext.SESSION_CHILD_MODE;
			if (childType.equals("PLAY"))
			{
				child = EquationCommonContext.SESSION_CHILD_PLAYBUTTON;
			}
			else if (childType.equals("DRILLDOWN"))
			{
				child = EquationCommonContext.SESSION_CHILD_DRILLDOWN;

				// set the repeating data
				int index = rowIndex.indexOf(RepeatingDataManager.INDEX_DELIMITER);
				String repeatingGroup = rowIndex.substring(0, index);
				int row = Toolbox.parseInt(rowIndex.substring(index + 1), 0);
				screenSet.getFunctionData().getRepeatingDataManager(repeatingGroup).setRow(row - 1);
			}

			// retrieve the function handler
			functionHandler = functionHandlerTable.addChild(name, child);
			functionHandler.getFhd().getContextHandler().copy(parentFhd.getContextHandler());
			functionHandler.getFhd().getContextHandler().saveFunctionToContextData(screenSet.getFunction(),
							screenSet.getFunctionData());

			// start the transaction
			boolean ok = functionHandler.doNewTransaction(optionId, context);
			if (!ok)
			{
				error = "20"
								+ functionHandler.getFhd().getFunctionMsgManager().getOtherMessages().getMessages().get(0)
												.rtvUnformattedText();
			}

			// cannot invoke WebFacing session in another window
			if (functionHandler.getFhd().isLegacyOption())
			{
				functionHandler.getFhd().getFunctionMsgManager().insertOtherMessage(functionHandler.rtvEquationSession(), 0, 0, "",
								0, null, "KSM7357", "", "");
				error = "20"
								+ functionHandler.getFhd().getFunctionMsgManager().getOtherMessages().getMessages().get(0)
												.rtvUnformattedText();
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			error = "20KSM7340" + Toolbox.getExceptionMessage(e);
		}

		// error?
		if (!error.equals("00"))
		{
			functionHandlerTable.removeChild(name);
		}

		return error;
	}

	/**
	 * Log-off a session
	 * <p>
	 * This also performs the BankFusion logoff
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 */
	public void logOffSession(String sessionIdentifier)
	{
		LOG.info("EquationFunctionContext - Logging off session");
		logOffSessionInternal(sessionIdentifier, true);
	}

	/**
	 * Log-off a session
	 * <p>
	 * This also performs the BankFusion logoff
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 * @param logoffBankFusion
	 *            Whether to attempt logoff of the BankFusion session
	 */
	public void logOffSession(String sessionIdentifier, boolean logoffBankFusion)
	{
		LOG.info("EquationFunctionContext - Logging off session");
		logOffSessionInternal(sessionIdentifier, logoffBankFusion);
	}
	/**
	 * Cleanup handles to pooled session.
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 */
	public void logOffSessionUserPool(String sessionIdentifier)
	{
		LOG.info("EquationFunctionContext - Logging off session");

		if (sessionIdentifier != null)
		{
			sessionToLoginUserId.remove(sessionIdentifier);
			if (sessionToEquationIseriesProfile.contains(sessionIdentifier))
			{
				sessionToEquationIseriesProfile.remove(sessionIdentifier);
			}
			functionHandlers.remove(sessionIdentifier);

			singletonCommonContext.logOffSessionUserPool(sessionIdentifier);
		}
	}

	/**
	 * Log-off all users on the given system and unit id
	 * 
	 * @param systemId
	 *            - the system where the unit is located
	 * @param unitId
	 *            - the unit id
	 */
	public void removeAllUsersOfUnit(String systemId, String unitId)
	{
		// Ask UI layer to cleanup first (if present)
		if (uiLayerCallback != null)
		{
			uiLayerCallback.removeAllUsersOfUnit(systemId, unitId);
		}

		// retrieve the list of users log into the unit
		List<String> listOfSessions = new ArrayList<String>();
		for (Entry<String, EquationUser> equationUserEntry : singletonCommonContext.getEqUsers().entrySet())
		{
			EquationUser equationUser = equationUserEntry.getValue();
			if (equationUser != null && equationUser.getSession() != null)
			{
				String userSystemId = equationUser.getSession().getSystemId();

				String userUnitId = equationUser.getSession().getUnitId();
				if (userUnitId.equalsIgnoreCase(unitId) && userSystemId.equalsIgnoreCase(systemId))
				{
					listOfSessions.add(equationUserEntry.getKey());
				}
			}
		}

		// log them off
		for (String sessionId : listOfSessions)
		{
			logOffSession(sessionId);
		}
	}

	/**
	 * Remove a unit from the system
	 * 
	 * @param sessionIdentifier
	 *            - the session id of the unit to be removed
	 * 
	 * @throws EQException
	 */
	public void removeEquationUnit(String sessionIdentifier) throws EQException
	{
		if (singletonCommonContext.sessionValid(sessionIdentifier))
		{
			EquationLogin eqLogin = singletonCommonContext.getEquationLogin(sessionIdentifier);
			// Use a cluster aware command to remove the unit
			RemoveUnitCommand cmd = new RemoveUnitCommand();
			cmd.perform(eqLogin.getSystem(), eqLogin.getUnitId());
		}
	}

	/**
	 * Returns the BankFusion user locator token for the specified session Id
	 * 
	 * @param sessionId
	 *            The Equation session id
	 * @return A String containing the BankFusion User Locator token
	 */
	public String getUserLocator(String sessionId)
	{
		return sessionToUserLocator.get(sessionId);
	}

	/**
	 * This method is called from the FunctionHandlerActivityStep, so there may be a valid session id (if EquationDesktop is the
	 * entry point), or the session id may be blank, if called directly from BankFusion.
	 * <p>
	 * This method will first try to retrieve a session using the session id, and if none is found, then an attempt is made to
	 * retrieve a session via the userLocator
	 * 
	 * @param sessionId
	 * @param userLocator
	 * @return String The session id
	 */
	public String getSessionId(String sessionId, String userLocator)
	{
		String realSessionId = null;
		EquationLogin equationLogin = singletonCommonContext.getEquationLogin(sessionId);
		if (equationLogin != null)
		{
			realSessionId = sessionId;
		}
		else
		{
			realSessionId = userLocatorToSession.get(userLocator);
		}
		return realSessionId;
	}

	/**
	 * Log-off a session triggered by logging off a BankFusion session
	 * 
	 * @param userLocator
	 *            - the BankFusion user locator
	 */
	public void logOffBankFusionSession(String userLocator)
	{
		String sessionIdentifier = userLocatorToSession.get(userLocator);
		if (sessionIdentifier != null)
		{
			LOG.info("Logging off session due to BankFusion session logging off");

			boolean logOffDesktop = logOffViaDesktop(sessionIdentifier);

			if (!logOffDesktop)
			{
				// As this has been called due to logging out of BankFusion, do not
				// attempt to logoff the BankFusion session (false parameter):
				logOffSessionInternal(sessionIdentifier, false);
			}
		}
	}

	/**
	 * Log-off a session using the desktop layer
	 * 
	 * @param sessionIdentifer
	 *            - the session identifier
	 * 
	 * @return true if logged is handled by the desktop context
	 */
	public boolean logOffViaDesktop(String sessionIdentifer)
	{
		// Desktop context in the class path?
		Class<?> equationDesktopContextClass = null;
		try
		{
			// try to load WFClient
			equationDesktopContextClass = Class.forName("com.misys.equation.ui.context.EquationDesktopContext");

			// Desktop context is in the library
			if (equationDesktopContextClass != null)
			{
				Method getContextMethod = equationDesktopContextClass.getMethod("getContext");
				Object desktopContext = getContextMethod.invoke(null);

				Method getLogoffDesktopMethod = equationDesktopContextClass.getMethod("logOffDesktop", String.class, String.class,
								boolean.class);
				getLogoffDesktopMethod.invoke(desktopContext, sessionIdentifer, "", false);
			}
			return true;
		}
		catch (ClassNotFoundException e)
		{
			return false;
		}
		catch (NoSuchMethodException e)
		{
			return false;
		}
		catch (IllegalArgumentException e)
		{
			return false;

		}
		catch (IllegalAccessException e)
		{
			return false;
		}
		catch (InvocationTargetException e)
		{
			return true;
		}
	}

	/**
	 * Return the default task engine
	 * 
	 * @param sessionIdentifier
	 *            - the session id
	 * 
	 * @return the task engine
	 */
	public ITaskEngine getTaskEngine(String sessionIdentifier)
	{
		String userLocator = getUserLocator(sessionIdentifier);
		return TaskEngineToolbox.getInstance().getTaskEngine(sessionIdentifier, userLocator);
	}

	/**
	 * Add open task in the List
	 * 
	 * @param taskId
	 *            - the task Id to be added
	 * @param sessionId
	 *            - the user's session id
	 */
	public void addOpenTask(String taskId, String sessionId)
	{
		if (activeTaskList == null)
		{
			activeTaskList = new Hashtable<String, String>();
		}

		activeTaskList.put(taskId, sessionId);
		LOG.debug("EquationFunctionContext.addOpenTask (" + taskId + ") taskId added to list ...");
	}

	/**
	 * Remove task from the list
	 * 
	 * @param taskId
	 *            - the task Id that will be removed
	 */
	public void removeOpenTask(String taskId)
	{
		if (activeTaskList.containsKey(taskId))
		{
			activeTaskList.remove(taskId);
			LOG.debug("EquationFunctionContext.removeOpenTask (" + taskId + ") taskId removed from list ...");
		}
	}

	/**
	 * Determine if task is currently active
	 * 
	 * @param taskId
	 *            - the task id
	 * 
	 * @return true if task is currently being viewed
	 */
	public boolean isOpenTask(String taskId)
	{
		LOG.debug("EquationFunctionContext.isOpenTask (" + taskId + ") started ...");
		if (activeTaskList.containsKey(taskId))
		{
			String sessionId = activeTaskList.get(taskId);

			if (EquationCommonContext.getContext().sessionValid(sessionId))
			{
				LOG.debug("EquationFunctionContext.isOpenTask (" + taskId + ") session active ...");
				return true;
			}
			else
			{
				removeOpenTask(taskId);
				LOG.debug("EquationFunctionContext.isOpenTask (" + taskId + ") session inactive .. taskId removed from list ...");
				return false;
			}
		}

		LOG.debug("EquationFunctionContext.isOpenTask (" + taskId + ") task not found ...");
		return false;
	}

	/**
	 * Remove and unclaim task from the list based on the associated session id
	 * 
	 * @param sessionId
	 *            - the session Id for the task that will be removed
	 */
	public void unclaimTask(String sessionId)
	{
		LOG.debug("EquationFunctionContext.removeUnclaimTask (" + sessionId + ") started ...");

		FunctionHandler functionHandler = getFunctionHandler(sessionId);

		TaskDetail taskDetail = functionHandler.getFhd().getTaskDetail();
		if (taskDetail != null)
		{
			try
			{
				unclaimTask(sessionId, null, taskDetail.getTkiid());
			}
			catch (Exception e)
			{
				LOG.error(e);
			}
		}

		LOG.debug("EquationFunctionContext.removeUnclaimTask (" + sessionId + ") finished ...");
	}

	/**
	 * Remove and unclaim task from the list based on the associated session id
	 * 
	 * @param sessionId
	 *            - the session Id for the task that will be removed
	 */
	public void unclaimTask(FunctionHandler functionHandler)
	{
		String name = functionHandler.getFhd().getFunctionInfo().getName();
		String sessionId = functionHandler.getFhd().getFunctionInfo().getSessionId();
		LOG.debug("EquationFunctionContext.removeUnclaimTask for function handler (" + name + ") started ...");

		TaskDetail taskDetail = functionHandler.getFhd().getTaskDetail();
		if (taskDetail != null)
		{
			try
			{
				unclaimTask(sessionId, null, taskDetail.getTkiid());
			}
			catch (Exception e)
			{
				LOG.error(e);
			}
		}

		LOG.debug("EquationFunctionContext.removeUnclaimTask (" + sessionId + ") finished ...");
	}

	/**
	 * Performing unclaiming of task
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 * @param taskEngine
	 *            - the task engine. If this is NULL, then it will create a new task engine using the session Id
	 * @param taskId
	 *            - the task id
	 * 
	 * @return the task engine
	 * 
	 */
	public ITaskEngine unclaimTask(String sessionIdentifier, ITaskEngine taskEngine, String taskId) throws EQException
	{
		if (taskEngine == null)
		{
			taskEngine = getTaskEngine(sessionIdentifier);
		}

		// remove from the list
		removeOpenTask(taskId);

		// unclaim it
		taskEngine.cancelClaim(taskId);

		return taskEngine;
	}

	/**
	 * Clear the task without unclaiming it
	 * 
	 * @param fhd
	 *            - the functionhandler data
	 */
	public void clearTaskActiveList(FunctionHandlerData fhd)
	{
		TaskDetail taskDetail = fhd.getTaskDetail();
		if (taskDetail != null)
		{
			removeOpenTask(taskDetail.getTkiid());
			fhd.setTaskDetail(null);
			fhd.setTaskRqHeader(null);
			fhd.setReason("");
			fhd.setReferToUserId("");
		}
	}

	/**
	 * Performing claim of task
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 * @param taskEngine
	 *            - the task engine. If this is NULL, then it will create a new task engine using the session Id
	 * @param taskId
	 *            - the task id
	 * 
	 * @return the task engine
	 * 
	 */
	public ITaskEngine claimTask(String sessionIdentifier, ITaskEngine taskEngine, String taskId) throws EQException
	{
		if (taskEngine == null)
		{
			taskEngine = getTaskEngine(sessionIdentifier);
		}

		// unclaim it
		taskEngine.claimTask(taskId);

		return taskEngine;
	}

	/**
	 * Refresh cache
	 * 
	 * @param sessionIdentifier
	 *            - the session id
	 */
	public void refreshCache(String sessionIdentifier) throws EQException
	{
		EquationCommonContext.getContext().refreshCache(sessionIdentifier);
		XMLToolbox.clear();
		EqDataToolbox.clear();
	}
	/**
	 * Determines whether BankFusion business date should be changed
	 */
	private void updateBankFusionBusinessDate()
	{
		updateBankFusionBusinessDate = EquationCommonContext.getConfigProperty(
						EquationCommonContext.BANKFUSION_UPDATE_BUSINESS_DATE_PROPERTY).equals("true");
	}

	/**
	 * Retrieves an EquationUnit for the supplied system and unit ids
	 * 
	 * @param systemId
	 * @param unitId
	 * @return An EquationUnit instance. This will never be return as null; an exception will be thrown instead.
	 * @throws EQException
	 */
	private EquationUnit getEquationUnit(String systemId, String unitId) throws EQException
	{

		EquationSystem system = singletonCommonContext.getEquationSystem(systemId);
		if (system == null)
		{
			throw new EQException(
							"System must be pre-initialized when using BankFusion credentials to log on to the Equation Desktop.");
		}
		EquationUnit equationUnit = null;
		try
		{
			equationUnit = system.getUnit(unitId);
		}
		catch (EQException e)
		{

		}
		if (equationUnit == null)
		{
			throw new EQException(
							"Unit must be pre-initialized when using BankFusion credentials to log on to the Equation Desktop.");
		}
		return equationUnit;
	}

	/**
	 * Maps a BankFusion user to an IBM i OS user
	 * 
	 * @throws EQException
	 */
	public String getiSeriesUserForBFUser(EquationUnit equationUnit, String bankFusionUserId) throws EQException
	{

		OCRecordDataModel ocRecord = equationUnit.getRecords().getOCRecordBFUser(bankFusionUserId);
		if (ocRecord == null)
		{
			throw new EQException("No mapping found for user");
		}
		String iSeriesUser = ocRecord.getOsUserId();
		if (!Toolbox.stringNotBlank(iSeriesUser))
		{
			throw new EQException("No iSeries user defined for user");
		}
		return iSeriesUser;
	}

	/**
	 * Creates a profile token for the specified iSeries user
	 * 
	 * @param equationUnit
	 *            The EquationUnit
	 * @param iSeriesUser
	 *            The iSeries user name
	 * @return the profile token in as a Base64 String
	 * 
	 * @throws EQException
	 */
	private String getProfileToken64(EquationUnit equationUnit, String iSeriesUser) throws EQException
	{
		String result = null;
		EquationStandardSession adminSession = null;
		try
		{
			adminSession = equationUnit.getEquationSessionPool().getEquationStandardSession();
			byte[] userTokenBytes = adminSession.getUserToken(iSeriesUser, DEFAULT_TOKEN_CHECK, "2");
			result = Toolbox.byteArrayToBase64String(userTokenBytes);
		}
		finally
		{
			if (equationUnit != null && adminSession != null)
			{
				equationUnit.getEquationSessionPool().returnEquationStandardSession(adminSession);
			}

		}
		return result;
	}

	/**
	 * Encapsulate conditioning of setting the BF processing date
	 * 
	 * @param processingDateChange
	 * @param equationUnit
	 * @param userLocator
	 */
	private void updateBFProcessingDate(boolean processingDateChange, EquationUnit equationUnit, String userLocator)
	{
		if ((processingDateChange || firstLogon) && updateBankFusionBusinessDate)
		{
			BFToolbox.setBankFusionBusinessDate(userLocator, equationUnit);
			firstLogon = false;
		}
	}

	/**
	 * Returns the original login user Id for the supplied session Id
	 * 
	 * When a BankFusion user id has been mapped to an Equation user id, this provides access to the original login credentials
	 * 
	 * @param sessionId
	 * @return a String containing the login user id
	 */
	public String getLoginUserBySessionId(String sessionId)
	{
		return sessionToLoginUserId.get(sessionId);
	}
	/**
	 * Returns the real Equation ISeries Profile for the supplied session Id
	 * 
	 * When a BankFusion user id has been mapped to an Equation user id, this provides access to the original login credentials
	 * 
	 * @param sessionId
	 * @return a String containing the Equation ISeries Profile user id
	 */
	public String getSessionToEquationIseriesProfile(String sessionId)
	{
		return sessionToEquationIseriesProfile.get(sessionId);
	}
	/**
	 * Maps a BankFusion user to a 4 character Equation user id
	 * 
	 * This is used in referral processing
	 * 
	 * @return String containing the 4 character Equation user Id
	 * 
	 * @throws EQException
	 */
	public String getEquationUserIdForBFUser(EquationUnit equationUnit, String bankFusionUserId) throws EQException
	{
		String equationUser = null;
		OCRecordDataModel ocRecord = equationUnit.getOCByBFUser(bankFusionUserId);
		if (ocRecord != null)
		{
			// Use the mapped (10 character) user Id:
			equationUser = ocRecord.getUserId();
		}
		if (equationUser == null)
		{
			throw new EQRuntimeException("Failed to determine Equation user for BankFusion user [" + bankFusionUserId + "]");
		}
		return equationUser;
	}

	/**
	 * Indicates if Equation/IBM i authentication is currently in use.
	 * 
	 * If not using Equation/IBM i authentication, this indicates that mapping must be performed between the login user id and the
	 * Equation/IBM i user id required for IBM i sessions, ESF processing etc.
	 * 
	 * @return true if Equation/IBM i, false if some other authentication provider
	 */
	public boolean isEquationAuthentication()
	{
		if (equationAuthentication == null)
		{
			equationAuthentication = Boolean.valueOf("EQ".equals(EquationCommonContext.getConfigProperty("eq.credentials", "EQ")));
		}
		return equationAuthentication.booleanValue();
	}

	/**
	 * Indicates if the CAS common profile should be used for accessing WebFacing functions when CAS authentication is in use.
	 * 
	 * @return true if the CAS common profile should be used.
	 */
	public boolean useCasCommonProfileForWebFacing()
	{
		if (useCasCommonProfileForWebFacing == null)
		{
			useCasCommonProfileForWebFacing = Boolean.valueOf("true".equals(EquationCommonContext.getConfigProperty(
							"use.cas.common.profile.for.webfacing", "true")));
		}
		return useCasCommonProfileForWebFacing.booleanValue();
	}

	/**
	 * Maps an Equation 4 character user id to a CAS user id
	 * 
	 * @param equationUnit
	 *            The EquationUnit instance
	 * 
	 * @param equationUserId
	 *            The 4 character Equation user id
	 * 
	 * @return String containing the CAS user Id, or null if no mapping was found
	 * 
	 * @throws EQException
	 */
	public String getCASUserIdForEquationUser(EquationUnit equationUnit, String equationUserId) throws EQException
	{
		String result = null;

		EquationStandardSession session = null;
		try
		{
			session = equationUnit.getEquationSessionPool().getEquationStandardSession();
			OCRecordDataModel ocRecord = new OCRecordDataModel(equationUserId);
			IOCRecordDao dao = new DaoFactory().getOCDao(session, ocRecord);
			ocRecord = dao.getOCRecord();
			if (ocRecord != null)
			{
				result = ocRecord.getBankFusionUserId();
			}
		}
		finally
		{
			if (session != null)
			{
				try
				{
					equationUnit.getEquationSessionPool().returnEquationStandardSession(session);
				}
				catch (EQException e)
				{
					LOG.error(e);
				}
			}
		}
		return result;
	}

	/**
	 * Returns the user id for display
	 * 
	 * This method will take the 4 character Equation user currently displayed when showing referral information. Depending on the
	 * status of CAS enablement, either the Equation user will be returned, or the CAS user will be looked up and returned.
	 * 
	 * @param equationUnit
	 *            The EquationUnit instance
	 * 
	 * @param equationUserId
	 *            The 4 character Equation user id
	 * 
	 * @return String containing the CAS user if appropriate, otherwise the supplied Equation user
	 */
	public String getDisplayUser(EquationUnit equationUnit, String equationUserId)
	{
		String result = equationUserId;
		if (!isEquationAuthentication())
		{
			try
			{
				String casUser = getCASUserIdForEquationUser(equationUnit, equationUserId);
				if (Toolbox.stringNotBlank(casUser))
				{
					result = casUser;
				}
			}
			catch (EQException e)
			{
				LOG.error(e);
			}
		}
		return result;
	}

	/**
	 * Obtains the configured GUI Administrator user and password for the specified unit
	 * 
	 * @param systemName
	 *            Name of the iSeries system
	 * @param unitMnemonic
	 *            Unit mnemonic
	 * @return If successful, returns a String [] with two elements; user and password. A null return value indicates that details
	 *         did not exist
	 */
	public String[] getConfiguredUnitCredentials(String systemName, String unitMnemonic)
	{
		String[] result = null;
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

		for (int index = 0; index < unitIds.length; index++)
		{
			if (systemIds[index].equals(systemName) && unitIds[index].equals(unitMnemonic))
			{
				result = new String[] { userIds[index], passwords[index] };
				break;
			}
		}
		return result;
	}

	/**
	 * Determine if branch server setup
	 * 
	 * @return true if branch server setup
	 */
	public static boolean isBranchServerSetup()
	{
		if (EquationCommonContext.isBankFusionInstalled())
		{
			if (FunctionBankFusion.isOfflineEnabled())
			{
				// Determine if branch server setup
				String dcServer = EquationCommonContext.getDCServer();
				return dcServer.length() > 0;
			}
		}
		return false;
	}
	/**
	 * Sets the Callback to the UI layer
	 * 
	 * This is called when the EquationDesktopContext class is initializing
	 * 
	 * @param uiLayerCallback
	 */
	public void setUILayerCallback(IUILayerCallback uiLayerCallback)
	{
		this.uiLayerCallback = uiLayerCallback;
	}

}