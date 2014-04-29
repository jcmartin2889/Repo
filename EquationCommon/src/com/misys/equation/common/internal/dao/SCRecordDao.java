package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.ISCRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.SCRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.SCRecordRowMapper;

public class SCRecordDao extends AbsEquationDao implements ISCRecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SCRecordDao.java 9659 2010-11-02 17:25:07Z MACDONP1 $";
	public SCRecordDao()
	{
		super();
	}

	@Override
	protected String getFields()
	{
		return " SCAB, SCAN, SCAS, SCSHN ";
	}

	@Override
	protected String getParameterizedFields()
	{
		return " SCAB=?, SCAN=?, SCAS=? ";
	}

	@Override
	protected String getParameters()
	{
		return " ?, ?, ? ";
	}

	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return SCRecordDataModel - Data model
	 */
	public SCRecordDataModel getMyDataModel()
	{
		SCRecordDataModel recordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof SCRecordDataModel)
		{
			recordDataModel = (SCRecordDataModel) getRecord();

		}
		return recordDataModel;
	}

	/**
	 * This method create an array that containing the fields values and its types. <br>
	 * It will be used by JDBC <code>PreparedStatement</code>.
	 * 
	 * @return Object[] - Parameterised Fields Values
	 */
	@Override
	protected Object[] getParameterizedFieldsValues()
	{
		SCRecordDataModel dataModel = getMyDataModel();
		Object[] object = new Object[] { dataModel.getAccountBranch(), dataModel.getBasicNumber(), dataModel.getAccountSuffix(), };
		return object;
	}

	@Override
	protected String getWhereConditionBaseOnIdRecord()
	{
		StringBuilder whereCondition = new StringBuilder("SCAB=");
		whereCondition.append(getMyDataModel().getAccountBranch());
		whereCondition.append(" AND ");
		whereCondition.append(" SCAN=");
		whereCondition.append(" AND ");
		whereCondition.append(getMyDataModel().getBasicNumber());
		whereCondition.append(" SCAS=");
		whereCondition.append(getMyDataModel().getAccountSuffix());
		return whereCondition.toString();
	}

	public List<AbsRecord> getRecordBy(String whereClause)
	{
		return getRecordBy(whereClause, new SCRecordRowMapper());
	}

	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new SCRecordRowMapper());
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		throw new UnsupportedOperationException(
						"SCRecordDao: createHashtableRecordModel() - This method has not been implemented for this DAO.");
	}
}
