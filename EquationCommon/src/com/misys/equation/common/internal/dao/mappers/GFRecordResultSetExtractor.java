package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.GFRecordDataModel;

public class GFRecordResultSetExtractor implements ResultSetExtractor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GFRecordResultSetExtractor.java 6928 2010-04-14 12:04:49Z MACDONP1 $";
	public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException
	{
		GFRecordDataModel record = new GFRecordDataModel();
		record.setCustomerMnemonic(resultSet.getString(1));
		record.setCustomerLocation(resultSet.getString(2));
		record.setCustomerFullName(resultSet.getString(3));
		record.setCustomerNumber(resultSet.getString(4));
		record.setCustomerShortName(resultSet.getString(5));
		return record;
	}

}
