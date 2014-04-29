package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.INORDRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class INORDRecordResultSetExtractor implements ResultSetExtractor
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: INORDRecordResultSetExtractor.java 4646 2009-09-07 16:28:30Z weddelc1 $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>INORDRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{

		INORDRecordDataModel record = new INORDRecordDataModel();

		record.setOrderNumber(resultSet.getString(1).trim());
		record.setProductCode(resultSet.getString(2).trim());
		record.setComponentType1(resultSet.getString(3).trim());
		record.setMachine(resultSet.getString(4).trim());
		record.setInstallSequence(resultSet.getInt(5));
		record.setParentReleaseFlag(resultSet.getString(6).trim());
		record.setComponent(resultSet.getString(7).trim());
		record.setComponentDescription(resultSet.getString(8).trim());
		record.setComponentMachine(resultSet.getString(9).trim());
		record.setComponentType2(resultSet.getString(10).trim());
		record.setNumberOfObjects(resultSet.getInt(11));
		record.setCurrentNumberOfObjects(resultSet.getInt(12));
		record.setSizeMB(resultSet.getInt(13));
		record.setSelectionField(resultSet.getString(14).trim());
		record.setLibLibraryName(resultSet.getString(15).trim());
		record.setFilLibraryName(resultSet.getString(16).trim());
		record.setWrkLibraryName(resultSet.getString(17).trim());
		record.setInpLibraryName(resultSet.getString(18).trim());

		return record;
	}

}
