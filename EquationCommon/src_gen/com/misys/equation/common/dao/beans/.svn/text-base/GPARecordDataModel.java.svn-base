package com.misys.equation.common.dao.beans;

/**
 * GPA Record data-model.
 * 
 * @author deroset
 * 
 */
public class GPARecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "GPA10LF";

	private String setIdentifier;
	private String ruleIdentifier;
	private int displayOrder;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1270638835789l;

	/**
	 * Default constructor
	 */
	public GPARecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public GPARecordDataModel(String setIdentifier, String ruleIdentifier)
	{
		this.setIdentifier = setIdentifier;
		this.ruleIdentifier = ruleIdentifier;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getSetIdentifier()
	{
		return this.setIdentifier;
	}
	public void setSetIdentifier(String parameter)
	{
		this.setIdentifier = parameter;
	}
	public String getRuleIdentifier()
	{
		return this.ruleIdentifier;
	}
	public void setRuleIdentifier(String parameter)
	{
		this.ruleIdentifier = parameter;
	}
	public int getDisplayOrder()
	{
		return this.displayOrder;
	}
	public void setDisplayOrder(int parameter)
	{
		this.displayOrder = parameter;
	}
}