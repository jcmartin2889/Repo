package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IBTRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.BTRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.BTRecordRowMapper;

/**
 * This the BTPF Dao implementation. <br>
 * This class is going to provide all back-end services for GWYPF.
 * 
 */
public class BTRecordDao extends AbsEquationReadOnlyDao implements IBTRecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BTRecordDao.java 14832 2012-11-05 19:03:33Z williae1 $";

	public BTRecordDao()
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
	public boolean checkIfThisBTRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisBTRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public BTRecordDataModel getMyDataModel()
	{
		BTRecordDataModel BTRecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof BTRecordDataModel)
		{
			BTRecordDataModel = (BTRecordDataModel) getRecord();

		}
		return BTRecordDataModel;
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
		StringBuilder whereCondition = new StringBuilder("BTEQF='");
		whereCondition.append(getMyDataModel().getParameterFile());
		whereCondition.append("' and ");

		whereCondition.append("BTCFL='");
		whereCondition.append(getMyDataModel().getParameterValue());
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
		String fields = " BTEQF=?, BTCFL=?, BTIGR=? ";
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
		String fields = " BTEQF, BTCFL, BTIGR ";
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
		String fields = " ?, ?, ?";
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
		BTRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getParameterFile(), dataModel.getParameterValue(),
						dataModel.getInformationGroup(), };

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
		return getRecordBy(whereClause, new BTRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{

		return getRecordBy(null, new BTRecordRowMapper());
	}

	/**
	 * This method is going to return a <code>BTRecordDataModel</code> base on SessionFID
	 * 
	 * @return a <code>BTRecordDataModel</code> base on SessionFID
	 */
	public BTRecordDataModel getBTRecord()
	{
		BTRecordDataModel btRecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new BTRecordRowMapper());

		if (!results.isEmpty())
		{
			btRecordDataModel = (BTRecordDataModel) results.get(0);
		}

		return btRecordDataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}

}
