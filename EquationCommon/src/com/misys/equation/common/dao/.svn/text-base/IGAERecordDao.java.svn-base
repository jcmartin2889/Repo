package com.misys.equation.common.dao;

import java.util.Hashtable;
import java.util.Map;

import com.misys.equation.common.dao.beans.GAERecordDataModel;

/**
 * This is an interface which is going to expose the Dao services. It is going to behave as a bridge between the Dao and the client
 * code.
 * 
 * @author deroset
 * 
 */
public interface IGAERecordDao extends IDao
{

	/**
	 * This method is going to return a <code>GAERecordDataModel</code> based on GAEAID
	 * 
	 * @return a <code>GARecordDataModel</code> base on GAEAID
	 */
	public GAERecordDataModel getGAERecord();

	/**
	 * This method is going execute a Sql query using the filter criteria.
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the sql filter.
	 * @return a <code>HashTable</code> which contains a record of <code>GAERecordDataModel</code>
	 */
	public Hashtable<String, GAERecordDataModel> getGAERecordKeyedByApiId(String whereClause);

	/**
	 * This method is going execute a Sql query using the filter criteria.
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the sql filter.
	 * @return a <code>HashTable</code> which contains a record of <code>GAERecordDataModel</code>
	 */
	public Hashtable<String, GAERecordDataModel> getGAERecordKeyedByScreenHandler(String whereClause);

	/**
	 * Read the GAE and return a LinkedHashMap which has the Program root as the key and the description as the 'value'.
	 * 
	 * Note that unlike the FindByWhereClause method, this does not actually return GAERecords
	 * 
	 * The returned collection excludes duplicate program roots (only the first is added).
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the sql filter.
	 * @return Map<String>
	 */
	public Map<String, String> findByWhereClauseOrderByRoot(String whereClause);

}
