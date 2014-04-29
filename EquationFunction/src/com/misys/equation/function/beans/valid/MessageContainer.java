package com.misys.equation.function.beans.valid;

import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.problems.AbsMessageContainer;
import com.misys.equation.problems.Message;
import com.misys.equation.problems.ProblemLocation;

/**
 * Maintains a collection of Message objects during validation.
 * 
 * This class provides utility methods to get messages by ID and as such is dependent upon the <BR>
 * specific LanguageResources implementation, and that classes associated ResourceBundle, per project.
 */
public class MessageContainer extends AbsMessageContainer
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MessageContainer.java 14800 2012-11-05 11:55:45Z williae1 $";

	/**
	 * Retrieves the specified message from the message resource, and adds an error message with the obtained text
	 * 
	 * @param id
	 *            Resource message id
	 * @param problemLocation
	 *            The location of the problem
	 * @return the newly added Message instance
	 */
	@Override
	public Message addErrorMessageId(String id, ProblemLocation problemLocation)
	{
		Message message = new Message(LanguageResources.getString(id), Message.SEVERITY_ERROR, problemLocation);

		// Add to message collection
		messages.add(message);
		return message;
	}

	/**
	 * Retrieves the specified message from the message resources, formatted using the specified parameter, and then adds it as an
	 * error message to the collection of messages
	 * 
	 * @param id
	 *            Resource message id
	 * @param param1
	 *            Single message parameter
	 * @param problemLocation
	 *            The location of the problem
	 * @return the newly added Message instance
	 */
	@Override
	public Message addErrorMessageId(String id, String param1, ProblemLocation problemLocation)
	{
		String text = LanguageResources.getFormattedString(id, param1);
		Message message = new Message(text, Message.SEVERITY_ERROR, problemLocation);
		messages.add(message);
		return message;
	}

	/**
	 * Retrieves the specified message from the message resources, formatted using the specified parameters, and then adds it as an
	 * error message to the collection of messages
	 * 
	 * @param id
	 *            Resource message id
	 * @param params
	 *            Single message parameter
	 * @param problemLocation
	 *            The location of the problem
	 * @return the newly added Message instance
	 */
	@Override
	public Message addErrorMessageId(String id, String[] params, ProblemLocation problemLocation)
	{
		String text = LanguageResources.getFormattedString(id, params);
		Message message = new Message(text, Message.SEVERITY_ERROR, problemLocation);
		messages.add(message);
		return message;
	}

	/**
	 * Retrieves the specified message from the message resources, formatted using the specified parameters, and then adds it as an
	 * warning message to the collection of messages
	 * 
	 * @param id
	 *            Resource message id
	 * @param params
	 *            Single message parameter
	 * @param problemLocation
	 *            The location of the problem
	 * @return the newly added Message instance
	 */
	@Override
	public Message addWarningMessageId(String id, String[] params, ProblemLocation problemLocation)
	{
		String text = LanguageResources.getFormattedString(id, params);
		Message message = new Message(text, Message.SEVERITY_WARNING, problemLocation);
		messages.add(message);
		return message;
	}

}