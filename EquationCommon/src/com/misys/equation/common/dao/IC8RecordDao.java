package com.misys.equation.common.dao;

import java.util.List;

import com.misys.equation.common.dao.beans.C8RecordDataModel;

public interface IC8RecordDao extends IDao
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
	public boolean checkIfThisC8RecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisC8RecordIsInTheDB();

	/**
	 * This method is going to return a <code>HBRecordDataModel</code> base on C8CCY
	 * 
	 * @return a <code>HBRecordDataModel</code> base on [FILED ID]
	 */
	public C8RecordDataModel getC8Record();

	/**
	 * This method is going to return a Set of unique currency mnemonics
	 * 
	 * @return a Set of C8CCY
	 */
	public List<String> getC8CurrencyMnemonics();
}
