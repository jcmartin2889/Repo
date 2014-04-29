package com.misys.equation.common.dates;

import java.util.Date;

/**
 * Interface defining the methods to be implemented for a CustomDateFormatter.
 * 
 * @author camillen
 */
public interface ICustomDateFormatter
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id:";

	/**
	 * Implement this method to convert from a JAva date to the specific custom date being supported.
	 * 
	 * @param javaDate
	 *            Date - Java Date object
	 * @return String - Custom Date
	 */
	public String converToCustomDate(final Date javaDate);

	/**
	 * Implement this method to convert from the specific custom date to a Java date.
	 * 
	 * @param customDate
	 *            String - Custom Date
	 * @return Date - Java Date
	 */
	public Date converFromCustomDate(final String customDate);

	/**
	 * Implement this method to convert from the specific custom date and time to a Java date.
	 * 
	 * @param customDate
	 *            String - Custom date
	 * @param customTime
	 *            String - Custom time
	 * @return Date - Java Date
	 */
	public Date converFromCustomDateTime(final String customDate, final String customTime);

}
