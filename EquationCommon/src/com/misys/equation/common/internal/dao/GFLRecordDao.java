package com.misys.equation.common.internal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.misys.equation.common.dao.IGFLRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GFLDataModel;

public class GFLRecordDao extends AbsEquationDao implements IGFLRecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GFLRecordDao.java 10561 2011-02-25 14:41:46Z WRIGHTP1 $";

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
	public Collection<GFLDataModel> getOvernightPositions(final String enqCurrency)
	{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(OKONPX) AS OVPOS ");
		sql.append("FROM OK01LF ");
		sql.append("WHERE OKCCY='");
		sql.append(enqCurrency);
		sql.append("' AND OKBRNM<>''");

		Collection<GFLDataModel> overnightPositions = getJdbcTemplate().query(sql.toString(), new RowMapper()
		{
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				GFLDataModel ovPos = new GFLDataModel();
				ovPos.setFxAggregate(rs.getLong(1));
				return ovPos;
			}
		});

		return overnightPositions;
	}

	@SuppressWarnings("unchecked")
	public Collection<GFLDataModel> getStartOfDayMaturityPositions(String enqCurrency, String enqEndDate)
	{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT U1DTE, SUM(U1NWP) AS PAY, SUM(U1NWR) AS RCV, (SUM(U1NWR)-SUM(U1NWP)) AS NET ");
		sql.append("FROM U110LF ");
		sql.append("WHERE U1CCY ='");
		sql.append(enqCurrency);
		sql.append("' AND U1BRNM<>'' ");
		sql.append("AND U1DTE<='");
		sql.append(enqEndDate);
		sql.append("' GROUP BY U1DTE");

		Collection<GFLDataModel> positions = getJdbcTemplate().query(sql.toString(), new RowMapper()
		{
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				GFLDataModel pos = new GFLDataModel();
				pos.setFxLadderDate(rs.getString(1));
				pos.setFxPayTotal(rs.getLong(2));
				pos.setFxReceiveTotal(rs.getLong(3));
				pos.setFxSumTotal(rs.getLong(4));
				return pos;
			}
		});

		return positions;
	}

	@SuppressWarnings("unchecked")
	public Collection<GFLDataModel> getPositions(String enqCurrency, String enqEndDate)
	{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT OIDTE, SUM(OINWP) AS PAY, SUM(OINWR) AS RCV, (SUM(OINWR)-SUM(OINWP)) AS NET ");
		sql.append("FROM OI01LF ");
		sql.append("WHERE OICCY ='");
		sql.append(enqCurrency);
		sql.append("' AND OIBRNM<>'' ");
		sql.append("AND OIDTE<='");
		sql.append(enqEndDate);
		sql.append("' GROUP BY OIDTE");

		Collection<GFLDataModel> positions = getJdbcTemplate().query(sql.toString(), new RowMapper()
		{
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				GFLDataModel pos = new GFLDataModel();
				pos.setFxLadderDate(rs.getString(1));
				pos.setFxPayTotal(rs.getLong(2));
				pos.setFxReceiveTotal(rs.getLong(3));
				pos.setFxSumTotal(rs.getLong(4));
				return pos;
			}
		});

		return positions;
	}
}