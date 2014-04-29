package com.misys.equation.common.internal.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.misys.equation.common.dao.IGFERecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;

public class GFERecordDao extends AbsEquationDao implements IGFERecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GFERecordDao.java 13723 2012-07-02 09:55:17Z whittap1 $";
	@Override
	protected Hashtable<String, AbsRecord> createHashtableRecordModel(List<AbsRecord> records)
	{
		throw new NoSuchMethodError("");
	}

	@Override
	protected String getFields()
	{
		throw new NoSuchMethodError("");
	}

	@Override
	protected String getParameterizedFields()
	{
		throw new NoSuchMethodError("");
	}

	@Override
	protected Object[] getParameterizedFieldsValues()
	{
		throw new NoSuchMethodError("");
	}

	@Override
	protected String getParameters()
	{
		throw new NoSuchMethodError("");
	}

	@Override
	protected String getWhereConditionBaseOnIdRecord()
	{
		throw new NoSuchMethodError("");
	}
	/**
	 * This method will return the overnight and net forward position per currency.
	 * 
	 * 
	 * @return all Currency overnight positions and net forward positions
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getFXCurrencyOvernightPositions(String unit, String system)
	{
		StringBuilder sql = new StringBuilder();
		List<Map<String, Object>> fxCurrencyOvernightPositions = null;

		sql.append("SELECT OKCCY, SUM(OKONPX) AS OKOVRN, SUM(OKFRX - OKFPX) AS OKNET, '");
		sql.append(unit + "' AS UNIT, '");
		sql.append(system + "' AS SYSTEM");
		sql.append(" FROM OK01LF ");
		sql.append("WHERE OKBRNM <> '' GROUP BY OKCCY");
		fxCurrencyOvernightPositions = getJdbcTemplate().queryForList(sql.toString());
		return fxCurrencyOvernightPositions;
	}
	/**
	 * This method will return the net position to yesterday per currency.
	 * 
	 * @param endDate
	 *            this is the latest PDATE across the global units, only records prior to this date are included
	 * 
	 * @return List of Currencies and Net positions to yesterday.
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getFXCurrencyPositionsForToday(String endDate, String unit, String system)
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<Map<String, Object>> result = null;

		sqlBuilder.append("SELECT OICCY, SUM(OINWR - OINWP) AS OINET, '");
		sqlBuilder.append(unit + "' AS UNIT, '");
		sqlBuilder.append(system + "' AS SYSTEM");
		sqlBuilder.append(" FROM OI01LF ");
		sqlBuilder.append("WHERE OIBRNM <> '' AND OIDTE <'");
		sqlBuilder.append(endDate);
		sqlBuilder.append("' ");
		sqlBuilder.append("GROUP BY OICCY");

		result = getJdbcTemplate().queryForList(sqlBuilder.toString());

		return result;

	}
	/**
	 * This method will return all daily positions per currency
	 * 
	 * @param staringDate
	 *            this is the latest PDATE across the global units, which is the start point for the enquiry
	 * 
	 * @param endDate
	 *            this date is the calculated end date, being PDATE + System option defined number of days for the query
	 * 
	 * @return List of Currencies and Net positions per date.
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getFXCurrencyPositionsByDate(String startingDate, String endDate, String unit, String system)
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<Map<String, Object>> fxCurrencyPositionsByDate = null;

		sqlBuilder.append("SELECT OICCY, OIDTE, SUM(OINWR - OINWP) AS OINET, '");
		sqlBuilder.append(unit + "' AS UNIT, '");
		sqlBuilder.append(system + "' AS SYSTEM");
		sqlBuilder.append(" FROM OI01LF ");
		sqlBuilder.append("WHERE OIBRNM <> '' AND OIDTE >='");
		sqlBuilder.append(startingDate);
		sqlBuilder.append("' AND OIDTE <='");
		sqlBuilder.append(endDate);
		sqlBuilder.append("' ");
		sqlBuilder.append("GROUP BY OICCY, OIDTE");

		fxCurrencyPositionsByDate = getJdbcTemplate().queryForList(sqlBuilder.toString());

		return fxCurrencyPositionsByDate;

	}

	public List<AbsRecord> getRecordBy(String whereClause)
	{
		throw new NoSuchMethodError("");
	}

	public List<AbsRecord> getRecords()
	{
		throw new NoSuchMethodError("");
	}
}