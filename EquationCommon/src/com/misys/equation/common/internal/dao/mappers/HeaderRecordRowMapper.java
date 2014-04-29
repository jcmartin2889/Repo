package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class HeaderRecordRowMapper implements RowMapper
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HeaderRecordRowMapper.java 8100 2010-07-07 08:50:57Z perkinj1 $";
	/**
	 * This method maps the current row in a data-model.
	 * 
	 * @param rs
	 *            the ResultSet to map (preinitialised for the current row)
	 * @param rowNum
	 *            the number of the current row
	 * @return the result object for the current row
	 */
	public Object mapRow(ResultSet resultSet, int line) throws SQLException
	{
		HeaderRecordResultSetExtractor extractor = new HeaderRecordResultSetExtractor();
		return extractor.extractData(resultSet);
	}
}
