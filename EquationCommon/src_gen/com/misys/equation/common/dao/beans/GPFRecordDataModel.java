package com.misys.equation.common.dao.beans;

/**
 * GPF Record data-model.
 * 
 * @author deroset
 * 
 */
public class GPFRecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "GPF10LF";

	private String ruleId;
	private String field;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1270638836055l;

	/**
	 * Default constructor
	 */
	public GPFRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public GPFRecordDataModel(String ruleId, String field)
	{
		this.ruleId = ruleId;
		this.field = field;
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
	public String getField()
	{
		return this.field;
	}
	public void setField(String parameter)
	{
		this.field = parameter;
	}
}