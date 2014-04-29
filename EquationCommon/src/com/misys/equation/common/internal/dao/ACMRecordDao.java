package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.misys.equation.common.dao.IACMRecordDao;
import com.misys.equation.common.dao.beans.ACMRecordDataModel;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.internal.dao.mappers.ACMRecordJoinRowMapper;
import com.misys.equation.common.internal.dao.mappers.ACMRecordRowMapper;

/**
 * This the ACM-Record Dao implementation. <br>
 * This class is going to provide all back-end services for ACM-Record.
 * 
 * @author deroset
 */
public class ACMRecordDao extends AbsEquationDao implements IACMRecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACMRecordDao.java 9725 2010-11-08 12:34:45Z MACDONP1 $";
	public ACMRecordDao()
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
	public boolean checkIfThisACMRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisACMRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public ACMRecordDataModel getMyDataModel()
	{
		ACMRecordDataModel ACMRecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof ACMRecordDataModel)
		{
			ACMRecordDataModel = (ACMRecordDataModel) getRecord();

		}
		return ACMRecordDataModel;
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
		StringBuilder whereCondition = new StringBuilder("ACMFLD='");
		whereCondition.append(getMyDataModel().getTypeName());
		whereCondition.append("' ");
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
		String fields = " ACMFLD=?, ACMDSC=?, ACMTYP=?, ACMUPP=?, ACMLTH=?, ACMDEC=?, ACMINT=?, ACMMAX=?, ACMMIN=?, ACMVAL=?, ACMREG=?, ACMPMT=?, ACMVLD=? ";
		return fields;
	}

	/**
	 * Returns a list of the field names
	 * 
	 * @return a list of the field names
	 */
	@Override
	protected String getFields()
	{
		String fields = " ACMFLD, ACMDSC, ACMTYP, ACMUPP, ACMLTH, ACMDEC, ACMINT, ACMMAX, ACMMIN, ACMVAL, ACMREG, ACMPMT, ACMVLD ";
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
		String fields = " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ";
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
		ACMRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getTypeName(), dataModel.getDescription(), dataModel.getDataType(),
						dataModel.getUpperCase(), dataModel.getLength(), dataModel.getDecimals(), dataModel.getInitialValue(),
						dataModel.getMinLength(), dataModel.getMaxLength(), dataModel.getValidValues(), dataModel.getRegEx(),
						dataModel.getPromptProgram(), dataModel.getValidationProgram(), };

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
		return getRecordBy(whereClause, new ACMRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{

		return getRecordBy(null, new ACMRecordRowMapper());
	}

	/**
	 * This method is going to return an <code>ACMRecordDataModel</code>
	 * 
	 * @return an <code>ACMRecordDataModel</code>
	 */
	public ACMRecordDataModel getACMRecord()
	{
		ACMRecordDataModel ACMRecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new ACMRecordRowMapper());

		if (!results.isEmpty())
		{
			ACMRecordDataModel = (ACMRecordDataModel) results.get(0);
		}

		return ACMRecordDataModel;
	}

	/**
	 * This method will return a Map of ACM records
	 * 
	 * @return a map of the data model records keyed by the field id
	 */
	@SuppressWarnings("unchecked")
	public Map<String, ACMRecordDataModel> getACMRecordsMap()
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<ACMRecordDataModel> dataModels = null;

		sqlBuilder.append("SELECT ");
		sqlBuilder.append(getFields());
		sqlBuilder.append(" FROM ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(" ORDER BY ACMFLD ASC");
		JdbcTemplate select = getJdbcTemplate();
		dataModels = select.query(sqlBuilder.toString(), new ACMRecordRowMapper());
		return createHashTableModel(dataModels);
	}

	/**
	 * This method will return a <code>ACMRecordDataModel</code>.
	 * 
	 * @param fieldType
	 * <br>
	 *            The filed type will be used as filter in the LEFT JOIN.
	 * @return a <code>ACMRecordDataModel</code>.
	 */
	@SuppressWarnings("unchecked")
	public ACMRecordDataModel findByFieldType(String fieldType)
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<ACMRecordDataModel> dataModels = null;
		ACMRecordDataModel aCMRecordDataModel = null;
		sqlBuilder.append("SELECT ");
		sqlBuilder.append(getFields());
		sqlBuilder.append(",GAEFNM");
		sqlBuilder.append(" FROM ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(" LEFT JOIN GAEPF ON GAESCN = ACMVLD AND GAEATYP = '009' where ACMFLD =' ");
		sqlBuilder.append(fieldType);
		sqlBuilder.append("'");

		JdbcTemplate select = getJdbcTemplate();

		if (LOG.isDebugEnabled())
		{
			LOG.debug(new StringBuilder("The executed sql is: ").append(sqlBuilder.toString()).toString());
		}

		dataModels = select.query(sqlBuilder.toString(), new ACMRecordJoinRowMapper());

		if (!dataModels.isEmpty())
		{

			aCMRecordDataModel = dataModels.get(0);
		}
		return aCMRecordDataModel;
	}
	/**
	 * This is a internal helper method which create a <code>Hashtable</code> <br>
	 * which contains a record of <code>ACMRecordDataModel</code>
	 * 
	 * @param dataModels
	 *            record of <code>ACMRecordDataModel</code>.
	 * @return a <code>Hashtable</code> which contains a <code>ACMRecordDataModel</code> objects.
	 */
	private Hashtable<String, ACMRecordDataModel> createHashTableModel(List<ACMRecordDataModel> dataModels)
	{
		Hashtable<String, ACMRecordDataModel> modelTable = new Hashtable<String, ACMRecordDataModel>();

		for (ACMRecordDataModel record : dataModels)
		{
			modelTable.put(record.getTypeName(), record);
		}

		return modelTable;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}