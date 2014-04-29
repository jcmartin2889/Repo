package com.misys.equation.common.internal.dao.mappers;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.WECRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class WECRecordResultSetExtractor implements ResultSetExtractor
{	
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WECRecordResultSetExtractor.java 13102 2012-03-28 06:22:08Z bernie.terrado $";
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1323183018088l;
	
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param resultSet The passed <code>ResultSet</code> which contains the data.
	 * 
	 * @return a <code>WECRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		WECRecordDataModel record= new WECRecordDataModel();
		record.setOptionMnemonic(resultSet.getString(1).trim());
		record.setRequiresMakerChecker(resultSet.getString(2).trim());
		record.setCompleteByChecker(resultSet.getString(3).trim());

		return record;
	}
}
