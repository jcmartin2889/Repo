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
public class WECRecordRowMapper implements RowMapper
{	
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WECRecordRowMapper.java 13102 2012-03-28 06:22:08Z bernie.terrado $";
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1323183018088l;
	
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
		WECRecordResultSetExtractor extractor = new WECRecordResultSetExtractor();
		return extractor.extractData(resultSet);
	}
}
