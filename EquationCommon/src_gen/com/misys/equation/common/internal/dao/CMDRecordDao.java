package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.ICMDRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.CMDRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.CMDRecordRowMapper;

/**
 * This the CMD -Record Dao implementation. <br>
 * This class is going to provide all back-end services for CMD -Record.
 * 
 * @author deroset
 */
public class CMDRecordDao extends AbsEquationDao implements ICMDRecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1282892037908l;

	public CMDRecordDao()
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
	public boolean checkIfThisCMDRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisCMDRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public CMDRecordDataModel getMyDataModel()
	{
		CMDRecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof CMDRecordDataModel)
		{
			dataModel = (CMDRecordDataModel) getRecord();

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
		whereCondition.append("CMDID ='");
		whereCondition.append(getMyDataModel().getIdentifier());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("CMDNAM ='");
		whereCondition.append(getMyDataModel().getName());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("CMDDES ='");
		whereCondition.append(getMyDataModel().getDescription());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("CMDOID ='");
		whereCondition.append(getMyDataModel().getOptionId());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("CMDAEXT ='");
		whereCondition.append(getMyDataModel().getAvailableForExternalInput());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("CMDAREC ='");
		whereCondition.append(getMyDataModel().getAvailableForRecovery());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("CMDMCXT ='");
		whereCondition.append(getMyDataModel().getManagementConsoleUserExit());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("CMDPARM ='");
		whereCondition.append(getMyDataModel().getParameters());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("CMDREL ='");
		whereCondition.append(getMyDataModel().getRelease());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("CMDPHS ='");
		whereCondition.append(getMyDataModel().getValidPhases());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("CMDCOND ='");
		whereCondition.append(getMyDataModel().getGenericConditions());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("CMDCAT ='");
		whereCondition.append(getMyDataModel().getCategory());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("CMDPDES ='");
		whereCondition.append(getMyDataModel().getParameterDescription());
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
		String fields = "CMDID= ?, CMDNAM= ?, CMDDES= ?, CMDOID= ?, CMDAEXT= ?, CMDAREC= ?, CMDMCXT= ?, CMDPARM= ?, CMDREL= ?, CMDPHS= ?, CMDCOND= ?, CMDCAT= ?, CMDPDES= ?";
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
		String fields = "CMDID, CMDNAM, CMDDES, CMDOID, CMDAEXT, CMDAREC, CMDMCXT, CMDPARM, CMDREL, CMDPHS, CMDCOND, CMDCAT, CMDPDES ";
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
		String fields = "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
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
		CMDRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getIdentifier(), dataModel.getName(), dataModel.getDescription(),
						dataModel.getOptionId(), dataModel.getAvailableForExternalInput(), dataModel.getAvailableForRecovery(),
						dataModel.getManagementConsoleUserExit(), dataModel.getParameters(), dataModel.getRelease(),
						dataModel.getValidPhases(), dataModel.getGenericConditions(), dataModel.getCategory(),
						dataModel.getParameterDescription() };
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
		return getRecordBy(whereClause, new CMDRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new CMDRecordRowMapper());
	}

	/**
	 * This method is going to return a <code> CMDRecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> CMDRecordDataModel </code> base on $tablePrefix-ID
	 */
	public CMDRecordDataModel getCMDRecord()
	{
		CMDRecordDataModel dataModel = null;
		List results = getRecordBy(getWhereConditionBaseOnIdRecord(), new CMDRecordRowMapper());

		if (!results.isEmpty())
		{

			dataModel = (CMDRecordDataModel) results.get(0);
		}

		return dataModel;
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}