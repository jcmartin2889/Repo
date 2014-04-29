package com.misys.equation.common.transaction.rce;

public class RCETransactionKeys
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RCETransactionKeys.java 17435 2013-10-15 14:16:27Z lima12 $";

	/** Loan type */
	private String loanType;

	/** Loan reference */
	private String loanReference;

	/** Loan branch */
	private String loanBranch;

	/** Customer mnemonic */
	private String customerMnemonic;

	/** Customer location */
	private String customerLocation;

	/**
	 * Return the loan type
	 * 
	 * @return the loan type
	 */
	public String getLoanType()
	{
		return loanType;
	}

	/**
	 * Set the loan type
	 * 
	 * @param loanType
	 *            - the loan type
	 */
	public void setLoanType(String loanType)
	{
		this.loanType = loanType;
	}

	/**
	 * Return the loan reference
	 * 
	 * @return the loan reference
	 */
	public String getLoanReference()
	{
		return loanReference;
	}

	/**
	 * Set the loan reference
	 * 
	 * @param loanReference
	 *            - the loan reference
	 */
	public void setLoanReference(String loanReference)
	{
		this.loanReference = loanReference;
	}

	/**
	 * Return the loan branch
	 * 
	 * @return the loan branch
	 */
	public String getLoanBranch()
	{
		return loanBranch;
	}

	/**
	 * Set the loan branch
	 * 
	 * @param loanBranch
	 *            - the loan branch
	 */
	public void setLoanBranch(String loanBranch)
	{
		this.loanBranch = loanBranch;
	}

	/**
	 * Return the customer mnemonic
	 * 
	 * @return the customer mnemonic
	 */
	public String getCustomerMnemonic()
	{
		return customerMnemonic;
	}

	/**
	 * Set the customer mnemonic
	 * 
	 * @param customerMnemonic
	 *            - the customer mnemonic
	 */
	public void setCustomerMnemonic(String customerMnemonic)
	{
		this.customerMnemonic = customerMnemonic;
	}

	/**
	 * Return the customer location
	 * 
	 * @return the customer location
	 */
	public String getCustomerLocation()
	{
		return customerLocation;
	}

	/**
	 * Set the customer location
	 * 
	 * @param customerLocation
	 *            - the customer location
	 */
	public void setCustomerLocation(String customerLocation)
	{
		this.customerLocation = customerLocation;
	}

}