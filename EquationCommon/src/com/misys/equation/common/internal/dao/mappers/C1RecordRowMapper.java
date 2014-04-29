package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class C1RecordRowMapper implements RowMapper
{
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: C1RecordRowMapper.java 11229 2011-06-17 12:11:19Z rpatrici $";
	public Object mapRow(ResultSet resultSet, int line) throws SQLException
	{
		C1RecordResultSetExtractor extractor = new C1RecordResultSetExtractor();
		return extractor.extractData(resultSet);
	}
}
