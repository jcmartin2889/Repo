package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.GAZRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */

public class GAZRecordResultSetExtractor implements ResultSetExtractor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GAZRecordResultSetExtractor.java 6928 2010-04-14 12:04:49Z MACDONP1 $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>GAZRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{

		GAZRecordDataModel record = new GAZRecordDataModel();

		record.setOptionId(resultSet.getString(1).trim());
		record.setFieldId(resultSet.getString(2).trim());
		record.setPvId(resultSet.getString(3).trim());
		record.setType(resultSet.getString(4).trim());
		record.setClassName(resultSet.getString(5).trim());
		record.setClassByte(resultSet.getBytes(6));

		return record;
	}

}
