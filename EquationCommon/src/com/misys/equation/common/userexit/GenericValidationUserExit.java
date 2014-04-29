/**
 * 
 */
package com.misys.equation.common.userexit;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.common.access.EquationScreenDataStructure;

/**
 * This class stores the EquationScreenDataStructure object and a collection of messages. It also provides various helper methods.
 * The author of the actual user class must implement the execute method, and use the helper methods to get/set screen field values
 * and add messages to return to the RPG program
 */
public abstract class GenericValidationUserExit implements UserExit
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GenericValidationUserExit.java 8910 2010-08-26 15:25:20Z MACDONP1 $";

	private EquationScreenDataStructure screenDS;
	private final List<ValidationUserExitMessage> messages = new ArrayList<ValidationUserExitMessage>();

	public void initialize(EquationScreenDataStructure screenDS)
	{
		this.screenDS = screenDS;
	}

	/**
	 * @return List of ValidationUserExitMessages
	 */
	public List<ValidationUserExitMessage> getMessages()
	{
		return messages;
	}

	/** Sets a Screen Field DS field value */
	protected void setFieldValue(String fieldName, String fieldValue)
	{
		screenDS.setField(fieldName, fieldValue);
	}

	/** Gets the value of a screen field from the DS */
	protected String getFieldValue(String fieldName)
	{
		return screenDS.getField(fieldName);
	}

	/**
	 * Adds a validation message to the collection of messages
	 * 
	 * The new validation message object is returned to the caller so that parameter information can be added
	 * 
	 * @param messageId
	 *            - the message Id
	 * @param fieldIndicator
	 *            - the field indicator
	 * 
	 * @return ValidateUserExitMessage The newly created message object
	 */
	public ValidationUserExitMessage addMessage(String messageId, int fieldIndicator)
	{
		ValidationUserExitMessage message = new ValidationUserExitMessage(messageId, fieldIndicator);
		messages.add(message);
		return message;
	}

}
