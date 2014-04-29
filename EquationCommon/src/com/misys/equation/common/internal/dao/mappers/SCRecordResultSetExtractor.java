package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.SCRecordDataModel;

public class SCRecordResultSetExtractor implements ResultSetExtractor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SCRecordResultSetExtractor.java 9659 2010-11-02 17:25:07Z MACDONP1 $";

	/**
	 * This method will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * 
	 * @return SCRecordDataModel - data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		final SCRecordDataModel record = new SCRecordDataModel();
		record.setAccountBranch(resultSet.getString(1).trim());
		record.setBasicNumber(resultSet.getString(2).trim());
		record.setAccountSuffix(resultSet.getString(3).trim());
		record.setShortName(resultSet.getString(4).trim());
		return record;
	}
}