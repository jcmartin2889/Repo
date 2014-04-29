package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.EDMRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class EDMRecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1285137724583l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>EDMRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		EDMRecordDataModel record = new EDMRecordDataModel();
		record.setStartTime(resultSet.getString(1).trim());
		record.setPhase(resultSet.getString(2).trim());
		record.setProcess(resultSet.getString(3).trim());
		record.setProcessDescription(resultSet.getString(4).trim());
		record.setProcessStatus(resultSet.getString(5).trim());
		record.setJobName(resultSet.getString(6).trim());
		record.setJobNumber(resultSet.getString(7).trim());
		record.setJobUser(resultSet.getString(8).trim());
		record.setType(resultSet.getString(9).trim());
		record.setIdentifier(resultSet.getString(10).trim());
		record.setProcessingDate(resultSet.getInt(11));
		record.setEndTime(resultSet.getString(12).trim());
		record.setSubStatusDescription(resultSet.getString(13).trim());
		record.setMessage(resultSet.getString(14).trim());

		return record;
	}
}