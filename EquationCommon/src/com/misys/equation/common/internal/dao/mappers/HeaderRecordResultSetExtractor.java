package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.HeaderRecordDataModel;

public class HeaderRecordResultSetExtractor implements ResultSetExtractor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HeaderRecordResultSetExtractor.java 10091 2010-12-03 17:12:30Z MACDONP1 $";

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>HeaderRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		HeaderRecordDataModel record = new HeaderRecordDataModel();
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

		// do not trim since AUTO APLLY == ' '
		record.setStatus(resultSet.getString(13));

		record.setAcknowledgedStatus(resultSet.getString(14).trim());
		record.setToUnit(resultSet.getString(15).trim());
		record.setToServer(resultSet.getString(16).trim());
		record.setPropDataSequenceNumber(resultSet.getLong(17));
		record.setUnitDatasequenceNumber(resultSet.getLong(18));
		record.setGlobalLayerStatus(resultSet.getString(19).trim());
		record.setLastUpdate(resultSet.getTimestamp(20));
		record.setLastAction(resultSet.getString(21));
		record.setLastRetrySeq(resultSet.getInt(22));

		return record;
	}
}
