package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IGAWRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAWRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.GAWRecordRowMapper;

/**
 * This the GAW -Record Dao implementation. <br>
 * This class is going to provide all back-end services for GAW -Record.
 * 
 * @author deroset
 */
public class GAWRecordDao extends AbsEquationDao implements IGAWRecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1280458524832l;

	public GAWRecordDao()
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
	public boolean checkIfThisGAWRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGAWRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public GAWRecordDataModel getMyDataModel()
	{
		GAWRecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof GAWRecordDataModel)
		{
			dataModel = (GAWRecordDataModel) getRecord();

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
		whereCondition.append("GAWTYP ='");
		whereCondition.append(getMyDataModel().getRecordType());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GAWSEQ ='");
		whereCondition.append(getMyDataModel().getSequenceId());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GAWGYUS ='");
		whereCondition.append(getMyDataModel().getUserId());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GAWGYWS ='");
		whereCondition.append(getMyDataModel().getWorkstationId());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GAWGYDI ='");
		whereCondition.append(getMyDataModel().getDayInMonth());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GAWGYTI ='");
		whereCondition.append(getMyDataModel().getTimeHhmmss());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GAWGYSQ ='");
		whereCondition.append(getMyDataModel().getSequenceNumber());
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
		String fields = "GAWTYP= ?, GAWSEQ= ?, GAWGYUS= ?, GAWGYWS= ?, GAWGYDI= ?, GAWGYTI= ?, GAWGYSQ= ?";
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
		String fields = "GAWTYP, GAWSEQ, GAWGYUS, GAWGYWS, GAWGYDI, GAWGYTI, GAWGYSQ ";
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
	 * It will be used by JDBC <code>PreparedStatement</code>
	 * 
	 * @return An array that contains the field values and their types.
	 */
	@Override
	public Object[] getParameterizedFieldsValues()
	{
		GAWRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getRecordType(), dataModel.getSequenceId(), dataModel.getUserId(),
						dataModel.getWorkstationId(), dataModel.getDayInMonth(), dataModel.getTimeHhmmss(),
						dataModel.getSequenceNumber() };
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
		return getRecordBy(whereClause, new GAWRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new GAWRecordRowMapper());
	}

	/**
	 * This method is going to return a <code> GAWRecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> GAWRecordDataModel </code> base on $tablePrefix-ID
	 */
	public GAWRecordDataModel getGAWRecord()
	{
		GAWRecordDataModel dataModel = null;
		List results = getRecordBy(getWhereConditionBaseOnIdRecord(), new GAWRecordRowMapper());

		if (!results.isEmpty())
		{

			dataModel = (GAWRecordDataModel) results.get(0);
		}

		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}