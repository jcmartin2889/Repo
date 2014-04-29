package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.misys.equation.common.dao.IDH0RecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.DH0RecordDataModel;
import com.misys.equation.common.internal.dao.mappers.DH0RecordRowMapper;

/**
 * This the DH0 -Record Dao implementation. <br>
 * This class is going to provide all back-end services for DH0 -Record.
 * 
 * @author deroset
 */

public class DH0RecordDao extends AbsEquationDao implements IDH0RecordDao
{

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1263561056191l;

	public DH0RecordDao()
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
	public boolean checkIfThisDH0RecordIsInTheDB(String sqlWhereStatement)
	{

		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisDH0RecordIsInTheDB()
	{

		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public DH0RecordDataModel getMyDataModel()
	{

		DH0RecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof DH0RecordDataModel)
		{
			dataModel = (DH0RecordDataModel) getRecord();

		}
		return dataModel;
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
		StringBuilder whereCondition = new StringBuilder(1024);
		whereCondition.append("DHANMD ='");
		whereCondition.append(getMyDataModel().getAccountMnemonic());
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
		String fields = "DHACD= ?, DHANMD= ?, DHATP= ?, DHBNOC= ?, DHCTP= ?, DHCRT= ?, DHDIA= ?, DHDLM= ?, DHDRT= ?, DHSNA= ?, DHULV= ?, DHPOT= ?, DHADES= ?";
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
		String fields = "DHACD, DHANMD, DHATP, DHBNOC, DHCTP, DHCRT, DHDIA, DHDLM, DHDRT, DHSNA, DHULV, DHPOT, DHADES ";
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
		String fields = "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
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
		DH0RecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getAnalysisCode(), dataModel.getAccountMnemonic(), dataModel.getAcType(),
						dataModel.getCustomerNumber(), dataModel.getCustomerType(), dataModel.getTcd(),
						dataModel.getDefaultInternalAcNoDescription(), dataModel.getDhdlm(), dataModel.getTcdrt(),
						dataModel.getShortName(), dataModel.getUpdateLevelNo(), dataModel.getPostContraTran(),
						dataModel.getAccountTypeDescription() };
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

		return getRecordBy(whereClause, new DH0RecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{

		return getRecordBy(null, new DH0RecordRowMapper());
	}

	/**
	 * This method is going to return a <code> DH0RecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> DH0RecordDataModel </code> base on $tablePrefix-ID
	 */
	public DH0RecordDataModel getDH0Record()
	{
		DH0RecordDataModel dataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new DH0RecordRowMapper());

		if (!results.isEmpty())
		{

			dataModel = (DH0RecordDataModel) results.get(0);
		}

		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}

	/**
	 * This method will return the system account information for the passed system account id.
	 * 
	 * @param systemAccountId
	 *            this is the systemAccount id.
	 * @return the system account information for the passed system account id
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSystemAccountInformation(String systemAccountId)
	{

		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<Map<String, Object>> result = null;

		sqlBuilder.append("SELECT  DHBNOC,DHANMD  FROM ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(" WHERE DHANMD= '");
		sqlBuilder.append(systemAccountId);
		sqlBuilder.append("'");

		result = getJdbcTemplate().queryForList(sqlBuilder.toString());
		return result;
	}

}
