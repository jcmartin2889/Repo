package com.misys.equation.function.runtime;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FieldData;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.RelatedFields;
import com.misys.equation.function.beans.RepeatingFieldData;
import com.misys.equation.function.useraccess.UserExitMessage;

public class FunctionMsgManager
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionMsgManager.java 13462 2012-06-06 08:19:07Z bterrado $";

	// Messages
	private FunctionMessages functionMessages;

	// List of overridden warning messages
	private FunctionMessages overWarnMessages;

	// List of displayed informational messages
	private final FunctionMessages dispInfoMessages;

	// List of any other adhoc messages
	private final FunctionMessages otherMessages;

	/**
	 * Construct a Function message manager
	 */
	public FunctionMsgManager()
	{
		functionMessages = new FunctionMessages();
		otherMessages = new FunctionMessages();
		dispInfoMessages = new FunctionMessages();
		overWarnMessages = new FunctionMessages();
	}

	/**
	 * Return the list of messages
	 * 
	 * @return the list of messages
	 */
	public FunctionMessages getFunctionMessages()
	{
		return functionMessages;
	}

	/**
	 * Set the list of messages
	 * 
	 * @param functionMessages
	 *            - the list of messages
	 */
	public void setFunctionMessages(FunctionMessages functionMessages)
	{
		this.functionMessages = functionMessages;
	}

	/**
	 * Return the list of overridden warning messages
	 * 
	 * @return the list of overridden warning messages
	 */
	public FunctionMessages getOverWarnMessages()
	{
		return overWarnMessages;
	}

	/**
	 * Set the list of overridden warning messages
	 * 
	 * @param overWarnMessages
	 *            - the list of overridden warning messages
	 */
	public void setOverWarnMessages(FunctionMessages overWarnMessages)
	{
		this.overWarnMessages = overWarnMessages;
	}

	/**
	 * Return the list of displayed informational messages
	 * 
	 * @return the list of displayed informational messages
	 */
	public FunctionMessages getDispInfoMessages()
	{
		return dispInfoMessages;
	}

	/**
	 * Return the other messages
	 * 
	 * @return the other messages
	 */
	public FunctionMessages getOtherMessages()
	{
		return otherMessages;
	}

	/**
	 * Clear all messages including overridden messages and displayed messages
	 */
	public void clearAllMessages()
	{
		functionMessages.clearMessages();
		otherMessages.clearMessages();
		dispInfoMessages.clearMessages();
		overWarnMessages.clearMessages();
	}

	/**
	 * Clear the list of other messages
	 */
	public void clearOtherMessages()
	{
		otherMessages.clearMessages();
	}

	/**
	 * Clear the list of messages
	 */
	public void clearMessages()
	{
		otherMessages.clearMessages();
		functionMessages.clearMessages();
	}

	/**
	 * Clear the list of function messages
	 */
	public void clearFunctionMessages()
	{
		functionMessages.clearMessages();
	}

	/**
	 * Mark all informational message as displayed
	 */
	public void markDispInfoMsg()
	{
		dispInfoMessages.insertMessages(functionMessages);
	}

	/**
	 * Override the first warning
	 * 
	 * @param authorisor
	 *            - the supervisor id who has overriden the warning
	 * 
	 * @return true - if warning has been overridden
	 */
	public boolean overrideFirstWarning(String authorisor)
	{
		// ensure there are message to be overridden
		if (functionMessages.getMessages().size() <= 0 || functionMessages.getMsgSev() != FunctionMessages.MSG_WARN)
		{
			return false;
		}

		FunctionMessage fm = functionMessages.getMessages().get(0);
		FunctionMessage fm2 = overWarnMessages.insertMessage(fm.getScreenSetId(), fm.getScrnNo(), fm.getFieldName(), fm
						.getSequence(), fm.getEqMessage(), fm.getFirstLevelText(), fm.getSecondLevelText());
		fm2.setAuthorisor(authorisor);

		return true;
	}

	/**
	 * Override all the warnings
	 * 
	 * @param authorisor
	 *            - the supervisor id who has overriden the warning
	 * 
	 * @return true - if warning has been overridden
	 */
	public boolean overrideAllWarning(String authorisor)
	{
		// ensure there are message to be overridden
		if (functionMessages.getMessages().size() <= 0 || functionMessages.getMsgSev() != FunctionMessages.MSG_WARN)
		{
			return false;
		}

		// set the supervisor
		for (FunctionMessage fm : functionMessages.getMessages())
		{
			fm.setAuthorisor(authorisor);
		}

		overWarnMessages.insertMessages(functionMessages);
		return true;
	}

	/**
	 * Insert messages to the other messages
	 * 
	 * @param screenSetId
	 *            - screen set id
	 * @param scrnNo
	 *            - screen no
	 * @param ksmId
	 *            - KSM id
	 * @param msgSev
	 *            - message severity
	 * @param msgText
	 *            - message text
	 * @param firstLevelText
	 *            - text to be appended to the start of messageText
	 * @param secondLevelText
	 *            - text to be appended to the end of messageText
	 * @return
	 */
	public int insertOtherMessage(int screenSetId, int scrnNo, String ksmId, String msgSev, String msgText, String firstLevelText,
					String secondLevelText)
	{
		EQMessage eqMessage = new EQMessage(ksmId, msgSev, msgText, "");
		otherMessages.insertMessage(screenSetId, scrnNo, "", 1, eqMessage, firstLevelText, secondLevelText);

		return Toolbox.parseInt(msgSev, FunctionMessages.MSG_ERROR);
	}

	/**
	 * Insert other messages
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param screenSetId
	 *            - screen set Id
	 * @param scrnNo
	 *            - the screen number
	 * @param fieldName
	 *            - the field name
	 * @param sequence
	 *            - sequence
	 * @param fieldData
	 *            - the field data
	 * @param messageText
	 *            - the message text in DSEPMS format
	 * @param firstLevelText
	 *            - text to be appended to the start of messageText
	 * @param secondLevelText
	 *            - text to be appended to the end of messageText
	 * 
	 * @return the message severity
	 * 
	 * @throws EQException
	 */
	public int insertOtherMessage(EquationStandardSession session, int screenSetId, int scrnNo, String fieldName, int sequence,
					FieldData fieldData, String messageText, String firstLevelText, String secondLevelText) throws EQException
	{
		// retrieve the message
		EQMessage eqMessage = session.getMessage(messageText);

		// add the message
		otherMessages.insertMessage(screenSetId, scrnNo, fieldName, sequence, eqMessage, firstLevelText, secondLevelText);

		// add the error message to the field
		if (fieldData != null)
		{
			fieldData.getFunctionMessages().insertMessage(screenSetId, scrnNo, fieldName, sequence, eqMessage, firstLevelText,
							secondLevelText);
		}
		return otherMessages.getMsgSev();
	}

	/**
	 * Insert a list of messages to the list of messages
	 * 
	 * @param screenSetId
	 *            - screen set Id
	 * @param scrnNo
	 *            - the screen number that generated that message
	 * @param fms
	 *            - the list of messages
	 * 
	 * @return the message severity
	 */
	public int insertMessages(int screenSetId, int scrnNo, FunctionMessages fms)
	{
		// add the list of messages to the function handler
		functionMessages.insertMessages(fms);

		// retrieve the message severity
		int msgSev = functionMessages.getMsgSev();

		// error?
		if (msgSev == FunctionMessages.MSG_ERROR)
		{
			// clear the warnings and info for this screen, as it should be regenerated once all the errors have been removed
			overWarnMessages.clearMessages(screenSetId, scrnNo);
			dispInfoMessages.clearMessages(screenSetId, scrnNo);
		}

		// warnings?
		else if (msgSev == FunctionMessages.MSG_WARN)
		{
			// clear the info for this screen, as it should be regenerated once all the warnings have been overridden
			dispInfoMessages.clearMessages(screenSetId, scrnNo);
		}

		// return the message severity
		return msgSev;
	}

	/**
	 * Generate a list of messages only if the message has not been overridden and has the same severity or worse than the existing
	 * messages
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * @param screenSetId
	 *            - screen set Id
	 * @param scrnNo
	 *            - the screen where the message occurred
	 * @param fieldName
	 *            - the field that generated the message
	 * @param sequence
	 *            - sequence number
	 * @param fieldData
	 *            - the field data
	 * @param messageTexts
	 *            - the list of messages
	 * @param firstLevelText
	 *            - text to be appended to the start of messageText
	 * @param secondLevelText
	 *            - text to be appended to the end of messageText
	 * @param ignoreMessages
	 *            - the message severity to ignore. All equal/lower message severity will be ignored
	 * 
	 * @return the message severity of generated
	 * 
	 * @throws EQException
	 */
	public int generateMessages(EquationStandardSession session, int screenSetId, int scrnNo, String fieldName, int sequence,
					FieldData fieldData, ArrayList<String> messageTexts, String firstLevelText, String secondLevelText,
					int ignoreMessages) throws EQException
	{
		int msgSev = generateMessages(session, functionMessages, screenSetId, scrnNo, fieldName, sequence, fieldData, messageTexts,
						firstLevelText, secondLevelText, ignoreMessages);

		// message severity
		return msgSev;
	}

	/**
	 * Generate a list of messages only if the message has not been overridden and has the same severity or worse than the existing
	 * messages
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * @param fms
	 *            - function messages to add the the new message
	 * @param screenSetId
	 *            - screen set Id
	 * @param scrnNo
	 *            - the screen where the message occurred
	 * @param fieldName
	 *            - the field that generated the message
	 * @param sequence
	 *            - sequence number
	 * @param fieldData
	 *            - the field data
	 * @param messageTexts
	 *            - the list of messages
	 * @param firstLevelText
	 *            - text to be appended to the start of messageText
	 * @param secondLevelText
	 *            - text to be appended to the end of messageText
	 * @param ignoreMessages
	 *            - the message severity to ignore. All equal/lower message severity will be ignored
	 * 
	 * @return the message severity of generated
	 * 
	 * @throws EQException
	 */
	public int generateMessages(EquationStandardSession session, FunctionMessages fms, int screenSetId, int scrnNo,
					String fieldName, int sequence, FieldData fieldData, List<String> messageTexts, String firstLevelText,
					String secondLevelText, int ignoreMessages) throws EQException
	{
		for (int i = 0; i < messageTexts.size(); i++)
		{
			generateMessage(session, fms, screenSetId, scrnNo, fieldName, sequence, fieldData, messageTexts.get(i), firstLevelText,
							secondLevelText, ignoreMessages);
		}

		// message severity
		return fms.getMsgSev();
	}

	/**
	 * Generate a list of messages only if the message has not been overridden and has the same severity or worse than the existing
	 * messages
	 * 
	 * @param fms
	 *            - function messages to add the the new message
	 * @param screenSetId
	 *            - screen set Id
	 * @param scrnNo
	 *            - the screen where the message occurred
	 * @param fieldName
	 *            - the field that generated the message
	 * @param sequence
	 *            - sequence number
	 * @param fieldData
	 *            - the field data
	 * @param eqMessages
	 *            - the list of messages
	 * @param firstLevelText
	 *            - text to be appended to the start of messageText
	 * @param secondLevelText
	 *            - text to be appended to the end of messageText
	 * @param ignoreMessages
	 *            - the message severity to ignore. All equal/lower message severity will be ignored
	 * 
	 * @return the message severity of generated
	 * 
	 * @throws EQException
	 */
	public int generateEQMessages(FunctionMessages fms, int screenSetId, int scrnNo, String fieldName, int sequence,
					FieldData fieldData, List<EQMessage> eqMessages, String firstLevelText, String secondLevelText,
					int ignoreMessages) throws EQException
	{
		for (EQMessage eqMessage : eqMessages)
		{
			generateMessage(fms, screenSetId, scrnNo, fieldName, sequence, fieldData, eqMessage, firstLevelText, secondLevelText,
							ignoreMessages);
		}

		// message severity
		return fms.getMsgSev();
	}

	/**
	 * Generate a list of messages only if the message has not been overridden and has the same severity or worse than the existing
	 * messages
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * @param fms
	 *            - function messages to add the the new message
	 * @param screenSetId
	 *            - screen set Id
	 * @param scrnNo
	 *            - the screen where the message occurred
	 * @param fieldName
	 *            - the field that generated the message
	 * @param sequence
	 *            - sequence number
	 * @param fieldData
	 *            - the field data
	 * @param messageTexts
	 *            - the list of messages
	 * @param firstLevelText
	 *            - text to be appended to the start of messageText
	 * @param secondLevelText
	 *            - text to be appended to the end of messageText
	 * @param functionData
	 *            - the function data
	 * @param ignoreMessages
	 *            - the message severity to ignore. All equal/lower message severity will be ignored
	 * 
	 * @return the message severity of generated
	 * 
	 * @throws EQException
	 */
	public int generateMessages(EquationStandardSession session, FunctionMessages fms, int screenSetId, int scrnNo,
					String fieldName, int sequence, FieldData fieldData, List<UserExitMessage> messageTexts, String firstLevelText,
					String secondLevelText, FunctionData functionData, int ignoreMessages) throws EQException
	{
		for (UserExitMessage rm : messageTexts)
		{
			// default to the field data in the parameter
			FieldData fd = fieldData;
			String fieldname = fieldName;

			// ignore message if text or field name is blank or null string
			if (rm.getMsgText().trim().length() <= 0)
			{
				continue;
			}

			// is field name specified in the list
			if (rm.getFieldName().trim().length() > 0)
			{
				RelatedFields relatedFields = new RelatedFields(rm.getFieldName());
				fd = functionData.rtvFieldData(relatedFields.getFirstField());

				// update message field name with those that are only valid
				rm.setFieldName(relatedFields.getValidRelatedFieldNames(functionData));
				if (fd == null)
				{
					fd = fieldData;
				}
				else
				{
					fieldname = rm.getFieldName();
				}
			}

			if (rm.getSequence() > 0 && fd instanceof RepeatingFieldData)
			{
				// set row on this repeating data manager
				String list = ((RepeatingFieldData) fd).rtvRepeatingDataManager().getId();
				functionData.getRepeatingDataManager(list).setRow(rm.getSequence() - 1);
			}

			if (rm.getSequence() == 0 && fd instanceof RepeatingFieldData && fieldData != null)
			{
				// the system does not know which row to highlight
				fd = fieldData;
			}

			String secondLevelTextNew = (rm.getSecondLevelText() == null ? "" : rm.getSecondLevelText())
							.concat(secondLevelText == null ? "" : secondLevelText);
			generateMessage(session, fms, screenSetId, scrnNo, fieldname, rm.getSequence(), fd, rm.getMsgText(), firstLevelText,
							secondLevelTextNew, ignoreMessages);
		}

		// message severity
		return fms.getMsgSev();
	}

	/**
	 * Generate a message only if the message has not been overridden and has the same severity or worse than the existing messages
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * @param screenSetId
	 *            - screen set Id
	 * @param scrnNo
	 *            - the screen where the message occurred
	 * @param fieldName
	 *            - the field that generated the message
	 * @param sequence
	 *            - sequence number
	 * @param fieldData
	 *            - the field data
	 * @param messageText
	 *            - the message
	 * @param firstLevelText
	 *            - text to be appended to the start of messageText
	 * @param secondLevelText
	 *            - text to be appended to the end of messageText
	 * @param ignoreMessages
	 *            - the message severity to ignore. All equal/lower message severity will be ignored
	 * 
	 * @return the function message added
	 * 
	 * @throws EQException
	 */
	public FunctionMessage generateMessage(EquationStandardSession session, int screenSetId, int scrnNo, String fieldName,
					int sequence, FieldData fieldData, String messageText, String firstLevelText, String secondLevelText,
					int ignoreMessages) throws EQException
	{
		FunctionMessage fm = generateMessage(session, functionMessages, screenSetId, scrnNo, fieldName, sequence, fieldData,
						messageText, firstLevelText, secondLevelText, ignoreMessages);

		// message severity
		return fm;
	}

	/**
	 * Generate a message only if the message has not been overridden and has the same severity or worse than the existing messages
	 * 
	 * @param session
	 *            - the Equation Standard session
	 * @param fms
	 *            - function messages to add the the new message
	 * @param screenSetId
	 *            - screen set Id
	 * @param scrnNo
	 *            - the screen where the message occurred
	 * @param fieldName
	 *            - the field that generated the message
	 * @param sequence
	 *            - sequence number
	 * @param fieldData
	 *            - the field data
	 * @param messageText
	 *            - the message
	 * @param firstLevelText
	 *            - text to be appended to the start of messageText
	 * @param secondLevelText
	 *            - text to be appended to the end of messageText
	 * @param ignoreMessages
	 *            - the message severity to ignore. All equal/lower message severity will be ignored
	 * 
	 * @return the function message added
	 * 
	 * @throws EQException
	 */
	public FunctionMessage generateMessage(EquationStandardSession session, FunctionMessages fms, int screenSetId, int scrnNo,
					String fieldName, int sequence, FieldData fieldData, String messageText, String firstLevelText,
					String secondLevelText, int ignoreMessages) throws EQException
	{
		// get the message
		EQMessage eqMessage;
		try
		{
			eqMessage = session.getMessage(messageText);
		}
		catch (Exception e)
		{
			eqMessage = session.getMessage("KSM7340" + Toolbox.getExceptionMessage(e));
		}

		// generate
		return generateMessage(fms, screenSetId, scrnNo, fieldName, sequence, fieldData, eqMessage, firstLevelText,
						secondLevelText, ignoreMessages);
	}

	/**
	 * Generate a message only if the message has not been overridden and has the same severity or worse than the existing messages
	 * 
	 * @param fms
	 *            - function messages to add the the new message
	 * @param screenSetId
	 *            - screen set Id
	 * @param scrnNo
	 *            - the screen where the message occurred
	 * @param fieldName
	 *            - the field that generated the message
	 * @param sequence
	 *            - sequence number
	 * @param fieldData
	 *            - the field data
	 * @param eqMessage
	 *            - the Equation message
	 * @param firstLevelText
	 *            - text to be appended to the start of messageText
	 * @param secondLevelText
	 *            - text to be appended to the end of messageText
	 * @param ignoreMessages
	 *            - the message severity to ignore. All equal/lower message severity will be ignored
	 * 
	 * @return the function message added
	 * 
	 */
	public FunctionMessage generateMessage(FunctionMessages fms, int screenSetId, int scrnNo, String fieldName, int sequence,
					FieldData fieldData, EQMessage eqMessage, String firstLevelText, String secondLevelText, int ignoreMessages)
	{
		// message to be ignored?
		if (Toolbox.parseInt(eqMessage.getSeverity(), FunctionMessages.MSG_ERROR) <= ignoreMessages)
		{
			return null;
		}

		// is the message already existing?
		eqMessage = chkMessage(screenSetId, scrnNo, fieldName, sequence, fieldData, eqMessage);

		if (eqMessage == null)
		{
			return null;
		}

		// is the message already exists in the list?
		if (fms.chkMessageExists(screenSetId, scrnNo, fieldName, sequence, eqMessage.getDsepms()))
		{
			return null;
		}

		// add the message
		FunctionMessage fm = fms
						.insertMessage(screenSetId, scrnNo, fieldName, sequence, eqMessage, firstLevelText, secondLevelText);
		if (fm == null)
		{
			return null;
		}

		// add the error message to the field
		if (fieldData != null)
		{
			fieldData.getFunctionMessages().insertMessage(screenSetId, scrnNo, fieldName, sequence, eqMessage, firstLevelText,
							secondLevelText);
		}

		// message severity
		return fm;
	}

	/**
	 * Check if a message already exists in the list of overridden or displayed messages
	 * 
	 * @param screenSetId
	 *            - screen set Id
	 * @param scrnNo
	 *            - the screen where the message occurred
	 * @param fieldName
	 *            - the field that generated the message
	 * @param sequence
	 *            - sequence number
	 * @param fieldData
	 *            - the field data
	 * @param eqMessage
	 *            - the Equation message
	 * 
	 * @return the Equation message if it it not existing<br>
	 *         Otherwise, return NULL
	 * 
	 * @throws EQException
	 */
	public EQMessage chkMessage(int screenSetId, int scrnNo, String fieldName, int sequence, FieldData fieldData,
					EQMessage eqMessage)
	{
		// check the message severity
		int msgSev = Toolbox.parseInt(eqMessage.getSeverity(), FunctionMessages.MSG_ERROR);

		// if this is a warning check if it has already been overridden, if is has, then do not issue this anymore
		if (msgSev == FunctionMessages.MSG_WARN)
		{
			if (overWarnMessages.chkMessageExists(screenSetId, scrnNo, fieldName, sequence, eqMessage.getDsepms()))
			{
				return null;
			}
		}

		// if this is an informational message check if it has already been displayed to the user, if it has, then do not issue
		// this anymore
		else if (msgSev == FunctionMessages.MSG_INFO)
		{
			if (dispInfoMessages.chkMessageExists(screenSetId, scrnNo, fieldName, sequence, eqMessage.getDsepms()))
			{
				return null;
			}
		}

		// not found
		return eqMessage;
	}

}
