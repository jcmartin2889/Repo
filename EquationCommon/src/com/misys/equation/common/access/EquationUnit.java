package com.misys.equation.common.access;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.resource.spi.security.PasswordCredential;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.sql.DataSource;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.CharacterDataArea;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.DecimalDataArea;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.IllegalObjectTypeException;
import com.ibm.as400.access.ObjectDescription;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.wsspi.security.auth.callback.Constants;
import com.ibm.wsspi.security.auth.callback.WSMappingCallbackHandlerFactory;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IGAXRecordDao;
import com.misys.equation.common.dao.IOCRecordDao;
import com.misys.equation.common.dao.IWARecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAXRecordDataModel;
import com.misys.equation.common.dao.beans.GBRecordDataModel;
import com.misys.equation.common.dao.beans.GYWRecordDataModel;
import com.misys.equation.common.dao.beans.HBXRecordDataModel;
import com.misys.equation.common.dao.beans.IPVRecordDataModel;
import com.misys.equation.common.dao.beans.OCRecordDataModel;
import com.misys.equation.common.dao.beans.QZRecordDataModel;
import com.misys.equation.common.dao.beans.WARecordDataModel;
import com.misys.equation.common.datastructure.EqDS_DSJRN;
import com.misys.equation.common.datastructure.EqDS_DSSYS2;
import com.misys.equation.common.datastructure.EqDS_DSSYS3;
import com.misys.equation.common.datastructure.EqDS_DSSYSE;
import com.misys.equation.common.globalprocessing.UnitStatusHandler;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQRuntimeException;
import com.misys.equation.common.utilities.ApplicationContextManager;
import com.misys.equation.common.utilities.Enhancement;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationAPICacheHandler;
import com.misys.equation.common.utilities.EquationControl;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.EquationPVMetaDataCacheHandler;
import com.misys.equation.common.utilities.FunctionClassLoader;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.dataaccess.connectionpooling.ConnectionAccess;
import com.misys.equation.dataaccess.connectionpooling.impl.JndiConnectionPool;

/**
 * This class represents a unit, that is an instance of the banking application.
 */
public class EquationUnit implements Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationUnit.java 17823 2014-01-30 09:23:00Z perkinj1 $";

	public static final String VERSION_EQ342 = "EQ342";
	public static final String VERSION_EQ38 = "EQ382";
	public static final String VERSION_EQ4 = "EQ4";

	public static final String PHASE_DAY = "DAY";

	// required if implementing serializable
	private static final long serialVersionUID = 1L;
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EquationUnit.class);

	/** UTF-8 Charset name. Used when encoding/decoding XML */
	private static final String UTF8_CHARSET = "UTF-8";

	// declare all the strings that are relevant to a unit
	private String unitId;
	private String fxVersion;
	private String processingDate;
	private int processingDateDb;
	private String localCurrency;
	private String baseCurrency;
	private int ccsid;
	private boolean esf;
	private String kapSysInf;
	private String kapUnDes;
	private String kapPhs;
	private String helpURL;
	private String supportURL;
	private String corpLnkTxt;
	private String corpLnkURL;
	private String extLnk1Txt;
	private String extLnk1URL;
	private String extLnk2Txt;
	private String extLnk2URL;
	private String extLnk3Txt;
	private String extLnk3URL;
	private String extLnk4Txt;
	private String extLnk4URL;
	private String extLnk5Txt;
	private String extLnk5URL;
	private String webFormServApp;
	private String webFormServSvr;
	private String webFormServPort;
	private String webFacingApplication;
	private String webFacingServer;
	private String webFacingPort;
	private String unitVersion;

	private DataSource xaDataSource;
	private AbstractEquationSessionPool sessionPool;
	private final Hashtable<String, AbstractEquationSessionPool> userSessionPools = new Hashtable<String, AbstractEquationSessionPool>();

	private boolean alive;
	private boolean isHBXPFInstalled;
	private boolean isGYWPFInstalled;
	private boolean isIPVPFInstalled;
	private boolean isWEYPFBdtaInstalled;
	private String administrator;
	private EquationSystem system;
	private final Hashtable<String, EquationWidget> widgets = new Hashtable<String, EquationWidget>();
	private EquationDataStructureData dasysctl;
	private EquationDataStructureData kapjrn;
	private EquationDataStructureData dasysctl2;
	private EquationDataStructureData dasysctl3;
	private transient EquationUser poolUser;
	private String printDesktop;
	private boolean developmentUnit;
	private boolean isMakerCheckerKSMnonL;

	/** A cache of enhancement installed to the unit */
	private final Map<String, Boolean> enhancementsInstalled = new Hashtable<String, Boolean>();

	/**
	 * A set of enhancements found in CHPF but not verified - this is used for performance only so that UTR00R doesn't need to be
	 * called if we know that an enhancement isn't even in the CHPF table
	 */
	private Set<String> unverifiedEnhancementsInstalled = null;

	/** Class loader for loading user exit Java classes. This will be destroyed and re-initialised to clear the cache */
	private transient FunctionClassLoader rpgUserExitClassLoader;

	/** Class loader for loading function user exit Java classes. This will be destroyed and re-initialised to clear the cache */
	private transient FunctionClassLoader functionUserExitClassLoader;

	/** Map of RPG program names/Java class names. */
	private final Map<String, String> validationUserExitClasses = new HashMap<String, String>();

	/** Cache of user exit Screen field data-structures */
	private final Map<String, EquationScreenDataStructure> equationUserExitDSs = new Hashtable<String, EquationScreenDataStructure>();

	/** Cache of Equation PV Meta Data */
	private final EquationPVMetaDataCacheHandler pvMetaDataCacheHandler = new EquationPVMetaDataCacheHandler();

	/** Cache of Equation PV timestamps */
	private final Hashtable<String, String> pvTimestampCache = new Hashtable<String, String>();

	/** Cache of Equation PV result */
	private final EquationAPICacheHandler apiCacheHandler = new EquationAPICacheHandler();

	/** Cache of Equation PV result */
	private final EquationRecords records = new EquationRecords(this);

	/** Cache of System option data by system option id */
	private final Hashtable<String, String> systemOptionData = new Hashtable<String, String>();

	/** Cache of KSM message file path by language */
	private final Hashtable<String, String> ksmMessageFilePaths = new Hashtable<String, String>();

	private final Hashtable<String, EquationUser> userTemplates = new Hashtable<String, EquationUser>();

	/** DAO factory required for some methods */
	private static DaoFactory daoFactory = new DaoFactory();

	/**
	 * Default constructor
	 */
	@SuppressWarnings("unused")
	private EquationUnit()
	{
	}

	/**
	 * Construct a unit
	 * 
	 * @param system
	 *            - an system instance
	 * @param unitId
	 *            - unit mnemonic
	 * @param systemUserId
	 *            - user id
	 */
	public EquationUnit(EquationSystem system, String unitId, String systemUserId, String password, boolean useJNDI)
					throws EQException
	{
		this.unitId = unitId;
		this.system = system;

		LOG.debug("EqUnit() - [" + unitId + "] - entry");
		AS400 eqAS400 = null;
		EquationStandardSession session = null;
		try
		{
			// Create an XA data source for making XA connection.
			createXADataSource();

			// Create the session pool
			initialisePool(systemUserId, password, useJNDI);

			// Populate the isEq4 variable for this unit
			setUnitVersion();

			// Get the connection from the context
			eqAS400 = system.getAS400();
			session = sessionPool.getEquationStandardSession();

			// Refresh the unit
			refresh(eqAS400, session);

			// ccsid
			BigDecimal ccsidDecimal = getDecimalDataArea(eqAS400, "KLIB", "EQCCSID");
			if (ccsidDecimal == null)
			{
				ccsid = eqAS400.getCcsid();
			}
			else
			{
				ccsid = ccsidDecimal.intValue();
			}

			createNewRpgUserExitClassLoader();
			createNewFunctionUserExitClassLoader();

			// Mark as alive
			alive = true;

			// Setup if HBXPF is installed in the unit
			this.setHBXPFInstalled(eqAS400);
			this.setGYWPFInstalled(eqAS400);
			this.setIPVPFInstalled(eqAS400);

			// Setup if WEYPF BDTA field is installed in the unit
			this.setWEYPFBdtaInstalled(session);

			// Load records
			records.setUpRecords(session);

			// maker-checker
			if (hasMakerCheckerEnhancement(session))
			{
				setMakerCheckerKSMnonL(session);
			}
			LOG.info("latest FX Release installed in [" + unitId + "]: " + getFXVersion(session));
		}
		catch (EQException e)
		{
			if (sessionPool != null)
			{
				close();
			}
			setAlive(false);
			throw e;
		}
		finally
		{
			// Return the AS400 to the pool
			if (eqAS400 != null)
			{
				system.returnAS400(eqAS400);
			}
			if (session != null)
			{
				sessionPool.returnEquationStandardSession(session);
			}
			LOG.debug("EqUnit() - [" + unitId + "] - exiting");
		}
	}

	/**
	 * Determine if maker/checker ksm set to non-L?
	 * 
	 */
	public void setMakerCheckerKSMnonL(EquationStandardSession session)
	{
		// Maker-Checker KSM must be non-"L" in WMENU1/SOM

		WARecordDataModel dataModel = new WARecordDataModel(WARecordDataModel.MAKER_CHECKER_KSM);
		DaoFactory daoFactory = new DaoFactory();
		IWARecordDao waDao = daoFactory.getWADao(session, dataModel);

		WARecordDataModel waRecord = waDao.getWARecord();

		if (waRecord != null && waRecord.getSev().trim().equals(WARecordDataModel.NO_SUPERVISOR))
		{
			isMakerCheckerKSMnonL = false;
		}
		else
		{
			isMakerCheckerKSMnonL = true;
		}
	}

	/**
	 * is maker/checker ksm set to non-L?
	 */
	public boolean isMakerCheckerKSMnonL()
	{
		return isMakerCheckerKSMnonL;
	}

	/**
	 * Return the Equation system
	 * 
	 * @return the Equation system
	 */
	public EquationSystem getEquationSystem()
	{
		return system;
	}

	/**
	 * Return the prompt validate meta data cache
	 * 
	 * @return the prompt validate meta data cache
	 */
	public EquationPVMetaDataCacheHandler getPvMetaDataCacheHandler()
	{
		return pvMetaDataCacheHandler;
	}

	/**
	 * Return the prompt validate result cache
	 * 
	 * @return the prompt validate result cache
	 */
	public EquationAPICacheHandler getApiCacheHandler()
	{
		return apiCacheHandler;
	}
	// this sets up the connection pool for the user specified on the user J2C alias
	public boolean initialiseConnectionPool(String dataSourceName) throws EQException
	{
		boolean initialised = false;
		if (getEquationSessionPool(dataSourceName) == null)
		{
			String[] userPoolCredentials = getUserPoolCredentials(dataSourceName);
			String poolUser = userPoolCredentials[0];
			String poolUserPassword = userPoolCredentials[1];

			initialisePool(poolUser, poolUserPassword, true, dataSourceName);
			initialised = true;

		}
		else
		{
			initialised = true;
		}
		return initialised;
	}
	/**
	 * Initialise the pool
	 * 
	 * @param userId
	 *            - user id
	 * @param password
	 *            - user password
	 * @param jndiPool
	 *            - true if there is a JNDI data source used in obtaining connections
	 * 
	 * @throws EQException
	 */
	private synchronized void initialisePool(String userId, String password, boolean jndiPool) throws EQException
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();

		if (jndiPool)
		{
			sessionPool = (EquationSessionPool) applicationContextManager.getBean("EquationSessionPool");
		}
		else
		{
			sessionPool = (NonJndiSessionPool) applicationContextManager.getBean("NonJndiSessionPool");
		}

		sessionPool.setUnit(this);
		sessionPool.setLibraries("*LIBL");
		sessionPool.setNaming("system");

		sessionPool.initialisePool(userId, password);

		// store the pool user here
		poolUser = new EquationUser(this, userId);
	}
	/**
	 * Initialise the pool
	 * 
	 * @param userId
	 *            - user id
	 * @param password
	 *            - user password
	 * @param jndiPool
	 *            - true if there is a JNDI data source used in obtaining connections
	 * @param dataSourceName
	 *            - the name of the Data Source this pool is being created with
	 * 
	 * @throws EQException
	 */
	public synchronized void initialisePool(String userId, String password, boolean jndiPool, String dataSourceName)
					throws EQException
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		AbstractEquationSessionPool userSessionPool = null;
		if (jndiPool)
		{
			userSessionPool = (EquationSessionPool) applicationContextManager.getBean("EquationSessionPool");
			userSessionPool.setUnit(this);
			userSessionPool.setLibraries("*LIBL");
			userSessionPool.setNaming("system");

			((EquationSessionPool) userSessionPool).initialisePool(userId, password, dataSourceName);
		}
		else
		{
			userSessionPool = (NonJndiSessionPool) applicationContextManager.getBean("NonJndiSessionPool");
			userSessionPool.setUnit(this);
			userSessionPool.setLibraries("*LIBL");
			userSessionPool.setNaming("system");

			userSessionPool.initialisePool(userId, password);
		}

		userSessionPools.put(dataSourceName, userSessionPool);
	}
	/**
	 * Create XA Data Source using JNDI lookup
	 */
	public void createXADataSource() throws EQException
	{
		// Service Composer Application will never be XA
		if (!EquationCommonContext.isServiceComposerInstalled())
		{
			if (EquationCommonContext.getContext().isXAUsed())
			{
				// Obtain our environment naming context
				Context envCtx;
				xaDataSource = null;

				try
				{
					envCtx = new InitialContext();
				}
				catch (NamingException e)
				{
					throw new EQException("EqUnit: createXADataSource: There was a problem obtaining IntialContext.", e);
				}
				// Look up our data source
				String dataSourceName = "jdbc/EQ-" + system.getSystemId() + "-" + unitId + "-XA";
				try
				{
					xaDataSource = (DataSource) envCtx.lookup(dataSourceName);
				}
				catch (Exception e)
				{
					throw new EQException("EqUnit: createXADataSource: data source [" + dataSourceName + "] not found.", e);
				}
			}
		}
	}

	/**
	 * Retrieve the contents of a decimal data area, given the specified library and data area.
	 * 
	 * @param eqAS400
	 *            - AS400
	 * @param lib
	 *            - library where the data area resides. This can be specified as a concrete library or if
	 *            <code>ObjectDescription.LIBRARY_LIST</code> is specified then the library list on the AS400 object will be set
	 *            based on the unit's current *LIBL and the data area will be searched for within that library list.
	 * @param dataArea
	 *            - data area name
	 * 
	 * @return the contents of the data area (or null if the data area is not found)
	 * 
	 * @throws EQException
	 */
	private BigDecimal getDecimalDataArea(AS400 eqAS400, String lib, String dataArea) throws EQException
	{
		try
		{
			DecimalDataArea decDataArea;

			if (lib.equals(ObjectDescription.LIBRARY_LIST))
			{
				setAS400Libl(eqAS400, unitId);
				ObjectDescription o = new ObjectDescription(eqAS400, ObjectDescription.LIBRARY_LIST, dataArea, "DTAARA");
				decDataArea = new DecimalDataArea(eqAS400, o.getPath());
				return decDataArea.read();
			}
			else
			{
				String library = lib + unitId;
				decDataArea = new DecimalDataArea(eqAS400, "/QSYS.LIB/" + library + ".LIB/" + dataArea + ".DTAARA");
			}
			return decDataArea.read();
		}
		catch (ObjectDoesNotExistException e)
		{
			return null;
		}
		catch (Exception e)
		{
			throw new EQException("EqUnit: getCharacterDataArea:" + Toolbox.getExceptionMessage(e), e);
		}
	}

	/**
	 * Retrieve the contents of a character data area, given the specified library and data area.
	 * 
	 * @param eqAS400
	 *            - AS400
	 * @param lib
	 *            - library where the data area resides. This can be specified as a concrete library or if
	 *            <code>ObjectDescription.LIBRARY_LIST</code> is specified then the library list on the AS400 object will be set
	 *            based on the unit's current *LIBL and the data area will be searched for within that library list.
	 * @param dataArea
	 *            - data area name
	 * 
	 * @return the contents of the data area (or null if the data area is not found)
	 * 
	 * @throws EQException
	 */
	private String getCharacterDataArea(AS400 eqAS400, String lib, String dataArea) throws EQException
	{
		try
		{
			CharacterDataArea charDataArea;

			if (lib.equals(ObjectDescription.LIBRARY_LIST))
			{
				setAS400Libl(eqAS400, unitId);
				ObjectDescription o = new ObjectDescription(eqAS400, ObjectDescription.LIBRARY_LIST, dataArea, "DTAARA");
				charDataArea = new CharacterDataArea(eqAS400, o.getPath());
				return charDataArea.read();
			}
			else
			{
				String library = lib + unitId;
				charDataArea = new CharacterDataArea(eqAS400, "/QSYS.LIB/" + library + ".LIB/" + dataArea + ".DTAARA");
			}

			// ignore position 344-349 of DASYSCTL as it contains HEX values FA-FF
			if (dataArea.equals("DASYSCTL"))
			{
				return (charDataArea.read(0, 343) + "      " + charDataArea.read(349, 163));
			}
			else
			{
				return charDataArea.read();
			}
		}
		catch (ErrorCompletingRequestException e)
		{
			throw new EQException("EqUnit: getCharacterDataArea:" + Toolbox.getExceptionMessage(e), e);
		}
		catch (AS400SecurityException e)
		{
			throw new EQException("EqUnit: getCharacterDataArea:" + Toolbox.getExceptionMessage(e), e);
		}
		catch (IllegalObjectTypeException e)
		{
			throw new EQException("EqUnit: getCharacterDataArea:" + Toolbox.getExceptionMessage(e), e);
		}
		catch (InterruptedException e)
		{
			throw new EQException("EqUnit: getCharacterDataArea:" + Toolbox.getExceptionMessage(e), e);
		}
		catch (IOException e)
		{
			throw new EQException("EqUnit: getCharacterDataArea:" + Toolbox.getExceptionMessage(e), e);
		}
		catch (ObjectDoesNotExistException e)
		{
			return null;
		}
	}

	/**
	 * Sets up the unit library list on the as400 object based on the EQLIBL data area in the unit's KLIB.
	 * 
	 * @param as400
	 *            - The AS400 object
	 * @param unitId
	 *            - The String ID of the Equation Unit
	 */
	public static void setAS400Libl(AS400 as400, String unitId)
	{
		try
		{
			CommandCall c = new CommandCall(as400);
			String eqlibl = new CharacterDataArea(as400, "/QSYS.LIB/KLIB" + unitId.trim() + ".LIB/EQLIBL.DTAARA").read().trim();
			StringTokenizer token = new StringTokenizer(eqlibl, " ");
			String[] commands = new String[token.countTokens()];

			for (int i = 0; i < commands.length; i++)
			{
				commands[i] = token.nextToken().trim();
			}

			for (int i = commands.length - 1; i >= 0; i--)
			{
				c.setCommand("ADDLIBLE " + commands[i]);
				c.run();
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}

	/**
	 * Setup the external link
	 * 
	 * @param extLnk
	 *            - external link
	 */
	private void setUnitLinks(String extLnk)
	{
		// Links
		if (extLnk != null)
		{
			extLnk1Txt = extLnk.substring(0, 16).trim(); // 0-16
			extLnk1URL = extLnk.substring(16, 116).trim(); // 16,116
			extLnk2Txt = extLnk.substring(116, 132).trim(); // 116,132
			extLnk2URL = extLnk.substring(132, 232).trim();// 132,232
			extLnk3Txt = extLnk.substring(232, 248).trim();// 232,248
			extLnk3URL = extLnk.substring(248, 348).trim();// 248,348
			extLnk4Txt = extLnk.substring(348, 364).trim();// 348,364
			extLnk4URL = extLnk.substring(364, 464).trim();// 364,464
			extLnk5Txt = extLnk.substring(464, 480).trim();// 464,480
			extLnk5URL = extLnk.substring(480, 580).trim();// 480,580
			corpLnkTxt = extLnk.substring(580, 596).trim();// 580,596
			corpLnkURL = extLnk.substring(596, 696).trim();// 596,696
		}
	}

	/**
	 * Retrieve the system option via QZ file from cache if it has already been cached. If not yet cache, then retrieve from iSeries
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param systemOptionId
	 *            - the system option id
	 * @param forceCheck
	 *            - true if it should always try to load the latest from database
	 * 
	 * @return the value of the system option (or "" if the system option is not found or null if the data area is not found)
	 */
	public String getSystemOption(EquationStandardSession session, String libRoot, String systemOptionId, boolean forceCheck)
					throws EQException
	{
		// development unit, always retrieve from iSeries
		if (forceCheck)
		{
			return getSystemOption(session, libRoot, systemOptionId);
		}

		// check if already cache
		else
		{
			String value = systemOptionData.get(systemOptionId);
			if (value != null)
			{
				return value;
			}
			else
			{
				value = getSystemOption(session, libRoot, systemOptionId);
				if (value != null)
				{
					systemOptionData.put(systemOptionId, value);
				}
				return value;
			}
		}
	}

	/**
	 * Retrieve the system option via QZ file. Consider using getSystemOption() with 'forceCheck' parameter for performance reason
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param systemOptionId
	 *            - the system option id
	 * 
	 * @return the value of the system option (or "" if the system option is not found or null if the data area is not found)
	 */
	public String getSystemOption(EquationStandardSession session, String libRoot, String systemOptionId) throws EQException
	{
		// retrieve the system option details
		QZRecordDataModel qzRecord = records.getQZRecord(systemOptionId);

		// system option details not found
		if (qzRecord == null)
		{
			return "";
		}

		// retrieve from the data area
		AS400 eqAS400 = null;
		String value = "";
		try
		{
			eqAS400 = system.getAS400();
			String dataAreaContent = getCharacterDataArea(eqAS400, libRoot, qzRecord.getDataarea());

			// data area not found
			if (dataAreaContent == null)
			{
				return null;
			}

			// extract the details
			else
			{
				value = dataAreaContent.substring(qzRecord.getFieldPosition() - 1, qzRecord.getFieldLength()
								+ qzRecord.getFieldPosition() - 1);
			}
		}
		finally
		{
			// return the session
			if (eqAS400 != null)
			{
				system.returnAS400(eqAS400);
			}
		}
		return value;
	}

	/**
	 * Retrieve a system option. System option must exists in one of the data areas DASYSCTL, DASYSCT2 or DASYSCT3.
	 * 
	 * @param fieldName
	 *            - field name
	 * 
	 * @return the system option value
	 * @equation.external
	 * 
	 */
	public String getSystemOption(String fieldName)
	{
		if (dasysctl.getEqDS().containsField(fieldName))
		{
			return dasysctl.getFieldValue(fieldName).trim();
		}
		else if (dasysctl2.getEqDS().containsField(fieldName))
		{
			return dasysctl2.getFieldValue(fieldName).trim();
		}
		else if (dasysctl3.getEqDS().containsField(fieldName))
		{
			return dasysctl3.getFieldValue(fieldName).trim();
		}
		else
		{
			return "";
		}
	}

	/**
	 * Return the Equation session pool
	 * 
	 * @return the Equation session pool
	 */
	public AbstractEquationSessionPool getEquationSessionPool()
	{
		return sessionPool;
	}
	/**
	 * Return the Equation session pool for the dataSourceName
	 * 
	 * @return the Equation session pool
	 */
	public AbstractEquationSessionPool getEquationSessionPool(String dataSourceName)
	{
		return userSessionPools.get(dataSourceName);
	}
	/**
	 * Return the unit's current phase
	 * 
	 * @return the unit's current phase
	 * @equation.external
	 */
	public String getPhase()
	{
		return kapPhs;
	}

	/**
	 * Return the unit's information
	 * 
	 * @return the unit's information
	 * @equation.external
	 * 
	 */
	public String getUnitInformation()
	{
		return kapSysInf;
	}

	/**
	 * Return the unit's description
	 * 
	 * @return the unit's description
	 * @equation.external
	 */
	public String getDescription()
	{
		return kapUnDes;
	}

	/**
	 * Return the unit's processing date
	 * 
	 * @return the unit's processing date
	 * @equation.external
	 */
	public String getProcessingDate()
	{
		return processingDate;
	}

	/**
	 * Set the unit's processing date in database format
	 * 
	 * @return the unit's processing date in database format
	 * @equation.external
	 */
	public int getProcessingDateCYYMMDD()
	{
		return processingDateDb;
	}

	/**
	 * Return the unit id
	 * 
	 * @return the unit id
	 * @equation.external
	 */
	public String getUnitId()
	{
		return unitId;
	}

	/**
	 * Return the unit KLIB library
	 * 
	 * @return the unit KLIB library
	 */
	public String getKLIBLibrary()
	{
		return "KLIB" + unitId;
	}

	/**
	 * Return the unit KFIL library
	 * 
	 * @return the unit KFIL library
	 * @equation.external
	 */
	public String getKFILLibrary()
	{
		return "KFIL" + unitId;
	}

	/**
	 * Return the unit KINP library
	 * 
	 * @return the unit KINP library
	 */
	public String getKINPLibrary()
	{
		return "KINP" + unitId;
	}

	/**
	 * Return the unit KWRK library
	 * 
	 * @return the unit KWRK library
	 */
	public String getKWRKLibrary()
	{
		return "KWRK" + unitId;
	}

	/**
	 * Return the unit's normal input Equation journal
	 * 
	 */
	private String getNormalInputJournal()
	{
		return kapjrn.getFieldValue(EqDS_DSJRN.NIJ).trim();
	}

	/**
	 * Return the unit's normal input Equation journal library
	 * 
	 */
	private String getNormalInputJournalLib()
	{
		return kapjrn.getFieldValue(EqDS_DSJRN.NIJL).trim();
	}

	/**
	 * Return the unit's normal input full path Equation journal
	 * 
	 */
	public String getNIJournalFullPath()
	{
		return getNormalInputJournalLib() + "/" + getNormalInputJournal();
	}

	/**
	 * Return the help URL
	 * 
	 * @return the help URL
	 */
	public String getHelpURL()
	{
		return helpURL;
	}

	/**
	 * Return the external link text 1
	 * 
	 * @return the external link text 1
	 */
	public String getExtLnk1Txt()
	{
		char space = 32;
		char nbsp = 160;
		extLnk1Txt = extLnk1Txt.replace(space, nbsp);
		return extLnk1Txt;
	}

	/**
	 * Return the external link URL 1
	 * 
	 * @return the external link URL 1
	 */
	public String getExtLnk1URL()
	{
		return extLnk1URL;
	}

	/**
	 * Return the external link text 2
	 * 
	 * @return the external link text 2
	 */
	public String getExtLnk2Txt()
	{
		char space = 32;
		char nbsp = 160;
		extLnk2Txt = extLnk2Txt.replace(space, nbsp);
		return extLnk2Txt;
	}

	/**
	 * Return the external link URL 2
	 * 
	 * @return the external link URL 2
	 */
	public String getExtLnk2URL()
	{
		return extLnk2URL;
	}

	/**
	 * Return the external link text 3
	 * 
	 * @return the external link text 3
	 */
	public String getExtLnk3Txt()
	{
		char space = 32;
		char nbsp = 160;
		extLnk3Txt = extLnk3Txt.replace(space, nbsp);
		return extLnk3Txt;
	}

	/**
	 * Return the external link URL 3
	 * 
	 * @return the external link URL 3
	 */
	public String getExtLnk3URL()
	{
		return extLnk3URL;
	}

	/**
	 * Return the external link text 4
	 * 
	 * @return the external link text 4
	 */
	public String getExtLnk4Txt()
	{
		char space = 32;
		char nbsp = 160;
		extLnk4Txt = extLnk4Txt.replace(space, nbsp);
		return extLnk4Txt;
	}

	/**
	 * Return the external link URL 4
	 * 
	 * @return the external link URL 4
	 */
	public String getExtLnk4URL()
	{
		return extLnk4URL;
	}

	/**
	 * Return the external link text 5
	 * 
	 * @return the external link text 5
	 */
	public String getExtLnk5Txt()
	{
		char space = 32;
		char nbsp = 160;
		extLnk5Txt = extLnk5Txt.replace(space, nbsp);
		return extLnk5Txt;
	}

	/**
	 * Return the external link URL 5
	 * 
	 * @return the external link URL 5
	 */
	public String getExtLnk5URL()
	{
		return extLnk5URL;
	}

	/**
	 * Return the support link URL
	 * 
	 * @return the support link URL
	 */
	public String getSupportURL()
	{
		return supportURL;
	}

	/**
	 * Return the corporate link text
	 * 
	 * @return the corporate link text
	 */
	public String getCorpLnkTxt()
	{
		char space = 32;
		char nbsp = 160;
		corpLnkTxt = corpLnkTxt.replace(space, nbsp);
		return corpLnkTxt;
	}

	/**
	 * Return the corporate link URL
	 * 
	 * @return the corporate link URL
	 */
	public String getCorpLnkURL()
	{
		return corpLnkURL;
	}

	/**
	 * Close the Equation session pool
	 */
	public void close()
	{
		// Cleanup User Templates
		userTemplates.clear();
		sessionPool.close();
		// additional action for WAS to purge data source connection pools
		if (Toolbox.isAWebSphereApplicationServer() && JndiConnectionPool.checkJndiAvailable())
		{
			String host = EquationCommonContext.getAppServerProperty(EquationCommonContext.BFEQ_HOST_NAME);
			String port = EquationCommonContext.getAppServerProperty(EquationCommonContext.BFEQ_SOAP_PORT);
			String dsName = "EQ-" + this.system.getSystemId() + "-" + this.unitId;

			// send a purgePoolContents action on the named Data Source
			Toolbox.sendJMXWebSphereMBeanRequest(host, port, "DataSource", dsName, "purgePoolContents");
		}

	}
	/**
	 * Close the Equation session pool for the dataSourceName
	 */
	public void close(String dataSourceName)
	{
		getEquationSessionPool(dataSourceName).close();
		// additional action for WAS to purge data source connection pools
		if (Toolbox.isAWebSphereApplicationServer() && JndiConnectionPool.checkJndiAvailable())
		{
			String host = EquationCommonContext.getAppServerProperty(EquationCommonContext.BFEQ_HOST_NAME);
			String port = EquationCommonContext.getAppServerProperty(EquationCommonContext.BFEQ_SOAP_PORT);
			// String dsName = "EQ-" + this.system.getSystemId() + "-" + this.unitId;

			// send a purgePoolContents action on the named Data Source
			Toolbox.sendJMXWebSphereMBeanRequest(host, port, "DataSource", dataSourceName, "purgePoolContents");
		}
		userSessionPools.remove(dataSourceName);

	}
	/**
	 * Return the web form server application
	 * 
	 * @return the web form server application
	 */
	public String getWebFormServApp()
	{
		return webFormServApp;
	}

	/**
	 * Return the web form server application port
	 * 
	 * @return the web form server application port
	 */
	public String getWebFormServPort()
	{
		return webFormServPort;
	}

	/**
	 * Return the web form server application service
	 * 
	 * @return the web form server application service
	 */
	public String getWebFormServSvr()
	{
		return webFormServSvr;
	}

	/**
	 * Return true if the Equation unit has been successfully initialised
	 * 
	 * @return true if the Equation unit has been successfully initialised
	 */
	public boolean isAlive()
	{
		return alive;
	}
	/**
	 * Return true if the Equation unit is in a usable state
	 * 
	 * @return true if the Equation unit is in a usable state
	 */
	public boolean isAvailable()
	{
		UnitStatusHandler unitStatusHandler = getUnitStatus();
		// Keep this code aligned with SystemStatusManager
		String mode = unitStatusHandler.getMode().toString().trim();
		// Phase must be DAY mode and mode must be NORM or EXTN
		if (!getPhase().trim().equals("DAY") || (!mode.equals("NORM") && !mode.equals("EXTN")))
		{
			return false;
		}
		String suspendRequest = unitStatusHandler.getSuspendRequest().trim();
		if (!suspendRequest.equals(""))
		{
			return false;
		}
		return true;
	}
	/**
	 * Set whether the Equation unit has been successfully initialised
	 * 
	 * @param alive
	 *            - true if the Equation unit has been successfully initialised
	 */
	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}

	/**
	 * Return the unit administrator
	 * 
	 * @return the unit administrator
	 */
	public String getAdministrator()
	{
		return administrator;
	}

	/**
	 * Return the Equation system where this unit resides
	 * 
	 * @return the Equation system where this unit resides
	 */
	public EquationSystem getSystem()
	{
		return system;
	}

	/**
	 * Return the pool user
	 * 
	 * @return the pool user
	 */
	public EquationUser getPoolUser()
	{
		return poolUser;
	}

	/**
	 * Return the unit's CCSID
	 * 
	 * @return the unit's CCSID
	 * @equation.external
	 */
	public int getCcsid()
	{
		return ccsid;
	}

	/**
	 * Return the Equation desktop application server application
	 * 
	 * @return the Equation desktop application server application
	 */
	public String getWebFacingApplication()
	{
		return webFacingApplication;
	}

	/**
	 * Return the Equation desktop application server
	 * 
	 * @return the Equation desktop application server
	 */
	public String getWebFacingServer()
	{
		return webFacingServer;
	}

	/**
	 * Return the Equation desktop application server port
	 * 
	 * @return the Equation desktop application server port
	 */
	public String getWebFacingPort()
	{
		return webFacingPort;
	}

	/**
	 * Return the local currency
	 * 
	 * @return the local currency
	 * @equation.external
	 */
	public String getLocalCurrency()
	{
		return localCurrency;
	}

	/**
	 * Return the base currency
	 * 
	 * @return the base currency
	 * @equation.external
	 */
	public String getBaseCurrency()
	{
		return baseCurrency;
	}

	/**
	 * Determine if Enhanced Security Feature is active
	 * 
	 * @return true if ESF is active
	 */
	public boolean isEsfActive()
	{
		return esf;
	}

	/**
	 * Return the RPG user exit class loader
	 * 
	 * @return the RPG user exit class loader
	 */
	public FunctionClassLoader getRpgUserExitClassLoader()
	{
		return rpgUserExitClassLoader;
	}

	/**
	 * Create a new RPG user exit class loader
	 */
	public void createNewRpgUserExitClassLoader()
	{
		AccessController.doPrivileged(new PrivilegedAction<Void>()
		{
			public Void run()
			{
				rpgUserExitClassLoader = new FunctionClassLoader(getClass().getClassLoader());
				return null;
			}
		});
	}

	/**
	 * Return the Function user exit class loader
	 * 
	 * @return the Function user exit class loader
	 */
	public FunctionClassLoader getFunctionUserExitClassLoader()
	{
		return functionUserExitClassLoader;
	}

	/**
	 * Create a new Function user exit class loader
	 */
	public void createNewFunctionUserExitClassLoader()
	{
		AccessController.doPrivileged(new PrivilegedAction<Void>()
		{
			public Void run()
			{
				functionUserExitClassLoader = new FunctionClassLoader(getClass().getClassLoader());
				return null;
			}
		});
	}

	/**
	 * Return the validate user exit classes
	 * 
	 * @return the validate user exit classes
	 */
	public Map<String, String> getValidationUserExitClasses()
	{
		return validationUserExitClasses;
	}

	/**
	 * Return the user exit data structures
	 * 
	 * @return the user exit data structures
	 */
	public Map<String, EquationScreenDataStructure> getEquationUserExitDSs()
	{
		return equationUserExitDSs;
	}

	/**
	 * Get the print desktop transaction system variable
	 * 
	 * @return the print desktop transaction system variable
	 */
	public String getPrintDesktop()
	{
		return printDesktop;
	}

	/**
	 * Refreshes the unit, as it may have already been changed
	 */
	public void refresh() throws EQException
	{
		// Get the connection from the context
		AS400 eqAS400 = system.getAS400();
		EquationStandardSession session = sessionPool.getEquationStandardSession();
		try
		{
			refresh(eqAS400, session);
		}
		catch (Exception e)
		{
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		finally
		{
			if (eqAS400 != null)
			{
				system.returnAS400(eqAS400);
			}
			if (session != null)
			{
				sessionPool.returnEquationStandardSession(session);
			}
		}
	}

	/**
	 * Refreshes the unit, as it may have already been changed
	 * 
	 * @param eqAS400
	 *            - the AS400 connection
	 * @param session
	 *            - the Equation session
	 * 
	 * @throws EQException
	 */
	private void refresh(AS400 eqAS400, EquationStandardSession session) throws EQException
	{
		// Retrieve the unit's DASYSCTL
		byte[] data = session.callUTW52R("DASYSCTL");
		dasysctl = new EquationDataStructureData(new EqDS_DSSYSE());
		dasysctl.setBytes(data);

		// Retrieve the unit's DASYSCTL2
		data = session.callUTW52R("DASYSCT2");
		dasysctl2 = new EquationDataStructureData(new EqDS_DSSYS2());
		dasysctl2.setBytes(data);

		// Retrieve the unit's DASYSCTL3
		data = session.callUTW52R("DASYSCT3");
		dasysctl3 = new EquationDataStructureData(new EqDS_DSSYS3());
		dasysctl3.setBytes(data);

		// Retrieve the unit's KAPJRN
		data = session.callUTW52R("KAPJRN");
		kapjrn = new EquationDataStructureData(new EqDS_DSJRN());
		kapjrn.setBytes(data);

		// Processing dates
		processingDate = getSystemOption(EqDS_DSSYSE.ZLDATE);
		processingDateDb = Toolbox.parseInt(getSystemOption(EqDS_DSSYSE.PDATE), 0);

		// Base ccy
		baseCurrency = getSystemOption(EqDS_DSSYSE.BCCYA);

		// Local ccy
		localCurrency = getSystemOption(EqDS_DSSYSE.LCDCD);

		// Load the set of CHPF enhancements so that UTR00R doesn't need to be called if we know that
		// an enhancement isn't even in the CHPF table
		loadUnverifiedEnhancementMnemonics(session);

		// ESF, ensure both K388 and ESE flags are Y
		esf = getSystemOption(EqDS_DSSYSE.ESE).equals(EqDataType.YES);
		if (esf)
		{
			esf = isEnhancementInstalled(Enhancement.K388);
		}

		// Unit info
		kapSysInf = getCharacterDataArea(eqAS400, "KLIB", "KAPSYSINF");

		// phase
		kapPhs = getCharacterDataArea(eqAS400, "KLIB", "KAPPHS");

		// Unit description
		kapUnDes = getCharacterDataArea(eqAS400, "KLIB", "KAPUNDES");

		// Help text path
		helpURL = getCharacterDataArea(eqAS400, "KLIB", "EQHELPPATH");

		// Support URL
		supportURL = "https://www.salesforce.com/login.jsp";

		// Set up external link URLs
		String extLnk = getCharacterDataArea(eqAS400, "KFIL", "DAWFLINK");
		setUnitLinks(extLnk);

		// Development Unit?
		developmentUnit = getSystemOption(EqDS_DSSYS3.EQ4DU).equals("Y");

		// Data area DASYSCT2
		String dasysct2 = getCharacterDataArea(eqAS400, "KFIL", "DASYSCT2");

		// This is needed as there is a bug in UTW52R that it is unable to return the last 36 characters!
		dasysctl2.setFieldValue(EqDS_DSSYS2.IPJD, dasysct2.substring(1956, 1966));
		dasysctl2.setFieldValue(EqDS_DSSYS2.CFPRI, dasysct2.substring(1967, 1968));
		dasysctl2.setFieldValue(EqDS_DSSYS2.CIACV, dasysct2.substring(1968, 1969));
		dasysctl2.setFieldValue(EqDS_DSSYS2.VDIFT, dasysct2.substring(1969, 1970));
		dasysctl2.setFieldValue(EqDS_DSSYS2.SACID, dasysct2.substring(1970, 1975));
		dasysctl2.setFieldValue(EqDS_DSSYS2.CVDS, dasysct2.substring(1977, 1978));
		dasysctl2.setFieldValue(EqDS_DSSYS2.AIKE, dasysct2.substring(1978, 1979));
		dasysctl2.setFieldValue(EqDS_DSSYS2.CACCI, dasysct2.substring(1981, 1982));
		dasysctl2.setFieldValue(EqDS_DSSYS2.PDT, dasysct2.substring(1983, 1984));
		dasysctl2.setFieldValue(EqDS_DSSYS2.AMSI, dasysct2.substring(1984, 1985));

		// Equation Services Application Name
		webFormServApp = getSystemOption(EqDS_DSSYS2.EQSID);
		if (webFormServApp.equals(""))
		{
			webFormServApp = "WebForm";
		}
		// Equation Services Application Server
		webFormServSvr = getSystemOption(EqDS_DSSYS2.EQSSV);

		// Equation Services Application Server Port
		webFormServPort = getSystemOption(EqDS_DSSYS2.EQSAP);
		if (webFormServPort.equals(""))
		{
			webFormServPort = "80";
		}

		// Web Facing details (also used for EQ4)
		webFacingServer = getSystemOption(EqDS_DSSYS2.WFAPS);
		webFacingApplication = getSystemOption(EqDS_DSSYS2.WFAPI);
		webFacingPort = getSystemOption(EqDS_DSSYS2.WFAPP);

		// Get the administrator
		administrator = getSystemOption(EqDS_DSSYS2.EQSUI);

		// Get the print desktop transaction
		printDesktop = getSystemOption(EqDS_DSSYS2.PDT);

		// TODO: Should we clear the hash tables?
	}

	/**
	 * Refresh/clear unit cache
	 * 
	 * @throws EQException
	 */
	public synchronized void refreshCache() throws EQException
	{
		// Cleanup User Templates
		userTemplates.clear();
		// Get the connection from the context
		AS400 eqAS400 = system.getAS400();
		EquationStandardSession session = sessionPool.getEquationStandardSession();
		try
		{
			refreshCache(eqAS400, session);
		}
		catch (Exception e)
		{
			LOG.error(e);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		finally
		{
			if (eqAS400 != null)
			{
				system.returnAS400(eqAS400);
			}
			if (session != null)
			{
				sessionPool.returnEquationStandardSession(session);
			}
		}
	}

	/**
	 * Refresh/clear unit cache
	 * 
	 * @param eqAS400
	 *            - the AS400 connection
	 * @param session
	 *            - the Equation session
	 * 
	 * @throws EQException
	 */
	public synchronized void refreshCache(AS400 eqAS400, EquationStandardSession session) throws EQException
	{
		// refresh unit
		refresh(eqAS400, session);

		// maker-checker
		if (hasMakerCheckerEnhancement(session))
		{
			setMakerCheckerKSMnonL(session);
		}

		// clear cache
		enhancementsInstalled.clear();
		validationUserExitClasses.clear();
		equationUserExitDSs.clear();
		pvMetaDataCacheHandler.clear();
		pvTimestampCache.clear();
		apiCacheHandler.clear();
		systemOptionData.clear();
		ksmMessageFilePaths.clear();
		userTemplates.clear();

		// clear record cache
		records.clear();

		// class loader
		createNewFunctionUserExitClassLoader();
		createNewRpgUserExitClassLoader();

		// reload HBX records
		records.reloadHBXRecords(session, HBXRecordDataModel.MISYS_OWNED_TEXT, true);
		records.reloadHBXRecords(session, HBXRecordDataModel.BANK_OWNED_TEXT, true);
	}

	/**
	 * Return a string delimited by ":" of the unique key fields for a given logical
	 * 
	 * @param root
	 *            - KFIL,KINP,KWRK
	 * @param tableId
	 *            - the table name
	 * @param globalTable
	 *            - is the table in the Equation global library
	 * 
	 * @return a string delimited by ":" of the unique key fields
	 * 
	 * @throws EQException
	 */
	public String getUniqueKeys(String root, String tableId, boolean globalTable) throws EQException
	{
		StringBuilder keys = new StringBuilder();
		String fileLibrary;

		Connection connection;
		if (!globalTable)
		{
			fileLibrary = root + getUnitId().trim();
			connection = sessionPool.getConnection(sessionPool.getUserId());
		}
		else
		{
			// Pass global library connection
			fileLibrary = EquationCommonContext.getConfigProperty("eq.GlobalLibraryName").trim();
			connection = ConnectionAccess.getGlobalConnectionPool().getConnection();
		}

		String sqlString = "SELECT APKEYF FROM LFFD WHERE APLIB='" + fileLibrary + "' AND APFILE='" + tableId.trim()
						+ "' ORDER BY APKEYN";
		Statement statement = null;
		Statement queryStatement = null;
		ResultSet resultSet = null;
		try
		{
			// First up need to get the info for whether the key is unique
			statement = connection.createStatement();

			// Files do not need to have unique keys or be logical files
			String command = "DSPFD FILE(" + fileLibrary + "/" + tableId.trim()
							+ ") TYPE(*ACCPTH) OUTPUT(*OUTFILE) OUTFILE(QTEMP/LFFD)";
			String query = SQLToolbox.getQcmdexcString(command);
			statement.execute(query);

			// Now do the query
			queryStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			resultSet = queryStatement.executeQuery(sqlString);
			while (resultSet.next())
			{
				keys.append(resultSet.getString(1).trim() + ":");
			}
		}
		catch (SQLException e)
		{
			throw new EQException(e);
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(queryStatement);
			SQLToolbox.close(statement);
			if (!globalTable)
			{
				sessionPool.returnConnectionToPool(connection);
			}
			else
			{
				SQLToolbox.close(connection);
			}
		}
		return keys.substring(0, keys.length() - 1);
	}

	/**
	 * Return a string delimited by ":" of the unique key fields for a given logical
	 * 
	 * @param root
	 *            - KFIL,KINP,KWRK
	 * @param tableId
	 *            - the table name
	 * 
	 * @return a string delimited by ":" of the unique key fields
	 * 
	 * @throws EQException
	 */
	public String getUniqueKeys(String root, String tableId) throws EQException
	{
		StringBuilder keys = new StringBuilder();
		String sqlString = "SELECT APKEYF FROM LFFD WHERE APLIB='" + root + getUnitId().trim() + "' AND APFILE='" + tableId.trim()
						+ "' ORDER BY APKEYN";
		Connection connection = sessionPool.getConnection(poolUser.getUserId());
		Statement statement = null;
		Statement queryStatement = null;
		ResultSet resultSet = null;
		try
		{
			// First up need to get the info for whether the key is unique
			statement = connection.createStatement();

			// Files do not need to have unique keys or be logical files
			String command = "DSPFD FILE(" + root + getUnitId().trim() + "/" + tableId.trim()
							+ ") TYPE(*ACCPTH) OUTPUT(*OUTFILE) OUTFILE(QTEMP/LFFD)";
			String query = SQLToolbox.getQcmdexcString(command);
			statement.execute(query);

			// Now do the query
			queryStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			resultSet = queryStatement.executeQuery(sqlString);
			while (resultSet.next())
			{
				keys.append(resultSet.getString(1).trim() + ":");
			}
		}
		catch (SQLException e)
		{
			throw new EQException(e.getMessage(), e);
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(queryStatement);
			SQLToolbox.close(statement);
			sessionPool.returnConnectionToPool(connection);
		}
		return keys.substring(0, keys.length() - 1);
	}

	/**
	 * Return a string for the physical of a given logical
	 * 
	 * @param root
	 *            - KFIL,KINP,KWRK
	 * @param indexId
	 *            - the index name
	 * @param indexFileOnly
	 *            - if this is true, only retrieves the physical if it is an index file. Otherwise, always retrieves the physical
	 * 
	 * @return a string for the physical of a given logical
	 */
	public String getTableForIndex(String root, String indexId, boolean indexFileOnly) throws EQException
	{
		String tableId = "";
		String sqlString = "SELECT DISTINCT APBOF FROM LFFD WHERE APLIB='" + root + getUnitId().trim() + "' AND APFILE='"
						+ indexId.trim() + "'";
		Connection connection = sessionPool.getConnection(sessionPool.getUserId());
		Statement statement = null;
		Statement queryStatement = null;
		ResultSet resultSet = null;

		try
		{
			// Only retrieve the table for index file (created via SQL Statement CREATE INDEX)
			if (indexFileOnly && !EquationControl.isIndexFile(connection, root + getUnitId().trim(), indexId.trim()))
			{
				return indexId;
			}

			// First up need to get the info for whether the key is unique
			statement = connection.createStatement();

			// Files do not need to have unique keys or be logical files
			String command = "DSPFD FILE(" + root + getUnitId().trim() + "/" + indexId.trim()
							+ ") TYPE(*ACCPTH) OUTPUT(*OUTFILE) OUTFILE(QTEMP/LFFD)";
			String query = SQLToolbox.getQcmdexcString(command);
			statement.execute(query);

			// Now do the query
			queryStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			resultSet = queryStatement.executeQuery(sqlString);
			if (resultSet.next())
			{
				tableId = resultSet.getString(1).trim();
			}
		}
		catch (SQLException e)
		{
			throw new EQException(e);
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(queryStatement);
			SQLToolbox.close(statement);
			sessionPool.returnConnectionToPool(connection);
		}
		if (tableId.equals(""))
		{
			tableId = indexId.trim();
		}
		return tableId;
	}
	/**
	 * Return the option description for the option id
	 * 
	 * @param optionId
	 *            - the option Id
	 * 
	 * @return the option description for the option id
	 */
	public String getOptionDescription(String optionId) throws EQException
	{
		String optionDescription = "";
		GBRecordDataModel gbRecord = getRecords().getGBRecord(optionId);
		if (gbRecord != null)
		{
			optionDescription = gbRecord.getProgramTitle();
		}
		return optionDescription;
	}

	/**
	 * Finds out if the option Id is a new or an old style function
	 * 
	 * @param optionId
	 *            - the option Id
	 * 
	 * @return true if the option Id is a legacy function
	 */
	public boolean isLegacyOption(String optionId) throws EQException
	{
		boolean isLegacyOption = true;
		GBRecordDataModel gbRecord = getRecords().getGBRecord(optionId);
		if (gbRecord != null)
		{
			isLegacyOption = !Toolbox.isEqServiceGBRecord(gbRecord.getProgramName());
		}
		return isLegacyOption;
	}

	/**
	 * Finds out if the option Id is custom GP option or not.
	 * 
	 * @param optionId
	 *            - the option Id
	 * 
	 * @return true if the option Id is a legacy function
	 */
	public boolean isCustomOption(String optionId) throws EQException
	{
		boolean isGPOption = false;
		GBRecordDataModel gbRecord = records.getGBRecord(optionId);
		if (gbRecord != null)
		{
			isGPOption = gbRecord.getApplication().equals("GP");
		}
		return isGPOption;
	}

	/**
	 * Determine whether an enhancement is installed to the unit or not
	 * 
	 * @return true if the enhancement is installed
	 * @equation.external
	 */
	public boolean isEnhancementInstalled(String enh) throws EQException
	{
		Boolean isEnhancementInstalled = enhancementsInstalled.get(enh);

		// not yet loaded
		if (isEnhancementInstalled == null)
		{
			EquationStandardSession session = sessionPool.getEquationStandardSession();
			try
			{
				if (unverifiedEnhancementsInstalled == null || unverifiedEnhancementsInstalled.contains(enh))
				{
					// enhancement might be in CHPF, now we must verify with UTR00R that it is valid
					isEnhancementInstalled = session.callUTR00R(enh);
				}
				else
				{
					// enhancement is definitely not in CHPF, this enhancement is not installed (no need to call UTR00R)
					isEnhancementInstalled = Boolean.FALSE;
				}
				enhancementsInstalled.put(enh, isEnhancementInstalled);
			}
			finally
			{
				sessionPool.returnEquationStandardSession(session);
			}
		}
		return isEnhancementInstalled;
	}
	/**
	 * Determine whether an enhancement is installed to the unit or not
	 * 
	 * @return true if the enhancement is installed
	 * @equation.external
	 */
	public boolean isEnhancementInstalled(String enh, EquationStandardSession session) throws EQException
	{
		Boolean isEnhancementInstalled = enhancementsInstalled.get(enh);

		// not yet loaded
		if (isEnhancementInstalled == null)
		{
			if (unverifiedEnhancementsInstalled == null || unverifiedEnhancementsInstalled.contains(enh))
			{
				// enhancement might be in CHPF, now we must verify with UTR00R that it is valid
				isEnhancementInstalled = session.callUTR00R(enh);
			}
			else
			{
				// enhancement is definitely not in CHPF, this enhancement is not installed (no need to call UTR00R)
				isEnhancementInstalled = Boolean.FALSE;
			}
			enhancementsInstalled.put(enh, isEnhancementInstalled);
		}
		return isEnhancementInstalled;
	}
	/**
	 * Determine which of the given enhancements are installed in the unit. This method is optimised for cases where multiple
	 * enhancements need to be checked and verifying them one by one could be inefficient.
	 * 
	 * @param enhancements
	 *            A set of enhancements to check if installed on this unit.
	 * @return A Set containing the enhancements which are installed on the unit that were included in the set of enhancements to
	 *         check
	 */
	public Set<String> getInstalledEnhancements(Collection<String> enhancements) throws EQException
	{
		// store all installed enhancements in this set
		final Set<String> installedEnhancements = new HashSet<String>();

		// prepare a single session only if needed
		EquationStandardSession session = null;
		try
		{
			for (String enh : enhancements)
			{
				Boolean isEnhancementInstalled = enhancementsInstalled.get(enh);

				// not yet loaded
				if (isEnhancementInstalled == null)
				{
					if (unverifiedEnhancementsInstalled == null || unverifiedEnhancementsInstalled.contains(enh))
					{
						if (session == null)
						{
							// retrieve a session only if necessary!
							session = sessionPool.getEquationStandardSession();
						}

						// enhancement might be in CHPF, now we must verify with UTR00R that it is valid
						isEnhancementInstalled = session.callUTR00R(enh);
					}
					else
					{
						// enhancement is definitely not in CHPF, this enhancement is not installed ( no need to call UTR00R)
						isEnhancementInstalled = Boolean.FALSE;
					}

					enhancementsInstalled.put(enh, isEnhancementInstalled);
				}

				if (isEnhancementInstalled)
				{
					// this enhancement is installed!
					installedEnhancements.add(enh);
				}
			}
		}
		finally
		{
			if (session != null)
			{
				// return session if we used one
				sessionPool.returnEquationStandardSession(session);
			}
		}

		// return the set of enhancements that are installed in the unit
		return installedEnhancements;
	}

	/**
	 * Returns the meta data of a PV program
	 * 
	 * @param pvName
	 *            - PV program name
	 * 
	 * @return the PV meta data
	 * 
	 * @throws EQException
	 * @equation.external
	 */
	public EquationPVMetaData getPVMetaData(String pvName) throws EQException
	{
		EquationPVMetaData pvMetaData = null;
		if (EquationPVMetaData.GLOBAL_PVS_IN_RESOURCES.containsKey(pvName))
		{
			pvMetaData = EquationPVMetaData.GLOBAL_PVS_IN_RESOURCES.get(pvName);
		}
		else if (EquationPVMetaData.UNIT_PVS_IN_RESOURCES.containsKey(pvName))
		{
			pvMetaData = EquationPVMetaData.UNIT_PVS_IN_RESOURCES.get(pvName);
		}
		else
		{
			InputStream s = Thread.currentThread().getContextClassLoader().getResourceAsStream("pvmetadata/" + pvName + ".xml");
			InputStream globalLibs = Thread.currentThread().getContextClassLoader().getResourceAsStream(
							"pvmetadata/globallib/" + pvName + ".xml");
			if (s != null)
			{
				pvMetaData = (EquationPVMetaData) EquationUnit.getBean(s, EquationPVMetaData.class);
				EquationPVMetaData.GLOBAL_PVS_IN_RESOURCES.put(pvName, pvMetaData);
			}
			else if (globalLibs != null)
			{
				pvMetaData = (EquationPVMetaData) EquationUnit.getBean(globalLibs, EquationPVMetaData.class);
				EquationPVMetaData.UNIT_PVS_IN_RESOURCES.put(pvName, pvMetaData);
			}
			else
			// If its not in resources then look for it on the I-Series
			{
				// check if it is in the cache
				pvMetaData = pvMetaDataCacheHandler.getPvMetaData(pvName);
				if (pvMetaData == null)
				{
					EquationStandardSession session = sessionPool.getEquationStandardSession();
					try
					{
						pvMetaData = getPVDefinition(session, pvName, session.getUnit().isDevelopmentUnit());
						if (pvMetaData == null)
						{
							pvMetaData = session.callUTW19R(pvName);
						}
					}
					finally
					{
						sessionPool.returnEquationStandardSession(session);
					}
				}
			}
		}

		// put into the cache
		if (pvMetaData != null)
		{
			pvMetaDataCacheHandler.addPvMetaData(pvName, pvMetaData);
		}
		return pvMetaData;
	}

	/**
	 * Attempts to loads a PV definition from the GAX
	 * <p>
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param optionId
	 *            - the PV option id
	 * @param forceCheck
	 *            - true if it should always try to load the latest from database
	 * 
	 * @return EquationPVMetaData
	 * 
	 * @throws EQException
	 */
	private synchronized EquationPVMetaData getPVDefinition(EquationStandardSession session, String optionId, boolean forceCheck)
					throws EQException
	{
		String unitId = session.getUnitId();
		EquationPVMetaData pvBean = null;
		try
		{
			// Determine whether to reload from database or not
			boolean reload = false;

			// Get the existing timestamp from the cache (will be null if no entry)
			String timestamp = pvTimestampCache.get(unitId + optionId);

			// GAX record of the PV
			GAXRecordDataModel gaxRecord = null;

			// Determine whether record should be loaded or not
			if (forceCheck || timestamp == null)
			{
				// Check if there is a more up to date definition in the database
				gaxRecord = new GAXRecordDataModel(GAXRecordDataModel.PV_CODE, optionId);
				IGAXRecordDao dao = daoFactory.getGAXDao(session, gaxRecord);
				gaxRecord = dao.findWithLaterTimestamp(GAXRecordDataModel.PV_CODE, optionId, timestamp);
				reload = gaxRecord != null;
			}

			if (reload)
			{
				// Convert the XML to a bean:
				EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
				pvBean = (EquationPVMetaData) beanFactory.deserialiseXMLAsBean(gaxRecord.getLayout(), EquationPVMetaData.class);

				// Update the caches
				pvTimestampCache.put(unitId + optionId, gaxRecord.getTimestamp());
				pvMetaDataCacheHandler.addPvMetaData(optionId, pvBean);

				// If the definition XML has changed, create a new class loader
				// to pick up any changed Java user exit classes. Note that we don't
				// need to do this when first accessing the definition XML
				if (timestamp != null)
				{
					LOG.info("readPV - PV has changed. Creating new ClassLoader");
					session.getUnit().createNewFunctionUserExitClassLoader();
				}
			}

			// retrieve from cache
			else
			{
				pvBean = pvMetaDataCacheHandler.getPvMetaData(optionId);
			}
		}
		catch (Exception e)
		{
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		return pvBean;
	}

	/**
	 * This method deserialises a bean from the supplied File object.
	 * 
	 * @param in
	 *            An InputStream containing the XML to deserialise. The file contents must be in UTF-8.
	 * @param clazz
	 *            The expected bean class
	 * @return The bean object
	 */
	public static Object getBean(InputStream in, Class<?> clazz)
	{
		Object result = null;
		Reader reader;
		try
		{
			reader = new BufferedReader(new InputStreamReader(in));
			EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
			result = beanFactory.deserialiseXMLAsBean(reader, clazz);
		}
		catch (Exception e)
		{
			LOG.error("getBean", e);
			throw new EQRuntimeException(e);
		}
		return result;
	}

	/**
	 * This method deserialises a bean from the supplied File object.
	 * 
	 * @param file
	 *            A File containing the XML to deserialise. The file contents must be in UTF-8.
	 * @param clazz
	 *            The expected bean class
	 * @return The bean object
	 */
	public static Object getBean(File file, Class<?> clazz)
	{
		Object result = null;
		Reader reader;
		try
		{
			InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), UTF8_CHARSET);
			reader = new BufferedReader(inputStreamReader);

			EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
			result = beanFactory.deserialiseXMLAsBean(reader, clazz);
		}
		catch (Exception e)
		{
			LOG.error("getBean", e);
			throw new EQRuntimeException(e);
		}
		return result;
	}

	/**
	 * Returns an Equation widget
	 * 
	 * @param widgetName
	 *            Widget name
	 * 
	 * @return the Equation widget
	 * 
	 * @throws EQException
	 */
	public EquationWidget getWidget(String widgetName) throws EQException
	{
		// FIXME: Refactor to use ACE DAO!!
		// check if it is in the cache
		EquationWidget widget = widgets.get(widgetName);
		if (widget == null)
		{
			Connection connection = null;
			ResultSet resultSet = null;
			PreparedStatement statement = null;
			try
			{
				// Get a connection from the pool...
				connection = sessionPool.getConnection(sessionPool.getUserId());

				String sqlString = "SELECT ACEDES, ACESC1, ACESC2, ACESC3, ACESC4, ACESC5, ACESC6, ACESC7, ACESC8, ACESC9, ACESC10, ACESC11, ACESC12, ACESC13, ACESC14 FROM KFIL"
								+ unitId + "/ACEPF WHERE ACEWID = ? ";
				statement = connection.prepareStatement(sqlString, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
				statement.setString(1, widgetName);

				resultSet = statement.executeQuery();

				if (resultSet.next())
				{
					String widgetDesc = resultSet.getString(1);
					String script1 = resultSet.getString(2);
					String script2 = resultSet.getString(3);
					String script3 = resultSet.getString(4);
					String script4 = resultSet.getString(5);
					String script5 = resultSet.getString(6);
					String script6 = resultSet.getString(7);
					String script7 = resultSet.getString(8);
					String script8 = resultSet.getString(9);
					String script9 = resultSet.getString(10);
					String script10 = resultSet.getString(11);
					String script11 = resultSet.getString(12);
					String script12 = resultSet.getString(13);
					String script13 = resultSet.getString(14);
					String script14 = resultSet.getString(15);
					widget = new EquationWidget(widgetName, widgetDesc, script1, script2, script3, script4, script5, script6,
									script7, script8, script9, script10, script11, script12, script13, script14);
					widgets.put(widgetName, widget);
				}
			}
			catch (SQLException e)
			{
				LOG.error(e);
				throw new EQException("EquationStandardSession: getWidget Failed: " + Toolbox.getExceptionMessage(e), e);
			}
			finally
			{
				SQLToolbox.close(resultSet);
				SQLToolbox.close(statement);
				sessionPool.returnConnectionToPool(connection);
			}
		}
		return widget;
	}

	/**
	 * Generate the language property of the unit
	 * 
	 * @throws EQException
	 */
	public void generateLanguageProperty() throws EQException
	{
		EquationStandardSession session = sessionPool.getEquationStandardSession();
		session.callUTW01R();
		sessionPool.returnEquationStandardSession(session);
	}

	/**
	 * @return the latest FX release installed in the system
	 */
	public String getFXVersion() throws EQException
	{
		Statement statement = null;
		ResultSet resultSet = null;

		// Get the FX version if we haven't already
		if (fxVersion == null)
		{
			String sqlString = "SELECT CAST(MAX(SUBSTR(CHENM,3,3)) AS CHAR(3) CCSID 37)  FROM CHPF WHERE CHENM LIKE 'FX%'";
			Connection connection = sessionPool.getConnection(sessionPool.getUserId());
			try
			{
				// Now do the query
				statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
				resultSet = statement.executeQuery(sqlString);
				if (resultSet.next())
				{
					// changed for EQ342 compatibility
					fxVersion = "FX" + resultSet.getString(1);
				}
			}
			catch (SQLException e)
			{
				throw new EQException(e);
			}
			finally
			{
				SQLToolbox.close(resultSet);
				SQLToolbox.close(statement);
				sessionPool.returnConnectionToPool(connection);
			}
		}
		return fxVersion;
	}
	/**
	 * @return the latest FX release installed in the system (based on existing session)
	 */
	public String getFXVersion(EquationStandardSession session) throws EQException
	{
		Statement statement = null;
		ResultSet resultSet = null;

		// Get the FX version if we haven't already
		if (fxVersion == null)
		{
			String sqlString = "SELECT CAST(MAX(SUBSTR(CHENM,3,3)) AS CHAR(3) CCSID 37)  FROM CHPF WHERE CHENM LIKE 'FX%'";
			Connection connection = session.getConnection();
			try
			{
				// Now do the query
				statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
				resultSet = statement.executeQuery(sqlString);
				if (resultSet.next())
				{
					// changed for EQ342 compatibility
					fxVersion = "FX" + resultSet.getString(1);
				}
			}
			catch (SQLException e)
			{
				throw new EQException(e);
			}
			finally
			{
				SQLToolbox.close(resultSet);
				SQLToolbox.close(statement);
			}
		}
		return fxVersion;
	}

	/**
	 * Return the list of users who are both defined in and authorised to this unit.
	 * <p>
	 * The processing takes into account whether the EQSEC or KAPSEC file should be used
	 * 
	 * @return An Map of User IDs to OCRecordDataModel objects. Note that the OCRecordDataModel objects returned in the Map are
	 *         <strong>not</strong> fully populated for performance reasons. Only the Language Mnemonic, Branch mnemonic and branch
	 *         number are populated. Also, in the OCRecordDataModel, the user id is truncated to four characters. Use the map key to
	 *         obtain the full user name.
	 * 
	 * @throws EQException
	 */
	public Map<String, OCRecordDataModel> getAuthorisedUsers() throws EQException
	{
		Map<String, OCRecordDataModel> result = new LinkedHashMap<String, OCRecordDataModel>();
		Connection connection = null;
		PreparedStatement newsqlStatement = null;
		ResultSet rs = null;
		try
		{
			// Get a connection
			connection = sessionPool.getConnection(sessionPool.getUserId());

			StringBuilder sql = new StringBuilder();
			if (system.isEQSECInBase())
			{
				sql.append("SELECT SECUSER, OCLNM, OCBRNM, OCBBN, OCCOA, OCUNAM FROM ");
				sql.append(getKFILLibrary());
				sql.append("/OCPF INNER JOIN ");
				sql.append(getSystem().getBaseLibrary());
				sql.append("/EQSEC ON LEFT(SECUSER,4) = OCUSID WHERE SECUNIT = ?");

				if (LOG.isInfoEnabled())
				{
					LOG.info("getAuthorisedUsers SQL: [" + sql.toString() + "]");
				}
				newsqlStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY,
								ResultSet.CONCUR_READ_ONLY);
				// Only parameter is the unit
				newsqlStatement.setString(1, unitId);

			}
			else
			{
				sql.append("SELECT SECUSR, OCLNM, OCBRNM, OCBBN, OCCOA, OCUNAM FROM ");
				sql.append(getKFILLibrary());
				sql.append("/OCPF INNER JOIN ");
				sql.append(getSystem().getBaseLibrary());
				sql
								.append("/KAPSEC ON LEFT(SECUSR,4) = OCUSID AND (SECUN1 = ? OR SECUN2 =? OR SECUN3 = ? OR SECUN4 = ? OR SECUN5 = ? OR SECUN6 = ? OR SECUN7 = ? OR SECUN8 = ? OR SECUN9 = ? OR SECUN10 = ?)");

				if (LOG.isInfoEnabled())
				{
					LOG.info("getAuthorisedUsers SQL: [" + sql.toString() + "]");
				}
				newsqlStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY,
								ResultSet.CONCUR_READ_ONLY);
				// Set up all 10 unit mnemonic parameters
				for (int index = 1; index < 11; index++)
				{
					newsqlStatement.setString(index, unitId);
				}
			}

			rs = newsqlStatement.executeQuery();
			while (rs.next())
			{
				String userid = rs.getString(1).trim();
				OCRecordDataModel ocRecord = new OCRecordDataModel(userid);
				ocRecord.setLanguage(rs.getString(2).trim());
				ocRecord.setBranch(rs.getString(3).trim());
				ocRecord.setBranchNo(rs.getString(4).trim());
				ocRecord.setUserClass(rs.getString(5).trim());
				ocRecord.setUserName(rs.getString(6).trim());
				result.put(userid, ocRecord);
			}
		}
		catch (SQLException sqle)
		{
			throw new EQException(sqle);
		}
		finally
		{
			SQLToolbox.close(rs);
			SQLToolbox.close(newsqlStatement);
			sessionPool.returnConnectionToPool(connection);
		}
		if (LOG.isDebugEnabled())
		{
			LOG.debug("EquationUnit [ " + unitId + "]: getAuthorisedUsers() - returning [" + result.toString() + "]");
		}
		return result;
	}

	/**
	 * Return the database records
	 * 
	 * @return the database records
	 */
	public EquationRecords getRecords()
	{
		return records;
	}

	/**
	 * Determine whether the unit has Global Processing Framework - K541 installed
	 * 
	 * @return true if the unit has the Global Processing enhancement installed, otherwise false
	 * 
	 * @throws EQException
	 *             if there is an error communicating with the unit
	 */
	public boolean hasGlobalProcessing() throws EQException
	{
		return isEnhancementInstalled(Enhancement.K541);
	}

	/**
	 * Determine whether the unit has Global Processing Compatibility - K544 installed
	 * 
	 * @return true if the unit has the Global Processing compatibility enhancement installed, otherwise false
	 * 
	 * @throws EQException
	 *             if there is an error communicating with the unit
	 */
	public boolean hasGlobalProcessingCompatibility() throws EQException
	{
		return isEnhancementInstalled(Enhancement.K544);
	}
	/**
	 * Determine whether the unit has Maker-Checker Enhancement - K552 installed
	 * 
	 * @return true if the unit has the has Maker-Checker Enhancement installed, otherwise false
	 * 
	 * @throws EQException
	 *             if there is an error communicating with the unit
	 */
	public boolean hasMakerCheckerEnhancement() throws EQException
	{
		return isEnhancementInstalled(Enhancement.K552);
	}
	/**
	 * Determine whether the unit has Maker-Checker Enhancement - K552 installed
	 * 
	 * @return true if the unit has the has Maker-Checker Enhancement installed, otherwise false
	 * 
	 * @throws EQException
	 *             if there is an error communicating with the unit
	 */
	public boolean hasMakerCheckerEnhancement(EquationStandardSession session) throws EQException
	{
		return isEnhancementInstalled(Enhancement.K552, session);
	}

	/**
	 * Return the version of the unit, it will be either EQ3 or EQ4
	 * 
	 * @return the version as <code>VERSION_EQ3</code> or <code>VERSION_EQ3</code>
	 */
	public String getUnitVersion()
	{
		return this.unitVersion;
	}

	/**
	 * This method loads the enhancement mnemonics found in the CHPF file into the unverifiedEnhancementsInstalled internal set.
	 * <p>
	 * NOTE: This method is used for performance purposes only. CHPF enhancements must always be verified through the UTR00R
	 * program. This method is included only to store the CHPF enhancements so that a call to UTR00R can be skipped if we know that
	 * the mnemonic doesn't even exist in CHFP.
	 */
	private void loadUnverifiedEnhancementMnemonics(EquationStandardSession session) throws EQException
	{
		Statement statement = null;
		ResultSet resultSet = null;

		final String sqlString = "SELECT DISTINCT CHENM FROM KFIL" + unitId + "/CHPF";
		Connection connection = session.getConnection();
		final Set<String> mnemonics = new HashSet<String>();
		try
		{
			// Now do the query
			statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(sqlString);
			while (resultSet.next())
			{
				// add to the distinct set of 'unverified' enhancements.
				final String mnemonic = Toolbox.convertAS400TextIntoCCSID(resultSet.getBytes(1), 10, 65535).trim();
				mnemonics.add(mnemonic);
			}

			// update the 'unverifiedEnhancementsInstalled' only if entire list was successfully processed
			this.unverifiedEnhancementsInstalled = mnemonics;
		}
		catch (SQLException e)
		{
			throw new EQException(e);
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(statement);
		}
	}

	/**
	 * Return the XA Data Source
	 * 
	 * @return the XA Data Source
	 */
	public DataSource getXaDataSource()
	{
		return xaDataSource;
	}

	/**
	 * Set the unit version based on the GPX values on the Equation configuration properties bean
	 */
	private void setUnitVersion() throws EQException
	{
		// set a default value, which we will overwrite if GP is installed.
		this.unitVersion = VERSION_EQ4;

		EquationConfigurationPropertiesBean props = EquationConfigurationPropertiesBean.getInstance();
		if ((hasGlobalProcessing() && props.isGlobalProcessingGoodToGo())
						|| (hasGlobalProcessingCompatibility() && props.isGlobalProcessingGoodToGo()))
		{
			this.unitVersion = props.getGlobalUnitVersion(this.system.getSystemId(), this.unitId);
		}
		else
		{
			this.unitVersion = VERSION_EQ4;
		}
	}

	/**
	 * Returns a {@link UnitStatusHandler} with data refreshed at the time of the call.
	 * 
	 * @return A UnitStatusHandler with up-to-date status information.
	 * @equation.external
	 */
	public UnitStatusHandler getUnitStatus()
	{
		return new UnitStatusHandler(unitId, system);
	}

	/**
	 * Determine if HBXPF file exists in the Unit
	 * 
	 * @throws EQException
	 */
	private void setHBXPFInstalled(AS400 as400) throws EQException
	{
		try
		{
			CommandCall commandCall = new CommandCall(as400);
			if (commandCall.run("CHKOBJ " + getKFILLibrary() + "/HBXPF *FILE"))
			{
				isHBXPFInstalled = true;
			}
			else
			{
				isHBXPFInstalled = false;
			}
		}
		catch (Exception e)
		{
			isHBXPFInstalled = false;
			throw new EQException("EqUnit: isHBXPFInstalled: There was a problem doing CHKOBJ on HBXPF.", e);
		}
	}

	/**
	 * @return whether HBXPF is installed in the unit
	 */
	public boolean isHBXPFInstalled()
	{
		return isHBXPFInstalled;
	}

	/**
	 * Determine if GWYPF file exists in the unit
	 * 
	 * @param as400
	 *            - the AS400 object
	 * 
	 * @throws EQException
	 */
	private void setGYWPFInstalled(AS400 as400) throws EQException
	{
		isGYWPFInstalled = isFileExists(as400, getKINPLibrary(), GYWRecordDataModel.RECORD_NAME);
		LOG.info("File GYWPF exist = " + isGYWPFInstalled);
	}

	/**
	 * Return true if GYWPF is in the unit
	 * 
	 * @return true if GYWPF is in the unit
	 */
	public boolean isGYWPFInstalled()
	{
		return isGYWPFInstalled;
	}

	/**
	 * Determine if IPVPF file exists in the unit
	 * 
	 * @param as400
	 *            - the AS400 object
	 * 
	 * @throws EQException
	 */
	private void setIPVPFInstalled(AS400 as400) throws EQException
	{
		isIPVPFInstalled = isFileExists(as400, getKFILLibrary(), IPVRecordDataModel.RECORD_NAME);
		LOG.info("File IPVPF exist = " + isIPVPFInstalled);
	}

	/**
	 * Return true if IPVPF is in the unit
	 * 
	 * @return true if IPVPF is in the unit
	 */
	public boolean isIPVPFInstalled()
	{
		return isIPVPFInstalled;
	}

	/**
	 * Determine if file exists or not
	 * 
	 * @param as400
	 *            - the AS400 object
	 * @param library
	 *            - the library where file is located
	 * @param fileName
	 *            - the file name
	 * 
	 * @return true if file name exists
	 * 
	 * @throws EQException
	 */
	private boolean isFileExists(AS400 as400, String library, String fileName) throws EQException
	{
		try
		{
			CommandCall commandCall = new CommandCall(as400);
			if (commandCall.run("CHKOBJ " + library.trim() + "/" + fileName + " *FILE"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			throw new EQException("EqUnit: isFileExists: There was a problem doing CHKOBJ on " + fileName, e);
		}
	}

	/**
	 * Determine if file exists or not
	 * 
	 * @param as400
	 *            - the AS400 object
	 * @param library
	 *            - the library where file is located
	 * @param fileName
	 *            - the file name
	 * 
	 * @return true if file name exists
	 * 
	 * @throws EQException
	 */
	public boolean isFileExists(String library, String fileName) throws EQException
	{
		AS400 eqAS400 = null;
		try
		{
			eqAS400 = system.getAS400();
			return isFileExists(eqAS400, library, fileName);
		}
		catch (EQException e)
		{
			throw e;
		}
		finally
		{
			// Return the AS400 to the pool
			if (eqAS400 != null)
			{
				system.returnAS400(eqAS400);
			}
		}
	}
	/**
	 * Determine if WEYPF file contains WEYBDTA in the Unit
	 * 
	 * @throws EQException
	 */
	private void setWEYPFBdtaInstalled(EquationStandardSession session) throws EQException

	{
		try
		{
			if (session != null)
			{
				isWEYPFBdtaInstalled = session.isWEYPFBdtaInstalled();
			}

		}
		catch (SQLException e)
		{
			isWEYPFBdtaInstalled = false;
			throw new EQException("EqUnit: setWEYPFBdtaInstalled: There was a problem doing Toolbox.isColumnInTable on WEYPF.", e);
		}
	}
	/**
	 * @return whether WEYPF has field WEYBDTA installed in the unit
	 */
	public boolean isWEYPFBdtaInstalled()
	{
		return isWEYPFBdtaInstalled;
	}
	/**
	 * @return whether this a development unit
	 */
	public boolean isDevelopmentUnit()
	{
		return developmentUnit;
	}
	/**
	 * @return is Misys Mode
	 * @throws EQException
	 */
	public boolean isMisysMode() throws EQException
	{
		return isEnhancementInstalled(Enhancement.K535);
	}
	/**
	 * Retrieve 10 character iSeries user id
	 * 
	 * @param userId
	 *            - 4 character Equation user id
	 * 
	 * @return the 10 character iSeries user id
	 */
	public String rtvFullUserId(String userId) throws EQException
	{
		String result = null;
		String user4 = Toolbox.trim(userId, 4).toUpperCase();

		Connection connection = null;
		PreparedStatement newsqlStatement = null;
		ResultSet rs = null;
		try
		{
			// Get a connection
			connection = sessionPool.getConnection(sessionPool.getUserId());

			StringBuilder sql = new StringBuilder();
			if (system.isEQSECInBase())
			{
				sql.append("SELECT SECUSER FROM ");
				sql.append(getSystem().getBaseLibrary());
				sql.append("/EQSEC WHERE LEFT(SECUSER,4) = ?");
				newsqlStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY,
								ResultSet.CONCUR_READ_ONLY);
				newsqlStatement.setString(1, user4);
			}
			else
			{
				sql.append("SELECT SECUSR FROM ");
				sql.append(getSystem().getBaseLibrary());
				sql
								.append("/KAPSEC WHERE LEFT(SECUSR,4) = ? AND (SECUN1 = ? OR SECUN2 =? OR SECUN3 = ? OR SECUN4 = ? OR SECUN5 = ? OR SECUN6 = ? OR SECUN7 = ? OR SECUN8 = ? OR SECUN9 = ? OR SECUN10 = ?)");
				newsqlStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY,
								ResultSet.CONCUR_READ_ONLY);

				// Userid
				newsqlStatement.setString(1, user4);
				// Set up all 10 unit mnemonic parameters
				for (int index = 2; index < 12; index++)
				{
					newsqlStatement.setString(index, unitId);
				}
			}

			rs = newsqlStatement.executeQuery();
			if (rs.next())
			{
				result = rs.getString(1).trim();
			}
		}
		catch (SQLException sqle)
		{
			throw new EQException(sqle);
		}
		finally
		{
			SQLToolbox.close(rs);
			SQLToolbox.close(newsqlStatement);
			sessionPool.returnConnectionToPool(connection);
		}
		if (LOG.isDebugEnabled())
		{
			LOG.debug("EquationUnit [ " + unitId + "]: rtvFullUserId() - returning [" + result.toString() + "]");
		}

		return result;
	}

	/**
	 * Check for change of date, and auto-refresh cache if needed
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * 
	 * @return true if processing date has changed
	 */
	public boolean isProcessingDateChange(EquationStandardSession session) throws EQException
	{
		// Retrieve the unit's DASYSCTL
		byte[] data = session.callUTW52R("DASYSCTL");
		EquationDataStructureData dasysctl = new EquationDataStructureData(new EqDS_DSSYSE());
		dasysctl.setBytes(data);

		// Retrieve the processing date
		String newProcessingDate = dasysctl.getFieldValue(EqDS_DSSYSE.ZLDATE);
		return !newProcessingDate.equals(processingDate);
	}

	/**
	 * Retrieve the library where the KSM message file is located for the given language
	 * 
	 * @param language
	 *            - the language id
	 * 
	 * @return the library where the KSM message file is located
	 */
	public String getKsmMessageFilePath(String language) throws EQException
	{
		return ksmMessageFilePaths.get(language);
	}

	/**
	 * Retrieve the library where the KSM message file is located for the given language
	 * 
	 * @param language
	 *            - the language id
	 * 
	 * @return the library where the KSM message file is located
	 */
	public String addksmMessageFilePath(String language, String path) throws EQException
	{
		return ksmMessageFilePaths.put(language, path);
	}

	/**
	 * Retrieve EquationUser for the user identifier
	 * 
	 * @param userId
	 *            - the user identifier
	 * 
	 * @return the EquationUser for the user identifier
	 */
	public EquationUser getUserTemplate(String userId) throws EQException
	{
		return userTemplates.get(userId);
	}

	/**
	 * Add the EquationUser to user template collection. This is to enable quick processing of user when using pooled connections.
	 * 
	 * @param userId
	 *            - the user identifier
	 * @param user
	 *            - the Equation User
	 * 
	 */
	public void addEquationUserToUserTemplates(String userId, EquationUser user) throws EQException
	{
		userTemplates.put(userId, user);
	}

	/**
	 * Obtains a OCRecord for the supplied bankfusion user id
	 * 
	 * @param bankFusionUserId
	 * @return OCRecordDataModel
	 * @throws EQException
	 */
	public OCRecordDataModel getOCByBFUser(String bankFusionUserId) throws EQException
	{
		OCRecordDataModel result = null;

		EquationStandardSession session = null;
		try
		{
			session = sessionPool.getEquationStandardSession();
			OCRecordDataModel ocRecord = new OCRecordDataModel();
			IOCRecordDao dao = daoFactory.getOCDao(session, ocRecord);
			List<AbsRecord> results = dao.getRecordBy("OCBFUS = '" + bankFusionUserId + "'");
			if (!results.isEmpty())
			{
				// Successfully found BankFusion user id
				result = (OCRecordDataModel) results.get(0);
			}
		}
		finally
		{
			if (session != null)
			{
				try
				{
					sessionPool.returnEquationStandardSession(session);
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
	 * Gets the Alias on the data source and derives the user and password
	 * 
	 * @param dataSourceName
	 * @return OCRecordDataModel
	 * 
	 */
	public String[] getUserPoolCredentials(String dataSourceName)
	{
		String[] results = new String[2];

		CallbackHandler callbackHandler;
		if (LOG.isInfoEnabled())
		{
			LOG.info("Retrieving user pool alias: [" + dataSourceName + "]");
		}
		try
		{
			Map<String, String> map = new HashMap<String, String>();
			map.put(Constants.MAPPING_ALIAS, dataSourceName);

			callbackHandler = WSMappingCallbackHandlerFactory.getInstance().getCallbackHandler(map, null);
			LoginContext loginContext = new LoginContext("DefaultPrincipalMapping", callbackHandler);
			loginContext.login();
			Subject subject = loginContext.getSubject();
			Set<?> credentials = subject.getPrivateCredentials();
			PasswordCredential passwordCredential = (PasswordCredential) credentials.iterator().next();
			results[0] = passwordCredential.getUserName();
			results[1] = new String(passwordCredential.getPassword());

		}
		catch (Throwable e)
		{
			LOG.error("Cannot load user pool Alias - " + dataSourceName, e);
		}
		return results;
	}

	/**
	 * Determine if UXP enhancement is installed in the unit
	 * 
	 * @return true if UXP enhancement is installed in the unit
	 * 
	 * @throws EQException
	 */
	public boolean isUXPEnhancementInstalled() throws EQException
	{
		return isEnhancementInstalled(Enhancement.K557);
	}

}