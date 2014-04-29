package com.misys.equation.common.dao.beans;

/**
 * NE1 Record data-model.
 * 
 * @author deroset
 * 
 */
public class NE1RecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "NE10LF";

	private String externalAcNumber;
	private String accountBranch;
	private String basicNumber;
	private String accountSuffix;
	private String iban;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1272538474872l;

	/**
	 * Default constructor
	 */
	public NE1RecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public NE1RecordDataModel(String accountBranch, String basicNumber, String accountSuffix)
	{
		this.accountBranch = accountBranch;
		this.basicNumber = basicNumber;
		this.accountSuffix = accountSuffix;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getExternalAcNumber()
	{
		return this.externalAcNumber;
	}
	public void setExternalAcNumber(String parameter)
	{
		this.externalAcNumber = parameter;
	}
	public String getAccountBranch()
	{
		return this.accountBranch;
	}
	public void setAccountBranch(String parameter)
	{
		this.accountBranch = parameter;
	}
	public String getBasicNumber()
	{
		return this.basicNumber;
	}
	public void setBasicNumber(String parameter)
	{
		this.basicNumber = parameter;
	}
	public String getAccountSuffix()
	{
		return this.accountSuffix;
	}
	public void setAccountSuffix(String parameter)
	{
		this.accountSuffix = parameter;
	}
	public String getIban()
	{
		return this.iban;
	}
	public void setIban(String parameter)
	{
		this.iban = parameter;
	}
}