package com.misys.equation.ui.beans;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;

import com.misys.equation.common.access.AbstractEquationSessionPool;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;

public class EqMenu
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqMenu.java 17481 2013-10-25 21:32:19Z williae1 $";
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EqMenu.class);

	private static final String INFRASTRUCTURE_SERVICES_PROPERTY_FILE = "infrastructureServices.properties";
	private static ArrayList<String> infrastructureServices = new ArrayList<String>();

	// these need getters and setters...
	private String userMenuHTML;
	private String fullMenuHTML;
	private String controlMenuHTML;
	private String unitId;
	private String userId;
	private boolean authUnitSpool;
	private boolean authSuspED;
	private boolean authSystemStatus;
	private boolean authJoblog;
	private boolean authRefreshCache;
	private final Hashtable<String, String[]> options = new Hashtable<String, String[]>();
	private EquationUser user;
	private EquationUnit unit;
	private boolean includeMnemonics;

	static
	{
		// Determine the infrastructure services from property file in EquationDesktop project
		URL resourceURL = null;
		String systemPropertyValue = null;

		// The property is named after the property file.
		systemPropertyValue = System.getProperty(INFRASTRUCTURE_SERVICES_PROPERTY_FILE);

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
			resourceURL = Thread.currentThread().getContextClassLoader().getResource(INFRASTRUCTURE_SERVICES_PROPERTY_FILE);
		}

		// Need to check if we can load the properties file for the given key name
		InputStream propertiesIS;
		try
		{
			propertiesIS = resourceURL.openStream();

			if (propertiesIS != null)
			{
				Properties properties = new Properties();

				try
				{
					properties.load(propertiesIS);
				}
				catch (IOException e)
				{
					LOG.error(e);
				}

				for (Entry<Object, Object> propertyName : properties.entrySet())
				{
					infrastructureServices.add(((String) propertyName.getKey()).trim());
				}
			}
		}
		catch (IOException e1)
		{
			LOG.error(e1);
		}

	}
	/*
	 * Constructor for reflection of the bean
	 */
	public EqMenu()
	{
	}
	/*
	 * Constructor
	 */
	public EqMenu(EquationUser user) throws EQException
	{
		this.user = user;
		unit = user.getEquationUnit();
		unitId = unit.getUnitId();
		userId = user.getUserId();
		// Get the connection from the context
		AbstractEquationSessionPool eqSessionPool = unit.getEquationSessionPool();
		Connection equationConnection = eqSessionPool.getConnection(user.getUserId());

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

		String includeMnemonicsString = EquationCommonContext.getConfigProperty("eq.navigator.includemnemonics");
		includeMnemonics = includeMnemonicsString.equalsIgnoreCase("true");

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
				gcRecord[0] = menuOptionsRS.getString(2).trim().replaceAll("[ ]", "&nbsp;");
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
			String optionSqlString = "SELECT GBOID, GBONM FROM GBPF, GDPF WHERE GBOID=GDOID AND GDUSID=?";
			optionStatement = equationConnection.prepareStatement(optionSqlString, ResultSet.TYPE_FORWARD_ONLY,
							ResultSet.CONCUR_READ_ONLY);
			optionStatement.setString(1, Toolbox.removeSQLChars(userId4));
			optionsRS = optionStatement.executeQuery();
			// Loop the menus and pop them into an array that loads into a hash...
			authUnitSpool = false;
			authSuspED = false;
			authRefreshCache = false;
			// Check if console customisation is allowed
			String allowConsoleCustomisation = EquationCommonContext.getConfigProperty("eq.AllowConsoleCustomisation");
			if (allowConsoleCustomisation.equals("false"))
			{
				authSystemStatus = true;
				authJoblog = true;
			}
			else
			{
				authSystemStatus = false;
				authJoblog = false;
			}

			while (optionsRS.next())
			{
				String[] gbRecord = new String[1];
				for (int j = 2; j <= 2; j++)
				{
					gbRecord[j - 2] = optionsRS.getString(j).trim();
				}
				options.put(optionsRS.getString(1), gbRecord);
				if (optionsRS.getString(1).equals("SUN"))
				{
					authUnitSpool = true;
				}
				else if (optionsRS.getString(1).equals("SED"))
				{
					authSuspED = true;
				}
				else if (optionsRS.getString(1).equals("SST"))
				{
					authSystemStatus = true;
				}
				else if (optionsRS.getString(1).equals("SJL"))
				{
					authJoblog = true;
				}
				else if (optionsRS.getString(1).equals("DRC"))
				{
					authRefreshCache = true;
				}
			}
			// Add infrastructure menu options to user options
			adjustInfrastructureMenuOptions(user, options, false);

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
			userMenuHTML = getMenuTreeHTML(userTopMenus, menus, options);
			fullMenuHTML = getMenuTreeHTML(allTopMenus, menus, options);
			controlMenuHTML = getMenuTreeHTML(controlTopMenus, menus, options);
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

	}
	/**
	 * Adjust the user options by adding infrastructure services
	 * 
	 * @param user
	 * @param options
	 * @param includeProgram
	 * @throws EQException
	 */

	public static void adjustInfrastructureMenuOptions(EquationUser user, Hashtable<String, String[]> options,
					boolean includeProgram) throws EQException
	{

		EquationUnit unit = user.getEquationUnit();

		// Get the connection from the context
		AbstractEquationSessionPool eqSessionPool = unit.getEquationSessionPool();
		Connection equationConnection = eqSessionPool.getConnection(user.getUserId());

		// Set up sql objects for finally clause
		PreparedStatement optionStatement = null;
		ResultSet optionsRS = null;

		try
		{
			String optionSqlString = "SELECT GBOID, GBONM, GBFPR FROM GBPF WHERE ";

			// Get the menu options for all the infrastructure services mentioned in the EquationDesktop property file
			// infrastructureServices.properties
			for (int i = 0; i < infrastructureServices.size(); i++)
			{
				if (i > 0)
				{
					optionSqlString = optionSqlString + " OR ";
				}
				optionSqlString = optionSqlString + "GBOID='" + infrastructureServices.get(i) + "'";
			}

			optionStatement = equationConnection.prepareStatement(optionSqlString, ResultSet.TYPE_FORWARD_ONLY,
							ResultSet.CONCUR_READ_ONLY);

			optionsRS = optionStatement.executeQuery();
			// Process result set and adjust options...
			while (optionsRS.next())
			{
				String[] gbRecord = null;
				if (includeProgram)
				{
					gbRecord = new String[2];
					for (int j = 2; j <= 3; j++)
					{
						gbRecord[j - 2] = optionsRS.getString(j).trim();
					}
				}
				else
				{
					gbRecord = new String[1];
					for (int j = 2; j <= 2; j++)
					{
						gbRecord[j - 2] = optionsRS.getString(j).trim();
					}
				}
				if (!options.containsKey(optionsRS.getString(1)))
				{
					options.put(optionsRS.getString(1), gbRecord);
					user.toggleAuthorisedOption(optionsRS.getString(1), true);
				}

			}
		}

		catch (SQLException sqle)
		{
			throw new EQException("EqMenu - Constructor failed (SQL)", sqle);
		}
		finally
		{
			SQLToolbox.close(optionsRS);
			SQLToolbox.close(optionStatement);
			eqSessionPool.returnConnnection(equationConnection);
		}

	}
	private String getMenuTreeHTML(List<String> topMenus, Hashtable<String, String[]> menus, Hashtable<String, String[]> options)
	{
		ArrayList<String> currentOptions = new ArrayList<String>();
		StringBuffer menuHTML = new StringBuffer("");
		Properties eqProperties = EquationCommonContext.getContext().getEquationDesktopLanguageResources();
		String languageId = user.getLanguageId();
		Iterator<String> menuIterator = topMenus.iterator();
		// Loop the menus banging in the sub menus...
		boolean empty = true;
		while (menuIterator.hasNext())
		{
			empty = false;
			currentOptions.clear();
			appendMenu(menuIterator.next(), menuHTML, menus, options, currentOptions);
		}
		if (empty)
		{
			menuHTML.append(EquationCommonContext.getContext().getLanguageResource(user, "GBL900024"));
		}

		if (menuHTML.length() == 0)
		{
			menuHTML.append(" ");
		}
		return menuHTML.toString();
	}

	private void appendMenu(String menu, StringBuffer menuHTML, Hashtable<String, String[]> menus,
					Hashtable<String, String[]> options, ArrayList<String> currentOptions)
	{
		boolean rtl = user.isLanguageRTL();
		// If we've already processed this menu then recursion is rejected!
		if (currentOptions.contains(menu))
		{
			return;
		}
		else
		{
			if (menus.containsKey(menu))
			{
				currentOptions.add(menu);
			}
			else
			{
				return;
			}
		}

		if (includeMnemonics)
		{
			if (rtl)
			{
				menuHTML.append("<li  title=\"" + menu.trim() + "\"> " + "<span class=\"menuAndOptionsText\">"
								+ (menus.get(menu))[0].replaceAll("-", "&#8209;") + "&nbsp;" + menu.trim() + "</span>");
			}
			else
			{
				menuHTML.append("<li  title=\"" + menu.trim() + "\"> " + "<span class=\"menuAndOptionsText\">" + menu.trim()
								+ "&nbsp;" + (menus.get(menu))[0].replaceAll("-", "&#8209;") + "</span>");
			}

		}
		else
		{
			menuHTML.append("<li  title=\"" + menu.trim() + "\">" + "<span class=\"menuAndOptionsText\">"
							+ (menus.get(menu))[0].replaceAll("-", "&#8209;") + "</span>");
		}

		menuHTML.append("<ul>");
		// loop options 1 to 9
		for (int j = 1; j <= 9; j++)
		{
			// Need to see if we have another menu or an option
			String currentOption = (menus.get(menu))[j];
			// only interested if it isn't blank...
			if (!currentOption.equals("   "))
			{
				// If we have a menu then loop back round...
				if (menus.containsKey(currentOption))
				{
					appendMenu(currentOption, menuHTML, menus, options, currentOptions);
				}
				// If we have a bona fide option.
				else if (options.containsKey(currentOption))
				{
					String optionMnemoinc = currentOption;
					String optionDescription = (options.get(currentOption))[0];
					if (includeMnemonics)
					{
						if (rtl)
						{
							menuHTML.append("<li id='" + currentOption + "'> " + "<A href=\"javascript:routeToOption2('*D','"
											+ unitId + "','" + optionMnemoinc + "','')\" title=\"" + optionMnemoinc + "\">"
											+ "<span class=\"menuAndOptionsText\">" + optionDescription.replaceAll("-", "&#8209;")
											+ "&nbsp;" + optionMnemoinc + "</span>" + "</A> " + "</li>");
						}
						else
						{
							menuHTML.append("<li id='" + currentOption + "'> " + "<A href=\"javascript:routeToOption2('*D','"
											+ unitId + "','" + optionMnemoinc + "','')\" title=\"" + optionMnemoinc + "\">"
											+ "<span class=\"menuAndOptionsText\">" + optionMnemoinc + "&nbsp;"
											+ optionDescription.replaceAll("-", "&#8209;") + "</span>" + "</A> " + "</li>");
						}
					}
					else
					{
						menuHTML.append("<li id='" + currentOption + "'> " + "<A href=\"javascript:routeToOption2('*D','" + unitId
										+ "','" + optionMnemoinc + "','')\" title=\"" + optionMnemoinc + "\">"
										+ "<span class=\"menuAndOptionsText\">" + optionDescription.replaceAll("-", "&#8209;")
										+ "</span>" + "</A> " + "</li>");
					}

				}
			}
		}
		// Close the tag
		menuHTML.append("</ul>");
		menuHTML.append("</li>");
	}
	/**
	 * @return Returns the controlMenuHTML.
	 */
	public String getControlMenuHTML()
	{
		return controlMenuHTML;
	}
	/**
	 * @param controlMenuHTML
	 *            The controlMenuHTML to set.
	 */
	public void setControlMenuHTML(String controlMenuHTML)
	{
		this.controlMenuHTML = controlMenuHTML;
	}
	/**
	 * @return Returns the fullMenuHTML.
	 */
	public String getFullMenuHTML()
	{
		return fullMenuHTML;
	}
	/**
	 * @param fullMenuHTML
	 *            The fullMenuHTML to set.
	 */
	public void setFullMenuHTML(String fullMenuHTML)
	{
		this.fullMenuHTML = fullMenuHTML;
	}
	/**
	 * @return Returns the userMenuHTML.
	 */
	public String getUserMenuHTML()
	{
		return userMenuHTML;
	}
	/**
	 * @param userMenuHTML
	 *            The userMenuHTML to set.
	 */
	public void setUserMenuHTML(String userMenuHTML)
	{
		this.userMenuHTML = userMenuHTML;
	}
	// Check if user is authorised to the unit spooled file
	public boolean isAuthUnitSpool()
	{
		return authUnitSpool;
	}
	// Check if user is authorised to suspend the Equation Desktop
	public boolean isAuthSuspED()
	{
		return authSuspED;
	}
	// check if user is authorised to system status console
	public boolean isAuthSystemStatus()
	{
		return authSystemStatus;
	}
	// check if user is authorised to joblog console
	public boolean isAuthJoblog()
	{
		return authJoblog;
	}

	/**
	 * Determine if authorise to refresh desktop cache
	 * 
	 * @return true if authorise to refresh desktop cache
	 */
	public boolean isAuthRefreshCache()
	{
		return authRefreshCache;
	}

	// Check if user is authorised to a WMENU1 option
	public boolean isValidOption(String opt)
	{
		return options.containsKey(opt);
	}

}