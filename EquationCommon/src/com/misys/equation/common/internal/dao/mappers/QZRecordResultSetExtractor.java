package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.QZRecordDataModel;

/**
 * This is a helper class used by the Spring framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */

public class QZRecordResultSetExtractor implements ResultSetExtractor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: QZRecordResultSetExtractor.java 7325 2010-05-18 07:33:04Z JAZULM $";
	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>QZRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{

		QZRecordDataModel record = new QZRecordDataModel();

		record.setFieldName(resultSet.getString(1).trim());
		record.setFieldPosition(resultSet.getInt(2));
		record.setFieldLength(resultSet.getInt(3));
		record.setDataarea(resultSet.getString(4).trim());
		record.setOptionType(resultSet.getString(5).trim());
		record.setOptionGroup(resultSet.getString(6).trim());
		record.setFieldDescription(resultSet.getString(7).trim());
		record.setRelease(resultSet.getString(8).trim());
		record.setFieldText(resultSet.getString(9).trim());
		record.setBlankAllowed(resultSet.getString(10).trim());
		record.setMaintainable(resultSet.getString(11).trim());
		record.setValidPhase(resultSet.getString(12).trim());
		record.setValidValues(resultSet.getString(13).trim());
		record.setLowerLimit(resultSet.getString(14).trim());
		record.setUpperLimit(resultSet.getString(15).trim());
		record.setPvModule(resultSet.getString(16).trim());
		record.setPvDecode(resultSet.getString(17).trim());
		record.setPvPromptAvailable(resultSet.getString(18).trim());
		record.setRelatedFieldName(resultSet.getString(19).trim());
		record.setDefaultSystemValue(resultSet.getString(20).trim());
		return record;
	}

}
