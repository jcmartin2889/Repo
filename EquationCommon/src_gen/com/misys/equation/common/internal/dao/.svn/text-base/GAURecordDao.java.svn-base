package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IGAURecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAURecordDataModel;
import com.misys.equation.common.internal.dao.mappers.GAURecordRowMapper;

/**
 * This the GAU -Record Dao implementation. <br>
 * This class is going to provide all back-end services for GAU -Record.
 * 
 * @author deroset
 */
public class GAURecordDao extends AbsEquationDao implements IGAURecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1275047167493l;

	public GAURecordDao()
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
	public boolean checkIfThisGAURecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGAURecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public GAURecordDataModel getMyDataModel()
	{
		GAURecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof GAURecordDataModel)
		{
			dataModel = (GAURecordDataModel) getRecord();

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
		whereCondition.append("GAUSEQ ='");
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
		String fields = "GAUSEQ= ?, GAUWSID= ?, GAUUSID= ?, GAUTSTP= ?, GAUSO= ?, GAUUNSO= ?, GAUSVSO= ?, GAUPDTE= ?, GAUBRNM= ?, GAUOID= ?, GAURTYP= ?, GAUREF= ?, GAUSTS= ?, GAUACK= ?, GAUUNTO= ?, GAUSVTO= ?, GAUDSEQ= ?, GAUUSEQ= ?, GAUGLO= ?";
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
		String fields = "GAUSEQ, GAUWSID, GAUUSID, GAUTSTP, GAUSO, GAUUNSO, GAUSVSO, GAUPDTE, GAUBRNM, GAUOID, GAURTYP, GAUREF, GAUSTS, GAUACK, GAUUNTO, GAUSVTO, GAUDSEQ, GAUUSEQ, GAUGLO ";
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
		String fields = "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
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
		GAURecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getSequenceId(), dataModel.getSessionId(), dataModel.getUser(),
						dataModel.getAuditTimestamp(), dataModel.getSource(), dataModel.getSourceUnit(),
						dataModel.getSourceServer(), dataModel.getProcessingDate(), dataModel.getUnitDefaultBranch(),
						dataModel.getOptionId(), dataModel.getReferenceDetailsType(), dataModel.getReferenceDetails(),
						dataModel.getStatus(), dataModel.getAcknowledgedStatus(), dataModel.getToUnit(), dataModel.getToServer(),
						dataModel.getPropDataSequenceNumber(), dataModel.getUnitDataSequenceNumber(),
						dataModel.getGlobalLayerStatus() };
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
		return getRecordBy(whereClause, new GAURecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new GAURecordRowMapper());
	}

	/**
	 * This method is going to return a <code> GAURecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> GAURecordDataModel </code> base on $tablePrefix-ID
	 */
	public GAURecordDataModel getGAURecord()
	{
		GAURecordDataModel dataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new GAURecordRowMapper());

		if (!results.isEmpty())
		{

			dataModel = (GAURecordDataModel) results.get(0);
		}

		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}