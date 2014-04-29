package com.misys.equation.common.dao;

import java.util.List;

import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.HeaderRecordDataModel;

public interface IHeaderRecordDao extends IDao
{

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfHeaderRecordIsInTheDB();

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @param seqId
	 *            - the sequence Id corresponding to the GAUPF record
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfHeaderRecordIsInTheDB(long seqId);

	/**
	 * This method is going to return a <code>HeaderRecordDataModel</code> base on GAUPF ID
	 * 
	 * @return a <code>HeaderRecordDataModel</code> base on [FILED ID]
	 */
	public HeaderRecordDataModel getHeaderRecord();

	/**
	 * This method is going to return <code>HeaderRecordDataModel</code> with last update and last action information
	 * 
	 * @param whereClause
	 */
	public List<AbsRecord> getRecordsBy(String whereClause);

	/**
	 * This method is going to return <code>GAURecordDataModel</code> with last update and last action information
	 * 
	 * @param whereClause
	 */
	public HeaderRecordDataModel getAuditHeader(long seqId);
}