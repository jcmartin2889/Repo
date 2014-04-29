/*
 * Created on 16-Mar-2007
 */
package com.misys.equation.ui.helpers;

import java.util.Properties;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.MessageQueue;
import com.ibm.as400.access.QueuedMessage;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.ui.tools.EqDesktopToolBox;

/**
 * @author Administrator
 * 
 */
public class EqMsgQueue extends EqMsgList
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqMsgQueue.java 11466 2011-07-18 09:53:40Z lima12 $";
	private MessageQueue messageQueue;

	/**
	 * Construct a message queue
	 * 
	 * @param eqAS400
	 *            - the AS400 job
	 * @param msgq
	 *            - the message queue name
	 * @param pos
	 *            - the starting position
	 * @param nmsgs
	 *            - number of message entry to be retrieved
	 * 
	 * @throws Exception
	 */
	public EqMsgQueue(AS400 eqAS400, String msgq, int pos, int nmsgs) throws Exception
	{
		if (msgq.equals(""))
		{
			messageQueue = new MessageQueue(eqAS400, MessageQueue.CURRENT);
		}
		else
		{
			messageQueue = new MessageQueue(eqAS400, msgq);
		}
		messageQueue.setListDirection(true);
		refresh(pos, nmsgs);
	}

	/**
	 * Retrieve the required message entries starting from the specified position
	 * 
	 * @param pos
	 *            - starting message entry to retrieve
	 * @param msgCount
	 *            - number of message entries to retrieve
	 */
	@Override
	public void refresh(int pos, int msgCount) throws Exception
	{
		// get maximum length
		totalMessages = messageQueue.getLength();

		// all messages can be displayed
		if (totalMessages < MAX_MSG && msgCount > 1)
		{
			queuePosition = 0;
			queueMessages = messageQueue.getMessages(-1, 0);
		}

		// Reset
		else if (pos == RETRIEVE_FROM_START)
		{
			queuePosition = totalMessages - MAX_MSG;
			queueMessages = messageQueue.getMessages(queuePosition, MAX_MSG);
		}

		else if (pos + msgCount > totalMessages)
		{
			queuePosition = totalMessages - MAX_MSG;
			queueMessages = messageQueue.getMessages(queuePosition, msgCount);
		}

		else
		{
			queuePosition = pos;
			queueMessages = messageQueue.getMessages(queuePosition, msgCount);
		}
	}

	/**
	 * Return the onClickAction of the message entry
	 * 
	 * @param msgq
	 *            - the message entry
	 * @param pos
	 *            - position within the message
	 */
	@Override
	public String onClickAction(QueuedMessage msgq, int pos)
	{
		String onClickAction = "javascript:showMsgQueue(" + "'" + msgq.getQueue().getPath() + "'," + "'"
						+ EqDesktopToolBox.formatDate(msgq.getDate()) + "'," + "'" + EqDesktopToolBox.formatTime(msgq.getDate())
						+ "'," + "'" + msgq.getType() + "'," + "'" + String.valueOf(pos) + "'" + ");";
		return onClickAction;
	}

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
	 *            - Equation User
	 */
	@Override
	public String formatMsgToHTML(QueuedMessage msgq, int pos, Properties eqProperties, EquationUser equationUser)
	{
		String str;
		if (equationUser.isLanguageRTL())
		{
			str = "<tr> "
							+ "<td class=\"messageStyle\" width=\"10%\"> "
							+ msgq.getUser()
							+ "</td>"
							+ "<td class=\"messageStyle\" width=\"7%\">"
							+ EqDesktopToolBox.formatTime(msgq.getDate())
							+ "</td>"
							+ "<td class=\"messageStyle\" width=\"10%\">"
							+ EqDesktopToolBox.formatDate(msgq.getDate())
							+ "</td>"
							+ "<td class=\"messageStyle\" width=\"70%\"> "
							+ EqDesktopToolBox.stripCtrlChar(msgq.getText())
							+ "</td>"
							+ "<td class=\"messageStyle\" width=\"3%\"> "
							+ formatIntoAnchor(msgq, pos,
											cvtMsgTypeToImage(msgq.getType(), "img" + pos, eqProperties, equationUser)) + "</td>"
							+ "</tr>";
		}
		else
		{
			str = "<tr> "
							+ "<td class=\"messageStyle\" width=\"3%\"> "
							+ formatIntoAnchor(msgq, pos,
											cvtMsgTypeToImage(msgq.getType(), "img" + pos, eqProperties, equationUser)) + "</td>"
							+ "<td class=\"messageStyle\" width=\"70%\"> " + EqDesktopToolBox.stripCtrlChar(msgq.getText())
							+ "</td>" + "<td class=\"messageStyle\" width=\"10%\">" + EqDesktopToolBox.formatDate(msgq.getDate())
							+ "</td>" + "<td class=\"messageStyle\" width=\"7%\">" + EqDesktopToolBox.formatTime(msgq.getDate())
							+ "</td>" + "<td class=\"messageStyle\" width=\"10%\"> " + msgq.getUser() + "</td>" + "</tr>";
		}
		return str;
	}
}
