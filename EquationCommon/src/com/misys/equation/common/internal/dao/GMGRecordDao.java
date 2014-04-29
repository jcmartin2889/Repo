package com.misys.equation.common.internal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.misys.equation.common.dao.IGMGRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GMGDataModel;
import com.misys.equation.common.globalprocessing.SystemStatusManager;

public class GMGRecordDao extends AbsEquationDao implements IGMGRecordDao
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GMGRecordDao.java 10168 2010-12-18 16:55:12Z WRIGHTP1 $";

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
	public Collection<GMGDataModel> getOvernightPosition(final String enqCurrency)
	{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(OKONPM) AS OVPOS ");
		sql.append("FROM OK01LF ");
		sql.append("WHERE OKCCY='");
		sql.append(enqCurrency);
		sql.append("' AND OKBRNM<>''");

		Collection<GMGDataModel> overnightPosition = getJdbcTemplate().query(sql.toString(), new RowMapper()
		{
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				GMGDataModel ovPos = new GMGDataModel();
				ovPos.setGapCumulativeAmt(rs.getLong(1));
				return ovPos;
			}
		});

		return overnightPosition;
	}

	@SuppressWarnings("unchecked")
	public Collection<GMGDataModel> getPositions(final String system, final String unit, final String enqCurrency)
	{
		final String processingDate = SystemStatusManager.getInstance().getUnitStatus(system, unit).getToday();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT OJDTE, SUM(OJNWP) AS PAY, SUM(OJNWR) AS RCV, (SUM(OJNWR)-SUM(OJNWP)) AS NET ");
		sql.append("FROM OJ01LF ");
		sql.append("WHERE OJCCY ='");
		sql.append(enqCurrency);
		sql.append("' AND OJBRNM<>'' ");
		sql.append("GROUP BY OJDTE");

		Collection<GMGDataModel> mmPositions = getJdbcTemplate().query(sql.toString(), new RowMapper()
		{
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				GMGDataModel mmPos = new GMGDataModel();
				mmPos.setGapDate(rs.getString(1));
				mmPos.setGapPayAmt(rs.getLong(2));
				mmPos.setGapReceiveAmt(rs.getLong(3));
				mmPos.setGapNetAmt(rs.getLong(4));
				mmPos.setProcessingDate(processingDate);
				return mmPos;
			}
		});

		return mmPositions;
	}
}
