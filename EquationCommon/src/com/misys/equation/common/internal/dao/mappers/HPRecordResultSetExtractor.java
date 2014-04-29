package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.HPRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */

public class HPRecordResultSetExtractor implements ResultSetExtractor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HPRecordResultSetExtractor.java 6928 2010-04-14 12:04:49Z MACDONP1 $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>HPRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{

		HPRecordDataModel record = new HPRecordDataModel();

		record.setCountryCode(resultSet.getString(1).trim());
		record.setGroupName(resultSet.getString(2).trim());
		record.setCusMnem(resultSet.getString(3).trim());
		record.setCusLoc(resultSet.getString(4).trim());
		record.setInThousand(resultSet.getString(5).trim());
		return record;
	}

}
