package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.APVRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class APVRecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1283761830709l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>APVRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		APVRecordDataModel record = new APVRecordDataModel();
		record.setApiReference(resultSet.getString(1).trim());
		record.setApiFileName(resultSet.getString(2).trim());
		record.setEquationApiLevel(resultSet.getString(3).trim());
		record.setApiProgram(resultSet.getString(4).trim());
		record.setApiKeyStart(resultSet.getString(5).trim());
		record.setApiKeyLength(resultSet.getString(6).trim());
		record.setProgramRoot(resultSet.getString(7).trim());
		record.setApiDescription(resultSet.getString(8).trim());
		record.setConditions(resultSet.getString(9).trim());
		record.setType(resultSet.getString(10).trim());

		return record;
	}
}