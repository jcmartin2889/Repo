package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * This a RowMapper class used by Sp framework to map a <code>ResultSet<code> with its extractor.
 * <p>
 * This particular row mapper is used when dealing with a <code>ResultSet<code> containing only the GAXKEY field.
 */
public class GAXRecordRowMapperKey implements RowMapper
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GAXRecordRowMapperKey.java 4646 2009-09-07 16:28:30Z weddelc1 $";
	/**
	 * This method maps the current row in a data-model.
	 * 
	 * @param rs
	 *            the ResultSet to map (pre-initialized for the current row)
	 * @param rowNum
	 *            the number of the current row
	 * @return the result object for the current row. Note that this is a String containing the value of the the GAXKEY field, not a
	 *         GAXRecordDataModel object
	 */
	public Object mapRow(ResultSet resultSet, int line) throws SQLException
	{
		// As this is a very simple extraction processing, a separate Extractor class is not required
		return resultSet.getString(1);
	}
}
