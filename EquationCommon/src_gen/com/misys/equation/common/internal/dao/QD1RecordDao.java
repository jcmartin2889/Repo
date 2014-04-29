package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IQD1RecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.QD1RecordDataModel;
import com.misys.equation.common.internal.dao.mappers.QD1RecordRowMapper;

/**
 * This the QD1 -Record Dao implementation. <br>
 * This class is going to provide all back-end services for QD1 -Record.
 * 
 * @author deroset
 */

public class QD1RecordDao extends AbsEquationDao implements IQD1RecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1266215593042l;

	public QD1RecordDao()
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
	public boolean checkIfThisQD1RecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisQD1RecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public QD1RecordDataModel getMyDataModel()
	{
		QD1RecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof QD1RecordDataModel)
		{
			dataModel = (QD1RecordDataModel) getRecord();

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
		whereCondition.append("QDSGP ='");
		whereCondition.append(getMyDataModel().getSystemOptionGroup());
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
		String fields = "QDSGP= ?, QDSGD= ?";
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
		String fields = "QDSGP, QDSGD ";
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
		String fields = "?, ?";
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
		QD1RecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getSystemOptionGroup(), dataModel.getSystemOptionGroupDescription() };
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
		return getRecordBy(whereClause, new QD1RecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new QD1RecordRowMapper());
	}

	/**
	 * This method is going to return a <code> QD1RecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> QD1RecordDataModel </code> base on $tablePrefix-ID
	 */
	public QD1RecordDataModel getQD1Record()
	{
		QD1RecordDataModel dataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new QD1RecordRowMapper());

		if (!results.isEmpty())
		{
			dataModel = (QD1RecordDataModel) results.get(0);
		}
		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}