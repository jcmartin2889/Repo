package com.misys.equation.common.dao.beans;

public class QZRecordDataModel extends AbsRecord
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: QZRecordDataModel.java 7607 2010-06-01 17:05:56Z MACDONP1 $";
	private static final long serialVersionUID = 1L;
	private final static String RECORD_NAME = "QZPF";

	private String fieldName = ""; // QZFLN
	private int fieldPosition; // QZPOS
	private int fieldLength; // QZLGTD
	private String dataarea = ""; // QZARA
	private String optionType; // QZTYP
	private String optionGroup = ""; // QZSGP
	private String fieldDescription = ""; // QZDES
	private String release = ""; // QZREL
	private String fieldText; // QZTXT
	private String blankAllowed; // QZBLN
	private String maintainable; // QZALM
	private String validPhase; // QZPHS
	private String validValues; // QZVLD
	private String lowerLimit; // QZLLM
	private String upperLimit; // QZULM
	private String pvModule; // QZPVMV
	private String pvDecode; // QZDCD
	private String pvPromptAvailable; // QZPAV
	private String relatedFieldName; // QZRFLN
	private String defaultSystemValue; // QZDFT

	/**
	 * Construct an empty file
	 * 
	 */
	public QZRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	/**
	 */
	public QZRecordDataModel(String fieldName)
	{
		super();
		setEqFileName(RECORD_NAME);
		setFieldName(fieldName);
	}

	// ---getters and setters----//

	/**
	 * Return the field name
	 * 
	 * @return the field name
	 */
	public String getFieldName()
	{
		return fieldName;
	}

	/**
	 * Set the field name
	 * 
	 * @param fieldName
	 *            - the field name
	 */
	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	/**
	 * Return the field position
	 * 
	 * @return the field position
	 */
	public int getFieldPosition()
	{
		return fieldPosition;
	}

	/**
	 * Set the field position
	 * 
	 * @param fieldPosition
	 *            - the field position
	 */
	public void setFieldPosition(int fieldPosition)
	{
		this.fieldPosition = fieldPosition;
	}

	/**
	 * Return the field length
	 * 
	 * @return the field length
	 */
	public int getFieldLength()
	{
		return fieldLength;
	}

	/**
	 * Set the field length
	 * 
	 * @param fieldLength
	 *            - the field length
	 */
	public void setFieldLength(int fieldLength)
	{
		this.fieldLength = fieldLength;
	}

	/**
	 * Return the data area name
	 * 
	 * @return the data area name
	 */
	public String getDataarea()
	{
		return dataarea;
	}

	/**
	 * Set the data area name
	 * 
	 * @param dataarea
	 *            - the data area name
	 */
	public void setDataarea(String dataarea)
	{
		this.dataarea = dataarea;
	}

	/**
	 * Get the option type
	 * 
	 * @return the option Type
	 */
	public String getOptionType()
	{
		return optionType;
	}

	/**
	 * Set the option type
	 * 
	 * @param optionType
	 *            - the option Type
	 */
	public void setOptionType(String optionType)
	{
		this.optionType = optionType;
	}

	/**
	 * Get the option group
	 * 
	 * @return the option group
	 */
	public String getOptionGroup()
	{
		return optionGroup;
	}

	/**
	 * Set the option group
	 * 
	 * @param optionGroup
	 *            - the option group
	 */
	public void setOptionGroup(String optionGroup)
	{
		this.optionGroup = optionGroup;
	}

	/**
	 * Get the field description
	 * 
	 * @return the field description
	 */
	public String getFieldDescription()
	{
		return fieldDescription;
	}

	/**
	 * Set the field description
	 * 
	 * @param fieldDescription
	 *            - the field description
	 */
	public void setFieldDescription(String fieldDescription)
	{
		this.fieldDescription = fieldDescription;
	}

	/**
	 * Set the release
	 * 
	 * @param release
	 *            - the release
	 */
	public void setRelease(String release)
	{
		this.release = release;
	}

	/**
	 * get the release
	 * 
	 * @return release - the release
	 */
	public String getRelease()
	{
		return release;
	}

	public void setFieldText(String fieldText)
	{
		this.fieldText = fieldText;
	}

	public String getFieldText()
	{
		return fieldText;
	}

	public void setBlankAllowed(String blankAllowed)
	{
		this.blankAllowed = blankAllowed;
	}

	public String getBlankAllowed()
	{
		return blankAllowed;
	}

	public void setMaintainable(String maintainable)
	{
		this.maintainable = maintainable;
	}

	public String getMaintainable()
	{
		return maintainable;
	}

	public void setValidPhase(String validPhase)
	{
		this.validPhase = validPhase;
	}

	public String getValidPhase()
	{
		return validPhase;
	}

	public void setValidValues(String validValues)
	{
		this.validValues = validValues;
	}

	public String getValidValues()
	{
		return validValues;
	}

	public void setLowerLimit(String lowerLimit)
	{
		this.lowerLimit = lowerLimit;
	}

	public String getLowerLimit()
	{
		return lowerLimit;
	}

	public void setUpperLimit(String upperLimit)
	{
		this.upperLimit = upperLimit;
	}

	public String getUpperLimit()
	{
		return upperLimit;
	}

	public String getPvModule()
	{
		return pvModule;
	}

	public void setPvModule(String pvModule)
	{
		this.pvModule = pvModule;
	}

	public String getPvDecode()
	{
		return pvDecode;
	}

	public void setPvDecode(String pvDecode)
	{
		this.pvDecode = pvDecode;
	}

	public String getPvPromptAvailable()
	{
		return pvPromptAvailable;
	}

	public void setPvPromptAvailable(String pvPromptAvailable)
	{
		this.pvPromptAvailable = pvPromptAvailable;
	}

	public String getRelatedFieldName()
	{
		return relatedFieldName;
	}

	public void setRelatedFieldName(String relatedFieldName)
	{
		this.relatedFieldName = relatedFieldName;
	}

	public String getDefaultSystemValue()
	{
		return defaultSystemValue;
	}

	public void setDefaultSystemValue(String defaultSystemValue)
	{
		this.defaultSystemValue = defaultSystemValue;
	}

}
