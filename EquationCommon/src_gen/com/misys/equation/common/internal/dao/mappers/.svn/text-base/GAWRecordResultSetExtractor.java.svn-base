package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.GAWRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class GAWRecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1280458525488l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>GAWRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		GAWRecordDataModel record = new GAWRecordDataModel();
		record.setRecordType(resultSet.getString(1).trim());
		record.setSequenceId(resultSet.getLong(2));
		record.setUserId(resultSet.getString(3).trim());
		record.setWorkstationId(resultSet.getString(4).trim());
		record.setDayInMonth(resultSet.getInt(5));
		record.setTimeHhmmss(resultSet.getInt(6));
		record.setSequenceNumber(resultSet.getInt(7));

		return record;
	}
}