package com.misys.equation.common.dates;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.ibm.as400.access.AS400;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.datastructure.EqDS_DSSYSE;
import com.misys.equation.common.globalprocessing.GlobalProcessingToolBox;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.Enhancement;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;

/**
 * This class works by using an instance of EquationDateFormatter to format to and from the custom represenatation of a date to a
 * Java based calendar.
 * 
 * @author camillen
 */
public class EquationCalendar extends CustomCalendar
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationCalendar.java 10472 2011-02-10 15:04:46Z MACDONP1 $";

	/** Logger for this class */
	private static final EquationLogger LOG = new EquationLogger(EquationCalendar.class);

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 275630887804742240L;

	/**
	 * EquationCalendar constructor
	 * 
	 * @param alternateDateRep
	 * @param alternateTimeRep
	 */
	public EquationCalendar(String alternateDateRep, final String alternateTimeRep)
	{
		super(new EquationDateFormatter(), alternateDateRep, alternateTimeRep);
		setTime(dateFormatter.converFromCustomDateTime(alternateDateRep, alternateTimeRep));
	}

	/**
	 * EquationCalendar constructor
	 * 
	 * @param alternateDateRep
	 */
	public EquationCalendar(final String alternateDateRep)
	{
		super(new EquationDateFormatter(), alternateDateRep, null);
		setTime(dateFormatter.converFromCustomDate(alternateDateRep));
	}

	/**
	 * EquationCalendar constructor
	 * 
	 * @param javaDate
	 */
	public EquationCalendar(final Date javaDate)
	{
		super(new EquationDateFormatter(), javaDate);
		setTime(javaDate);
	}

	/**
	 * EquationCalendar constructor
	 */
	public EquationCalendar()
	{
		super(new EquationDateFormatter());
	}

	/**
	 * Returns a short string rep. This is useful for displaying the date on the user interface.
	 * 
	 * @return String - short rep.
	 */
	public String getShortStringRep()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		return formatter.format(getTime());
	}

	/**
	 * This method queries all unit data areas to retrieve the latest overnight date. Interrogate all online linked units to
	 * determine the latest overnight date, #LOD (the calendar day before the latest �PDATE from all linked units (substitute �YDATE
	 * for �PDATE if K401 (24*7) is enabled and in night mode).
	 * 
	 * @return String
	 */
	public static String getLatestProcessingDateString(final List<EquationStandardSession> sessions)
	{
		// First we get all the processing and yesterday dates for all units ...
		String[] pDates = getPDatesForAllUnits(sessions);
		String[] yDates = getYDatesForAllUnits(sessions);

		String lastDate = "00000000";
		// For each of the units determine if K401 (24*7) is enabled and in night mode
		boolean isEnhancementInstalled = false;
		for (int i = 0; i < sessions.size(); i++)
		{
			try
			{
				isEnhancementInstalled = sessions.get(i).getUnit().isEnhancementInstalled(Enhancement.K401);

				if (isEnhancementInstalled)
				{
					AS400 as400 = sessions.get(i).getUnit().getEquationSystem().getAS400();
					if (GlobalProcessingToolBox.isInNightMode(as400, sessions.get(i).getUnit().getUnitId()))
					{
						// If K401 (24*7) is enabled and in night mode substitute �YDATE for �PDATE
						if (Integer.parseInt(yDates[i]) > Integer.parseInt(lastDate))
						{

							lastDate = yDates[i];
						}
					}
					else
					{
						if (Integer.parseInt(pDates[i]) > Integer.parseInt(lastDate))
						{
							// The calendar day before the latest �PDATE
							lastDate = pDates[i];
						}
					}
					sessions.get(i).getUnit().getEquationSystem().returnAS400(as400);
				}
				else
				{
					// The calendar day before the latest �PDATE
					if (Integer.parseInt(pDates[i]) > Integer.parseInt(lastDate))
					{
						lastDate = pDates[i];
					}
				}
			}
			catch (EQException e)
			{
				LOG.error(e);
			}
		}
		return lastDate;
	}

	/**
	 * This method queries all unit data areas to retrieve the latest overnight date. Interrogate all online linked units to
	 * determine the latest overnight date, #LOD (the calendar day before the latest �PDATE from all linked units (substitute �YDATE
	 * for �PDATE if K401 (24*7) is enabled and in night mode).
	 * 
	 * @return String
	 */
	public static String getLatestOvernightDateString(final List<EquationStandardSession> sessions)
	{
		// First we get all the processing and yesterday dates for all units ...
		String[] pDates = getPDatesForAllUnits(sessions);
		// String[] yDates = getYDatesForAllUnits(sessions);

		String lastDate = "00000000";
		// For each of the units determine if K401 (24*7) is enabled and in night mode
		boolean isEnhancementInstalled = false;
		for (int i = 0; i < sessions.size(); i++)
		{
			try
			{
				isEnhancementInstalled = sessions.get(i).getUnit().isEnhancementInstalled(Enhancement.K401);
				GregorianCalendar yesterday = Toolbox.parseEqDate(pDates[i]);
				yesterday.add(Calendar.DATE, -1);

				if (isEnhancementInstalled)
				{
					AS400 as400 = sessions.get(i).getUnit().getEquationSystem().getAS400();
					if (GlobalProcessingToolBox.isInNightMode(as400, sessions.get(i).getUnit().getUnitId()))
					{
						yesterday.add(Calendar.DATE, -1);
						// If K401 (24*7) is enabled and in night mode, then the current ydate is the effective actual pdate
						if (Integer.parseInt(Toolbox.getDateDBStringFormat(yesterday)) > Integer.parseInt(lastDate))
						{
							lastDate = Toolbox.getDateDBStringFormat(yesterday);
						}
					}
					else
					{
						if (Integer.parseInt(Toolbox.getDateDBStringFormat(yesterday)) > Integer.parseInt(lastDate))
						{
							// The latest "yesterday" date
							lastDate = Toolbox.getDateDBStringFormat(yesterday);
						}
					}
					sessions.get(i).getUnit().getEquationSystem().returnAS400(as400);
				}
				else
				{
					if (Integer.parseInt(Toolbox.getDateDBStringFormat(yesterday)) > Integer.parseInt(lastDate))
					{
						// The latest "yesterday" date
						lastDate = Toolbox.getDateDBStringFormat(yesterday);
					}
				}
			}
			catch (EQException e)
			{
				// Unit might not be available
				// TODO CAMILLEN - What shall we do in this case?
			}
		}
		return lastDate;
	}

	/**
	 * Returns the latest overnight date.
	 * 
	 * @param sessions
	 *            List<EquationStandardSession> - List of sessions
	 * 
	 * @return EquationCalendar - Calendar object representing the latest overnight date.
	 */
	public static EquationCalendar getLatestOvernightDate(final List<EquationStandardSession> sessions)
	{
		return new EquationCalendar(getLatestOvernightDateString(sessions));
	}

	/**
	 * Returns the latest processing date.
	 * 
	 * @param sessions
	 *            List<EquationStandardSession> - List of sessions
	 * 
	 * @return EquationCalendar - Calendar object representing the latest processing date.
	 */
	public static EquationCalendar getLatestProcessingDate(final List<EquationStandardSession> sessions)
	{
		return new EquationCalendar(getLatestProcessingDateString(sessions));
	}

	/**
	 * Returns the Pdate(s) for all units.
	 * 
	 * @param sessions
	 *            List<EquationStandardSession> - List of sessionss
	 * @return
	 */
	public static String[] getPDatesForAllUnits(final List<EquationStandardSession> sessions)
	{
		return GlobalProcessingToolBox.getDataAreaValuesFromUnit(EqDS_DSSYSE.PDATE, sessions);
	}

	/**
	 * Returns the Ydate(s) for all units.
	 * 
	 * @param sessions
	 *            List<EquationStandardSession> - List of sessions
	 * @return
	 */
	public static String[] getYDatesForAllUnits(final List<EquationStandardSession> sessions)
	{
		return GlobalProcessingToolBox.getDataAreaValuesFromUnit(EqDS_DSSYSE.YDATE, sessions);
	}
}
