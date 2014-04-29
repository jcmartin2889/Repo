package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IOC2RecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.OC2RecordDataModel;
import com.misys.equation.common.internal.dao.mappers.OC2RecordRowMapper;

/**
 * This the OC2-Record Dao implementation. <br>
 * This class is going to provide all back-end services for OC2-Record.
 * 
 * @author deroset
 */
public class OC2RecordDao extends AbsEquationDao implements IOC2RecordDao
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OC2RecordDao.java 9725 2010-11-08 12:34:45Z MACDONP1 $";
	public OC2RecordDao()
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
	public boolean checkIfThisOC2RecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisOC2RecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public OC2RecordDataModel getMyDataModel()
	{
		OC2RecordDataModel OC2RecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof OC2RecordDataModel)
		{
			OC2RecordDataModel = (OC2RecordDataModel) getRecord();

		}
		return OC2RecordDataModel;
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
		StringBuilder whereCondition = new StringBuilder("OCUSID='");
		whereCondition.append(getMyDataModel().getUserId());
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
		String fields = " OCUSID=?, OCPWD=?, OCPWDM=? ";
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
		String fields = " OCUSID, OCPWD, OCPWDM ";
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
		OC2RecordDataModel dataModel = getMyDataModel();
		Object[] object = new Object[] { dataModel.getUserId(), dataModel.getPassword(), dataModel.getPwdDate(), };
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
		return getRecordBy(whereClause, new OC2RecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new OC2RecordRowMapper());
	}

	/**
	 * This method is going to return a <code>OC2RecordDataModel</code> base on OC2FID
	 * 
	 * @return a <code>OC2RecordDataModel</code> base on OC2FID
	 */
	public OC2RecordDataModel getOC2Record()
	{
		OC2RecordDataModel OC2RecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new OC2RecordRowMapper());

		if (!results.isEmpty())
		{
			OC2RecordDataModel = (OC2RecordDataModel) results.get(0);
		}
		return OC2RecordDataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}