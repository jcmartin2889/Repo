/*
 * Created on 17-Jan-06
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.misys.equation.common.internal.eapi.core;

/***********************************************************************************************************************************
 * Controls an Equation enquiry.
 * <P>
 * An EQEnquiry or subclass instance is created through the createEQObject method of the EQSession class.
 * <P>
 * Field values can then be set in a number of ways. The concrete subclasses only provide get/set methods named after the fields
 * (e.g. getZLAB, setZLAB):
 * <UL>
 * <LI>setFieldsFromParameters( map ). This is useful for setting the fields delivered through an http request.</LI>
 * <LI>setFieldValue( fieldName, fieldValue ). This is useful when setting specific fields directly.</LI>
 * <LI>And lastly by obtaining the EQField object itself through getField( fieldName ) and then using EQField setValue( value ).</LI>
 * </UL>
 * Once the key input fields have been set it is possible to read the database object. This is done through the retrieve method.
 * This will validate the input fields and return the database object if the key is valid. This will be indicated by a return value
 * of true. If false is returned getStatus should be called to determine the reason for failure. Extended error and warning messages
 * can be examined using the getMessages method.
 * <P>
 * See the EQEnquirySample.
 * <P>
 * 
 * @see com.misys.equation.ebs.samples.EQEnquirySample
 * @author Misys International Banking Systems Ltd.
 */
public interface EQEnquiry extends EQObject
{
	/*******************************************************************************************************************************
	 * Call Mode Constant - The call was to retrieve the data from the database.
	 */
	public static final int ACTION_RETRIEVE = 5;
	/*******************************************************************************************************************************
	 * Reset method for the object.
	 */
	public abstract void reset();
	/*******************************************************************************************************************************
	 * Calls the retrieve method in an enquiry.
	 * 
	 * The object should have its data set prior to this method being called.
	 * 
	 * @param session
	 *            the session connected to the server.
	 * @return whether the call succeeded. Use getStatus for detailed status information.
	 * @throws EQException
	 *             if an error occurs during processing
	 */
	public abstract boolean performAction(EQSession session) throws EQException;
	/*******************************************************************************************************************************
	 * Retrieve - calls Equation to obtain data associated with key.
	 * <P>
	 * The object should have its (key) data set prior to this method being called
	 * <P>
	 * Enquiries with list data may take multiple retrievals to get all the list items. The number of list items requested in one
	 * retrieval is configurable. This method's return status will be false and the detailed status will be INCOMPLETE if all list
	 * items have not be returned.
	 * <P>
	 * 
	 * @param session
	 *            the session connected to the server.
	 * @return whether the call succeeded. Use getStatus for detailed status information.
	 * @throws EQException
	 *             if an error occurs during processing
	 * 
	 */
	public abstract boolean retrieve(EQSession session) throws EQException;
	/*******************************************************************************************************************************
	 * 
	 * Get the user identifier of the person who has entered data into the object.
	 * 
	 * @return the user who has entered data into the object
	 */
	public String getAuditUserID();
	/*******************************************************************************************************************************
	 * 
	 * Specifies the user identifier of the person who has entered data into the object.
	 * 
	 * @param auditUserID
	 *            the user who has entered data into the object
	 */
	public void setAuditUserID(String auditUserID);
}