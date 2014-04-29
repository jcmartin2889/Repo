package com.misys.equation.common.dao;

import com.misys.equation.common.dao.beans.GYWRecordDataModel;

/**
 * This is an interface which is going to expose all common Dao services. It is going to behave as a bridge between the Dao and the
 * client code.
 * 
 */

public interface IGYWRecordDao extends IDao
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
	public boolean checkIfThisGYWRecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGYWRecordIsInTheDB();

	/**
	 * This method is going to return a <code>WEYRecordDataModel</code> base on SessionFID
	 * 
	 * @return a <code>WEYDataModel</code> base on [WEYPF]
	 */
	public GYWRecordDataModel getGYWRecord();

}
