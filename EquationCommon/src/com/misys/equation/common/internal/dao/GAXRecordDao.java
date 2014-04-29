package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.misys.equation.common.dao.IGAXRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAXRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.GAXRecordRowMapper;
import com.misys.equation.common.internal.dao.mappers.GAXRecordRowMapperKey;

/**
 * This the GAX-Record Dao implementation. <br>
 * This class is going to provide all back-end services for GAX-Record.
 * 
 * @author deroset
 */
public class GAXRecordDao extends AbsEquationDao implements IGAXRecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GAXRecordDao.java 9725 2010-11-08 12:34:45Z MACDONP1 $";
	public GAXRecordDao()
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
	public boolean checkIfThisGAXRecordIsInTheDB(String sqlWhereStatement)
	{

		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGAXRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB();
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public GAXRecordDataModel getMyDataModel()
	{
		GAXRecordDataModel GAXRecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof GAXRecordDataModel)
		{
			GAXRecordDataModel = (GAXRecordDataModel) getRecord();

		}
		return GAXRecordDataModel;
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
		StringBuilder whereCondition = new StringBuilder("GAXCOD='");
		whereCondition.append(getMyDataModel().getCode());
		whereCondition.append("' and GAXKEY ='");
		whereCondition.append(getMyDataModel().getKey());
		whereCondition.append("'");
		return whereCondition.toString();
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * 
	 * 
	 * @param sqlWhereStatement
	 *            - the WHERE clause of the SQL statement
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new GAXRecordRowMapper());

		result = results.isEmpty();
		return !result;
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
		String fields = " GAXCOD=?, GAXKEY=?, GAXTIM=?, GAXXML=? ";
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
		String fields = " GAXCOD,GAXKEY,GAXTIM,GAXXML ";
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
		String fields = " ?, ?, ?, ? ";
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

		GAXRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getCode(), dataModel.getKey(), dataModel.getTimestamp(), dataModel.getLayout(), };
		return object;
	}

	/**
	 * This method is going execute a Sql query using the filter criteria.
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the SQL filter.
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecordBy(String whereClause)
	{

		return getRecordBy(whereClause, new GAXRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains records
	 */
	public List<AbsRecord> getRecords()
	{

		return getRecordBy(null, new GAXRecordRowMapper());
	}

	/**
	 * This method is going to return a <code>GAXRecordDataModel</code> base on GAXFID
	 * 
	 * @return a <code>GAXRecordDataModel</code> base on GAXFID
	 */
	public GAXRecordDataModel getGAXRecord()
	{

		GAXRecordDataModel GAXRecordDataModel = null;

		StringBuilder whereCondition = new StringBuilder(getWhereConditionBaseOnIdRecord());
		whereCondition.append(" order by GAXCOD asc, GAXKEY asc ");

		List<AbsRecord> results = getRecordBy(whereCondition.toString(), new GAXRecordRowMapper());

		if (!results.isEmpty())
		{

			GAXRecordDataModel = (GAXRecordDataModel) results.get(0);
		}

		return GAXRecordDataModel;
	}

	/**
	 * Attempts to load a XML definition record where the Timestamp is greater than that supplied.<br>
	 * This method will return a <code>GAXRecordDataModel</code>.
	 * 
	 * @param code
	 *            This the code that will used as a filter. Then it will be set in the returned <code>GAXRecordDataModel</code>.
	 * @param key
	 *            This the key that will used as a filter. Then it will be set in the returned <code>GAXRecordDataModel</code>.
	 * @param timestamp
	 *            the Timestamp already loaded (or null if not loaded)
	 * @return return a <code>GAXRecordDataModel</code> if the record in the table has a later Timestamp, otherwise null.
	 */
	@SuppressWarnings("unchecked")
	public GAXRecordDataModel findWithLaterTimestamp(final String code, final String key, String timestamp)
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<GAXRecordDataModel> dataModels = null;
		GAXRecordDataModel gAXRecordDataModel = null;

		// "SELECT GAXTIM, GAXXML FROM GAXPF WHERE GAXCOD = ? AND GAXKEY = ? AND GAXTIM > ? ORDER BY GAXCOD ASC, GAXKEY ASC, GAXTIM ASC"
		if (timestamp == null)
		{
			timestamp = "";
		}

		sqlBuilder.append("SELECT ");
		sqlBuilder.append(getFields());
		sqlBuilder.append(" FROM ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(" WHERE GAXCOD ='");
		sqlBuilder.append(code);
		sqlBuilder.append("' AND GAXKEY ='");
		sqlBuilder.append(key);
		sqlBuilder.append("' AND GAXTIM >'");
		sqlBuilder.append(timestamp.trim());
		sqlBuilder.append("' ORDER BY GAXCOD ASC, GAXKEY ASC, GAXTIM ASC ");

		JdbcTemplate select = getJdbcTemplate();

		if (LOG.isDebugEnabled())
		{
			LOG.debug(new StringBuilder("The executed sql is: ").append(sqlBuilder.toString()).toString());
		}

		dataModels = select.query(sqlBuilder.toString(), new GAXRecordRowMapper());

		if (!dataModels.isEmpty())
		{
			gAXRecordDataModel = dataModels.get(0);
		}

		return gAXRecordDataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}

	/**
	 * Returns a list of keys (service option Ids) for the given code type
	 * <p>
	 * 
	 * @param code
	 *            The GAXCOD value (either {@link GAXRecordDataModel#SERVICE_CODE} or {@link GAXRecordDataModel#LAYOUT_CODE}
	 * @return The <code>List</code> of Strings in ascending order
	 */
	@SuppressWarnings("unchecked")
	public List<String> getKeysBy(String code)
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<String> dataModels = null;

		sqlBuilder.append("SELECT GAXKEY FROM ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(" WHERE GAXCOD = '");
		sqlBuilder.append(code);
		sqlBuilder.append("' ORDER BY GAXKEY ASC");

		if (LOG.isDebugEnabled())
		{
			LOG.debug(new StringBuilder("The executed sql is: ").append(sqlBuilder.toString()).toString());
		}

		JdbcTemplate select = getJdbcTemplate();
		dataModels = select.query(sqlBuilder.toString(), new GAXRecordRowMapperKey());
		return dataModels;
	}
}