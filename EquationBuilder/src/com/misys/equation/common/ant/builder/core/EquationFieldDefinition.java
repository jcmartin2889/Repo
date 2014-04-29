package com.misys.equation.common.ant.builder.core;

/**
 * This class represents a field definition for the Equation Data Structure
 * 
 */
public class EquationFieldDefinition
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationFieldDefinition.java 4741 2009-09-16 16:33:23Z esther.williams $";
	private String fieldName;
	private String fieldLabel;
	private int fieldDataType;
	private int fieldStartIndex;
	private int fieldLength;
	private int internalFieldLength;
	private int fieldDecimal;
	private String fieldDataTypeString;

	/**
	 * Create an empty field definition for the bean
	 */
	public EquationFieldDefinition()
	{
		super();
	}

	/**
	 * Create a field definition
	 * 
	 * @param fieldName
	 *            - field name
	 * @param fieldDataType
	 *            - field type
	 * @param fieldLength
	 *            - field length
	 * @param fieldDecimal
	 *            - field decimal
	 * @param fieldLabel
	 *            - field label
	 * @param fieldDataTypeString
	 *            - field data type string
	 * @param fieldType
	 *            0 field type
	 */
	public EquationFieldDefinition(String fieldName, int fieldDataType, int fieldStart, int fieldLength, int fieldDecimal,
					String fieldLabel, String fieldDataTypeString, String fieldType)
	{
		this.fieldName = fieldName;
		this.fieldDataType = fieldDataType;
		this.fieldStartIndex = fieldStart;
		this.fieldDecimal = fieldDecimal;
		this.fieldLabel = fieldLabel;
		this.fieldDataTypeString = fieldDataTypeString;
		setFieldLength(fieldLength);
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
	 * Set the field name
	 * 
	 * @param fieldName
	 *            -the field name
	 */
	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
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

		if (fieldDataTypeString.equals(EqDataType.TYPE_PACKED))
		{
			this.internalFieldLength = (fieldLength + 1) / 2;
		}
		else
		{
			this.internalFieldLength = fieldLength;
		}
	}

	/**
	 * Return the field type
	 * 
	 * @return the field type
	 */
	public int getFieldDataType()
	{
		return fieldDataType;
	}

	/**
	 * Set the field type
	 * 
	 * @param fieldDataType
	 *            - the field type
	 */
	public void setFieldDataType(int fieldDataType)
	{
		this.fieldDataType = fieldDataType;
	}

	/**
	 * Return the field label
	 * 
	 * @return the field label
	 */
	public String getFieldLabel()
	{
		return fieldLabel;
	}

	/**
	 * Set the field label
	 * 
	 * @param fieldLabel
	 *            - the field label
	 */
	public void setFieldLabel(String fieldLabel)
	{
		this.fieldLabel = fieldLabel;
	}

	/**
	 * Return the field start position
	 * 
	 * @return the field start position
	 */
	public int getFieldStartIndex()
	{
		return fieldStartIndex;
	}

	/**
	 * Set the field start position
	 * 
	 * @param fieldStartIndex
	 *            - the field start position
	 */
	public void setFieldStartIndex(int fieldStartIndex)
	{
		this.fieldStartIndex = fieldStartIndex;
	}

	/**
	 * Return the number of decimal places
	 * 
	 * @return the number of decimal places
	 */
	public int getFieldDecimal()
	{
		return fieldDecimal;
	}

	/**
	 * Set the number of decimal places
	 * 
	 * @param fieldDecimal
	 *            - the number of decimal places
	 */
	public void setFieldDecimal(int fieldDecimal)
	{
		this.fieldDecimal = fieldDecimal;
	}

	/**
	 * Return the field data type in string (A, S, P)
	 * 
	 * @return the field data type in string (A, S, P)
	 */
	public String getFieldDataTypeString()
	{
		return fieldDataTypeString;
	}

	/**
	 * Set the field data type in string (A, S, P)
	 * 
	 * @param fieldDataTypeString
	 *            - the field data type in string (A, S, P)
	 */
	public void setFieldDataTypeString(String fieldDataTypeString)
	{
		this.fieldDataTypeString = fieldDataTypeString;
	}

	/**
	 * Return the internal field length
	 * 
	 * @return the internal field length
	 */
	public int getInternalFieldLength()
	{
		return internalFieldLength;
	}

	/**
	 * Set the internal field length
	 * 
	 * @param internalFieldLength
	 *            - the internal field length
	 */
	public void setInternalFieldLength(int internalFieldLength)
	{
		this.internalFieldLength = internalFieldLength;
	}

}
