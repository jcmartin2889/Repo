package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.OCRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */

public class OCRecordResultSetExtractor implements ResultSetExtractor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OCRecordResultSetExtractor.java 15470 2013-03-08 15:56:33Z whittap1 $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>OCRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{

		OCRecordDataModel oCRecordDataModel = new OCRecordDataModel();

		oCRecordDataModel.setUserId(resultSet.getString(1).trim());
		oCRecordDataModel.setOptionId(resultSet.getString(2).trim());
		oCRecordDataModel.setBranch(resultSet.getString(3).trim());
		oCRecordDataModel.setLanguage(resultSet.getString(4).trim());
		oCRecordDataModel.setLimitAuth(resultSet.getString(5).trim());
		oCRecordDataModel.setUserName(resultSet.getString(6).trim());
		oCRecordDataModel.setBranchNo(resultSet.getString(7).trim());
		oCRecordDataModel.setPhoneNo(resultSet.getString(8).trim());
		oCRecordDataModel.setExtNo(resultSet.getString(9).trim());
		oCRecordDataModel.setAvailCode(resultSet.getString(10).trim());
		oCRecordDataModel.setUserClass(resultSet.getString(11).trim());
		oCRecordDataModel.setAuthAnyBranch(resultSet.getString(12).trim());
		oCRecordDataModel.setAuthClass(resultSet.getString(13).trim());
		oCRecordDataModel.setAuthLevel(resultSet.getString(14).trim());
		oCRecordDataModel.setShowLocalTime(resultSet.getString(15).trim());
		oCRecordDataModel.setBankFusionUserId(resultSet.getString(16).trim());
		oCRecordDataModel.setOsUserId(resultSet.getString(17).trim());

		return oCRecordDataModel;
	}

}
