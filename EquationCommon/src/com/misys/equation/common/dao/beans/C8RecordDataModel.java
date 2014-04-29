package com.misys.equation.common.dao.beans;

public class C8RecordDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: C8RecordDataModel.java 5818 2010-01-13 16:07:25Z perkinj1 $";

	private static final long serialVersionUID = 1L;
	private final static String RECORD_NAME = "C8PF";

	private String currencyMnem; // C8CCY
	private String currencyNum; // C8CCYN
	private String swiftCode; // C8SCY
	private String editField; // C8CED
	private String currencyName; // C8CUR

	/**
	 * Default constructor
	 */
	public C8RecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);

	}

	/**
	 * Construct a new HA record
	 * 
	 * @param languageCode
	 *            - the language code
	 * @param fileKey
	 *            - the file key
	 */
	public C8RecordDataModel(String currencyMnem)
	{
		super();
		setEqFileName(RECORD_NAME);
		this.currencyMnem = currencyMnem;
	}

	// ---getters and setters----//

	/**
	 * Return the currency mnemonic
	 * 
	 * @return the currency mnemonic
	 */
	public String getCurrencyMnem()
	{
		return currencyMnem;
	}

	/**
	 * Set the currency mnemonic
	 * 
	 * @param currencyMnem
	 *            - the currency mnemonic
	 */
	public void setCurrencyMnem(String currencyMnem)
	{
		this.currencyMnem = currencyMnem;
	}

	/**
	 * Return the currency number
	 * 
	 * @return the currency number
	 */
	public String getCurrencyNum()
	{
		return currencyNum;
	}

	/**
	 * Set the currency number
	 * 
	 * @param currencyNum
	 *            - the currency number
	 */
	public void setCurrencyNum(String currencyNum)
	{
		this.currencyNum = currencyNum;
	}

	/**
	 * Return the SWIFT currency code
	 * 
	 * @return the SWIFT currency code
	 */
	public String getSwiftCode()
	{
		return swiftCode;
	}

	/**
	 * Set the SWIFT currency code
	 * 
	 * @param swiftCode
	 *            - the SWIFT currency code
	 */
	public void setSwiftCode(String swiftCode)
	{
		this.swiftCode = swiftCode;
	}

	/**
	 * Return the currency edit field
	 * 
	 * @return the currency edit field
	 */
	public String getEditField()
	{
		return editField;
	}

	/**
	 * Set the currency edit field
	 * 
	 * @param editField
	 *            - the currency edit field
	 */
	public void setEditField(String editField)
	{
		this.editField = editField;
	}

	/**
	 * Return the currency name
	 * 
	 * @return the currency name
	 */
	public String getCurrencyName()
	{
		return currencyName;
	}

	/**
	 * Set the currency name
	 * 
	 * @param currencyName
	 *            - the currency name
	 */
	public void setCurrencyName(String currencyName)
	{
		this.currencyName = currencyName;
	}

}
