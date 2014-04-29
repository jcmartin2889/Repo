package com.misys.equation.function.test.beans;

import junit.framework.TestCase;

import com.misys.equation.function.beans.valid.IValidation;
import com.misys.equation.function.beans.valid.MessageContainer;
import com.misys.equation.problems.Message;

/**
 * Base class for Bean test cases
 * 
 */
public class BeanTestCase extends TestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BeanTestCase.java 13190 2012-04-05 09:42:31Z jonathan.perkins $";
	public Message validateForOneMessage(IValidation element)
	{
		MessageContainer messageContainer = new MessageContainer();
		element.validateBean(messageContainer, null, true);

		return messageContainer.getFirstMessage();

	}

	/**
	 * Attempts to return a Message object with the specified text.
	 * 
	 * This can be used to ensure that the collection of messages includes the specified text.
	 * 
	 * @param element
	 *            Field object to validate
	 * @param text
	 *            Message text to find
	 * 
	 * @return A Message object if found, otherwise null
	 */
	public Message getMessageWithText(IValidation element, String text)
	{
		Message result = null;
		MessageContainer messageContainer = new MessageContainer();
		element.validateBean(messageContainer, null, true);
		for (Message message : messageContainer)
		{
			if (message.getText().equals(text))
			{
				result = message;
			}
		}

		return result;
	}
}
