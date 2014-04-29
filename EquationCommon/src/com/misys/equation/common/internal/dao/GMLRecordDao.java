package com.misys.equation.common.internal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.misys.equation.common.dao.IGMLRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GMLDataModel;

public class GMLRecordDao extends AbsEquationDao implements IGMLRecordDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GMLRecordDao.java 10087 2010-12-03 09:03:37Z WRIGHTP1 $";

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
	public Collection<GMLDataModel> getOvernightPositions(final String enqCurrency)
	{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(OKONPM) AS OVPOS ");
		sql.append("FROM OK01LF ");
		sql.append("WHERE OKCCY='");
		sql.append(enqCurrency);
		sql.append("' AND OKBRNM<>''");

		Collection<GMLDataModel> overnightPositions = getJdbcTemplate().query(sql.toString(), new RowMapper()
		{
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				GMLDataModel ovPos = new GMLDataModel();
				ovPos.setMMCumulativeAmt(rs.getLong(1));
				return ovPos;
			}
		});

		return overnightPositions;
	}

	@SuppressWarnings("unchecked")
	public Collection<GMLDataModel> getPositions(String enqCurrency, String enqEndDate)
	{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT OJDTE, SUM(OJNWP) AS PAY, SUM(OJNWR) AS RCV, (SUM(OJNWR)-SUM(OJNWP)) AS NET ");
		sql.append("FROM OJ01LF ");
		sql.append("WHERE OJCCY ='");
		sql.append(enqCurrency);
		sql.append("' AND OJBRNM<>'' ");
		sql.append("AND OJDTE<='");
		sql.append(enqEndDate);
		sql.append("' GROUP BY OJDTE");

		Collection<GMLDataModel> positions = getJdbcTemplate().query(sql.toString(), new RowMapper()
		{
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				GMLDataModel pos = new GMLDataModel();
				pos.setMMLadderDate(rs.getString(1));
				pos.setMMPayAmt(rs.getLong(2));
				pos.setMMReceiveAmt(rs.getLong(3));
				pos.setMMNetAmt(rs.getLong(4));
				return pos;
			}
		});

		return positions;
	}
}