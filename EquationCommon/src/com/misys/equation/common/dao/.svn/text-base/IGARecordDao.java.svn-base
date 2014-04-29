package com.misys.equation.common.dao;

import java.util.Map;

import com.misys.equation.common.dao.beans.GARecordDataModel;

/**
 * This is an interface which is going to expose the Dao services. It is going to behave as a bridge between the Dao and the client
 * code.
 * 
 * @author deroset
 * 
 */
public interface IGARecordDao extends IDao
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
	public boolean checkIfThisGARecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGARecordIsInTheDB();

	/**
	 * This method is going to return a <code>GARecordDataModel</code> base on GAFID
	 * 
	 * @return a <code>GARecordDataModel</code> base on GAFID
	 */
	public GARecordDataModel getGARecord();

	/**
	 * This method is going execute a SQL query using the filter criteria.
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the SQL filter. This should be null if no filtering is required.
	 * @return a <code>Map</code> containing <code>GAERecordDataModel</code> objects, keyed by option id Strings
	 */
	public Map<String, GARecordDataModel> getGARecordBy(String whereClause);

}
