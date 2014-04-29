package com.misys.equation.common.dao;

import com.misys.equation.common.dao.beans.GAWRecordDataModel;

/**
 * This is an interface which is going to expose all common Dao services. It is going to behave as a bridge between the Dao and the
 * client code.
 * 
 * @author deroset
 */
public interface IGAWRecordDao extends IDao
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
	public boolean checkIfThisGAWRecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGAWRecordIsInTheDB();

	/**
	 * This method is going to return a <code>GAWRecordDataModel</code> base on GAW ID
	 * 
	 * @return a <code>GAWRecordDataModel</code> base on [FILED ID]
	 */
	public GAWRecordDataModel getGAWRecord();
}