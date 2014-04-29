package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IEDMRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.EDMRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.EDMRecordRowMapper;

/**
 * This the EDM -Record Dao implementation. <br>
 * This class is going to provide all back-end services for EDM -Record.
 * 
 * @author deroset
 */
public class EDMRecordDao extends AbsEquationDao implements IEDMRecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1285137724099l;

	public EDMRecordDao()
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
	public boolean checkIfThisEDMRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisEDMRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public EDMRecordDataModel getMyDataModel()
	{
		EDMRecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof EDMRecordDataModel)
		{
			dataModel = (EDMRecordDataModel) getRecord();

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
		whereCondition.append("EDMSTRT ='");
		whereCondition.append(getMyDataModel().getStartTime());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("EDMPHS ='");
		whereCondition.append(getMyDataModel().getPhase());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("EDMPRC ='");
		whereCondition.append(getMyDataModel().getProcess());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("EDMPRCD ='");
		whereCondition.append(getMyDataModel().getProcessDescription());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("EDMSTS ='");
		whereCondition.append(getMyDataModel().getProcessStatus());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("EDMJBD ='");
		whereCondition.append(getMyDataModel().getJobName());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("EDMJBN ='");
		whereCondition.append(getMyDataModel().getJobNumber());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("EDMUSR ='");
		whereCondition.append(getMyDataModel().getJobUser());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("EDMTYP ='");
		whereCondition.append(getMyDataModel().getType());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("EDMID ='");
		whereCondition.append(getMyDataModel().getIdentifier());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("EDMPDT ='");
		whereCondition.append(getMyDataModel().getProcessingDate());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("EDMENDT ='");
		whereCondition.append(getMyDataModel().getEndTime());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("EDMSTSD ='");
		whereCondition.append(getMyDataModel().getSubStatusDescription());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("EDMMES ='");
		whereCondition.append(getMyDataModel().getMessage());
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
		String fields = "EDMSTRT= ?, EDMPHS= ?, EDMPRC= ?, EDMPRCD= ?, EDMSTS= ?, EDMJBD= ?, EDMJBN= ?, EDMUSR= ?, EDMTYP= ?, EDMID= ?, EDMPDT= ?, EDMENDT= ?, EDMSTSD= ?, EDMMES= ?";
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
		String fields = "EDMSTRT, EDMPHS, EDMPRC, EDMPRCD, EDMSTS, EDMJBD, EDMJBN, EDMUSR, EDMTYP, EDMID, EDMPDT, EDMENDT, EDMSTSD, EDMMES ";
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
		String fields = "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
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
		EDMRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getStartTime(), dataModel.getPhase(), dataModel.getProcess(),
						dataModel.getProcessDescription(), dataModel.getProcessStatus(), dataModel.getJobName(),
						dataModel.getJobNumber(), dataModel.getJobUser(), dataModel.getType(), dataModel.getIdentifier(),
						dataModel.getProcessingDate(), dataModel.getEndTime(), dataModel.getSubStatusDescription(),
						dataModel.getMessage() };
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
		return getRecordBy(whereClause, new EDMRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new EDMRecordRowMapper());
	}

	/**
	 * This method is going to return a <code> EDMRecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> EDMRecordDataModel </code> base on $tablePrefix-ID
	 */
	public EDMRecordDataModel getEDMRecord()
	{
		EDMRecordDataModel dataModel = null;
		List results = getRecordBy(getWhereConditionBaseOnIdRecord(), new EDMRecordRowMapper());

		if (!results.isEmpty())
		{

			dataModel = (EDMRecordDataModel) results.get(0);
		}

		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}