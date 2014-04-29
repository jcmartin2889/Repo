package com.misys.equation.common.access;

import java.io.Serializable;
import java.util.List;

import com.misys.equation.common.internal.eapi.core.EQMessage;

/**
 * This class provides constants for EquationStandardObject processing.
 */
public interface IEquationStandardObject extends Serializable
{
	// transaction modes
	/** Add mode */
	public final static String FCT_ADD = "A";
	/** Delete mode */
	public final static String FCT_DEL = "D";
	/** Maintain mode */
	public final static String FCT_MNT = "M";
	/** Retrieve mode */
	public final static String FCT_RTV = "B";
	/** Validate mode */
	public final static String FCT_VAL = "V";
	/** Fully functional mode */
	public final static String FCT_FUL = "F";
	/** Enquiry mode */
	public final static String FCT_ENQ = "E";
	/** Report mode */
	public final static String FCT_RPT = "RPT";
	/** Exclude mode */
	public final static String FCT_XCL = "XCL";
	/** Indicates that the mode for this API is not yet known */
	public static final String FCT_NONE = "";

	// transaction types
	/** An Equation Input Transaction API */
	public static final String TYPE_TRANSACTION = "";
	/** An Equation Enquiry API */
	public static final String TYPE_ENQUIRY = "E";
	/** An Equation List Enquiry API */
	public static final String TYPE_LIST_ENQUIRY = "LE";
	/** An Equation database table API */
	public static final String TYPE_TABLE = "T";
	/** An Equation database table used to obtain a list */
	public static final String TYPE_LIST_TABLE = "LT";
	/** A SQL query used to obtain a list */
	public static final String TYPE_LIST_QUERY = "SQL";
	/** An Equation Prompt and Validate module */
	public static final String TYPE_PV = "PV";
	/** An Equation Report API */
	public static final String TYPE_REPORT = "RPT";
	/** An Equation Service API */
	public static final String TYPE_SERVICE = "SRV";
	/** An Equation Input Transaction API - this is only used for a PV FieldSet */
	public static final String TYPE_TRANSACTION2 = "TRN";
	/** An Equation PV module created via the PV editor */
	public static final String TYPE_PVEDITOR = "PVEDITOR";
	/** A SQL query used to obtain a list */
	public static final String TYPE_SQL_QUERY = "S";
	/** A CRM API */
	public static final String TYPE_CRM = "CRM";

	/**
	 * Set the unique id
	 * 
	 * @equation.external
	 */
	public void setId(String id);

	/**
	 * Return the Id of the transaction
	 * 
	 * @return the Id of the transaction
	 * @equation.external
	 */
	public String getId();

	/**
	 * Return the field value of the specified field name
	 * 
	 * @param fieldName
	 *            - the field name
	 * 
	 * @return the field value
	 * @equation.external
	 */
	public String getFieldValue(String fieldName);

	/**
	 * Set the field value of the specified field name
	 * 
	 * @param fieldName
	 *            - field name
	 * @param fieldValue
	 *            - field value
	 * @equation.external
	 */
	public void setFieldValue(String fieldName, String fieldValue);

	/**
	 * Return the list of messages
	 * 
	 * @return the list of messages
	 * @equation.external
	 */
	public List<EQMessage> getMessages();

	/**
	 * Return if the transaction has been successfully executed or not
	 * 
	 * @return true if transaction is successful
	 * @equation.external
	 */
	public boolean getValid();

	/**
	 * Set that the transaction is valid
	 * 
	 */
	public void setValid(boolean valid);

	/**
	 * Return a byte representation of the object
	 * 
	 * @return the bytes
	 */
	public byte[] getBytes();

	/**
	 * Set the data byte representation of the object
	 * 
	 * @param data
	 *            - the byte representation of the object
	 */
	public void setBytes(byte[] data);

	/**
	 * Return the transaction mode
	 * 
	 * @return the transaction mode
	 * @equation.external
	 */
	public String getMode();

	/**
	 * Set the transaction mode
	 * 
	 * @param mode
	 *            - the transaction mode
	 * @equation.external
	 */
	public void setMode(String mode);

}
