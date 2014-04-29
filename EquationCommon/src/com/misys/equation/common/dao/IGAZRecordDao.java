package com.misys.equation.common.dao;

import com.misys.equation.common.dao.beans.GAZRecordDataModel;

/**
 * This is an interface which is going to expose all common Dao services. It is going to behave as a bridge between the Dao and the
 * client code.
 * 
 * @author deroset
 * 
 */

public interface IGAZRecordDao extends IDao
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
	public boolean checkIfThisGAZRecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGAZRecordIsInTheDB();

	/**
	 * This method is going to return a <code>GAZRecordDataModel</code> base on GAZFID
	 * 
	 * @return a <code>GAZRecordDataModel</code> base on [FILED ID]
	 */
	public GAZRecordDataModel getGAZRecord();

	/**
	 * This method is going to delete all the records which have the supplied option id and type.
	 * <p>
	 * This method is called to delete a records of the specified type during deployment, before any items still existing are
	 * re-added. Otherwise, items would remain on the GAZPF
	 * 
	 * @param option
	 *            this is the service or layout id
	 * @param type
	 *            This indicates the type of item (e.g. layout display attribute, service PV mapping)
	 * 
	 */
	public void deleteRecordByOptionAndType(String option, String type);

}
