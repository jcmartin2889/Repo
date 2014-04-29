package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IGAVRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAVRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.GAVRecordRowMapper;

/**
 * This the GAV -Record Dao implementation. <br>
 * This class is going to provide all back-end services for GAV -Record.
 * 
 * @author deroset
 */
public class GAVRecordDao extends AbsEquationDao implements IGAVRecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1275040799004l;

	public GAVRecordDao()
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
	public boolean checkIfThisGAVRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGAVRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public GAVRecordDataModel getMyDataModel()
	{
		GAVRecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof GAVRecordDataModel)
		{
			dataModel = (GAVRecordDataModel) getRecord();

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
		whereCondition.append("GAVDSEQ ='");
		whereCondition.append(getMyDataModel().getSequenceId());
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
		String fields = "GAVDSEQ= ?, GAVCCS= ?, GAVUNSO= ?, GAVSVSO= ?, GAVGYUS= ?, GAVGYWS= ?, GAVGYDI= ?, GAVGYTI= ?, GAVGYSQ= ?, GAVJTT= ?, GAVGZIM= ?, GAVGSIM= ?, GAVAPI= ?, GAVPSID= ?, GAVPRID= ?, GAVCOND= ?, GAVFLD= ?";
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
		String fields = "GAVDSEQ, GAVCCS, GAVUNSO, GAVSVSO, GAVGYUS, GAVGYWS, GAVGYDI, GAVGYTI, GAVGYSQ, GAVJTT, GAVGZIM, GAVGSIM, GAVAPI, GAVPSID, GAVPRID, GAVCOND, GAVFLD ";
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
		String fields = "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
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
		GAVRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getSequenceId(), dataModel.getSourceUnitCcsid(), dataModel.getSourceUnit(),
						dataModel.getSourceServerId(), dataModel.getUserId(), dataModel.getWorkstationId(),
						dataModel.getDayInMonth(), dataModel.getTimeHhmmss(), dataModel.getSequenceNumber(), dataModel.getType(),
						dataModel.getSourceGzImage(), dataModel.getSourceGsImage(), dataModel.getApiFormat(),
						dataModel.getPropagationSetId(), dataModel.getPropagationRuleId(), dataModel.getConditions(),
						dataModel.getExcludedFields() };
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
		return getRecordBy(whereClause, new GAVRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new GAVRecordRowMapper());
	}

	/**
	 * This method is going to return a <code> GAVRecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> GAVRecordDataModel </code> base on $tablePrefix-ID
	 */
	public GAVRecordDataModel getGAVRecord()
	{
		GAVRecordDataModel dataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new GAVRecordRowMapper());

		if (!results.isEmpty())
		{
			dataModel = (GAVRecordDataModel) results.get(0);
		}

		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}