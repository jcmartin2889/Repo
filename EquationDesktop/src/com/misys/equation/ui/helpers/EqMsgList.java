package com.misys.equation.ui.helpers;

import java.util.Calendar;
import java.util.Properties;

import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.QueuedMessage;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.ui.tools.EqDesktopToolBox;

public abstract class EqMsgList
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqMsgList.java 16593 2013-06-24 15:32:19Z perkinj1 $";
	public static final int MAX_MSG = 50;
	public static final int RETRIEVE_FROM_START = -1;
	public static final int RETRIEVE_UP = 1;
	public static final int RETRIEVE_DOWN = 2;

	// all messages available
	protected QueuedMessage[] queueMessages;
	protected int queuePosition;
	protected int totalMessages;

	// current message
	private QueuedMessage curMessage;

	/**
	 * Construct an empty message list
	 */
	public EqMsgList()
	{
		totalMessages = 0;
		queuePosition = -1;
		queueMessages = null;
		curMessage = null;
	}

	/**
	 * Retrieve the message list starting from the specified position
	 * 
	 * @param pos
	 *            - the start position within the message list
	 * @param msgCount
	 *            - the number of message entries to retrieve
	 * 
	 * @throws Exception
	 */
	public abstract void refresh(int pos, int msgCount) throws Exception;

	/**
	 * Return the onClickAction for this message entry
	 * 
	 * @param msgq
	 *            - the message entry details
	 * @param pos
	 *            - the position within the message list
	 * 
	 * @return the onclick Action
	 */
	public abstract String onClickAction(QueuedMessage msgq, int pos);

	/**
	 * Return the HTML equivalent of the message
	 * 
	 * @param msgq
	 *            - the message entry
	 * @param pos
	 *            - position within the joblog
	 * @param eqProperties
	 *            - properties
	 * @param equationUser
	 *            - EquationUser
	 */
	public abstract String formatMsgToHTML(QueuedMessage msgq, int pos, Properties eqProperties, EquationUser equationUser);

	/**
	 * Return the queue position
	 * 
	 * @return
	 */
	public int getQueuePosition()
	{
		return queuePosition;
	}

	/**
	 * Return the total number of entries in the message list
	 * 
	 * @return the total number of entries in the message list
	 */
	public int getTotalMessages()
	{
		return totalMessages;
	}

	/**
	 * Return the HTML representation of the message list
	 * 
	 * @param eqProperties
	 *            - properties
	 * @param equationUser
	 *            - Equation User
	 * 
	 * @return the HTML representation of the message list
	 */
	public String toHTML(Properties eqProperties, EquationUser equationUser)
	{
		StringBuffer strHTML = new StringBuffer();

		// initial HTML
		if (equationUser.isLanguageRTL())
		{
			strHTML.append("<table class='wf_RIGHT_ALIGN'>");
		}
		else
		{
			strHTML.append("<table>");
		}

		// offset
		int offset = strHTML.length();

		// content
		if (queueMessages != null)
		{
			for (int i = 0; i < queueMessages.length; i++)
			{
				strHTML.insert(offset, formatMsgToHTML(queueMessages[i], i + queuePosition, eqProperties, equationUser));
			}
		}
		else
		{
			strHTML.append("<tr><td>");
			strHTML.append(EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000002"));
			strHTML.append("</tr></td>");
		}
		strHTML.append("</table>");
		// final HTML
		return strHTML.toString();
	}

	/**
	 * Return the anchor HTML element of the message
	 * 
	 * @param msgq
	 *            - the message entry
	 * @param pos
	 *            - the position in the message list
	 * @param text
	 *            - text to display
	 * 
	 * @return the anchor HTML element of the message
	 */
	public String formatIntoAnchor(QueuedMessage msgq, int pos, String text)
	{
		String str;
		str = EqDesktopToolBox.formatIntoAnchorHTML(text, "", onClickAction(msgq, pos), "messageStyle");
		return str;
	}

	/**
	 * Return the icon representation of the message
	 * 
	 * @param type
	 *            - message type
	 * @param id
	 *            - id of the message entry
	 * @param eqProperties
	 *            - properties
	 * @param languageId
	 *            - language of the user
	 * 
	 * @return the icon representation of the message
	 */
	public static String cvtMsgTypeToImage(int type, String id, Properties eqProperties, EquationUser equationUser)
	{
		String str = "";
		switch (type)
		{
			case AS400Message.INFORMATIONAL:
				str = EqDesktopToolBox.formatIntoImageHTML("../images/EqMsgInfo.gif", cvtMsgTypeToStr(type, eqProperties,
								equationUser), id);
				break;
			case AS400Message.INQUIRY:
				str = EqDesktopToolBox.formatIntoImageHTML("../images/EqMsgInq.gif", cvtMsgTypeToStr(type, eqProperties,
								equationUser), id);
				break;
			case AS400Message.REPLY_FROM_SYSTEM_REPLY_LIST:
			case AS400Message.REPLY_MESSAGE_DEFAULT_USED:
			case AS400Message.REPLY_NOT_VALIDITY_CHECKED:
			case AS400Message.REPLY_SYSTEM_DEFAULT_USED:
			case AS400Message.REPLY_VALIDITY_CHECKED:
				str = EqDesktopToolBox.formatIntoImageHTML("../images/EqMsgReply.gif", cvtMsgTypeToStr(type, eqProperties,
								equationUser), id);
				break;
			case AS400Message.DIAGNOSTIC:
			case AS400Message.ESCAPE:
			case AS400Message.ESCAPE_NOT_HANDLED:
				str = EqDesktopToolBox.formatIntoImageHTML("../images/EqMsgEsc.gif", cvtMsgTypeToStr(type, eqProperties,
								equationUser), id);
				break;
			case AS400Message.COMPLETION:
				str = EqDesktopToolBox.formatIntoImageHTML("../images/EqMsgComp.gif", cvtMsgTypeToStr(type, eqProperties,
								equationUser), id);
				break;
			case AS400Message.NOTIFY:
			case AS400Message.NOTIFY_NOT_HANDLED:
				str = EqDesktopToolBox.formatIntoImageHTML("../images/EqMsgWarn.gif", cvtMsgTypeToStr(type, eqProperties,
								equationUser), id);
				break;
			case AS400Message.SENDERS_COPY:
				// case AS400Message.MESSAGE_OPTION_ALL:
				// case AS400Message.MESSAGE_OPTION_NONE:
				// case AS400Message.MESSAGE_OPTION_UP_TO_10:
			case AS400Message.REQUEST:
			case AS400Message.REQUEST_WITH_PROMPTING:
				str = EqDesktopToolBox.formatIntoImageHTML("../images/EqMsgOther.gif", cvtMsgTypeToStr(type, eqProperties,
								equationUser), id);
				break;
		}
		return str;
	}

	/**
	 * Return the String representation of the message type
	 * 
	 * @param type
	 *            - message type
	 * @param eqProperties
	 *            - properties
	 * @param equationUser
	 *            - Equation User
	 * 
	 * @return the String representation of the message type
	 */
	public static String cvtMsgTypeToStr(int type, Properties eqProperties, EquationUser equationUser)
	{
		String str = "";
		switch (type)
		{
			case AS400Message.INFORMATIONAL:
				str = "GBLINFOL";
				break;
			case AS400Message.INQUIRY:
				str = "GBLINQ";
				break;
			case AS400Message.REPLY_FROM_SYSTEM_REPLY_LIST:
			case AS400Message.REPLY_MESSAGE_DEFAULT_USED:
			case AS400Message.REPLY_NOT_VALIDITY_CHECKED:
			case AS400Message.REPLY_SYSTEM_DEFAULT_USED:
			case AS400Message.REPLY_VALIDITY_CHECKED:
				str = "GBLREPLY";
				break;
			case AS400Message.SENDERS_COPY:
				str = "GBL000062";
				break;
			case AS400Message.COMPLETION:
				str = "GBLCOMPN";
				break;
			case AS400Message.DIAGNOSTIC:
				str = "GBLDIAGC";
				break;
			case AS400Message.ESCAPE:
			case AS400Message.ESCAPE_NOT_HANDLED:
				str = "GBLESC";
				break;
			// case AS400Message.MESSAGE_OPTION_ALL:
			// case AS400Message.MESSAGE_OPTION_NONE:
			// case AS400Message.MESSAGE_OPTION_UP_TO_10:
			case AS400Message.NOTIFY:
			case AS400Message.NOTIFY_NOT_HANDLED:
				str = "GBLNOTFY";
				break;
			case AS400Message.REQUEST:
			case AS400Message.REQUEST_WITH_PROMPTING:
				str = "GBLRQST";
				break;
		}
		return EquationCommonContext.getContext().getLanguageResource(equationUser, str);
	}

	/**
	 * Add control status to determine whether there are more messages or not (for page up/ page down
	 * 
	 * @param len
	 *            - position of this message entry
	 * @param pos
	 *            - position of the top most message
	 * 
	 * @return the control status to determine whether there are more messages or not (for page up/ page down
	 */
	public static String addStatus(int len, int pos)
	{
		// position 0 --> Y = later messages exists
		// position 1 --> Y = older messages exists
		// position 2-9 --> position of the topmost message within the list
		StringBuffer str = new StringBuffer("                    ");

		// determine if later messages exist
		if (len > (pos + MAX_MSG))
		{
			str.replace(0, 1, "Y");
		}

		// determine if older messages exist
		if (pos > 1)
		{
			str.replace(1, 2, "Y");
		}

		// add the current position
		str.replace(2, 10, String.valueOf(pos));

		// append control end
		str.append("*x*x*x*");

		// return
		return str.toString();
	}

	/**
	 * Return the message identified by the index
	 * 
	 * @param index
	 *            - the message index within the current list
	 * 
	 * @return true if message has been retrieved
	 */
	public boolean getMessage(int index)
	{
		if (index < 0 || index >= queueMessages.length)
		{
			curMessage = null;
			return false;
		}
		curMessage = queueMessages[index];
		return true;
	}

	/**
	 * Return the message id of the current message
	 * 
	 * @return the message id of the current message
	 */
	public String getId()
	{
		// message description not setup
		if (curMessage == null)
		{
			return ("");
		}

		// return Id
		return curMessage.getID();
	}

	/**
	 * Return the message text of the current message
	 * 
	 * @return the message text of the current message
	 */
	public String getText()
	{
		// message description not setup
		if (curMessage == null)
		{
			return ("");
		}

		// return Id
		return curMessage.getText();
	}

	/**
	 * Return the message severity of the current message
	 * 
	 * @return the message severity of the current message
	 */
	public int getSeverity()
	{
		// message description not setup
		if (curMessage == null)
		{
			return -1;
		}

		// return severity
		return curMessage.getSeverity();
	}

	/**
	 * Return the second level message of the current message
	 * 
	 * @return the second level message of the current message
	 */
	public String getHelp()
	{
		String str;
		// message description not setup
		if (curMessage == null)
		{
			return ("");
		}

		// check if 2nd level text has any substitution text
		str = curMessage.getHelp();
		return str;
	}

	/**
	 * Return the message user of the current message
	 * 
	 * @return the message user of the current message
	 */
	public String getUser()
	{
		// message description not setup
		if (curMessage == null)
		{
			return ("");
		}

		// return severity
		return curMessage.getUser();
	}

	/**
	 * Return the message date of the current message
	 * 
	 * @return the message date of the current message
	 */
	public Calendar getDate()
	{
		// message description not setup
		if (curMessage == null)
		{
			return (Calendar.getInstance());
		}

		// return severity
		return curMessage.getDate();
	}

	/**
	 * Return the message type of the current message
	 * 
	 * @return the message type of the current message
	 */
	public int getType()
	{
		// message description not setup
		if (curMessage == null)
		{
			return (-1);
		}

		// return severity
		return curMessage.getType();
	}

	/**
	 * Return the message path of the current message
	 * 
	 * @return the message path of the current message
	 */
	public String getPath()
	{
		// message description not setup
		if (curMessage == null)
		{
			return ("");
		}

		// return severity
		return curMessage.getPath();
	}

}
