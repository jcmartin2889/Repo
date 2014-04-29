package com.misys.equation.common.dao.beans;

/**
 * GPX Record data-model.
 * 
 * @author deroset
 * 
 */
public class GPXRecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "GPX10LF";

	private String groupMnemonic;
	private String unitMnemonic;
	private int unitSequence;
	private String systemName;
	private String unitType;
	private String unitDescription;
	private String branchNumber;
	private String branchMnemonic;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1275048566688l;

	/**
	 * Default constructor
	 */
	public GPXRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public GPXRecordDataModel(String groupMnemonic, String unitMnemonic, String systemName)
	{
		this.groupMnemonic = groupMnemonic;
		this.unitMnemonic = unitMnemonic;
		this.systemName = systemName;
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
	public String getUnitDescription()
	{
		return this.unitDescription;
	}
	public void setUnitDescription(String parameter)
	{
		this.unitDescription = parameter;
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