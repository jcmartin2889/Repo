package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IGLURecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GLURecordDataModel;
import com.misys.equation.common.internal.dao.mappers.GLURecordRowMapper;

/**
 * This the GLU -Record Dao implementation. <br>
 * This class is going to provide all back-end services for GLU -Record.
 * 
 * @author deroset
 */

public class GLURecordDao extends AbsEquationDao implements IGLURecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1258127204371l;

	public GLURecordDao()
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
	public boolean checkIfThisGLURecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGLURecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public GLURecordDataModel getMyDataModel()
	{
		GLURecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof GLURecordDataModel)
		{
			dataModel = (GLURecordDataModel) getRecord();

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
		StringBuilder whereCondition = new StringBuilder();
		whereCondition.append("GLUMNM ='");
		whereCondition.append(getMyDataModel().getUnitMnemonic());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GLUSYS ='");
		whereCondition.append(getMyDataModel().getSystemID());
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
		String fields = "GLUMNM= ?, GLUSYS= ?, GLUDES= ?, GLUEQL= ?, GLUDTPS= ?";
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
		String fields = "GLUMNM, GLUSYS, GLUDES, GLUEQL, GLUDTPS ";
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
		String fields = "?, ?, ?, ?, ?";
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
		GLURecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getUnitMnemonic(), dataModel.getSystemID(), dataModel.getUnitDescription(),
						dataModel.getEquationLevel(), dataModel.getDesktopSupported() };
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
		return getRecordBy(whereClause, new GLURecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new GLURecordRowMapper());
	}

	/**
	 * This method is going to return a <code> GLURecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> GLURecordDataModel </code> base on $tablePrefix-ID
	 */
	public GLURecordDataModel getGLURecord()
	{
		GLURecordDataModel dataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new GLURecordRowMapper());

		if (!results.isEmpty())
		{
			dataModel = (GLURecordDataModel) results.get(0);
		}

		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}
