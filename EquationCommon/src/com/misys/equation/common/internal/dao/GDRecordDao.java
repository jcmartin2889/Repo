package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.misys.equation.common.dao.IGDRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GDRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.GDRecordRowMapper;

/**
 * This the GD-Record Dao implementation. <br>
 * This class is going to provide all back-end services for GD-Record.
 * 
 * @author deroset
 */
public class GDRecordDao extends AbsEquationDao implements IGDRecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GDRecordDao.java 9725 2010-11-08 12:34:45Z MACDONP1 $";
	// defualt constructor
	public GDRecordDao()
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
	public boolean checkIfThisGDRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGDRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public GDRecordDataModel getMyDataModel()
	{
		GDRecordDataModel gdRecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof GDRecordDataModel)
		{
			gdRecordDataModel = (GDRecordDataModel) getRecord();

		}
		return gdRecordDataModel;
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
		StringBuilder whereCondition = new StringBuilder("GDUSID='");
		whereCondition.append(getMyDataModel().getUserId());
		whereCondition.append("' and GDOID='");
		whereCondition.append(getMyDataModel().getOptionId());
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
		String fields = " GDUSID=?, GDOID=? ";
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
		String fields = " GDUSID, GDOID ";
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
		String fields = " ? , ? ";
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
		GDRecordDataModel gdRecordDataModel = getMyDataModel();
		Object[] object = new Object[] { gdRecordDataModel.getUserId(), gdRecordDataModel.getOptionId() };

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
		return getRecordBy(whereClause, new GDRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new GDRecordRowMapper());
	}

	/**
	 * This method is going to return a <code>GDRecordDataModel</code> base on GDFID
	 * 
	 * @return a <code>GDRecordDataModel</code> base on GDUSID + GDFID
	 */
	public GDRecordDataModel getGDRecord()
	{
		GDRecordDataModel gDRecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new GDRecordRowMapper());

		if (!results.isEmpty())
		{

			gDRecordDataModel = (GDRecordDataModel) results.get(0);
		}

		return gDRecordDataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}

	/**
	 * file specific implementation to build a typed list of data models
	 * 
	 * @param records
	 *            List of <code>AbsRecord</code>s
	 * 
	 * @return a Map which stores GD record models, keyed by equation user id and function id
	 */
	private Map<String, GDRecordDataModel> createHashTableModel(List<AbsRecord> records)
	{
		Map<String, GDRecordDataModel> modelTable = new Hashtable<String, GDRecordDataModel>();

		for (AbsRecord record : records)
		{
			GDRecordDataModel model = (GDRecordDataModel) record;
			modelTable.put(model.getUserId() + model.getOptionId(), model);
		}

		return modelTable;
	}

	/**
	 * This method is going to execute a SQL query using the filter criteria.
	 * <p>
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the sql filter. This must be null if there is no filter
	 * @return a <code>Map</code> which contains a record of <code>GDRecordDataModel</code>
	 */
	public Map<String, GDRecordDataModel> getGDRecordBy(String whereClause)
	{

		List<AbsRecord> results = getRecordBy(whereClause, new GDRecordRowMapper());
		return createHashTableModel(results);
	}

}
