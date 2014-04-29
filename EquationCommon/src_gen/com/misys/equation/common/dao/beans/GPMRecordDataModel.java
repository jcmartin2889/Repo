package com.misys.equation.common.dao.beans;

/**
 * GPM Record data-model.
 * 
 * @author deroset
 * 
 */
public class GPMRecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "GPM10LF";

	private String systemOptionId;
	private String systemOptionValue;
	private String optionType;
	private String optionDescription;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1275040799144l;

	/**
	 * Default constructor
	 */
	public GPMRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	public GPMRecordDataModel(String systemOptionId)
	{
		this.systemOptionId = systemOptionId;
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getSystemOptionId()
	{
		return this.systemOptionId;
	}
	public void setSystemOptionId(String parameter)
	{
		this.systemOptionId = parameter;
	}
	public String getSystemOptionValue()
	{
		return this.systemOptionValue;
	}
	public void setSystemOptionValue(String parameter)
	{
		this.systemOptionValue = parameter;
	}
	public String getOptionType()
	{
		return this.optionType;
	}
	public void setOptionType(String parameter)
	{
		this.optionType = parameter;
	}
	public String getOptionDescription()
	{
		return this.optionDescription;
	}
	public void setOptionDescription(String parameter)
	{
		this.optionDescription = parameter;
	}
}