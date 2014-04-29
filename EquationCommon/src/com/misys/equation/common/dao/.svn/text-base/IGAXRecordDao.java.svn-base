package com.misys.equation.common.dao;

import java.util.List;

import com.misys.equation.common.dao.beans.GAXRecordDataModel;

/**
 * This is an interface which is going to expose all common Dao services. It is going to behave as a bridge between the Dao and the
 * client code.
 * 
 * @author deroset
 * 
 */

public interface IGAXRecordDao extends IDao
{
	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @param sqlWhereStatement
	 *            - the WHERE clause of the SQL statement
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGAXRecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGAXRecordIsInTheDB();

	/**
	 * This method is going to return a <code>GAXRecordDataModel</code> base on GAXFID
	 * 
	 * @return a <code>GAXRecordDataModel</code> base on [FILED ID]
	 */
	public GAXRecordDataModel getGAXRecord();

	/**
	 * Attempts to load a XML definition record where the Timestamp is greater than that supplied.<br>
	 * This method will return a <code>GAXRecordDataModel</code>, or null if no record was found with a later timestamp
	 * 
	 * @param code
	 *            This the code that will used as a filter.Then it will be set in the returned <code>GAXRecordDataModel</code>.
	 * @param key
	 *            This the key that will used as a filter.Then it will be set in the returned <code>GAXRecordDataModel</code>.
	 * @param timestamp
	 *            the Timestamp already loaded (can be null if not previously loaded)
	 * @return return a <code>GAXRecordDataModel</code> if the record in the table has a later Timestamp, otherwise null.
	 */
	public GAXRecordDataModel findWithLaterTimestamp(final String code, final String key, final String timestamp);

	/**
	 * Retrieves the list of Keys values (option ids) for the particular code
	 * <p>
	 * This non-standard method is used by the Service Composer when retrieving the list of services for content assist
	 * 
	 * @param code
	 *            The GAXCOD value (either {@link GAXRecordDataModel#SERVICE_CODE} or {@link GAXRecordDataModel#LAYOUT_CODE}
	 * @return A <code>List</code> of Key values in ascending order
	 * 
	 */
	public List<String> getKeysBy(String code);
}
