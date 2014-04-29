package com.misys.equation.function.linkage;

import com.misys.equation.function.beans.valid.MessageContainer;

public abstract class AbstractServiceLinkage
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractServiceLinkage.java 14806 2012-11-05 11:58:11Z williae1 $";
	// list of messages
	protected MessageContainer messageContainer;

	/**
	 * Constructor
	 */
	public AbstractServiceLinkage()
	{
		messageContainer = new MessageContainer();
	}

	/**
	 * Return list of messages
	 * 
	 * @return list of messages
	 */
	public MessageContainer getMessageContainer()
	{
		return messageContainer;
	}

	/**
	 * Generate a message
	 * 
	 * @param messages
	 *            - the list of messages to add the new message
	 * @param code
	 *            - the code as defined in the equationFunctionMessage.property
	 * @param parameter1
	 *            - parameter 1
	 * @param parameter2
	 *            - parameter 2
	 * @param parameter3
	 *            - parameter 3
	 * @param parameter4
	 *            - parameter 4
	 * @param parameter5
	 *            - parameter 5
	 * @return
	 */
	public void addMessage(String code, String parameter1, String parameter2, String parameter3, String parameter4,
					String parameter5)
	{
		String[] params = new String[] { parameter1, parameter2, parameter3, parameter4, parameter5 };

		// Warning or error?
		if (code.endsWith("Warning"))
		{
			messageContainer.addWarningMessageId(code, params, null);
		}
		else
		{
			messageContainer.addErrorMessageId(code, params, null);
		}
	}

}
