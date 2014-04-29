package com.misys.equation.function.beans;

import com.misys.equation.common.utilities.EqDataType;

/**
 * This class represents the run time definition of a field.
 * <p>
 * A collection of these definitions are held in a FunctionData instance and are used when interacting with the actual runtime data.
 * <p>
 * This is used to separate the definition from the data storage, so that this definition information is not duplicated for each row
 * of repeating data.
 * <p>
 * TODO: The separation of field definition/data is not complete. The FieldData class still holds all definition information
 * 
 * @see FieldValues
 * @see RepeatingFieldData
 */
public class FieldDefinition
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FieldDefinition.java 7610 2010-06-01 17:10:41Z MACDONP1 $";

	/** Orientation not specified */
	public static final int ORIENT_NOT_SET = 0;
	/** Orientation must be Right to Left */
	public static final int ORIENT_RTL = 1;
	/** Orientation must be Left to Right */
	public static final int ORIENT_LTR = 2;

	// field name
	private String fieldName;

	// field data type;
	private String fieldType;
	private int fieldLength;
	private int fieldDecimal;

	// properties
	private boolean upperCase; // automatically convert to upper case
	private boolean locked; // never change any of the value
	private boolean defaultLocked; // this determine whether the field has been initially locked by the system
	private boolean workField; // work field?

	// these properties are display specific
	private int orientation; // right-to-left orientation or left-to-right orientation?
	private boolean showDescAsValue; // set to true if show description as value

	/** Is the field repeating */
	private boolean repeating;

	/**
	 * Constructor
	 */
	public FieldDefinition()
	{
		super();
		commonInitialisation();
	}

	/**
	 * Constructs a FieldDefinition using the properties of a FieldData object
	 * 
	 * @param fieldData
	 */
	public FieldDefinition(FieldData fieldData)
	{
		commonInitialisation();
		this.fieldName = fieldData.getFieldName().toUpperCase();
		this.fieldType = fieldData.getFieldType();
		this.fieldLength = fieldData.getFieldLength();
		this.fieldDecimal = fieldData.getFieldDecimal();
		this.upperCase = fieldData.isUpperCase();
		this.workField = fieldData.isWorkField();
		this.repeating = fieldData instanceof RepeatingFieldData;
	}

	/**
	 * Initialise default properties
	 */
	private void commonInitialisation()
	{
		this.fieldName = "";
		this.fieldType = "";
		this.fieldLength = 0;
		this.fieldDecimal = 0;

		this.locked = false;
		this.upperCase = false;
		this.defaultLocked = false;
		this.workField = false;

		this.orientation = ORIENT_NOT_SET;
		this.showDescAsValue = false;
		this.repeating = false;
	}

	/**
	 * Set the field name
	 * 
	 * @param fieldName
	 *            - field name
	 */
	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

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
	 * Set the field type
	 * 
	 * @param fieldType
	 *            - field type
	 */
	public void setFieldType(String fieldType)
	{
		this.fieldType = fieldType;
	}

	/**
	 * Return the field type
	 * 
	 * @return the field type
	 */
	public String getFieldType()
	{
		return fieldType;
	}

	/**
	 * Set the number of decimal
	 * 
	 * @param fieldDecimal
	 *            - number of decimal
	 */
	public void setFieldDecimal(int fieldDecimal)
	{
		this.fieldDecimal = fieldDecimal;
	}

	/**
	 * Return the number of decimal
	 * 
	 * @return the number of decimal
	 */
	public int getFieldDecimal()
	{
		return fieldDecimal;
	}

	/**
	 * Set the field length
	 * 
	 * @param fieldLength
	 *            - field length
	 */
	public void setFieldLength(int fieldLength)
	{
		this.fieldLength = fieldLength;
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
	 * Determine if the field should be in upper case
	 * 
	 * @return true - if the field must be in upper case
	 */
	public boolean isUpperCase()
	{
		return upperCase;
	}

	/**
	 * Set whether the field should be in upper case
	 * 
	 * @param upperCase
	 *            - true - if the field must be in upper case
	 */
	public void setUpperCase(boolean upperCase)
	{
		this.upperCase = upperCase;
	}

	/**
	 * Determine if the field cannot be changed
	 * 
	 * @return true if the field cannot be changed
	 */
	public boolean isLocked()
	{
		return locked;
	}

	/**
	 * Set if the field cannot be changed
	 * 
	 * @param locked
	 *            - true if the field cannot be changed
	 */
	public void setLocked(boolean locked)
	{
		this.locked = locked;
	}

	/**
	 * Determine if the field has been defaulted by the system as cannot be changed
	 * 
	 * @return true if the field has been defaulted by the system as cannot be changed
	 */
	public boolean isDefaultLocked()
	{
		return defaultLocked;
	}

	/**
	 * Set whether the field has been defaulted by the system as cannot be changed
	 * 
	 * @param systemLocked
	 *            - true if the field has been defaulted by the system as cannot be changed
	 */
	public void setDefaultLocked(boolean systemLocked)
	{
		this.defaultLocked = systemLocked;
	}

	/**
	 * Return the text orientation
	 * 
	 * @return the text orientation
	 */
	public int getOrientation()
	{
		return orientation;
	}

	/**
	 * Set the text orientation
	 * 
	 * @param orientation
	 *            - the text orientation
	 */
	public void setOrientation(int orientation)
	{
		this.orientation = orientation;
	}

	/**
	 * Return the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(fieldName + " :");
		buffer.append(" Typ=" + fieldLength + fieldType);
		if (!EqDataType.isAlpha(fieldType))
		{
			buffer.append("," + fieldDecimal);
		}
		return buffer.toString();
	}

	/**
	 * Determine if data is supposed to be displayed as RTL
	 * 
	 * @return true if data is supposed to be displayed as RTL
	 */
	public boolean chkRTL()
	{
		return (orientation == ORIENT_RTL);
	}

	/**
	 * Determine if this is a work field
	 * 
	 * @return true if this is a work field
	 */
	public boolean isWorkField()
	{
		return workField;
	}

	/**
	 * Set if this is a work field
	 * 
	 * @param workField
	 *            - true if this a work field
	 */
	public void setWorkField(boolean workField)
	{
		this.workField = workField;
	}

	/**
	 * Determine if value has been set to the description
	 * 
	 * @return true if value has been set to the description
	 */
	public boolean isShowDescAsValue()
	{
		return showDescAsValue;
	}

	/**
	 * Set if value has been set to the description
	 * 
	 * @param showDescAsValue
	 *            - true if value has been set to the description
	 */
	public void setShowDescAsValue(boolean showDescAsValue)
	{
		this.showDescAsValue = showDescAsValue;
	}

	/**
	 * @param repeating
	 *            the repeating to set
	 */
	public void setRepeating(boolean repeating)
	{
		this.repeating = repeating;
	}

	/**
	 * @return the repeating
	 */
	public boolean isRepeating()
	{
		return repeating;
	}
}
