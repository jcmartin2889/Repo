package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.HARecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 */

public class HARecordResultSetExtractor implements ResultSetExtractor
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HARecordResultSetExtractor.java 6928 2010-04-14 12:04:49Z MACDONP1 $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param resultSet
	 *            passed <code>ResultSet</code> which contains the data
	 * 
	 * @return a <code>HARecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		HARecordDataModel record = new HARecordDataModel();

		record.setLanguageCode(resultSet.getString(1).trim());
		record.setFileKey(resultSet.getString(2).trim());
		record.setCodeDescription(resultSet.getString(3).trim());
		record.setByteCodeDescription(resultSet.getBytes(3)); // description in bytes
		record.setDateLastMaintained(resultSet.getInt(4));
		record.setMaintIllegal(resultSet.getString(5).trim());

		return record;
	}

}
