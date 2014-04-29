package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IBFRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.BFRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.BFRecordRowMapper;

/**
 * This the BFPF Dao implementation. <br>
 * This class is going to provide all back-end services for GWYPF.
 * 
 */
public class BFRecordDao extends AbsEquationDao implements IBFRecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BFRecordDao.java 14832 2012-11-05 19:03:33Z williae1 $";

	public BFRecordDao()
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
	public boolean checkIfThisBFRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisBFRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public BFRecordDataModel getMyDataModel()
	{
		BFRecordDataModel BFRecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof BFRecordDataModel)
		{
			BFRecordDataModel = (BFRecordDataModel) getRecord();

		}
		return BFRecordDataModel;
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
		StringBuilder whereCondition = new StringBuilder("BFCIGR='");
		whereCondition.append(getMyDataModel().getInformationGroup());
		whereCondition.append("' and ");

		whereCondition.append("BFCITM=");
		whereCondition.append(getMyDataModel().getItemName());
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
		String fields = " BFEQF=?, BFCIGR=?, BFINIG=?, BFCITM=? ";
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
		String fields = " BFEQF, BFCIGR, BFINIG, BFCITM ";
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
		String fields = " ?, ?, ?, ?";
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
		BFRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getEquationFile(), dataModel.getInformationGroup(),
						dataModel.getItemGroupNumber(), dataModel.getItemName(), };

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
		return getRecordBy(whereClause, new BFRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{

		return getRecordBy(null, new BFRecordRowMapper());
	}

	/**
	 * This method is going to return a <code>BFRecordDataModel</code> base on SessionFID
	 * 
	 * @return a <code>BFRecordDataModel</code> base on SessionFID
	 */
	public BFRecordDataModel getBFRecord()
	{
		BFRecordDataModel bfRecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new BFRecordRowMapper());

		if (!results.isEmpty())
		{
			bfRecordDataModel = (BFRecordDataModel) results.get(0);
		}

		return bfRecordDataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		Hashtable<String, AbsRecord> results = null;
		BFRecordDataModel bfRecordDataModel;

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
			bfRecordDataModel = (BFRecordDataModel) absRecord;
			results.put(bfRecordDataModel.getItemName(), bfRecordDataModel);
		}

		return results;
	}

}
