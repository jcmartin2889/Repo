package com.misys.equation.common.dao.beans;

/**
 * GPE Record data-model.
 * 
 * @author deroset
 * 
 */
public class GPERecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "GPE10LF";

	private String identifier;
	private String description;
	private String automaticOrManualApply;
	private String propagateToAllUnits;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1270638835914l;

	/**
	 * Default constructor
	 */
	public GPERecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public GPERecordDataModel(String identifier)
	{
		this.identifier = identifier;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getIdentifier()
	{
		return this.identifier;
	}
	public void setIdentifier(String parameter)
	{
		this.identifier = parameter;
	}
	public String getDescription()
	{
		return this.description;
	}
	public void setDescription(String parameter)
	{
		this.description = parameter;
	}
	public String getAutomaticOrManualApply()
	{
		return this.automaticOrManualApply;
	}
	public void setAutomaticOrManualApply(String parameter)
	{
		this.automaticOrManualApply = parameter;
	}
	public String getPropagateToAllUnits()
	{
		return this.propagateToAllUnits;
	}
	public void setPropagateToAllUnits(String parameter)
	{
		this.propagateToAllUnits = parameter;
	}
}