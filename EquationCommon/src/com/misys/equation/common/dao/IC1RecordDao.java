package com.misys.equation.common.dao;

import com.misys.equation.common.dao.beans.C1RecordDataModel;

/**
 * This is an interface which is going to expose all common Dao services. It is going to behave as a bridge between the Dao and the
 * client code.
 * 
 * @author eranag1
 * 
 */

public interface IC1RecordDao extends IDao
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
	public boolean checkIfThisC1RecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisC1RecordIsInTheDB();

	/**
	 * This method is going to return a <code>C1RecordDataModel</code>
	 * 
	 * @return a <code>C1RecordDataModel</code> base on [FILED ID]
	 */
	public C1RecordDataModel getC1Record();

}
