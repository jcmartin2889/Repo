package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.OC2RecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */

public class OC2RecordResultSetExtractor implements ResultSetExtractor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OC2RecordResultSetExtractor.java 6928 2010-04-14 12:04:49Z MACDONP1 $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>OC2RecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{

		OC2RecordDataModel record = new OC2RecordDataModel();
		record.setUserId(resultSet.getString(1).trim());
		record.setPassword(resultSet.getBytes(2));
		record.setPwdDate(resultSet.getInt(3));

		return record;
	}

}
