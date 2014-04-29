package com.misys.equation.ui.context;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationLogin;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.SessionLimitConfiguration;
import com.misys.equation.common.dao.beans.OCRecordDataModel;
import com.misys.equation.common.globalprocessing.SystemStatusManager;
import com.misys.equation.common.globalprocessing.UnitStatus;
import com.misys.equation.common.internal.eapi.core.EQActionErrorException;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.context.BFEQCredentials;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.function.context.IUILayerCallback;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionHandlerTable;
import com.misys.equation.function.runtime.FunctionInfo;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.misys.equation.ui.beans.EqReferral;
import com.misys.equation.ui.beans.EquationUserExtension;
import com.misys.equation.ui.helpers.DesktopUnitStatusObserver;
import com.misys.equation.ui.helpers.EqHelpDetail;
import com.misys.equation.ui.helpers.EqInfo;
import com.misys.equation.ui.helpers.EqNavigator;
import com.misys.equation.ui.tools.EqDesktopToolBox;

public class EquationDesktopContext implements IUILayerCallback
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationDesktopContext.java 17823 2014-01-30 09:23:00Z perkinj1 $";
	private static EquationDesktopContext singletonDesktopContext;
	private static EquationFunctionContext singletonFunctionContext;
	private static EquationCommonContext singletonCommonContext;

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EquationDesktopContext.class);
	private static Hashtable<String, EqNavigator> equationNavigators = new Hashtable<String, EqNavigator>();
	private static Hashtable<String, EqInfo> equationInfos = new Hashtable<String, EqInfo>();
	private static Hashtable<String, EquationUserExtension> equationUserExtension = new Hashtable<String, EquationUserExtension>();

	// Session attribute
	// SESSION_LOGIN value must be the same as in EquationCommonContext LOGIN_KEY to enable logging
	public static final String SESSION_LOGIN = "equationLogin";
	public static final String SESSION_UNIT = "equationUnit";
	public static final String SESSION_USER = "equationUser";
	public static final String SESSION_NAVI = "eqNavigator";
	public static final String SESSION_INFO = "equationInfo";

	// Session details
	public static final String PARAM_WORKSTATION = "workstationId";
	public static final String PARAM_ROLLBACK = "rollback";
	public static final String PARAM_DEBUG = "debug";

	// this contains the list of logoff-ed sessions
	private static int LOGOFFSESSION_LIMIT = 50;
	private final List<String> logoffedSessions = new ArrayList<String>();

	// this contains the list of logged on workstation IDs
	private ArrayList<String> loggedonWorkStation = new ArrayList<String>();

	// contains the date and time of ESF referral for users
	private final Map<String, String> wepfEsfReferralTimestamps = new HashMap<String, String>();

	// contains the date and time of LRP tasks for users
	private final Map<String, String> lrpTaskTimestamps = new HashMap<String, String>();

	// HTTP sessions
	private final Map<String, HttpSession> httpSessions = new HashMap<String, HttpSession>();
	// menu statuses by session - this is to stop constant sending of the same status while the unit is unavailable is offline
	private final Map<String, String> menuStatus = new HashMap<String, String>();
	/**
	 * Constructor
	 */
	private EquationDesktopContext()
	{
		// ain't nobody creating one of me, except me!
	}

	/**
	 * Get the singleton context
	 */
	public static synchronized EquationDesktopContext getContext()
	{
		// Create our one and only instance of this class
		if (singletonDesktopContext == null)
		{
			singletonCommonContext = EquationCommonContext.getContext();
			singletonFunctionContext = EquationFunctionContext.getContext();
			singletonDesktopContext = new EquationDesktopContext();
			singletonFunctionContext.setUILayerCallback(singletonDesktopContext);
			// start the Desktop/UXP layer unit status listener
			singletonDesktopContext.startUnitStatusListener();
		}
		return singletonDesktopContext;
	}
	/*
	 * Start the unit status listener for the Desktop layer
	 */
	private void startUnitStatusListener()
	{
		String useDefaultUnitStatusListener = EquationCommonContext.getConfigProperty("eq.unitStatus.useDefaultUnitStatusListener")
						.trim();
		if (useDefaultUnitStatusListener.equals("true"))
		{
			String[] unitIds = EquationCommonContext.getConfigProperty("eq.units").split(",");
			String[] systemIds = EquationCommonContext.getConfigProperty("eq.systems").split(",");
			for (int i = 0; i < unitIds.length && !unitIds[i].equals(""); i++)
			{
				UnitStatus unitStatus = SystemStatusManager.getInstance().getUnitStatus(systemIds[i], unitIds[i]);

				unitStatus.addObserver(new DesktopUnitStatusObserver());
			}
		}
	}
	/**
	 * Get the sessionId from the request or EquationLogin (UXP)
	 */
	public static String getSessionId(HttpServletRequest request)
	{
		EquationLogin equationLogin = (EquationLogin) request.getSession().getAttribute("equationLogin");

		// get the session id from EquationLogin if using UXP
		if (equationLogin != null && equationLogin.getUserInterfaceType() == EquationCommonContext.UI_UXP)
		{
			return equationLogin.getSessionId();
		}
		// get the session id from the request if using Desktop
		return request.getSession().getId();
	}

	/**
	 * Get an Equation session
	 * 
	 * @param systemId
	 * @param unitId
	 * @param userId
	 * @param password
	 * @param request
	 * @param sessionType
	 * @return the ID of the session created
	 * @throws EQException
	 */
	public String getEqSession(String systemId, String unitId, String userId, String password, HttpServletRequest request,
					int sessionType) throws EQException
	{
		// initialise method variables
		HttpSession session = request.getSession();
		String sessionIdentifier = session.getId();
		String ipAddress = getIpv4Address(request);
		systemId = systemId.trim().toUpperCase();
		unitId = unitId.trim().toUpperCase();

		sessionIdentifier = getEqSession(systemId, unitId, userId, password, sessionIdentifier, ipAddress, sessionType, session);

		// setup the functionHandler
		if (sessionIdentifier != null && sessionType != EquationCommonContext.SESSION_CLASSIC_DESKTOP)
		{
			setupFunctionHandler(sessionIdentifier, request);
		}

		// classic login
		if (sessionIdentifier != null && sessionType == EquationCommonContext.SESSION_CLASSIC_DESKTOP)
		{
			setupClassicLogin(sessionIdentifier, request);
		}

		return sessionIdentifier;
	}

	/**
	 * Get an Equation session
	 * 
	 * @param systemId
	 * @param unitId
	 * @param userId
	 * @param password
	 * @param sessionIdentifier
	 * @param ipAddress
	 * @param sessionType
	 * @param session
	 * @return the ID of the session create
	 * @throws EQException
	 */
	private String getEqSession(String systemId, String unitId, String userId, String password, String sessionIdentifier,
					String ipAddress, int sessionType, HttpSession session) throws EQException
	{
		// remove from the list
		removeLogoffedSession(sessionIdentifier);

		// Only force user and password to uppercase for EQ authentication:
		if (singletonFunctionContext.isEquationAuthentication())
		{
			userId = userId.trim().toUpperCase();
			password = password.trim().toUpperCase();
		}

		// If we already have a valid session with the same login details why bother going any further?
		if (singletonCommonContext.sessionValid(sessionIdentifier) && (session.getAttribute(SESSION_LOGIN) != null))
		{
			// get the existing login details and see if changed
			EquationLogin existinglogin = singletonCommonContext.getEquationLogin(sessionIdentifier);
			if (existinglogin.getUserId().equals(userId) && existinglogin.getPassword().equals(password)
							&& existinglogin.getUnitId().equals(unitId) && existinglogin.getSystem().equals(systemId))
			{
				return sessionIdentifier;
			}
		}

		// Log-in user in EquationFunctionContext level
		BFEQCredentials credentials = new BFEQCredentials(userId, password, EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN,
						sessionIdentifier);

		// Session id/user id combination issue when 1 WAS profile used by UI and web services. Web Services should use pooled
		// connections and problem will disappear when pooled connections are used.
		String equationIseriesProfile = null;

		if (EquationCommonContext.isCASAuthentication())
		{
			equationIseriesProfile = EquationFunctionContext.getContext().getiSeriesUserForBFUser(
							EquationCommonContext.getContext().getEquationUnit(systemId, unitId), userId);
		}
		else
		{
			equationIseriesProfile = userId.toUpperCase();
		}

		sessionIdentifier = singletonFunctionContext.getEqSession(systemId, unitId, credentials, ipAddress, sessionType,
						equationIseriesProfile);

		// If successfully login but in the list of logged-off session, then log-it off again
		if (sessionIdentifier != null && isLogoffedSession(sessionIdentifier) >= 0)
		{
			removeLogoffedSession(sessionIdentifier);
			logOffDesktop(sessionIdentifier, "", true);
			sessionIdentifier = null;
		}

		// Session has been created successfully
		if (sessionIdentifier != null)
		{
			// classic desktop
			if (sessionType == EquationCommonContext.SESSION_CLASSIC_DESKTOP)
			{
				incrementSessionLimit(userId, sessionIdentifier);
				session.setAttribute(SESSION_LOGIN, singletonCommonContext.getEquationLogin(sessionIdentifier));
				return sessionIdentifier;
			}

			// ensure user has user information defined, otherwise logs-off user again
			EquationUser eqUser = singletonCommonContext.getEquationUser(sessionIdentifier);
			if (eqUser.getInputBranch().equals(EqDataType.VOID))
			{
				singletonFunctionContext.logOffSession(sessionIdentifier);
				throw new EQActionErrorException("Context.authenticate: failed", "error.invalid.noKmenu8Details");
			}

			EqInfo info;
			EqNavigator navigator;

			// Pop the menu into the session
			navigator = new EqNavigator(sessionIdentifier, singletonCommonContext.getEquationUser(sessionIdentifier));
			equationNavigators.put(sessionIdentifier, navigator);

			// Pop the info into the session
			info = new EqInfo(singletonCommonContext.getEquationUser(sessionIdentifier));
			equationInfos.put(sessionIdentifier, info);

			// Pop the sessions limit into the EquationUserExtension
			if (sessionType != EquationCommonContext.SESSION_DIRECT_TRANS_DESKTOP)
			{
				incrementSessionLimit(userId, sessionIdentifier);
			}

			// Store the HTTP session
			addHttpSession(sessionIdentifier, session);

			// Pop the function handler into the session
			session.setAttribute(SESSION_LOGIN, singletonCommonContext.getEquationLogin(sessionIdentifier));
			session.setAttribute(SESSION_UNIT, singletonCommonContext.getEquationUnit(sessionIdentifier));
			session.setAttribute(SESSION_USER, singletonCommonContext.getEquationUser(sessionIdentifier));
			session.setAttribute(SESSION_NAVI, getEqNavigator(sessionIdentifier));
			session.setAttribute(SESSION_INFO, getEqInfo(sessionIdentifier));
			session.setAttribute(FunctionRuntimeToolbox.NAME, singletonFunctionContext.getFunctionHandlerTable(sessionIdentifier));
			LOG.info("Session MaxInactiveInterval = [ " + session.getMaxInactiveInterval() + "] seconds");
		}

		return sessionIdentifier;
	}

	/**
	 * Maps a BankFusion user to an IBM i OS user
	 * 
	 * Throws EQActionErrorException suitable for Desktop processing, in comparison to the EquationFunctionContext version
	 * 
	 * @see EquationFunctionContext#getiSeriesUserForBFUser(EquationUnit, String)
	 * 
	 * @throws EQActionErrorException
	 */
	public String getiSeriesUserForBFUser(EquationUnit equationUnit, String bankFusionUserId) throws EQException
	{
		OCRecordDataModel ocRecord = equationUnit.getOCByBFUser(bankFusionUserId);
		if (ocRecord == null)
		{
			throw new EQActionErrorException("error.parameterised", null, "error.user.not.mapped", bankFusionUserId);
		}
		String iSeriesUser = ocRecord.getOsUserId();
		if (!Toolbox.stringNotBlank(iSeriesUser))
		{
			throw new EQActionErrorException("error.parameterised", null, "error.no.iseries.user", bankFusionUserId);
		}
		return iSeriesUser;
	}

	/**
	 * Increments the session count in EquationUserExtension for each user
	 * 
	 * @param userId
	 *            - user id from login form
	 * @param sessionId
	 *            - session id generated by the HttpServletRequest
	 * @throws EQException
	 */
	public void incrementSessionLimit(String userId, String sessionId) throws EQException
	{
		EquationUserExtension user = equationUserExtension.get(userId);
		if (user == null)
		{
			int limit = SessionLimitConfiguration.getContext().getSessionLimit(userId);
			user = new EquationUserExtension(limit);
		}
		try
		{
			user.incrementCurrentSessionCount(sessionId);
			equationUserExtension.put(userId, user);
		}
		catch (EQActionErrorException e)
		{
			rollbackLogin(sessionId);
			throw e;
		}
	}
	/**
	 * Getter method of Hashtable that contains the EquationUserExtension objects
	 * 
	 * @return EquationUserExtension
	 */
	public Hashtable<String, EquationUserExtension> getEquationUserExtension()
	{
		return equationUserExtension;
	}

	/**
	 * This will decrement the session count for each user.
	 * 
	 * @param userId
	 *            - user Id from the login form
	 * @param sessionId
	 *            - session id generated by the HttpServletRequest
	 */
	public void decrementEquationUserExtension(String userId, String sessionId)
	{
		EquationUserExtension user = equationUserExtension.get(userId);
		if (user != null)
		{
			user.decrementCurrentSessionCount(sessionId);
		}
	}

	/**
	 * This will remove all sessions that are stored during the initialization of getEqSession
	 * 
	 * @param sessionId
	 *            - session id generated by the HttpServletRequest
	 */
	public void rollbackLogin(String sessionId)
	{
		singletonFunctionContext.logOffSession(sessionId);
		equationNavigators.remove(sessionId);
		equationInfos.remove(sessionId);
	}

	/**
	 * Processing to perform clean up when desktop session has ended
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param name
	 *            - name of the function handler
	 * @param logoffBankFusion
	 *            - true to attempt logoff of the BankFusion session
	 */
	public void logOffDesktop(String sessionIdentifier, String name, boolean logoffBankFusion)
	{
		// Add to the list
		if (name.trim().length() == 0)
		{
			int i = addLogoffedSession(sessionIdentifier);
			if (i == -1)
			{
				return;
			}
		}

		// classic login?
		EquationLogin existinglogin = singletonCommonContext.getEquationLogin(sessionIdentifier);
		if (existinglogin != null && existinglogin.getSessionType() == EquationCommonContext.SESSION_CLASSIC_DESKTOP)
		{
			removeWorkstationId(existinglogin.getSystem(), existinglogin.getUnitId(), existinglogin.getWorkstationId());
			decrementEquationUserExtension(existinglogin.getUserId(), existinglogin.getSessionId());
			removeLogoffedSession(sessionIdentifier);
			singletonCommonContext.removeSession(sessionIdentifier);
		}

		// retrieve the function handler
		FunctionHandlerTable functionHandlerTable = singletonFunctionContext.getFunctionHandlerTable(sessionIdentifier);
		if (functionHandlerTable == null)
		{
			removeLogoffedSession(sessionIdentifier);
			return;
		}

		FunctionHandler functionHandler = functionHandlerTable.getFunctionHandler();
		if (functionHandler == null)
		{
			removeLogoffedSession(sessionIdentifier);
			return;
		}

		// only a child is logging off
		if (!name.equals(""))
		{
			functionHandlerTable.removeChild(name);
			return;
		}

		// when session logs-off, make sure to remove the task id currently linked to the session
		if (singletonCommonContext.isLRPProcessing())
		{
			singletonFunctionContext.unclaimTask(sessionIdentifier);
		}

		// If the workstationId was previously stored in the unique id list, remove it now
		String workstationIdUnique = EquationCommonContext.getConfigProperty("eq.workstationId.unique");
		if (workstationIdUnique != null && workstationIdUnique.equals("true"))
		{
			String workstationId = functionHandler.getFhd().getFunctionInfo().getWorkStationId();
			// remove workstation id from the list
			removeWorkstationId(sessionIdentifier, workstationId);
		}

		// Log-off WebFacing
		HttpSession httpSession = httpSessions.get(sessionIdentifier);
		if (httpSession != null)
		{
			EqDesktopToolBox.closeWebFacingJob(httpSession);
		}

		try
		{
			// For CAS compatibility retrieve the user id from the sessionId-to-User x-ref table
			String sessionLoginUser = singletonFunctionContext.getLoginUserBySessionId(sessionIdentifier);

			// log-off the session
			httpSessions.remove(sessionIdentifier);
			wepfEsfReferralTimestamps.remove(sessionIdentifier);
			lrpTaskTimestamps.remove(sessionIdentifier);
			singletonFunctionContext.logOffSession(sessionIdentifier, logoffBankFusion);
			equationNavigators.remove(sessionIdentifier);
			equationInfos.remove(sessionIdentifier);
			removeLogoffedSession(sessionIdentifier);
			decrementEquationUserExtension(sessionLoginUser, existinglogin.getSessionId());
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}
	/**
	 * Destroy the specified unit
	 * 
	 * @param systemId
	 *            - the system identifier
	 * @param unitId
	 *            - the unit identifier
	 * @throws EQException
	 */
	public void destroyUnit(String systemId, String unitId) throws EQException
	{
		singletonCommonContext.removeEquationUnit(systemId, unitId);
	}
	/**
	 * Initialise the specified unit
	 * 
	 * @param systemId
	 *            - the system identifier
	 * @param unitId
	 *            - the unit identifier
	 * @throws EQException
	 */
	public void initialiseUnit(String systemId, String unitId) throws EQException
	{
		// Initialize the Session Limit for every user
		SessionLimitConfiguration.getContext().initialiseSessionLimit();

		// LOG.info("Starting Desktop");
		String[] unitIds = EquationCommonContext.getConfigProperty("eq.units").split(",");
		String[] systemIds = EquationCommonContext.getConfigProperty("eq.systems").split(",");
		String[] userIds = EquationCommonContext.getConfigProperty("eq.admin.users").split(",");
		String[] passwords = EquationCommonContext.getConfigProperty("eq.admin.passwords").split(",");

		String userId = null;
		String password = null;
		// get user and password for the specified unit
		for (int i = 0; i < unitIds.length; i++)
		{
			if (systemIds[i].trim().equals(systemId) && unitIds[i].trim().equals(unitId))
			{
				userId = userIds[i].trim();
				password = passwords[i].trim();
				break;
			}
		}
		EquationSystem system = EquationCommonContext.getContext().getEquationSystem(systemId);
		if (!system.getUnits().containsKey(unitId))
		{
			try
			{
				boolean initialised = EquationCommonContext.getContext().initialiseUnitEnvironment(systemId.trim(), unitId.trim(),
								userId, password, EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN);

				if (initialised)
				{
					EquationCommonContext.getContext().getEquationUnit(systemId.trim(), unitId.trim());
				}
			}
			catch (Exception e)
			{
				LOG.error("Unable to initialise unit " + unitId, e);
				throw new EQException("Unable to initialise unit " + unitId + " due to " + e.getLocalizedMessage());
			}

		}
	}
	/**
	 * Return the help details
	 * 
	 * @return the help details
	 */
	public String getPromptHelpDetails(String sessionIdentifier, String prompt, String decode, String security, String dsccn)
	{
		// retrieve the function handler
		FunctionHandler functionHandler = singletonFunctionContext.getFunctionHandler(sessionIdentifier);
		if (functionHandler == null)
		{
			return "";
		}

		try
		{
			// return the details
			EqHelpDetail eqPromptHelp = new EqHelpDetail();
			return eqPromptHelp.getPromptHelpDetails(functionHandler.getFhd().getEquationUser(), prompt, decode, security, dsccn);
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
		return "";
	}

	/**
	 * Apply a transaction. It is assumed an EquationStandardSession already exists before calling this method.
	 * 
	 * @param sessionIdentifier
	 *            - session identifier
	 * @param data
	 *            - data
	 * @param image
	 *            - journal image
	 * @param fct
	 *            - function mode
	 * @param optionId
	 *            - option id
	 * @param journal
	 *            ? - journal it?
	 * 
	 * @return the work load of the user in HTML format
	 */
	public String applyTransactionData(String sessionIdentifier, String data, String image, String fct, String optionId,
					String journal) throws EQException
	{
		String str = null;
		EquationUnit unit = singletonCommonContext.getEquationUnit(sessionIdentifier);
		if (unit.isLegacyOption(optionId))
		{
			EquationStandardSession session = null;
			try
			{
				// Get a session
				session = singletonCommonContext.getEquationUser(sessionIdentifier).getSession();
				// set the transaction id
				String txnId = unit.getRecords().getGBRecord(optionId).getProgramName().substring(0, 4) + "RR" + optionId;
				// Get a new transaction
				EquationStandardTransaction transaction = new EquationStandardTransaction(txnId, session);
				// Set the mode
				transaction.setMode(fct);
				// Set the fields required for the sundry item
				transaction.setGzBytes(Toolbox.cvtHexStringToBytes(data));
				transaction.setWorkStationId(transaction.getFieldValue("GZWSID"));
				// add the transaction into the session, this won't do anything on the as400
				session.addEquationTransaction(transaction);
				// Apply the transaction
				session.applyTransactions();
				// Commit
				session.commitTransactions();
				// just first error message
				if (transaction.getErrorList().size() > 0)
				{
					str = transaction.getErrorList().get(0).getFormattedMessage();
				}
				else
				{
					return "";
				}
			}
			catch (Exception e)
			{
				LOG.error(e);
				return "20KSM7340" + Toolbox.pad(e.getMessage(), 30);
			}
		}
		else
		{
			FunctionHandler functionHandler = singletonFunctionContext.getFunctionHandler(sessionIdentifier, "");
			str = functionHandler.applyTransactionAsString(optionId, data, image, fct);
		}
		return str;
		// No return of connection to the pool as connection was not created here.
	}

	/**
	 * Processing to perform clean up when a server has been terminated
	 */
	public void removeContext()
	{
		Enumeration<String> loginSessions = singletonCommonContext.getSessionIdentifiers();
		while (loginSessions.hasMoreElements())
		{
			String sessionId = loginSessions.nextElement();
			logOffDesktop(sessionId, "", true);
		}

		// shut down the equation common context
		EquationCommonContext.getContext().shutdown();
	}

	/**
	 * Get an EqNavigator given a session ID
	 * 
	 * @param sessionIdentifier
	 * @return an EqNavigator
	 */
	public EqNavigator getEqNavigator(String sessionIdentifier)
	{
		if (singletonCommonContext.sessionValid(sessionIdentifier))
		{
			return equationNavigators.get(sessionIdentifier);
		}
		else
		{
			return null;
		}
	}

	/**
	 * Get an EqInfo given a session ID
	 * 
	 * @param sessionIdentifier
	 * @return an EqInfo
	 */
	public EqInfo getEqInfo(String sessionIdentifier)
	{
		if (singletonCommonContext.sessionValid(sessionIdentifier))
		{
			return equationInfos.get(sessionIdentifier);
		}
		else
		{
			return null;
		}
	}

	/**
	 * Return the Equation infos
	 * 
	 * @return the Equation infos
	 */
	public Hashtable<String, EqInfo> getEquationInfos()
	{
		return equationInfos;
	}

	// FIXME: We should not be constructing a new one of these EqReferral objects every time!
	public EqReferral getEqReferral(String sessionIdentifier) throws EQException
	{
		if (singletonCommonContext.sessionValid(sessionIdentifier))
		{
			return new EqReferral(singletonCommonContext.getEquationUser(sessionIdentifier));
		}
		else
		{
			// we should only get here if UXP is the UI

			String systemId = EquationCommonContext.getConfigProperty("eq.systems");
			String unitId = EquationCommonContext.getConfigProperty("eq.units");
			EquationSystem system = EquationCommonContext.getContext().getEquationSystem(systemId);
			String currentStatus = null;
			if (system.getUnits().containsKey(unitId))
			{
				EquationUnit unit = system.getUnit(unitId);
				if (unit.isAvailable())
				{
					currentStatus = "eq.resume";
				}
				else
				{
					currentStatus = "eq.suspend";
				}
			}
			else
			{
				currentStatus = "eq.suspend";
			}
			// if the menu status has changed let the client know
			String status = menuStatus.get(sessionIdentifier);
			LOG
							.debug("Browser menu status: " + sessionIdentifier + " Current status: " + currentStatus
											+ " Unit status: " + status);
			if (status == null || !status.equals(currentStatus))
			{
				menuStatus.put(sessionIdentifier, currentStatus);
				LOG.info("Browser menu status change: " + sessionIdentifier + " " + currentStatus);
				return new EqReferral(currentStatus);
			}
			else
			{
				return new EqReferral();
			}
		}
	}

	/**
	 * Setup the function handler details
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 * @param request
	 *            - the HTTP request
	 */
	private void setupFunctionHandler(String sessionIdentifier, HttpServletRequest request)
	{
		// Note: if you change this method consider whether similar changes are needed in UXP EqSessionIntegration
		FunctionInfo functionInfo = singletonFunctionContext.getFunctionHandler(sessionIdentifier).getFhd().getFunctionInfo();

		// debug mode?
		if (request.getParameter(PARAM_DEBUG) != null)
		{
			if (request.getParameter(PARAM_DEBUG).equals("true"))
			{
				functionInfo.setDebugMode(true);
			}
		}

		// forced rollback?
		if (request.getParameter(PARAM_ROLLBACK) != null)
		{
			if (request.getParameter(PARAM_ROLLBACK).equals("true"))
			{
				functionInfo.setRollback(true);
			}
		}

		// workstation id
		if (request.getParameter(PARAM_WORKSTATION) != null && !request.getParameter(PARAM_WORKSTATION).trim().equals(""))
		{
			functionInfo.setWorkStationId(request.getParameter(PARAM_WORKSTATION).trim());
		}

		// enable warning/info for update api
		functionInfo.setGenerateWarningInfo(true);
	}

	/**
	 * Setup the Equation login for classic login
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 * @param request
	 *            - the HTTP request
	 */
	private void setupClassicLogin(String sessionIdentifier, HttpServletRequest request)
	{
		EquationLogin equationLogin = singletonCommonContext.getEquationLogin(sessionIdentifier);

		// workstation id
		if (request.getParameter(PARAM_WORKSTATION) != null && !request.getParameter(PARAM_WORKSTATION).trim().equals(""))
		{
			equationLogin.setWorkstationId(request.getParameter(PARAM_WORKSTATION).trim());
		}
	}

	/**
	 * Remove a session id from the list of log-offed sessions
	 * 
	 * @param sessionIdentifier
	 *            - the session id to be removed
	 * 
	 * @return true if session remove from the list
	 */
	private boolean removeLogoffedSession(String sessionIdentifier)
	{
		int i = logoffedSessions.indexOf(sessionIdentifier);
		if (i >= 0)
		{
			logoffedSessions.remove(i);
		}

		// loop to remove all
		while (i >= 0)
		{
			i = logoffedSessions.indexOf(sessionIdentifier);

			if (i >= 0)
			{
				logoffedSessions.remove(i);
			}
			else
			{
				return true;
			}
		}

		// nothing deleted
		return false;
	}

	/**
	 * Add a session id from the list of log-offed sessions
	 * 
	 * @param sessionIdentifier
	 *            - the session id to be removed
	 * 
	 * @return the index if the session is added (or -1 if the session is already logging off
	 */
	private synchronized int addLogoffedSession(String sessionIdentifier)
	{
		// session id already exists?
		int i = logoffedSessions.indexOf(sessionIdentifier);
		if (i >= 0)
		{
			return -1;
		}

		// exceed limit?
		if (logoffedSessions.size() > LOGOFFSESSION_LIMIT)
		{
			logoffedSessions.remove(0);
		}

		// add and return index
		logoffedSessions.add(sessionIdentifier);
		return logoffedSessions.size() - 1;
	}

	/**
	 * Return the index if session is already logging off
	 * 
	 * @param sessionIdentifier
	 *            - the session id
	 * 
	 * @return the index if the session is logging off
	 */
	private int isLogoffedSession(String sessionIdentifier)
	{
		return logoffedSessions.indexOf(sessionIdentifier);
	}

	/**
	 * Checks whether the workstation is already in used or not
	 * 
	 * @param systemId
	 * @param unitId
	 * @param workstationId
	 * @return true if workstation ID is valid
	 */
	public boolean isValidWorkstationId(String systemId, String unitId, String workstationId)
	{
		// if workstationId is blank do not validate
		if (workstationId.trim().equals(""))
			return true;
		// Only the first 4 characters of workstationId are used
		String wsid4;
		if (workstationId.length() < 4)
			wsid4 = workstationId.substring(0, workstationId.length());
		else
			wsid4 = workstationId.substring(0, 4);

		String arrayId = systemId + "." + unitId + "." + wsid4;
		if (loggedonWorkStation.contains(arrayId))
		{
			return false;
		}
		return true;
	}

	/**
	 * Add the sessionIdentifier, system id, unit id and workstation id to the list of logged on workstations
	 * 
	 * @param systemId
	 * @param unitId
	 * @param workstationId
	 * @param validateFlag
	 */
	public synchronized void addWorkstationId(String systemId, String unitId, String workstationId, boolean validateFlag)
	{
		// If workstationId is blank, do not store anything
		if (workstationId.trim().equals(""))
			return;

		if (validateFlag && isValidWorkstationId(systemId, unitId, workstationId))
		{
			// Only the first 4 characters of workstationId are used
			String wsid4;
			if (workstationId.length() < 4)
				wsid4 = workstationId.substring(0, workstationId.length());
			else
				wsid4 = workstationId.substring(0, 4);

			// add workstation id to the list
			String newWsid = systemId + "." + unitId + "." + wsid4;
			loggedonWorkStation.add(newWsid);
		}

	}

	/**
	 * Remove the specified record from the list of logged on workstations
	 * 
	 * @param sessionIdentifier
	 */
	private void removeWorkstationId(String sessionIdentifier, String workstationId)
	{
		EquationLogin existinglogin = singletonCommonContext.getEquationLogin(sessionIdentifier);
		if (existinglogin != null)
		{
			removeWorkstationId(existinglogin.getSystem(), existinglogin.getUnitId(), workstationId);
		}
	}

	/**
	 * Remove the specified record from the list of logged on workstations
	 * 
	 * @param sessionIdentifier
	 */
	private void removeWorkstationId(String system, String unitId, String workstationId)
	{
		// Only the first 4 characters of workstationId are used
		String wsid4;
		if (workstationId.length() < 4)
		{
			wsid4 = workstationId.substring(0, workstationId.length());
		}
		else
		{
			wsid4 = workstationId.substring(0, 4);
		}

		loggedonWorkStation.remove(system + "." + unitId + "." + wsid4);
	}

	/**
	 * Set WEPF referral alert timestamp for the given session Id
	 * 
	 * @param sessionIdentifier
	 *            - the session Id
	 * @param newTimestamp
	 *            - the new timestamp
	 * @return the date
	 */
	public String setWepfEsfReferralTimestamp(String sessionIdentifier, String newTimestamp)
	{

		return wepfEsfReferralTimestamps.put(sessionIdentifier, newTimestamp);

	}

	/**
	 * Get WEPF referral alert timestamp given a session Id
	 * 
	 * @param sessionIdentifier
	 *            - the session Id
	 * @return the date
	 */
	public String getWepfEsfReferralTimestamp(String sessionIdentifier)
	{

		return wepfEsfReferralTimestamps.get(sessionIdentifier);

	}

	/**
	 * Set LRP referral alert timestamp for the given session Id
	 * 
	 * @param sessionIdentifier
	 *            - the session Id
	 * @param newTimestamp
	 *            - the new timestamp
	 * @return the date
	 */
	public String setLrpTaskTimestamp(String sessionIdentifier, String newTimestamp)
	{

		return lrpTaskTimestamps.put(sessionIdentifier, newTimestamp);

	}

	/**
	 * Get LRP referral alert timestamp given a session ID
	 * 
	 * @param sessionIdentifier
	 *            - the session Id
	 * @return the date
	 */
	public String getLrpTaskTimestamp(String sessionIdentifier)
	{
		return lrpTaskTimestamps.get(sessionIdentifier);
	}

	/**
	 * Refresh cache
	 * 
	 * @param sessionIdentifier
	 *            - the session id
	 */
	public void refreshCache(String sessionIdentifier) throws EQException
	{
		EquationFunctionContext.getContext().refreshCache(sessionIdentifier);
	}

	/**
	 * Obtains an IPv4 Address from the request.
	 * 
	 * This method will lookup the IPv4 address if the IP address in the request was an IPv6 address
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return String containing the IPv4 Address for this request.
	 */
	private String getIpv4Address(HttpServletRequest request)
	{
		String address = request.getRemoteAddr();
		// If the length is greater than 15, assume IPv6 address
		if (address.length() > 15)
		{
			String host = request.getRemoteHost();
			InetAddress remoteAddress;
			try
			{
				remoteAddress = Inet4Address.getByName(host);
				address = remoteAddress.getHostAddress();
			}
			catch (UnknownHostException e)
			{
				LOG.error(e);
			}
		}
		return address;
	}

	/**
	 * Add HTTP session to the context
	 * 
	 * @param sessionIdentifier
	 *            - the session id
	 * @param httpSession
	 *            - the HTTP session
	 */
	public void addHttpSession(String sessionIdentifier, HttpSession httpSession)
	{
		httpSessions.put(sessionIdentifier, httpSession);
	}
	/**
	 * Return the menu status collection
	 * 
	 * @return the menu status collection
	 */
	public Map<String, String> getMenuStatus()
	{
		return menuStatus;
	}

	/**
	 * Called from Equation Function layer to remove user UI sessions for the specified unit
	 */
	@Override
	public void removeAllUsersOfUnit(String systemId, String unitId)
	{
		List<String> sessionIds = new ArrayList<String>();
		String sessionIdentifier = null;

		Iterator<String> sessionIterator = singletonDesktopContext.getEquationInfos().keySet().iterator();
		// Put all the session ids in an array as the logoff process is changing the EquationInfos key set
		while (sessionIterator.hasNext())
		{
			sessionIdentifier = sessionIterator.next();
			sessionIds.add(sessionIdentifier);
		}
		for (int i = 0; i < sessionIds.size(); i++)
		{
			sessionIdentifier = sessionIds.get(i);
			if (singletonCommonContext.sessionValid(sessionIdentifier))
			{
				EquationUser sessionUser = singletonCommonContext.getEquationUser(sessionIdentifier);
				EquationUnit sessionUnit = sessionUser.getEquationUnit();
				if (sessionUnit.getUnitId().equals(unitId))
				{
					if (singletonDesktopContext.getEqInfo(sessionIdentifier).rtvUxpUsed())
					{
						singletonDesktopContext.logOffDesktop(sessionIdentifier, "", false);
					}
					else
					{
						singletonDesktopContext.logOffDesktop(sessionIdentifier, "", true);
					}
				}
			}
		}

	}

}