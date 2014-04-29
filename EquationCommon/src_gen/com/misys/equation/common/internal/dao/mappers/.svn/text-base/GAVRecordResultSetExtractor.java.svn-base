package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.GAVRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class GAVRecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1275040799082l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>GAVRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		GAVRecordDataModel record = new GAVRecordDataModel();
		record.setSequenceId(resultSet.getLong(1));
		record.setSourceUnitCcsid(resultSet.getInt(2));
		record.setSourceUnit(resultSet.getString(3).trim());
		record.setSourceServerId(resultSet.getString(4).trim());
		record.setUserId(resultSet.getString(5).trim());
		record.setWorkstationId(resultSet.getString(6).trim());
		record.setDayInMonth(resultSet.getInt(7));
		record.setTimeHhmmss(resultSet.getInt(8));
		record.setSequenceNumber(resultSet.getInt(9));
		record.setType(resultSet.getString(10).trim());
		record.setSourceGzImage(resultSet.getBytes(11));
		record.setSourceGsImage(resultSet.getBytes(12));
		record.setApiFormat(resultSet.getString(13).trim());
		record.setPropagationSetId(resultSet.getString(14).trim());
		record.setPropagationRuleId(resultSet.getString(15).trim());
		record.setConditions(resultSet.getString(16).trim());
		record.setExcludedFields(resultSet.getString(17).trim());

		return record;
	}
}