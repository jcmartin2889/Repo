package com.misys.equation.common.internal.eapi.core;

import java.util.ArrayList;
import java.util.Map;

import com.misys.equation.common.utilities.EquationLogger;

/***********************************************************************************************************************************
 * A helper class for an Equation user interface.
 * <P>
 * This is an internal class for storing information and methods related to user interface processing.
 * <P>
 * This class can contain an object which is an EQEnquiry, an EQTransaction, or an EQPrompt. This class can contain a prompt as
 * well. If both the object and prompt are EQPrompt objects then the prompt is being tested in isolation from an enquiry or
 * transaction.
 * <P>
 * 
 * @author Misys International Banking Systems Ltd.
 */
public class EQUserInterfaceHelper
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQUserInterfaceHelper.java 4741 2009-09-16 16:33:23Z esther.williams $";
	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";
	private static final EquationLogger LOG = new EquationLogger(EQUserInterfaceHelper.class);
	/*******************************************************************************************************************************
	 * Event Constant - requests an exit from the current processing.
	 */
	public static final String EVENT_EXIT = "03";
	/*******************************************************************************************************************************
	 * Event Constant - requests a that the data in the object is verified (validate).
	 */
	public static final String EVENT_VERIFY = "05";
	/*******************************************************************************************************************************
	 * Event Constant - requests a page up in a list object.
	 */
	public static final String EVENT_BACKWARDS = "07";
	/*******************************************************************************************************************************
	 * Event Constant - requests a page down in a list object.
	 */
	public static final String EVENT_FORWARDS = "08";
	/*******************************************************************************************************************************
	 * Event Constant - requests current processing moves backwards to the previous screen.
	 */
	public static final String EVENT_PREVIOUS = "12"; // cancel
	/*******************************************************************************************************************************
	 * Event Constant - requests the object is deleted from the database.
	 */
	public static final String EVENT_DELETE = "22";
	/*******************************************************************************************************************************
	 * Event Constant - requests an override of a warning condition.
	 */
	public static final String EVENT_OVERRIDE = "24";
	/*******************************************************************************************************************************
	 * Event Constant - reserved.
	 */
	public static final String EVENT_SAVE = "25";
	/*******************************************************************************************************************************
	 * Event Constant - reserved.
	 */
	public static final String EVENT_NEW = "26";
	/*******************************************************************************************************************************
	 * Event Constant - requests the object is read from the database.
	 */
	public static final String EVENT_RETRIEVE = "27";
	/*******************************************************************************************************************************
	 * Event Constant - reserved.
	 */
	public static final String EVENT_CLONE = "28";
	/*******************************************************************************************************************************
	 * Event Constant - reserved.
	 */
	public static final String EVENT_DISPLAY = "29";
	// Event Constants
	/*******************************************************************************************************************************
	 * Event Constant - requests a supervisor override for a warning condition.
	 */
	public static final String EVENT_SUPER_OVERRIDE = "30";
	/*******************************************************************************************************************************
	 * Event Constant - signals a prompt has occured.
	 */
	public static final String EVENT_PROMPTED = "31";
	/**
	 * Is the object in this helper class an enquiry?
	 */
	public boolean isEnquiry;
	/**
	 * Is the object in this helper class a transaction?
	 */
	public boolean isTransaction;
	/**
	 * Is the object in this helper class a function?
	 */
	public boolean isFunction;
	/**
	 * Is the object in this helper class a prompt?
	 */
	public boolean isPromptManager;
	private EQPrompt prompt;
	private EQObject object;
	protected String currentDisplayName;
	// Name of associated display (varies at run-time)
	protected String displayName;
	/*******************************************************************************************************************************
	 * Default view name for prompts.
	 */
	public static final String DEFAULT_PROMPT_VIEW = "templates/PromptTemplate";
	/*******************************************************************************************************************************
	 * Default view name for functions.
	 */
	public static final String DEFAULT_FUNCTION_VIEW = "templates/FunctionTemplate";
	/*******************************************************************************************************************************
	 * Default Constuctor.
	 */
	public EQUserInterfaceHelper()
	{
		// setIncrementalMode(false);
		displayName = DEFAULT_FUNCTION_VIEW;
		currentDisplayName = "";
	}
	/*******************************************************************************************************************************
	 * Get the current EQObject.
	 * <P>
	 * 
	 * @return the current EQEnquiry, EQTransaction, or EQPrompt associated with the user interface.
	 */
	public EQObject getObject()
	{
		return object;
	}
	/*******************************************************************************************************************************
	 * Helper method to reset prompting.
	 */
	public void resetPrompt()
	{
		if (object != null && isFunction)
		{
			((EQFunction) object).resetPrompt();
			prompt = null;
		}
	}
	/*******************************************************************************************************************************
	 * Get the field ID being prompted.
	 * <P>
	 * 
	 * @return the prompt field id.
	 */
	public String getPromptFieldID()
	{
		if (isFunction)
		{
			return ((EQFunction) object).promptFieldID;
		}
		else
		{
			return null;
		}
	}
	/*******************************************************************************************************************************
	 * Set the field ID being prompted.
	 * <P>
	 * Specify the fieldID for which this prompt is being invoked. This is only relevant when being invoked in the context of an
	 * EQFunction. This does not need to be specified when used with the prompts.
	 * <P>
	 * Note this is a fieldID of a field in the function, not a field on the prompt itself.
	 * <P>
	 * 
	 * @param fieldID
	 *            the field being prompted.
	 */
	void setPromptFieldID(String fieldID)
	{
		((EQFunction) object).promptFieldID = fieldID;
	}
	/*******************************************************************************************************************************
	 * Helper method to setup a prompt call for a field within a function.
	 */
	protected void setPrompt(String theField, boolean setEntryEvent)
	{
		if (isIncrementalMode() && setEntryEvent)
		{
			((EQFunction) object).ENTRYEVENT = "04"; // emulate an F4 key event
		}
		// PVROOT = "";
		((EQFunction) object).promptFieldID = theField;
	}
	/*******************************************************************************************************************************
	 * Helper method to cause a selected prompt item to set a field in the EQFunction.
	 * <P>
	 * 
	 * @param nRowNumber
	 *            the number of the row in the prompt's list which has been selcted.
	 */
	public void setPromptSelection(int nRowNumber)
	{
		if (currentDisplayName.trim().length() != 0)
		{
			displayName = currentDisplayName;
		}
		else
		{
			displayName = DEFAULT_FUNCTION_VIEW;
		}
		// selection when testing prompts does nothing
		if (!isPromptManager)
		{
			((EQFunction) object).PVROOT = "";
			((EQFunction) object).promptSelected = "Y";
			((EQPromptImpl) prompt).setSelection(nRowNumber);
		}
	}
	/*******************************************************************************************************************************
	 * Set the Prompts's properties from a map.
	 * <P>
	 * The properties supported are decode, pvRequiredRows.
	 * <P>
	 * 
	 * @param parameters
	 *            the map of property/value pairs. The map must have a key of String, and the values must be String[].
	 * @return true if prompt controls were changed.
	 */
	public boolean setPromptControlFromParameters(Map<String, String[]> parameters)
	{
		// The request contains the fields required to create a prompt
		String[] values;
		boolean promptChanged = false;
		char decode = ' ';
		int nRequiredRows = 16;
		// now look for any other values to override defaults
		values = parameters.get("decode");
		if (values != null && values[0].length() > 0)
		{
			decode = values[0].charAt(0);
			if (decode != ((EQPrompt) object).getDecode())
			{
				promptChanged = true;
			}
		}
		values = parameters.get("pvRequiredRows");
		if (values != null && values[0].length() > 0)
		{
			nRequiredRows = Integer.parseInt(values[0]);
			if (nRequiredRows != ((EQPrompt) object).getList().getPageSize())
			{
				promptChanged = true;
			}
		}
		if (promptChanged)
		{
			try
			{
				((EQPromptImpl) object).reset();
				((EQPrompt) object).setDecode(decode);
				((EQPrompt) object).getList().setPageSize(nRequiredRows);
			}
			catch (EQException e)
			{
			}
		}
		return promptChanged;
	}
	/*******************************************************************************************************************************
	 * Set any of the object's fields from a map.
	 * <P>
	 * The map should contain a set of field name/value pairs. The object will examine the map for fields it supports and set its
	 * internal values for any fields found to the values in the map.
	 * <P>
	 * The map must have a key of String, and the values must be String[]. Typically, this is passed in an HttpRequest.
	 * <P>
	 * 
	 * @param parameters
	 *            the map of field values to be set in the object.
	 * @return true if any fields were found and used.
	 */
	public boolean setFieldsFromParameters(Map<String, String[]> parameters)
	{
		if (isPromptManager)
		{
			return ((EQPrompt) object).setFieldValue(parameters);
		}
		else
		{
			return prompt.setFieldValue(parameters);
		}
	}
	/*******************************************************************************************************************************
	 * Call Equation to get prompt values for a specific field.
	 * <P>
	 * Only available in Incremental mode.
	 * <P>
	 * 
	 * @param session
	 *            the session connected to the server.
	 * @param fieldID
	 *            the required field.
	 * @return whether the call succeeded. Use getStatus for more detailed information throws EQException if errors occur during
	 *         processing
	 */
	public boolean prompt(EQSession session, String fieldID) throws EQException
	{
		if (isFunction)
		{
			// check it we need to remove a previous prompt becuase we are prompting
			// on a new field.
			if (prompt != null && fieldID != null && ((EQFunction) object).promptFieldID != null)
			{
				if (fieldID.compareToIgnoreCase(((EQFunction) object).promptFieldID) != 0)
				{
					prompt = null;
					((EQFunction) object).prompt = null;
				}
			}
			// only set the entry event if we hav a fieldID to prompt on
			boolean setEntryEvent = false;
			if (fieldID != null && fieldID.length() > 0)
			{
				setEntryEvent = true;
			}
			setPrompt(fieldID, setEntryEvent);
			// mode = MODE_PROMPT;
			((EQFunction) object).APIMODE = EQFunction.API_MODE_VERIFY;
			((EQFunction) object).callAPILoop(session);
			return object.getStatus() >= EQObject.STATUS_SUCCESS;
		}
		else
		{
			return ((EQPrompt) object).retrieve(session);
		}
	}
	/*******************************************************************************************************************************
	 * Remove any prompting in this object.
	 */
	public void removePrompt()
	{
		resetPrompt();
	}
	/*******************************************************************************************************************************
	 * Get the current EQPrompt.
	 * <P>
	 * 
	 * @return the current EQPrompt associated with the object. This can be null.
	 */
	public EQPrompt getPrompt()
	{
		if (isPromptManager)
		{
			return (EQPrompt) object;
		}
		else
		{
			return prompt;
		}
	}
	/*******************************************************************************************************************************
	 * Calls the API.
	 * <P>
	 * The object should have its data set prior to this method being called.
	 * <P>
	 * 
	 * @param session
	 *            the session connected to the server.
	 * @param action
	 *            the transaction mode constant.
	 * @return whether the call succeeded. Use getStatus for detailed status information.
	 * @throws EQException
	 *             if an error occurs during processing
	 */
	public boolean defaultAction(EQSession session, int action) throws EQException
	{
		boolean status = false;
		if (isPromptManager || prompt != null)
		{
			status = prompt.retrieve(session);
		}
		else
		{
			if (isTransaction)
			{
				char maintenanceMode = ((EQTransaction) object).getMaintenanceMode();
				if (maintenanceMode == EQTransaction.MAINTENANCE_MODE_ADD)
				{
					status = ((EQTransaction) object).add(session, action);
					if (((EQTransactionImpl) object).prompt != null)
					{
						setPrompt(((EQTransactionImpl) object).prompt);
					}
					else
					{
						setPrompt(null);
					}
				}
				else
				{
					if (maintenanceMode == EQTransaction.MAINTENANCE_MODE_MAINTAIN)
					{
						status = ((EQTransaction) object).maintain(session, action);
						if (((EQTransactionImpl) object).prompt != null)
						{
							setPrompt(((EQTransactionImpl) object).prompt);
						}
						else
						{
							setPrompt(null);
						}
					}
					else
					{
						if (maintenanceMode == EQTransaction.MAINTENANCE_MODE_DELETE)
						{
							status = ((EQTransaction) object).delete(session, action);
							if (((EQTransactionImpl) object).prompt != null)
							{
								setPrompt(((EQTransactionImpl) object).prompt);
							}
							else
							{
								setPrompt(null);
							}
						}
						else
						{
							if (maintenanceMode == EQTransaction.MAINTENANCE_MODE_UNKNOWN
											&& ((EQTransactionImpl) object).isIncrementalMode())
							{
								// only supported in incremental mode
								status = ((EQTransaction) object).performAction(session, action);
								if (((EQTransactionImpl) object).prompt != null)
								{
									setPrompt(((EQTransactionImpl) object).prompt);
								}
								else
								{
									setPrompt(null);
								}
							}
							else
							{
								LOG.error("Maintence Mode has not been set");
								throw new EQException("Maintence Mode has not been set");
							}
						}
					}
				}
			}
			else
			{
				if (isEnquiry)
				{
					status = ((EQEnquiry) object).retrieve(session);
					if (((EQEnquiryImpl) object).prompt != null)
					{
						setPrompt(((EQEnquiryImpl) object).prompt);
					}
					else
					{
						setPrompt(null);
					}
				}
			}
		}
		return status;
	}
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
	public EQField getField(String fieldID)
	{
		if ((prompt != null))
		{
			return prompt.getField(fieldID);
		}
		else
		{
			return object.getField(fieldID);
		}
	}
	/*******************************************************************************************************************************
	 * Get the display file name.
	 * <P>
	 * Only availbile in interacive mode.
	 * <P>
	 * 
	 * @return the name of the display associated with the object.
	 */
	public String getDisplayName()
	{
		if ((prompt != null))
		{
			return "templates/PromptTemplate";
		}
		else
		{
			return displayName;
		}
	}
	/*******************************************************************************************************************************
	 * Get the default view of the object.
	 * <P>
	 * 
	 * @return the name of the default view associated with the object.
	 */
	public String getDefaultView()
	{
		if (isPromptManager || prompt != null)
		{
			return DEFAULT_PROMPT_VIEW;
		}
		else
		{
			return DEFAULT_FUNCTION_VIEW;
		}
	}
	/*******************************************************************************************************************************
	 * Get the resource name of the object.
	 * <P>
	 * 
	 * @return the name of the default view associated with the object.
	 */
	public String getResourceName()
	{
		if (!(prompt != null))
		{
			// we're displaying the function, so the resource is the function's root id
			return getObject().getMetaData().getRootID();
		}
		else
		{
			// we're displaying a prompt, so the resource is the prompt's root id
			return prompt.getMetaData().getRootID();
		}
	}
	/*******************************************************************************************************************************
	 * Get the object status.
	 * <P>
	 * 
	 * @return status of the object.
	 */
	public int getStatus()
	{
		if ((prompt != null))
		{
			return prompt.getStatus();
		}
		else
		{
			return object.getStatus();
		}
	}
	/*******************************************************************************************************************************
	 * Get the current function keys.
	 * <P>
	 * 
	 * @return the function keys for the prompt
	 */
	public ArrayList<String> getFunctionKeys()
	{
		ArrayList<String> arrPromptKeys = new ArrayList<String>();
		arrPromptKeys.add("F3=Common/Exit");
		return arrPromptKeys;
	}
	/*******************************************************************************************************************************
	 * Sets the function to operate in incremental mode.
	 * <P>
	 * In incremental mode Equation processes its business logic screen by screen and returns visibility information for fields and
	 * text on those screens.
	 * <P>
	 * Once switched on it cannot be switched off.
	 * 
	 * @param incrementalMode
	 *            boolean the function is set to incremental mode, or not.
	 */
	public void setIncrementalMode(boolean incrementalMode)
	{
		if (isFunction)
		{
			((EQFunction) object).bIncrementalMode = incrementalMode;
		}
	}
	/*******************************************************************************************************************************
	 * Determine whether the Function is operating in incremental mode.
	 * <P>
	 * In incremental mode Equation processes its business logic screen by screen and returns visibility information for fields and
	 * text on those screens.
	 * <P>
	 * 
	 * @return true if the function makes calls in incremental mode
	 */
	public boolean isIncrementalMode()
	{
		/* Prompt Manager always runs in incremental mode. */
		if ((prompt != null))
		{
			return true;
		}
		else
		{
			return ((EQFunction) object).bIncrementalMode;
		}
	}
	/*******************************************************************************************************************************
	 * Assigns a display name to the object.
	 * <P>
	 * 
	 * @param session
	 *            the session connected to the server.
	 * @param promptIdentifier
	 *            the new display name.
	 */
	public void setFunctionPrompt(EQSession session, String promptIdentifier)
	{
		// Determine display name
		StringBuffer buff = new StringBuffer(20);
		if (prompt != null)
		{
			// display is set for a prompt
			buff.append(DEFAULT_PROMPT_VIEW);
			displayName = buff.toString();
		}
		if (prompt == null)
		{
			setPromptClass(session, promptIdentifier);
		}
	}
	/*******************************************************************************************************************************
	 * Helper method to setup a prompt class.
	 */
	private void setPromptClass(EQSession session, String promptIdentifier)
	{
		// allocate the prompt to process the output
		if (prompt == null)
		{
			try
			{
				// Construct an appropriate prompt
				prompt = (EQPrompt) session.createEQObject(promptIdentifier);
			}
			catch (Exception e)
			{
				LOG.error("EQUserInterfaceHelper:setPromptClass failed: " + e);
			}
		}
	}
	/*******************************************************************************************************************************
	 * Assigns a display name to the object.
	 * <P>
	 * 
	 * @param newDisplayName
	 *            the new display name.
	 */
	public void setDisplayName(String newDisplayName)
	{
		if ((prompt != null))
		{
			displayName = newDisplayName;
		}
		else
		{
			currentDisplayName = newDisplayName;
			displayName = newDisplayName;
		}
	}
	/**
	 * Get the object title.
	 * <P>
	 * 
	 * @return the object title.
	 */
	public String getTitle()
	{
		if ((prompt != null))
		{
			return prompt.getMetaData().getTitle();
		}
		else
		{
			return object.getMetaData().getTitle();
		}
	}
	/**
	 * Get the object identifier.
	 * <P>
	 * 
	 * @return the object identifier.
	 */
	public String getAPIID()
	{
		if ((prompt != null))
		{
			return prompt.getMetaData().getIdentifier();
		}
		else
		{
			return object.getMetaData().getIdentifier();
		}
	}
	/*******************************************************************************************************************************
	 * Call Equation to perform the action associated with an event.
	 * <P>
	 * These are typically user events (i.e. button/function key presses) Equation will perform validatation in addition to
	 * processing the event.
	 * <P>
	 * Only available in Incremental mode
	 * <P>
	 * 
	 * @param session
	 *            the session connected to the server.
	 * @param event
	 *            the event in question.
	 * @return whether the call succeeded. Use getStatus for more detailed information
	 * @throws EQException
	 *             if an error occurs during processing.
	 */
	public boolean processEvent(EQSession session, String event) throws EQException
	{
		if (!isIncrementalMode())
		{
			throw new EQException("can't process events in non-incremenental mode");
		}
		resetPrompt();
		((EQEnquiryImpl) object).action = EQEnquiryImpl.ACTION_EVENT;
		((EQEnquiryImpl) object).APIMODE = EQFunction.API_MODE_VERIFY;
		((EQEnquiryImpl) object).ENTRYEVENT = event;
		((EQFunction) object).callAPILoop(session);
		// For Enquiries only - check last return status to see if we have finished processing
		if (!(object instanceof EQTransaction) && object.getStatus() == EQObject.STATUS_DATA_RETRIEVED)
		{
			object.setReadOnly();
		}
		return object.getStatus() >= EQObject.STATUS_SUCCESS;
	}
	/*******************************************************************************************************************************
	 * Initialize the API.
	 * <P>
	 * Calls Equation to obtain initial defaults/visibility.
	 * <P>
	 * 
	 * @param session
	 *            the session connected to the server.
	 * @return whether the call succeeded. Use getStatus for more detailed information
	 * @throws EQException
	 *             if an error occurs during processing.
	 */
	public boolean initialize(EQSession session) throws EQException
	{
		if (object != null && isFunction)
		{
			resetPrompt();
			((EQEnquiryImpl) object).action = EQEnquiryImpl.ACTION_INIT;
			((EQFunction) object).APIMODE = EQFunction.API_MODE_INIT;
			((EQFunction) object).callAPILoop(session);
			return object.getStatus() >= EQObject.STATUS_SUCCESS;
		}
		else
		{
			return true;
		}
	}
	/*******************************************************************************************************************************
	 * Get the name of the screen currently being processed.
	 * <P>
	 * Only relevant when incremental mode is on.
	 * <P>
	 * This method returns the name of the record format in the display file that Equation is currently processing.
	 * <P>
	 * 
	 * @return the current record format.
	 */
	public String getFormat()
	{
		if (isFunction && ((EQFunction) object).bIncrementalMode)
		{
			return ((EQFunction) object).FORMAT;
		}
		else
		{
			if (prompt != null)
			{
				return prompt.getMetaData().getRootID();
			}
			else
			{
				return object.getMetaData().getRootID();
			}
		}
	}
	/**
	 * Store the object in this helper class.
	 * <P>
	 * 
	 * @param object
	 *            the EQObject to be stored.
	 */
	public void setObject(EQObject object)
	{
		this.object = object;
		isEnquiry = false;
		isTransaction = false;
		isFunction = false;
		isPromptManager = false;
		if (object instanceof EQPrompt)
		{
			isPromptManager = true;
			isFunction = false;
		}
		else
		{
			if (object instanceof EQTransaction)
			{
				isTransaction = true;
				isFunction = true;
			}
			else
			{
				if (object instanceof EQEnquiry)
				{
					isEnquiry = true;
					isFunction = true;
				}
			}
		}
	}
	/**
	 * Store the prompt in this helper class.
	 * <P>
	 * 
	 * @param prompt
	 *            the EQPrompt to be stored.
	 */
	public void setPrompt(EQPrompt prompt)
	{
		this.prompt = prompt;
	}
	/*******************************************************************************************************************************
	 * Determine if a field is both visible and should be displayed as an input field.
	 * <P>
	 * Only relevant when incremental mode is on.
	 * <P>
	 * 
	 * @param fieldID
	 *            the name of the field
	 * @return true if the field is visible and input capable.
	 */
	public boolean isVisibleInputField(String fieldID)
	{
		EQField eqField = (object.getField(fieldID));
		if (eqField != null)
		{
			return eqField.displayAsInputField();
		}
		else
		{
			return false;
		}
	}
	/*******************************************************************************************************************************
	 * Set Commitment Control flags.
	 * <P>
	 * For internal testing use only.
	 * <P>
	 * This method only takes effect when used on an EQSession with Auto Commit turned off.
	 * <P>
	 * Specifies whether the next call that this function makes should start a new commitment control boundary and whether it should
	 * also end it (i.e. commit).
	 * <P>
	 * 
	 * @param startCCBoundary
	 *            whether to create a new transaction boundary on the next call.
	 * @param endCCBoundary
	 *            whether to commit the boundary on the next call.
	 */
	public void setCommitmentControlBoundaries(boolean startCCBoundary, boolean endCCBoundary)
	{
		((EQFunction) object).CCSTARTTRANSACTION = startCCBoundary ? "Y" : "N";
		((EQFunction) object).CCENDTRANSACTION = endCCBoundary ? "Y" : "N";
	}
}
