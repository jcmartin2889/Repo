package com.misys.equation.common.ant.builder.helpText.backEnd;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.ant.builder.helpText.models.GAERecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class GAERecordResultSetExtractor implements ResultSetExtractor
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GAERecordResultSetExtractor.java 4581 2009-09-02 13:53:07Z esther.williams $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>GAERecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		GAERecordDataModel gAERecord = new GAERecordDataModel();

		gAERecord.setRoot(resultSet.getString(1).trim());

		if (resultSet.getString(2).trim().equals(""))
		{
			gAERecord.setId(resultSet.getString(6).trim());
		}
		else
		{
			gAERecord.setId(resultSet.getString(2).trim());
		}

		gAERecord.setDescription(resultSet.getString(3).trim());
		gAERecord.setKeys(resultSet.getString(4).trim());
		gAERecord.setType(resultSet.getString(5).trim());
		gAERecord.setHeaderJournalFileName(resultSet.getString(7).trim());
		gAERecord.setDetailJournalFileName(resultSet.getString(8).trim());

		return gAERecord;
	}

}