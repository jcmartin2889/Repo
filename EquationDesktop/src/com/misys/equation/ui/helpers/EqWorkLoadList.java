package com.misys.equation.ui.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.GregorianCalendar;
import java.util.Properties;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IWE2RecordDao;
import com.misys.equation.common.dao.beans.WE2RecordDataModel;
import com.misys.equation.common.dao.beans.WERecordDataModel;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.ui.tools.CategorySlot;
import com.misys.equation.ui.tools.EqDesktopToolBox;

public class EqWorkLoadList
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqWorkLoadList.java 16593 2013-06-24 15:32:19Z perkinj1 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EqWorkLoadList.class);

	// Transaction category
	public static final int TRAN_IGNORE = -1; // ignored transactions
	public static final int TRAN_TMPLT = 0; // template transactions
	public static final int TRAN_SAVED = 1; // saved transactions
	public static final int TRAN_INPRG = 2; // in progress
	public static final int TRAN_REFER = 3; // referred transactions
	public static final int TRAN_REQST = 4; // request for authorisation
	public static final int TRAN_OTHER = 5; // unknown status

	// For use by LRP
	public static final int LRP_ENTRY = 6; // data entry task
	public static final int LRP_ENTRY_CLAIMED = 7; // claimed data entry task
	public static final int LRP_AUTH = 8; // authorization task
	public static final int LRP_AUTH_CLAIMED = 9; // claimed authorization task
	public static final int LRP_ESF = 10; // ESF task
	public static final int LRP_ESF_CLAIMED = 11; // claimed ESF task

	// For use by Maker-Checker
	public static final int MAKER_CHECKER_SUBMITTED = 12; // submitted for checking
	public static final int MAKER_CHECKER_AWAITING = 13; // awaiting checking
	public static final int MAKER_CHECKER_REJECTED = 14; // rejected by checker
	public static final int MAKER_CHECKER_APPROVED = 15; // request authorisation
	public static final int MAKER_CHECKER_REVIEWING_MKR = 16; // being reviewed by maker

	private String userId;
	private String id = "WLOAD";

	/**
	 * Construct a new work load list
	 * 
	 */
	public EqWorkLoadList(String userId)
	{
		this.userId = Toolbox.trim(userId, 4);
	}

	/**
	 * Return the HTML equivalent for the spooled files
	 * 
	 * @param eqProperties
	 *            - properties
	 * @param equationUser
	 *            - Equation User
	 * 
	 * @return the spooled file list in HTML format
	 * 
	 * @throws Exception
	 */
	public String toHTML(Connection connection, Properties eqProperties, EquationUser equationUser) throws Exception
	{
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		StringBuilder strHTML = new StringBuilder();
		try
		{
			// select the following records
			// 1. saved transactions belonging to this user
			// 2. transactions referred to this user
			// 3. transactions referred to another user
			// 4. all template transactions (across all users)
			// 5. submitted, rejected, or approved maker-checker transactions belonging to this user
			// 6. submitted maker-checker transactions referred to this user
			String sqlString = "SELECT WEOID, WESID, WETID, WEASTS, WEDTE, WETIM, WEUID, WEAUID, WEJBD, WEJBN, WESCRN, WEONM, WEUID2, WESSET, WEAUTH, WERTXT, WEOFFL FROM WEPF "
							+ "WHERE (WEUID=? AND WEAUID='') "
							+ "or (WEAUID=? and (WEASTS=? or WEASTS=?)) "
							+ "or WEUID=? AND WEAUID<>? AND WEAUID<>'' AND WEASTS<>'7' "
							+ "or WEASTS='T' "
							+ "or (WEUID=? AND (WEASTS=? OR WEASTS=? OR WEASTS=?)) "
							+ "or (WEAUID=? AND WEASTS=?) "
							+ "ORDER BY WEDTE DESC, WETIM DESC";
			statement = connection.prepareStatement(sqlString, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, userId);
			statement.setString(2, userId);
			statement.setString(3, WERecordDataModel.STAT_AVAIL);
			statement.setString(4, WERecordDataModel.STAT_CHECK);
			statement.setString(5, userId);
			statement.setString(6, userId);
			statement.setString(7, userId);
			statement.setString(8, WERecordDataModel.MAKER_CHECKER_STAT_AWAIT);
			statement.setString(9, WERecordDataModel.MAKER_CHECKER_STAT_REJCTD);
			statement.setString(10, WERecordDataModel.MAKER_CHECKER_STAT_APPRVD);
			statement.setString(11, userId);
			statement.setString(12, WERecordDataModel.MAKER_CHECKER_STAT_AWAIT);
			resultSet = statement.executeQuery();

			// Slot by Status (e.g template, saved transactions, referred transactions, request transactions, etc)
			CategorySlot categorySlot = new CategorySlot(getSlotStatusName(eqProperties, equationUser), EqDesktopToolBox
							.getSlotName(eqProperties, equationUser));

			// Process the result set
			boolean empty1 = processResultSet(eqProperties, equationUser, categorySlot, resultSet);

			// Think we need to pass the resultSet as well and call processResultSet for task not in WE2PF?
			if (EquationCommonContext.getContext().isLRPProcessing())
			{
				EqWorkLoadListLRP eqworkloadListLRP = new EqWorkLoadListLRP();
				boolean empty2 = eqworkloadListLRP.processLRPTask(eqProperties, equationUser, categorySlot);
				empty1 = empty1 && empty2;
			}

			// Construct
			if (empty1)
			{
				strHTML.append(EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL900039"));
			}
			else
			{
				String[] slotStatus = categorySlot.getCategory();
				for (int i = 0; i < slotStatus.length; i++)
				{
					CategorySlot subCategory = categorySlot.getSubcategory(i);
					String content = rtvSlotDate(subCategory.getCategory(), subCategory.getContent(), String.valueOf(i));
					if (content.length() > 0)
					{
						String ul = EqDesktopToolBox.formatUL(id + "STAT_ul_" + i, content);
						String li = EqDesktopToolBox.formatLI(id + "STAT_li_" + i, EqDesktopToolBox.formatSpan(slotStatus[i],
										"menuAndOptionsText")
										+ ul);
						strHTML.append(li);
					}
				}
			}
		}
		catch (Exception e)
		{
			EquationCommonContext.getContext().getLOG().error(e);
			return Toolbox.getExceptionMessage(e);
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(statement);
		}

		// return the HTML string
		return strHTML.toString();
	}

	/**
	 * Process the result set
	 * 
	 * @param eqProperties
	 *            - properties
	 * @param equationUser
	 *            - Equation User
	 * @param slotStatus
	 *            - status slot
	 * @param slotStatusContent
	 *            - status slot content
	 * @param resultSet
	 *            - result set
	 * 
	 * @return true if it contains data
	 * 
	 * @throws Exception
	 */
	public boolean processResultSet(Properties eqProperties, EquationUser equationUser, CategorySlot categorySlot,
					ResultSet resultSet) throws Exception
	{
		// Get date instances
		GregorianCalendar dateInstance = new GregorianCalendar();
		GregorianCalendar nowInstance = new GregorianCalendar();
		int idx = 0;

		// Loop all the returned details
		boolean empty = true;
		while (resultSet.next())
		{
			String optionId = resultSet.getString(1).trim();
			String sessionId = resultSet.getString(2).trim();
			String transactionId = resultSet.getString(3).trim();
			String status = resultSet.getString(4).trim();
			String date = Toolbox.cvtDbDateToStr(resultSet.getInt(5));
			String time = String.valueOf(resultSet.getInt(6));
			String origUser = resultSet.getString(7).trim();
			String superUser = resultSet.getString(8).trim();
			String jobName = resultSet.getString(9).trim();
			String jobNumber = String.valueOf(resultSet.getInt(10));
			String scrnNo = String.valueOf(resultSet.getInt(11));
			String optionTitle = resultSet.getString(12).trim();
			String origFullUser = resultSet.getString(13).trim();
			String screenSetId = String.valueOf(resultSet.getInt(14));
			String authLevel = String.valueOf(resultSet.getString(15));
			String reason = resultSet.getString(16).trim();
			String offline = resultSet.getString(17).trim();

			// Check if this is waiting for LRP referral, if it does, this do not display in the workload list
			if (EquationCommonContext.getContext().isLRPProcessing() && !userId.equals(origUser)
							&& !status.equals(WERecordDataModel.STAT_TMPLT))
			{
				IWE2RecordDao dao = new DaoFactory().getWE2Dao(equationUser.getSession(), new WE2RecordDataModel(sessionId,
								origFullUser, optionId, transactionId));
				WE2RecordDataModel we2record = dao.getWE2Record();
				if (we2record != null)
				{
					continue;
				}
			}

			idx++;
			String htmlStr = formatToHTML(eqProperties, equationUser, optionId, optionTitle, sessionId, transactionId, status,
							authLevel, date, time, origUser, origFullUser, superUser, jobName, jobNumber, screenSetId, scrnNo,
							reason, offline, idx);

			// retrieve the main category
			int slotCategory = getStatusSlot(origUser, superUser, status);

			// retrieve the sub-category (date order)
			dateInstance.set(1900 + Integer.parseInt(date.substring(0, 3)), Integer.parseInt(date.substring(3, 5)) - 1, Integer
							.parseInt(date.substring(5, 7)));
			int slotDate = EqDesktopToolBox.getSlot(nowInstance, dateInstance);

			// add to the list
			if (!status.equals(WERecordDataModel.MAKER_CHECKER_STAT_COMPL))
			{
				categorySlot.addContent(slotCategory, slotDate, htmlStr);
			}

			empty = false;
		}

		return empty;
	}

	/**
	 * Return the HTML equivalent of a spooled file entry
	 * 
	 * Setup the text
	 * 
	 * @param eqProperties
	 *            - Properties
	 * @param equationUser
	 *            - Equation User
	 * @param optionId
	 *            - option Id
	 * @param optionTitle
	 *            - option Title
	 * @param sessionId
	 *            - session Id
	 * @param transactionId
	 *            - transaction Id
	 * @param status
	 *            - status
	 * @param date
	 *            - date
	 * @param time
	 *            - time
	 * @param origUser
	 *            - originating user
	 * @param superUser
	 *            - supervisor user
	 * @param jobName
	 *            - job name
	 * @param jobNumber
	 *            - job number
	 * @param scrnNo
	 *            - screen number
	 * 
	 * @return the equivalent HTML
	 * 
	 * @throws Exception
	 */
	private String formatToHTML(Properties eqProperties, EquationUser equationUser, String optionId, String optionTitle,
					String sessionId, String transactionId, String status, String authLevel, String date, String time,
					String origUser, String origFullUser, String superUser, String jobName, String jobNumber, String screenSetId,
					String scrnNo, String reason, String offline, int index) throws Exception
	{
		String onClickAction = setupOnClickAction(optionId, sessionId, transactionId, status, authLevel, origUser, origFullUser,
						superUser, screenSetId, scrnNo, equationUser, jobName);
		String hoverText = setupHoverText(optionId, sessionId, transactionId, origUser, jobName, jobNumber, equationUser
						.isLanguageRTL());
		String text = setupText(eqProperties, equationUser, optionId, optionTitle, sessionId, transactionId, status, authLevel,
						date, time, origUser, superUser, jobName, jobNumber, reason);

		String key = "event,this" + ",'" + optionId + "','" + sessionId + "','" + transactionId + "','" + origFullUser + "','"
						+ status + "'";
		String contextMenu = " oncontextmenu=\"return(session_Popoup(" + key + "))\" " + "id=\"workloadItem_\" + index + ";

		String anchor = EqDesktopToolBox.formatIntoAnchorHTML(EqDesktopToolBox.formatSpan(text, ""), hoverText, onClickAction, "",
						contextMenu);
		String li = EqDesktopToolBox.formatLI(optionId + sessionId + transactionId + origUser + jobName + jobNumber, anchor);

		return li;
	}

	/**
	 * Return the on click action for this transaction
	 * 
	 * @param optionId
	 *            - optionId
	 * @param sessionId
	 *            - session Id
	 * @param transactionId
	 *            - transaction Id
	 * @param origUser
	 *            - originating user
	 * @param scrnNo
	 *            - screen number
	 * 
	 * @return the OnClickAction()
	 */
	public String setupOnClickAction(String optionId, String sessionId, String transactionId, String status, String authLevel,
					String origUser, String origFullUser, String superUser, String screenSetId, String scrnNo,
					EquationUser equationUser, String jobName)
	{
		// Non-equation desktop function
		if (sessionId.equals(""))
		{
			return "javascript:routeToOption2('*D',unitId,'AU','');";
		}

		// Non-equation desktop equation with maker-checker processing
		// Submitted for checking
		else if (status.equals(WERecordDataModel.MAKER_CHECKER_STAT_AWAIT) && userId.equals(origUser) && !userId.equals(superUser)
						&& !superUser.equals("") && !jobName.equalsIgnoreCase(""))
		{
			return "javascript:routeToOption2('*D',unitId,'SBL','');";
		}
		// Awaiting checking
		else if (status.equals(WERecordDataModel.MAKER_CHECKER_STAT_AWAIT) && !jobName.equalsIgnoreCase(""))
		{
			return "javascript:routeToOption2('*D',unitId,'AWL','');";
		}
		// Approved by checker
		else if (status.equals(WERecordDataModel.MAKER_CHECKER_STAT_APPRVD) && !jobName.equalsIgnoreCase(""))
		{
			return "javascript:routeToOption2('*D',unitId,'ARL','');";
		}
		// Rejected by checker
		else if (status.equals(WERecordDataModel.MAKER_CHECKER_STAT_REJCTD) && !jobName.equalsIgnoreCase(""))
		{
			return "javascript:routeToOption2('*D',unitId,'RJL','');";
		}

		// Non-equation desktop function
		else if (!jobName.equalsIgnoreCase(""))
		{
			return "javascript:routeToOption2('*D',unitId,'AU','');";
		}

		// Template
		else if (status.equals(WERecordDataModel.STAT_TMPLT))
		{
			return "javascript:routeToSessionRestore2('" + optionId + "','" + sessionId + "','" + transactionId + "','"
							+ origFullUser + "')";
		}

		// Referred by another user
		else if (status.equals(WERecordDataModel.STAT_REFUSR))
		{
			return "javascript:routeToSessionRestore2('" + optionId + "','" + sessionId + "','" + transactionId + "','"
							+ origFullUser + "')";
		}

		// Desktop function - referred to another user
		else if (userId.equals(origUser) && !userId.equals(superUser) && !superUser.equals(""))
		{
			return "javascript:routeToOffOverride2('" + optionId + "','" + sessionId + "','" + transactionId + "','" + origFullUser
							+ "','" + status + "','" + authLevel + "'," + screenSetId + "," + scrnNo + ")";
		}

		// Desktop function - user's transaction
		else if (userId.equals(origUser))
		{
			return "javascript:routeToSessionRestore2('" + optionId + "','" + sessionId + "','" + transactionId + "','"
							+ origFullUser + "')";
		}

		// As supervisor
		else
		{
			return "javascript:routeToSupervisor2('" + optionId + "','" + sessionId + "','" + transactionId + "','" + origFullUser
							+ "'," + screenSetId + "," + scrnNo + ")";
		}
	}

	/**
	 * Return the hover text for this transaction
	 * 
	 * @param optionId
	 *            - optionId
	 * @param sessionId
	 *            - session Id
	 * @param transactionId
	 *            - transaction Id
	 * @param origUser
	 *            - originating user
	 * @param jobName
	 *            - job name
	 * @param jobNumber
	 *            - job number
	 * @param rtl
	 *            - right to left?
	 * 
	 * @return the hover text
	 */
	public String setupHoverText(String optionId, String sessionId, String transactionId, String origUser, String jobName,
					String jobNumber, boolean rtl)
	{
		StringBuilder hoverText = new StringBuilder();
		String delimeter = " ";

		// Non-equation desktop function
		if (sessionId.equals(""))
		{
			if (rtl)
			{
				hoverText.append(jobNumber + delimeter);
				hoverText.append(jobName + delimeter);
				hoverText.append(origUser + delimeter);
				hoverText.append(optionId + delimeter);
			}
			else
			{
				hoverText.append(optionId + delimeter);
				hoverText.append(origUser + delimeter);
				hoverText.append(jobName + delimeter);
				hoverText.append(jobNumber + delimeter);
			}
		}

		// Desktop function
		else
		{
			if (rtl)
			{
				hoverText.append(sessionId + delimeter);
				hoverText.append(origUser + delimeter);
				hoverText.append(optionId + delimeter);
				hoverText.append(transactionId + delimeter);
			}
			else
			{
				hoverText.append(transactionId + delimeter);
				hoverText.append(optionId + delimeter);
				hoverText.append(origUser + delimeter);
				hoverText.append(sessionId + delimeter);
			}
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
	 * @param optionId
	 *            - option Id
	 * @param optionTitle
	 *            - option Title
	 * @param sessionId
	 *            - session Id
	 * @param transactionId
	 *            - transaction Id
	 * @param status
	 *            - status
	 * @param date
	 *            - date
	 * @param time
	 *            - time
	 * @param origUser
	 *            - originating user
	 * @param superUser
	 *            - supervisor user
	 * @param jobName
	 *            - job name
	 * @param jobNumber
	 *            - job number
	 * 
	 * @return the text
	 */
	public String setupText(Properties eqProperties, EquationUser equationUser, String optionId, String optionTitle,
					String sessionId, String transactionId, String status, String authLevel, String date, String time,
					String origUser, String superUser, String jobName, String jobNumber, String reason)
	{
		StringBuilder text = new StringBuilder();
		String delimiter = " ";
		String sdate = Toolbox.formatDate(date);
		String stime = Toolbox.formatTime(time, 6);

		// If this is the user's transaction?
		if (userId.equals(origUser))
		{
			String userText = "";
			String statusText = "";

			// referred to another user?
			if (!superUser.equals(userId) && !superUser.equals(""))
			{
				String superUserTemp = EquationFunctionContext.getContext().getDisplayUser(equationUser.getEquationUnit(),
								superUser);
				userText = superUserTemp + delimiter;
				if (status.equals(WERecordDataModel.STAT_AUTH) && authLevel.equals(WERecordDataModel.LVL_ALL))
				{
					statusText = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBLAUTHD");
				}
				else if (status.equals(WERecordDataModel.STAT_AUTH))
				{
					statusText = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000094");
				}
				else if (status.equals(WERecordDataModel.STAT_AVAIL))
				{
					statusText = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBLPEND");
				}
				else if (status.equals(WERecordDataModel.STAT_CHECK))
				{
					statusText = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBLRVWNG");
				}
				else if (status.equals(WERecordDataModel.STAT_DECL))
				{
					statusText = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBLDECLD");
					statusText += ": " + reason;
				}
				else if (status.equals(WERecordDataModel.MAKER_CHECKER_STAT_REJCTD))
				{
					statusText += reason;
				}
				statusText = "(" + statusText + ")" + delimiter;
			}
			else if (status.equals(WERecordDataModel.STAT_REFUSR))
			{
				statusText = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBLREF");
				statusText = "(" + statusText + ")" + delimiter;
			}

			if (equationUser.isLanguageRTL())
			{
				text.append(statusText + delimiter);
				text.append(transactionId + delimiter);
				text.append(optionTitle + delimiter);
				text.append(optionId + delimiter);
				text.append(userText);
				text.append(stime + delimiter);
				text.append(sdate + delimiter);
			}
			else
			{
				text.append(sdate + delimiter);
				text.append(stime + delimiter);
				text.append(userText);
				text.append(optionId + delimiter);
				text.append(optionTitle + delimiter);
				text.append(transactionId + delimiter);
				text.append(statusText + delimiter);
			}
		}

		// Record from another user
		else
		{
			String otherUser = EquationFunctionContext.getContext().getDisplayUser(equationUser.getEquationUnit(), origUser);
			if (equationUser.isLanguageRTL())
			{
				text.append(transactionId + delimiter);
				text.append(optionTitle + delimiter);
				text.append(optionId + delimiter);
				text.append(otherUser + delimiter);
				text.append(stime + delimiter);
				text.append(sdate + delimiter);
			}
			else
			{
				text.append(sdate + delimiter);
				text.append(stime + delimiter);
				text.append(otherUser + delimiter);
				text.append(optionId + delimiter);
				text.append(optionTitle + delimiter);
				text.append(transactionId + delimiter);
			}
		}

		// return the text
		return text.toString();
	}

	/**
	 * Return the slot status name. This should matched with getStatusSlot()
	 * 
	 * @param eqProperties
	 *            - Properties
	 * @param equationUser
	 *            - Equation User
	 * 
	 * @return the slot name
	 */
	public String[] getSlotStatusName(Properties eqProperties, EquationUser equationUser)
	{
		String[] strSlotName = new String[16];

		// Create arrays
		for (int i = 0; i < strSlotName.length; i++)
		{
			strSlotName[i] = "";
		}

		// Template transactions
		strSlotName[TRAN_TMPLT] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000081");

		// Saved transactions
		strSlotName[TRAN_SAVED] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000080");

		// In progress
		strSlotName[TRAN_INPRG] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000083");

		// Referred transactions
		strSlotName[TRAN_REFER] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000082");

		// Request authorisation
		strSlotName[TRAN_REQST] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000084");

		// Unknown status
		strSlotName[TRAN_OTHER] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000085");

		// Data entry task
		strSlotName[LRP_ENTRY] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000371");

		// Authorisation task
		strSlotName[LRP_AUTH] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000372");

		// ESF task
		strSlotName[LRP_ESF] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000373");

		// Data entry task (claimed)
		strSlotName[LRP_ENTRY_CLAIMED] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000371") + " "
						+ EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000374");

		// Authorisation task (claimed)
		strSlotName[LRP_AUTH_CLAIMED] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000372") + " "
						+ EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000374");

		// ESF task (claimed)
		strSlotName[LRP_ESF_CLAIMED] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000373") + " "
						+ EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000374");

		// For Maker-Checker

		// transactions submitted for checking
		strSlotName[MAKER_CHECKER_SUBMITTED] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000386");

		// transactions awaiting checking
		strSlotName[MAKER_CHECKER_AWAITING] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000387");

		// transactions rejected by checker
		strSlotName[MAKER_CHECKER_REJECTED] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000388");

		// transactions approved by checker
		strSlotName[MAKER_CHECKER_APPROVED] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000389");

		return strSlotName;
	}

	/**
	 * Return the slot status index within the slot. This should matched with getSlotStatusName()
	 * 
	 * @param origUser
	 *            - user who created the transaction
	 * @param status
	 *            - transaction status
	 * 
	 * @return the slot number
	 */
	public int getStatusSlot(String origUser, String superUser, String status)
	{
		int slot = TRAN_OTHER; // Unknown status

		// the current user is the one who created the transactions
		if (userId.equals(origUser))
		{
			if (status.equals(WERecordDataModel.STAT_WIP))
			{
				slot = TRAN_SAVED; // Saved
			}
			else if (status.equals(WERecordDataModel.STAT_TMPLT))
			{
				slot = TRAN_TMPLT; // Template
			}
			else if (status.equals(WERecordDataModel.STAT_AVAIL))
			{
				slot = TRAN_REFER; // Referred
			}
			else if (status.equals(WERecordDataModel.STAT_AUTH))
			{
				slot = TRAN_REFER; // Referred
			}
			else if (status.equals(WERecordDataModel.STAT_DECL))
			{
				slot = TRAN_REFER; // Referred
			}
			else if (status.equals(WERecordDataModel.STAT_REFUSR))
			{
				slot = TRAN_REFER; // Referred
			}
			else if (status.equals(WERecordDataModel.STAT_CHECK) && superUser.equals(""))
			{
				slot = TRAN_INPRG; // in progress (current user restore his own saved transaction)
			}
			else if (status.equals(WERecordDataModel.STAT_CHECK) && superUser.equals(userId))
			{
				slot = TRAN_INPRG; // in progress
			}
			else if (status.equals(WERecordDataModel.STAT_CHECK))
			{
				slot = TRAN_REFER; // Referred (supervisor currently reviewing the transaction)
			}
			// for maker-checker transactions
			else if (status.equals(WERecordDataModel.MAKER_CHECKER_STAT_AWAIT) && !superUser.equals(userId))
			{
				slot = MAKER_CHECKER_SUBMITTED; // Submitted by maker
			}
			else if (status.equals(WERecordDataModel.MAKER_CHECKER_STAT_AWAIT) && superUser.equals(userId))
			{
				slot = MAKER_CHECKER_AWAITING; // Awaiting checking
			}
			else if (status.equals(WERecordDataModel.MAKER_CHECKER_STAT_REJCTD))
			{
				slot = MAKER_CHECKER_REJECTED; // Rejected by checker
			}
			else if (status.equals(WERecordDataModel.MAKER_CHECKER_STAT_APPRVD))
			{
				slot = MAKER_CHECKER_APPROVED; // Approved by checker
			}
		}

		// transaction created by other user
		else
		{
			if (status.equals(WERecordDataModel.STAT_TMPLT))
			{
				slot = TRAN_TMPLT; // Template
			}
			else if (status.equals(WERecordDataModel.MAKER_CHECKER_STAT_AWAIT) && superUser.equals(userId))
			{
				slot = MAKER_CHECKER_AWAITING; // Awaiting checking
			}
			else
			{
				slot = TRAN_REQST; // submitted by other user
			}
		}

		// return the slot
		return slot;
	}

	/**
	 * Combine all the items for the date
	 * 
	 * @param strSlot
	 *            - list of dates
	 * @param strSlotContent
	 *            - list of contents
	 * @param status
	 *            - status
	 * 
	 * @return the consolidated item
	 */
	public String rtvSlotDate(String[] strSlot, StringBuilder[] strSlotContent, String status)
	{
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < strSlotContent.length; i++)
		{
			if (strSlotContent[i].length() > 0)
			{
				String ul = EqDesktopToolBox.formatUL(id + "DATE_ul_" + i + "_" + status, strSlotContent[i].toString());
				String li = EqDesktopToolBox.formatLI(id + "DATE_li_" + i + "_" + status, EqDesktopToolBox.formatSpan(strSlot[i],
								"menuAndOptionsText")
								+ ul);
				buffer.append(li);
			}
		}
		return buffer.toString().trim();

	}

}