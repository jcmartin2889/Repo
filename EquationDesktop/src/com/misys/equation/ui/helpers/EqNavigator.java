package com.misys.equation.ui.helpers;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Properties;

import com.misys.equation.common.access.AbstractEquationSessionPool;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.globalprocessing.GPSearch;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.search.results.ISelectedSearchResult;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.ui.beans.EqMenu;
import com.misys.equation.ui.tools.EqDesktopToolBox;

public class EqNavigator implements Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqNavigator.java 13842 2012-07-19 03:20:35Z mamiguel $";
	private static final long serialVersionUID = 1L;

	// Get the logger class ...
	private static final EquationLogger LOG = new EquationLogger(EqNavigator.class);

	private String unitId;
	private String userId;
	private EquationUser user;
	private EquationUnit unit;
	private String sessionIdentifier;
	private boolean includeMnemonics;

	/**
	 * Construct an empty navigator
	 */
	public EqNavigator()
	{
	}

	/**
	 * Construct an navigator with user details
	 * 
	 * @param user
	 */
	public EqNavigator(final String sessionIdentifier, EquationUser user)
	{
		this.sessionIdentifier = sessionIdentifier;
		this.user = user;
		unit = user.getEquationUnit();
		unitId = unit.getUnitId();
		userId = user.getUserId();
	}

	/**
	 * Construct an navigator with user details
	 * 
	 * @param user
	 */
	public EqNavigator(EquationUser user)
	{
		this.user = user;
		unit = user.getEquationUnit();
		unitId = unit.getUnitId();
		userId = user.getUserId();
	}

	/**
	 * Return the menu of the user
	 * 
	 * @return the menu of the user
	 * 
	 * @throws EQException
	 */
	public EqMenu getEqMenu() throws EQException
	{
		return new EqMenu(user);
	}

	public String getRecentSearches()
	{
		final StringBuffer strHTML = new StringBuffer();

		// Get a list of slot headers (date periods) to store the results in ...
		final String[] strSlotName = EqDesktopToolBox.getSlotName(EquationCommonContext.getContext()
						.getEquationDesktopLanguageResources(), user);
		int[] nArrSlot = new int[strSlotName.length];

		// Create and Initialise slot
		final StringBuffer[] strSlot = new StringBuffer[strSlotName.length];
		for (int i = 0; i < strSlot.length; i++)
		{
			strSlot[i] = new StringBuffer();
		}

		try
		{
			// Retrieve the recent search items for this user ...
			final Collection<ISelectedSearchResult> searchResults = new GPSearch()
							.retrieveRecentSelectedSearches(sessionIdentifier);

			boolean empty = true;
			final String id = "RecentSelections";

			if (searchResults.size() > 0)
			{
				final Iterator<ISelectedSearchResult> iSearchCriteria = searchResults.iterator();
				while (iSearchCriteria.hasNext())
				{
					// Get the next search results ...
					ISelectedSearchResult selectedSearchResult = iSearchCriteria.next();

					// Get a slot for the current date ...
					final GregorianCalendar now = new GregorianCalendar();
					final GregorianCalendar date = new GregorianCalendar();
					date.setTime(selectedSearchResult.getTimeStamp());
					int slot = EqDesktopToolBox.getSlot(now, date);

					// Append the HTML string to the slot ...
					strSlot[slot].append(EqDesktopToolBox.formatLI(id, EqDesktopToolBox.formatIntoAnchorHTML(selectedSearchResult
									.getSelectedResultKeyString(), EquationCommonContext.getContext().getLanguageResource(user,
									"GBLRECENT"), selectedSearchResult.getSelectedResultLink(), "messageStyle")));
					nArrSlot[slot]++;
				}

				for (int i = 0; i < strSlot.length; i++)
				{
					if (strSlot[i].length() > 0)
					{
						String text = "";
						text = strSlotName[i] + " (" + String.valueOf(nArrSlot[i]) + ")";
						String ul = EqDesktopToolBox.formatUL(id + "_ul_" + i, strSlot[i].toString());
						String li = EqDesktopToolBox.formatLI(id + "_li_" + i, text + ul);
						strHTML.append(li);
						empty = false;
					}
				}
			}
			// Else we do noting ...

			if (empty)
			{
				strHTML.append(EqDesktopToolBox.getLanguageText(EquationCommonContext.getContext()
								.getEquationDesktopLanguageResources(), user.getLanguageId(), "search.recentselections"));
			}

		}
		catch (EQException e)
		{
			// Not much to do here ...
			LOG.error("ERROR: EqNavigator.getRecentSearches()", e);
		}
		catch (Exception e)
		{
			// Not much to do here ...
			LOG.error("ERROR: EqNavigator.getRecentSearches()", e);
		}

		// finally return the content ...
		return strHTML.toString();
	}

	/**
	 * Return the recent option taken by the user in HTML format
	 * 
	 * @return the recent option taken by the user in HTML format
	 */
	public String getRecentOptionHTML()
	{
		StringBuffer recentOptionHTML = new StringBuffer();
		PreparedStatement recentOptionStatement = null;
		ResultSet recentOptionRS = null;
		AbstractEquationSessionPool eqSessionPool = null;
		Connection equationConnection = null;

		String includeMnemonicsString = EquationCommonContext.getConfigProperty("eq.navigator.includemnemonics");
		includeMnemonics = includeMnemonicsString.equalsIgnoreCase("true");
		
		try
		{
			// Get the connection from the context
			eqSessionPool = unit.getEquationSessionPool();
			equationConnection = eqSessionPool.getConnection(user.getUserId());
			Properties eqProperties = EquationCommonContext.getContext().getEquationDesktopLanguageResources();
			String languageId = user.getLanguageId();
			boolean rtl = user.isLanguageRTL();
			String userId4 = Toolbox.trim(userId, 4);
			/***********************************************************************************************************************
			 * First up let's find out what all the menus in the system are...
			 **********************************************************************************************************************/
			String recentOptionSqlString = "SELECT GYOID,GBONM,COUNT(GYOID) AS X FROM GYPF, GBPF WHERE GYUSID=? AND GYOID=GBOID GROUP BY GYOID, GBONM ";
			recentOptionSqlString += " UNION ";
			recentOptionSqlString += "SELECT G5OID as GYOID,GBONM,COUNT(G5OID) AS X FROM G5PF, GBPF WHERE G5USID=? AND G5OID=GBOID GROUP BY G5OID, GBONM ";
			recentOptionSqlString += " ORDER BY X DESC";
			recentOptionStatement = equationConnection.prepareStatement(recentOptionSqlString, ResultSet.TYPE_FORWARD_ONLY,
							ResultSet.CONCUR_READ_ONLY);
			recentOptionStatement.setString(1, Toolbox.removeSQLChars(userId4));
			recentOptionStatement.setString(2, Toolbox.removeSQLChars(userId4));
			recentOptionRS = recentOptionStatement.executeQuery();
			// Loop the menu records and pop them into an array that loads into a hash...
			boolean empty = true;
			while (recentOptionRS.next())
			{
				empty = false;
				
				if (includeMnemonics)
				{
					if (rtl)
					{
						recentOptionHTML.append("<li> " + "<A href=\"javascript:routeToOption2('*D','" + unitId + "','"
										+ recentOptionRS.getString("GYOID") + "','')\" title=\""
										+ recentOptionRS.getString("GYOID") + "\" dir=\"ltr\">" + "(" + recentOptionRS.getInt("X")
										+ ") "
										+ recentOptionRS.getString("GBONM") + "&nbsp;" + recentOptionRS.getString("GYOID")
										+ "</A> " + "</li>");
					}
					else
					{
						recentOptionHTML.append("<li> " + "<A href=\"javascript:routeToOption2('*D','" + unitId + "','"
										+ recentOptionRS.getString("GYOID") + "','')\" title=\""
										+ recentOptionRS.getString("GYOID") + "\">" + recentOptionRS.getString("GYOID") + "&nbsp;"
										+ recentOptionRS.getString("GBONM") + " (" + recentOptionRS.getInt("X") + ")" + "</A> "
										+ "</li>");
					}
				}
				else
				{
					if (rtl)
					{
						recentOptionHTML.append("<li> " + "<A href=\"javascript:routeToOption2('*D','" + unitId + "','"
										+ recentOptionRS.getString("GYOID") + "','')\" title=\""
										+ recentOptionRS.getString("GYOID") + "\" dir=\"ltr\">" + "(" + recentOptionRS.getInt("X")
										+ ") "
										+ recentOptionRS.getString("GBONM") + "</A> " + "</li>");
					}
					else
					{
						recentOptionHTML.append("<li> " + "<A href=\"javascript:routeToOption2('*D','" + unitId + "','"
										+ recentOptionRS.getString("GYOID") + "','')\" title=\""
										+ recentOptionRS.getString("GYOID") + "\">" + recentOptionRS.getString("GBONM") + " ("
										+ recentOptionRS.getInt("X") + ")" + "</A> " + "</li>");
					}
				}
			}
			if (empty)
			{
				recentOptionHTML.append(EquationCommonContext.getContext().getLanguageResource(user, "GBL900023"));
			}
		}
		catch (NumberFormatException e)
		{
			EquationCommonContext.getContext().getLOG().error(e);
			recentOptionHTML.append("Error reading recent option files - check CCSIDs of GYPF and GBPF");
		}
		catch (Exception e)
		{
			EquationCommonContext.getContext().getLOG().error(e);
			recentOptionHTML.append(Toolbox.getExceptionMessage(e));
		}

		finally
		{
			// Close the result set, statement and return the connection
			SQLToolbox.close(recentOptionRS);
			SQLToolbox.close(recentOptionStatement);
			try
			{
				eqSessionPool.returnConnnection(equationConnection);
			}
			catch (Exception e)
			{
				LOG.error(e);
			}
		}
		return recentOptionHTML.toString();
	}
}
