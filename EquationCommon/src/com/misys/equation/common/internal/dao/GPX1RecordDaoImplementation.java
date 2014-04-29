package com.misys.equation.common.internal.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.misys.equation.common.dao.IGPX1RecordDaoImp;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.internal.dao.mappers.GPX1RecordRowMapperImp;

/**
 * This is the implementation of GPX1RecordDao class.<br>
 * This class is not generated.
 * 
 * @author deroset
 */
public class GPX1RecordDaoImplementation extends GPX1RecordDao implements IGPX1RecordDaoImp
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GPX1RecordDaoImplementation.java 11001 2011-05-19 14:03:00Z MACDONP1 $";

	/**
	 * This method will a <code>List</code> data model which hold the queried system and unit.
	 * 
	 * @param unit
	 *            this the current unit which will be used as filter.
	 * @return a <code>List</code> of data model which hold the queried system and unit.
	 */
	@SuppressWarnings("unchecked")
	public List<AbsRecord> getUnitsAndSystems(String unit)
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<AbsRecord> dataModels = null;

		sqlBuilder.append("SELECT DISTINCT GPXUNC,GPXSYS, GPXUNT ");
		sqlBuilder.append("FROM ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(" WHERE GPXGMN = (");

		sqlBuilder.append("SELECT DISTINCT GPXGMN FROM GPX40LF WHERE GPXUNC = '");
		sqlBuilder.append(unit);
		sqlBuilder.append("'");

		sqlBuilder.append(")");

		// Only add the where clause if there is one:
		JdbcTemplate select = getJdbcTemplate();

		if (LOG.isDebugEnabled())
		{
			LOG.debug(new StringBuilder("The executed sql is: ").append(sqlBuilder.toString()).toString());
		}
		dataModels = select.query(sqlBuilder.toString(), new GPX1RecordRowMapperImp());

		return dataModels;
	}

	/**
	 * This method will a <code>List</code> data model which hold the queried system and unit.
	 * 
	 * @param unit
	 *            this the current unit which will be used as filter.
	 * @return a <code>List</code> of data model which hold the queried system and unit.
	 */
	@SuppressWarnings("unchecked")
	public List<AbsRecord> getUnitsAndSystems()
	{
		StringBuilder sqlBuilder = new StringBuilder(1024);
		List<AbsRecord> dataModels = null;

		sqlBuilder.append("SELECT DISTINCT GPXUNC,GPXSYS,GPXUNT,GPXUND ");
		sqlBuilder.append("FROM ");
		sqlBuilder.append(getTableName());

		// Only add the where clause if there is one:
		JdbcTemplate select = getJdbcTemplate();

		if (LOG.isDebugEnabled())
		{
			LOG.debug(new StringBuilder("The executed sql is: ").append(sqlBuilder.toString()).toString());
		}
		dataModels = select.query(sqlBuilder.toString(), new GPX1RecordRowMapperImp());

		return dataModels;
	}
}