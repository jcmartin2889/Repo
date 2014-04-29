package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IACERecordDao;
import com.misys.equation.common.dao.beans.ACERecordDataModel;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.internal.dao.mappers.ACERecordRowMapper;

/**
 * This the ACE-Record Dao implementation. <br>
 * This class is going to provide all back-end services for ACE-Record.
 * 
 * @author deroset
 */
public class ACERecordDao extends AbsEquationDao implements IACERecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACERecordDao.java 9659 2010-11-02 17:25:07Z MACDONP1 $";
	public ACERecordDao()
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
	public boolean checkIfThisACERecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisACERecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public ACERecordDataModel getMyDataModel()
	{
		ACERecordDataModel ACERecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof ACERecordDataModel)
		{
			ACERecordDataModel = (ACERecordDataModel) getRecord();

		}
		return ACERecordDataModel;
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
		StringBuilder whereCondition = new StringBuilder("ACEWID='");
		whereCondition.append(getMyDataModel().getWidgetName());
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
		String fields = " ACEWID=?, ACEDES=?, ACEMISY=? ";
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
		String fields = " ACEWID, ACEDES, ACEMISY ";
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
		ACERecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getWidgetName(), dataModel.getDescription(), dataModel.getMisysSupplied(), };

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
		return getRecordBy(whereClause, new ACERecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new ACERecordRowMapper());
	}

	/**
	 * This method is going to return a <code>ACERecordDataModel</code> base on ACEFID
	 * 
	 * @return a <code>ACERecordDataModel</code> base on ACEFID
	 */
	public ACERecordDataModel getACERecord()
	{
		ACERecordDataModel ACERecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new ACERecordRowMapper());

		if (!results.isEmpty())
		{
			ACERecordDataModel = (ACERecordDataModel) results.get(0);
		}

		return ACERecordDataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}