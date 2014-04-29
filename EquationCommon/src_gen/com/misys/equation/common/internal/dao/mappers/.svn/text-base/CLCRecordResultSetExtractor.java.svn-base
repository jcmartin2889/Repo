package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.CLCRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class CLCRecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1271749368825l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>CLCRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		CLCRecordDataModel record = new CLCRecordDataModel();
		record.setSynchId(resultSet.getString(1).trim());
		record.setDescription(resultSet.getString(2).trim());
		record.setSynchMasterBasicDetails(resultSet.getString(3).trim());
		record.setSynchMasterOtherDetails(resultSet.getString(4).trim());
		record.setSynchMasterFreeFormatDetails(resultSet.getString(5).trim());
		record.setSynchMasterAddressDetails(resultSet.getString(6).trim());
		record.setSynchMasterExtendedDetails(resultSet.getString(7).trim());
		record.setSynchMasterACDetails(resultSet.getString(8).trim());
		record.setSynchNonMasterBasicDetails(resultSet.getString(9).trim());
		record.setSynchNonMasterOtherDetails(resultSet.getString(10).trim());
		record.setSynchNonMasterFreeFormatDetails(resultSet.getString(11).trim());
		record.setSynchNonMasterAddressDetails(resultSet.getString(12).trim());
		record.setSynchNonMasterExtendedDetails(resultSet.getString(13).trim());
		record.setSynchNonMasterACDetails(resultSet.getString(14).trim());

		return record;
	}
}