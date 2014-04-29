package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IGYWRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GYWRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.GYWRecordRowMapper;

/**
 * This the GYWPF Dao implementation. <br>
 * This class is going to provide all back-end services for GWYPF.
 * 
 */
public class GYWRecordDao extends AbsEquationDao implements IGYWRecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GYWRecordDao.java 14832 2012-11-05 19:03:33Z williae1 $";

	public GYWRecordDao()
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
	public boolean checkIfThisGYWRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGYWRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public GYWRecordDataModel getMyDataModel()
	{
		GYWRecordDataModel GYWRecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof GYWRecordDataModel)
		{
			GYWRecordDataModel = (GYWRecordDataModel) getRecord();

		}
		return GYWRecordDataModel;
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
		StringBuilder whereCondition = new StringBuilder("GYWWSD='");
		whereCondition.append(getMyDataModel().getWorkstationId());
		whereCondition.append("' and ");

		whereCondition.append("GYWDIM=");
		whereCondition.append(getMyDataModel().getDate());
		whereCondition.append(" and ");

		whereCondition.append("GYWTIM=");
		whereCondition.append(getMyDataModel().getTime());
		whereCondition.append(" and ");

		whereCondition.append("GYWSEQ=");
		whereCondition.append(getMyDataModel().getSequence());
		whereCondition.append(" and ");

		whereCondition.append("GYWFRO='");
		whereCondition.append(getMyDataModel().getProgramRoot());
		whereCondition.append("' and ");

		whereCondition.append("GYWJTT='");
		whereCondition.append(getMyDataModel().getJtt());
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
		String fields = " GYWWSD=?, GYWDIM=?, GYWTIM=?, GYWSEQ=?, GYWFRO=?, GYWJTT=?, GYWCLS=?, GYWDTA=?, GYWMLS=?, GYWMTA=? ";
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
		String fields = " GYWWSD, GYWDIM, GYWTIM, GYWSEQ, GYWFRO, GYWJTT, GYWCLS, GYWDTA, GYWMLS, GYWMTA ";
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
		String fields = " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
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
		GYWRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getWorkstationId(), dataModel.getDate(), dataModel.getTime(),
						dataModel.getSequence(), dataModel.getProgramRoot(), dataModel.getJtt(), dataModel.getUserClassName(),
						dataModel.getUserData(), dataModel.getMisysClassName(), dataModel.getMisysData(), };

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
		return getRecordBy(whereClause, new GYWRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{

		return getRecordBy(null, new GYWRecordRowMapper());
	}

	/**
	 * This method is going to return a <code>GYWRecordDataModel</code> base on SessionFID
	 * 
	 * @return a <code>GYWRecordDataModel</code> base on SessionFID
	 */
	public GYWRecordDataModel getGYWRecord()
	{
		GYWRecordDataModel GYWRecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new GYWRecordRowMapper());

		if (!results.isEmpty())
		{
			GYWRecordDataModel = (GYWRecordDataModel) results.get(0);
		}

		return GYWRecordDataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}

}
