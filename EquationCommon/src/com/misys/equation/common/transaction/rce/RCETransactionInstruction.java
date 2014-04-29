package com.misys.equation.common.transaction.rce;

import com.misys.equation.common.conversion.InputParameterConversion;

public class RCETransactionInstruction implements IRCETransactionEvent
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RCETransactionInstruction.java 17493 2013-10-28 09:55:10Z lima12 $";

	/** Determine if date is in yyyymmddDateFormat */
	private boolean yyyymmddDateFormat = false;

	/** Instruction date */
	private int instructionDate;

	/** Repayment frequency */
	private String repaymentFrequency;

	/** Number of payments */
	private String numberOfPayments;

	/** First repayment date */
	private int firstRepaymentDate;

	/** Maturity date */
	private int maturityDate;

	/** Schedule term */
	private String scheduleTerm;

	/** Repayment amount */
	private String repaymentAmount;

	/** First amount */
	private String firstAmount;

	/** First amount can differ flag */
	private String firstAmountCanDiffer;

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
	 * Return the instruction date
	 * 
	 * @return the instruction date
	 */
	public int getInstructionDate()
	{
		if (yyyymmddDateFormat)
		{
			return InputParameterConversion.convertDate(instructionDate);
		}
		else
		{
			return instructionDate;
		}
	}

	/**
	 * Set the instruction date
	 * 
	 * @param instructionDate
	 *            - the instruction date
	 */
	public void setInstructionDate(int instructionDate)
	{
		this.instructionDate = instructionDate;
	}

	/**
	 * Return the repayment frequency
	 * 
	 * @return the repayment frequency
	 */
	public String getRepaymentFrequency()
	{
		return repaymentFrequency;
	}

	/**
	 * Set the repayment frequency
	 * 
	 * @param repaymentFrequency
	 *            - the repayment frequency
	 */
	public void setRepaymentFrequency(String repaymentFrequency)
	{
		this.repaymentFrequency = repaymentFrequency;
	}

	/**
	 * Return the number of payments
	 * 
	 * @return the number of payments
	 */
	public String getNumberOfPayments()
	{
		return numberOfPayments;
	}

	/**
	 * Set the number of payments
	 * 
	 * @param numberOfPayments
	 *            - the number of payments
	 */
	public void setNumberOfPayments(String numberOfPayments)
	{
		this.numberOfPayments = numberOfPayments;
	}

	/**
	 * Return the first repayment date
	 * 
	 * @return the first repayment date
	 */
	public int getFirstRepaymentDate()
	{
		if (yyyymmddDateFormat)
		{
			return InputParameterConversion.convertDate(firstRepaymentDate);
		}
		else
		{
			return firstRepaymentDate;
		}
	}

	/**
	 * Set the first repayment date
	 * 
	 * @param firstRepaymentDate
	 *            - the first repayment date
	 */
	public void setFirstRepaymentDate(int firstRepaymentDate)
	{
		this.firstRepaymentDate = firstRepaymentDate;
	}

	/**
	 * Return the maturity date
	 * 
	 * @return the maturity date
	 */
	public int getMaturityDate()
	{
		if (yyyymmddDateFormat)
		{
			return InputParameterConversion.convertDate(maturityDate);
		}
		else
		{
			return maturityDate;
		}
	}

	/**
	 * Set the maturity date
	 * 
	 * @param maturityDate
	 *            - the maturity date
	 */
	public void setMaturityDate(int maturityDate)
	{
		this.maturityDate = maturityDate;
	}

	/**
	 * Return the schedule term
	 * 
	 * @return the schedule term
	 */
	public String getScheduleTerm()
	{
		return scheduleTerm;
	}

	/**
	 * Set the schedule term
	 * 
	 * @param scheduleTerm
	 *            - the schedule term
	 */
	public void setScheduleTerm(String scheduleTerm)
	{
		this.scheduleTerm = scheduleTerm;
	}

	/**
	 * Return the repayment amount
	 * 
	 * @return the repayment amount
	 */
	public String getRepaymentAmount()
	{
		return repaymentAmount;
	}

	/**
	 * Set the repayment amount
	 * 
	 * @param repaymentAmount
	 *            - the repayment amount
	 */
	public void setRepaymentAmount(String repaymentAmount)
	{
		this.repaymentAmount = repaymentAmount;
	}

	/**
	 * Return the first amount
	 * 
	 * @return the first amount
	 */
	public String getFirstAmount()
	{
		return firstAmount;
	}

	/**
	 * Set the first amount
	 * 
	 * @param firstAmount
	 *            - the first amount
	 */
	public void setFirstAmount(String firstAmount)
	{
		this.firstAmount = firstAmount;
	}

	/**
	 * Return the first amount can differ flag
	 * 
	 * @return the first amount can differ flag
	 */
	public String getFirstAmountCanDiffer()
	{
		return firstAmountCanDiffer;
	}

	/**
	 * Set the first amount can differ flag
	 * 
	 * @param firstAmountCanDiffer
	 *            - the first amount can differ flag
	 */
	public void setFirstAmountCanDiffer(String firstAmountCanDiffer)
	{
		this.firstAmountCanDiffer = firstAmountCanDiffer;
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
