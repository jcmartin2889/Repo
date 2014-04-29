package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.VP1RecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class VP1RecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1279602363276l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>VP1RecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		VP1RecordDataModel record = new VP1RecordDataModel();
		record.setProductCode(resultSet.getString(1).trim());
		record.setProductDescription(resultSet.getString(2).trim());
		record.setMonthToDateVolume(resultSet.getLong(3));
		record.setDaysProcThisMonth(resultSet.getInt(4));
		record.setJanuarysAverageVol(resultSet.getLong(5));
		record.setFebruarysAverageVl(resultSet.getLong(6));
		record.setMarchsAverageVol(resultSet.getLong(7));
		record.setAprilsAverageVol(resultSet.getLong(8));
		record.setMaysAverageVol(resultSet.getLong(9));
		record.setJunesAverageVol(resultSet.getLong(10));
		record.setJulysAverageVol(resultSet.getLong(11));
		record.setAugustsAverageVol(resultSet.getLong(12));
		record.setSeptembersAverageV(resultSet.getLong(13));
		record.setOctobersAverageVol(resultSet.getLong(14));
		record.setNovembersAverageVl(resultSet.getLong(15));
		record.setDecembersAverageVl(resultSet.getLong(16));

		return record;
	}
}