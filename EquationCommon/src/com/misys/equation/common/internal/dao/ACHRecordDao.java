package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IACHRecordDao;
import com.misys.equation.common.dao.beans.ACHRecordDataModel;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.internal.dao.mappers.ACHRecordRowMapper;

/**
 * This the ACH-Record Dao implementation. <br>
 * This class is going to provide all back-end services for ACH-Record.
 * 
 * @author deroset
 */
public class ACHRecordDao extends AbsEquationDao implements IACHRecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACHRecordDao.java 8910 2010-08-26 15:25:20Z MACDONP1 $";
	public ACHRecordDao()
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
	public boolean checkIfThisACHRecordIsInTheDB(String sqlWhereStatement)
	{

		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisACHRecordIsInTheDB()
	{

		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public ACHRecordDataModel getMyDataModel()
	{

		ACHRecordDataModel ACHRecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof ACHRecordDataModel)
		{
			ACHRecordDataModel = (ACHRecordDataModel) getRecord();

		}
		return ACHRecordDataModel;
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

		StringBuilder whereCondition = new StringBuilder("ACHRFLD='");
		whereCondition.append(getMyDataModel().getAchrfld());
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
		String fields = " ACHRFLD=?, ACHIOB=?, ACHWID=? ";
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
		String fields = " ACHRFLD, ACHIOB, ACHWID ";
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
		String fields = " ?, ?, ? ";
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
		ACHRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getAchrfld(), dataModel.getAchiob(), dataModel.getAchwid(), };

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
		return getRecordBy(whereClause, new ACHRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new ACHRecordRowMapper());
	}

	/**
	 * This method is going to return a <code>ACHRecordDataModel</code> base on ACHFID
	 * 
	 * @return a <code>ACHRecordDataModel</code> base on ACHFID
	 */
	public ACHRecordDataModel getACHRecord()
	{
		ACHRecordDataModel ACHRecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new ACHRecordRowMapper());

		if (!results.isEmpty())
		{
			ACHRecordDataModel = (ACHRecordDataModel) results.get(0);
		}

		return ACHRecordDataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}