package com.misys.equation.common.dao;

import com.misys.equation.common.dao.beans.APVRecordDataModel;

/**
 * This is an interface which is going to expose all common Dao services. It is going to behave as a bridge between the Dao and the
 * client code.
 * 
 * @author deroset
 */
public interface IAPVRecordDao extends IDao
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
	public boolean checkIfThisAPVRecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisAPVRecordIsInTheDB();

	/**
	 * This method is going to return a <code>APVRecordDataModel</code> base on APV ID
	 * 
	 * @return a <code>APVRecordDataModel</code> base on [FILED ID]
	 */
	public APVRecordDataModel getAPVRecord();
}