package com.misys.equation.common.dao.beans;

/**
 * GYWPF Data model
 * 
 */
public class BDRecordDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BDRecordDataModel.java 14832 2012-11-05 19:03:33Z williae1 $";

	private static final long serialVersionUID = 1L;
	private final static String RECORD_NAME = "BDPF";

	public static String ITEM_MANDATORY = "M";
	public static String ITEM_OPTIONAL = "O";

	private String itemName; // BDCITM
	private String equationFile; // BDEQF
	private String itemDescription; // BDDES
	private String dataTypeCode; // BDDTCO
	private int screenFieldLength; // BDFLEN
	private String mandatory; // BDMOOP
	private String userValidationProgram; // BDVPRG
	private String uniqueCustomerIdentifier; // BDUNIQ
	private String editCode; // BDEDIT
	private int internalItemNumber; // BDIIN
	private int endPosition; // BDEPOS
	private int startPosition; // BDSPOS
	private String fieldType; // BDFTYP
	private int fieldLength; // BDDFLN
	private String selectionCode; // BDSLCD
	private String currencyOfAmount; // BDCCY
	private String signOfAmount; // BDNEGP
	private String displayOnStatementEnqs; // BDPRNT
	private String validateESFLimitAmount; // VAMT

	/**
	 * Default constructor
	 */
	public BDRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);

	}

	/**
	 * Construct a new BD record key
	 * 
	 * @param itemName
	 *            - the item name
	 */
	public BDRecordDataModel(String itemName)
	{
		super();
		setEqFileName(RECORD_NAME);

		setItemName(itemName);
	}

	/**
	 * Return the String representation
	 */
	@Override
	public String toString()
	{
		return itemName + " " + equationFile + " " + itemDescription;
	}

	// ---getters and setters----//

	/**
	 * Return the Equation file code
	 * 
	 * @return the Equation file code
	 */
	public String getEquationFile()
	{
		return equationFile;
	}

	/**
	 * Set the Equation file code
	 * 
	 * @param equationFile
	 *            - the Equation file code
	 */
	public void setEquationFile(String equationFile)
	{
		this.equationFile = equationFile;
	}

	/**
	 * Return the item name
	 * 
	 * @return the item name
	 */
	public String getItemName()
	{
		return itemName;
	}

	/**
	 * Set the item name
	 * 
	 * @param itemName
	 *            - the item name
	 */
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	/**
	 * Return the item description
	 * 
	 * @return the item description
	 */
	public String getItemDescription()
	{
		return itemDescription;
	}

	/**
	 * Set the item description
	 * 
	 * @param itemDescription
	 *            - the item description
	 */
	public void setItemDescription(String itemDescription)
	{
		this.itemDescription = itemDescription;
	}

	/**
	 * Return the data type code
	 * 
	 * @return the data type code
	 */
	public String getDataTypeCode()
	{
		return dataTypeCode;
	}

	/**
	 * Set the data type code
	 * 
	 * @param dataTypeCode
	 *            - the data type code
	 */
	public void setDataTypeCode(String dataTypeCode)
	{
		this.dataTypeCode = dataTypeCode;
	}

	/**
	 * Return the screen field length
	 * 
	 * @return the screen field length
	 */
	public int getScreenFieldLength()
	{
		return screenFieldLength;
	}

	/**
	 * Set the screen field length
	 * 
	 * @param screenFieldLength
	 *            - the screen field length
	 */
	public void setScreenFieldLength(int screenFieldLength)
	{
		this.screenFieldLength = screenFieldLength;
	}

	/**
	 * Return whether mandatory or optional
	 * 
	 * @return whether mandatory or optional
	 */
	public String getMandatory()
	{
		return mandatory;
	}

	/**
	 * Set whether mandatory or optional
	 * 
	 * @param mandatory
	 *            - whether mandatory or optional
	 */
	public void setMandatory(String mandatory)
	{
		this.mandatory = mandatory;
	}

	/**
	 * Return true if mandatory
	 * 
	 * @return true if mandatory
	 */
	public boolean isMandatory()
	{
		return this.mandatory.equals(ITEM_MANDATORY);
	}

	/**
	 * Return the user validation program
	 * 
	 * @return the user validation program
	 */
	public String getUserValidationProgram()
	{
		return userValidationProgram;
	}

	/**
	 * Set the user validation program
	 * 
	 * @param userValidationProgram
	 *            - the user validation program
	 */
	public void setUserValidationProgram(String userValidationProgram)
	{
		this.userValidationProgram = userValidationProgram;
	}

	/**
	 * Return the unique customer identifier
	 * 
	 * @return the unique customer identifier
	 */
	public String getUniqueCustomerIdentifier()
	{
		return uniqueCustomerIdentifier;
	}

	/**
	 * Set the unique customer identifier
	 * 
	 * @param uniqueCustomerIdentifier
	 *            - the unique customer identifier
	 */
	public void setUniqueCustomerIdentifier(String uniqueCustomerIdentifier)
	{
		this.uniqueCustomerIdentifier = uniqueCustomerIdentifier;
	}

	/**
	 * Return the edit code
	 * 
	 * @return the edit code
	 */
	public String getEditCode()
	{
		return editCode;
	}

	/**
	 * Set the edit code
	 * 
	 * @param editCode
	 *            - the edit code
	 */
	public void setEditCode(String editCode)
	{
		this.editCode = editCode;
	}

	/**
	 * Return the internal item number
	 * 
	 * @return the internal item number
	 */
	public int getInternalItemNumber()
	{
		return internalItemNumber;
	}

	/**
	 * Set the internal item number
	 * 
	 * @param internalItemNumber
	 *            - the internal item number
	 */
	public void setInternalItemNumber(int internalItemNumber)
	{
		this.internalItemNumber = internalItemNumber;
	}

	/**
	 * Return the end position
	 * 
	 * @return the end position
	 */
	public int getEndPosition()
	{
		return endPosition;
	}

	/**
	 * Set the end position
	 * 
	 * @param endPosition
	 *            - the end position
	 */
	public void setEndPosition(int endPosition)
	{
		this.endPosition = endPosition;
	}

	/**
	 * Return the start position
	 * 
	 * @return the start position
	 */
	public int getStartPosition()
	{
		return startPosition;
	}

	/**
	 * Set the start position
	 * 
	 * @param startPosition
	 *            - the start position
	 */
	public void setStartPosition(int startPosition)
	{
		this.startPosition = startPosition;
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
	 * Set the field type
	 * 
	 * @param fieldType
	 *            - the field type
	 */
	public void setFieldType(String fieldType)
	{
		this.fieldType = fieldType;
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
	 * Return the selection code
	 * 
	 * @return the selection code
	 */
	public String getSelectionCode()
	{
		return selectionCode;
	}

	/**
	 * Set the selection code
	 * 
	 * @param selectionCode
	 *            - the selection code
	 */
	public void setSelectionCode(String selectionCode)
	{
		this.selectionCode = selectionCode;
	}

	/**
	 * Return the currency of amount
	 * 
	 * @return the currency of amount
	 */
	public String getCurrencyOfAmount()
	{
		return currencyOfAmount;
	}

	/**
	 * Set the currency of amount
	 * 
	 * @param currencyOfAmount
	 *            - the currency of amount
	 */
	public void setCurrencyOfAmount(String currencyOfAmount)
	{
		this.currencyOfAmount = currencyOfAmount;
	}

	/**
	 * Return the sign of amount
	 * 
	 * @return the sign of amount
	 */
	public String getSignOfAmount()
	{
		return signOfAmount;
	}

	/**
	 * Set the sign of amount
	 * 
	 * @param signOfAmount
	 *            - the sign of amount
	 */
	public void setSignOfAmount(String signOfAmount)
	{
		this.signOfAmount = signOfAmount;
	}

	/**
	 * Return the display on statement enquiry flag
	 * 
	 * @return the display on statement enquiry flag
	 */
	public String getDisplayOnStatementEnqs()
	{
		return displayOnStatementEnqs;
	}

	/**
	 * Set the display on statement enquiry flag
	 * 
	 * @param displayOnStatementEnqs
	 *            - the display on statement enquiry flag
	 */
	public void setDisplayOnStatementEnqs(String displayOnStatementEnqs)
	{
		this.displayOnStatementEnqs = displayOnStatementEnqs;
	}

	/**
	 * Return the validate ESF limit amount flag
	 * 
	 * @return the validate ESF limit amount flag
	 */
	public String getValidateESFLimitAmount()
	{
		return validateESFLimitAmount;
	}

	/**
	 * Set the validate ESF limit amount flag
	 * 
	 * @param validateESFLimitAmount
	 *            - the validate ESF limit amount flag
	 */
	public void setValidateESFLimitAmount(String validateESFLimitAmount)
	{
		this.validateESFLimitAmount = validateESFLimitAmount;
	}

}
