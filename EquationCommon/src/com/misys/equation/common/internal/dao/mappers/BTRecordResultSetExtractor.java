package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.BTRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 */

public class BTRecordResultSetExtractor implements ResultSetExtractor
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BTRecordResultSetExtractor.java 14832 2012-11-05 19:03:33Z williae1 $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * 
	 * @return a <code>BTRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		BTRecordDataModel record = new BTRecordDataModel();
		record.setParameterFile(resultSet.getString(1).trim());
		record.setParameterValue(resultSet.getString(2).trim());
		record.setInformationGroup(resultSet.getString(3).trim());
		return record;
	}
}
