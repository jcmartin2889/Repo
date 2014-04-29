// ------------------------------------------------------------------------------
// Copyright Misys IBS
//
// Owner: Des Middlemass
//
// Class: EQPromptImpl: Definition of an Equation prompt call
// ------------------------------------------------------------------------------
package com.misys.equation.common.internal.eapi.core;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.misys.equation.common.utilities.EQCommonConstants;
import com.misys.equation.common.utilities.Toolbox;

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
public class EQPromptImpl extends EQObjectImpl implements EQPrompt
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQPromptImpl.java 10812 2011-04-15 20:46:28Z perkinj1 $";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";
	/*******************************************************************************************************************************
	 * Call Mode Constant - The call was to prompt on a field.
	 */
	private static final int MODE_PROMPT = 3;

	/*
	 * Controls
	 */
	private static final int PARAM_PROMPT_MAINCONTROLS = 1;
	private static final int PARAM_PROMPT_INTERNALCONTROLS = 2;
	private static final int PARAM_PROMPT_LIST_CONTROLS = 3;
	private static final int PARAM_PROMPT_FILTER = 4;
	private static final int[] PARAM_PROMPT_LIST = { 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
	private static final int EQPROMPT_MESSAGE_SIZE = 7;
	private static final int PARAM_PROMPT_CALL_STATUS = 15;
	private static final int PARAM_PROMPT_MESSAGE_ID = 16;
	private static final int PARAM_PROMPT_MESSAGE_TEXT = 17;
	private static final int EQPROMPT_MAIN_CONTROLS_SIZE = 20;
	// For JDBC SQL Stored Procedure call
	static private final String storedProcedureCallEQPRMT = "CALL EQPRMTJ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final int EQPROMPT_INTERNAL_CONTROLS_SIZE = 40;
	private static final String EQPROMPT_BLANK_INTERNAL_CONTROLS = EQCommonConstants.initBlankStringBuffer(
					EQPROMPT_INTERNAL_CONTROLS_SIZE).toString();
	private static final String EQPROMPT_BLANK_MAIN_CONTROLS = EQCommonConstants.initBlankStringBuffer(EQPROMPT_MAIN_CONTROLS_SIZE)
					.toString();
	private static final String EQPROMPT_BLANK_MESSAGE = EQCommonConstants.initBlankStringBuffer(EQPROMPT_MESSAGE_SIZE).toString();

	/*
	 * Others
	 */
	private String selectedRow;
	private char decode;
	private char formatOutput;
	private boolean callStatus;
	protected char promptCharacter;
	protected char singlePromptCharacter;
	static private int nPromptDefaultSize;
	static private String strPromptDefaultControls;
	static private String strPromptFirstRetrievalControls;
	static
	{
		try
		{
			EQEnvironment app = EQEnvironment.getAppEnvironment();
			String strSize = app.getProperty("API_DEFAULT_PROMPT_SIZE");
			nPromptDefaultSize = Integer.parseInt(strSize);
			StringBuffer s = new StringBuffer(EQList.EQLIST_BLANK_CONTROLS);
			// first retrieval string has initial character of 'Y' to indicate
			// a retrieval is required
			s.setCharAt(0, 'Y');
			s.replace(1, 6, "00000");
			// overwrite with actual value required
			String strNumReq = Integer.toString(nPromptDefaultSize);
			s.replace(6 - strNumReq.length(), 6, strNumReq);
			s.replace(6, 56, "00000000000000000000000000000000000000000000000000");
			strPromptFirstRetrievalControls = s.toString();
			// default control string has initial character ' ' to indicate
			// no retrieval is required
			s.setCharAt(0, ' ');
			strPromptDefaultControls = s.toString();
		}
		catch (Exception e)
		{
			// set up standard defaults
			nPromptDefaultSize = 16;
			strPromptFirstRetrievalControls = "Y0001600000000000000000000000000000000000000000000000000";
			strPromptDefaultControls = " 0001600000000000000000000000000000000000000000000000000";
		}
	}
	/*******************************************************************************************************************************
	 * Default Constructor.
	 */
	@Override
	protected void initialise(EQObjectMetaData map)
	{
		// Application specific parameters retrieved from environment
		try
		{
			EQEnvironment env = EQEnvironment.getAppEnvironment();
			formatOutput = (env.getProperty("API_FORMATOUTPUT", "N")).charAt(0);
			promptCharacter = (env.getProperty("PROMPT_CHARACTER", "*")).charAt(0);
			singlePromptCharacter = (env.getProperty("SINGLE_PROMPT_CHARACTER", "?")).charAt(0);
			// promptFieldID = ""; // unknown
			super.initialise(map);
			// override the EQList default page size
			list.setPageSize(nPromptDefaultSize);
		}
		catch (Exception e)
		{
		}
		// no need to worry, env problems will get reported earlier
	}
	/*******************************************************************************************************************************
	 * Resets all of the prompt's data attributes to its uninitialised state.
	 * <P>
	 * Note that this does not reset the prompt's definition or its page size. Only the data content of the underlying list and
	 * filter.
	 */
	@Override
	public void reset()
	{
		super.reset();
		resetFilter();
		selectedRow = "";
		// promptFieldID = "";
		callStatus = true;
	}
	/*******************************************************************************************************************************
	 * Resets the prompt's filter field values.
	 * <P>
	 * All filter fields are set to the single prompt character.
	 */
	protected void resetFilter()
	{
		String strPromptChar = String.valueOf(promptCharacter);
		// Reset Input/Output Fields
		int nCount = 0;
		EQFieldDefinition eqFieldDefinition = null;
		EQField eqField = null;
		for (nCount = metaData.nFixedInputFields; --nCount >= 0;)
		{
			eqFieldDefinition = metaData.arrFixedInputFieldDefinitions.get(nCount);
			eqField = hmFields.get(eqFieldDefinition.fieldName);
			if (!eqField.isProtected() && eqField.getDefinition().isInputCapable())
			{
				eqField.internal_SetValue(strPromptChar);
			}
		}
	}
	/*******************************************************************************************************************************
	 * Set the decode for the prompt.
	 */
	public void setDecode(char d)
	{
		decode = d;
	}
	/*******************************************************************************************************************************
	 * Get the decode for the prompt.
	 */
	public char getDecode()
	{
		return decode;
	}
	/*******************************************************************************************************************************
	 * Get the EQPromptImpl's internal controls, i.e control parameters for a call. These controls are not available through other
	 * interfaces.
	 */
	private String getInternalControls()
	{
		char INTERNALCONTROL[] = new char[EQPROMPT_INTERNAL_CONTROLS_SIZE];
		// initialse with blanks
		EQPROMPT_BLANK_INTERNAL_CONTROLS.getChars(0, EQPROMPT_INTERNAL_CONTROLS_SIZE, INTERNALCONTROL, 0);
		// copy over any INTERNALCONTROL information
		metaData.getSourceApplication().getChars(0, metaData.getSourceApplication().length(), INTERNALCONTROL, 0);
		metaData.getSourceVersion().getChars(0, metaData.getSourceVersion().length(), INTERNALCONTROL, 6);
		metaData.getSourceRelease().getChars(0, metaData.getSourceRelease().length(), INTERNALCONTROL, 14);
		metaData.getSourceTask().getChars(0, metaData.getSourceTask().length(), INTERNALCONTROL, 21);
		return EQCommonConstants.rightTrimmedString(INTERNALCONTROL, 0, EQPROMPT_INTERNAL_CONTROLS_SIZE);
	}
	/*******************************************************************************************************************************
	 * Get the EQPromptImpl's main controls, i.e control parameters for a call.
	 */
	private String getControls()
	{
		char MAINCONTROL[] = new char[EQPROMPT_MAIN_CONTROLS_SIZE];
		// initialse with blanks
		EQPROMPT_BLANK_MAIN_CONTROLS.getChars(0, EQPROMPT_MAIN_CONTROLS_SIZE, MAINCONTROL, 0);
		// copy over any MAINCONTROL information
		metaData.getEntryProgram().getChars(0, metaData.getEntryProgram().length(), MAINCONTROL, 0);
		MAINCONTROL[10] = getDecode();
		MAINCONTROL[11] = formatOutput;
		// Reset of job is not to be done at a prompt level. See EQSession.
		MAINCONTROL[12] = 'N';
		return EQCommonConstants.rightTrimmedString(MAINCONTROL, 0, EQPROMPT_MAIN_CONTROLS_SIZE);
	}
	/*******************************************************************************************************************************
	 * Set the input parameters in the CallableStatement.
	 */
	private void populateCallableStatement(CallableStatement cs) throws SQLException
	{
		cs.setString(PARAM_PROMPT_MAINCONTROLS, getControls());
		cs.setString(PARAM_PROMPT_INTERNALCONTROLS, getInternalControls());
		cs.setString(PARAM_PROMPT_LIST_CONTROLS, getListControls());
		cs.setString(PARAM_PROMPT_FILTER, getFieldImage());
	}
	/*******************************************************************************************************************************
	 * Register the output fields in the callable statement.
	 */
	private void registerOutParameters(CallableStatement cs) throws SQLException
	{
		// EQPromptImpl Main Control (this is really an input only parameter but the SQL does not work otherwise)
		cs.registerOutParameter(PARAM_PROMPT_MAINCONTROLS, EQCommonConstants.VARCHAR);
		// EQPromptImpl Internal Control (this is really an input only parameter but the SQL does not work otherwise)
		cs.registerOutParameter(PARAM_PROMPT_INTERNALCONTROLS, EQCommonConstants.VARCHAR);
		// EQPromptImpl Control
		cs.registerOutParameter(PARAM_PROMPT_LIST_CONTROLS, EQCommonConstants.VARCHAR);
		// EQPromptImpl Filter
		cs.registerOutParameter(PARAM_PROMPT_FILTER, EQCommonConstants.VARCHAR);
		// EQPromptImpl List
		for (int i = 0; i < EQList.nListParameters; i++)
		{
			cs.registerOutParameter(PARAM_PROMPT_LIST[i], EQCommonConstants.VARCHAR);
		}
		// EQPromptImpl Call Status
		cs.registerOutParameter(PARAM_PROMPT_CALL_STATUS, EQCommonConstants.CHAR);
		// EQPromptImpl Message ID
		cs.registerOutParameter(PARAM_PROMPT_MESSAGE_ID, EQCommonConstants.CHAR);
		// EQPromptImpl Message Text
		cs.registerOutParameter(PARAM_PROMPT_MESSAGE_TEXT, EQCommonConstants.VARCHAR);
	}
	/*******************************************************************************************************************************
	 * Set the EQPromptImpl's attributes from the results returned from the call.
	 */
	private void setFieldsFromCallableStatement(CallableStatement cs) throws EQException
	{
		try
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("####### Begin Results from Prompt API Call");
			}
			MSGS = new ArrayList<EQMessage>(0);
			// Extract individual field values
			String callStatusStr = cs.getString(PARAM_PROMPT_CALL_STATUS);
			// Extract individual field values
			String msgId = cs.getString(PARAM_PROMPT_MESSAGE_ID);
			// Extract individual field values
			String msgText = cs.getString(PARAM_PROMPT_MESSAGE_TEXT);
			if (!msgId.equals(EQPROMPT_BLANK_MESSAGE))
			{
				setMessage(new EQMessage(msgId, "00", msgText));
			}
			if (callStatusStr.equals("1"))
			{
				callStatus = false;
				if (msgId.equals(EQPROMPT_BLANK_MESSAGE))
				{
					throw new EQException("STATUS_FAILED(1)");
				}
				else
				{
					throw new EQException(msgText);
				}
			}
			else
			{
				callStatus = true;
			}
			// Extract individual field values
			list.setControls(cs.getString(PARAM_PROMPT_LIST_CONTROLS));
			// if (getReturnCode() == EQPromptImpl.EQ_RETURN_STATUS_DATA_RETRIEVED) {
			// complete = true;
			modified = false;
			list.modified = false;

			setFieldsFromImage(cs.getString(PARAM_PROMPT_FILTER));
			// Extract individual field values
			String imageArray[] = new String[EQList.nListParameters];
			for (int i = 0; i < EQList.nListParameters; i++)
			{
				if (list.NUMRET[i] > 0)
				{
					imageArray[i] = cs.getString(PARAM_PROMPT_LIST[i]);
				}
			}
			list.setList(imageArray);
			if (LOG.isDebugEnabled())
			{
				LOG.debug("####### End Results from Prompt API Call");
			}
		}
		catch (SQLException e)
		{
			LOG.error("EQPromptImpl::setFieldsFromCallableStatement: SQL error:" + Toolbox.getExceptionMessage(e) + e.getSQLState()
							+ e.getErrorCode());
			throw new EQException(e);
		}
	}
	/*******************************************************************************************************************************
	 * Add a message to the prompt.
	 * <P>
	 * Provides the ability to associate a message with the prompt.
	 * <P>
	 * 
	 * @param msg
	 *            the message, may be information, warning or error.
	 */
	private void setMessage(EQMessage msg)
	{
		if (msg != null)
		{
			MSGS.add(msg);
		}
	}
	/*******************************************************************************************************************************
	 * Set the user's prompt selection.
	 * <P>
	 * Specify which item in the prompt's list the user has selected. This is only relevant in the context of an EQFunction. On the
	 * next call to Equation Equation will move the selected data into the field being prompted (as specified by promptFieldID.
	 * <P>
	 * 
	 * @param rowNum
	 *            the number of the row in the prompt's list which has been selcted.
	 */
	protected void setSelection(int rowNum)
	{
		selectedRow = list.getRowImage(rowNum);
	}
	/*******************************************************************************************************************************
	 * Return the user's prompt selection.
	 */
	protected String getSelection()
	{
		return selectedRow;
	}
	/*******************************************************************************************************************************
	 * Get the default controls for a call.
	 */
	protected static String getDefaultControls()
	{
		return strPromptDefaultControls;
	}
	/*******************************************************************************************************************************
	 * Get the default EQList controls for a call.
	 */
	protected static String getControlsForFirstRetrieval()
	{
		return strPromptFirstRetrievalControls;
	}
	/*******************************************************************************************************************************
	 * Get the EQPromptImpl's controls, i.e control parameters for a call.
	 */
	protected String getListControls()
	{
		if (modified)
		{
			list.BEGIN = 'Y';
			list.rowsReset();
		}
		return list.getControls();
	}
	/*******************************************************************************************************************************
	 * Get the current call mode for the object, one of the MODE constants.
	 * <P>
	 * 
	 * @return the current call mode.
	 */
	protected int getCallMode()
	{
		return MODE_PROMPT;
	}
	/*******************************************************************************************************************************
	 * Make the call to the Equation SQL Stored Procedure. For list, loop as many times as needed to get the number of rows
	 * requested.
	 */
	private final int callAPILoop(EQSession session) throws EQException
	{
		status = EQObject.STATUS_FAILED;
		callAPI(session);
		if (list != null)
		{
			while (list.BEGIN == 'M' && status == EQObject.STATUS_INCOMPLETE && !list.isPageComplete() && !list.isComplete()
							&& getMessages().isEmpty())
			{
				callAPI(session);
			}
		}
		return status;
	}
	/*******************************************************************************************************************************
	 * Make the call to the Equation SQL Stored Procedure
	 */
	private final int callAPI(EQSession session) throws EQException
	{
		if (metaData.getEntryProgram().length() == 0)
		{
			throw new EQException("Prompt has no entryProgram value");
		}
		CallableStatement cs = null;
		try
		{
			cs = session.getConnection().prepareCall(EQPromptImpl.storedProcedureCallEQPRMT);
			// Set up the callable statement from the Function
			try
			{
				populateCallableStatement(cs);
			}
			catch (SQLException e)
			{
				LOG.error("EQPromptImpl::CallAPI:populateCallableStatement: SQL error:" + Toolbox.getExceptionMessage(e)
								+ e.getSQLState() + e.getErrorCode());
				status = STATUS_SQL_ERROR;
				return status;
			}
			// Register output parameters
			try
			{
				registerOutParameters(cs);
			}
			catch (SQLException e)
			{
				LOG.error("EQPromptImpl::CallAPI:registerOutParameters: SQL error:" + Toolbox.getExceptionMessage(e)
								+ e.getSQLState() + e.getErrorCode());
				status = STATUS_SQL_ERROR;
				return status;
			}
			// Execute the call statement
			try
			{
				cs.setQueryTimeout(session.getProperties().getTimeOut());
				if (LOG.isDebugEnabled())
				{
					LOG.debug("EQPromptImpl,------> before call: " + getListControls());
				}
				cs.execute();
				if (LOG.isDebugEnabled())
				{
					LOG.debug("EQPromptImpl,<------ after  call: " + getListControls());
				}
			}
			catch (SQLException e)
			{
				LOG.error("EQPromptImpl:CallAPI:cs.execute: SQL error:" + Toolbox.getExceptionMessage(e) + e.getSQLState()
								+ e.getErrorCode());
				status = EQObject.STATUS_SQL_ERROR;
				return status;
			}
			setFieldsFromCallableStatement(cs);
			cs.close();
		}
		catch (Exception e)
		{
			LOG.error("EQPromptImpl::CallAPI failed: " + e);
			status = EQObject.STATUS_FAILED;
			return status;
		}
		finally
		{
			try
			{
				if (cs != null)
				{
					cs.close();
				}
			}
			catch (Exception e)
			{
			}
		}
		try
		{
			if (callStatus == true)
			{
				if (list.isComplete())
				{
					status = EQObject.STATUS_DATA_RETRIEVED;
				}
				else
				{
					status = EQObject.STATUS_INCOMPLETE;
				}
			}
			else
			{
				status = EQObject.STATUS_FAILED;
			}
		}
		catch (Exception e)
		{
			status = EQObject.STATUS_FAILED;
		}
		return status;
	}
	/*******************************************************************************************************************************
	 * Call the retrieve method in an enquiry.
	 * <P>
	 * 
	 * @param session
	 *            the session connected to the server.
	 * @return whether the retrieve was successful.
	 * @throws EQException
	 *             if an error occurred during proccessing.
	 */
	public boolean performAction(EQSession session) throws EQException
	{
		return retrieve(session);
	}
	/*******************************************************************************************************************************
	 * Retrieve - calls Equation to obtain data associated with key.
	 * <P>
	 * The object should have its (key) data set prior to this method being called
	 * <P>
	 * Prompts with list data may take multiple retrievals to get all the list items. The number of list items requested in one
	 * retrieval is configurable. This method's return status will be false and the detailed status will be INCOMPLETE if all list
	 * items have not be returned. The detailed status will be DATA_RETRIEVED if all list items are successfully returned.
	 * <P>
	 * 
	 * @param session
	 *            the session connected to the server.
	 * @return whether the call succeeded. Use getStatus for detailed status information.
	 * @throws EQException
	 *             if an error occurs during processing
	 * 
	 */
	public boolean retrieve(EQSession session) throws EQException
	{
		if (modified)
		{
			list.reset();
		}
		// Should this be an error? There are no more list items to get and the key has not changed
		// User interface needs to handle this if not handled here
		if (list.BEGIN != 'L')
		{
			callAPILoop(session);
		}
		return status >= STATUS_SUCCESS;
	}
	/*******************************************************************************************************************************
	 * Calls the retrieve method for a prompt.
	 * <P>
	 * 
	 * @param session
	 *            the session connected to the server.
	 * @return whether the retrieve was successful.
	 * @throws EQException
	 *             if an error occurred during proccessing.
	 */
	public boolean defaultAction(EQSession session) throws EQException
	{
		return retrieve(session);
	}
	/*******************************************************************************************************************************
	 * Not implemented.
	 */
	public String getAuditUserID()
	{
		return null;
	}
	/*******************************************************************************************************************************
	 * Not implemented.
	 */
	public void setAuditUserID(String auditUserID) throws EQException
	{
		throw new EQException("Audit User ID cannot be set on a prompt");
	}
}
