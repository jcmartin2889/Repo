package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IWECRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.WECRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.WECRecordRowMapper;
import com.misys.equation.common.utilities.EquationLogger;

/**
 * This the WEC -Record Dao implementation. <br>
 * This class is going to provide all back-end services for WEC -Record.
 * 
 * @author deroset
 */
public class WECRecordDao extends AbsEquationDao implements IWECRecordDao
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WECRecordDao.java 13102 2012-03-28 06:22:08Z bernie.terrado $";
	private static final EquationLogger LOG = new EquationLogger(WECRecordDao.class);

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1323183017791l;

	public WECRecordDao()
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
	public boolean checkIfThisWECRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisWECRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public WECRecordDataModel getMyDataModel()
	{
		WECRecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof WECRecordDataModel)
		{
			dataModel = (WECRecordDataModel) getRecord();

		}
		return dataModel;
	}

	/**
	 * This method will return a <code>String</code> which represents and sql where condition base on the record id.<br>
	 * For example <code>id = getDataModel.getId()</code>
	 * 
	 * @return a <code>String</code> which represents and sql filter.
	 */
	protected String getWhereConditionBaseOnIdRecord()
	{
		StringBuilder whereCondition = new StringBuilder(1024);
		whereCondition.append("WECOID ='");
		whereCondition.append(getMyDataModel().getOptionMnemonic());
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
	protected String getParameterizedFields()
	{
		String fields = "WECOID= ?, WECRMC= ?, WECCBC= ?";
		return fields;
	}

	/**
	 * Returns a list of the filed's name
	 * 
	 * @return a list of the filed's name
	 */
	protected String getFields()
	{
		String fields = "WECOID, WECRMC, WECCBC ";
		return fields;
	}

	/**
	 * Returns a list of the filed's parameters
	 * 
	 * @return the list of the filed's parameters
	 */
	protected String getParameters()
	{
		String fields = "?, ?, ? ";
		return fields;
	}

	/**
	 * This method create an array that contains the fields values and Its types. <br>
	 * It will be used by JDBC <code>PreparedStatement</code>
	 * 
	 * @return An array that contains the field values and their types.
	 */
	public Object[] getParameterizedFieldsValues()
	{
		WECRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getOptionMnemonic(), dataModel.getRequiresMakerChecker(),
						dataModel.getCompleteByChecker() };
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
		return getRecordBy(whereClause, new WECRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new WECRecordRowMapper());
	}

	/**
	 * This method is going to return a <code> WECRecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> WECRecordDataModel </code> base on $tablePrefix-ID
	 */
	public WECRecordDataModel getWECRecord()
	{
		WECRecordDataModel dataModel = null;
		List results = getRecordBy(getWhereConditionBaseOnIdRecord(), new WECRecordRowMapper());

		if (!results.isEmpty())
		{

			dataModel = (WECRecordDataModel) results.get(0);
		}

		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		Hashtable<String, AbsRecord> results = null;

		WECRecordDataModel wecRecordDataModel;

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
			wecRecordDataModel = (WECRecordDataModel) absRecord;
			results.put(wecRecordDataModel.getOptionMnemonic(), wecRecordDataModel);
		}

		return results;
	}
}
