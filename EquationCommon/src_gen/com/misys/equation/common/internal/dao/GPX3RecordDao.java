package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IGPX3RecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GPX3RecordDataModel;
import com.misys.equation.common.internal.dao.mappers.GPX3RecordRowMapper;

/**
 * This the GPX3 -Record Dao implementation. <br>
 * This class is going to provide all back-end services for GPX3 -Record.
 * 
 * @author deroset
 */

public class GPX3RecordDao extends AbsEquationDao implements IGPX3RecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1258630647122l;

	public GPX3RecordDao()
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
	public boolean checkIfThisGPX3RecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGPX3RecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public GPX3RecordDataModel getMyDataModel()
	{
		GPX3RecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof GPX3RecordDataModel)
		{
			dataModel = (GPX3RecordDataModel) getRecord();

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
		whereCondition.append("GPXBMN ='");
		whereCondition.append(getMyDataModel().getBranchMnemonic());
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
		String fields = "GPXGMN= ?, GPXUNC= ?, GPXSEQ= ?, GPXSYS= ?, GPXUNT= ?, GPXBBN= ?, GPXBMN= ?";
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
		String fields = "GPXGMN, GPXUNC, GPXSEQ, GPXSYS, GPXUNT, GPXBBN, GPXBMN ";
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
		String fields = "?, ?, ?, ?, ?, ?, ?";
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
		GPX3RecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getGroupMnemonic(), dataModel.getUnitMnemonic(), dataModel.getUnitSequence(),
						dataModel.getSystemName(), dataModel.getUnitType(), dataModel.getBranchNumber(),
						dataModel.getBranchMnemonic() };
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
		return getRecordBy(whereClause, new GPX3RecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new GPX3RecordRowMapper());
	}

	/**
	 * This method is going to return a <code> GPX3RecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> GPX3RecordDataModel </code> base on $tablePrefix-ID
	 */
	public GPX3RecordDataModel getGPX3Record()
	{
		GPX3RecordDataModel dataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new GPX3RecordRowMapper());

		if (!results.isEmpty())
		{
			dataModel = (GPX3RecordDataModel) results.get(0);
		}

		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}