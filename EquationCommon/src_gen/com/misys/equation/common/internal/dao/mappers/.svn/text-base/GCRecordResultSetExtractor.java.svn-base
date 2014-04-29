package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.GCRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class GCRecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1276150829558l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>GCRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		GCRecordDataModel record = new GCRecordDataModel();
		record.setMenuId(resultSet.getString(1).trim());
		record.setMenuTitle(resultSet.getString(2).trim());
		record.setC01(resultSet.getString(3).trim());
		record.setFid1(resultSet.getString(4).trim());
		record.setFid2(resultSet.getString(5).trim());
		record.setFid3(resultSet.getString(6).trim());
		record.setFid4(resultSet.getString(7).trim());
		record.setFid5(resultSet.getString(8).trim());
		record.setFid6(resultSet.getString(9).trim());
		record.setFid7(resultSet.getString(10).trim());
		record.setFid8(resultSet.getString(11).trim());
		record.setFid9(resultSet.getString(12).trim());
		record.setDte(resultSet.getInt(13));
		record.setOptionMenuType(resultSet.getString(14).trim());

		return record;
	}
}