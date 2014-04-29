package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.XVRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */

public class XVRecordResultSetExtractor implements ResultSetExtractor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: XVRecordResultSetExtractor.java 6928 2010-04-14 12:04:49Z MACDONP1 $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>XVRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{

		XVRecordDataModel record = new XVRecordDataModel();

		record.setBlankAllowed(resultSet.getString(1).trim());
		record.setDateLastMaintainedD(resultSet.getInt(2));
		record.setDateLastMaintainedM(resultSet.getInt(3));
		record.setDateLastMaintainedY(resultSet.getInt(4));
		record.setDecode(resultSet.getString(5).trim());
		record.setFieldName(resultSet.getString(6).trim());
		record.setLastRecordIndicator(resultSet.getString(7).trim());
		record.setNewCode(resultSet.getString(8).trim());
		record.setPromptAvailable(resultSet.getString(9).trim());
		record.setSysProgram(resultSet.getString(10).trim());
		record.setPvModule(resultSet.getString(11).trim());
		record.setSeqNo(resultSet.getInt(12));
		record.setUpdateLevelNo(resultSet.getInt(13));

		return record;
	}

}
