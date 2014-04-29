package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IIPVRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.IPVRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.IPVRecordRowMapper;

/**
 * This the IPV Record Dao implementation. <br>
 * 
 * This class is going to provide all back-end services for IPV Record.
 * 
 */
public class IPVRecordDao extends AbsEquationDao implements IIPVRecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1278481551853l;

	public IPVRecordDao()
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
	public boolean checkIfThisIPVRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisIPVRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public IPVRecordDataModel getMyDataModel()
	{
		IPVRecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof IPVRecordDataModel)
		{
			dataModel = (IPVRecordDataModel) getRecord();

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
		whereCondition.append("IPVXREF ='");
		whereCondition.append(getMyDataModel().getReferenceId());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("IPVAPP ='");
		whereCondition.append(getMyDataModel().getApplicationId());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("IPVSQN =");
		whereCondition.append(getMyDataModel().getSequence());
		whereCondition.append(" ");

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
		String fields = "IPVXREF=?, IPVAPP=?, IPVSQN=?, IPVCDT=?, IPVCLTM=?, IPVCSEQ=?, IPVJOB=?, IPVUSID=?, IPVOPT=?, IPVPDTE=?, IPVEXP=?, IPVRSP=?";
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
		String fields = "IPVXREF, IPVAPP, IPVSQN, IPVCDT, IPVCLTM, IPVCSEQ, IPVJOB, IPVUSID, IPVOPT, IPVPDTE, IPVEXP, IPVRSP ";
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
		String fields = "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ";
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
		IPVRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getReferenceId(), dataModel.getApplicationId(), dataModel.getSequence(),
						dataModel.getCreateDate(), dataModel.getCcLinkTime(), dataModel.getCcLinkSeqNo(), dataModel.getJobNumber(),
						dataModel.getUserId(), dataModel.getOptionId(), dataModel.getProcessedDate(), dataModel.getExpiryDate(),
						dataModel.getResponse() };
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
		return getRecordBy(whereClause, new IPVRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new IPVRecordRowMapper());
	}

	/**
	 * This method is going to return a <code> IPVRecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> IPVRecordDataModel </code> base on $tablePrefix-ID
	 */
	public IPVRecordDataModel getIPVRecord()
	{
		IPVRecordDataModel dataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new IPVRecordRowMapper());

		if (!results.isEmpty())
		{
			dataModel = (IPVRecordDataModel) results.get(0);
		}

		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		Hashtable<String, AbsRecord> results = null;

		IPVRecordDataModel dataModel;

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
			dataModel = (IPVRecordDataModel) absRecord;
			results.put(dataModel.getReferenceId() + dataModel.getApplicationId(), dataModel);
		}

		return results;
	}
}