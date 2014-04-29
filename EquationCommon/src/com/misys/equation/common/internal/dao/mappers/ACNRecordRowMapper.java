package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * This is a mapper class used by Spring framework to map the <code>ResultSet<Code> with its extractor.
 */
public class ACNRecordRowMapper implements RowMapper
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACNRecordRowMapper.java 11052 2011-05-26 08:08:54Z perkinj1 $";
	/**
	 * This method maps the current row in a data-model.
	 * 
	 * @param resultSet
	 *            the ResultSet to map (pre-initialized for the current row)
	 * @param line
	 *            the number of the current row
	 * @return the result object for the current row
	 */
	public Object mapRow(ResultSet resultSet, int line) throws SQLException
	{

		ACNRecordResultSetExtractor extractor = new ACNRecordResultSetExtractor();
		return extractor.extractData(resultSet);
	}

}
