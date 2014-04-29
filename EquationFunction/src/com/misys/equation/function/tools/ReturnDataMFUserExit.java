package com.misys.equation.function.tools;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.function.useraccess.UserExitMessage;

public class ReturnDataMFUserExit
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ReturnDataMFUserExit.java 14804 2012-11-05 11:57:35Z williae1 $";

	private boolean changed;
	private List<UserExitMessage> messages;

	/**
	 * Constructor
	 */
	public ReturnDataMFUserExit()
	{
		messages = new ArrayList<UserExitMessage>();
	}

	/**
	 * Determine whether data has been changed
	 * 
	 * @return true if data has been changed
	 */
	public boolean isChanged()
	{
		return changed;
	}

	/**
	 * Set whether data has been changed
	 * 
	 * @param changed
	 *            - true if data has been changed
	 */
	public void setChanged(boolean changed)
	{
		this.changed = changed;
	}

	/**
	 * Return the list of messages
	 * 
	 * @return the list of messages
	 */
	public List<UserExitMessage> getMessages()
	{
		return messages;
	}

	/**
	 * Set the list of messages
	 * 
	 * @param messages
	 *            - the list of messages
	 */
	public void setMessages(List<UserExitMessage> messages)
	{
		this.messages = messages;
	}

	/**
	 * Return the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		StringBuilder buffer = new StringBuilder();

		if (messages != null)
		{
			for (UserExitMessage returnMessage : messages)
			{
				buffer.append(returnMessage.toString());
			}
		}
		return buffer.toString();
	}

	/**
	 * Append the return data
	 * 
	 * @param returnData
	 *            - the return data to be added
	 */
	public void append(ReturnDataMFUserExit returnData)
	{
		// change
		changed = changed || returnData.isChanged();

		// list
		messages.addAll(returnData.getMessages());
	}

}
