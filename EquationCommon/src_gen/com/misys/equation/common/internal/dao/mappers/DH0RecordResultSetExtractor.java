package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.DH0RecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */

public class DH0RecordResultSetExtractor implements ResultSetExtractor
{

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1263561056572l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>DH0RecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{

		DH0RecordDataModel record = new DH0RecordDataModel();
		record.setAnalysisCode(resultSet.getString(1).trim());
		record.setAccountMnemonic(resultSet.getString(2).trim());
		record.setAcType(resultSet.getString(3).trim());
		record.setCustomerNumber(resultSet.getString(4).trim());
		record.setCustomerType(resultSet.getString(5).trim());
		record.setTcd(resultSet.getString(6).trim());
		record.setDefaultInternalAcNoDescription(resultSet.getString(7).trim());
		record.setDhdlm(resultSet.getInt(8));
		record.setTcdrt(resultSet.getString(9).trim());
		record.setShortName(resultSet.getString(10).trim());
		record.setUpdateLevelNo(resultSet.getInt(11));
		record.setPostContraTran(resultSet.getString(12).trim());
		record.setAccountTypeDescription(resultSet.getString(13).trim());

		return record;
	}

}
