package com.misys.equation.common.dates;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This class uses works by using an instance of ICustomDateFormatter to format to and from the custom represenatation of a date to
 * a Java based calendar.
 * 
 * @author camillen
 * 
 */
public class CustomCalendar extends GregorianCalendar
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id:";

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -1036912680184741779L;

	protected transient final ICustomDateFormatter dateFormatter;

	protected CustomCalendar(final ICustomDateFormatter dateFormatter)
	{
		super();
		this.dateFormatter = dateFormatter;
	}

	protected CustomCalendar(final ICustomDateFormatter dateFormatter, String alternateDateRep, final String alternateTimeRep)
	{
		this(dateFormatter);
		setTime(dateFormatter.converFromCustomDateTime(alternateDateRep, alternateTimeRep));
	}

	protected CustomCalendar(final ICustomDateFormatter dateFormatter, final Date javaDate)
	{
		this(dateFormatter);
		setTime(javaDate);
	}

	public boolean isSameDateIgnoreTime(Calendar calendar)
	{
		return get(Calendar.YEAR) == calendar.get(Calendar.YEAR) && get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
						&& get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH);
	}

	public void incrementDayForDate(final int noOfDaysToIncrement)
	{
		add(Calendar.DAY_OF_MONTH, noOfDaysToIncrement);

	}

	public final String getAlternateDateRep()
	{
		return dateFormatter.converToCustomDate(getTime());
	}

	public final String getAlternateTimeRep()
	{
		return dateFormatter.converToCustomDate(getTime());
	}

}
