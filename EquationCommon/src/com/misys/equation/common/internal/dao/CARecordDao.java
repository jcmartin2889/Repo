package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.dao.ICARecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.CARecordDataModel;
import com.misys.equation.common.dao.beans.OSRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.CARecordRowMapper;

public class CARecordDao extends AbsEquationDao implements ICARecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CARecordDao.java 17189 2013-09-03 11:49:03Z Lima12 $";
	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return CARecordDataModel - Data model
	 */
	public CARecordDataModel getMyDataModel()
	{
		CARecordDataModel recordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof OSRecordDataModel)
		{
			recordDataModel = (CARecordDataModel) getRecord();

		}
		return recordDataModel;
	}

	@Override
	protected String getFields()
	{
		return " CABBN, CABRN, CABRNM ";
	}

	@Override
	protected String getParameterizedFields()
	{
		return " CABBN=?, CABRN=?, CABRNM=? ";
	}

	@Override
	protected Object[] getParameterizedFieldsValues()
	{
		CARecordDataModel dataModel = getMyDataModel();
		Object[] object = new Object[] { dataModel.getBranchNumber(), dataModel.getBranchName(), dataModel.getBranchMnemonic(), };
		return object;
	}

	@Override
	protected String getParameters()
	{
		return " ?, ?, ? ";
	}

	@Override
	protected String getWhereConditionBaseOnIdRecord()
	{
		StringBuilder whereCondition = new StringBuilder("CABBN=");
		whereCondition.append(getMyDataModel().getBranchNumber());
		whereCondition.append(" AND CABRN='");
		whereCondition.append(getMyDataModel().getBranchName());
		whereCondition.append("' AND CABRNM='");
		whereCondition.append(getMyDataModel().getBranchMnemonic());
		whereCondition.append("'");
		return whereCondition.toString();
	}

	public List<AbsRecord> getRecordBy(String whereClause)
	{
		return getRecordBy(whereClause, new CARecordRowMapper());
	}

	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new CARecordRowMapper());
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		Hashtable<String, AbsRecord> results = null;

		CARecordDataModel caRecordDataModel;

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
			caRecordDataModel = (CARecordDataModel) absRecord;
			results.put(caRecordDataModel.getBranchMnemonic(), caRecordDataModel);
		}

		return results;
	}

}
