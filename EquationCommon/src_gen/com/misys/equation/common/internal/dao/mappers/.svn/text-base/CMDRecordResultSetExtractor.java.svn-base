package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.CMDRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class CMDRecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1282892038580l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>CMDRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		CMDRecordDataModel record = new CMDRecordDataModel();
		record.setIdentifier(resultSet.getString(1).trim());
		record.setName(resultSet.getString(2).trim());
		record.setDescription(resultSet.getString(3).trim());
		record.setOptionId(resultSet.getString(4).trim());
		record.setAvailableForExternalInput(resultSet.getString(5).trim());
		record.setAvailableForRecovery(resultSet.getString(6).trim());
		record.setManagementConsoleUserExit(resultSet.getString(7).trim());
		record.setParameters(resultSet.getString(8).trim());
		record.setRelease(resultSet.getString(9).trim());
		record.setValidPhases(resultSet.getString(10).trim());
		record.setGenericConditions(resultSet.getString(11).trim());
		record.setCategory(resultSet.getString(12).trim());
		record.setParameterDescription(resultSet.getString(13).trim());

		return record;
	}
}