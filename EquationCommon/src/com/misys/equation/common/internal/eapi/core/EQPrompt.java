/*
 * Created on 26-Jan-06
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.misys.equation.common.internal.eapi.core;

/***********************************************************************************************************************************
 * Represents an Equation prompt filter and list.
 * <P>
 * An Equation prompt represents a list of permitted values for a specific Equation object type. Some of these objects (e.g.
 * Account, Customer) are represented by more than one field in a function, others (e.g. Currency, Deal Type) are represented by a
 * single field.
 * <P>
 * The prompt allows an application to retrieve the list of valid values for an object, and further, to refine that list by
 * specifying a filter for key fields on the list.
 * <P>
 * Thus a prompt has a set of fixed input fields which represent the key fields for the filter, and has an EQList object which holds
 * the rows of valid values for the object. Each row has the field values for the object type being prompted, plus additional
 * descriptive field values which allow the user to choose from the list.
 * <P>
 * When a prompt is invoked in the context of an Enquiry or Transacition (through the prompt method) the context of the underlying
 * EQFunction is used to define the filter. A user can be presented with the possible choices and should select a row. The object's
 * setPromptSelection method should then be called.
 * <P>
 * If the prompt is invoked through the EQPromptImpl it is the responsiblity of the application to provide the filter, no context
 * can be provided. Also, the application must map the returned fields in the list into whichever form is required.
 * <P>
 * As the amount of data which could be retireved by a prompt can be very large (consider the Account prompt) only a fixed number of
 * rows are returned in each prompt call. This is configured thought the API_DEFAULT_PROMPT_SIZE value in the Application
 * Environment, but can be overridden through the setPageSize method. (Note Equation may return more than the requested number of
 * rows, but never less).
 * <P>
 * As rows are retrieved they are cached. A call to isComplete can be made to determine if the end of the data has been reached. If
 * not calls to nextPage can be used to increment through the returned data until it returns false. This false value indicates that
 * the page is not cached and a further prompt call is required to retrieve the neccesary data.
 * <P>
 * An EQPromptImpl or subclass instance is created through the createEQObject method of the EQSession class.
 * <P>
 * Field values can then be set in a number of ways. The concrete subclasses only provide get/set methods named after the fields
 * (e.g. getZLAB, setZLAB):
 * <UL>
 * <LI>setFieldsFromParameters( map ). This is useful for setting the fields delivered through an http request.</LI>
 * <LI>setFieldValue( fieldName, fieldValue ). This is useful when setting specific fields directly.</LI>
 * <LI>And lastly by obtaining the EQField object itself through getField( fieldName ) and then using EQField setValue( value ).</LI>
 * </UL>
 * Once the key input fields have been set it is possible to read the database object. This is done through the prompt method. If
 * false is returned getStatus should be called to determine the reason for failure. Extended error and warning messages can be
 * examined using the getMessages method.
 * <P>
 * See the Prompt example for further usage details.
 * <P>
 * 
 * @see com.misys.equation.ebs.samples.EQPromptSample
 * @author Misys International Banking Systems Ltd.
 */
// Do not extend EQEnquiry unless changes are also made where checks are
// for EQEnquiry instances.
public interface EQPrompt extends EQObject
{
	/*******************************************************************************************************************************
	 * Retrieve - calls Equation to obtain data associated with key.
	 * <P>
	 * The object should have its (key) data set prior to this method being called
	 * <P>
	 * Prompts with list data may take multiple retrievals to get all the list items. The number of list items requested in one
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
	 * Set the decode for the prompt.
	 * <P>
	 * 
	 * @param d
	 *            the decode.
	 */
	public abstract void setDecode(char d);
	/*******************************************************************************************************************************
	 * Get the decode for the prompt.
	 * <P>
	 * 
	 * @return the decode
	 */
	public abstract char getDecode();
}