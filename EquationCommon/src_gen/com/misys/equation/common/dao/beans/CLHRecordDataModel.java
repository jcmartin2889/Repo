package com.misys.equation.common.dao.beans;

/**
 * CLH Record data-model.
 * 
 * @author deroset
 */
public class CLHRecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "CLH10LF";

	private String globalCustomerIdentifier;
	private String globalCustomerName;
	private java.sql.Timestamp timestamp;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1287559303510l;

	/**
	 * Default constructor
	 */
	public CLHRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public CLHRecordDataModel(String globalCustomerIdentifier)
	{
		this.globalCustomerIdentifier = globalCustomerIdentifier;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getGlobalCustomerIdentifier()
	{
		return this.globalCustomerIdentifier;
	}
	public void setGlobalCustomerIdentifier(String parameter)
	{
		this.globalCustomerIdentifier = parameter;
	}
	public String getGlobalCustomerName()
	{
		return this.globalCustomerName;
	}
	public void setGlobalCustomerName(String parameter)
	{
		this.globalCustomerName = parameter;
	}
	public java.sql.Timestamp getTimestamp()
	{
		return this.timestamp;
	}
	public void setTimestamp(java.sql.Timestamp parameter)
	{
		this.timestamp = parameter;
	}
}