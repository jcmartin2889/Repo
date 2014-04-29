package com.misys.equation.common.transaction.rce;

import com.misys.equation.common.conversion.InputParameterConversion;

public class RCETransactionPrincipalIncrease implements IRCETransactionEvent
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RCETransactionPrincipalIncrease.java 17435 2013-10-15 14:16:27Z lima12 $";

	/** Determine if date is in yyyymmddDateFormat */
	private boolean yyyymmddDateFormat = false;

	/** Principal increase changed date */
	private int principalDate;

	/** Principal increase amount */
	private String principalAmount;

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
	 * Return the principal increase changed date
	 * 
	 * @return the principal increase changed date
	 */
	public int getPrincipalDate()
	{
		if (yyyymmddDateFormat)
		{
			return InputParameterConversion.convertDate(principalDate);
		}
		else
		{
			return principalDate;
		}
	}

	/**
	 * Set the principal increase changed date
	 * 
	 * @param principalDate
	 *            - the principal increase changed date
	 */
	public void setPrincipalDate(int principalDate)
	{
		this.principalDate = principalDate;
	}

	/**
	 * Return the principal increase amount
	 * 
	 * @return the principal increase amount
	 */
	public String getPrincipalAmount()
	{
		return principalAmount;
	}

	/**
	 * Set the principal increase amount
	 * 
	 * @param principalAmount
	 *            - the principal increase amount
	 */
	public void setPrincipalAmount(String principalAmount)
	{
		this.principalAmount = principalAmount;
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
