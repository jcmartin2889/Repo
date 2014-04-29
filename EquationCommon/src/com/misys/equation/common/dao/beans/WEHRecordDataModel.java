package com.misys.equation.common.dao.beans;
/**
 * WEH Record data-model.
 * 
 * @author deroset
 * 
 */
public class WEHRecordDataModel extends AbsRecord
{		
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WEHRecordDataModel.java 13101 2012-03-28 06:15:02Z bernie.terrado $";
	private final static String RECORD_NAME = "WEHPF";

	private String maker;
	private String checker;
	private String sessionId;
	private String transactionId;
	private int sequence;
	private String optionId;
	private String status;
	private int processedDate;
	private int processedTime;
	private String reason;
	private String branch;
	private String customerNumber;
	private String account;
	private String reference;
	private String secondAccount;
	private String additionalRef;
	
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1323183018213l;
	
	
	/**
	 * Default constructor
	 */
	public WEHRecordDataModel()
	{
		super();
		
		setMaker("");
		setChecker("");
		setSessionId("");
		setTransactionId("");
		setSequence(0);
		setOptionId("");
		setStatus("");
		setProcessedDate(0);
		setProcessedTime(0);
		setReason("");
		setBranch("");
		setCustomerNumber("");
		setAccount("");
		setReference("");
		setSecondAccount("");
		setAdditionalRef("");

		setEqFileName(RECORD_NAME);		
	}
	
	/**
	 * Retrieve the Maker of the Maker Checker referral
	 * @return Maker of the Maker Checker referral
	 */
	public String getMaker()
	{
		return this.maker;
	}
	
	/**
	 * Sets the Checker of a Maker Checker referral
	 * @param parameter Checker of a Maker Checker referral
	 */
	public void setMaker( String parameter)
	{
		this.maker= parameter;
	}
	
	/**
	 * Retrieves the Checker of a Maker Checker referral
	 * @return Checker of a Maker Checker referral
	 */
	public String getChecker()
	{
		return this.checker;
	}
	
	/**
	 * Sets the Checker of a Maker Checker referral
	 * @param parameter Checker of a Maker Checker referral
	 */
	public void setChecker( String parameter)
	{
		this.checker= parameter;
	}
	
	/**
	 * Retrieves the Session Id
	 * @return Session Id
	 */
	public String getSessionId()
	{
		return this.sessionId;
	}
	
	/**
	 * Sets the Session Id
	 * @param parameter Session Id
	 */
	public void setSessionId( String parameter)
	{
		this.sessionId= parameter;
	}
	
	/**
	 * Retrieves the Transaction Id
	 * @return Transaction Id
	 */
	public String getTransactionId()
	{
		return this.transactionId;
	}
	
	/**
	 * Sets the Transaction Id
	 * @param parameter Transaction Id
	 */
	public void setTransactionId( String parameter)
	{
		this.transactionId= parameter;
	}
	
	/**
	 * Retrieves the sequence number
	 * @return sequence number
	 */
	public int getSequence()
	{
		return this.sequence;
	}
	
	/**
	 * Sets the sequence number
	 * @param parameter sequence number
	 */
	public void setSequence(int parameter)
	{
		this.sequence= parameter;
	}	
	
	/**
	 * Retrieves the Option Id
	 * @return Option Id
	 */
	public String getOptionId()
	{
		return this.optionId;
	}
	
	/**
	 * Sets the Option Id
	 * @param parameter Option Id
	 */
	public void setOptionId( String parameter)
	{
		this.optionId= parameter;
	}
	
	/**
	 * Retrieves the status
	 * @return status
	 */
	public String getStatus()
	{
		return this.status;
	}
	
	/**
	 * Sets the status
	 * @param parameter status
	 */
	public void setStatus( String parameter)
	{
		this.status= parameter;
	}
	
	/**
	 * Retrieves the Processed date
	 * @return Processed date
	 */
	public int getProcessedDate()
	{
		return this.processedDate;
	}
	
	/**
	 * Sets the Processed date
	 * @param parameter Processed date
	 */
	public void setProcessedDate( int parameter)
	{
		this.processedDate= parameter;
	}
	
	/**
	 * Retrieves the Processed time
	 * @return Processed time
	 */
	public int getProcessedTime()
	{
		return this.processedTime;
	}
	
	/**
	 * Sets the Processed time
	 * @param parameter Processed time
	 */
	public void setProcessedTime( int parameter)
	{
		this.processedTime= parameter;
	}
	
	/**
	 * Retrieves the reason
	 * 
	 * @return Reason
	 */
	public String getReason()
	{
		return this.reason;
	}
	
	/**
	 * Sets the reason
	 * @param parameter Reason
	 */
	public void setReason( String parameter)
	{
		this.reason= parameter;
	}
	
	/**
	 * Retrieves the Branch 
	 * @return branch
	 */
	public String getBranch()
	{
		return this.branch;
	}
	
	/**
	 * Sets the Branch 
	 * @param parameter Branch 
	 */
	public void setBranch( String parameter)
	{
		this.branch= parameter;
	}
	
	/**
	 * Retrieves the Customer Number
	 * @return Customer Number
	 */
	public String getCustomerNumber()
	{
		return this.customerNumber;
	}
	
	/**
	 * Sets the Customer Number
	 * @param parameter Customer Number
	 */
	public void setCustomerNumber( String parameter)
	{
		this.customerNumber= parameter;
	}
	
	/**
	 * Retrieves the Account
	 * @return
	 */
	public String getAccount()
	{
		return this.account;
	}
	
	/**
	 * Sets the Account
	 * @param parameter Account
	 */
	public void setAccount( String parameter)
	{
		this.account= parameter;
	}
	
	/**
	 * Retrieves the reference
	 * @return Reference
	 */
	public String getReference()
	{
		return this.reference;
	}
	
	/**
	 * Sets the Reference
	 * @param parameter Reference
	 */
	public void setReference( String parameter)
	{
		this.reference= parameter;
	}
	
	/**
	 * Retrieves the Second Account
	 * @return Second Account
	 */
	public String getSecondAccount()
	{
		return this.secondAccount;
	}
	
	/**
	 * Sets the Second Account 
	 * @param parameter Second Account
	 */
	public void setSecondAccount( String parameter)
	{
		this.secondAccount= parameter;
	}
	
	/**
	 * Retrieves Additional Reference field
	 * 
	 * @return Additional Reference field
	 */
	public String getAdditionalRef()
	{
		return this.additionalRef;
	}
	
	/**
	 * Sets the Additional Reference field
	 * 
	 * @param parameter Additional Reference field
	 */
	public void setAdditionalRef( String parameter)
	{
		this.additionalRef= parameter;
	}
}