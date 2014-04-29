package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.GPX1RecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class GPX1RecordResultSetExtractorImp implements ResultSetExtractor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GPX1RecordResultSetExtractorImp.java 9555 2010-10-20 16:27:55Z MACDONP1 $";
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1258630646747l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>GPX1RecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{

		GPX1RecordDataModel record = new GPX1RecordDataModel();

		record.setUnitMnemonic(resultSet.getString(1).trim());
		record.setSystemName(resultSet.getString(2).trim());
		record.setUnitType(resultSet.getString(3).trim());
		record.setUnitDescription(resultSet.getString(4).trim());

		return record;
	}
}
