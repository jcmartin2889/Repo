package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.ACNRecordDataModel;

/**
 * This is a helper class used by Spring framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 */

public class ACNRecordResultSetExtractor implements ResultSetExtractor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACNRecordResultSetExtractor.java 11052 2011-05-26 08:08:54Z perkinj1 $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param resultSet
	 *            the passed <code>ResultSet</code> which contains the data.
	 * @return a <code>ACNRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{

		ACNRecordDataModel record = new ACNRecordDataModel();

		record.setProgram(resultSet.getString(1).trim());
		record.setClassName(resultSet.getString(2).trim());
		return record;
	}

}
