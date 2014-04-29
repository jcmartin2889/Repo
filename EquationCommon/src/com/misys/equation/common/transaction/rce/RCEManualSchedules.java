package com.misys.equation.common.transaction.rce;

import com.misys.equation.common.conversion.InputParameterConversion;

public class RCEManualSchedules implements IRCETransactionEvent
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RCEManualSchedules.java 17435 2013-10-15 14:16:27Z lima12 $";

	/** Determine if date is in yyyymmddDateFormat */
	private boolean yyyymmddDateFormat = false;

	/** Schedule date */
	private int scheduleDate;

	/** Interest amount */
	private String interestScheduleAmount;

	/** Principal amount */
	private String principalScheduleAmount;

	/** Is ad-hoc? */
	private String isAdHoc;

	/** Delete principal increase */
	private boolean delete;

	/**
	 * Determine if date is in yyyymmdd format (true) or cyymmdd format (false)
	 * 
	 * @return true if date is in yyyymmdd format (true) or cyymmdd format (false)
	 */
	public boolean isYyyymmddDateFormat()
	{
		return yyyymmddDateFormat;
	}

	/**
	 * Set if date is in yyyymmdd format (true) or cyymmdd format (false)
	 * 
	 * If date is in yyyymmdd format, then the date is automatically converted into cyymmdd by its getter method
	 * 
	 * @param yyyymmddDateFormat
	 *            - true if date is in yyyymmdd format (true) or cyymmdd format (false)
	 */
	public void setYyyymmddDateFormat(boolean yyyymmddDateFormat)
	{
		this.yyyymmddDateFormat = yyyymmddDateFormat;
	}

	/**
	 * Return the schedule date
	 * 
	 * @return the schedule date
	 */
	public int getScheduleDate()
	{
		if (yyyymmddDateFormat)
		{
			return InputParameterConversion.convertDate(scheduleDate);
		}
		else
		{
			return scheduleDate;
		}
	}

	/**
	 * Set the schedule date
	 * 
	 * @param scheduleDate
	 *            - the schedule date
	 */
	public void setScheduleDate(int scheduleDate)
	{
		this.scheduleDate = scheduleDate;
	}

	/**
	 * Return the interest schedule amount
	 * 
	 * @return the interest schedule amount
	 */
	public String getInterestScheduleAmount()
	{
		return interestScheduleAmount;
	}

	/**
	 * Set the interest schedule amount
	 * 
	 * @param interestScheduleAmount
	 *            - the interest schedule amount
	 */
	public void setInterestScheduleAmount(String interestScheduleAmount)
	{
		this.interestScheduleAmount = interestScheduleAmount;
	}

	/**
	 * Return the principal schedule amount
	 * 
	 * @return the principal schedule amount
	 */
	public String getPrincipalScheduleAmount()
	{
		return principalScheduleAmount;
	}

	/**
	 * Set the principal schedule amount
	 * 
	 * @param principalScheduleAmount
	 *            - the principal schedule amount
	 */
	public void setPrincipalScheduleAmount(String principalScheduleAmount)
	{
		this.principalScheduleAmount = principalScheduleAmount;
	}

	/**
	 * Return the ad-hoc schedule flag
	 * 
	 * @return the ad-hoc schedule flag
	 */
	public String getIsAdHoc()
	{
		return isAdHoc;
	}

	/**
	 * Set the ad-hoc schedule flag
	 * 
	 * @param isAdHoc
	 *            - the ad-hoc schedule flag
	 */
	public void setIsAdHoc(String isAdHoc)
	{
		this.isAdHoc = isAdHoc;
	}

	/**
	 * Determine if principal increase to be deleted or added/maintained
	 * 
	 * @return true if principal increase to be deleted
	 */
	public boolean isDelete()
	{
		return delete;
	}

	/**
	 * Set if principal increase to be deleted or added/maintained
	 * 
	 * @param delete
	 *            - true if principal increase to be deleted
	 */
	public void setDelete(boolean delete)
	{
		this.delete = delete;
	}

}
