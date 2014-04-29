package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * This a mapper class used by the Spring framework to map the <code>ResulSets<Code> with its mapper.
 * 
 * @author deroset
 * 
 */
public class GAERecordRowMapper implements RowMapper
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GAERecordRowMapper.java 4646 2009-09-07 16:28:30Z weddelc1 $";
	/**
	 * This method maps the current row in a data-model.
	 * 
	 * @param rs
	 *            the ResultSet to map (pre-initialised for the current row)
	 * @param rowNum
	 *            the number of the current row
	 * @return the result object for the current row
	 */
	public Object mapRow(ResultSet resultSet, int line) throws SQLException
	{
		GAERecordResultSetExtractor extractor = new GAERecordResultSetExtractor();
		return extractor.extractData(resultSet);
	}

}
