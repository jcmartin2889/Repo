package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IGBRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GBRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.GBRecordRowMapper;

/**
 * This the GB-Record Dao implementation. <br>
 * This class is going to provide all back-end services for GB-Record.
 * 
 * @author deroset
 */
public class GBRecordDao extends AbsEquationDao implements IGBRecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GBRecordDao.java 4646 2009-09-07 16:28:30Z weddelc1 $";
	public GBRecordDao()
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
	public boolean checkIfThisGBRecordIsInTheDB(String sqlWhereStatement)
	{

		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGBRecordIsInTheDB()
	{

		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public GBRecordDataModel getMyDataModel()
	{

		GBRecordDataModel GBRecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof GBRecordDataModel)
		{
			GBRecordDataModel = (GBRecordDataModel) getRecord();

		}
		return GBRecordDataModel;
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
		StringBuilder whereCondition = new StringBuilder("GBOID='");
		whereCondition.append(getMyDataModel().getOptionId());
		whereCondition.append("' ");
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
		String fields = " GBOID=?, GBFPR=?, GBONM=?, GBMNR=?, GBODT=?, GBOID1=?, GBOID2=?, GBOID3=?, GBOID4=?, GBMNC=?, GBFID=?, GBRPP=?, GBOID9=?, GBOMT=?, GBEIA=?, GBAPP=?, GBWM1=? ";
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
		String fields = " GBOID, GBFPR, GBONM, GBMNR, GBODT, GBOID1, GBOID2, GBOID3, GBOID4, GBMNC, GBFID, GBRPP, GBOID9, GBOMT, GBEIA, GBAPP, GBWM1 ";
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
		String fields = " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ";
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
		GBRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getOptionId(), dataModel.getProgramName(), dataModel.getProgramTitle(),
						dataModel.getMandatoryNextReq(), dataModel.getDefEntryData(), dataModel.getUserFuncKey1(),
						dataModel.getUserFuncKey2(), dataModel.getUserFuncKey3(), dataModel.getUserFuncKey4(),
						dataModel.getPcMnem(), dataModel.getOptionIdGA(), dataModel.getRepeatProcessing(),
						dataModel.getActionBarMenu(), dataModel.getOptionType(), dataModel.getExtendedInput(),
						dataModel.getApplication(), dataModel.getGbwm1(), };

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
		return getRecordBy(whereClause, new GBRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new GBRecordRowMapper());
	}

	/**
	 * This method is going to return a <code>GBRecordDataModel</code> base on GBFID
	 * 
	 * @return a <code>GBRecordDataModel</code> base on GBFID
	 */
	public GBRecordDataModel getGBRecord()
	{
		GBRecordDataModel GBRecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new GBRecordRowMapper());

		if (!results.isEmpty())
		{

			GBRecordDataModel = (GBRecordDataModel) results.get(0);
		}

		return GBRecordDataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		Hashtable<String, AbsRecord> results = null;

		GBRecordDataModel gbRecordDataModel;

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
			gbRecordDataModel = (GBRecordDataModel) absRecord;
			results.put(gbRecordDataModel.getOptionId(), gbRecordDataModel);
		}

		return results;
	}

}
