package com.misys.equation.common.globalprocessing;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.CharacterDataArea;
import com.misys.equation.common.access.EquationConfigurationPropertiesBean;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.UnitPropertiesBean;
import com.misys.equation.common.language.LanguageResources;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;

public class GlobalProcessingToolBox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalProcessingToolBox.java 9555 2010-10-20 16:27:55Z MACDONP1 $";
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(GlobalProcessingToolBox.class);

	public static List<String> getListOfUnitsAndDescriptions(EquationUser eqUser)
	{
		List<String> listOfUnits = new ArrayList<String>();
		Collection<UnitPropertiesBean> units = EquationConfigurationPropertiesBean.getInstance().getUnitsList();

		for (UnitPropertiesBean unit : units)
		{
			try
			{
				listOfUnits.add(unit.getSystemId() + " " + unit.getUnitId() + " " + unit.getUnitDescription());
			}
			catch (Exception e)
			{
				LOG.error("There is a problem creating Global Processing sessions: " + Toolbox.getExceptionMessage(e), e);
			}
		}
		return listOfUnits;
	}

	/**
	 * Given a user this method will return all the units that can be accessed by the user.
	 * 
	 * @param system
	 * @param eqUser
	 * @return
	 */
	public static String getUnits(final EquationSystem system, final EquationUser eqUser)
	{
		final StringBuffer resultingUnits = new StringBuffer();
		// Get the valid units ...
		final String validUnits = eqUser.getValidUnits();
		final String[] validUnitsArr = validUnits.split("!_");

		// Filter the and return only the ones in GPXPF
		Statement statement = null;
		ResultSet rs = null;
		final Set<String> unitsSet = new HashSet<String>();
		try
		{
			statement = eqUser.getSession().getConnectionWrapper().getGlobalConnection()
							.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery("SELECT GPXUNC, GPXSYS FROM GPXPF GROUP BY GPXUNC, GPXSYS");
			while (rs.next())
			{
				unitsSet.add(rs.getString("GPXUNC"));
			}

		}
		catch (Exception e)
		{
			LOG.error(e);
		}
		finally
		{
			SQLToolbox.close(rs);
			SQLToolbox.close(statement);
		}

		// Compare and filter only the ones which are in both sets ...
		for (String unit : validUnitsArr)
		{
			if (unitsSet.contains(unit))
			{
				resultingUnits.append(unit);
				resultingUnits.append("!_");
			}
		}

		// finally return the set of units to be shown ...
		return resultingUnits.toString();
	}

	public static String getCharacterDataArea(AS400 eqAS400, String lib, String dataArea, String unitId)
	{
		try
		{
			String library = lib + unitId;
			CharacterDataArea charDataArea = new CharacterDataArea(eqAS400, "/QSYS.LIB/" + library + ".LIB/" + dataArea + ".DTAARA");

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
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * Input Mode · KQIMODE – if set to SUSP display ‘Suspended’
	 * 
	 * KQISTAT – if set to YNNN display ‘Normal’
	 * 
	 * KQISTAT – if set to YYYN display ‘Extended’
	 * 
	 * KQISTAT – otherwise display ‘Disabled’
	 **/
	public static String getInputMode(AS400 eqAS400, String unitID)
	{
		try
		{
			String kqimodeStr = getCharacterDataArea(eqAS400, "KLIB", "KQIMODE", unitID);

			if (kqimodeStr.equals("SUSP"))
			{
				return LanguageResources.getString("Language.Suspended");
			}
			else
			{
				String kqistatStr = getCharacterDataArea(eqAS400, "KLIB", "KQISTAT", unitID);
				if (kqistatStr.equals("YNNN"))
				{
					return LanguageResources.getString("Language.Normal");
				}
				else if (kqistatStr.equals("YYYN"))
				{
					return LanguageResources.getString("Language.Extended");
				}
			}
			return LanguageResources.getString("Language.Disabled");
		}
		finally
		{
			eqAS400.disconnectAllServices();
		}
	}

	public static boolean isInNightMode(AS400 eqAS400, String unitID)
	{
		return getInputMode(eqAS400, unitID).equals(LanguageResources.getString("Language.Extended"));
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static String[] getDataAreaValuesFromUnit(final String key, final List<EquationStandardSession> sessions)
	{
		final String[] values = new String[sessions.size()];
		// Loop through all the units ...
		for (int i = 0; i < sessions.size(); i++)
		{
			values[i] = sessions.get(i).getUnit().getSystemOption(key);
		}
		return values;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static Map<String, Long> getProcessingDatesByUnit(final String key, List<EquationStandardSession> sessions)
	{
		final Map<String, Long> values = new HashMap<String, Long>();
		// Loop through all the units ...
		for (EquationStandardSession session : sessions)
		{
			values.put(session.getUnit().getUnitId(), Long.parseLong(session.getUnit().getSystemOption(key)));
		}
		return values;
	}
}