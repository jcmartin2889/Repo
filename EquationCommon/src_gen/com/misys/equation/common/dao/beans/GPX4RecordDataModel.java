package com.misys.equation.common.dao.beans;

/**
 * GPX4 Record data-model.
 * 
 * @author deroset
 * 
 */
public class GPX4RecordDataModel extends AbsRecord
{

	private final static String RECORD_NAME = "GPX40LF";

	private String groupMnemonic;
	private String unitMnemonic;
	private int unitSequence;
	private String systemName;
	private String unitType;
	private String branchNumber;
	private String branchMnemonic;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1258630647466l;

	/**
	 * Default constructor
	 */
	public GPX4RecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);

	}

	public GPX4RecordDataModel(String unitMnemonic)
	{
		this.unitMnemonic = unitMnemonic;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getGroupMnemonic()
	{
		return this.groupMnemonic;
	}
	public void setGroupMnemonic(String parameter)
	{
		this.groupMnemonic = parameter;
	}
	public String getUnitMnemonic()
	{
		return this.unitMnemonic;
	}
	public void setUnitMnemonic(String parameter)
	{
		this.unitMnemonic = parameter;
	}
	public int getUnitSequence()
	{
		return this.unitSequence;
	}
	public void setUnitSequence(int parameter)
	{
		this.unitSequence = parameter;
	}
	public String getSystemName()
	{
		return this.systemName;
	}
	public void setSystemName(String parameter)
	{
		this.systemName = parameter;
	}
	public String getUnitType()
	{
		return this.unitType;
	}
	public void setUnitType(String parameter)
	{
		this.unitType = parameter;
	}
	public String getBranchNumber()
	{
		return this.branchNumber;
	}
	public void setBranchNumber(String parameter)
	{
		this.branchNumber = parameter;
	}
	public String getBranchMnemonic()
	{
		return this.branchMnemonic;
	}
	public void setBranchMnemonic(String parameter)
	{
		this.branchMnemonic = parameter;
	}

}
