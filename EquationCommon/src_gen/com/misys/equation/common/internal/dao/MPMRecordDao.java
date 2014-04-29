package com.misys.equation.common.internal.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.misys.equation.common.dao.IMPMRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.MPMRecordDataModel;
import com.misys.equation.common.globalprocessing.UnitServerUnitValue;
import com.misys.equation.common.internal.dao.mappers.MPMRecordRowMapper;

/**
 * This the MPM -Record Dao implementation. <br>
 * This class is going to provide all back-end services for MPM -Record.
 * 
 * @author deroset
 */
public class MPMRecordDao extends AbsEquationDao implements IMPMRecordDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1276834712823l;

	public MPMRecordDao()
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
	public boolean checkIfThisMPMRecordIsInTheDB(String sqlWhereStatement)
	{
		return checkIfThisRecordIsInTheDB(sqlWhereStatement);
	}

	/**
	 * This method will check if the current record was already set in the database. <br>
	 * The SQL <code>SELECT COUNT(*)</code> query will be executed.
	 * 
	 * @return true if the SQL statement returns a count >= 1
	 */
	public boolean checkIfThisMPMRecordIsInTheDB()
	{
		return checkIfThisRecordIsInTheDB(getWhereConditionBaseOnIdRecord());
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return an instance of the preset data model.
	 */
	public MPMRecordDataModel getMyDataModel()
	{
		MPMRecordDataModel dataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof MPMRecordDataModel)
		{
			dataModel = (MPMRecordDataModel) getRecord();
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
		whereCondition.append("MPMCOD ='");
		whereCondition.append(getMyDataModel().getCodeFile());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("MPMMVL ='");
		whereCondition.append(getMyDataModel().getMasterValue());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("MPMUNT ='");
		whereCondition.append(getMyDataModel().getUnitMnemonic());
		whereCondition.append("'");
		whereCondition.append(" and ");

		whereCondition.append("MPMSRV ='");
		whereCondition.append(getMyDataModel().getServerName());
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
		String fields = "MPMCOD= ?, MPMMVL= ?, MPMUNT= ?, MPMSRV= ?, MPMUVL= ?";
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
		String fields = "MPMCOD, MPMMVL, MPMUNT, MPMSRV, MPMUVL ";
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
		String fields = "?, ?, ?, ?, ?";
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
		MPMRecordDataModel dataModel = getMyDataModel();

		Object[] object = new Object[] { dataModel.getCodeFile(), dataModel.getMasterValue(), dataModel.getUnitMnemonic(),
						dataModel.getServerName(), dataModel.getUnitValue() };
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
		return getRecordBy(whereClause, new MPMRecordRowMapper());
	}

	/**
	 * This method is going to get all records.
	 * 
	 * @return a <code>List</code> that contains the records
	 */
	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new MPMRecordRowMapper());
	}

	/**
	 * This method is going to return a <code> MPMRecordDataModel </code> base on $tablePrefix-ID
	 * 
	 * @return a <code> MPMRecordDataModel </code> base on $tablePrefix-ID
	 */
	public MPMRecordDataModel getMPMRecord()
	{
		MPMRecordDataModel dataModel = null;
		List<AbsRecord> results = getRecordBy(getWhereConditionBaseOnIdRecord(), new MPMRecordRowMapper());

		if (!results.isEmpty())
		{
			dataModel = (MPMRecordDataModel) results.get(0);
		}
		return dataModel;
	}

	public String getMajorValue(String code, String unit, String server, String unitValue)
	{
		MPMRecordDataModel record = (MPMRecordDataModel) getRecordBy("MPMCOD = '" + code + "' and MPMUNT = '" + unit
						+ "' and MPMSRV = '" + server + "' and MPMUVL = '" + unitValue + "'");
		return record.getMasterValue();
	}

	public UnitServerUnitValue getUnitServerUnitValue(String code, String majorValue)
	{
		MPMRecordDataModel record = (MPMRecordDataModel) getRecordBy("code = '" + code + "' and MPMMVL = '" + majorValue + "'");

		return new UnitServerUnitValue(record.getUnitMnemonic(), record.getServerName(), record.getUnitValue());
	}

	// String = Master+Unit+System
	public Map<String, String> getMapByCode(String code)
	{
		Map<String, String> returnMap = new HashMap<String, String>();

		List<MPMRecordDataModel> newRecords = new ArrayList<MPMRecordDataModel>();
		for (AbsRecord record : getRecordBy("MPMCOD = '" + code + "'"))
		{
			newRecords.add((MPMRecordDataModel) record);
		}

		for (MPMRecordDataModel record : newRecords)
		{
			returnMap.put(record.getUnitMnemonic() + ":" + record.getServerName() + ":" + record.getUnitValue(),
							record.getMasterValue());
		}
		return returnMap;
	}
	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		return null;
	}
}