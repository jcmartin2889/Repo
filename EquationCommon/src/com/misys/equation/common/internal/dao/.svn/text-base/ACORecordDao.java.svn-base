package com.misys.equation.common.internal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IACORecordDao;
import com.misys.equation.common.dao.beans.ACORecordDataModel;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.internal.dao.mappers.ACORecordRowMapper;

/**
 * This class is going to provide all back-end services for ACORecordDao
 * 
 */
public class ACORecordDao extends AbsEquationDao implements IACORecordDao
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACORecordDao.java 12399 2011-12-13 03:57:50Z fraramos $";
	/**
	 * This method is going execute a Sql query using the filter criteria.
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the sql filter.
	 * 
	 * @param rowMapper
	 *            this is the mapper which will populate the data-model
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		Hashtable<String, AbsRecord> results = null;

		ACORecordDataModel model;

		if (!records.isEmpty())
		{

			results = new Hashtable<String, AbsRecord>();
		}
		else
		{

			return null;
		}

		for (AbsRecord absRecord : records)
		{
			model = (ACORecordDataModel) absRecord;
			results.put(model.getUserExit(), model);
		}

		return results;
	}
	/**
	 * Helper to convert a String from a resultset to a char
	 * 
	 * @param rs
	 * @param columnIndex
	 * @return char
	 */
	public static char getResultSetChar(ResultSet rs, int columnIndex)
	{
		String temp = null;
		try
		{
			temp = rs.getString(columnIndex);
		}
		catch (SQLException e)
		{
			// Throw an unchecked exception
			throw new RuntimeException(e);
		}

		return (temp != null && temp.length() > 0) ? temp.charAt(0) : ' ';
	}
	/**
	 * Default Constructor
	 */
	public ACORecordDao()
	{
	}
	/**
	 * Returns a list of the filed's name
	 * 
	 * @return a list of the filed's name
	 */
	@Override
	protected String getFields()
	{
		String fields = " ACOPGM, ACOSCN, ACOMOD ";
		return fields;
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
		String fields = " ACOPGM= ?, ACOSCN= ?, ACOMOD= ? ";
		return fields;
	}
	/**
	 * This method create an array that contains the fields values and Its types. <br>
	 * It will be used by jdbc <code>PreparedStatement</code>
	 * 
	 * @return An array that contains the fields values and Its types.
	 */
	@Override
	protected Object[] getParameterizedFieldsValues()
	{
		ACORecordDataModel model = getMyDataModel();
		Object[] objects = new Object[] { model.getUserExit(), model.getScreen(), model.getMode() };
		return objects;
	}
	/**
	 * Returns a list of the filed's parameters
	 * 
	 * @return the list of the filed's parameters
	 */
	@Override
	protected String getParameters()
	{
		return "?, ?, ?";
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
		StringBuilder whereCondition = new StringBuilder("ACOPGM='");
		whereCondition.append(getMyDataModel().getUserExit());
		whereCondition.append("' ");
		return whereCondition.toString();
	}

	/**
	 * This method is going to return a <code>ACORecordDataModel</code>
	 * 
	 * @return a <code>ACORecordDataModel</code>
	 */
	public ACORecordDataModel getACORecord()
	{
		ACORecordDataModel model = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new ACORecordRowMapper());

		if (!results.isEmpty())
		{

			model = (ACORecordDataModel) results.get(0);
		}

		return model;
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
		return getRecordBy(whereClause, new ACORecordRowMapper());
	}
	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new ACORecordRowMapper());
	}
	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public ACORecordDataModel getMyDataModel()
	{

		ACORecordDataModel model = null;
		AbsRecord record = getRecord();

		if (record instanceof ACORecordDataModel)
		{
			model = (ACORecordDataModel) getRecord();
		}
		return model;
	}
}
