package com.misys.equation.common.globalprocessing;

import org.apache.commons.lang.StringUtils;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.CharacterDataArea;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.globalprocessing.UnitStatus.InputMode;
import com.misys.equation.common.utilities.EquationLogger;

/**
 * This class is a bean to represent the current phase/mode of an Equation unit
 */
public class UnitStatusHandler
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UnitStatusHandler.java 17622 2013-11-27 02:51:09Z williae1 $";

	private static final EquationLogger LOG = new EquationLogger(UnitStatusHandler.class);

	private final String unitId;
	private InputMode mode;
	private String phase;
	private String kapphs;
	private String today;
	private String tomorrow;
	private String yesterday;
	private String description;
	private String dp0DataArea;
	private String suspendRequest; // to contain the contents of KQISUSR data area

	// 24x7 data-areas
	private String eqn0sdu;
	private String eqn0eds;
	private String eqn0ehp;
	private String eqn0sds;
	private String eqn0shp;
	private String kqiendon;

	private final EquationSystem system;

	/**
	 * Construct a UnitStatus bean
	 * 
	 * @param unitId
	 *            - 3 character unit mnemonic
	 * @param session
	 *            - an EquationStandardSession to the unit
	 */
	public UnitStatusHandler(String unitId, EquationSystem system)
	{
		this.system = system;
		this.unitId = unitId;
		refreshUnitStatus();
	}

	/**
	 * Update the status of the units
	 */
	private void refreshUnitStatus()
	{
		AS400 eqAS400 = null;
		String kqiModeStr = null;
		String kqiStatStr = null;

		try
		{
			eqAS400 = system.getAS400();
			this.phase = GlobalProcessingToolBox.getCharacterDataArea(eqAS400, "KLIB", "KAPPHS", unitId).trim();
			this.today = GlobalProcessingToolBox.getCharacterDataArea(eqAS400, "KFIL", "DASYSCTL", unitId).substring(134, 141)
							.trim();
			this.tomorrow = GlobalProcessingToolBox.getCharacterDataArea(eqAS400, "KFIL", "DASYSCTL", unitId).substring(146, 153)
							.trim();
			this.yesterday = GlobalProcessingToolBox.getCharacterDataArea(eqAS400, "KFIL", "DASYSCTL", unitId).substring(158, 165)
							.trim();
			this.description = GlobalProcessingToolBox.getCharacterDataArea(eqAS400, "KLIB", "KAPUNDES", unitId).trim();

			kqiModeStr = GlobalProcessingToolBox.getCharacterDataArea(eqAS400, "KLIB", "KQIMODE", unitId).trim();
			kqiStatStr = GlobalProcessingToolBox.getCharacterDataArea(eqAS400, "KLIB", "KQISTAT", unitId).trim();
			this.mode = getInputMode(kqiModeStr, kqiStatStr);

			// try read the new data area - this may not exist, so catch exception and set the value to an empty string
			try
			{
				this.suspendRequest = new CharacterDataArea(eqAS400, "/QSYS.LIB/KLIB" + unitId.trim() + ".LIB/KQISUSR.DTAARA")
								.read();
			}
			catch (ObjectDoesNotExistException odnee)
			{
				this.suspendRequest = "";
			}

			try
			{
				// read the suspension state for the data propagation jobs
				this.dp0DataArea = new CharacterDataArea(eqAS400, "/QSYS.LIB/QGPL.LIB/EQGP" + unitId.trim() + "DP0.DTAARA").read();
			}
			catch (ObjectDoesNotExistException odnee)
			{
				// data area not yet created?!?
				this.dp0DataArea = "";
			}

			refreshEodUnitAndPhase(eqAS400);
			refresh247DataAreas(eqAS400);
		}
		catch (Exception ex)
		{
			LOG.error(ex);
		}
		finally
		{
			if (eqAS400 != null)
			{
				system.returnAS400(eqAS400);
			}
		}
	}

	/**
	 * Refresh the end of day phase value from the unit
	 * 
	 * @param eqAS400
	 */
	private void refreshEodUnitAndPhase(AS400 eqAS400)
	{
		String dtaara = GlobalProcessingToolBox.getCharacterDataArea(eqAS400, "KLIB", "EQN0SDU", unitId);
		if (dtaara != null)
		{
			eqn0sdu = StringUtils.substring(dtaara, 3, 6).trim();
			kapphs = "";
			if (!"".equals(eqn0sdu))
			{
				kapphs = StringUtils.substring(GlobalProcessingToolBox.getCharacterDataArea(eqAS400, "KLIB", "KAPPHS", eqn0sdu), 3,
								6).trim();
			}
		}
	}

	private void refresh247DataAreas(AS400 eqAS400)
	{
		eqn0eds = GlobalProcessingToolBox.getCharacterDataArea(eqAS400, "KLIB", "EQN0EDS", unitId);
		eqn0sds = GlobalProcessingToolBox.getCharacterDataArea(eqAS400, "KLIB", "EQN0SDS", unitId);
		eqn0ehp = GlobalProcessingToolBox.getCharacterDataArea(eqAS400, "KLIB", "EQN0EHP", unitId);
		eqn0shp = GlobalProcessingToolBox.getCharacterDataArea(eqAS400, "KLIB", "EQN0HSP", unitId);
		kqiendon = GlobalProcessingToolBox.getCharacterDataArea(eqAS400, "KLIB", "KQIENDON", unitId);
	}

	/**
	 * Input Mode KQIMODE - if set to SUSP display 'Suspended'
	 * 
	 * KQISTAT - if set to YNNN display 'Normal'
	 * 
	 * KQISTAT - if set to YYYN display 'Extended'
	 * 
	 * KQISTAT - otherwise display 'Disabled'
	 **/
	private InputMode getInputMode(String kqimodeStr, String kqistatStr)
	{
		if (kqimodeStr.equals("SUSP"))
		{
			return InputMode.SUSP;
		}
		else
		{
			if (kqistatStr.equals("YNNN"))
			{
				return InputMode.NORM;
			}
			else if (kqistatStr.equals("YYYN"))
			{
				return InputMode.EXTN;
			}
		}
		return InputMode.DISA;
	}

	/**
	 * @return the phase of the unit
	 */
	public String getPhase()
	{
		return phase;
	}

	/**
	 * Set the phase of the unit
	 * 
	 * @param phase
	 *            - the current unit's phase
	 */
	public void setPhase(String phase)
	{
		this.phase = phase;
	}

	/**
	 * @return the mode of the unit
	 * @equation.external
	 */
	public InputMode getMode()
	{
		return mode;
	}

	/**
	 * Set the mode of the unit
	 * 
	 * @param mode
	 *            - the current unit's mode
	 */
	public void setMode(InputMode mode)
	{
		this.mode = mode;
	}

	/**
	 * @return the eqn0sdu data area contents
	 */
	public String getEodUnit()
	{
		return eqn0sdu;
	}

	/**
	 * @return the current value of kapphs
	 */
	public String getKapphs()
	{
		return kapphs;
	}
	/**
	 * @return today's processing date
	 * @equation.external
	 */
	public String getToday()
	{
		return today;
	}

	/**
	 * @return tomorrow's processing date
	 */
	public String getTomorrow()
	{
		return tomorrow;
	}

	/**
	 * @return yesterday's processing date
	 */
	public String getYesterday()
	{
		return yesterday;
	}

	/**
	 * @return the unit's ID
	 */
	public String getUnitId()
	{
		return unitId;
	}

	/**
	 * @return the unit's description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Set the unit's description
	 * 
	 * @param description
	 *            - the description of the unit
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the eqn0sdu data area contents
	 */
	public String getEqn0sdu()
	{
		return eqn0sdu;
	}

	/**
	 * @return the eqn0eds data area contents
	 */
	public String getEqn0eds()
	{
		return eqn0eds;
	}

	/**
	 * @return the eqn0ehp data area contents
	 */
	public String getEqn0ehp()
	{
		return eqn0ehp;
	}

	/**
	 * @return the eqn0sds data area contents
	 */
	public String getEqn0sds()
	{
		return eqn0sds;
	}

	/**
	 * @return the eqn0shp data area contents
	 */
	public String getEqn0shp()
	{
		return eqn0shp;
	}

	/**
	 * @return the kqiendon data area contents
	 */
	public String getKqiendon()
	{
		return kqiendon;
	}

	/**
	 * Returns the EQGPxxxDP0 data area (Unit Data Propagation Job active data areas) content where 'xxx' is the unit mnemonic.
	 * 
	 * @return the content of the DP0 data area for this unit.
	 */
	public String getDp0DataArea()
	{
		return dp0DataArea;
	}

	public String getSuspendRequest()
	{
		return suspendRequest;
	}

	public void setSuspendRequest(String suspendRequest)
	{
		this.suspendRequest = suspendRequest;
	}

}