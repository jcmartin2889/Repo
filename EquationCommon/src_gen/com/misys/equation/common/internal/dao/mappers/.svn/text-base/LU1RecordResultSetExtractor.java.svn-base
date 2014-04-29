package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.LU1RecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class LU1RecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1280744205023l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>LU1RecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		LU1RecordDataModel record = new LU1RecordDataModel();
		record.setBranchNumber(resultSet.getString(1).trim());
		record.setCycleStatus(resultSet.getString(2).trim());
		record.setLinkStatus(resultSet.getString(3).trim());
		record.setOperationalStatus(resultSet.getString(4).trim());
		record.setDownloadStatus(resultSet.getString(5).trim());
		record.setCycleStatusOverridden(resultSet.getString(6).trim());
		record.setCashierVersion(resultSet.getString(7).trim());

		return record;
	}
}