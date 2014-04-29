package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * This a mapper class used by Sp framework to map the <code>ResultSets<Code> with its extractor.
 * 
 */
public class WFRecordRowMapper implements RowMapper
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WFRecordRowMapper.java 15470 2013-03-08 15:56:33Z whittap1 $";
	/**
	 * This method maps the current row in a data-model.
	 * 
	 * @param rs
	 *            the ResultSet to map (pre-initialized for the current row)
	 * @param rowNum
	 *            the number of the current row
	 * @return the result object for the current row
	 */
	public Object mapRow(ResultSet resultSet, int line) throws SQLException
	{
		WFRecordResultSetExtractor extractor = new WFRecordResultSetExtractor();
		return extractor.extractData(resultSet);
	}

}
