package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * This a mapper class used by Sp framework to map the <code>ResulSets<Code> with its extractor.
 * 
 * @author deroset
 * 
 */
public class GPMRecordRowMapper implements RowMapper
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1275040799160l;

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
		GPMRecordResultSetExtractor extractor = new GPMRecordResultSetExtractor();
		return extractor.extractData(resultSet);
	}
}