package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.ACORecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 */
public class ACORecordResultSetExtractor implements ResultSetExtractor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACORecordResultSetExtractor.java 12399 2011-12-13 03:57:50Z fraramos $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>ACORecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException
	{
		ACORecordDataModel model = new ACORecordDataModel();
		model.setUserExit(resultSet.getString(1).trim());
		model.setScreen(resultSet.getInt(2));
		model.setMode(resultSet.getString(3).charAt(0));
		return model;
	}

}
