package com.misys.equation.common.dao.beans;

/**
 * GPV Record data-model.
 * 
 * @author deroset
 * 
 */
public class GPVRecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "GPV10LF";

	private String setId;
	private String unitType;
	private String serverId;
	private String unitMnemonic;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1270638836414l;

	/**
	 * Default constructor
	 */
	public GPVRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public GPVRecordDataModel(String setId, String unitType, String serverId, String unitMnemonic)
	{
		this.setId = setId;
		this.unitType = unitType;
		this.serverId = serverId;
		this.unitMnemonic = unitMnemonic;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getSetId()
	{
		return this.setId;
	}
	public void setSetId(String parameter)
	{
		this.setId = parameter;
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