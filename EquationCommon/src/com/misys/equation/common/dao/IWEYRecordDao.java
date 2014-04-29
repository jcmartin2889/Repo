package com.misys.equation.common.dao;

import com.misys.equation.common.dao.beans.WEYRecordDataModel;

/**
 * This is an interface which is going to expose all common Dao services. It is going to behave as a bridge between the Dao and the
 * client code.
 * 
 * @author deroset
 * 
 */

public interface IWEYRecordDao extends IDao
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
	public boolean checkIfThisWEYRecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisWEYRecordIsInTheDB();

	/**
	 * This method is going to return a <code>WEYRecordDataModel</code> base on SessionFID
	 * 
	 * @return a <code>WEYDataModel</code> base on [WEYPF]
	 */
	public WEYRecordDataModel getWEYRecord();

	/**
	 * This method deletes all the records which have the supplied session id, user id, transaction id and option id
	 * <p>
	 * 
	 * @param sessionId
	 *            - the session Id
	 * @param userId
	 *            - the user id
	 * @param transactionId
	 *            - the transaction id
	 * @param optionId
	 *            - the option id
	 */
	public void deleteRecordByTransaction(String sessionId, String userId, String transactionId, String optionId);

}
