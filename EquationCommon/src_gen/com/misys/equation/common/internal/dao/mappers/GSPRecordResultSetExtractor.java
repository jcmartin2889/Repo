package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.GSPRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 */
public class GSPRecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1275040799722l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>GSPRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		GSPRecordDataModel record = new GSPRecordDataModel();
		record.setSearchIdentifier(resultSet.getLong(1));
		record.setSearchProperties(resultSet.getString(2).trim());
		record.setSearchValue(resultSet.getString(3).trim());

		return record;
	}
}