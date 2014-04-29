package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.misys.equation.common.dao.INE1RecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.NE1RecordDataModel;
import com.misys.equation.common.internal.dao.mappers.NE1RecordRowMapper;

/**
 * This the NE1 -Record Dao implementation. <br>
 * This class is going to provide all back-end services for NE1 -Record.
 * 
 * @author deroset
 */
public class NE1RecordDao extends AbsEquationDao implements INE1RecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1272538474700l;

	public NE1RecordDao()
	{
		super();
	}

	/**
	 * This method will check if the current record is already in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @param sqlWhereStatement
	 *            - the WHERE clause of the SQL statement
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisNE1RecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisNE1RecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public NE1RecordDataModel getMyDataModel()
	{
		NE1RecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof NE1RecordDataModel)
		{
			dataModel = (NE1RecordDataModel) getRecord();

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
		whereCondition.append("NEAB ='");
		whereCondition.append(getMyDataModel().getAccountBranch());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("NEAN ='");
		whereCondition.append(getMyDataModel().getBasicNumber());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("NEAS ='");
		whereCondition.append(getMyDataModel().getAccountSuffix());
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
		String fields = "NEEAN= ?, NEAB= ?, NEAN= ?, NEAS= ?, NEIBAN= ?";
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
		String fields = "NEEAN, NEAB, NEAN, NEAS, NEIBAN ";
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
		String fields = "?, ?, ?, ?, ?";
		return fields;
	}

	/**
	 * This method create an array that contains the fields values and Its types. <br>
	 * It will be used by JDBC <code>PreparedStatement</code>
	 * 
	 * @return An array that contains the field values and their types.
	 */
	@Override
	public Object[] getParameterizedFieldsValues()
	{
		NE1RecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getExternalAcNumber(), dataModel.getAccountBranch(), dataModel.getBasicNumber(),
						dataModel.getAccountSuffix(), dataModel.getIban() };
		return object;
	}

	/**
	 * This method is going execute an SQL query using the filter criteria.
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the SQL filter.
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecordBy(String whereClause)
	{
		return getRecordBy(whereClause, new NE1RecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new NE1RecordRowMapper());
	}

	/**
	 * This method is going to return a <code> NE1RecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> NE1RecordDataModel </code> base on $tablePrefix-ID
	 */
	public NE1RecordDataModel getNE1Record()
	{
		NE1RecordDataModel dataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new NE1RecordRowMapper());

		if (!results.isEmpty())
		{
			dataModel = (NE1RecordDataModel) results.get(0);
		}
		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}

	/**
	 * This method will return the Iban and the external account.
	 * 
	 * @param branch
	 *            this is the current account branch.
	 * @param basicNumber
	 *            this is the current account basic.
	 * @param suffix
	 *            this is the current account suffix.
	 * @return the Iban and the external account.
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getIbanAndEXtAccount(String branch, String basicNumber, String suffix)
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<Map<String, Object>> result = null;

		sqlBuilder.append("SELECT NEEAN, NEIBAN FROM ");
		sqlBuilder.append(getTableName());

		sqlBuilder.append(" WHERE NEAB= '");
		sqlBuilder.append(branch);
		sqlBuilder.append("' AND NEAN='");
		sqlBuilder.append(basicNumber);

		sqlBuilder.append("' AND NEAS='");
		sqlBuilder.append(suffix);
		sqlBuilder.append("'");

		result = getJdbcTemplate().queryForList(sqlBuilder.toString());
		return result;
	}
}