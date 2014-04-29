package com.misys.equation.common.internal.dao.mappers;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.WEHRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class WEHRecordResultSetExtractor implements ResultSetExtractor
{	
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WEHRecordResultSetExtractor.java 13101 2012-03-28 06:15:02Z bernie.terrado $";
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1323183018244l;
	
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param resultSet The passed <code>ResultSet</code> which contains the data.
	 * @return a <code>WEHRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		WEHRecordDataModel record= new WEHRecordDataModel();
		record.setMaker(resultSet.getString(1).trim());
		record.setChecker(resultSet.getString(2).trim());
		record.setSessionId(resultSet.getString(3).trim());
		record.setTransactionId(resultSet.getString(4).trim());
		record.setSequence(resultSet.getInt(5));
		record.setOptionId(resultSet.getString(6).trim());
		record.setStatus(resultSet.getString(7).trim());
		record.setProcessedDate(resultSet.getInt(8));
		record.setProcessedTime(resultSet.getInt(9));
		record.setReason(resultSet.getString(10).trim());
		record.setBranch(resultSet.getString(11).trim());
		record.setCustomerNumber(resultSet.getString(12).trim());
		record.setAccount(resultSet.getString(13).trim());
		record.setReference(resultSet.getString(14).trim());
		record.setSecondAccount(resultSet.getString(15).trim());
		record.setAdditionalRef(resultSet.getString(16).trim());

		return record;
	}
}
