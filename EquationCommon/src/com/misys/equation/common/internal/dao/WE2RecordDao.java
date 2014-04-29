package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.misys.equation.common.dao.IWE2RecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.WE2RecordDataModel;
import com.misys.equation.common.internal.dao.mappers.WE2RecordRowMapper;

/**
 * This the WE2-Record Dao implementation. <br>
 * This class is going to provide all back-end services for WE2-Record.
 * 
 * @author deroset
 */
public class WE2RecordDao extends AbsEquationDao implements IWE2RecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WE2RecordDao.java 11959 2011-09-30 09:29:54Z rpatrici $";

	// NOTE TO DEVELOPERS *********************************************************************
	// ********************************************************************************************
	// - To retrieve record by Task Id:
	// dao = daoFactory.getWE2RecordDao(new WE2RecordDataModel(taskId))
	// dao.getW2ERecord();
	//
	// - To retrieve record by WE Unique keys:
	// daoFactory.getWE2RecordDao(new WE2RecordDataModel(sessionId, userId, optionId, transactionId)
	// dao.getW2Record();
	//
	// - To retrieve record by Linked Task Id:
	// dao = daoFactory.getWE2RecordDaodataModel(new WE2RecordDataModel(linkedTaskId, true))
	// dao.getLinkedWE2Record();
	// 
	// - To retrieve record by Linked WE Unique keys:
	// dao = daoFactory.getWE2RecordDaodataModel(new WE2RecordDataModel(linkedSessionId, linkedUserId, linkedOptionId,
	// linkedTransactionId, true))
	// dao.getLinkedWE2Record();
	//
	// - To delete record by task id
	// dao = daoFactory.getWE2RecordDao(new WE2RecordDataModel(taskId))
	// dao.deleteRecord()
	//
	// - To delete record by WE Unique keys
	// dao = daoFactory.getWE2RecordDao(new WE2RecordDataModel(sessionId, userId, optionId, transactionId))
	// dao.deleteRecord()
	//
	// - To delete record by linked task id
	// dao = daoFactory.getWE2RecordDao(new WE2RecordDataModel(linkedTaskId, true))
	// dao.deleteWE2LinkedRecord()
	//
	// - To delete record by linked WE Unique keys
	// dao = daoFactory.getWE2RecordDao(new WE2RecordDataModel(linkedSessionId, linkedUserId, linkedOptionId, linkedTransactionId,
	// true))
	// dao.deleteWE2LinkedRecord()
	//
	// - To do existence check using the task id
	// dao = daoFactory.getWE2RecordDao(new WE2RecordDataModel(taskId))
	// dao.checkIfThisWE2RecordIsInTheDB();
	// 
	// - To do existence check using WE Unique keys
	// dao = daoFactory.getWE2RecordDao(new WE2RecordDataModel(sessionId, userId, optionId, transactionId))
	// use checkIfThisWE2RecordIsInTheDB()
	//
	// SEE FunctionSession.delete()
	// NOTE******************************
	// *********************************************************************************************

	public WE2RecordDao()
	{
		super();
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @param sqlWhereStatement
	 *            - the WHERE clause of the SQL statement
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisWE2RecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisWE2RecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public WE2RecordDataModel getMyDataModel()
	{
		WE2RecordDataModel WE2RecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof WE2RecordDataModel)
		{
			WE2RecordDataModel = (WE2RecordDataModel) getRecord();
		}
		return WE2RecordDataModel;
	}

	/**
	 * This method will return a <code>String</code> which represents and sql where condition base on the record id.<br>
	 * For example <code>id = getDataModel.getId()</code>
	 * 
	 * If the Task Id is populated this will be the key. Otherwise the key will be the combination of the Session Id, User Id,
	 * Option Id and Transaction Id
	 * 
	 * @return a <code>String</code> which represents and sql filter.
	 */
	@Override
	protected String getWhereConditionBaseOnIdRecord()
	{
		StringBuilder whereCondition;

		if (getMyDataModel().getTaskId() != null && getMyDataModel().getTaskId().trim().length() > 0)
		{
			whereCondition = new StringBuilder("WE2TSK='");
			whereCondition.append(getMyDataModel().getTaskId());
			whereCondition.append("'");

		}
		else
		{
			whereCondition = new StringBuilder("WE2SID='");
			whereCondition.append(getMyDataModel().getSessionId());
			whereCondition.append("'and WE2UID='");
			whereCondition.append(getMyDataModel().getUserId());
			whereCondition.append("'and WE2OID='");
			whereCondition.append(getMyDataModel().getOptionId());
			whereCondition.append("'and WE2TID='");
			whereCondition.append(getMyDataModel().getTransactionId());
			whereCondition.append("'");

		}
		return whereCondition.toString();
	}

	/**
	 * This method will return a <code>String</code> which represents and sql where condition base on the record id.<br>
	 * For example <code>id = getDataModel.getId()</code>
	 * 
	 * If the Linked Task Id is populated this will be the key. Otherwise the key will be the combination of the Linked Session Id,
	 * Linked User Id, Linked Option Id and Linked Transaction Id
	 * 
	 * @return a <code>String</code> which represents and sql filter.
	 */
	private String getWhereConditionBaseOnLinkedIdRecord()
	{
		StringBuilder whereCondition;

		if (getMyDataModel().getLinkedTaskId() != null && getMyDataModel().getLinkedTaskId().trim().length() > 0)
		{
			whereCondition = new StringBuilder("WE2LSK='");
			whereCondition.append(getMyDataModel().getLinkedTaskId());
			whereCondition.append("'");

		}
		else
		{
			whereCondition = new StringBuilder("WE2LSD='");
			whereCondition.append(getMyDataModel().getLinkedSessionId());
			whereCondition.append("'and WE2LUD='");
			whereCondition.append(getMyDataModel().getLinkedUserId());
			whereCondition.append("'and WE2LOD='");
			whereCondition.append(getMyDataModel().getLinkedOptionId());
			whereCondition.append("'and WE2LTD='");
			whereCondition.append(getMyDataModel().getLinkedTransactionId());
			whereCondition.append("'");

		}
		return whereCondition.toString();
	}

	/**
	 * Returns a list of name and ? pairs in the format of:
	 * <p>
	 * field1=?, field2=?, field3=?, ...
	 * 
	 * @return a list of name and ? pairs
	 */
	@Override
	protected String getParameterizedFields()
	{
		String fields = " WE2TSK = ?, WE2SID = ?, WE2UID = ?, WE2OID = ?, WE2TID = ?, WE2LTK = ?, WE2LSD = ?, WE2LUD = ?, WE2LOD = ?, WE2LTD = ? ";
		return fields;
	}

	/**
	 * Returns a list of the filed's name
	 * 
	 * @return a list of the filed's name
	 */
	@Override
	protected String getFields()
	{
		String fields = " WE2TSK, WE2SID, WE2UID, WE2OID, WE2TID, WE2LTK, WE2LSD, WE2LUD, WE2LOD, WE2LTD ";
		return fields;
	}

	/**
	 * Returns a list of the filed's parameters
	 * 
	 * @return the list of the filed's parameters
	 */
	@Override
	protected String getParameters()
	{
		String fields = " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
		return fields;
	}

	/**
	 * This method create an array that contains the fields values and Its types. <br>
	 * It will be used by jdbc <code>PreparedStatement</code>
	 * 
	 * @return An array that contains the fields values and Its types.
	 */
	@Override
	public Object[] getParameterizedFieldsValues()
	{
		WE2RecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getTaskId(), dataModel.getSessionId(), dataModel.getUserId(),
						dataModel.getOptionId(), dataModel.getTransactionId(), dataModel.getLinkedTaskId(),
						dataModel.getLinkedSessionId(), dataModel.getLinkedUserId(), dataModel.getLinkedOptionId(),
						dataModel.getLinkedTransactionId() };

		return object;
	}

	/**
	 * This method is going execute a Sql query using the filter criteria.
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the sql filter.
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecordBy(String whereClause)
	{
		return getRecordBy(whereClause, new WE2RecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new WE2RecordRowMapper());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.misys.equation.common.dao.IWE2RecordDao#getWE2Record()
	 */
	public WE2RecordDataModel getWE2Record()
	{
		WE2RecordDataModel WE2RecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new WE2RecordRowMapper());

		if (!results.isEmpty())
		{
			WE2RecordDataModel = (WE2RecordDataModel) results.get(0);
		}
		return WE2RecordDataModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.misys.equation.common.dao.IWE2RecordDao#getWE2LinkedRecord()
	 */
	public WE2RecordDataModel getWE2LinkedRecord()
	{
		WE2RecordDataModel WE2RecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnLinkedIdRecord(), new WE2RecordRowMapper());

		if (!results.isEmpty())
		{
			WE2RecordDataModel = (WE2RecordDataModel) results.get(0);
		}
		return WE2RecordDataModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.misys.equation.common.dao.IWE2RecordDao#deleteWE2LinkedRecord()
	 */
	public void deleteWE2LinkedRecord()
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		JdbcTemplate updateTemplate;

		sqlBuilder.append("DELETE FROM ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(" WHERE ");
		sqlBuilder.append(getWhereConditionBaseOnLinkedIdRecord());
		updateTemplate = getJdbcTemplate();

		if (LOG.isDebugEnabled())
		{
			LOG.debug(new StringBuilder("The executed sql is: ").append(sqlBuilder.toString()).toString());
		}
		updateTemplate.update(sqlBuilder.toString());

		if (isAutocommitable())
		{
			commit();
		}
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}

}
