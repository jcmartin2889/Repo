package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.WEHRecordDataModel;

public class WEHRecordSequenceNumberExtractor implements ResultSetExtractor
{
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WEHRecordSequenceNumberExtractor.java 13101 2012-03-28 06:15:02Z bernie.terrado $";
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1323183018244l;
	
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param resultSet the passed <code>ResultSet</code> which contains the data.
	 * @return a <code>WEHRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		WEHRecordDataModel record= new WEHRecordDataModel();
		record.setSequence(resultSet.getInt(1));
		return record;
	}
}
