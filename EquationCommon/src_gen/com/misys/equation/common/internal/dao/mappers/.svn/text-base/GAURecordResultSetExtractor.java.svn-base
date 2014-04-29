package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.GAURecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class GAURecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1275047167618l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>GAURecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		GAURecordDataModel record = new GAURecordDataModel();
		record.setSequenceId(resultSet.getLong(1));
		record.setSessionId(resultSet.getString(2).trim());
		record.setUser(resultSet.getString(3).trim());
		record.setAuditTimestamp(resultSet.getTimestamp(4));
		record.setSource(resultSet.getString(5).trim());
		record.setSourceUnit(resultSet.getString(6).trim());
		record.setSourceServer(resultSet.getString(7).trim());
		record.setProcessingDate(resultSet.getInt(8));
		record.setUnitDefaultBranch(resultSet.getString(9).trim());
		record.setOptionId(resultSet.getString(10).trim());
		record.setReferenceDetailsType(resultSet.getString(11).trim());
		record.setReferenceDetails(resultSet.getString(12).trim());
		record.setStatus(resultSet.getString(13).trim());
		record.setAcknowledgedStatus(resultSet.getString(14).trim());
		record.setToUnit(resultSet.getString(15).trim());
		record.setToServer(resultSet.getString(16).trim());
		record.setPropDataSequenceNumber(resultSet.getLong(17));
		record.setUnitDataSequenceNumber(resultSet.getLong(18));
		record.setGlobalLayerStatus(resultSet.getString(19).trim());

		return record;
	}
}