package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.HBRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */

public class HBRecordResultSetExtractor implements ResultSetExtractor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HBRecordResultSetExtractor.java 5654 2009-12-07 16:37:52Z perkinj1 $";

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>HBRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{

		HBRecordDataModel record = new HBRecordDataModel();

		record.setLanguageCode(resultSet.getString(1).trim());
		record.setFilePrefix(resultSet.getString(2).trim());
		record.setFileKey(resultSet.getString(3).trim());
		record.setCodeDescription(resultSet.getString(4).trim());
		record.setDateLastMaintained(resultSet.getInt(5));

		record.setByteCodeDescription(resultSet.getBytes(4));

		return record;
	}

}
