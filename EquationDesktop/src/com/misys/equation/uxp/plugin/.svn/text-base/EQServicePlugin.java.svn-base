package com.misys.equation.uxp.plugin;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.misys.bankfusion.common.HostStatusTemporaryCache;
import com.misys.bankfusion.offline.state.StatusEnum;
import com.misys.bankfusion.uxp.plugin.BankFusionServicePlugin;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.runtime.FunctionBankFusion;
import com.misys.equation.ui.context.EquationDesktopContext;
import com.misys.uxp.framework.api.json.IMessageItem;
import com.misys.uxp.framework.api.json.MenuItem;
import com.misys.uxp.framework.api.json.RequestItem;
import com.misys.uxp.framework.api.json.UserPreferenceItem;
import com.misys.uxp.framework.api.metadata.IUXFormMetadata;
import com.misys.uxp.framework.api.metadata.IUXServiceData;
import com.misys.uxp.framework.api.service.IServicePlugin;
import com.misys.uxp.framework.api.service.IUXRequestMessage;
import com.misys.uxp.framework.api.service.IUXResponseMessage;
import com.misys.uxp.framework.common.utilities.UXException;
import com.misys.uxp.framework.common.utilities.UXFactory;

/**
 * This plug-in class is an injection point for Equation services
 * 
 */
public class EQServicePlugin implements IServicePlugin
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQServicePlugin.java 318 2013-05-09 11:24:59Z alex.lim $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EQServicePlugin.class);

	public static final String ID = "id";
	public static final String VALUE = "value";
	private static final String SERVICE_NAME = "sname";
	private static final String EMPTY_STRING = "";
	private static final String ACTION_MENU = "menu";
	private static final String ACTION_START = "start";
	private static final String ACTION_SUB_PROCESS_START = "subProcessStart";
	private static final String ACTION_CLOSE = "Close";
	private static final String ACTION_SAVEPREFS = "saveprefs";
	private static final String ACTION_LOGIN = "login";
	private static final String ACTION_LOGOUT = "logout";
	private static final String ACTION_EQ_SUSPEND = "eq.suspend";
	private static final String ACTION_EQ_RESUME = "eq.resume";
	private static final String ACTION_EQ_LOGIN = "eq.login";
	private static final String ACTION_EQ_REFRESH_CACHE = "eq.refreshCache";

	/** Application information map returned in login response UXSD */
	private static final String BODY_APPLICATIONDATA = "applicationData";
	/** User token from the branch */
	public static String BODY_BRANCH_USER_TOKEN = "brnToken";
	/** Server type in a Offline Teller topology */
	public static String BODY_SERVER_TYPE = "srvType";
	/** Server type Data Centre */
	public static String SERVER_TYPE_DATA_CENTRE = "dc";

	/** File containing hard-coded menu items */
	private static final String MENU_FILE = "configuration/menu.json";

	/** The DC Server */
	private static String dcServer = null;

	private static Map<String, String> passwords = new HashMap<String, String>();

	static
	{
		// Register custom serializer/deserializer for MenuItem[]s
		UXFactory.registerGsonTypeAdapter(MenuItemTypeAdapter.MENUITEM_ARRAY_TYPE, new MenuItemTypeAdapter());

		// Retrieve the DC server setup
		dcServer = EquationCommonContext.getDCServer();
		if (dcServer != null && dcServer.length() == 0)
		{
			dcServer = null;
		}
		LOG.info("dcServer is [" + dcServer + "]");
	}

	/**
	 * This is the method invoked by the UX Servlet.
	 */
	@Override
	public IUXResponseMessage executeService(IUXRequestMessage request) throws UXException
	{
		IUXResponseMessage response = null;
		RequestItem requestItem = request.getRequestItem();
		HashMap<String, String> hmHeader = requestItem.getHeader();
		HashMap<String, String> hmBody = requestItem.getRequestBody();
		String action = hmBody.get(IMessageItem.BODY_SERVICE_ACTION);
		LOG.info("Received request action [" + action + "]");
		try
		{
			String serverType = hmBody.get(BODY_SERVER_TYPE);
			if (SERVER_TYPE_DATA_CENTRE.equals(serverType))
			{
				response = processDataCentreRequest(request, hmHeader, hmBody, action);
			}
			else
			{
				if (action.equalsIgnoreCase(ACTION_LOGIN))
				{
					response = processLoginRequest(request, hmHeader, hmBody);
				}
				else if (action.equalsIgnoreCase(ACTION_LOGOUT))
				{
					response = processLogoutRequest(request, hmHeader, hmBody);
				}
				else if (action.equalsIgnoreCase(ACTION_MENU))
				{
					response = processMenuRequest(request, hmHeader, hmBody);
				}
				else if (action.equalsIgnoreCase(ACTION_EQ_SUSPEND))
				{
					response = equationSuspendRequest(request, hmHeader, hmBody);
				}
				else if (action.equalsIgnoreCase(ACTION_EQ_RESUME))
				{
					response = equationResumeRequest(request, hmHeader, hmBody);
				}
				else if (action.equalsIgnoreCase(ACTION_EQ_LOGIN))
				{
					response = equationLoginRequest(request, hmHeader, hmBody);
				}
				else if (action.equalsIgnoreCase(ACTION_EQ_REFRESH_CACHE))
				{
					response = equationRefreshCacheRequest(request, hmHeader, hmBody);
				}
				else
				{
					response = processOtherRequests(request, action, hmHeader, hmBody);
				}
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			throw new UXException(e);
		}
		LOG.info("Returning response for action [" + action + "]");
		return response;
	}

	/**
	 * Process Data Centre request
	 * 
	 * @param request
	 *            The original UXRequestMessage
	 * @param hmHeader
	 *            request header
	 * @param hmBody
	 *            request body
	 * @return - processed response message
	 * @throws Exception
	 */
	private IUXResponseMessage processDataCentreRequest(final IUXRequestMessage request, final HashMap<String, String> hmHeader,
					final HashMap<String, String> hmBody, String action) throws Exception
	{
		IUXResponseMessage response = null;
		if (action.equalsIgnoreCase(ACTION_LOGIN))
		{
			response = dataCentreLoginRequest(request, hmHeader, hmBody);
		}
		else if (action.equalsIgnoreCase(ACTION_MENU))
		{
			response = dataCentreMenuRequest(request, hmHeader, hmBody);
		}
		else
		{
			// Only the request types above are forwarded to the Data Centre
			throw new UXException("Unhandled Data Centre action [" + action + "]");
		}
		return response;
	}
	/**
	 * Process Login command
	 * 
	 * @param request
	 *            The original UXRequestMessage
	 * @param hmHeader
	 *            request header
	 * @param hmBody
	 *            request body
	 * @return - processed response message
	 * @throws Exception
	 */
	private IUXResponseMessage processLoginRequest(final IUXRequestMessage request, final HashMap<String, String> hmHeader,
					final HashMap<String, String> hmBody) throws Exception
	{
		IUXResponseMessage response = null;

		// read the data from the input request
		String userName = hmHeader.get(IMessageItem.HEADER_USER_NAME);
		String userToken = "YOU_SHALL_NOT_PASS!REALLY?";
		boolean offline = true;

		EqSessionIntegration eqSessionIntegration = new EqSessionIntegration();
		if (!Toolbox.stringNotBlank(eqSessionIntegration.getEquationWebAppName()))
		{
			throw new UXException("Desktop project could not be determined.");
		}

		if (EquationCommonContext.isBankFusionInstalled())
		{
			String password = hmBody.get(IMessageItem.BODY_USER_PASSWORD);
			// Login using BFPlugin
			BankFusionServicePlugin bfPlugin = new BankFusionServicePlugin();
			response = bfPlugin.executeService(request);
			// Get user locator/token from BFPlugin login
			userToken = response.getResponseItem().getHeader().get("ut");

			String returnedBFUserName = response.getResponseItem().getHeader().get(IMessageItem.HEADER_USER_NAME);
			if (userName.equalsIgnoreCase(returnedBFUserName))
			{
				// BF will return the exact case of the user name
				// so if BF login successful, use this name to login to EQ
				userName = returnedBFUserName;
			}

			// Save plain text password
			passwords.put(userToken, password);

			// Is BF Offline enabled and Data center has been specified, then submit the request to the
			// data center
			if (isBranchServerSetup())
			{
				// default details
				eqSessionIntegration.retrieveDefaultDetails();

				// Check if online
				if (isOnline())
				{
					// Pass the userToken to the DC
					request.getRequestItem().getRequestBody().put(BODY_BRANCH_USER_TOKEN, userToken);

					// Forward the request to the DC to create the EQ session:
					IUXResponseMessage dcResponse = new EqBranchForward().forwardUXRequest(dcServer, request);
					String dcUserToken = dcResponse.getResponseItem().getHeader().get(IMessageItem.HEADER_USER_TOKEN);
					offline = false;
				}
				// If not online, then cannot log into Equation
			}
			else
			{
				// Login to Equation using user locator/token from BF
				// BF user locator/token will become Equation session identifier
				if (eqSessionIntegration.isDefaultUnitAvailable())
				{
					eqSessionIntegration.loginToEquation(userName, password, EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN,
									userToken);
				}
				offline = false;
			}
			// Set response parameters from BFPlugin login
			HashMap<String, String> uxsdData = response.getUXServiceData().getData();
			// UXP approved approach for returning application specific
			// information, to be available in layout page startup
			Map<String, String> appData = new HashMap<String, String>();
			appData.put(EqSessionIntegration.WEB_APP_NAME, eqSessionIntegration.getEquationWebAppName());
			appData.put(EqSessionIntegration.UNIT_NAME, eqSessionIntegration.getDefaultUnit());
			appData.put(EqSessionIntegration.INITIAL_OFFLINE_STATUS, (offline ? "true" : "false"));
			appData.put(EqSessionIntegration.JS_LOGGING, String.valueOf(EquationCommonContext.getJSLogging()));
			appData.put(EqSessionIntegration.JS_NOTIFICATION, String.valueOf(EquationCommonContext.getJSNotification()));
			appData.put(EqSessionIntegration.UXP_POLL_INTERVAL, String.valueOf(eqSessionIntegration.getUXPPollInterval()));
			appData.put(EqSessionIntegration.VERSION, String.valueOf(EquationCommonContext.PLUGIN_VERSION));
			uxsdData.put(BODY_APPLICATIONDATA, UXFactory.getJsonString(appData));
		}
		else
		{
			HashMap<String, String> responseHeader = new HashMap<String, String>();
			responseHeader.put(IMessageItem.HEADER_USER_NAME, userName);
			responseHeader.put(IMessageItem.HEADER_USER_TOKEN, userToken);

			HashMap<String, String> uxsdData = new HashMap<String, String>();
			UserPreferenceItem prefs = getPreferences();
			prefs.setAdditionalData("un", userName);
			prefs.setAdditionalData("ut", userToken);
			prefs.setAdditionalData(EqSessionIntegration.WEB_APP_NAME, eqSessionIntegration.getEquationWebAppName());
			prefs.setAdditionalData(EqSessionIntegration.UNIT_NAME, eqSessionIntegration.getDefaultUnit());
			prefs.setAdditionalData(EqSessionIntegration.INITIAL_OFFLINE_STATUS, "true");
			prefs.setAdditionalData(EqSessionIntegration.JS_LOGGING, String.valueOf(EquationCommonContext.getJSLogging()));
			prefs
							.setAdditionalData(EqSessionIntegration.JS_NOTIFICATION, String.valueOf(EquationCommonContext
											.getJSNotification()));
			prefs
							.setAdditionalData(EqSessionIntegration.VERSION, String.valueOf(EquationCommonContext
											.getPluginVersionFormatted()));
			uxsdData.put(IMessageItem.BODY_USER_PREFERENCES, UXFactory.getJsonString(prefs));
			response = setResponseMessage(responseHeader, null, uxsdData);
		}
		return response;
	}
	/**
	 * Process Logout command
	 * 
	 * @param request
	 *            The original UXRequestMessage
	 * @param hmHeader
	 *            request header
	 * @param hmBody
	 *            request body
	 * @return - processed response message
	 * @throws Exception
	 */
	private IUXResponseMessage processLogoutRequest(final IUXRequestMessage request, final HashMap<String, String> hmHeader,
					final HashMap<String, String> hmBody) throws Exception
	{

		IUXResponseMessage response = null;
		// Extract UX user token
		String userToken = hmHeader.get(IMessageItem.HEADER_USER_TOKEN);
		// Logout from Equation using user token. Logout from BankFusion happens under the covers.
		new EqSessionIntegration().logoutFromEquation(userToken, false);
		passwords.remove(userToken);
		EquationDesktopContext.getContext().getMenuStatus().remove(userToken);
		if (EquationCommonContext.isBankFusionInstalled())
		{
			// Logout using BFPlugin
			BankFusionServicePlugin bfPlugin = new BankFusionServicePlugin();
			response = bfPlugin.executeService(request);

			// Note that it is assumed that when the BankFusion session on the
			// Data Centre is ended, the equivalent Equation session is also ended
		}
		return response;
	}

	/**
	 * Process Menu command
	 * 
	 * @param request
	 *            The original UXRequestMessage
	 * @param hmHeader
	 *            request header
	 * @param hmBody
	 *            request body
	 * @return - processed response message
	 * @throws Exception
	 */
	private IUXResponseMessage processMenuRequest(final IUXRequestMessage request, final HashMap<String, String> hmHeader,
					final HashMap<String, String> hmBody) throws Exception
	{
		// Get user locator/token/session identifier from BF
		String userToken = hmHeader.get(IMessageItem.HEADER_USER_TOKEN);
		String userName = hmHeader.get(IMessageItem.HEADER_USER_NAME);
		EqSessionIntegration eqSessionIntegration = new EqSessionIntegration();

		if (EquationCommonContext.isBankFusionInstalled())
		{
			// Get BF Menus by calling BFPlugin
			BankFusionServicePlugin bfPlugin = new BankFusionServicePlugin();
			IUXResponseMessage uxResponseMessage = bfPlugin.executeService(request);
			// Set response parameters from BFPlugin
			IUXServiceData uxsd = uxResponseMessage.getUXServiceData();
			HashMap<String, String> data = uxsd.getData();
			String bfJsonMenuString = data.get(IMessageItem.BODY_MENU);

			String eqJsonMenuString = null;

			// Get EQ menu options
			if (isBranchServerSetup())
			{
				if (isOnline())
				{
					// Branch Server processing:
					// Hack in the password:
					if (passwords.containsKey(userToken))
					{
						request.getRequestItem().getRequestBody().put(IMessageItem.BODY_USER_PASSWORD, passwords.get(userToken));
					}
					IUXResponseMessage response = new EqBranchForward().forwardUXRequest(dcServer, request);
					// TODO: handle going off-line (comms error)
					String exception = new EqBranchForward().getResponseException(response);
					if (exception != null)
					{
						throw new UXException(exception);
					}
					else
					{
						IUXServiceData dcUxsd = response.getUXServiceData();
						// TODO Check with Jonathan/Alex about how desktop suspension should work here
						eqJsonMenuString = dcUxsd.getData().get(IMessageItem.BODY_MENU);
						LOG.info(eqJsonMenuString);
					}
				}
			}
			else
			{
				MenuItem eqMenu = null;
				// if unit has been suspended then only the administrator gets an Equation menu
				if (!eqSessionIntegration.isDefaultUnitAvailable())
				{
					// if allowed to resume then 1 menu option will be returned otherwise no menu options will be returned
					eqMenu = eqSessionIntegration.getUserMenuTreeWithResumeDesktop(userName, 0);
				}
				else
				{
					// Load from local Equation
					eqMenu = eqSessionIntegration.getUserMenuTree(userToken, 0);
				}
				if (eqMenu != null)
				{
					eqJsonMenuString = "[" + UXFactory.getJsonString(eqMenu) + "]";
				}
			}

			if (eqJsonMenuString != null)
			{
				MenuItem[] eqMenus = MenuItemTypeAdapter.deserializeMenuJson(eqJsonMenuString);
				MenuItem[] bfMenus = MenuItemTypeAdapter.deserializeMenuJson(bfJsonMenuString);
				LOG.debug("BF Menu String: " + bfJsonMenuString);
				LOG.debug("BF Menu: " + bfMenus.toString());

				int numberOfBFMenus = bfMenus.length;

				MenuItem[] xMenus = new MenuItem[eqMenus.length + numberOfBFMenus];
				for (int index = 0; index < numberOfBFMenus; index++)
				{
					xMenus[index] = bfMenus[index];
				}
				for (int index = 0; index < eqMenus.length; index++)
				{
					xMenus[numberOfBFMenus + index] = eqMenus[index];
				}

				Set<String> ids = new HashSet<String>();
				Set<String> duplicates = new HashSet<String>();
				MenuItem[] xMenusPruned = removeDuplicateMenuItems(xMenus, ids, duplicates);
				String xMenusPrunedJson = UXFactory.getJsonString(xMenusPruned, MenuItemTypeAdapter.MENUITEM_ARRAY_TYPE);

				data.put(IMessageItem.BODY_MENU, xMenusPrunedJson);
			}
			else
			{
				// Only have BankFusion menus
				data.put(IMessageItem.BODY_MENU, bfJsonMenuString);
			}

			uxsd.setData(data);
			return uxResponseMessage;
		}
		else
		{
			String demoMenuString = loadResourceFile(MENU_FILE);

			MenuItem[] demoMenus = MenuItemTypeAdapter.deserializeMenuJson(demoMenuString);
			MenuItem eqMenuTree = null;
			if (!"YOU_SHALL_NOT_PASS!REALLY?".equals(userToken))
			{
				eqMenuTree = eqSessionIntegration.getUserMenuTree(userToken, 0);
			}

			int menuCount = demoMenus.length + (eqMenuTree != null ? 1 : 0);
			MenuItem[] unPruned = new MenuItem[menuCount];
			System.arraycopy(demoMenus, 0, unPruned, 0, demoMenus.length);
			if (eqMenuTree != null)
			{
				unPruned[demoMenus.length] = eqMenuTree;
			}
			Set<String> ids = new HashSet<String>();
			Set<String> duplicates = new HashSet<String>();
			MenuItem[] allMenus = removeDuplicateMenuItems(unPruned, ids, duplicates);

			IUXServiceData uxsd = UXFactory.getInitializedUXServiceData();
			HashMap<String, String> hmData = new HashMap<String, String>(1);

			String menuString = UXFactory.getJsonString(allMenus);
			// The response must be a JSON String of an array of MenuItems.
			hmData.put(IMessageItem.BODY_MENU, menuString);
			hmData.put(IMessageItem.BODY_SERVICE_ACTION, IMessageItem.BODY_MENU);
			uxsd.setData(hmData);
			IUXResponseMessage responseMessage = UXFactory.getInitializedUXResponseMessage();
			responseMessage.setUXServiceData(uxsd);
			return responseMessage;
		}
	}
	/**
	 * Process Equation Suspend command - no further transactions to be allowed - end Equation sessions
	 * 
	 * @param request
	 *            The original UXRequestMessage
	 * @param hmHeader
	 *            request header
	 * @param hmBody
	 *            request body
	 * @return - processed response message
	 * @throws Exception
	 */
	private IUXResponseMessage equationSuspendRequest(final IUXRequestMessage request, final HashMap<String, String> hmHeader,
					final HashMap<String, String> hmBody) throws Exception
	{
		EqSessionIntegration eqSessionIntegration = new EqSessionIntegration();
		eqSessionIntegration.destroyDefaultUnit();

		return setResponseMessage(null, null, null);
	}
	/**
	 * Process Equation Resume command - transaction processing can resume
	 * 
	 * @param request
	 *            The original UXRequestMessage
	 * @param hmHeader
	 *            request header
	 * @param hmBody
	 *            request body
	 * @return - processed response message
	 * @throws Exception
	 */
	private IUXResponseMessage equationResumeRequest(final IUXRequestMessage request, final HashMap<String, String> hmHeader,
					final HashMap<String, String> hmBody) throws Exception
	{
		EqSessionIntegration eqSessionIntegration = new EqSessionIntegration();
		eqSessionIntegration.initialiseDefaultUnit();

		return setResponseMessage(null, null, null);
	}
	/**
	 * Process Equation Login command. Only Equation sessions are logged in as BankFusion sessions should already exist.
	 * 
	 * @param request
	 *            The original UXRequestMessage
	 * @param hmHeader
	 *            request header
	 * @param hmBody
	 *            request body
	 * @return - processed response message
	 * @throws Exception
	 */
	private IUXResponseMessage equationLoginRequest(final IUXRequestMessage request, final HashMap<String, String> hmHeader,
					final HashMap<String, String> hmBody) throws Exception
	{
		String userToken = hmHeader.get(IMessageItem.HEADER_USER_TOKEN);
		String password = passwords.get(userToken);
		if (password != null)
		{
			String userName = hmHeader.get(IMessageItem.HEADER_USER_NAME);
			EqSessionIntegration eqSessionIntegration = new EqSessionIntegration();
			eqSessionIntegration.loginToEquation(userName, password, EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, userToken);
		}
		return setResponseMessage(null, null, null);
	}
	/**
	 * Process Equation Refresh Cache command
	 * 
	 * @param request
	 *            The original UXRequestMessage
	 * @param hmHeader
	 *            request header
	 * @param hmBody
	 *            request body
	 * @return - processed response message
	 * @throws Exception
	 */
	private IUXResponseMessage equationRefreshCacheRequest(final IUXRequestMessage request, final HashMap<String, String> hmHeader,
					final HashMap<String, String> hmBody) throws Exception
	{
		// Get user locator/token/session identifier from BF
		String userToken = hmHeader.get(IMessageItem.HEADER_USER_TOKEN);
		EqSessionIntegration eqSessionIntegration = new EqSessionIntegration();
		eqSessionIntegration.refreshCache(userToken);

		return setResponseMessage(null, null, null);

	}
	/**
	 * Processing any request from the UXP client except for login
	 * 
	 * @param request
	 *            The original UXRequestMessage
	 * @param action
	 *            - Type of action like a service execution
	 * @param hmHeader
	 *            - Request header
	 * @param hmBody
	 *            - Request body
	 * @return As per the contract between UXP and BF, we need to wrap the response in an implementation of IUXResponseMessage
	 */
	private IUXResponseMessage processOtherRequests(IUXRequestMessage request, String action, HashMap<String, String> hmHeader,
					HashMap<String, String> hmBody) throws Exception
	{
		String menuType = hmBody.get("menuType");
		String tabId = hmBody.get("tabid");
		EquationCommonContext.getContext();

		EqSessionIntegration eqSessionIntegration = new EqSessionIntegration();

		// BankFusion
		if (EquationCommonContext.isBankFusionInstalled())
		{
			// Non Equation Transaction
			if (menuType == null || !eqSessionIntegration.isEquationMenuType(menuType))
			{
				BankFusionServicePlugin bfPlugin = new BankFusionServicePlugin();
				IUXResponseMessage uxResponseMessage = bfPlugin.executeService(request);
				return uxResponseMessage;
			}
		}
		if (action.equalsIgnoreCase(ACTION_CLOSE))
		{
			// Try to remove functionHandler:
			String sessionIdentifier = hmHeader.get(IMessageItem.HEADER_USER_TOKEN);
			eqSessionIntegration.removeFunctionHandler(sessionIdentifier, tabId);
			hmHeader.put(IMessageItem.HEADER_RESPONSE_MESSAGE_TYPE, "");
			return setResponseMessage(hmHeader, null, hmBody);
		}
		else
		{
			throw new UXException("Unknown Action : " + action);
		}
	}
	/**
	 * Set the response message
	 * 
	 * @param header
	 *            - Response Header
	 * @param uxfm
	 *            - Response UX Form Metadata
	 * @param data
	 *            - Response Service Data
	 * @return Response Message
	 */
	private IUXResponseMessage setResponseMessage(HashMap<String, String> header, IUXFormMetadata uxfm, HashMap<String, String> data)
	{
		IUXResponseMessage responseMessage = UXFactory.getInitializedUXResponseMessage();
		if (header != null && uxfm != null)
		{
			// Store the unique service id in the message header (Form id = unique id)
			header.put(IMessageItem.HEADER_SERVICE_UNIQUE_ID, uxfm.getId());
			responseMessage.getResponseItem().setHeader(header);
		}
		else
		{
			if (header != null)
			{
				responseMessage.getResponseItem().setHeader(header);
			}
		}

		if (uxfm != null)
		{
			responseMessage.setUXFormMetadata(uxfm);
		}
		// Always set a data string as well
		IUXServiceData uxsd = UXFactory.getInitializedUXServiceData();
		if (data == null)
		{
			data = new HashMap<String, String>();
		}
		uxsd.setData(data);
		responseMessage.setUXServiceData(uxsd);
		return responseMessage;
	}

	/**
	 * Builds a new UserPreferenceItem for stand-alone mode
	 * 
	 * @return UserPreferenceItem
	 */
	private UserPreferenceItem getPreferences()
	{
		UserPreferenceItem prefs = new UserPreferenceItem();

		// direct properties
		prefs.setCalendar("gregorian");
		prefs.setDateFormat("dd-MM-yyyy");
		prefs.setLocale("en-gb");
		prefs.setTimeZone("Europe/London");

		// business date
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat ISO8601FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		prefs.setAdditionalData("dt", ISO8601FORMAT.format(date));

		// Put in a blank String to stop the UX Desktop code attempting to access
		// Device support
		prefs.setAdditionalData("devConfigLoc", "");

		ArrayList<HashMap<String, String>> bankBranchZoneInfo = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> items = new HashMap<String, String>();
		items.put(ID, "Bank");
		items.put(VALUE, "Demo bank");
		items.put(SERVICE_NAME, EMPTY_STRING);
		bankBranchZoneInfo.add(items);

		items = new HashMap<String, String>();
		items.put(ID, "Branch");
		items.put(VALUE, "LOND");
		items.put(SERVICE_NAME, EMPTY_STRING);
		bankBranchZoneInfo.add(items);

		prefs.setAdditionalData("ZoneInfo", UXFactory.getJsonString(bankBranchZoneInfo));

		return prefs;
	}

	/**
	 * Load a file from the uxConfigLocation folder, or an internal resource
	 * 
	 * @param path
	 *            File path
	 * @return String containing the contents of the file
	 */
	private String loadResourceFile(String path)
	{
		StringBuilder result = new StringBuilder(1024);
		try
		{
			byte[] buffer = new byte[1024];
			// See if an externalised version of the menu structure is available:
			String configLocation = System.getProperty("uxConfigLocation");
			InputStream is = null;
			try
			{
				is = new FileInputStream((new StringBuilder()).append(configLocation).append("/").append(path).toString());
			}
			catch (Exception e)
			{
				LOG.info((new StringBuilder()).append("Could not load external file: ").append(configLocation).append("/").append(
								path).toString());
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
			}
			BufferedInputStream iStream = new BufferedInputStream(is);
			int bytesRead = 0;
			while ((bytesRead = iStream.read(buffer)) != -1)
			{
				String sMenu = new String(buffer, 0, bytesRead);
				result.append(sMenu);
				buffer = new byte[1024];
			}
			iStream.close();
		}
		catch (Exception ex)
		{
			LOG.error(ex);
			throw new UXException(ex);
		}
		return result.toString();
	}

	/**
	 * This method calls itself recursively to prune duplicate menu items (both folders and processes) from the menu structure
	 * 
	 * @param menus
	 *            An array of MenuItems.
	 * @param ids
	 *            The set of ids found so far
	 * @param duplicates
	 *            A set of duplicates (for information purposes)
	 * 
	 * @return An array of MenuItems with all duplicates removed
	 */
	public MenuItem[] removeDuplicateMenuItems(MenuItem[] menus, Set<String> ids, Set<String> duplicates)
	{
		ArrayList<Object> newMenus = new ArrayList<Object>();

		for (MenuItem menuItem : menus)
		{
			String id = menuItem.getId();

			if (menuItem.getState() != -1 && ids.contains(id))
			{
				LOG.info("Duplicate id found [" + id + "]");
				duplicates.add(id);
			}
			else
			{
				if (menuItem.getId() != null)
				{
					ids.add(id);
				}
				newMenus.add(menuItem);

				if (menuItem.getChildren() != null)
				{
					MenuItem[] childItems = new MenuItem[menuItem.getChildren().size()];
					menuItem.getChildren().toArray(childItems);
					MenuItem[] newChildItems = removeDuplicateMenuItems(childItems, ids, duplicates);
					if (childItems.length != newChildItems.length)
					{
						ArrayList<Object> newChildren = new ArrayList<Object>();
						for (MenuItem i : newChildItems)
						{
							newChildren.add(i);
						}
						menuItem.setChildren(newChildren);
					}
				}
			}
		}

		// Replace original menu list if any items removed
		if (newMenus.size() != menus.length)
		{
			MenuItem[] result = new MenuItem[newMenus.size()];
			newMenus.toArray(result);
			return result;
		}
		return menus;
	}

	/**
	 * Calls BankFusion to determine the status of the branch
	 * 
	 * @return boolean - true if online
	 */
	private boolean isOnline()
	{
		// Check if online
		boolean result = false;
		HostStatusTemporaryCache hostStatusTemporaryCache = HostStatusTemporaryCache.getInstance();
		StatusEnum currentStatus = hostStatusTemporaryCache.getOverAllHostStatus();
		result = StatusEnum.ONLINE.equals(currentStatus);
		return result;
	}

	/**
	 * Handles login request in Data Centre mode.
	 * 
	 * This will create an Equation session using the supplied user token.
	 * 
	 * @param request
	 *            The original UXRequestMessage
	 * @param hmHeader
	 *            request header
	 * @param hmBody
	 *            request body
	 * @return - processed response message
	 * @throws Exception
	 */
	private IUXResponseMessage dataCentreLoginRequest(final IUXRequestMessage request, final HashMap<String, String> hmHeader,
					final HashMap<String, String> hmBody) throws Exception
	{
		IUXResponseMessage response = null;
		// Must do EquationCommonContext.getContext() at least once before using static methods.
		EquationCommonContext.getContext();

		// read the data from the input request
		String userName = hmHeader.get(IMessageItem.HEADER_USER_NAME);
		String userToken = hmBody.get(BODY_BRANCH_USER_TOKEN);
		String password = hmBody.get(IMessageItem.BODY_USER_PASSWORD);

		EqSessionIntegration eqSessionIntegration = new EqSessionIntegration();
		// Login to Equation using user locator/token from Branch Server
		// BF user locator/token will become Equation session identifier
		eqSessionIntegration.loginToEquation(userName, password, EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, userToken);

		response = UXFactory.getInitializedUXResponseMessage();
		HashMap<String, String> responseHeader = new HashMap<String, String>();
		responseHeader.put(IMessageItem.HEADER_USER_NAME, userName);
		responseHeader.put(IMessageItem.HEADER_USER_TOKEN, userToken);
		// TODO set Service Data
		response = setResponseMessage(responseHeader, null, null);
		response.setUXServiceData(null);
		return response;
	}

	/**
	 * Process Menu command
	 * 
	 * @param request
	 *            The original UXRequestMessage
	 * @param hmHeader
	 *            request header
	 * @param hmBody
	 *            request body
	 * @return - processed response message
	 * @throws Exception
	 */
	private IUXResponseMessage dataCentreMenuRequest(final IUXRequestMessage request, final HashMap<String, String> hmHeader,
					final HashMap<String, String> hmBody) throws Exception
	{
		// Get user locator/token/session identifier from BF
		String userToken = hmHeader.get(IMessageItem.HEADER_USER_TOKEN);
		String userName = hmHeader.get(IMessageItem.HEADER_USER_NAME);
		String password = hmBody.get(IMessageItem.BODY_USER_PASSWORD);

		EqSessionIntegration eqSessionIntegration = new EqSessionIntegration();
		eqSessionIntegration.loginToEquation(userName, password, EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, userToken);

		MenuItem eqMenu = eqSessionIntegration.getUserMenuTree(userToken, 1);
		MenuItem[] menuArray = new MenuItem[] {};
		if (eqMenu != null)
		{
			menuArray = new MenuItem[] { eqMenu };
		}

		IUXServiceData uxsd = UXFactory.getInitializedUXServiceData();
		HashMap<String, String> serviceData = new HashMap<String, String>();
		// The response must be a JSON String of an array of MenuItems.
		String menuString = UXFactory.getJsonString(menuArray);
		serviceData.put(IMessageItem.BODY_MENU, menuString);
		serviceData.put(IMessageItem.BODY_SERVICE_ACTION, IMessageItem.BODY_MENU);
		uxsd.setData(serviceData);
		IUXResponseMessage responseMessage = UXFactory.getInitializedUXResponseMessage();
		responseMessage.setUXServiceData(uxsd);
		return responseMessage;
	}

	/**
	 * Determine if this is a branch server setup
	 * 
	 * @return true if branch server setup
	 */
	private boolean isBranchServerSetup()
	{
		return FunctionBankFusion.isOfflineEnabled() && dcServer != null;
	}

}
