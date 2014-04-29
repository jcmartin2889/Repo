package com.misys.equation.common.dao;

import java.util.Map;

import com.misys.equation.common.dao.beans.ACMRecordDataModel;

/**
 * This is an interface which is going to expose all common Dao services. It is going to behave as a bridge between the Dao and the
 * client code.
 * 
 * @author deroset
 * 
 */

public interface IACMRecordDao extends IDao
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
	public boolean checkIfThisACMRecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisACMRecordIsInTheDB();

	/**
	 * This method is going to return a <code>ACMRecordDataModel</code> base on ACMFID
	 * 
	 * @return a <code>ACMRecordDataModel</code> base on [FILED ID]
	 */
	public ACMRecordDataModel getACMRecord();

	/**
	 * This method will return a <code>ACMRecordDataModel</code>.
	 * 
	 * @param fieldType
	 * <br>
	 *            The filed type will be used as filter in the LEFT JOIN.
	 * @return a <code>ACMRecordDataModel</code>.
	 */
	public ACMRecordDataModel findByFieldType(String fieldType);

	/**
	 * This method will return a <code>Map</code> of <code>ACMRecordDataModel</code> objects keyed by the field name.
	 * 
	 * @return a <code>Map</code> of <code>ACMRecordDataModel</code> objects.
	 */
	public Map<String, ACMRecordDataModel> getACMRecordsMap();
}
