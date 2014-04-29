package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.GSRRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 */
public class GSRRecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1287559304166l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>GSRRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		GSRRecordDataModel record = new GSRRecordDataModel();
		record.setSearchedBy(resultSet.getString(1).trim());
		record.setSearchedInSystem(resultSet.getString(2).trim());
		record.setSearchedInUnit(resultSet.getString(3).trim());
		record.setSearchType(resultSet.getString(4).trim());
		record.setSearchIdentifier(resultSet.getLong(5));
		record.setTimestamp(resultSet.getTimestamp(6));

		return record;
	}
}