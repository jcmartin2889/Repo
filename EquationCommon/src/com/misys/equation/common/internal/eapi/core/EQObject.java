/*
 * Created on 17-Jan-06
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.misys.equation.common.internal.eapi.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/***********************************************************************************************************************************
 * Class for storing the non-control data for an Equation API.
 * <P>
 * An API primariliy consists of a set of fields. These fields can be accesed through the getField, getFieldValue, setFieldValue
 * methods. Some APIs have an additional EQList object which contains a repeating set of rows of further fields. These fields can be
 * accessed through the corresponding methods on the list object. The list object itself is accessed through the getList method.
 * <P>
 * The fields used in an API and other API definition information is stored in an EQObjectMetaData object. This can be accessed
 * through the accessor method getMetaData.
 * <P>
 * EQTransaction, EQEnquiry, and EQPrompt implement EQObject. For examples of how the underlying EQObjects are accessed see the
 * samples.
 * <P>
 * 
 * @see com.misys.equation.ebs.samples.EQTransactionSample
 * @see com.misys.equation.ebs.samples.EQEnquirySample
 * @see com.misys.equation.ebs.samples.EQPromptSample
 * @author Misys International Banking Systems Ltd.
 */
public interface EQObject
{
	// Status Constants
	/*******************************************************************************************************************************
	 * Status constant - call - a fatal error has occured.
	 */
	public static final int STATUS_FAILED = -1;
	/*******************************************************************************************************************************
	 * Status constant - call - the call succeeded.
	 */
	public static final int STATUS_SUCCESS = 0;
	/*******************************************************************************************************************************
	 * Status constant - object - the object has been constructed but no properties have been set, and no call has been made.
	 */
	public static final int STATUS_UNINITIALISED = 1;
	/*******************************************************************************************************************************
	 * Status constant - object - some properties set, validity unknown.
	 */
	public static final int STATUS_INCOMPLETE = 2;
	/*******************************************************************************************************************************
	 * Status constant - object - a supervisor override is required, via the accept method.
	 */
	public static final int STATUS_REFER = 3;
	/*******************************************************************************************************************************
	 * 
	 * Status constant - object - the object has errors.
	 */
	public static final int STATUS_ERRORS = 4;
	/*******************************************************************************************************************************
	 * Status constant - object - the object has warnings.
	 */
	public static final int STATUS_WARNINGS = 5;
	/*******************************************************************************************************************************
	 * Status constant - object - the object has informational messages.
	 */
	public static final int STATUS_INFO = 6;
	/*******************************************************************************************************************************
	 * Status constant - object - the object is valid and a retrieval or update can proceed.
	 */
	public static final int STATUS_VALID = 7;
	/*******************************************************************************************************************************
	 * Status constant - object - the object has been completed its task succesfully. For an Enquiry this means that the retrieval
	 * completed. For a transaction this means that the database was succesfully updated.
	 */
	public static final int STATUS_COMPLETE = 8;
	/*******************************************************************************************************************************
	 * Status constant - object - a prompt has been actioned.
	 */
	public static final int STATUS_PROMPTED = 10;
	/*******************************************************************************************************************************
	 * Status constant - object - the database object was modified between the read and write.
	 */
	public static final int STATUS_INT_UPDATE_ERROR = 11;
	/*******************************************************************************************************************************
	 * Status constant - object - the object has been retrieved from the database.
	 */
	public static final int STATUS_DATA_RETRIEVED = 12;
	/*******************************************************************************************************************************
	 * Status constant - object - the supervisor has rejected the transaction.
	 */
	public static final int STATUS_REJECTED = 13;
	/*******************************************************************************************************************************
	 * Status constant - object - an SQL error has occurred.
	 */
	public static final int STATUS_SQL_ERROR = 14;
	/*******************************************************************************************************************************
	 * Get the list of messages for the object.
	 * <P>
	 * 
	 * @return an array list of EQMessages for the object. EQMessages can be either informational, warning or error messages.
	 */
	public abstract ArrayList<EQMessage> getMessages();
	/*******************************************************************************************************************************
	 * Get the string representation of the current status of the object.
	 * <P>
	 * 
	 * @return the string representation of the status code.
	 */
	public abstract String getStatusString();
	/*******************************************************************************************************************************
	 * Get the field object for a specific field in the function.
	 * <P>
	 * Note this method does not return any of the fields contained in any list in the fucntion. Use getList to obtain the list.
	 * <P>
	 * 
	 * @param fieldID
	 *            the name of the field required.
	 * @return the field object if found, null otherwise
	 */
	public abstract EQField getField(String fieldID);
	/*******************************************************************************************************************************
	 * Get the HashMap of all fields in the function.
	 * <P>
	 * Note this method does not return fields contained in any lists in the fucntion. Use getList, getRow and getRowFields to
	 * obtain the fields involved in any lists. list.
	 * <P>
	 * 
	 * @return a HashMap of all fields in the function
	 */
	public abstract HashMap<String, EQField> getFields();
	/*******************************************************************************************************************************
	 * Get the value of a specific field in the function.
	 * <P>
	 * Note this method does not return any values for the fields contained in any list in the fucntion. Use getList to obtain the
	 * list.
	 * <P>
	 * Note this method is equivalent to <code>getField(String).getValue()</code>.
	 * <P>
	 * 
	 * @param fieldID
	 *            the name of the field value required.
	 * @return the value of the field.
	 */
	public abstract String getFieldValue(String fieldID);
	/*******************************************************************************************************************************
	 * Get any list associated with the equation enquiry, transaction or prompt.
	 * <P>
	 * 
	 * @return the list, or null if no list is used.
	 */
	public abstract EQList getList();
	/*******************************************************************************************************************************
	 * Determine if the function has been modified.
	 * <P>
	 * Note that the function and list only track modification if the application sets field values through the function or list's
	 * methods. Not if field objects are modified directly.
	 * <P>
	 * 
	 * @return whether the function contains data that has not been saved. This is true when the object has had fields set but has
	 *         not yet been written to the database. This also applies to key fields in an enquiry or prompt.
	 */
	public abstract boolean isModified();
	/*******************************************************************************************************************************
	 * Determine if the object can be modified.
	 * <P>
	 * 
	 * @return whether the object is read only, i.e. fields can not be modified. This is true when a transaction has completed.
	 */
	public abstract boolean isReadOnly();
	/*******************************************************************************************************************************
	 * Set any of the object's fields from a map.
	 * <P>
	 * The map should contain a set of field name/value pairs. The object will examine the map for fields it supports and set its
	 * internal values for any fields found to the values in the map.
	 * <P>
	 * The map must have a key of String, and the values must be String[]. Typically, this is passed in an HttpRequest.
	 * <P>
	 * List fields need to have a key of "List:" followed by row number followed by ':' followed by field name (e.g. "List:1:ZLAB")
	 * <P>
	 * 
	 * @param parameters
	 *            the map of field values to be set in the object.
	 * @return true if any fields were found and used.
	 */
	public abstract boolean setFieldValue(Map<String, String[]> parameters);
	/*******************************************************************************************************************************
	 * Set the value of a specific field in the API.
	 * <P>
	 * Note this method does not return any values for the fields contained in any list in the fucntion. Use getList to obtain the
	 * list.
	 * <P>
	 * 
	 * @param fieldID
	 *            the name of the field whose value is to be set.
	 * @param fieldValue
	 *            the new value for the field.
	 * @return whether the value modified the field or not (false if it was the same). If this returns true this indicated that the
	 *         function has also been marked invalid and modified.
	 */
	public abstract boolean setFieldValue(String fieldID, String fieldValue);
	/*******************************************************************************************************************************
	 * Return the current status of the object, one of the STATUS constants.
	 * <P>
	 * 
	 * @return the object status resulting from it's creation or last call.
	 */
	public abstract int getStatus();
	/*******************************************************************************************************************************
	 * Get the object identifier.
	 * <P>
	 * 
	 * @return the identifier for the API
	 */
	public abstract String getIdentifier();
	/*******************************************************************************************************************************
	 * Get the EQObjectMetaData associated with the Equation object.
	 * <P>
	 * 
	 * @return the EQObjectMetaData associated with the Equation object.
	 */
	public EQObjectMetaData getMetaData();
	/*******************************************************************************************************************************
	 * Specifies that the fields of the function can no longer be modified.
	 * <P>
	 * This is done automatically when a succesful database update has occured.
	 * <P>
	 * However, reset can be used to reinitialise the object.
	 */
	public abstract void setReadOnly();
}