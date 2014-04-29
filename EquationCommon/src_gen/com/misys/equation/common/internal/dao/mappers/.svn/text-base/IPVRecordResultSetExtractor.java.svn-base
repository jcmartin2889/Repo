package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.IPVRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 */
public class IPVRecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1278481552197l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>IPVRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		IPVRecordDataModel record = new IPVRecordDataModel();
		record.setReferenceId(resultSet.getString(1).trim());
		record.setApplicationId(resultSet.getString(2).trim());
		record.setSequence(resultSet.getInt(3));
		record.setCreateDate(resultSet.getInt(4));
		record.setCcLinkTime(resultSet.getInt(5));
		record.setCcLinkSeqNo(resultSet.getInt(6));
		record.setJobNumber(resultSet.getInt(7));
		record.setUserId(resultSet.getString(8).trim());
		record.setOptionId(resultSet.getString(9).trim());
		record.setProcessedDate(resultSet.getInt(10));
		record.setExpiryDate(resultSet.getInt(11));
		record.setResponse(resultSet.getBytes(12));

		return record;
	}
}