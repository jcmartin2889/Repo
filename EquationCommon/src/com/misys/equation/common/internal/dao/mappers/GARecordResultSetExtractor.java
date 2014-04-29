package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.GARecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class GARecordResultSetExtractor implements ResultSetExtractor
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GARecordResultSetExtractor.java 6928 2010-04-14 12:04:49Z MACDONP1 $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>GARecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{

		GARecordDataModel gaRecordDataModel = new GARecordDataModel();

		gaRecordDataModel.setOptionId(resultSet.getString(1).trim());
		gaRecordDataModel.setProgramName(resultSet.getString(2).trim());
		gaRecordDataModel.setProgramTitle(resultSet.getString(3).trim());
		gaRecordDataModel.setUserDefinedKeysAllowed(resultSet.getString(4).trim());
		gaRecordDataModel.setUserDefinedAndPromptable(resultSet.getString(5).trim());
		gaRecordDataModel.setPcProgramName(resultSet.getString(6).trim());
		gaRecordDataModel.setExtendedInput(resultSet.getString(7).trim());
		gaRecordDataModel.setApplication(resultSet.getString(8).trim());

		return gaRecordDataModel;
	}

}
