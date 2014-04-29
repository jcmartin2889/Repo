package com.misys.equation.common.dao;

import com.misys.equation.common.dao.beans.CHRecordDataModel;

/**
 * This is an interface which is going to expose the Dao services. It is going to behave as a bridge between the Dao and the client
 * code.
 * 
 * @author deroset
 * 
 */
public interface ICHRecordDao extends IDao
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
	public boolean checkIfThisCHRecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisCHRecordIsInTheDB();

	/**
	 * This method is going to return a <code>CHRecordDataModel</code> base on INDDRNO, INDDMCH, INDDSEQ, INDDPRD, INDCPRD
	 * 
	 * @return a <code>CHRecordDataModel</code> base on INDDRNO, INDDMCH, INDDSEQ, INDDPRD, INDCPRD
	 */
	public CHRecordDataModel getCHRecord();
}