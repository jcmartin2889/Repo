package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.GAARecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class GAARecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1270638835227l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>GAARecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		GAARecordDataModel record = new GAARecordDataModel();
		record.setSequenceId(resultSet.getLong(1));
		record.setRetrySequence(resultSet.getInt(2));
		record.setRetryUser(resultSet.getString(3).trim());
		record.setApplyType(resultSet.getString(4).trim());
		record.setApplyStatus(resultSet.getString(5).trim());
		record.setApplicationMessages(resultSet.getString(6).trim());
		record.setApplyTimestamp(resultSet.getTimestamp(7));

		return record;
	}
}