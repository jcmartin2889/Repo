package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.CHRecordDataModel;
import com.misys.equation.common.utilities.Toolbox;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class CHRecordResultSetExtractor implements ResultSetExtractor
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CHRecordResultSetExtractor.java 4646 2009-09-07 16:28:30Z weddelc1 $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>CHRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		int ccsid = 37;

		CHRecordDataModel record = new CHRecordDataModel();

		record.setEnhancementMnemonic(Toolbox.convertAS400TextIntoCCSID(resultSet.getBytes(1), 10, ccsid).trim());
		record.setEnhancementDescription(resultSet.getString(2).trim());
		record.setEnhancementLibraryName(Toolbox.convertAS400TextIntoCCSID(resultSet.getBytes(3), 10, ccsid).trim());
		record.setEnhancementInstalledToBase(Toolbox.convertAS400TextIntoCCSID(resultSet.getBytes(4), 1, ccsid).trim());
		record.setEncryptedEnhancementMnemonic(Toolbox.convertAS400TextIntoCCSID(resultSet.getBytes(5), 10, ccsid).trim());
		record.setInstallationDate(resultSet.getInt(6));
		record.setInstallationTime(resultSet.getInt(7));
		record.setEnhancementLevel(Toolbox.convertAS400TextIntoCCSID(resultSet.getBytes(8), 1, ccsid).trim());
		record.setEnhancementUpgrade(Toolbox.convertAS400TextIntoCCSID(resultSet.getBytes(9), 1, ccsid).trim());

		return record;
	}
}
