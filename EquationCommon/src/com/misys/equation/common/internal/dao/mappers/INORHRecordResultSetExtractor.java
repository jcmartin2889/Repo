package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.INORHRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class INORHRecordResultSetExtractor implements ResultSetExtractor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: INORHRecordResultSetExtractor.java 4646 2009-09-07 16:28:30Z weddelc1 $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>GDRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{

		INORHRecordDataModel record = new INORHRecordDataModel();

		record.setOrderNumber(resultSet.getString(1).trim());
		record.setCustomerCode(resultSet.getString(2).trim());
		record.setDespatchDate(resultSet.getInt(3));
		record.setCutDate(resultSet.getInt(4));
		record.setDespatchType(resultSet.getString(5).trim());
		record.setCustomerOrderNumber(resultSet.getString(6).trim());
		record.setMediaType(resultSet.getString(7).trim());
		record.setDestination(resultSet.getString(8).trim());
		record.setDespatchMethod(resultSet.getString(9).trim());
		record.setGenerateSource(resultSet.getString(10).trim());
		record.setAs400TargetRelease(resultSet.getString(11).trim());
		record.setOrderText(resultSet.getString(12).trim());
		record.setParentOrder(resultSet.getString(13).trim());
		record.setMediaText(resultSet.getString(14).trim());
		record.setDespatchTypeText(resultSet.getString(15).trim());
		record.setDespatchCode(resultSet.getString(16).trim());
		record.setLicenceType(resultSet.getString(17).trim());
		record.setCurrentNumberOfObjects(resultSet.getInt(18));
		record.setSizeMB(resultSet.getInt(19));

		return record;
	}

}
