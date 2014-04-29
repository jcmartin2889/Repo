package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.MPMRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class MPMRecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1276834713183l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>MPMRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		MPMRecordDataModel record = new MPMRecordDataModel();
		record.setCodeFile(resultSet.getString(1).trim());
		record.setMasterValue(resultSet.getString(2).trim());
		record.setUnitMnemonic(resultSet.getString(3).trim());
		record.setServerName(resultSet.getString(4).trim());
		record.setUnitValue(resultSet.getString(5).trim());

		return record;
	}
}