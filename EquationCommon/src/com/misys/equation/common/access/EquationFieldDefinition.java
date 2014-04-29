package com.misys.equation.common.access;

import java.io.Serializable;

import com.misys.equation.common.utilities.EqDataType;

/**
 * This class represents a field definition for the Equation Data Structure
 * 
 */
@SuppressWarnings("serial")
public class EquationFieldDefinition implements Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationFieldDefinition.java 10420 2011-02-03 12:22:26Z MACDONP1 $";
	private String fieldName;
	private String fieldLabel;
	private int fieldDataType;
	private int fieldStartIndex;
	private int fieldLength;
	private int byteLength;
	private int fieldDecimal;
	private String fieldDataTypeString;
	private String fieldType;

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
	 */
	public EquationFieldDefinition(String fieldName, int fieldDataType, int fieldStart, int fieldLength, int fieldDecimal,
					String fieldLabel, String fieldDataTypeString)
	{
		this.fieldName = fieldName;
		this.fieldDataType = fieldDataType;
		this.fieldStartIndex = fieldStart;
		this.fieldDecimal = fieldDecimal;
		this.fieldLabel = fieldLabel;
		this.fieldDataTypeString = fieldDataTypeString;
		this.fieldType = "";
		this.fieldLength = fieldLength;
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
	 * Return the byte length
	 * 
	 * @return the byte length
	 */
	public int getByteLength()
	{
		return byteLength;
	}

	/**
	 * Set the byte length
	 * 
	 * @param byteLength
	 *            - the byte length
	 */
	public void setByteLength(int byteLength)
	{
		this.byteLength = byteLength;
	}

	/**
	 * Return the data type text
	 * 
	 * @return the data type text
	 */
	public String rtvDataTypeString()
	{
		StringBuilder buffer = new StringBuilder();

		// length and decimal
		if (fieldDataTypeString.equals(EqDataType.TYPE_PACKED))
		{
			buffer.append(byteLength);
			buffer.append(EqDataType.TYPE_PACKED);
			buffer.append(" ");
			buffer.append("(");
			buffer.append(fieldLength);
			buffer.append(",");
			buffer.append(fieldDecimal);
			buffer.append(")");
		}
		else if (fieldDataTypeString.equals(EqDataType.TYPE_ZONED))
		{
			buffer.append(byteLength);
			buffer.append(EqDataType.TYPE_ZONED);
			buffer.append(" ");
			buffer.append("(");
			buffer.append(fieldLength);
			buffer.append(",");
			buffer.append(fieldDecimal);
			buffer.append(")");
		}
		else
		{
			buffer.append(byteLength);
			buffer.append(EqDataType.TYPE_CHAR);
		}

		return buffer.toString();
	}

	/**
	 * Return the reference field type (REFFLD in the DDS)
	 * 
	 * @return a String containing the name of the referenced field
	 */
	public String getFieldType()
	{
		return fieldType;
	}

	/**
	 * Set the reference field type (REFFLD in the DDS)
	 * 
	 * @param fieldType
	 *            - the reference field type
	 */
	public void setFieldType(String fieldType)
	{
		this.fieldType = fieldType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + byteLength;
		result = prime * result + fieldDataType;
		result = prime * result + ((fieldDataTypeString == null) ? 0 : fieldDataTypeString.hashCode());
		result = prime * result + fieldDecimal;
		result = prime * result + ((fieldLabel == null) ? 0 : fieldLabel.hashCode());
		result = prime * result + fieldLength;
		result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
		result = prime * result + fieldStartIndex;
		result = prime * result + ((fieldType == null) ? 0 : fieldType.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		EquationFieldDefinition other = (EquationFieldDefinition) obj;
		if (byteLength != other.byteLength)
		{
			return false;
		}
		if (fieldDataType != other.fieldDataType)
		{
			return false;
		}
		if (fieldDataTypeString == null)
		{
			if (other.fieldDataTypeString != null)
			{
				return false;
			}
		}
		else if (!fieldDataTypeString.equals(other.fieldDataTypeString))
		{
			return false;
		}
		if (fieldDecimal != other.fieldDecimal)
		{
			return false;
		}
		if (fieldLabel == null)
		{
			if (other.fieldLabel != null)
			{
				return false;
			}
		}
		else if (!fieldLabel.equals(other.fieldLabel))
		{
			return false;
		}
		if (fieldLength != other.fieldLength)
		{
			return false;
		}
		if (fieldName == null)
		{
			if (other.fieldName != null)
			{
				return false;
			}
		}
		else if (!fieldName.equals(other.fieldName))
		{
			return false;
		}
		if (fieldStartIndex != other.fieldStartIndex)
		{
			return false;
		}
		if (fieldType == null)
		{
			if (other.fieldType != null)
			{
				return false;
			}
		}
		else if (!fieldType.equals(other.fieldType))
		{
			return false;
		}
		return true;
	}

	/**
	 * Return the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		return fieldName + " " + fieldLabel + "[SQL Type=" + fieldDataType + "[Data Type=" + fieldDataTypeString + "][Index="
						+ fieldStartIndex + "][Length=" + fieldLength + "][Bytes" + byteLength + "][Decimal=" + fieldDecimal + "]";
	}
}
