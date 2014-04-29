package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.misys.equation.common.dao.INostroPositionsDao;
import com.misys.equation.common.dao.beans.AbsRecord;

public class NostroPositionsDao extends AbsEquationDao implements INostroPositionsDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: NostroPositionsDao.java 13723 2012-07-02 09:55:17Z whittap1 $";
	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getFields()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getParameterizedFields()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object[] getParameterizedFieldsValues()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getParameters()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getWhereConditionBaseOnIdRecord()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public List<AbsRecord> getRecordBy(String whereClause)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public List<AbsRecord> getRecords()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This method will return nostro accounts information base on the passed currency.
	 * 
	 * @param currency
	 *            this is the padded currency.
	 * @return this a collection of nostro accounts information.
	 */
	public List<Map<String, Object>> getAllNostrosAccountsBaseOnThePassedCurrency(String currency)
	{

		// select GJBAL, GJCCY, GJNST from GJ03LF where GJCCY='USD' and GJVST='1'
		// select count (*) as A from GJ03LF where GJCCY='USD' and GJVST='1'

		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<Map<String, Object>> result = null;

		sqlBuilder.append("SELECT  GJBAL, GJCCY, GJNST, GJABF FROM GJ03LF");

		sqlBuilder.append(" WHERE GJCCY='");
		sqlBuilder.append(currency);
		sqlBuilder.append("' AND GJVST='1'");
		sqlBuilder.append(" ORDER BY GJABF, GJNST");
		result = getJdbcTemplate().queryForList(sqlBuilder.toString());

		return result;
	}

	/**
	 * This method will return all nostro-account details base on the passed nostro-account id and currency.
	 * 
	 * @param nostroMnemonic
	 *            this is the nostro-account id.
	 * 
	 * @param currency
	 *            this is the passed currency.
	 * 
	 * @param staringDate
	 *            this is date that will be used to filter the data. it is the from date .
	 * 
	 * @param endDate
	 *            this is date that will be used to filter the data. it is the until date .
	 * 
	 * @return all nostro-account details base on the passed nostro-account id and currency.
	 */
	public List<Map<String, Object>> getAllNostrosAccountsDetails(String nostroMnemonic, String currency, String staringDate,
					String endDate)
	{
		// SELECT OPNWR,OPNWP,OPCCY,OPNST,OPDTE FROM OP03LF WHERE OPCCY='USD' AND OPNST='AB1USD'
		// SELECT OPNWR,OPNWP,OPCCY,OPNST,OPDTE FROM OP03LF WHERE OPCCY='USD' AND OPNST='AB1USD' AND OPDTE >='1000104' AND
		// OPDTE <='1000130'

		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<Map<String, Object>> result = null;

		sqlBuilder.append("SELECT OPNWR,OPNWP,OPCCY,OPNST,OPDTE FROM OP03LF WHERE OPCCY='");
		sqlBuilder.append(currency);
		sqlBuilder.append("' AND OPNST='");
		sqlBuilder.append(nostroMnemonic);
		sqlBuilder.append("' AND OPDTE >='");
		sqlBuilder.append(staringDate);
		sqlBuilder.append("' AND OPDTE <='");
		sqlBuilder.append(endDate);
		sqlBuilder.append("'");

		result = getJdbcTemplate().queryForList(sqlBuilder.toString());

		return result;
	}

	/**
	 * This method will return the last nostro-account position. <br>
	 * This calculation is done SUM( OPNWR - OPNWP ) per each date before to the unit date.
	 * 
	 * @param nostroMnemonic
	 *            this is the nostro-account id.
	 * @param currency
	 *            this is the passed currency.
	 * 
	 * @param staringDate
	 *            this is date that will be used to filter the data. it is the from date .
	 * 
	 * @return the last nostro-account position.
	 */
	public List<Map<String, Object>> getAllNostrosAccountsLastPosition(String nostroMnemonic, String currency, String staringDate)
	{
		// SELECT ( OPNWR - OPNWP ) AS RESULT FROM OP03LF WHERE OPCCY='USD' AND OPNST='BARUSD' AND OPDTE <'1000104'
		// SELECT SUM( OPNWR - OPNWP ) AS RESULT FROM OP03LF WHERE OPCCY='USD' AND OPNST='BARUSD' AND OPDTE <'1000104'

		// SELECT SUM( OPNWR - OPNWP ) AS RESULT FROM OP03LF WHERE OPCCY='USD' AND OPNST='BARUSD' AND OPDTE <'1000104'

		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<Map<String, Object>> result = null;

		sqlBuilder.append("SELECT SUM( OPNWR - OPNWP ) AS RESULT FROM OP03LF WHERE OPCCY='");
		sqlBuilder.append(currency);
		sqlBuilder.append("' AND OPNST='");
		sqlBuilder.append(nostroMnemonic);
		sqlBuilder.append("' AND OPDTE < ");
		sqlBuilder.append(staringDate);
		result = getJdbcTemplate().queryForList(sqlBuilder.toString());
		return result;
	}
}