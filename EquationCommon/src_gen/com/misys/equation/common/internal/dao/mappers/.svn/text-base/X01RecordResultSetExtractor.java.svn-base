package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.X01RecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class X01RecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1284999546713l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>X01RecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		X01RecordDataModel record = new X01RecordDataModel();
		record.setRedundant(resultSet.getString(1).trim());
		record.setTransferDate(resultSet.getInt(2));
		record.setDebitAccountBranch(resultSet.getString(3).trim());
		record.setDebitAccountBasic(resultSet.getString(4).trim());
		record.setDebitAccountSuffix(resultSet.getString(5).trim());
		record.setCreditAccountBranch(resultSet.getString(6).trim());
		record.setCreditAccountBasic(resultSet.getString(7).trim());
		record.setCreditAccountSuffix(resultSet.getString(8).trim());
		record.setPosting1Date(resultSet.getInt(9));
		record.setPosting1Branch(resultSet.getString(10).trim());
		record.setPosting1BatchId(resultSet.getString(11).trim());
		record.setPosting1Sequence(resultSet.getInt(12));
		record.setPosting2Date(resultSet.getInt(13));
		record.setPosting2Branch(resultSet.getString(14).trim());
		record.setPosting2BatchId(resultSet.getString(15).trim());
		record.setPosting2Sequence(resultSet.getInt(16));
		record.setPosting3Date(resultSet.getInt(17));
		record.setPosting3Branch(resultSet.getString(18).trim());
		record.setPosting3BatchId(resultSet.getString(19).trim());
		record.setPosting3Sequence(resultSet.getInt(20));
		record.setPosting4Date(resultSet.getInt(21));
		record.setPosting4Branch(resultSet.getString(22).trim());
		record.setPosting4BatchId(resultSet.getString(23).trim());
		record.setPosting4Sequence(resultSet.getInt(24));
		record.setFontisTransfer(resultSet.getString(25).trim());
		record.setStandingOrderTransfer(resultSet.getString(26).trim());
		record.setTransferReference(resultSet.getString(27).trim());

		return record;
	}
}