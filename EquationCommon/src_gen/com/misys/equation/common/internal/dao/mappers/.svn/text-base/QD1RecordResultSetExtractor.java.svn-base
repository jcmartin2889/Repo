package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.QD1RecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */

public class QD1RecordResultSetExtractor implements ResultSetExtractor
{

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1266215593698l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>QD1RecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		// maye: added this variable for invoking setLibrary method, i.e. patterned after CHRecordResultSetExtractor
		// int ccsid = 37;

		QD1RecordDataModel record = new QD1RecordDataModel();

		// maye: added this line to ensure that KFILxxx lib will be referred to instead of KGRPMNG group lib
		// patterned after CHRecordResultSetExtractor
		// record.setLibrary(Toolbox.convertAS400TextIntoCCSID(resultSet.getBytes(3), 10, ccsid).trim());

		record.setSystemOptionGroup(resultSet.getString(1).trim());
		record.setSystemOptionGroupDescription(resultSet.getString(2).trim());

		return record;
	}

}
