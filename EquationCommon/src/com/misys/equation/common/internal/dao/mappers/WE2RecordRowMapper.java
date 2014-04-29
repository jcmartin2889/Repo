package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * This a mapper class used by Sp framework to map the <code>ResulSets<Code> with its extractor.
 * 
 * @author yzobdabu
 * 
 */
public class WE2RecordRowMapper implements RowMapper
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WE2RecordRowMapper.java 11959 2011-09-30 09:29:54Z rpatrici $";

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
		WE2RecordResultSetExtractor extractor = new WE2RecordResultSetExtractor();
		return extractor.extractData(resultSet);
	}

}
