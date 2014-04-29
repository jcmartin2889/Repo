package com.misys.equation.function.adaptor;

import com.misys.equation.function.useraccess.UserExitMessages;

public abstract class AbstractAdaptor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractAdaptor.java 14796 2012-11-05 11:52:03Z williae1 $";

	// list of messages
	private UserExitMessages returnMessages = new UserExitMessages();

	/**
	 * Return the messages
	 * 
	 * @return the list of messages
	 * @equation.external
	 */
	public UserExitMessages getReturnMessages()
	{
		return returnMessages;
	}

	/**
	 * Clear the messages
	 * 
	 * @equation.external
	 */
	public void clearMessages()
	{
		returnMessages.getReturnMessages().clear();
	}
}
