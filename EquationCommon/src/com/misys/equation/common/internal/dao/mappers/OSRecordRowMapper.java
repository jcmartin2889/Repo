package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OSRecordRowMapper implements RowMapper
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OSRecordRowMapper.java 6928 2010-04-14 12:04:49Z MACDONP1 $";
	public Object mapRow(ResultSet resultSet, int line) throws SQLException
	{
		OSRecordResultSetExtractor extractor = new OSRecordResultSetExtractor();
		return extractor.extractData(resultSet);
	}

}
