package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IAPJRecordDao;
import com.misys.equation.common.dao.beans.APJRecordDataModel;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.internal.dao.mappers.APJRecordRowMapper;

/**
 * This the APJ -Record Dao implementation. <br>
 * This class is going to provide all back-end services for APJ -Record.
 * 
 * @author deroset
 */
public class APJRecordDao extends AbsEquationDao implements IAPJRecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1273209982494l;

	public APJRecordDao()
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
	public boolean checkIfThisAPJRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisAPJRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public APJRecordDataModel getMyDataModel()
	{
		APJRecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof APJRecordDataModel)
		{
			dataModel = (APJRecordDataModel) getRecord();

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
		whereCondition.append("APJARF ='");
		whereCondition.append(getMyDataModel().getApiReference());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("APJFIL ='");
		whereCondition.append(getMyDataModel().getApiFileName());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("APJASQ ='");
		whereCondition.append(getMyDataModel().getApiFieldSequence());
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
		String fields = "APJARF= ?, APJFIL= ?, APJALV= ?, APJASQ= ?, APJACL= ?, APJADS= ?, APJAFT= ?, APJAFS= ?, APJAFE= ?, APJAFL= ?, APJAFI= ?, APJAFF= ?, APJDCL= ?, APJDDS= ?, APJDFT= ?, APJDFS= ?, APJDFE= ?, APJDFL= ?, APJDFI= ?, APJDFF= ?, APJDIL= ?, APJRTI= ?, APJCTP= ?, APJSTP= ?, APJCPG= ?, APJMAP= ?";
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
		String fields = "APJARF, APJFIL, APJALV, APJASQ, APJACL, APJADS, APJAFT, APJAFS, APJAFE, APJAFL, APJAFI, APJAFF, APJDCL, APJDDS, APJDFT, APJDFS, APJDFE, APJDFL, APJDFI, APJDFF, APJDIL, APJRTI, APJCTP, APJSTP, APJCPG, APJMAP ";
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
		String fields = "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
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
		APJRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getApiReference(), dataModel.getApiFileName(), dataModel.getEquationApiLevel(),
						dataModel.getApiFieldSequence(), dataModel.getApiFieldName(), dataModel.getApiFieldDescripton(),
						dataModel.getApiFieldType(), dataModel.getApiFieldStart(), dataModel.getApiFieldEnd(),
						dataModel.getApiFieldLength(), dataModel.getApiFieldIntegers(), dataModel.getApiFieldFractions(),
						dataModel.getDbFieldName(), dataModel.getDbFieldDescription(), dataModel.getDbFieldType(),
						dataModel.getDbFieldStart(), dataModel.getDbFieldEnd(), dataModel.getDbFieldLength(),
						dataModel.getDbFieldIntegers(), dataModel.getDbFieldFractions(), dataModel.getDbFileName(),
						dataModel.getRetrievalInstance(), dataModel.getControlType(), dataModel.getSubControlType(),
						dataModel.getConversionProgram(), dataModel.getMapParameter() };
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
		return getRecordBy(whereClause, new APJRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new APJRecordRowMapper());
	}

	/**
	 * This method is going to return a <code> APJRecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> APJRecordDataModel </code> base on $tablePrefix-ID
	 */
	public APJRecordDataModel getAPJRecord()
	{
		APJRecordDataModel dataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new APJRecordRowMapper());

		if (!results.isEmpty())
		{

			dataModel = (APJRecordDataModel) results.get(0);
		}

		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}