package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.APJRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class APJRecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1273209982619l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>APJRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		APJRecordDataModel record = new APJRecordDataModel();
		record.setApiReference(resultSet.getString(1).trim());
		record.setApiFileName(resultSet.getString(2).trim());
		record.setEquationApiLevel(resultSet.getString(3).trim());
		record.setApiFieldSequence(resultSet.getString(4).trim());
		record.setApiFieldName(resultSet.getString(5).trim());
		record.setApiFieldDescripton(resultSet.getString(6).trim());
		record.setApiFieldType(resultSet.getString(7).trim());
		record.setApiFieldStart(resultSet.getString(8).trim());
		record.setApiFieldEnd(resultSet.getString(9).trim());
		record.setApiFieldLength(resultSet.getString(10).trim());
		record.setApiFieldIntegers(resultSet.getString(11).trim());
		record.setApiFieldFractions(resultSet.getString(12).trim());
		record.setDbFieldName(resultSet.getString(13).trim());
		record.setDbFieldDescription(resultSet.getString(14).trim());
		record.setDbFieldType(resultSet.getString(15).trim());
		record.setDbFieldStart(resultSet.getString(16).trim());
		record.setDbFieldEnd(resultSet.getString(17).trim());
		record.setDbFieldLength(resultSet.getString(18).trim());
		record.setDbFieldIntegers(resultSet.getString(19).trim());
		record.setDbFieldFractions(resultSet.getString(20).trim());
		record.setDbFileName(resultSet.getString(21).trim());
		record.setRetrievalInstance(resultSet.getString(22).trim());
		record.setControlType(resultSet.getString(23).trim());
		record.setSubControlType(resultSet.getString(24).trim());
		record.setConversionProgram(resultSet.getString(25).trim());
		record.setMapParameter(resultSet.getString(26).trim());

		return record;
	}
}