package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * This a mapper class used by Sp framework to map the <code>ResulSets<Code> with its extractor.
 * 
 */
public class HARecordRowMapper implements RowMapper
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HARecordRowMapper.java 6928 2010-04-14 12:04:49Z MACDONP1 $";
	/**
	 * This method maps the current row in a data-model.
	 * 
	 * @param resultSet
	 *            the ResultSet to map (pre-initialized for the current row)
	 * @param line
	 *            the number of the current row
	 * 
	 * @return the result object for the current row
	 */
	public Object mapRow(ResultSet resultSet, int line) throws SQLException
	{
		HARecordResultSetExtractor extractor = new HARecordResultSetExtractor();
		return extractor.extractData(resultSet);
	}

}
