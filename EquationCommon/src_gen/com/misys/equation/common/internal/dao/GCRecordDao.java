package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.IGCRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GCRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.GCRecordRowMapper;

/**
 * This the GC -Record Dao implementation. <br>
 * This class is going to provide all back-end services for GC -Record.
 * 
 * @author deroset
 */
public class GCRecordDao extends AbsEquationDao implements IGCRecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1276150829261l;

	public GCRecordDao()
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
	public boolean checkIfThisGCRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGCRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public GCRecordDataModel getMyDataModel()
	{
		GCRecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof GCRecordDataModel)
		{
			dataModel = (GCRecordDataModel) getRecord();

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
		whereCondition.append("GCOID ='");
		whereCondition.append(getMyDataModel().getMenuId());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GCONM ='");
		whereCondition.append(getMyDataModel().getMenuTitle());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GCMNC ='");
		whereCondition.append(getMyDataModel().getC01());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GCOID1 ='");
		whereCondition.append(getMyDataModel().getFid1());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GCOID2 ='");
		whereCondition.append(getMyDataModel().getFid2());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GCOID3 ='");
		whereCondition.append(getMyDataModel().getFid3());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GCOID4 ='");
		whereCondition.append(getMyDataModel().getFid4());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GCOID5 ='");
		whereCondition.append(getMyDataModel().getFid5());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GCOID6 ='");
		whereCondition.append(getMyDataModel().getFid6());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GCOID7 ='");
		whereCondition.append(getMyDataModel().getFid7());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GCOID8 ='");
		whereCondition.append(getMyDataModel().getFid8());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GCOID9 ='");
		whereCondition.append(getMyDataModel().getFid9());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GCDLM ='");
		whereCondition.append(getMyDataModel().getDte());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("GCOMT ='");
		whereCondition.append(getMyDataModel().getOptionMenuType());
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
		String fields = "GCOID= ?, GCONM= ?, GCMNC= ?, GCOID1= ?, GCOID2= ?, GCOID3= ?, GCOID4= ?, GCOID5= ?, GCOID6= ?, GCOID7= ?, GCOID8= ?, GCOID9= ?, GCDLM= ?, GCOMT= ?";
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
		String fields = "GCOID, GCONM, GCMNC, GCOID1, GCOID2, GCOID3, GCOID4, GCOID5, GCOID6, GCOID7, GCOID8, GCOID9, GCDLM, GCOMT ";
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
		String fields = "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
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
		GCRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getMenuId(), dataModel.getMenuTitle(), dataModel.getC01(), dataModel.getFid1(),
						dataModel.getFid2(), dataModel.getFid3(), dataModel.getFid4(), dataModel.getFid5(), dataModel.getFid6(),
						dataModel.getFid7(), dataModel.getFid8(), dataModel.getFid9(), dataModel.getDte(),
						dataModel.getOptionMenuType() };
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
		return getRecordBy(whereClause, new GCRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new GCRecordRowMapper());
	}

	/**
	 * This method is going to return a <code> GCRecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> GCRecordDataModel </code> base on $tablePrefix-ID
	 */
	public GCRecordDataModel getGCRecord()
	{
		GCRecordDataModel dataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new GCRecordRowMapper());

		if (!results.isEmpty())
		{

			dataModel = (GCRecordDataModel) results.get(0);
		}

		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}