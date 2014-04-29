package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.CARecordDataModel;

public class CARecordResultSetExtractor implements ResultSetExtractor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CARecordResultSetExtractor.java 6928 2010-04-14 12:04:49Z MACDONP1 $";
	public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException
	{
		CARecordDataModel record = new CARecordDataModel();
		record.setBranchNumber(resultSet.getString(1));
		record.setBranchName(resultSet.getString(2));
		record.setBranchMnemonic(resultSet.getString(3));
		return record;
	}

}