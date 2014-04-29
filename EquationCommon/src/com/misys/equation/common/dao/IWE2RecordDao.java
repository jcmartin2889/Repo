package com.misys.equation.common.dao;

import com.misys.equation.common.dao.beans.WE2RecordDataModel;

/**
 * This is an interface which is going to expose all common Dao services. It is going to behave as a bridge between the Dao and the
 * client code.
 * 
 * @author yzobdabu
 * 
 */

public interface IWE2RecordDao extends IDao
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: IWE2RecordDao.java 11959 2011-09-30 09:29:54Z rpatrici $";

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @param sqlWhereStatement
	 *            - the WHERE clause of the SQL statement
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisWE2RecordIsInTheDB(String sqlWhereStatement);

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisWE2RecordIsInTheDB();

	/**
	 * This method is going to return a <code>WE2RecordDataModel</code> base on Task Id (WE2TSK)
	 * 
	 * If the Task Id is not provided or is blank, this is going to return a <code>WE2RecordDataModel</code> base on WE record keys
	 * Session Id (WE2SID), User Id (WE2UID), Option Id (WE2OID) and Transaction Id (WE2TID)
	 * 
	 * @return a <code>WERecordDataModel</code> base on Task Id (WE2TSK) or base on WE record keys Session Id (WE2SID), User Id
	 *         (WE2UID), Option Id (WE2OID) and Transaction Id (WE2TID)
	 */
	public WE2RecordDataModel getWE2Record();

	/**
	 * This method is going to return a <code>WE2RecordDataModel</code> base on Linked Task Id (WE2LTK)
	 * 
	 * If the Linked Task Id is not provided or is blank, this is going to return a <code>WE2RecordDataModel</code> base on WE
	 * record keys Linked Session Id (WE2LSD), Linked User Id (WE2LUD), Linked Option Id (WE2LOD) and Linked Transaction Id (WE2LTD)
	 * 
	 * @return a <code>WERecordDataModel</code> base on Linked Task Id (WE2LTK) or base on WE record keys Linked Session Id
	 *         (WE2LSD), Linked User Id (WE2LUD), Linked Option Id (WE2LOD) and Linked Transaction Id (WE2LTD)
	 */
	public WE2RecordDataModel getWE2LinkedRecord();

	/**
	 * This method is going to delete a WE2 record base on Linked Task Id (WE2LTK)
	 * 
	 * If the Linked Task Id is not provided or is blank, this is going to delete a WE2 record base on WE record keys Linked Session
	 * Id (WE2LSD), Linked User Id (WE2LUD), Linked Option Id (WE2LOD) and Linked Transaction Id (WE2LTD)
	 */
	public void deleteWE2LinkedRecord();

}
