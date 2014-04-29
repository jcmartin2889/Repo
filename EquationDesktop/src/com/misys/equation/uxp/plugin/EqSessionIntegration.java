package com.misys.equation.uxp.plugin;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gson.reflect.TypeToken;
import com.misys.equation.common.access.AbstractEquationSessionPool;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationLogin;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.SessionLimitConfiguration;
import com.misys.equation.common.internal.eapi.core.EQActionErrorException;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.context.BFEQCredentials;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.function.runtime.FunctionBankFusion;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionHandlerTable;
import com.misys.equation.function.runtime.FunctionInfo;
import com.misys.equation.ui.beans.EqMenu;
import com.misys.equation.ui.context.EquationDesktopContext;
import com.misys.equation.ui.helpers.EqInfo;
import com.misys.equation.ui.servlets.DesktopStartupServlet;
import com.misys.uxp.framework.api.json.MenuItem;
import com.misys.uxp.framework.api.json.UserPreferenceItem;
import com.misys.uxp.framework.common.utilities.UXException;
import com.misys.uxp.framework.common.utilities.UXFactory;

public class EqSessionIntegration
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqSessionIntegration.java 317 2013-05-08 15:18:18Z alex.lim $";

	private static final EquationLogger LOG = new EquationLogger(EqSessionIntegration.class);

	public static final String WEB_APP_NAME = "equationWebAppName";
	public static final String UNIT_NAME = "equationUnit";
	public static final String INITIAL_OFFLINE_STATUS = "eqOffline";
	public static final String JS_LOGGING = "equationJSLogging";
	public static final String JS_NOTIFICATION = "equationJSNotification";
	public static final String UXP_POLL_INTERVAL = "equationRefPollInt";
	public static final String VERSION = "equationVersion";

	/** Equation service menu type */
	public static final String MENU_TYPE_EQSERVICE = "EQ";
	/** Webfacing menu type */
	public static final String MENU_TYPE_WEBFACING = "WF";

	private static boolean defaultedAlready = false;
	private static String defaultUnit = "";
	private static String defaultSystem = "";

	/** Fallback date format for business date */
	private static SimpleDateFormat ISO8601FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/** The characters to be concatenated between the option id and description for Equation menu options */
	private static final String MENU_ITEM_SEPARATOR = ": ";
	static
	{
		// Initialise the Session Limit for every user
		SessionLimitConfiguration.getContext().initialiseSessionLimit();

		// LOG.info("Starting Desktop");
		String[] unitIds = EquationCommonContext.getConfigProperty("eq.units").split(",");
		String[] systemIds = EquationCommonContext.getConfigProperty("eq.systems").split(",");
		String[] userIds = EquationCommonContext.getConfigProperty("eq.admin.users").split(",");
		String[] passwords = EquationCommonContext.getConfigProperty("eq.admin.passwords").split(",");

		for (int i = 0; i < unitIds.length && !unitIds[i].equals(""); i++)
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info("Initialising unit: [" + unitIds[i] + "]");
			}
			if (systemIds.length <= i || systemIds[i].equals(""))
			{
				LOG.error("Cannot initialise unit " + unitIds[i] + " due to unspecified system id");
				break;
			}
			if (userIds.length <= i || userIds[i].equals(""))
			{
				LOG.error("Cannot initialise unit " + unitIds[i] + " due to unspecified user id");
				break;
			}
			if (passwords.length <= i || passwords[i].equals(""))
			{
				LOG.error("Cannot initialise unit " + unitIds[i] + " due to unspecified password");
				break;
			}
			if (i == 0)
			{
				defaultUnit = unitIds[i].toUpperCase();
				defaultSystem = systemIds[i].toUpperCase();
				LOG.info("The default system/unit is " + defaultSystem + "/" + defaultUnit);
			}
		}
	}
	/**
	 * Login to Equation. Note only 1 system and unit can be used with UXP. These values must come from
	 * equationConfiguration.properties.
	 * 
	 * @param user
	 * @param password
	 * @param passwordType
	 * @param userLocator
	 * 
	 * @return session identifier
	 * @throws Exception
	 */
	public String loginToEquation(String user, String password, String passwordType, String userLocator) throws Exception
	{
		String sessionId = null;
		try
		{
			BFEQCredentials credentials = new BFEQCredentials(user, password, passwordType, userLocator, userLocator);

			// Make sure default system/unit is specified
			String systemId = getDefaultSystem();
			String unitId = getDefaultUnit();
			if (systemId.length() == 0 || unitId.length() == 0)
			{
				LOG.warn("Default system/unit is not specified in the configuration");
				return null;
			}
			EquationUnit equationUnit = EquationCommonContext.getContext().getEquationUnit(defaultSystem, defaultUnit);
			if (equationUnit != null)
			{
				boolean isUXPInstalledInDefaultUnit = equationUnit.isUXPEnhancementInstalled();
				// UXP enhancement not installed
				if (!isUXPInstalledInDefaultUnit)
				{
					LOG.warn("K557 UXP enhancement is not installed in the default system/unit " + systemId + "/" + unitId);
					return null;
				}
				// if unit does not exist then this means unit suspend has occurred.
				// only the administrator can resume the unit from the menu option RBE.
				EquationSystem system = EquationCommonContext.getContext().getEquationSystem(systemId);
				if (system.getUnits().containsKey(unitId))
				{
					EquationUnit unit = system.getUnit(unitId);
					if (unit.isAvailable())
					{
						String equationIseriesProfile = null;
						if (EquationCommonContext.isCASAuthentication())
						{
							equationIseriesProfile = system.getiSeriesUserForCASUser(getDefaultUnit(), user);
						}
						else
						{
							equationIseriesProfile = user.toUpperCase();
						}

						// Session id/user id combination issue when 1 WAS profile used by UI and web services. Web Services should
						// use
						// pooled
						// connections and problem will disappear when pooled connections are used.

						// login into Equation
						sessionId = EquationFunctionContext.getContext().getEqSession(getDefaultSystem(), getDefaultUnit(),
										credentials, "", EquationCommonContext.SESSION_BANKFUSION, equationIseriesProfile);

						FunctionHandler functionHandler = EquationFunctionContext.getContext().getFunctionHandler(sessionId);

						// Generate warning info should be set to true for UXP to enable Maker/Checker. See EquationDesktopContext
						// setup
						// of
						// session.
						FunctionInfo functionInfo = functionHandler.getFhd().getFunctionInfo();
						functionInfo.setGenerateWarningInfo(true);
						functionInfo.setSessionType(EquationCommonContext.SESSION_FULL_DESKTOP);

						// Set UI type to UXP
						EquationLogin thisLogin = EquationCommonContext.getContext().getEquationLogin(sessionId);

						// Sessions started as a BankFusion session are assumed to be using
						// Base64 tokens.
						// As a temporary fix, overwrite the password type and password
						// to allow the plain text password to be retrieved for WebFacing:
						if (EquationFunctionContext.getContext().isEquationAuthentication()
										&& passwordType.equals(EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN))
						{
							thisLogin.setPasswordType(EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN);
							if (password != null)
							{
								thisLogin.setPassword(password);
							}
						}
						thisLogin.setSessionType(EquationCommonContext.SESSION_FULL_DESKTOP);
						thisLogin.setUserInterfaceType(EquationCommonContext.UI_UXP);

						EqInfo equationInfo = new EqInfo(EquationCommonContext.getContext().getEquationUser(sessionId));
						EquationDesktopContext.getContext().getEquationInfos().put(sessionId, equationInfo);
					}
				}
			}
		}
		catch (EQActionErrorException e)
		{
			LOG.warn(e);
		}
		catch (EQException e)
		{
			LOG.warn(e);
			throw new UXException(convertToEquationError(e));
		}

		return sessionId;
	}
	/**
	 * Logout from Equation
	 * 
	 * @param sessionIdentifier
	 *            - the session id
	 * @param logoffBankFusion
	 *            - true to attempt logoff of the BankFusion session
	 */
	public void logoutFromEquation(String sessionIdentifier, boolean logoffBankFusion)
	{
		EquationDesktopContext.getContext().logOffDesktop(sessionIdentifier, "", logoffBankFusion);
	}

	/**
	 * Remove child Function Handler
	 * 
	 * @param sessionIdentifier
	 *            - the session id
	 * @param name
	 *            - the name of the child function handler
	 */
	public void removeFunctionHandler(String sessionIdentifier, String name)
	{
		FunctionHandlerTable table = EquationFunctionContext.getContext().getFunctionHandlerTable(sessionIdentifier);
		if (table != null)
		{
			table.removeChild(name);
		}
	}

	/**
	 * Determine if this is an Equation transaction
	 * 
	 * @param menuType
	 *            - the menu type of the transaction
	 * 
	 * @return true if this is an Equation transaction
	 */
	public boolean isEquationMenuType(String menuType)
	{
		if (menuType == null)
		{
			return false;
		}
		else
		{
			return menuType.equals(MENU_TYPE_EQSERVICE) || menuType.equals(MENU_TYPE_WEBFACING);
		}
	}
	/**
	 * Determine if this is an Equation transaction
	 * 
	 * @param menuType
	 *            - the menu type of the transaction
	 * 
	 * @return true if this is an Equation transaction
	 * @throws EQException
	 */
	public boolean isDefaultUnitAvailable() throws EQException
	{
		EquationSystem system = EquationCommonContext.getContext().getEquationSystem(getDefaultSystem());
		if (!system.getUnits().containsKey(getDefaultUnit()))
		{
			return false;
		}
		EquationUnit unit = system.getUnit(getDefaultUnit());
		return unit.isAvailable();
	}
	/**
	 * Return default unit
	 * 
	 * @return default unit
	 */
	protected String getDefaultUnit()
	{
		return defaultUnit;
	}

	/**
	 * Return default system
	 * 
	 * @return default system
	 */
	protected String getDefaultSystem()
	{
		return defaultSystem;
	}

	/**
	 * return Web Application Name. First the equationConfiguration.properites file will be tried. If this is not set then the
	 * DesktopStartupServlet will be used.
	 * 
	 * @return Web Application Name
	 */
	public String getEquationWebAppName()
	{
		String result = "";

		// BF offline enabled, then load from configuration if specified
		if (EquationCommonContext.isBankFusionInstalled() && FunctionBankFusion.isOfflineEnabled())
		{
			result = EquationCommonContext.getConfigProperty("eq.offline.webAppName").trim();
		}

		// Web name not specified, then load default
		if (result.length() == 0)
		{
			result = DesktopStartupServlet.getProjectContextName();
		}

		if (result.length() == 0)
		{
			throw new UXException("Equation Web Application name has not been set in the equationConfiguration.properties");
		}
		return result;
	}

	/**
	 * Update bankfusion user preferences with Equation user preferences
	 * 
	 * @param sessionIdentifier
	 * @param userPreferences
	 */
	public void updateUserPreferences(String sessionIdentifier, UserPreferenceItem userPreferences)
	{
		String zoneInfoJson = userPreferences.getAdditionalData().get("ZoneInfo");
		Type zoneInfoType = new TypeToken<ArrayList<HashMap<String, String>>>()
		{
		}.getType();
		ArrayList<HashMap<String, String>> bankBranchZoneInfo = (ArrayList<HashMap<String, String>>) UXFactory.getObjectFromJson(
						zoneInfoJson, zoneInfoType);

		for (int i = bankBranchZoneInfo.size() - 1; i > -1; i--)
		{
			HashMap<String, String> item = bankBranchZoneInfo.get(i);
			String id = item.get(EQServicePlugin.ID);
			if ("Bank".equals(id) || "Branch".equals(id))
			{
				bankBranchZoneInfo.remove(i);
			}
		}

		// From BF:
		// [{"value":"","id":"","enableLink":"false","sname":"ConfigureZoneBranchMF"},{"value":"Head Office","id":"","enableLink":"false","sname":"ConfigureZoneBranchMF"}]
		bankBranchZoneInfo.clear();

		EquationUser eqUser = EquationCommonContext.getContext().getEquationUser(sessionIdentifier);
		HashMap<String, String> items = new HashMap<String, String>();

		items.put(EQServicePlugin.ID, "Bank");
		items.put(EQServicePlugin.VALUE, eqUser.getEquationUnit().getSystemOption("$BNKLN"));
		// items.put(SERVICE_NAME, EMPTY_STRING);
		bankBranchZoneInfo.add(items);

		items = new HashMap<String, String>();
		items.put(EQServicePlugin.ID, "Branch");
		items.put(EQServicePlugin.VALUE, eqUser.getInputBranch());
		// items.put(SERVICE_NAME, EMPTY_STRING);
		bankBranchZoneInfo.add(items);

		// Replace Zone info:
		userPreferences.setAdditionalData("ZoneInfo", UXFactory.getJsonString(bankBranchZoneInfo));

		// Set UX Desktop Business Date from Equation processing date:
		int cyymmddDate = eqUser.getEquationUnit().getProcessingDateCYYMMDD();

		SimpleDateFormat userDateFormat = ISO8601FORMAT;
		try
		{
			// Try to use user specific date format:
			userDateFormat = new SimpleDateFormat(userPreferences.getDateFormat());
		}
		catch (Exception e)
		{
			LOG.error("Invalid user date format [" + userPreferences.getDateFormat() + "]");
		}
		// Note that this only caters for Gregorian calendar. See BankFusion
		// DateFormatUtility class for Jalali/Shamsi calendar support
		Calendar calendar = Toolbox.parseEqDate(Integer.toString(cyymmddDate));
		userPreferences.setAdditionalData("dt", userDateFormat.format(calendar.getTime()));
	}

	/**
	 * Builds a tree of menu items
	 * 
	 * @param sessionIdentifier
	 * @param state
	 * @return MenuItem
	 * 
	 * @throws Exception
	 */
	public MenuItem getUserMenuTree(String sessionIdentifier, int state) throws Exception
	{
		MenuItem result = null;
		boolean active = true; // Default that items are active

		// Get EquationsUser
		EquationUser eqUser = EquationCommonContext.getContext().getEquationUser(sessionIdentifier);
		if (eqUser == null)
		{
			return null;
		}

		Hashtable<String, String[]> options = new Hashtable<String, String[]>();
		EquationUnit unit = eqUser.getEquationUnit();
		String userId = eqUser.getUserId();

		// Get the connection from the context
		AbstractEquationSessionPool eqSessionPool = unit.getEquationSessionPool();
		Connection equationConnection = eqSessionPool.getConnection(eqUser.getUserId());

		// Method variables, hopefully go out of scope when method finished
		List<String> userTopMenus = new ArrayList<String>();
		List<String> allTopMenus = new ArrayList<String>();
		List<String> controlTopMenus = new ArrayList<String>();
		List<String> allSubMenus = new ArrayList<String>();
		Hashtable<String, String[]> menus = new Hashtable<String, String[]>();

		// Set up sql objects for finally clause
		PreparedStatement menuStatement = null;
		ResultSet menuOptionsRS = null;

		PreparedStatement userMenuStatement = null;
		ResultSet userMenuRS = null;

		PreparedStatement optionStatement = null;
		ResultSet optionsRS = null;

		/***************************************************************************************************************************
		 * First up let's find out what all the menus in the system are...
		 **************************************************************************************************************************/
		try
		{
			String menuSqlString = "SELECT GCOID, GCONM, GCOID1, GCOID2, GCOID3, GCOID4, GCOID5, GCOID6, GCOID7, GCOID8, GCOID9 FROM GCPF,GDPF WHERE GCOID=GDOID AND GDUSID=?";
			menuStatement = equationConnection.prepareStatement(menuSqlString, ResultSet.TYPE_FORWARD_ONLY,
							ResultSet.CONCUR_READ_ONLY);
			String userId4 = Toolbox.trim(userId, 4);
			menuStatement.setString(1, Toolbox.removeSQLChars(userId4));
			menuOptionsRS = menuStatement.executeQuery();
			// Loop the menu records and pop them into an array that loads into a hash...
			while (menuOptionsRS.next())
			{
				String[] gcRecord = new String[10];
				// The first element will be the description
				// gcRecord[0] = menuOptionsRS.getString(2).trim().replaceAll("[ ]", "&nbsp;");
				gcRecord[0] = menuOptionsRS.getString(2).trim();
				// The remaining 9 elements will be the submenus
				for (int j = 3; j <= 11; j++)
				{
					gcRecord[j - 2] = menuOptionsRS.getString(j);
					// also store all the subMenus as we need it later to find the top menus
					allSubMenus.add(menuOptionsRS.getString(j));
				}
				menus.put(menuOptionsRS.getString(1), gcRecord);
			}
			/***********************************************************************************************************************
			 * Now find out which menus are top of the pops...
			 **********************************************************************************************************************/
			Enumeration<String> menusEnumeration = menus.keys();
			while (menusEnumeration.hasMoreElements())
			{
				String currentMenu = menusEnumeration.nextElement();
				if (!allSubMenus.contains(currentMenu))
				{
					allTopMenus.add(currentMenu);
				}
			}
			/***********************************************************************************************************************
			 * Now we need to find out what the user's top option is...
			 **********************************************************************************************************************/
			String userMenuSqlString = "SELECT OCOID FROM OCPF WHERE OCUSID=?";
			userMenuStatement = equationConnection.prepareStatement(userMenuSqlString, ResultSet.TYPE_FORWARD_ONLY,
							ResultSet.CONCUR_READ_ONLY);
			userMenuStatement.setString(1, Toolbox.removeSQLChars(userId4));
			userMenuRS = userMenuStatement.executeQuery();
			// Loop the menus and pop them into an array that loads into a hash...
			if (userMenuRS.next())
			{
				userTopMenus.add(userMenuRS.getString(1));
				// remove this one from the full list..
				allTopMenus.remove(userMenuRS.getString(1));
			}

			/***********************************************************************************************************************
			 * Next up let's find out which options in the system we are authorised to...
			 **********************************************************************************************************************/
			String optionSqlString = "SELECT GBOID, GBONM, GBFPR FROM GBPF, GDPF WHERE GBOID=GDOID AND GDUSID=?";
			optionStatement = equationConnection.prepareStatement(optionSqlString, ResultSet.TYPE_FORWARD_ONLY,
							ResultSet.CONCUR_READ_ONLY);
			optionStatement.setString(1, Toolbox.removeSQLChars(userId4));
			optionsRS = optionStatement.executeQuery();
			// Loop the menus and pop them into an array that loads into a hash...

			while (optionsRS.next())
			{
				String[] gbRecord = new String[2];
				for (int j = 2; j <= 3; j++)
				{
					gbRecord[j - 2] = optionsRS.getString(j).trim();
				}
				options.put(optionsRS.getString(1), gbRecord);

			}

			// Add infrastructure menu options to user options
			EqMenu.adjustInfrastructureMenuOptions(eqUser, options, true);
			/***********************************************************************************************************************
			 * Now hard code the control menu to be EC
			 **********************************************************************************************************************/
			controlTopMenus.add("EC ");
			// remove this one from the full list..
			allTopMenus.remove("EC ");
			/***********************************************************************************************************************
			 * Sort the menus
			 **********************************************************************************************************************/
			Collections.sort(allTopMenus);
			/***********************************************************************************************************************
			 * Now build up the menuHTML
			 **********************************************************************************************************************/
			// userMenuHTML = getMenuTreeHTML(userTopMenus, menus, options);
			// fullMenuHTML = getMenuTreeHTML(allTopMenus, menus, options);
			// controlMenuHTML = getMenuTreeHTML(controlTopMenus, menus, options);

			result = createNewMenuItem("folder", "Equation", "Equation", true, state);
			ArrayList<Object> eqMenus = new ArrayList<Object>();

			Set<String> currentOptions = new HashSet<String>();
			MenuItem userMenu = getMenuTree(userTopMenus, menus, options, currentOptions, active, state);
			if (userMenu != null)
			{
				eqMenus.add(userMenu);
			}
			LOG.info("Total authorised option [" + options.size() + "] menu options [" + currentOptions.size() + "]");

			// Options not in a tree...
			// MenuItem unassignedOptions = createNewMenuItem("folder", "EQ Unassigned", "EQ Unassigned");
			ArrayList<Object> unassigned = new ArrayList<Object>();
			for (String optionId : options.keySet())
			{
				if (!currentOptions.contains(optionId))
				{
					currentOptions.add(optionId); // Avoid duplicates
					String program = options.get(optionId)[1];
					String type = program.startsWith("W90") ? MENU_TYPE_EQSERVICE : MENU_TYPE_WEBFACING;
					unassigned.add(createNewMenuItem(type, optionId, optionId + MENU_ITEM_SEPARATOR + options.get(optionId)[0],
									active, state));
				}
			}
			if (unassigned.size() > 0)
			{
				MenuItem unassignedMenu = createNewMenuItem("folder", "EQ Unassigned", "EQ Unassigned", active, state);
				unassignedMenu.addChildren(unassigned);
				eqMenus.add(unassignedMenu);
			}
			result.addChildren(eqMenus);

		}
		catch (SQLException sqle)
		{
			throw new EQException("EqMenu - Constructor failed (SQL)", sqle);
		}
		finally
		{
			SQLToolbox.close(menuOptionsRS);
			SQLToolbox.close(menuStatement);
			SQLToolbox.close(userMenuRS);
			SQLToolbox.close(userMenuStatement);
			SQLToolbox.close(optionsRS);
			SQLToolbox.close(optionStatement);
			eqSessionPool.returnConnnection(equationConnection);
		}

		return result;
	}

	/**
	 * Builds a tree of menu items with only the resume unit option
	 * 
	 * @param user
	 *            identifier
	 * @param state
	 * @return MenuItem
	 * 
	 * @throws Exception
	 */
	protected MenuItem getUserMenuTreeWithResumeDesktop(String userId, int state) throws Exception
	{
		MenuItem result = null;

		retrieveDefaultDetails();
		EquationSystem system = EquationCommonContext.getContext().getEquationSystem(getDefaultSystem());
		String equationIseriesProfile = null;
		EquationCommonContext.getContext();
		if (EquationCommonContext.isCASAuthentication())
		{
			equationIseriesProfile = system.getiSeriesUserForCASUser(getDefaultUnit(), userId);
		}
		else
		{
			equationIseriesProfile = userId;
		}
		String description = system.getMenuOptionDescription(getDefaultUnit(), equationIseriesProfile, "RBE");
		if (description != null)
		{
			result = createNewMenuItem("folder", "Equation", "Equation", true, state);

			ArrayList<Object> eqMenus = new ArrayList<Object>();
			boolean active = true; // Default that items are active
			String optionId = "RBE";
			// Options not in a tree...
			// MenuItem unassignedOptions = createNewMenuItem("folder", "EQ Unassigned", "EQ Unassigned");
			ArrayList<Object> unassigned = new ArrayList<Object>();

			String type = MENU_TYPE_EQSERVICE;
			unassigned.add(createNewMenuItem(type, optionId, optionId + MENU_ITEM_SEPARATOR + description, active, state));

			MenuItem unassignedMenu = createNewMenuItem("folder", "EQ Unassigned", "EQ Unassigned", active, state);
			unassignedMenu.addChildren(unassigned);
			eqMenus.add(unassignedMenu);

			result.addChildren(eqMenus);
		}

		return result;
	}
	private MenuItem getMenuTree(List<String> topMenus, Hashtable<String, String[]> menus, Hashtable<String, String[]> options,
					Set<String> currentOptions, boolean active, int state)
	{
		MenuItem result = null;
		Iterator<String> menuIterator = topMenus.iterator();
		// Loop the menus banging in the sub menus...
		while (menuIterator.hasNext())
		{
			String topMenu = menuIterator.next(); // Should be only one!
			if (menus.get(topMenu) != null) // May not be authorised
			{
				result = createNewMenuItem("folder", topMenu, topMenu + MENU_ITEM_SEPARATOR + menus.get(topMenu)[0], active, state);
				currentOptions.clear();
				appendMenu(topMenu, result, menus, options, currentOptions, active, state);
			}
		}
		return result;
	}

	private void appendMenu(String menu, MenuItem parentMenu, Hashtable<String, String[]> menus,
					Hashtable<String, String[]> options, Set<String> currentOptions, boolean active, int state)
	{

		if (menus.containsKey(menu))
		{
			currentOptions.add(menu);
		}
		else
		{
			return;
		}
		// }

		ArrayList<Object> childrenToAdd = new ArrayList<Object>();

		// loop options 1 to 9
		for (int j = 1; j <= 9; j++)
		{
			// Need to see if we have another menu or an option
			String currentOption = (menus.get(menu))[j];
			// only interested if it isn't blank...and unique!
			if (!currentOption.equals("   "))
			{
				if (currentOptions.contains(currentOption))
				{
					LOG.debug(" Duplicate option found : " + currentOption);
				}
				else
				{
					// If we have a menu then recurse...
					if (menus.containsKey(currentOption))
					{
						String optionDescription = menus.get(currentOption)[0];

						MenuItem child = createNewMenuItem("folder", currentOption, currentOption + MENU_ITEM_SEPARATOR
										+ optionDescription, active, state);
						childrenToAdd.add(child);
						currentOptions.add(currentOption);
						appendMenu(currentOption, child, menus, options, currentOptions, active, state);

					}
					// If we have a bona-fide option.
					else if (options.containsKey(currentOption))
					{
						String optionDescription = options.get(currentOption)[0];
						String program = options.get(currentOption)[1];
						String type = program.startsWith("W90") ? MENU_TYPE_EQSERVICE : MENU_TYPE_WEBFACING;
						MenuItem child = createNewMenuItem(type, currentOption, currentOption + MENU_ITEM_SEPARATOR
										+ optionDescription, active, state);
						currentOptions.add(currentOption);
						childrenToAdd.add(child);
					}
				}
			}
		}
		// if (childrenToAdd.size() > 0)
		// {
		parentMenu.addChildren(childrenToAdd);
		// }
	}

	/**
	 * Helper method to create a new Item
	 * 
	 * @param type
	 * @param id
	 * @param name
	 * @param active
	 * @param state
	 * @return MenuItem - The newly created MenuItem
	 */
	private MenuItem createNewMenuItem(String type, String id, String name, boolean active, int state)
	{
		MenuItem result = new MenuItem();
		result.setType(type);
		result.setId(id.trim());
		result.setName(name);
		result.setActive(active);
		result.setState(state);
		return result;
	}

	/**
	 * Reconnect to BFEQ
	 * 
	 * @param equationLogin
	 *            - the EquationLogin to reconnect
	 * 
	 * @throws Exception
	 */
	public void reConnectBFEQ(EquationLogin equationLogin) throws Exception
	{
		EquationUnit equationUnit = EquationCommonContext.getContext().getEquationUnit(equationLogin.getSystem(),
						equationLogin.getUnitId());

		String userId = equationLogin.getUserId();

		EquationStandardSession adminSession = equationUnit.getEquationSessionPool().getEquationStandardSession();
		String utr58rPassword = userId.equals(adminSession.getUserId()) ? "" : "*NOPWDCHK";
		byte[] userTokenBytes = adminSession.getUserToken(userId, utr58rPassword, "2");
		String tokenString = Toolbox.cvtBytesToHexString(userTokenBytes);

		String bfUserId = userId;
		if (EquationCommonContext.isCASAuthentication())
		{
			bfUserId = EquationFunctionContext.getContext().getCASUserIdForEquationUser(equationUnit, userId);
		}

		loginToEquation(bfUserId, tokenString, EquationCommonContext.PASSWORD_TYPE_PROFILETOKEN_PLAIN, equationLogin.getSessionId());
	}

	/**
	 * When an error is generated from Equation, append KSM7340 in order to let user knows that it is an Equation message, instead
	 * of an BankFusion/UXP message
	 * 
	 * @param e
	 *            - the error
	 * 
	 * @return the message
	 */
	private String convertToEquationError(Throwable e)
	{
		String message = e.getMessage().trim();
		if (!message.startsWith("KSM"))
		{
			message = "KSM7340 " + message;
		}
		return message;
	}

	/**
	 * Initialise the units. Note only 1 system and unit can be used with UXP.
	 */
	public void retrieveDefaultDetails()
	{
		if (defaultedAlready)
		{
			return;
		}

		// LOG.info("Starting Desktop");
		String[] unitIds = EquationCommonContext.getConfigProperty("eq.units").split(",");
		String[] systemIds = EquationCommonContext.getConfigProperty("eq.systems").split(",");

		// setup unit id
		if (unitIds.length > 0)
		{
			defaultUnit = unitIds[0].toUpperCase();
		}

		// setup system id
		if (unitIds.length > 0)
		{
			defaultSystem = systemIds[0].toUpperCase();
		}

		defaultedAlready = true;
	}

	/**
	 * Retrieves the UXP poll interval This will return the value specified in the eq.uxp.pollInterval setting in the
	 * equationConfiguration.properites file. If not specified, or an invalid value (e.g. non numeric), 0 will be returned to
	 * disable to polling.
	 * 
	 * @return referral poll interval (in seconds)
	 */
	public int getUXPPollInterval()
	{
		String value = EquationCommonContext.getConfigProperty("eq.uxp.browserPollInterval").trim();
		return Toolbox.parseInt(value, 0);
	}
	/**
	 * Process Equation Refresh Cache command
	 * 
	 * @param sessionIdentifier
	 *            the session identifier
	 * @throws EQException
	 * @throws Exception
	 */
	protected void refreshCache(String sessionIdentifier) throws EQException
	{
		EquationFunctionContext.getContext().refreshCache(sessionIdentifier);
	}
	/**
	 * Initialise the default unit
	 * 
	 * @throws Exception
	 */
	protected void initialiseDefaultUnit() throws EQException
	{
		EquationDesktopContext.getContext().initialiseUnit(getDefaultSystem(), getDefaultUnit());
	}
	/**
	 * Destroy the default unit
	 * 
	 * @throws EQException
	 */
	protected void destroyDefaultUnit() throws EQException
	{
		EquationDesktopContext.getContext().destroyUnit(getDefaultSystem(), getDefaultUnit());

	}
}
