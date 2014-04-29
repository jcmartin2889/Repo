package com.misys.equation.common.dao;

import java.util.List;
import java.util.Map;

import com.misys.equation.common.dao.beans.DH0RecordDataModel;

/**
 * This is an interface which is going to expose all common Dao services. It is going to behave as a bridge between the Dao and the
 * client code.
 * 
 * @author deroset
 * 
 */

public interface IDH0RecordDao extends IDao
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
	public boolean checkIfThisDH0RecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisDH0RecordIsInTheDB();

	/**
	 * This method is going to return a <code>DH0RecordDataModel</code> base on DH0 ID
	 * 
	 * @return a <code>DH0RecordDataModel</code> base on [FILED ID]
	 */
	public DH0RecordDataModel getDH0Record();

	/**
	 * This method will return the system account information for the passed system account id.
	 * 
	 * @param systemAccountId
	 *            this is the systemAccount id.
	 * @return the system account information for the passed system account id
	 */
	public List<Map<String, Object>> getSystemAccountInformation(String systemAccountId);
}
