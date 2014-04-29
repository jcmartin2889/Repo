package com.misys.equation.common.dao;

import java.util.List;

import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.HBXRecordDataModel;

/**
 * This is an interface which is going to expose all common Dao services. It is going to behave as a bridge between the Dao and the
 * client code.
 * 
 * @author deroset
 * 
 */

public interface IHBXRecordDao extends IDao
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
	public boolean checkIfThisHBXRecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisHBXRecordIsInTheDB();

	/**
	 * This method is going to return a <code>HBRecordDataModel</code> base on HBFID
	 * 
	 * @return a <code>HBRecordDataModel</code> base on [FILED ID]
	 */
	public HBXRecordDataModel getHBXRecord();

	/**
	 * This method will return the HBX record with timestamp greater than the timestamp supplied
	 * 
	 * @param owner
	 *            - owner
	 * @param timestamp
	 *            - timestamp
	 * @return a <code>HBRecordDataModel</code>
	 */
	public HBXRecordDataModel findWithLaterTimestamp(final String owner, String timestamp);
	/**
	 * This method is going execute a SQL query using the filter criteria.
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the SQL filter. This should be null if no filtering is required.
	 * @return a <code>Map</code> containing <code>HBXRecordDataModel</code> objects, keyed by owner Strings
	 */
	public List<AbsRecord> getRecordBy(String whereClause);
}
