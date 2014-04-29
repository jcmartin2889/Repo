package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.WFRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author lima
 * 
 */

public class WFRecordResultSetExtractor implements ResultSetExtractor
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WFRecordResultSetExtractor.java 15470 2013-03-08 15:56:33Z whittap1 $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * 
	 * @return a <code>WFRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		WFRecordDataModel record = new WFRecordDataModel();
		record.setClassOfUser(resultSet.getString(1).trim());
		record.setDescription(resultSet.getString(2).trim());
		record.setAuthorisationLevel(resultSet.getString(3).trim());

		return record;
	}

}