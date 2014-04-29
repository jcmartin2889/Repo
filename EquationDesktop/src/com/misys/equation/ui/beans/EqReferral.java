package com.misys.equation.ui.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.misys.equation.bankfusion.lrp.bean.TaskQueryDetail;
import com.misys.equation.bankfusion.lrp.bean.TaskQueryDetails;
import com.misys.equation.bankfusion.lrp.engine.ITaskEngine;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IWE2RecordDao;
import com.misys.equation.common.dao.IWERecordDao;
import com.misys.equation.common.dao.beans.WE2RecordDataModel;
import com.misys.equation.common.dao.beans.WERecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.ui.context.EquationDesktopContext;

public class EqReferral
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqReferral.java 17625 2013-11-27 02:55:12Z williae1 $";

	// logger
	private static final EquationLogger LOG = new EquationLogger(EqReferral.class);

	// list of referred items
	private List<ReferralItem> referredItemsList = new ArrayList<ReferralItem>();

	// dao factory
	private final DaoFactory daoFactory = new DaoFactory();

	/**
	 * Construct an empty referral
	 */
	public EqReferral()
	{
	}

	/**
	 * Construct a referral for the user
	 * 
	 * @param user
	 *            - the user
	 * @throws EQException
	 */
	public EqReferral(EquationUser user) throws EQException
	{
		retrieveReferralAlerts(user, true);
		retrieveBreakMessages(user);

		// LRP ESF referrals are not currently supported:
		// if (EquationCommonContext.getContext().isLRPProcessing())
		// {
		// // retrieve LRP task referrals for the user
		// retrieveLrpTaskReferralAlerts(user);
		//
		// // sort referrals by referral date/time
		// Collections.sort(referredItemsList, new ReferralComparator());
		// }
	}
	/**
	 * Construct a referral just to notify the client of change of menu status
	 * 
	 * @param menuStatus
	 * @throws EQException
	 */
	public EqReferral(String menuStatus) throws EQException
	{
		// add the referral item to list
		ReferralItem referralItem = new ReferralItem();
		referralItem.setReferredOption("");
		referralItem.setReferredUser("");
		referralItem.setDesktopSession("");
		referralItem.setDesktopTranId("");
		referralItem.setDesktopUser("");
		referralItem.setStatus("");
		referralItem.setSupervisor("");
		referralItem.setScreenSetId("");
		referralItem.setPrimeScrn("");
		referralItem.setAuthLevel("");
		referralItem.setReason("");
		referralItem.setOffline("");
		referralItem.setReferredDate("");
		referralItem.setReferredTime("");
		referralItem.setFormattedDate("");
		referralItem.setFormattedTime("");
		referralItem.setOptionTitle("");
		referralItem.setJobDescription(" ");
		referralItem.setSessionUser("");
		referralItem.setDisplayUser("");
		referralItem.setTaskType(menuStatus);
		referredItemsList.add(referralItem);
	}

	/**
	 * Retrieve referral alerts from WEPF for the user
	 * 
	 * @param user
	 *            - the user
	 * @throws EQException
	 */
	private void retrieveReferralAlerts(EquationUser user, boolean includeServiceReferrals) throws EQException
	{
		EquationStandardSession session = null;
		PreparedStatement sqlStatement = null;
		ResultSet rs = null;

		try
		{
			// get a session
			session = user.getEquationUnit().getEquationSessionPool().getEquationStandardSession();
			Connection connection = session.getConnection();

			// retrieve the authorising user
			String authorisingUser = Toolbox.removeSQLChars(Toolbox.trim(user.getUserId(), 4).trim());

			// retrieve last referral alert date and time
			String dateTime = EquationDesktopContext.getContext().getWepfEsfReferralTimestamp(
							user.getSession().getSessionIdentifier());
			String referralDate = retrieveReferralDate(dateTime);
			String referralTime = retrieveReferralTime(dateTime);

			// not set, then set to the earliest time
			if (dateTime == null)
			{
				dateTime = "0000000000000";
			}

			// selection to delete session
			StringBuffer alertSessions = new StringBuffer();
			StringBuffer deleteSessions = new StringBuffer();
			StringBuffer deleteWEXSessions = new StringBuffer();
			StringBuffer deleteWEYSessions = new StringBuffer();

			// Note: If WPS referrals are re-introduced, this SQL will need to be
			// updated to exclude records that exist on the WE2PF
			String sqlString = "SELECT WEUID,WEOID, WEUID2, WESID, WETID, WEJBD, WEJBN, WESCRN, WEAUID, WEASTS, WEAUTH, WESSET, WERTXT, WEOFFL, WEDTE, WETIM, WEONM FROM WEPF ";
			StringBuffer criteria = null;
			if (includeServiceReferrals)
			{
				// retrieve all alert for this user

				criteria = new StringBuffer();
				criteria.append(" WHERE (WEAUID = ? AND (WEALRT<>'Y' OR (WEDTE >? OR (WEDTE>=? AND WETIM>?))) "
								+ "AND (WEASTS = '5' or WEASTS = '7' or WEASTS = '8')) "); // as supervisor
				criteria.append(" OR (WEAUID = ? AND (WEDTE >? OR (WEDTE>=? AND WETIM>?)) AND (WEASTS = '6' OR WEASTS = 'S')) "); // as
				// supervisor
				// (reviewing)
				criteria
								.append(" OR (WEUID = ? AND (WEALRT<>'Y' OR (WEDTE >? OR (WEDTE>=? AND WETIM>?)))"
												+ " AND (WEASTS = '1' or WEASTS = '4' or WEASTS = 'P' or WEASTS = 'R' or WEASTS = 'S' or WEASTS = 'C')) "); // response
				// from
				// other
				// supervisor
				criteria.append(" OR (WEUID = ? AND (WEALRT<>'Y' OR (WEDTE >? OR (WEDTE>=? AND WETIM>?))) AND WEASTS = '9') "); // referred
				// to me
			}
			else
			{
				// retrieve all alert for this user but not services - WEJBD job description should not be blanks

				criteria = new StringBuffer();
				criteria.append(" WHERE (WEAUID = ? AND (WEALRT<>'Y' OR (WEDTE >? OR (WEDTE>=? AND WETIM>?))) "
								+ "AND (WEASTS = '5' or WEASTS = '7' or WEASTS = '8') AND TRIM(WEJBD) <> '') "); // as supervisor
				criteria
								.append(" OR (WEAUID = ? AND (WEDTE >? OR (WEDTE>=? AND WETIM>?)) AND (WEASTS = '6' OR WEASTS = 'S') AND TRIM(WEJBD) <> '') "); // as
				// supervisor
				// (reviewing)
				criteria
								.append(" OR (WEUID = ? AND (WEALRT<>'Y' OR (WEDTE >? OR (WEDTE>=? AND WETIM>?)))"
												+ " AND (WEASTS = '1' or WEASTS = '4' or WEASTS = 'P' or WEASTS = 'R' or WEASTS = 'S' or WEASTS = 'C') AND TRIM(WEJBD) <> '') "); // response
				// from
				// other
				// supervisor
				criteria
								.append(" OR (WEUID = ? AND (WEALRT<>'Y' OR (WEDTE >? OR (WEDTE>=? AND WETIM>?))) AND WEASTS = '9' AND TRIM(WEJBD) <> '') "); // referred
				// to me
			}

			String orderByClause = " ORDER BY WEDTE, WETIM";

			sqlStatement = connection.prepareStatement(sqlString + criteria + orderByClause, ResultSet.TYPE_FORWARD_ONLY,
							ResultSet.CONCUR_READ_ONLY);
			sqlStatement.setString(1, authorisingUser);
			sqlStatement.setString(2, referralDate);
			sqlStatement.setString(3, referralDate);
			sqlStatement.setString(4, referralTime);
			sqlStatement.setString(5, authorisingUser);
			sqlStatement.setString(6, referralDate);
			sqlStatement.setString(7, referralDate);
			sqlStatement.setString(8, referralTime);
			sqlStatement.setString(9, authorisingUser);
			sqlStatement.setString(10, referralDate);
			sqlStatement.setString(11, referralDate);
			sqlStatement.setString(12, referralTime);
			sqlStatement.setString(13, authorisingUser);
			sqlStatement.setString(14, referralDate);
			sqlStatement.setString(15, referralDate);
			sqlStatement.setString(16, referralTime);

			rs = sqlStatement.executeQuery();
			ReferralItem referralItem = null;
			String latestReferralDateTime = null;

			while (rs.next())
			{
				String weUserId = rs.getString("WEUID");
				String weOptionId = rs.getString("WEOID");
				String weSessionId = rs.getString("WESID");
				String weTranId = rs.getString("WETID");
				String fullUserId = rs.getString("WEUID2");

				// if user id is not found, ignore this item
				if (weUserId == null)
				{
					continue;
				}

				// add the referral item to list
				referralItem = new ReferralItem();
				referralItem.setReferredOption(weOptionId);
				referralItem.setReferredUser(weUserId);
				referralItem.setDesktopSession(weSessionId);
				referralItem.setDesktopTranId(weTranId);
				referralItem.setDesktopUser(rs.getString("WEUID2"));
				referralItem.setStatus(rs.getString("WEASTS"));
				referralItem.setSupervisor(rs.getString("WEAUID"));
				referralItem.setScreenSetId(rs.getString("WESSET"));
				referralItem.setPrimeScrn(rs.getString("WESCRN"));
				referralItem.setAuthLevel(rs.getString("WEAUTH"));
				referralItem.setReason(Toolbox.rplSlashWith2Slash(Toolbox.rplQuote(Toolbox
								.rplSingleQuoteWithSlashSingleQuote(Toolbox.convertAS400TextIntoCCSID(rs.getBytes("WERTXT"), rs
												.getBytes("WERTXT").length, session.getCcsid())))));
				referralItem.setOffline(rs.getString("WEOFFL"));
				referralItem.setReferredDate(rs.getString("WEDTE"));
				referralItem.setReferredTime(rs.getString("WETIM"));
				referralItem.setFormattedDate(Toolbox.formatDate(Toolbox.cvtDbDateToStr(rs.getInt("WEDTE"))));
				referralItem.setFormattedTime(Toolbox.formatTime(rs.getString("WETIM"), 6));
				referralItem.setOptionTitle(rs.getString("WEONM"));
				referralItem.setJobDescription(rs.getString("WEJBD"));
				referralItem.setSessionUser(authorisingUser);
				// referral items NOT awaiting checking are always added
				// and an awaiting checking txn will only be added provided the current user is the authorising user
				if (!referralItem.getStatus().equalsIgnoreCase(WERecordDataModel.MAKER_CHECKER_STAT_AWAIT)
								|| (referralItem.getStatus().equalsIgnoreCase(WERecordDataModel.MAKER_CHECKER_STAT_AWAIT) && authorisingUser
												.equalsIgnoreCase(referralItem.getSupervisor())))
				{
					// The main user displayed varies depending on the referral status:
					String status = referralItem.getStatus();
					String displayUser = "";
					if (WERecordDataModel.STAT_AUTH.equals(status) || WERecordDataModel.STAT_DECL.equals(status)
									|| WERecordDataModel.STAT_REFUSR.equals(status)
									|| WERecordDataModel.MAKER_CHECKER_STAT_APPRVD.equals(status)
									|| WERecordDataModel.MAKER_CHECKER_STAT_REJCTD.equals(status)
									|| WERecordDataModel.MAKER_CHECKER_STAT_COMPL.equals(status))
					{
						displayUser = referralItem.getSupervisor();
					}
					else if (WERecordDataModel.STAT_ABORT.equals(status) || WERecordDataModel.STAT_TMOUT.equals(status)
									|| WERecordDataModel.STAT_AVAIL.equals(status) || WERecordDataModel.STAT_CHECK.equals(status)
									|| WERecordDataModel.MAKER_CHECKER_STAT_AWAIT.equals(status))
					{
						displayUser = referralItem.getReferredUser();
					}

					displayUser = EquationFunctionContext.getContext().getDisplayUser(user.getEquationUnit(), displayUser);
					referralItem.setDisplayUser(displayUser);
					referredItemsList.add(referralItem);
				}

				// keep track of the last date/time
				latestReferralDateTime = referralItem.getReferredDate()
								+ Toolbox.padAtFront(referralItem.getReferredTime(), "0", 6);

				String weJobName = rs.getString("WEJBD");
				String weJobNum = rs.getString("WEJBN");

				// now either mark the record as already been alerted or delete it
				if (referralItem.getStatus().equals(WERecordDataModel.STAT_ABORT)
								|| referralItem.getStatus().equals(WERecordDataModel.STAT_TMOUT)
								|| referralItem.getStatus().equals(WERecordDataModel.MAKER_CHECKER_STAT_COMPL))
				{
					append(deleteSessions, rtvWEKey(referralItem.getReferredUser(), weJobName, weJobNum, referralItem
									.getReferredOption(), referralItem.getDesktopUser(), referralItem.getDesktopSession(),
									referralItem.getDesktopTranId()));
					append(deleteWEXSessions, rtvWEXKey(referralItem.getDesktopUser(), referralItem.getReferredOption(),
									referralItem.getDesktopSession(), referralItem.getDesktopTranId()));
					append(deleteWEYSessions, rtvWEYKey(referralItem.getDesktopUser(), referralItem.getReferredOption(),
									referralItem.getDesktopSession(), referralItem.getDesktopTranId()));
				}
				else
				{
					append(alertSessions, rtvWEKey(referralItem.getReferredUser(), weJobName, weJobNum, referralItem
									.getReferredOption(), referralItem.getDesktopUser(), referralItem.getDesktopSession(),
									referralItem.getDesktopTranId()));
				}

			}

			updateRecords(session, alertSessions, deleteSessions, deleteWEXSessions, deleteWEYSessions);

			if (latestReferralDateTime != null && latestReferralDateTime.compareTo(dateTime) > 0)
			{
				EquationDesktopContext.getContext().setWepfEsfReferralTimestamp(user.getSession().getSessionIdentifier(),
								latestReferralDateTime);
			}

		}

		catch (SQLException e)
		{
			LOG.error("EqReferral error retrieving WEPF referral alerts for user " + user.getUserName() + ": ", e);
			throw new EQException("EqReferral: Constructor Failed", e);
		}
		finally
		{
			SQLToolbox.close(rs);
			SQLToolbox.close(sqlStatement);

			try
			{
				if (session != null)
				{
					user.getEquationUnit().getEquationSessionPool().returnEquationStandardSession(session);
				}
			}
			catch (EQException e)
			{
				LOG.error(e);
			}
		}

	}
	/**
	 * Retrieve break messages stored in the user session
	 * 
	 * @param user
	 *            - the Equation user
	 * @throws EQException
	 */
	private void retrieveBreakMessages(EquationUser user) throws EQException
	{
		ReferralItem referralItem = null;
		ArrayList<String> breakMessages = user.getSession().getBreakMessages();
		if (breakMessages != null)
		{
			for (int i = 0; i < user.getSession().getBreakMessages().size(); i++)
			{
				String text = user.getSession().getBreakMessages().get(i);
				int pos = text.indexOf(Toolbox.COLON_DELIMITER);
				int pos2 = text.indexOf(Toolbox.COLON_DELIMITER, pos + 1);
				int pos3 = text.indexOf(Toolbox.COLON_DELIMITER, pos2 + 1);
				String severity = text.substring(0, pos).trim();
				// do not trim here otherwise js will show null when there is no sender
				String sender = text.substring(pos + 3, pos2);
				String dateTime = text.substring(pos2 + 3, pos3).trim();
				String message = text.substring(pos3 + 3).trim();

				// add the referral item to list
				referralItem = new ReferralItem();
				referralItem.setReferredOption("");
				referralItem.setReferredUser(user.getUserId());
				referralItem.setDesktopSession("");
				referralItem.setDesktopTranId("");
				referralItem.setDesktopUser("");
				referralItem.setStatus(severity);
				referralItem.setSupervisor("");
				referralItem.setScreenSetId("");
				referralItem.setPrimeScrn("");
				referralItem.setAuthLevel("");
				referralItem.setReason(message);
				referralItem.setOffline("");
				referralItem.setReferredDate("");
				referralItem.setReferredTime("");
				referralItem.setFormattedDate(dateTime);
				referralItem.setFormattedTime("");
				referralItem.setOptionTitle("");
				referralItem.setJobDescription(" ");
				referralItem.setSessionUser("");
				referralItem.setDisplayUser(sender);
				referralItem.setTaskType("BREAKMSG");
				referredItemsList.add(referralItem);
			}
			breakMessages.clear();
		}

	}

	/**
	 * Retrieve LRP task referrals for the user
	 * 
	 * @param user
	 *            - the user
	 */
	private void retrieveLrpTaskReferralAlerts(EquationUser user)
	{
		EquationStandardSession session = user.getSession();
		String userId = user.getUserId();
		try
		{
			// Retrieve LRP alert tasks
			ITaskEngine taskEngine = EquationFunctionContext.getContext().getTaskEngine(user.getSession().getSessionIdentifier());
			TaskQueryDetails taskQueryDetails = taskEngine.getAlertTaskLists();

			// retrieve last referral alert date and time
			String dateTime = EquationDesktopContext.getContext().getLrpTaskTimestamp(user.getSession().getSessionIdentifier());

			String latestReferralDateTime = null;
			ReferralItem referralItem = null;
			for (TaskQueryDetail taskQueryDetail : taskQueryDetails.getTaskDetails())
			{
				// ignore this with date time is equal of less than to the last referral alert date and time
				if (dateTime != null)
				{
					String taskDateTime = taskQueryDetail.getCreateDate() + taskQueryDetail.getCreateTime();
					if (taskDateTime.compareTo(dateTime) <= 0)
					{
						continue;
					}
				}

				// make sure task is valid to be shown on the user
				if (!taskQueryDetail.isTaskValidForUser(user))
				{
					continue;
				}

				// if the referral item exists in WE2PF, ignore this item
				IWE2RecordDao we2Dao = daoFactory.getWE2Dao(session, new WE2RecordDataModel(taskQueryDetail.getTkiid()));
				if (we2Dao.checkIfThisWE2RecordIsInTheDB())
				{
					continue;
				}

				// keep track of the latest referred LRP task
				String referredItemCreateDateTime = taskQueryDetail.getCreateDate() + taskQueryDetail.getCreateTime();
				if (latestReferralDateTime == null || latestReferralDateTime.compareTo(referredItemCreateDateTime) < 0)
				{
					latestReferralDateTime = referredItemCreateDateTime;
				}

				// add referred item to the list
				referralItem = new ReferralItem();
				referralItem.setReferredOption(taskQueryDetail.getOptionId());
				referralItem.setTaskType(taskQueryDetail.getType());
				referralItem.setDesktopTranId(taskQueryDetail.getTkiid());
				referralItem.setReferredUser(taskQueryDetail.getOriginator().toUpperCase());
				referralItem.setReferredDate(taskQueryDetail.getCreateDate());
				referralItem.setReferredTime(taskQueryDetail.getCreateTime());
				referredItemsList.add(referralItem);
			}

			if (latestReferralDateTime != null)
			{
				EquationDesktopContext.getContext().setLrpTaskTimestamp(user.getSession().getSessionIdentifier(),
								latestReferralDateTime);
			}

		}
		catch (Exception e)
		{
			LOG.error("EqReferral error retrieving LRP task referral alerts for user " + user.getUserName() + ": ", e);
		}

	}

	/**
	 * Append a string to another string, appending an OR
	 * 
	 * @param str1
	 *            - the target string
	 * @param str2
	 *            - the string to be appended
	 */
	private void append(StringBuffer str1, StringBuffer str2)
	{
		if (str1.length() == 0)
		{
			str1.append(str2);
		}
		else
		{
			str1.append(" OR ");
			str1.append(str2);
		}
	}

	/**
	 * Generate the SQL statement to select the specified WE record
	 * 
	 * @param userId
	 *            - user id
	 * @param jobName
	 *            - job name
	 * @param jobNumber
	 *            - job number
	 * @param optionId
	 *            option id
	 * @param userId2
	 *            - user id (10 character)
	 * @param sessionId
	 *            - session id
	 * @param transactionId
	 *            - transaction id
	 * 
	 * @return the selection query
	 */
	private StringBuffer rtvWEKey(String userId, String jobName, String jobNumber, String optionId, String userId2,
					String sessionId, String transactionId)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("WEUID='" + userId + "'");
		buffer.append(" AND ");
		buffer.append("WEJBD='" + jobName + "'");
		buffer.append(" AND ");
		buffer.append("WEJBN=" + jobNumber);
		buffer.append(" AND ");
		buffer.append("WEOID='" + optionId + "'");
		buffer.append(" AND ");
		buffer.append("WEUID2='" + userId2 + "'");
		buffer.append(" AND ");
		buffer.append("WESID='" + sessionId + "'");
		buffer.append(" AND ");
		buffer.append("WETID='" + transactionId + "'");
		return buffer;
	}

	/**
	 * Generate the SQL statement to select the specified WEX record
	 * 
	 * @param userId
	 *            - user id
	 * @param optionId
	 *            option id
	 * @param sessionId
	 *            - session id
	 * @param transactionId
	 *            - transaction id
	 * 
	 * @return the selection query
	 */
	private StringBuffer rtvWEXKey(String userId, String optionId, String sessionId, String transactionId)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("WEXUID='" + userId + "'");
		buffer.append(" AND ");
		buffer.append("WEXOID='" + optionId + "'");
		buffer.append(" AND ");
		buffer.append("WEXSID='" + sessionId + "'");
		buffer.append(" AND ");
		buffer.append("WEXTID='" + transactionId + "'");
		return buffer;
	}

	/**
	 * Generate the SQL statement to select the specified WEY record
	 * 
	 * @param userId
	 *            - user id
	 * @param optionId
	 *            option id
	 * @param sessionId
	 *            - session id
	 * @param transactionId
	 *            - transaction id
	 * 
	 * @return the selection query
	 */
	private StringBuffer rtvWEYKey(String userId, String optionId, String sessionId, String transactionId)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("WEYUID='" + userId + "'");
		buffer.append(" AND ");
		buffer.append("WEYOID='" + optionId + "'");
		buffer.append(" AND ");
		buffer.append("WEYSID='" + sessionId + "'");
		buffer.append(" AND ");
		buffer.append("WEYTID='" + transactionId + "'");
		return buffer;
	}

	/**
	 * Update the records
	 * 
	 * @param connection
	 *            - the connection
	 * @param alertSessions
	 *            - the selection query for the records to be set to Y
	 * @param deleteSessions
	 *            - the selection query for the records to be deleted
	 * @param deleteWEXSessions
	 *            - - the selection query for the WEX records to be deleted
	 * @param deleteWEYSessions
	 *            - - the selection query for the WEY records to be deleted
	 */
	private void updateRecords(EquationStandardSession session, StringBuffer alertSessions, StringBuffer deleteSessions,
					StringBuffer deleteWEXSessions, StringBuffer deleteWEYSessions)
	{
		IWERecordDao weDao = null;

		try
		{
			WERecordDataModel weRecord = new WERecordDataModel();
			weDao = daoFactory.getWEDao(session, weRecord);

			// mark as alerted
			if (alertSessions.length() > 0)
			{
				weDao.updateAllRecords(alertSessions.toString());
			}

			// delete records
			if (deleteSessions.length() > 0)
			{
				weDao.deleteSessions(deleteWEXSessions.toString(), deleteSessions.toString(), deleteWEYSessions.toString());
			}

			// commit the changes
			if (alertSessions.length() > 0 || deleteSessions.length() > 0)
			{
				session.connectionCommit();
			}
		}
		catch (Exception exception)
		{
			try
			{
				session.connectionRollback();
			}
			catch (Exception eQException)
			{
				if (LOG.isErrorEnabled())
				{
					LOG.error("There was problem trying to updateRecords", eQException);
				}
			}
			if (LOG.isErrorEnabled())
			{
				LOG.error("There was problem trying to rollback the transaction", exception);
			}
		}

	}

	/**
	 * This Comparator class will compare the referral items by referral date and time
	 */
	private class ReferralComparator implements Comparator<ReferralItem>
	{

		public int compare(ReferralItem referredItem1, ReferralItem referredItem2)
		{
			String s1 = referredItem1.getReferredDate() + referredItem1.getReferredTime();
			String s2 = referredItem2.getReferredDate() + referredItem2.getReferredTime();

			return s1.compareTo(s2);
		}
	}

	/**
	 * Returns the referral date
	 * 
	 * @param dateTime
	 *            - the date and time of referral
	 * @return the referral date
	 */
	private String retrieveReferralDate(String dateTime)
	{
		String referralDate = null;

		if (dateTime == null)
		{
			referralDate = "-1";
		}
		else
		{
			try
			{
				referralDate = dateTime.substring(0, 7); // date part YYYMMDD
			}
			catch (IndexOutOfBoundsException e)
			{
				referralDate = "-1";
			}
		}

		return referralDate;
	}

	/**
	 * Returns the referral time
	 * 
	 * @param dateTime
	 *            - the date and time of referral
	 * @return the referral time
	 */
	private String retrieveReferralTime(String dateTime)
	{
		String referralTime = null;
		if (dateTime == null)
		{
			referralTime = "-1";
		}
		else
		{
			try
			{
				referralTime = dateTime.substring(7); // time part HHMMSS
			}
			catch (IndexOutOfBoundsException e)
			{
				referralTime = "-1";
			}
		}

		return referralTime;
	}

	/**
	 * Sets the list of referred items
	 * 
	 * @param referredItemsList
	 *            - the list of referred items
	 */
	public void setReferredItemsList(List<ReferralItem> referredItemsList)
	{
		this.referredItemsList = referredItemsList;
	}

	/**
	 * Returns the list of referred items
	 * 
	 * @return the list of referred items
	 */
	public List<ReferralItem> getReferredItemsList()
	{
		return referredItemsList;
	}

}