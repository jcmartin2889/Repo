package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.GLURecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */

public class GLURecordResultSetExtractor implements ResultSetExtractor
{

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1258127204793l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>GLURecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{

		GLURecordDataModel record = new GLURecordDataModel();
		record.setUnitMnemonic(resultSet.getString(1).trim());
		record.setSystemID(resultSet.getString(2).trim());
		record.setUnitDescription(resultSet.getString(3).trim());
		record.setEquationLevel(resultSet.getString(4).trim());
		record.setDesktopSupported(resultSet.getString(5).trim());

		return record;
	}

}
