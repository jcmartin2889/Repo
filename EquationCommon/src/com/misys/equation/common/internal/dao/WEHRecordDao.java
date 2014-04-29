package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.misys.equation.common.dao.IWEHRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.WEHRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.WEHRecordRowMapper;
import com.misys.equation.common.internal.dao.mappers.WEHRecordSequenceNumber;

/**
 * This the WEH -Record Dao implementation. <br>
 * This class is going to provide all back-end services for WEH -Record.
 * 
 * @author deroset
 */ 
public class WEHRecordDao extends AbsEquationDao implements IWEHRecordDao
{	
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WEHRecordDao.java 13101 2012-03-28 06:15:02Z bernie.terrado $";
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1323183018166l;
	
	public WEHRecordDao()
	{		
		super();
	}
	
		/**
	 * This method will check if the current record is already in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed. 
	 * 
	 * @param sqlWhereStatement -
	 *            the WHERE clause of the SQL statement
	 * 
	 * @return true if the SQL statement returns a count >= 1 
	 */
	public boolean checkIfThisWEHRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}
	
	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1 
	 */
	public boolean checkIfThisWEHRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * @return an instance of the preset data model.
	 */
	public WEHRecordDataModel getMyDataModel()
	{
		WEHRecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof WEHRecordDataModel)
		{
			dataModel = (WEHRecordDataModel) getRecord();

		}
		return dataModel;
	}

	/**
	 * This method will return a <code>String</code> which represents and sql  
	 * where condition base on the record id.<br> 
	 * For example <code>id = getDataModel.getId()</code>
	 * @return a <code>String</code> which represents and sql filter.
	 */
	protected String getWhereConditionBaseOnIdRecord()
	{
		StringBuilder whereCondition= new StringBuilder(1024);
		whereCondition.append("WEHUID ='");
		whereCondition.append(getMyDataModel().getMaker() );
		whereCondition.append("'");
		whereCondition.append(" and ");
		
		whereCondition.append("WEHAUID ='");
		whereCondition.append(getMyDataModel().getChecker() );
		whereCondition.append("'");
		whereCondition.append(" and ");
		
		whereCondition.append("WEHSID ='");
		whereCondition.append(getMyDataModel().getSessionId() );
		whereCondition.append("'");
		whereCondition.append(" and ");
		
		whereCondition.append("WEHTID ='");
		whereCondition.append(getMyDataModel().getTransactionId() );
		whereCondition.append("'");
		whereCondition.append(" and ");
		
		whereCondition.append("WEHSEQ =");
		whereCondition.append(getMyDataModel().getSequence());
		whereCondition.append("");
		
		return whereCondition.toString();
	}

	/**
	 * Returns a list of name and ? pairs in the format of:
	 * <p>
	 * field1=?, field2=?, field3=?, ...
	 * 
	 * @return a  list of name and ? pairs
	 */
	protected String getParameterizedFields()
	{
		String fields = "WEHUID= ?, WEHAUID= ?, WEHSID= ?, WEHTID= ?, WEHSEQ= ?, WEHOID= ?, WEHASTS= ?, WEHDTE= ?, WEHTIM= ?, WEHRTXT= ?, WEHBRNM= ?, WEHCUSN= ?, WEHACC1= ?, WEHREF1= ?, WEHACC2= ?, WEHREF2= ?";
		return fields;
	}

	/**
	 * Returns a list of the filed's name
	 * @return a list of the filed's name
	 */
	protected String getFields()
	{	
		String fields = "WEHUID, WEHAUID, WEHSID, WEHTID, WEHSEQ, WEHOID, WEHASTS, WEHDTE, WEHTIM, WEHRTXT, WEHBRNM, WEHCUSN, WEHACC1, WEHREF1, WEHACC2, WEHREF2 ";
		return fields;
	}

	/**
	 * Returns a list of the filed's parameters
	 * @return the list of the filed's parameters
	 */
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
	public Object[] getParameterizedFieldsValues()
	{
		WEHRecordDataModel dataModel = getMyDataModel();
		
		Object[] object = new Object[] {
			dataModel.getMaker(),
			dataModel.getChecker(),	
			dataModel.getSessionId(),
			dataModel.getTransactionId(),
			dataModel.getSequence(),
			dataModel.getOptionId(),
			dataModel.getStatus(),
			dataModel.getProcessedDate(),
			dataModel.getProcessedTime(),
			dataModel.getReason(),
			dataModel.getBranch(),
			dataModel.getCustomerNumber(),
			dataModel.getAccount(),
			dataModel.getReference(),
			dataModel.getSecondAccount(),
			dataModel.getAdditionalRef()
		};
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
		return getRecordBy(whereClause, new WEHRecordRowMapper());
	}
	
	
	/**
	 * This method is going to get all records.
	 * @return a <code>List</code> that contains the records 
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new WEHRecordRowMapper());
	}
	
		/**	  
	 * This method is going to return a <code> WEHRecordDataModel </code> base on $tablePrefix-ID   
	 * @return  a <code> WEHRecordDataModel </code> base on $tablePrefix-ID
	 */
	public WEHRecordDataModel getWEHRecord()
	{
		WEHRecordDataModel dataModel = null;
		List results = getRecordBy(getWhereConditionBaseOnIdRecord(), new WEHRecordRowMapper());

		if (!results.isEmpty())
		{

			dataModel = (WEHRecordDataModel) results.get(0);
		}

		return dataModel;
	}
	
	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Retrieve the last sequence number of Maker Checker History file
	 */
	public int getLastSequenceNumber()
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);

		sqlBuilder.append("SELECT max(WEHSEQ)");
		sqlBuilder.append(" FROM ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(" WHERE ");
		sqlBuilder.append("WEHOID ='");
		sqlBuilder.append(getMyDataModel().getOptionId() );
		sqlBuilder.append("'");
		sqlBuilder.append(" and ");

		sqlBuilder.append("WEHTID ='");
		sqlBuilder.append(getMyDataModel().getTransactionId() );
		sqlBuilder.append("'");
		sqlBuilder.append(" and ");
		
		sqlBuilder.append("WEHSID ='");
		sqlBuilder.append(getMyDataModel().getSessionId() );
		sqlBuilder.append("'");

		WEHRecordDataModel dataModel = getRecord(sqlBuilder.toString(), new WEHRecordSequenceNumber());
		if (dataModel != null)
		{
			return dataModel.getSequence();
		}
		else
		{
			return 0;
		}
	}
	
	/**
	 * Retrieves a WEH record
	 * 
	 * @param sql static SQL
	 * @param rowMapper Mapping rows of a ResultSet on a per-row basis
	 * @return WEH record 
	 */
	private WEHRecordDataModel getRecord(String sql, RowMapper rowMapper)
	{
		JdbcTemplate select = getJdbcTemplate();

		if (LOG.isDebugEnabled())
		{
			LOG.debug(new StringBuilder("The executed sql is: ").append(sql).toString());
		}

		List<AbsRecord> dataModels = select.query(sql, rowMapper);

		if (!dataModels.isEmpty())
		{
			return (WEHRecordDataModel) dataModels.get(0);
		}
		
		return null;
	}
	
}
