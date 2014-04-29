package com.misys.equation.common.dao.beans;

import java.math.BigDecimal;

/**
 * ACMRecord data-model.
 * 
 * @author deroset
 * 
 */
public class ACMRecordDataModel extends AbsRecord
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACMRecordDataModel.java 5018 2009-10-02 12:54:54Z lima12 $";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static String RECORD_NAME = "ACMPF";

	private String typeName; // ACMFLD
	private String description; // ACMDSC
	private String dataType; // ACMTYPE
	private boolean upperCase; // ACMUPP
	private int length; // ACMLTH
	private int decimals; // ACMDEC
	private String initialValue; // ACMINT
	private String minLength; // ACMMAX
	private String maxLength; // ACMMIN
	private String validValues; // ACMVAL
	private String regEx; // ACMREG
	private String promptProgram; // ACMPMT
	private String validationProgram; // ACMVLD
	private String validationProgramDescription; // GAEFNM

	/**
	 * Default constructor
	 */
	public ACMRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);

	}

	// ---getters and setters----//

	/**
	 * Return the field name
	 * 
	 * @return the field name
	 */
	public String getTypeName()
	{
		return typeName;
	}
	/**
	 * Set the field name
	 * 
	 * @param typeName
	 *            - the field name
	 */
	public void setTypeName(String typeName)
	{
		this.typeName = typeName.trim();
	}

	/**
	 * Return the description
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Set the description
	 * 
	 * @param description
	 *            - the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description.trim();
	}

	/**
	 * Return the data type
	 * 
	 * @return the data type
	 */
	public String getDataType()
	{
		return dataType;
	}

	/**
	 * Set the data type
	 * 
	 * @param dataType
	 *            - the data type
	 */
	public void setDataType(String dataType)
	{
		this.dataType = dataType.trim();
	}

	/**
	 * Return the upper case flag
	 * 
	 * @return true if it must be in upper case
	 */
	public boolean isUpperCase()
	{
		return upperCase;
	}

	/**
	 * Return the upper case flag
	 * 
	 * @return the upper case flag
	 */
	public String getUpperCase()
	{
		if (upperCase)
		{
			return "T";
		}
		else
		{
			return "F";
		}

	}

	/**
	 * Set the upper case flag
	 * 
	 * @param upperCase
	 *            - the upper case flag
	 */
	public void setUpperCase(boolean upperCase)
	{
		this.upperCase = upperCase;
	}

	/**
	 * Return the length
	 * 
	 * @return the length
	 */
	public int getLength()
	{
		return length;
	}

	/**
	 * Set the length
	 * 
	 * @param length
	 *            the length to set
	 */
	public void setLength(int length)
	{
		this.length = length;
	}

	/**
	 * Return the number of decimal
	 * 
	 * @return the number of decimal
	 */
	public int getDecimals()
	{
		return decimals;
	}

	/**
	 * Set the number of decimal
	 * 
	 * @param decimals
	 *            - the number of decimal
	 */
	public void setDecimals(int decimals)
	{
		this.decimals = decimals;
	}

	/**
	 * Return the initial value
	 * 
	 * @return the initial value
	 */
	public String getInitialValue()
	{
		return initialValue;
	}

	/**
	 * Set the initial value
	 * 
	 * @param initialValue
	 *            - the initial value
	 */
	public void setInitialValue(String initialValue)
	{
		this.initialValue = initialValue;
	}

	/**
	 * Return the minimum length
	 * 
	 * @return the minimum length
	 */
	public String getMinLength()
	{
		return minLength;
	}

	/**
	 * Set the minimum length
	 * 
	 * @param minLength
	 *            - the minimum length
	 */
	public void setMinLength(BigDecimal minLength)
	{
		if (minLength.doubleValue() == 0)
		{
			this.minLength = "";

		}
		else if (BigDecimal.ZERO.equals(minLength))
		{
			this.minLength = "";
		}
		else
		{
			this.minLength = minLength.toPlainString();
		}
	}

	/**
	 * Return the maximum length
	 * 
	 * @return the maximum length
	 */
	public String getMaxLength()
	{
		return maxLength;
	}

	/**
	 * Set the maximum length
	 * 
	 * @param maxLength
	 *            - the maximum length
	 */
	public void setMaxLength(BigDecimal maxLength)
	{
		if (maxLength.doubleValue() == 0)
		{
			this.maxLength = "";
		}
		else if (BigDecimal.ZERO.equals(maxLength))
		{
			this.maxLength = "";
		}
		else
		{
			this.maxLength = maxLength.toPlainString();
		}
	}

	/**
	 * Return the valid values
	 * 
	 * @return the valid values
	 */
	public String getValidValues()
	{
		return validValues;
	}

	/**
	 * Set the valid values
	 * 
	 * @param validValues
	 *            - the valid values to set
	 */
	public void setValidValues(String validValues)
	{
		this.validValues = validValues.trim();

	}

	/**
	 * Return the regular expression
	 * 
	 * @return the regular expression
	 */
	public String getRegEx()
	{
		return regEx;
	}

	/**
	 * Set the regular expression
	 * 
	 * @param regEx
	 *            - the regular expression
	 */
	public void setRegEx(String regEx)
	{
		this.regEx = regEx.trim();
	}

	/**
	 * Return the validation program
	 * 
	 * @return the validation program
	 */
	public String getValidationProgram()
	{
		return validationProgram;
	}

	/**
	 * Set the validation program
	 * 
	 * @param validationProgram
	 *            - the validation program
	 */
	public void setValidationProgram(String validationProgram)
	{
		this.validationProgram = validationProgram.trim();
	}

	/**
	 * Return the validation program description
	 * 
	 * @return the validation program description
	 */
	public String getValidationProgramDescription()
	{
		return validationProgramDescription;
	}

	/**
	 * Set the validation program description
	 * 
	 * @param validationProgramDescription
	 *            - the validation program description
	 */
	public void setValidationProgramDescription(String validationProgramDescription)
	{
		this.validationProgramDescription = (validationProgramDescription == null) ? "Not found" : validationProgramDescription
						.trim();
	}

	/**
	 * Return the prompt program
	 * 
	 * @return the prompt program
	 */
	public String getPromptProgram()
	{
		return promptProgram;
	}

	/**
	 * Set the prompt program
	 * 
	 * @param promptProgram
	 *            - the prompt program
	 */
	public void setPromptProgram(String promptProgram)
	{
		this.promptProgram = promptProgram.trim();
	}

}
