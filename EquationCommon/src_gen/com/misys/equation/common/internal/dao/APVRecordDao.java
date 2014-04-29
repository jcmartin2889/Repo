package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IAPVRecordDao;
import com.misys.equation.common.dao.beans.APVRecordDataModel;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.internal.dao.mappers.APVRecordRowMapper;

/**
 * This the APV -Record Dao implementation. <br>
 * This class is going to provide all back-end services for APV -Record.
 * 
 * @author deroset
 */
public class APVRecordDao extends AbsEquationDao implements IAPVRecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1283761830381l;

	public APVRecordDao()
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
	public boolean checkIfThisAPVRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisAPVRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public APVRecordDataModel getMyDataModel()
	{
		APVRecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof APVRecordDataModel)
		{
			dataModel = (APVRecordDataModel) getRecord();

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
		whereCondition.append("APVARF ='");
		whereCondition.append(getMyDataModel().getApiReference());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("APVFIL ='");
		whereCondition.append(getMyDataModel().getApiFileName());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("APVALV ='");
		whereCondition.append(getMyDataModel().getEquationApiLevel());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("APVPGM ='");
		whereCondition.append(getMyDataModel().getApiProgram());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("APVKST ='");
		whereCondition.append(getMyDataModel().getApiKeyStart());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("APVKLG ='");
		whereCondition.append(getMyDataModel().getApiKeyLength());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("APVFRO ='");
		whereCondition.append(getMyDataModel().getProgramRoot());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("APVAPID ='");
		whereCondition.append(getMyDataModel().getApiDescription());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("APVCOND ='");
		whereCondition.append(getMyDataModel().getConditions());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("APVTYP ='");
		whereCondition.append(getMyDataModel().getType());
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
		String fields = "APVARF= ?, APVFIL= ?, APVALV= ?, APVPGM= ?, APVKST= ?, APVKLG= ?, APVFRO= ?, APVAPID= ?, APVCOND= ?, APVTYP= ?";
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
		String fields = "APVARF, APVFIL, APVALV, APVPGM, APVKST, APVKLG, APVFRO, APVAPID, APVCOND, APVTYP ";
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
		String fields = "?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
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
		APVRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getApiReference(), dataModel.getApiFileName(), dataModel.getEquationApiLevel(),
						dataModel.getApiProgram(), dataModel.getApiKeyStart(), dataModel.getApiKeyLength(),
						dataModel.getProgramRoot(), dataModel.getApiDescription(), dataModel.getConditions(), dataModel.getType() };
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
		return getRecordBy(whereClause, new APVRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new APVRecordRowMapper());
	}

	/**
	 * This method is going to return a <code> APVRecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> APVRecordDataModel </code> base on $tablePrefix-ID
	 */
	public APVRecordDataModel getAPVRecord()
	{
		APVRecordDataModel dataModel = null;
		List results = getRecordBy(getWhereConditionBaseOnIdRecord(), new APVRecordRowMapper());

		if (!results.isEmpty())
		{

			dataModel = (APVRecordDataModel) results.get(0);
		}

		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}