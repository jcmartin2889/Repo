package com.misys.equation.common.ant.builder.core;

public interface IEquationStandardObject
{
	// transaction modes
	public final static String FCT_ADD = "A";
	public final static String FCT_DEL = "D";
	public final static String FCT_MNT = "M";
	public final static String FCT_RTV = "B";
	public final static String FCT_VAL = "V";
	public final static String FCT_FUL = "F";
	public final static String FCT_ENQ = "E";
	public final static String FCT_XCL = "XCL";

	// transaction types
	public static final String TYPE_TRANSACTION = "";
	public static final String TYPE_ENQUIRY = "E";
	public static final String TYPE_TABLE = "T";
	public static final String TYPE_PV = "PV";

	/**
	 * Set the unique id
	 * 
	 * @return the unique id
	 */
	public void setId(String id);

	/**
	 * Return the Id of the transaction
	 * 
	 * @return the Id of the transaction
	 */
	public String getId();

	/**
	 * Return the field value of the specified field name
	 * 
	 * @param fieldName
	 *            - the field name
	 * 
	 * @return the field value
	 */
	public String getFieldValue(String fieldName);

	/**
	 * Set the field value of the specified field name
	 * 
	 * @param fieldName
	 *            - field name
	 * @param fieldValue
	 *            - field value
	 */
	public void setFieldValue(String fieldName, String fieldValue);

	/**
	 * Return if the transaction has been successfully executed or not
	 * 
	 * @return true if transaction is successful
	 */
	public boolean getValid();

	/**
	 * Set that the transaction is valid
	 * 
	 * @return true if transaction is valid
	 */
	public void setValid(boolean valid);

	/**
	 * Return a byte representation of the object
	 * 
	 * @return the bytes
	 */
	public byte[] getBytes();

	/**
	 * Return the transaction mode
	 * 
	 * @return the transaction mode
	 */
	public String getMode();

	/**
	 * Set the transaction mode
	 * 
	 * @param mode
	 *            - the trasaction mode
	 */
	public void setMode(String mode);

}
