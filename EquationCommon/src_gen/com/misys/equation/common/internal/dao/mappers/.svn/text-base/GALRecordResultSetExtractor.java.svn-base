package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.GALRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class GALRecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1270638835383l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>GALRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		GALRecordDataModel record = new GALRecordDataModel();
		record.setSequenceId(resultSet.getLong(1));
		record.setActionSequence(resultSet.getInt(2));
		record.setToUnit(resultSet.getString(3).trim());
		record.setToServer(resultSet.getString(4).trim());
		record.setUserId(resultSet.getString(5).trim());
		record.setWorkstationId(resultSet.getString(6).trim());
		record.setDayInMonth(resultSet.getInt(7));
		record.setTimeHhmmss(resultSet.getInt(8));
		record.setSequenceNumber(resultSet.getInt(9));

		return record;
	}
}