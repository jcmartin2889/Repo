package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.misys.equation.common.dao.IGFRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GFRecordDataModel;
import com.misys.equation.common.internal.dao.mappers.GFRecordRowMapper;

public class GFRecordDao extends AbsEquationDao implements IGFRecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GFRecordDao.java 13723 2012-07-02 09:55:17Z whittap1 $";
	/**
	 * This method will return an instance of the preset data model.
	 * 
	 * @return CARecordDataModel - Data model
	 */
	public GFRecordDataModel getMyDataModel()
	{
		GFRecordDataModel recordDataModel = null;
		AbsRecord record = getRecord();

		if (record instanceof GFRecordDataModel)
		{
			recordDataModel = (GFRecordDataModel) getRecord();

		}
		return recordDataModel;
	}

	@Override
	protected String getFields()
	{
		return " GFCUS, GFCLC, GFCUN, GFCPNC, GFDAS ";
	}

	@Override
	protected String getParameterizedFields()
	{
		return " GFCUS=?, GFCLC=?, GFCUN=?, GFCPNC=?, GFDAS=? ";
	}

	@Override
	protected Object[] getParameterizedFieldsValues()
	{
		GFRecordDataModel dataModel = getMyDataModel();
		Object[] object = new Object[] { dataModel.getCustomerMnemonic(), dataModel.getCustomerLocation(),
						dataModel.getCustomerFullName(), dataModel.getCustomerNumber(), dataModel.getCustomerShortName(), };
		return object;
	}

	@Override
	protected String getParameters()
	{
		return " ?, ?, ?, ?, ?, ";
	}

	@Override
	protected String getWhereConditionBaseOnIdRecord()
	{
		StringBuilder whereCondition = new StringBuilder("GFCUS='");
		whereCondition.append(getMyDataModel().getCustomerMnemonic());
		whereCondition.append("' AND GFCLC='");
		whereCondition.append(getMyDataModel().getCustomerLocation());
		whereCondition.append("' AND CABRNM=");
		whereCondition.append(getMyDataModel().getCustomerNumber());
		return whereCondition.toString();
	}

	public List<AbsRecord> getRecordBy(String whereClause)
	{
		return getRecordBy(whereClause, new GFRecordRowMapper());
	}

	public List<AbsRecord> getRecords()
	{
		return getRecordBy(null, new GFRecordRowMapper());
	}

	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		throw new UnsupportedOperationException(
						"OSRecordDao: createHashtableRecordModel() - This method has not been implemented for this DAO.");
	}

	/**
	 * This method will get all customer information (location and mnemonic ).
	 * 
	 * @param customerNumber
	 *            this the customer id.
	 * @return the <code> List </code> of customer of information (location and mnemonic ).
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCustomerLocationAndMnemonic(String customerNumber)
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<Map<String, Object>> result = null;

		sqlBuilder.append("SELECT GFCUS,GFCLC FROM ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(" WHERE GFCPNC = '");
		sqlBuilder.append(customerNumber);
		sqlBuilder.append("' ");

		result = getJdbcTemplate().queryForList(sqlBuilder.toString());
		return result;
	}
}