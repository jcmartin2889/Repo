package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.misys.equation.common.dao.IWEYRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.WEYRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.WEYRecordRowMapper;

/**
 * This the WEY-Record Dao implementation. <br>
 * This class is going to provide all back-end services for Session-Record.
 * 
 * @author lima
 */
public class WEYRecordDao extends AbsEquationDao implements IWEYRecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WEYRecordDao.java 9725 2010-11-08 12:34:45Z MACDONP1 $";
	public WEYRecordDao()
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
	public boolean checkIfThisWEYRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisWEYRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public WEYRecordDataModel getMyDataModel()
	{
		WEYRecordDataModel WEYRecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof WEYRecordDataModel)
		{
			WEYRecordDataModel = (WEYRecordDataModel) getRecord();

		}
		return WEYRecordDataModel;
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
		StringBuilder whereCondition = new StringBuilder("WEYSID='");
		whereCondition.append(getMyDataModel().getSessionId());
		whereCondition.append("' and ");

		whereCondition.append("WEYTID='");
		whereCondition.append(getMyDataModel().getTransactionId());
		whereCondition.append("' and ");

		whereCondition.append("WEYOID='");
		whereCondition.append(getMyDataModel().getOptionId());
		whereCondition.append("' and ");

		whereCondition.append("WEYUID='");
		whereCondition.append(getMyDataModel().getUserId());
		whereCondition.append("' and ");

		whereCondition.append("WEYLID='");
		whereCondition.append(getMyDataModel().getLoadId());
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
		String fields = " WEYUID=?, WEYOID=?, WEYSID=?, WEYTID=?, WEYLID=?, WEYTYP=?, WEYGOF=?, WEYDTA=? ";
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
		String fields = " WEYUID, WEYOID, WEYSID, WEYTID, WEYLID, WEYTYP, WEYGOF, WEYDTA ";
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
		String fields = " ?, ?, ?, ?, ?, ?, ?, ?";
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
		WEYRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getUserId(), dataModel.getOptionId(), dataModel.getSessionId(),
						dataModel.getTransactionId(), dataModel.getLoadId(), dataModel.getLoadType(), dataModel.getGsOffset(),
						dataModel.getBeforeImage(), };

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

		return getRecordBy(whereClause, new WEYRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{

		return getRecordBy(null, new WEYRecordRowMapper());
	}

	/**
	 * This method is going to return a <code>WEYRecordDataModel</code> base on SessionFID
	 * 
	 * @return a <code>WEYRecordDataModel</code> base on SessionFID
	 */
	public WEYRecordDataModel getWEYRecord()
	{
		WEYRecordDataModel WEYRecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new WEYRecordRowMapper());

		if (!results.isEmpty())
		{

			WEYRecordDataModel = (WEYRecordDataModel) results.get(0);
		}

		return WEYRecordDataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		Hashtable<String, AbsRecord> results = null;

		WEYRecordDataModel weyRecordDataModel;

		if (!records.isEmpty())
		{
			results = new Hashtable<String, AbsRecord>();
		}
		else
		{
			return null;
		}

		for (AbsRecord absRecord : records)
		{
			weyRecordDataModel = (WEYRecordDataModel) absRecord;
			results.put(weyRecordDataModel.getKey(), weyRecordDataModel);
		}

		return results;
	}

	/**
	 * This method deletes all the records which have the supplied session id, user id, transaction id and option id
	 * <p>
	 * 
	 * @param sessionId
	 *            - the session Id
	 * @param userId
	 *            - the user id
	 * @param transactionId
	 *            - the transaction id
	 * @param optionId
	 *            - the option id
	 */
	public void deleteRecordByTransaction(String sessionId, String userId, String transactionId, String optionId)
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		JdbcTemplate updateTemplate;

		sqlBuilder.append("DELETE FROM ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(" WHERE ");
		sqlBuilder.append("WEYSID ='");
		sqlBuilder.append(sessionId);
		sqlBuilder.append("' AND WEYUID = '");
		sqlBuilder.append(userId);
		sqlBuilder.append("' AND WEYTID = '");
		sqlBuilder.append(transactionId);
		sqlBuilder.append("' AND WEYOID = '");
		sqlBuilder.append(optionId);
		sqlBuilder.append("'");

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

}
