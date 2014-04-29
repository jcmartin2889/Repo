package com.misys.equation.common.dao;

import com.misys.equation.common.dao.beans.HARecordDataModel;

public interface IHARecordDao extends IDao
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
	public boolean checkIfThisHARecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisHARecordIsInTheDB();

	/**
	 * This method is going to return a <code>HBRecordDataModel</code> base on HALNM and HACFN
	 * 
	 * @return a <code>HBRecordDataModel</code> base on [FILED ID]
	 */
	public HARecordDataModel getHARecord();
}
