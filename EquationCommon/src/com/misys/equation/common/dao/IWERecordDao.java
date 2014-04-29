package com.misys.equation.common.dao;

import com.misys.equation.common.dao.beans.WERecordDataModel;

/**
 * This is an interface which is going to expose all common Dao services. It is going to behave as a bridge between the Dao and the
 * client code.
 * 
 * @author deroset
 * 
 */

public interface IWERecordDao extends IDao
{
	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @param sqlWhereStatement
	 *            - the WHERE clause of the SQL statement
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisWERecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisWERecordIsInTheDB();

	/**
	 * This method is going to return a <code>WERecordDataModel</code> base on WEFID
	 * 
	 * @return a <code>WERecordDataModel</code> base on [FILED ID]
	 */
	public WERecordDataModel getWERecord();

	/**
	 * This method will update all records base on alerted-sessions.
	 * 
	 * @param alertSessions
	 *            this is the alerted-sessions which are going to be used as filter.
	 */
	public void updateAllRecords(String alertSessions);

	/**
	 * This method will delete all records base on deleteWEXSessions and deleteSessions.
	 * 
	 * @param deleteWEXSessions
	 *            this is the deleteWEXSessions which are going to be used as filter.
	 * @param deleteSessions
	 *            this is the delete-sessions which are going to be used as filter.
	 * @param deleteWEYSessions
	 *            this is the deleteWEXSessions which are going to be used as filter.
	 * 
	 */
	public void deleteSessions(String deleteWEXSessions, String deleteSessions, String deleteWEYSessions);
}
