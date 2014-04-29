package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.CLDRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class CLDRecordResultSetExtractor implements ResultSetExtractor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CLDRecordResultSetExtractor.java 8975 2010-09-01 14:42:39Z deroset $";
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1275045708060l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>CLDRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		CLDRecordDataModel record = new CLDRecordDataModel();
		record.setGlobalCustomerId(resultSet.getString(1).trim());
		record.setSequenceNumber(resultSet.getInt(2));
		record.setSystemName(resultSet.getString(3).trim());
		record.setCustomerOwningUnit(resultSet.getString(4).trim());
		record.setCustomerNumber(resultSet.getString(5).trim());
		record.setMasterFlag(resultSet.getString(6).trim().equals("Y"));
		record.setSyncID(resultSet.getString(7).trim());

		return record;
	}
}
