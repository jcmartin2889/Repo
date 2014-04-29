package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.NE1RecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class NE1RecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1272538474903l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>NE1RecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		NE1RecordDataModel record = new NE1RecordDataModel();
		record.setExternalAcNumber(resultSet.getString(1).trim());
		record.setAccountBranch(resultSet.getString(2).trim());
		record.setBasicNumber(resultSet.getString(3).trim());
		record.setAccountSuffix(resultSet.getString(4).trim());
		record.setIban(resultSet.getString(5).trim());

		return record;
	}
}