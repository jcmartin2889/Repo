package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.GYWRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 */

public class GYWRecordResultSetExtractor implements ResultSetExtractor
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GYWRecordResultSetExtractor.java 14832 2012-11-05 19:03:33Z williae1 $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * 
	 * @return a <code>GYWRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		GYWRecordDataModel record = new GYWRecordDataModel();
		record.setWorkstationId(resultSet.getString(1).trim());
		record.setDate(resultSet.getInt(2));
		record.setTime(resultSet.getInt(3));
		record.setSequence(resultSet.getInt(4));
		record.setProgramRoot(resultSet.getString(5).trim());
		record.setJtt(resultSet.getString(6).trim());
		record.setUserClassName(resultSet.getString(7).trim());
		record.setUserData(resultSet.getBytes(8));
		record.setMisysClassName(resultSet.getString(9).trim());
		record.setMisysData(resultSet.getBytes(10));
		return record;
	}
}
