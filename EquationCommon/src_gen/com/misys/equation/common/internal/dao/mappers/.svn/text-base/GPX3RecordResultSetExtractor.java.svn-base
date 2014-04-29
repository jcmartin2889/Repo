package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.GPX3RecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */

public class GPX3RecordResultSetExtractor implements ResultSetExtractor
{

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1258630647232l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>GPX3RecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{

		GPX3RecordDataModel record = new GPX3RecordDataModel();
		record.setGroupMnemonic(resultSet.getString(1).trim());
		record.setUnitMnemonic(resultSet.getString(2).trim());
		record.setUnitSequence(resultSet.getInt(3));
		record.setSystemName(resultSet.getString(4).trim());
		record.setUnitType(resultSet.getString(5).trim());
		record.setBranchNumber(resultSet.getString(6).trim());
		record.setBranchMnemonic(resultSet.getString(7).trim());

		return record;
	}

}
