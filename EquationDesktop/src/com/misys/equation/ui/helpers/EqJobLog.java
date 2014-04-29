package com.misys.equation.ui.helpers;

import java.util.Properties;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.JobLog;
import com.ibm.as400.access.QueuedMessage;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.ui.tools.EqDesktopToolBox;

public class EqJobLog extends EqMsgList
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqJobLog.java 11466 2011-07-18 09:53:40Z lima12 $";
	private final JobLog jobLog;

	/**
	 * Create a job log session
	 * 
	 * @param eqAS400
	 *            - the AS400 job
	 * @param jobName
	 *            - the job name
	 * @param jobUser
	 *            - the job user
	 * @param jobNumber
	 *            - the job number
	 * @param pos
	 *            - position
	 * @param njoblogs
	 *            - number of joblog entries
	 */
	public EqJobLog(AS400 eqAS400, String jobName, String jobUser, String jobNumber, int pos, int njoblogs)
	{
		jobLog = new JobLog(eqAS400, jobName, jobUser, jobNumber);
		jobLog.setListDirection(true);
		refresh(pos, njoblogs);
	}

	/**
	 * Retrieve the required joblog entries starting from the specified position
	 * 
	 * @param pos
	 *            - starting joblog entry to retrieve
	 * @param msgCount
	 *            - number of joblog entries to retrieve
	 */
	@Override
	public void refresh(int pos, int msgCount)
	{
		try
		{
			// get maximum length
			totalMessages = jobLog.getLength();

			// all messages can be displayed
			if (totalMessages < MAX_MSG && msgCount > 1)
			{
				queuePosition = 0;
				queueMessages = jobLog.getMessages(-1, 0);
			}

			// Reset
			else if (pos == RETRIEVE_FROM_START)
			{
				queuePosition = totalMessages - MAX_MSG;
				queueMessages = jobLog.getMessages(queuePosition, MAX_MSG);
			}

			else if (pos + msgCount > totalMessages)
			{
				queuePosition = totalMessages - MAX_MSG;
				queueMessages = jobLog.getMessages(queuePosition, msgCount);
			}

			else
			{
				queuePosition = pos;
				queueMessages = jobLog.getMessages(queuePosition, msgCount);
			}
		}
		catch (Exception arithmeticException)
		{
			EquationCommonContext.getContext().getLOG().error(arithmeticException);
		}
	}

	/**
	 * Return the onClickAction of the job log entry
	 * 
	 * @param msgq
	 *            - the message entry
	 * @param pos
	 *            - position within the joblog
	 */
	@Override
	public String onClickAction(QueuedMessage msgq, int pos)
	{
		String onClickAction = "javascript:showJobLog(" + "'" + jobLog.getName() + "'," + "'" + jobLog.getUser() + "'," + "'"
						+ jobLog.getNumber() + "'," + "'" + EqDesktopToolBox.formatDate(msgq.getDate()) + "'," + "'"
						+ EqDesktopToolBox.formatTime(msgq.getDate()) + "'," + "'" + msgq.getType() + "'," + "'"
						+ String.valueOf(pos) + "'" + ");";
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
							+ "<td class=\"messageStyle\" width=\"7%\">"
							+ EqDesktopToolBox.formatTime(msgq.getDate())
							+ "</td>"
							+ "<td class=\"messageStyle\" width=\"10%\">"
							+ EqDesktopToolBox.formatDate(msgq.getDate())
							+ "</td>"
							+ "<td class=\"messageStyle\" width=\"80%\"> "
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
							+ "<td class=\"messageStyle\" width=\"80%\"> " + EqDesktopToolBox.stripCtrlChar(msgq.getText())
							+ "</td>" + "<td class=\"messageStyle\" width=\"10%\">" + EqDesktopToolBox.formatDate(msgq.getDate())
							+ "</td>" + "<td class=\"messageStyle\" width=\"7%\">" + EqDesktopToolBox.formatTime(msgq.getDate())
							+ "</td>" + "</tr>";
		}
		return str;
	}

}
