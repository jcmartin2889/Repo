package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.C8RecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 */

public class C8RecordResultSetExtractor implements ResultSetExtractor
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: C8RecordResultSetExtractor.java 6928 2010-04-14 12:04:49Z MACDONP1 $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>C8RecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{

		C8RecordDataModel record = new C8RecordDataModel();

		record.setCurrencyMnem(resultSet.getString(1).trim());
		record.setCurrencyNum(resultSet.getString(2).trim());
		record.setSwiftCode(resultSet.getString(3).trim());
		record.setEditField(resultSet.getString(4).trim());
		record.setCurrencyName(resultSet.getString(5).trim());

		return record;
	}

}
