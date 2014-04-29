package com.misys.equation.function.useraccess;

public class UserExitMessage
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UserExitMessage.java 13251 2012-04-19 08:16:27Z jonathan.perkins $";
	private String fieldName;
	private String msgText; // in DSEPMS format
	private int sequence;
	private String secondLevelText;

	/**
	 * Construct an empty Return message
	 */
	public UserExitMessage()
	{
		this.fieldName = "";
		this.msgText = "";
		this.sequence = 0;
		this.secondLevelText = "";
	}

	/**
	 * Construct a Return message
	 * 
	 * @param fieldName
	 *            - the field name
	 * @param msgText
	 *            - the message text
	 */
	public UserExitMessage(String fieldName, String msgText)
	{
		this.fieldName = fieldName;
		this.msgText = msgText;
	}

	/**
	 * Return the field name
	 * 
	 * @return the field name
	 */
	public String getFieldName()
	{
		return fieldName;
	}

	/**
	 * Set the field name
	 * 
	 * @param fieldName
	 *            - the field name
	 */
	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	/**
	 * Return the message text
	 * 
	 * @return the message text
	 */
	public String getMsgText()
	{
		return msgText;
	}

	/**
	 * Set the message id
	 * 
	 * @param msgText
	 *            - the message text
	 */
	public void setMsgText(String msgText)
	{
		this.msgText = msgText;
	}

	/**
	 * Return the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		return "[" + fieldName + " " + msgText + "]";
	}

	/**
	 * Return the sequence number
	 * 
	 * @return sequence
	 */
	public int getSequence()
	{
		return sequence;
	}

	/**
	 * Set the sequence number
	 * 
	 * @param sequence
	 *            - the sequence number
	 */
	public void setSequence(int sequence)
	{
		this.sequence = sequence;
	}

	/**
	 * Return the second level text
	 * 
	 * @return secondLevelText
	 */
	public String getSecondLevelText()
	{
		return secondLevelText;
	}

	/**
	 * Set the second level text
	 * 
	 * @param secondLevelText
	 *            - the second level text
	 */
	public void setSecondLevelText(String secondLevelText)
	{
		this.secondLevelText = secondLevelText;
	}

}
