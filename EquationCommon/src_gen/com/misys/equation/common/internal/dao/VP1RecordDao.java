package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IVP1RecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.VP1RecordDataModel;
import com.misys.equation.common.internal.dao.mappers.VP1RecordRowMapper;

/**
 * This the VP1 -Record Dao implementation. <br>
 * This class is going to provide all back-end services for VP1 -Record.
 * 
 * @author deroset
 */
public class VP1RecordDao extends AbsEquationDao implements IVP1RecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1279602362948l;

	public VP1RecordDao()
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
	public boolean checkIfThisVP1RecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisVP1RecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public VP1RecordDataModel getMyDataModel()
	{
		VP1RecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof VP1RecordDataModel)
		{
			dataModel = (VP1RecordDataModel) getRecord();
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
		whereCondition.append("VPPD ='");
		whereCondition.append(getMyDataModel().getProductCode());
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
		String fields = "VPPD= ?, VPDES= ?, VPMTD= ?, VPDPM= ?, VPAV01= ?, VPAV02= ?, VPAV03= ?, VPAV04= ?, VPAV05= ?, VPAV06= ?, VPAV07= ?, VPAV08= ?, VPAV09= ?, VPAV10= ?, VPAV11= ?, VPAV12= ?";
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
		String fields = "VPPD, VPDES, VPMTD, VPDPM, VPAV01, VPAV02, VPAV03, VPAV04, VPAV05, VPAV06, VPAV07, VPAV08, VPAV09, VPAV10, VPAV11, VPAV12 ";
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
		String fields = "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
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
		VP1RecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getProductCode(), dataModel.getProductDescription(),
						dataModel.getMonthToDateVolume(), dataModel.getDaysProcThisMonth(), dataModel.getJanuarysAverageVol(),
						dataModel.getFebruarysAverageVl(), dataModel.getMarchsAverageVol(), dataModel.getAprilsAverageVol(),
						dataModel.getMaysAverageVol(), dataModel.getJunesAverageVol(), dataModel.getJulysAverageVol(),
						dataModel.getAugustsAverageVol(), dataModel.getSeptembersAverageV(), dataModel.getOctobersAverageVol(),
						dataModel.getNovembersAverageVl(), dataModel.getDecembersAverageVl() };
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
		return getRecordBy(whereClause, new VP1RecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new VP1RecordRowMapper());
	}

	/**
	 * This method is going to return a <code> VP1RecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> VP1RecordDataModel </code> base on $tablePrefix-ID
	 */
	public VP1RecordDataModel getVP1Record()
	{
		VP1RecordDataModel dataModel = null;
		List results = getRecordBy(getWhereConditionBaseOnIdRecord(), new VP1RecordRowMapper());

		if (!results.isEmpty())
		{

			dataModel = (VP1RecordDataModel) results.get(0);
		}

		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}