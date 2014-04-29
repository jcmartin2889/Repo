// ------------------------------------------------------------------------------
// Copyright Misys IBS
//
// Owner: Des Middlemass
//
// Class: EQMessage: Class to store error/warning/info messages for an Equation field
// ------------------------------------------------------------------------------
package com.misys.equation.common.internal.eapi.core;

import com.misys.equation.common.utilities.Toolbox;

/***********************************************************************************************************************************
 * Represents an Equation message.
 * <P>
 * Equation provides realtime diagnostic messages during its processing. These can have severities of Errors, Warnings and
 * Information Only. Messages can be accessed using the object's getMessages method. This method returns an ArrayList of EQMessage,
 * EQFieldMessage, or EQCRMMessage objects.
 * <P>
 * 
 * @author Misys International Banking Systems Ltd.
 */
public class EQMessage extends Object implements java.io.Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQMessage.java 15086 2012-12-18 19:03:27Z williae1 $";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*******************************************************************************************************************************
	 * Status constant - object - the object has been completed its task succesfully. For an Enquiry this means that the retrieval
	 * completed. For a transaction this means that the database was succesfully updated.
	 */
	public static final String SEVERITY_INFO = "00";
	public static final String SEVERITY_WARNING = "10";
	public static final String SEVERITY_ERROR = "20";
	public static final String PARAM_DELIMETER = "!!=!!";
	public static final int DSEPMS_LENGTH = 37;

	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";

	// the id of the message, e.g. KSMnnnn
	protected String messageID;

	// the severity of the message, e.g. 00, 10, or 20
	protected String severity;

	// the formated description, in the language of the user
	protected String formattedMessage;

	// the message text, in the language of the user, no prefix
	// calculated on demand
	protected String unFormattedText;

	// the message in 37 character long
	protected String dsepms;

	// message parameter 1
	protected String messageParameter1;
	// message parameter 2
	protected String messageParameter2;
	// message parameter 3
	protected String messageParameter3;

	// colon-separated field names associated with the message
	protected String fieldNames;

	/*******************************************************************************************************************************
	 * Default Constructor.
	 */
	public EQMessage()
	{
		messageID = "";
		severity = "";
		formattedMessage = "";
		unFormattedText = null;
		messageParameter1 = "";
		messageParameter2 = "";
		messageParameter3 = "";
		dsepms = "";
		fieldNames = "";
	}

	/*******************************************************************************************************************************
	 * Constructs a simple EQFieldMessage.
	 * <P>
	 * Build an Equatiom message with only basic information:
	 * 
	 * <pre>
	 * messageID	7 chars;
	 * severity	2 chars;
	 * text		132 chars;
	 * </pre>
	 * 
	 * @param msgID
	 *            the KSM message id string
	 * @param sev
	 *            the severity string (e.g. 00, 10, 20)
	 * @param description
	 *            the message description
	 */
	public EQMessage(String msgID, String sev, String description, String dsepms)
	{
		this();
		this.messageID = msgID;
		this.severity = sev;

		// append '\' to every '\' found in DSEPMS string to avoid losing the backslash in resulting message
		dsepms = dsepms.replace("\\", "\\\\");
		// perform string replacement

		// special case where special marker exists, this will allow more than 10 characters of parameter
		// the format will be: KSMxxxxParameter 1......PARAM_DELIMETERParameter 2.....PARAM_DELIMETERParameter 3....
		if (dsepms.indexOf(PARAM_DELIMETER) >= 0)
		{
			String params = dsepms.substring(7);
			String[] paramList = params.split(PARAM_DELIMETER);
			String parm1 = "";
			String parm2 = "";
			String parm3 = "";
			if (paramList.length > 0)
			{
				parm1 = paramList[0].trim();
				setMessageParameter1(parm1);
			}
			if (paramList.length > 1)
			{
				parm2 = paramList[1].trim();
				setMessageParameter2(parm2);
			}
			if (paramList.length > 2)
			{
				parm3 = paramList[2].trim();
				setMessageParameter3(parm3);
			}
			description = description.replaceAll("&1", Toolbox.replaceString(parm1.trim()));
			description = description.replaceAll("&2", Toolbox.replaceString(parm2.trim()));
			description = description.replaceAll("&3", Toolbox.replaceString(parm3.trim()));
		}
		else
		{
			dsepms = Toolbox.pad(dsepms, 37);
			setMessageParameter1(dsepms.substring(7, 17));
			setMessageParameter2(dsepms.substring(17, 27));
			setMessageParameter3(dsepms.substring(27));

			// special case where &1&2&3 exists
			if (description.indexOf("&1&2&3") >= 0)
			{
				description = description.replaceAll("&1&2&3", Toolbox.replaceString(dsepms.substring(7).trim()));
			}

			// special case where &2&3 exists
			else if (description.indexOf("&2&3") >= 0)
			{

				String parm1 = dsepms.substring(7, 17);
				String parm2 = dsepms.substring(17);
				description = description.replaceAll("&1", Toolbox.replaceString(parm1.trim()));
				description = description.replaceAll("&2&3", Toolbox.replaceString(parm2.trim()));
			}
			else
			{
				// perform string replacement

				String parm1 = dsepms.substring(7, 17);
				String parm2 = dsepms.substring(17, 27);
				String parm3 = dsepms.substring(27);
				description = description.replaceAll("&1", Toolbox.replaceString(parm1.trim()));
				description = description.replaceAll("&2", Toolbox.replaceString(parm2.trim()));
				description = description.replaceAll("&3", Toolbox.replaceString(parm3.trim()));
			}
		}
		this.dsepms = dsepms.trim();
		this.unFormattedText = description.trim();
		this.formattedMessage = Toolbox.removeControlCharacter(unFormattedText);
		this.fieldNames = "";
	}

	/*******************************************************************************************************************************
	 * Constructs a simple EQFieldMessage.
	 * <P>
	 * Build an Equatiom message with only basic information:
	 * 
	 * <pre>
	 * messageID	7 chars;
	 * severity	2 chars;
	 * text		132 chars;
	 * </pre>
	 * 
	 * @param msgID
	 *            the KSM message id string
	 * @param sev
	 *            the severity string (e.g. 00, 10, 20)
	 * @param description
	 *            the message description
	 */
	public EQMessage(String msgID, String sev, String description)
	{
		this();
		this.messageID = msgID;
		this.severity = sev;
		this.dsepms = msgID.trim();
		this.unFormattedText = description.trim();
		this.formattedMessage = Toolbox.removeControlCharacter(unFormattedText);
		this.fieldNames = "";
	}

	/*******************************************************************************************************************************
	 * Get the Equation Error Message number.
	 * <P>
	 * 
	 * @return the KSM message id.
	 * @equation.external
	 */
	public String getMessageID()
	{
		return messageID;
	}

	/*******************************************************************************************************************************
	 * Return the severity of the message, one of the SEVERITY constants.
	 * <P>
	 * 
	 * @return the message severity
	 * @equation.external
	 */
	public String getSeverity()
	{
		return severity;
	}

	/*******************************************************************************************************************************
	 * Get the fully formatted message text.
	 * <P>
	 * 
	 * @return a formatted message string. This is the description prefixed by the KSM Message id, in the language of the user.
	 * @equation.external
	 */
	public String getFormattedMessage()
	{
		return formattedMessage;
	}

	/*******************************************************************************************************************************
	 * Get the message in 37 character format
	 * <P>
	 * 
	 * @return a message string in 37 character format
	 */
	public String getDsepms()
	{
		return dsepms;
	}

	/*******************************************************************************************************************************
	 * Get the message in 37 character format
	 * <P>
	 * 
	 * @return a message string in 37 character format
	 */
	public String getDsepms37()
	{
		if (dsepms.indexOf(PARAM_DELIMETER) >= 0)
		{
			String params = dsepms.substring(7);
			String[] paramList = params.split(PARAM_DELIMETER);
			String parm1 = "";
			String parm2 = "";
			String parm3 = "";
			if (paramList.length > 0)
			{
				parm1 = Toolbox.pad(Toolbox.trim(paramList[0].trim(), 10), 10);
			}
			if (paramList.length > 1)
			{
				parm2 = Toolbox.pad(Toolbox.trim(paramList[1].trim(), 10), 10);
			}
			if (paramList.length > 2)
			{
				parm3 = Toolbox.pad(Toolbox.trim(paramList[2].trim(), 10), 10);
			}

			return dsepms.substring(0, 7) + parm1 + parm2 + parm3;
		}
		else
		{
			return Toolbox.trim(dsepms, DSEPMS_LENGTH);
		}
	}

	/*******************************************************************************************************************************
	 * Get the unformatted message text.
	 * <P>
	 * 
	 * @return a unformatted message string. This is the description prefixed by the KSM Message id, in the language of the user.
	 */
	public String getUnFormattedText()
	{
		return unFormattedText;
	}

	/*******************************************************************************************************************************
	 * Set the Equation Error Message number.
	 * 
	 */
	public void setMessageID(String messageID)
	{
		this.messageID = messageID;
	}

	/*******************************************************************************************************************************
	 * Set the severity of the message, one of the SEVERITY constants.
	 * 
	 */
	public void setSeverity(String severity)
	{
		this.severity = severity;
	}

	/*******************************************************************************************************************************
	 * Set the fully formatted message text.
	 * 
	 */
	public void setFormattedMessage(String formattedMessage)
	{
		this.formattedMessage = formattedMessage;
	}

	/*******************************************************************************************************************************
	 * Set the unformatted message text.
	 * 
	 * @equation.external
	 * 
	 */
	public void setUnFormattedText(String unFormattedText)
	{
		this.unFormattedText = unFormattedText;
	}

	/*******************************************************************************************************************************
	 * Set the message in 37 character format
	 * 
	 */
	public void setDsepms(String dsepms)
	{
		this.dsepms = dsepms;
	}

	/*******************************************************************************************************************************
	 * Get a string representation of the message.
	 * <P>
	 * 
	 * @return getFormattedMessage().
	 */
	@Override
	public String toString()
	{
		return getFormattedMessage();
	}
	/**
	 * Get message parameter 1
	 * <P>
	 * 
	 * @return message parameter 1. This is positions 8-17 of DSEPMS.
	 * @equation.external
	 */
	public String getMessageParameter1()
	{
		return messageParameter1;
	}

	private void setMessageParameter1(String messageParameter1)
	{
		this.messageParameter1 = messageParameter1;
	}
	/**
	 * Get message parameter 2
	 * <P>
	 * 
	 * @return message parameter 2. This is positions 18-27 of DSEPMS.
	 * @equation.external
	 */
	public String getMessageParameter2()
	{
		return messageParameter2;
	}

	private void setMessageParameter2(String messageParameter2)
	{
		this.messageParameter2 = messageParameter2;
	}
	/**
	 * Get message parameter 3
	 * <P>
	 * 
	 * @return message parameter 3. This is positions 28-37 of DSEPMS.
	 * @equation.external
	 */
	public String getMessageParameter3()
	{
		return messageParameter3;
	}

	private void setMessageParameter3(String messageParameter3)
	{
		this.messageParameter3 = messageParameter3;
	}

	/**
	 * Return the field names associated with the message
	 * 
	 * @return the field names associated with the message
	 * @equation.external
	 */
	public String getFieldNames()
	{
		return fieldNames;
	}

	/**
	 * Set the field names associated with the message
	 * 
	 * @param fieldNames
	 *            - the field names associated with the message
	 * @equation.external
	 */
	public void setFieldNames(String fieldNames)
	{
		this.fieldNames = fieldNames;
	}

}
