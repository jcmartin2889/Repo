package com.misys.equation.common.dao.beans;

/**
 * GPU Record data-model.
 * 
 * @author deroset
 * 
 */
public class GPURecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "GPU10LF";

	private String ruleId;
	private String unitType;
	private String serverId;
	private String unitMnemonic;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1270638836289l;

	/**
	 * Default constructor
	 */
	public GPURecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public GPURecordDataModel(String ruleId, String unitType, String serverId, String unitMnemonic)
	{
		this.ruleId = ruleId;
		this.unitType = unitType;
		this.serverId = serverId;
		this.unitMnemonic = unitMnemonic;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getRuleId()
	{
		return this.ruleId;
	}
	public void setRuleId(String parameter)
	{
		this.ruleId = parameter;
	}
	public String getUnitType()
	{
		return this.unitType;
	}
	public void setUnitType(String parameter)
	{
		this.unitType = parameter;
	}
	public String getServerId()
	{
		return this.serverId;
	}
	public void setServerId(String parameter)
	{
		this.serverId = parameter;
	}
	public String getUnitMnemonic()
	{
		return this.unitMnemonic;
	}
	public void setUnitMnemonic(String parameter)
	{
		this.unitMnemonic = parameter;
	}
}