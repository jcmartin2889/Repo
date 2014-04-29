package com.misys.equation.ui.helpers;

import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Properties;

import com.misys.equation.bankfusion.lrp.bean.TaskQueryDetail;
import com.misys.equation.bankfusion.lrp.bean.TaskQueryDetails;
import com.misys.equation.bankfusion.lrp.engine.ITaskEngine;
import com.misys.equation.bankfusion.lrp.engine.TaskEngineToolbox;
import com.misys.equation.bankfusion.tools.LRPToolbox;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IWE2RecordDao;
import com.misys.equation.common.dao.beans.GBRecordDataModel;
import com.misys.equation.common.dao.beans.WE2RecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.ui.tools.CategorySlot;
import com.misys.equation.ui.tools.EqDesktopToolBox;

public class EqWorkLoadListLRP
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqWorkLoadListLRP.java 14412 2012-09-04 12:37:46Z williae1 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EqWorkLoadListLRP.class);

	public EqWorkLoadListLRP()
	{
	}

	/**
	 * Construct the HTML for LRP
	 * 
	 * @param eqProperties
	 *            - Properties
	 * @param equationUser
	 *            - Equation User
	 * @param taskId
	 *            - the task id
	 * @param optionId
	 *            - option id
	 * @param date
	 *            - date
	 * @param time
	 *            - time
	 * @param origUser
	 *            - originating user
	 * @param isClaimed
	 *            - true if task is claimed
	 * @param taskType
	 *            - the task type
	 * @param index
	 *            - the nth index
	 * @param taskExist
	 *            - true if task is waiting for ESF authorisation
	 * @param weKey
	 *            - the WE key if waiting for ESF authorisation
	 * 
	 * @return the equivalent HTML
	 * 
	 * @throws Exception
	 */
	private String formatLRPToHTML(Properties eqProperties, EquationUser equationUser, String taskId, String optionId, String date,
					String time, String origUser, boolean isClaimed, String taskType, int index, boolean taskExist, String weKey)
					throws Exception
	{
		String hoverText = setupLRPHoverText(taskId, weKey, equationUser.isLanguageRTL());
		String text = setupText(eqProperties, equationUser, taskId, taskType, optionId, date, time, origUser);
		String contextMenu = " oncontextmenu=\"return false\" ";

		String anchor = "";
		if (taskExist)
		{
			anchor = EqDesktopToolBox.formatIntoSpanHTML(text, hoverText, "", "wf_NOWRAP", contextMenu);
		}
		else
		{
			String onClickAction = setupLRPOnClickAction(taskId, taskType, optionId);
			anchor = EqDesktopToolBox.formatIntoAnchorHTML(text, hoverText, onClickAction, "", contextMenu);
		}
		String li = EqDesktopToolBox.formatLI(taskId, anchor);

		return li;
	}

	/**
	 * This method process LRP tasks
	 * 
	 * @param eqProperties
	 * @param equationUser
	 * @param categorySlot
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public boolean processLRPTask(Properties eqProperties, EquationUser equationUser, CategorySlot categorySlot) throws Exception
	{
		// Get date instances
		GregorianCalendar dateInstance = new GregorianCalendar();
		GregorianCalendar nowInstance = new GregorianCalendar();
		int idx = 0;

		// Retrieve the list of task
		String sessionIdentifier = equationUser.getSession().getSessionIdentifier();
		ITaskEngine taskEngine = EquationFunctionContext.getContext().getTaskEngine(sessionIdentifier);
		TaskQueryDetails taskDetails = taskEngine.getTaskLists();
		Collections.sort(taskDetails.getTaskDetails(), new LRPToolbox.sortTasks());

		// Handle to workload list
		String userId = equationUser.getUserId();
		EqWorkLoadList eqworkloadList = new EqWorkLoadList(userId);

		// Assume empty
		boolean empty = true;

		// Loop all the returned details
		Iterator<TaskQueryDetail> iTaskDetail = taskDetails.iterator();
		while (iTaskDetail.hasNext())
		{
			TaskQueryDetail taskDetail = iTaskDetail.next();
			String optionId = taskDetail.getOptionId();
			String taskId = taskDetail.getTkiid();
			String date = taskDetail.getCreateDate();
			String time = taskDetail.getCreateTime();
			String origUser = taskDetail.getOriginator();
			boolean claim = taskDetail.isClaimed();
			String type = taskDetail.getType();
			String weKey = "";

			// make sure task is valid to be shown on the user
			if (!taskDetail.isTaskValidForUser(equationUser))
			{
				continue;
			}

			idx++;

			// Check if task Id exists in WE2PF
			IWE2RecordDao dao = new DaoFactory().getWE2Dao(equationUser.getSession(), new WE2RecordDataModel(taskId));
			WE2RecordDataModel we2record = dao.getWE2Record();

			boolean taskExist = false;

			if (we2record != null && we2record.getTaskId().equals(taskId))
			{
				taskExist = true;

				String weOptionId = we2record.getLinkedOptionId();
				String weUserId = we2record.getLinkedUserId();
				String weSessionId = we2record.getLinkedSessionId() + we2record.getLinkedTaskId();
				String weTransactionId = we2record.getLinkedTransactionId();
				weKey = eqworkloadList.setupHoverText(weOptionId, weSessionId, weTransactionId, weUserId, "", "", equationUser
								.isLanguageRTL());
			}

			String htmlStr = formatLRPToHTML(eqProperties, equationUser, taskId, optionId, date, time, origUser, claim, type, idx,
							taskExist, weKey);

			// retrieve the main category
			int slotCategory = getLRPCategory(type, taskDetail.isClaimed());

			// retrieve the sub-category (date order)
			dateInstance.set(1900 + Integer.parseInt(date.substring(0, 3)), Integer.parseInt(date.substring(3, 5)) - 1, Integer
							.parseInt(date.substring(5, 7)));
			int slotDate = EqDesktopToolBox.getSlot(nowInstance, dateInstance);

			// add to the list
			// TODO:: Confirm if we should be adding only the tasks that exist in WE2PF for this part
			categorySlot.addContent(slotCategory, slotDate, htmlStr);

			empty = false;
		}

		return empty;
	}
	/**
	 * Retrieves LRP category
	 * 
	 * @param taskType
	 *            - the task type
	 * @param claimed
	 *            - true if the task is claimed
	 * 
	 * @return the category
	 */
	public int getLRPCategory(String taskType, boolean claimed)
	{
		int slot = EqWorkLoadList.TRAN_OTHER; // Unknown status

		// Data entry task
		if (taskType.equals(TaskEngineToolbox.TASK_TYPE_DATA_ENTRY))
		{
			if (claimed)
			{
				slot = EqWorkLoadList.LRP_ENTRY_CLAIMED;
			}
			else
			{
				slot = EqWorkLoadList.LRP_ENTRY;
			}
		}

		// Authorisation task
		else if (taskType.equals(TaskEngineToolbox.TASK_TYPE_AUTH))
		{
			if (claimed)
			{
				slot = EqWorkLoadList.LRP_AUTH_CLAIMED;
			}
			else
			{
				slot = EqWorkLoadList.LRP_AUTH;
			}
		}

		// ESF task
		else if (taskType.equals(TaskEngineToolbox.TASK_TYPE_ESF))
		{
			if (claimed)
			{
				slot = EqWorkLoadList.LRP_ESF_CLAIMED;
			}
			else
			{
				slot = EqWorkLoadList.LRP_ESF;
			}
		}

		// return the slot
		return slot;
	}

	/**
	 * Return the on click action for this transaction
	 * 
	 * @param taskId
	 *            - the task id
	 * @param taskType
	 *            - the task type
	 * @param optionId
	 *            - the optionId
	 * 
	 * @return the on click action for this transaction
	 */
	public String setupLRPOnClickAction(String taskId, String taskType, String optionId)
	{
		return "javascript:routeToLRPTask2('" + taskId + "','" + taskType + "','" + optionId + "')";
	}

	/**
	 * Generate hover text for use in LRP
	 * 
	 * @param taskId
	 *            - the task id
	 * @param weKey
	 *            - the WE Key
	 * @param rtl
	 *            - right to left?
	 * 
	 * @return the hover text
	 */
	public String setupLRPHoverText(String taskId, String weKey, boolean rtl)
	{
		StringBuilder hoverText = new StringBuilder();
		String delimeter = " ";

		if (rtl)
		{
			hoverText.append(weKey);
			hoverText.append(delimeter);
			hoverText.append(taskId);
			hoverText.append(delimeter);
		}
		else
		{
			hoverText.append(taskId);
			hoverText.append(delimeter);
			hoverText.append(weKey);
			hoverText.append(delimeter);
		}

		// return the hover text
		return hoverText.toString();
	}

	/**
	 * Setup the text
	 * 
	 * @param eqProperties
	 *            - Properties
	 * @param equationUser
	 *            - Equation User
	 * @param taskId
	 *            - task id
	 * @param taskType
	 *            - the task type
	 * @param optionId
	 *            - option Id
	 * @param date
	 *            - date
	 * @param time
	 *            - time
	 * @param origUser
	 *            - originating user
	 * 
	 * @return the text
	 */
	public String setupText(Properties eqProperties, EquationUser equationUser, String taskId, String taskType, String optionId,
					String date, String time, String origUser)
	{
		StringBuilder text = new StringBuilder();
		String delimeter = " ";
		String sdate = Toolbox.formatDate(date);
		String stime = Toolbox.formatTime(time, 6);
		String optionTitle = "";
		String user = origUser.toUpperCase();

		// do not display the user for non-ESF
		if (!taskType.equals(TaskEngineToolbox.TASK_TYPE_ESF))
		{
			user = "";
		}

		try
		{
			GBRecordDataModel gbRecord = equationUser.getEquationUnit().getRecords().getGBRecord(optionId);
			optionTitle = gbRecord.getProgramTitle();
		}
		catch (EQException e)
		{
			LOG.error("Unable to find option for task " + taskId + "-" + optionId);
		}

		if (equationUser.isLanguageRTL())
		{
			text.append(optionTitle);
			text.append(delimeter);
			text.append(optionId);
			text.append(delimeter);
			text.append(user);
			text.append(delimeter);
			text.append(stime);
			text.append(delimeter);
			text.append(sdate);
			text.append(delimeter);
		}
		else
		{
			text.append(sdate);
			text.append(delimeter);
			text.append(stime);
			text.append(delimeter);
			text.append(delimeter);
			text.append(user);
			text.append(delimeter);
			text.append(optionId);
			text.append(delimeter);
			text.append(optionTitle);
			text.append(delimeter);
		}

		// return the text
		return text.toString();
	}

}
