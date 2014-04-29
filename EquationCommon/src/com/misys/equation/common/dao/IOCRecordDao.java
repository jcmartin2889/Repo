package com.misys.equation.common.dao;

import com.misys.equation.common.dao.beans.OCRecordDataModel;

/**
 * This is an interface which is going to expose all common Dao services. It is going to behave as a bridge between the Dao and the
 * client code.
 * 
 * @author deroset
 * 
 */
public interface IOCRecordDao extends IDao
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
	public boolean checkIfThisOCRecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisOCRecordIsInTheDB();

	/**
	 * This method is going to return a <code>OCRecordDataModel</code> base on OCFID
	 * 
	 * @return a <code>OCRecordDataModel</code> base on OCFID
	 */
	public OCRecordDataModel getOCRecord();

	/**
	 * This method will return an int count of the number of records on the file
	 * 
	 * @return the number of records on the file
	 */
	public int getRecordCount();
}
