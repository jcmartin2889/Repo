package com.misys.equation.common.internal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.misys.equation.common.dao.IGFGRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GFGDataModel;
import com.misys.equation.common.globalprocessing.SystemStatusManager;

public class GFGRecordDao extends AbsEquationDao implements IGFGRecordDao
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GFGRecordDao.java 10557 2011-02-25 14:06:35Z WRIGHTP1 $";

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

	@SuppressWarnings("unchecked")
	public Collection<GFGDataModel> getAggregatePosition(final String enqCurrency)
	{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(OKONPX) AS AGGR ");
		sql.append("FROM OK01LF ");
		sql.append("WHERE OKCCY = '");
		sql.append(enqCurrency);
		sql.append("' AND OKBRNM <> ''");

		Collection<GFGDataModel> totalpos = getJdbcTemplate().query(sql.toString(), new RowMapper()
		{
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				GFGDataModel fxTotal2 = new GFGDataModel();
				fxTotal2.setGfgAggregate(rs.getLong(1));
				return fxTotal2;
			}
		});

		return totalpos;
	}

	@SuppressWarnings("unchecked")
	public Collection<GFGDataModel> getStartOfDayMaturities(final String system, final String unit, final String enqCurrency)
	{
		final String processingDate = SystemStatusManager.getInstance().getUnitStatus(system, unit).getToday();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT U1DTE, SUM(U1NWP) AS TOTNWP, SUM(U1NWR) AS TOTNWR, SUM(U1NWR - U1NWP) AS SODIT ");
		sql.append("FROM U110LF ");
		sql.append("WHERE U1CCY = '");
		sql.append(enqCurrency);
		sql.append("' AND U1BRNM <> '' ");
		sql.append("GROUP BY U1DTE");

		Collection<GFGDataModel> totalsod = getJdbcTemplate().query(sql.toString(), new RowMapper()
		{
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				GFGDataModel fxTotal2 = new GFGDataModel();
				fxTotal2.setUpToDate(rs.getString(1));
				fxTotal2.setGfgPayTotal(rs.getLong(2));
				fxTotal2.setGfgReceiveTotal(rs.getLong(3));
				fxTotal2.setGfgSumTotal(rs.getLong(4));
				fxTotal2.setProcessingDate(processingDate);
				return fxTotal2;
			}
		});

		return totalsod;
	}

	@SuppressWarnings("unchecked")
	public Collection<GFGDataModel> getFxTotal(final String system, final String unit, final String enqCurrency)
	{
		final String processingDate = SystemStatusManager.getInstance().getUnitStatus(system, unit).getToday();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT OIDTE, SUM(OINWP) AS TOTWP, SUM(OINWR) AS TOTWR, (SUM(OINWR)-SUM(OINWP)) AS FXNET ");
		sql.append("FROM OI01LF WHERE OICCY ='" + enqCurrency + "' ");
		sql.append("AND OIBRNM <> '' ");
		sql.append("GROUP BY OIDTE");

		Collection<GFGDataModel> totals = getJdbcTemplate().query(sql.toString(), new RowMapper()
		{
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				GFGDataModel fxTotal = new GFGDataModel();
				fxTotal.setUpToDate(rs.getString(1));
				fxTotal.setGfgPayTotal(rs.getLong(2));
				fxTotal.setGfgReceiveTotal(rs.getLong(3));
				fxTotal.setGfgSumTotal(rs.getLong(4));
				fxTotal.setProcessingDate(processingDate);
				return fxTotal;
			}
		});

		return totals;
	}
}
