package com.misys.equation.common.dao;

import java.util.Map;

import com.misys.equation.common.dao.beans.QZRecordDataModel;

/**
 * This is an interface which is going to expose all common Dao services. It is going to behave as a bridge between the Dao and the
 * client code.
 * 
 * @author deroset
 * 
 */

public interface IQZRecordDao extends IDao

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
	public boolean checkIfThisQZRecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisQZRecordIsInTheDB();

	/**
	 * This method is going to return a <code>QZRecordDataModel</code> based on QZFID
	 * 
	 * @return a <code>QZRecordDataModel</code> based on [FILED ID]
	 */
	public QZRecordDataModel getQZRecordByQZFID();

	/**
	 * This method is going to return a <code>Map</code> of <code>QZRecordDataModel</code> with no selection criteria
	 * 
	 * @return a <code>Map</code> of <code>QZRecordDataModel</code>
	 */
	public Map<String, QZRecordDataModel> getQZRecord();

	/**
	 * This method is going execute a SQL query using the filter criteria.
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the SQL filter. This should be null if no filtering is required.
	 * @return a <code>Map</code> containing <code>QZRecordDataModel</code> objects, keyed by option id Strings
	 */
	public Map<String, QZRecordDataModel> getQZRecordBy(String whereClause);
}
