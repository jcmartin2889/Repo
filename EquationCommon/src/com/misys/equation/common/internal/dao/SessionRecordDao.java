package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.ISessionRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.SessionRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.SessionRecordRowMapper;

/**
 * This the Session-Record Dao implementation. <br>
 * This class is going to provide all back-end services for Session-Record.
 * 
 * @author deroset
 */
public class SessionRecordDao extends AbsEquationDao implements ISessionRecordDao
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SessionRecordDao.java 9725 2010-11-08 12:34:45Z MACDONP1 $";

	public SessionRecordDao()
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
	public boolean checkIfThisSessionRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisSessionRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public SessionRecordDataModel getMyDataModel()
	{
		SessionRecordDataModel SessionRecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof SessionRecordDataModel)
		{
			SessionRecordDataModel = (SessionRecordDataModel) getRecord();

		}
		return SessionRecordDataModel;
	}

	/**
	 * This method will return a <code>String</code> which represents and sql where condition base on the record id.<br>
	 * For example <code>id = getDataModel.getId()</code>
	 * 
	 * @return a <code>String</code> which represents and sql filter.
	 */
	@Override
	protected String getWhereConditionBaseOnIdRecord()
	{
		StringBuilder whereCondition = new StringBuilder("WEXSID='");
		whereCondition.append(getMyDataModel().getSessionId());
		whereCondition.append("' and ");

		whereCondition.append("WEXTID='");
		whereCondition.append(getMyDataModel().getTransactionId());
		whereCondition.append("' and ");

		whereCondition.append("WEXOID='");
		whereCondition.append(getMyDataModel().getOptionId());
		whereCondition.append("' and ");

		whereCondition.append("WEXUID='");
		whereCondition.append(getMyDataModel().getUserId());
		whereCondition.append("'");

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
		String fields = " WEXUID=?, WEXOID=?, WEXSID=?, WEXTID=?, WEXFCT=?, WEXDTA=?, WEXDTA2=?, WEXDTA3=?, WEXDTA4=?, WEXMSG=?, WEXWARN=?, WEXBDTA=? ";
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
		String fields = " WEXUID, WEXOID, WEXSID, WEXTID, WEXFCT, WEXDTA, WEXDTA2, WEXDTA3, WEXDTA4, WEXMSG, WEXWARN, WEXBDTA ";
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
		String fields = " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ";
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
		SessionRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getUserId(), dataModel.getOptionId(), dataModel.getSessionId(),
						dataModel.getTransactionId(), dataModel.getMode(), dataModel.getFunctionData(),
						dataModel.getFunctionCRMData(), dataModel.getFunctionEFCData(), dataModel.getFunctionEFC2Data(),
						dataModel.getFunctionMessages(), dataModel.getWarningMessages(), dataModel.getBeforeImage(), };

		return object;
	}

	/**
	 * Check whether the transaction id is unique
	 * 
	 * @param tranId
	 *            - the transaction Id to check
	 * 
	 * @return true - if the transaction id already exists
	 */
	public boolean checkIfThisSessionIsInTheDBBaseOnTransactionId(String userId, String tranId)
	{
		StringBuilder sqlWhereStatement = new StringBuilder("WEXUID='");
		sqlWhereStatement.append(userId);
		sqlWhereStatement.append("' AND WEXTID='");
		sqlWhereStatement.append(tranId);
		sqlWhereStatement.append("'");
		return checkIfThisRecordIsInTheDB(sqlWhereStatement.toString());
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
		return getRecordBy(whereClause, new SessionRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new SessionRecordRowMapper());
	}

	/**
	 * This method is going to return a <code>SessionRecordDataModel</code> base on SessionFID
	 * 
	 * @return a <code>SessionRecordDataModel</code> base on SessionFID
	 */
	public SessionRecordDataModel getSessionRecord()
	{
		SessionRecordDataModel SessionRecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new SessionRecordRowMapper());

		if (!results.isEmpty())
		{
			SessionRecordDataModel = (SessionRecordDataModel) results.get(0);
		}

		return SessionRecordDataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}
