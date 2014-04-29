package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.misys.equation.common.dao.IC1RecordDao;
import com.misys.equation.common.dao.beans.ACMRecordDataModel;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.C1RecordDataModel;
import com.misys.equation.common.internal.dao.mappers.C1RecordRowMapper;

/**
 * This the C1-Record Dao implementation. <br>
 * This class is going to provide all back-end services for C1-Record.
 * 
 * @author eranag1
 */
public class C1RecordDao extends AbsEquationDao implements IC1RecordDao
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: C1RecordDao.java 11229 2011-06-17 12:11:19Z rpatrici $";
	public C1RecordDao()
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
	public boolean checkIfThisC1RecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisC1RecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public C1RecordDataModel getMyDataModel()
	{
		C1RecordDataModel c1RecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof ACMRecordDataModel)
		{
			c1RecordDataModel = (C1RecordDataModel) getRecord();

		}
		return c1RecordDataModel;
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
		StringBuilder whereCondition = new StringBuilder("C1LNM='");
		whereCondition.append(getMyDataModel().getLanguageMnemonic());
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
		String fields = " C1DLMD=?, C1DLMM=?, C1DLMY=?, C1ILM=?, C1KCD=?, C1LAN=?, C1LGN=?, C1MIL=?, C1ULV=?, C1LNM=?, C1YRTL=? ";
		return fields;
	}

	/**
	 * Returns a list of the field names
	 * 
	 * @return a list of the field names
	 */
	@Override
	protected String getFields()
	{
		String fields = " C1DLMD, C1DLMM, C1DLMY, C1ILM, C1KCD, C1LAN, C1LGN, C1MIL, C1ULV, C1LNM, C1YRTL ";
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
		String fields = " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ";
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
		C1RecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getDateLastMaintainedDay(), dataModel.getDateLastMaintainedMonth(),
						dataModel.getDateLastMaintainedYear(), dataModel.getIllegalMaintenanceIndicator(),
						dataModel.getNarrativeType(), dataModel.getLanguageName(), dataModel.getLanguageNumber(),
						dataModel.getIsMaintenanceIllegalOnThisField(), dataModel.getUpdateLevelNumber(),
						dataModel.getLanguageMnemonic(), dataModel.getRightToLeftTextOrientation(), };

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
		return getRecordBy(whereClause, new C1RecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{

		return getRecordBy(null, new C1RecordRowMapper());
	}

	/**
	 * This method is going to return an <code>ACMRecordDataModel</code>
	 * 
	 * @return an <code>ACMRecordDataModel</code>
	 */
	public C1RecordDataModel getC1Record()
	{
		C1RecordDataModel C1RecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new C1RecordRowMapper());

		if (!results.isEmpty())
		{
			C1RecordDataModel = (C1RecordDataModel) results.get(0);
		}

		return C1RecordDataModel;
	}

	/**
	 * This method will return a Map of C1 records
	 * 
	 * @return a map of the data model records keyed by the field id
	 */
	@SuppressWarnings("unchecked")
	public Map<String, C1RecordDataModel> getC1RecordsMap()
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<C1RecordDataModel> dataModels = null;

		sqlBuilder.append("SELECT ");
		sqlBuilder.append(getFields());
		sqlBuilder.append(" FROM ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(" ORDER BY C1LAN ASC");
		JdbcTemplate select = getJdbcTemplate();
		dataModels = select.query(sqlBuilder.toString(), new C1RecordRowMapper());
		return createHashTableModel(dataModels);
	}

	/**
	 * This is a internal helper method which create a <code>Hashtable</code> <br>
	 * which contains a record of <code>C1RecordDataModel</code>
	 * 
	 * @param dataModels
	 *            record of <code>C1RecordDataModel</code>.
	 * @return a <code>Hashtable</code> which contains a <code>C1RecordDataModel</code> objects.
	 */
	private Hashtable<String, C1RecordDataModel> createHashTableModel(List<C1RecordDataModel> dataModels)
	{
		Hashtable<String, C1RecordDataModel> modelTable = new Hashtable<String, C1RecordDataModel>();

		for (C1RecordDataModel record : dataModels)
		{
			modelTable.put(record.getLanguageMnemonic(), record);
		}

		return modelTable;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}
