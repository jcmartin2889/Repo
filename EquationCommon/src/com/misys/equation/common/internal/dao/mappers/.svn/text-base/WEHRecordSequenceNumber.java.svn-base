package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.misys.equation.common.utilities.EquationLogger;

public class WEHRecordSequenceNumber implements RowMapper
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WEHRecordSequenceNumber.java 13101 2012-03-28 06:15:02Z bernie.terrado $";
	private static final EquationLogger LOG = new EquationLogger(WEHRecordSequenceNumber.class);

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1323183018244l;

	/**
	 * This method maps the current row in a data-model.
	 * 
	 * @param resultSet
	 *            the ResultSet to map (preinitialised for the current row)
	 * @param line
	 *            the number of the current row
	 * @return the result object for the current row
	 */
	public Object mapRow(ResultSet resultSet, int line) throws SQLException
	{
		WEHRecordSequenceNumberExtractor extractor = new WEHRecordSequenceNumberExtractor();
		return extractor.extractData(resultSet);
	}
}
