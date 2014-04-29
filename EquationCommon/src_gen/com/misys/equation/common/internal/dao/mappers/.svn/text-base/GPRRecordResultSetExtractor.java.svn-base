package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.GPRRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class GPRRecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1274173436547l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>GPRRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		GPRRecordDataModel record = new GPRRecordDataModel();
		record.setIdentifier(resultSet.getString(1).trim());
		record.setDescription(resultSet.getString(2).trim());
		record.setMonitorOrAdHoc(resultSet.getString(3).trim());
		record.setExportGroup(resultSet.getString(4).trim());
		record.setExportType(resultSet.getString(5).trim());
		record.setLinkedCustomers(resultSet.getString(6).trim());
		record.setMonitorEnabled(resultSet.getString(7).trim());
		record.setMonitorAdditions(resultSet.getString(8).trim());
		record.setMonitorMaintenance(resultSet.getString(9).trim());
		record.setMonitorDeletions(resultSet.getString(10).trim());
		record.setMonitorAllUnits(resultSet.getString(11).trim());
		record.setConditions(resultSet.getString(12).trim());
		record.setAutomaticOrManualApply(resultSet.getString(13).trim());
		record.setPropagateToAllUnits(resultSet.getString(14).trim());
		record.setIncludeAllFields(resultSet.getString(15).trim());

		return record;
	}
}