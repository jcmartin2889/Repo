package com.misys.equation.common.dao;

import java.util.List;
import java.util.Map;

import com.misys.equation.common.dao.beans.NE1RecordDataModel;

/**
 * This is an interface which is going to expose all common Dao services. It is going to behave as a bridge between the Dao and the
 * client code.
 * 
 * @author deroset
 */
public interface INE1RecordDao extends IDao
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
	public boolean checkIfThisNE1RecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisNE1RecordIsInTheDB();

	/**
	 * This method is going to return a <code>NE1RecordDataModel</code> base on NE1 ID
	 * 
	 * @return a <code>NE1RecordDataModel</code> base on [FILED ID]
	 */
	public NE1RecordDataModel getNE1Record();

	/**
	 * This method will return the Iban and the external account.
	 * 
	 * @param branch
	 *            this is the current account branch.
	 * @param basic
	 *            this is the current account basic.
	 * @param suffix
	 *            this is the current account suffix.
	 * @return the Iban and the external account.
	 */
	public List<Map<String, Object>> getIbanAndEXtAccount(String branch, String basic, String suffix);
}