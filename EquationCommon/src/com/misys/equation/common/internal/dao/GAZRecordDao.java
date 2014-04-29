package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.misys.equation.common.dao.IGAZRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.GAZRecordRowMapper;

/**
 * This the GAZ-Record Dao implementation. <br>
 * This class is going to provide all back-end services for GAZ-Record.
 * 
 * @author deroset
 */
public class GAZRecordDao extends AbsEquationDao implements IGAZRecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GAZRecordDao.java 10068 2010-12-01 12:13:25Z MACDONP1 $";
	public GAZRecordDao()
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
	public boolean checkIfThisGAZRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The Sql <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisGAZRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public GAZRecordDataModel getMyDataModel()
	{
		GAZRecordDataModel GAZRecordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof GAZRecordDataModel)
		{
			GAZRecordDataModel = (GAZRecordDataModel) getRecord();

		}
		return GAZRecordDataModel;
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
		StringBuilder whereCondition = new StringBuilder("GAZOID='");
		whereCondition.append(getMyDataModel().getOptionId());
		whereCondition.append("'and GAZFLD='");
		whereCondition.append(getMyDataModel().getFieldId());
		whereCondition.append("'and GAZPVN='");
		whereCondition.append(getMyDataModel().getPvId());
		whereCondition.append("'and GAZTYP='");
		whereCondition.append(getMyDataModel().getType());
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
		String fields = " GAZOID=?, GAZFLD=?, GAZPVN=?, GAZTYP=?, GAZCLN=?, GAZCLS=? ";
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
		String fields = " GAZOID, GAZFLD, GAZPVN, GAZTYP, GAZCLN, GAZCLS ";
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
		String fields = " ?, ?, ?, ?, ?, ? ";
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
		GAZRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getOptionId(), dataModel.getFieldId(), dataModel.getPvId(), dataModel.getType(),
						dataModel.getClassName(), dataModel.getClassByte(), };
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
		return getRecordBy(whereClause, new GAZRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> which contains a records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new GAZRecordRowMapper());
	}

	/**
	 * This method is going to return a <code>GAZRecordDataModel</code> based on GAZOID, GAZFLD, GAZPVN and GAZTYP
	 * 
	 * @return a <code>GAZRecordDataModel</code> based on GAZOID, GAZFLD, GAZPVN and GAZTYP
	 */
	public GAZRecordDataModel getGAZRecord()
	{
		GAZRecordDataModel GAZRecordDataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new GAZRecordRowMapper());

		if (!results.isEmpty())
		{
			GAZRecordDataModel = (GAZRecordDataModel) results.get(0);
		}

		return GAZRecordDataModel;
	}

	/**
	 * This method is going execute a Sql query using the filter criteria.
	 * 
	 * @param whereClause
	 *            this is the <code>String</code> that represent the sql filter.
	 * 
	 * @param rowMapper
	 *            this is the mapper which will populate the data-model
	 * 
	 * @return a <code>List</code> which contains a records
	 */

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		Hashtable<String, AbsRecord> results = null;
		StringBuilder key = null;
		GAZRecordDataModel gazRecordDataModel;

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
			gazRecordDataModel = (GAZRecordDataModel) absRecord;
			// key=optionId + fieldId + pvId + type;
			key = new StringBuilder(gazRecordDataModel.getOptionId());
			key.append(gazRecordDataModel.getFieldId());
			key.append(gazRecordDataModel.getPvId());
			key.append(gazRecordDataModel.getType());

			results.put(key.toString(), gazRecordDataModel);
		}

		return results;
	}

	/**
	 * This method deletes all the records which have the supplied option id and type.
	 * <p>
	 * This method is called to delete a records of the specified type during deployment, before any items still existing are
	 * re-added. Otherwise, items would remain on the GAZPF
	 * 
	 * @param option
	 *            this is the service or layout id
	 * @param type
	 *            This indicates the type of item (e.g. layout display attribute, service PV mapping)
	 * 
	 */
	public void deleteRecordByOptionAndType(String option, String type)
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		JdbcTemplate updateTemplate;

		sqlBuilder.append("DELETE FROM ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(" WHERE ");
		sqlBuilder.append("GAZOID ='");
		sqlBuilder.append(option);
		sqlBuilder.append("' AND GAZTYP = '");
		sqlBuilder.append(type);
		sqlBuilder.append("'");

		updateTemplate = getJdbcTemplate();

		if (LOG.isDebugEnabled())
		{
			LOG.debug(new StringBuilder("The executed sql is: ").append(sqlBuilder.toString()).toString());
		}
		updateTemplate.update(sqlBuilder.toString());

		if (isAutocommitable())
		{
			commit();
		}
	}
}
