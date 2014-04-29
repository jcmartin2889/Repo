package com.misys.equation.common.internal.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.misys.equation.common.dao.beans.GYRecordDataModel;

/**
 * This is a helper class used by Sp framework to populate the data model.<br>
 * This class will extract data from the <code>ResultSet</code> and create a data model.
 * 
 * @author deroset
 * 
 */
public class GYRecordResultSetExtractor implements ResultSetExtractor
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1278481552197l;

	/**
	 * This class will extract data from the <code>ResultSet</code> and create a data model.
	 * 
	 * @param the
	 *            passed <code>ResultSet</code> which contains the data.
	 * @return a <code>GYRecordDataModel</code> data model.
	 */
	public Object extractData(ResultSet resultSet) throws SQLException
	{
		GYRecordDataModel record = new GYRecordDataModel();
		record.setWorkstationId(resultSet.getString(1).trim());
		record.setUserId(resultSet.getString(2).trim());
		record.setDayInMonth(resultSet.getInt(3));
		record.setTimeHhmmss(resultSet.getInt(4));
		record.setSequenceNumber(resultSet.getInt(5));
		record.setProgramRoot(resultSet.getString(6).trim());
		record.setJournalTransType(resultSet.getString(7).trim());
		record.setBranchMnem(resultSet.getString(8).trim());
		record.setPrintedFlag(resultSet.getString(9).trim());
		record.setRecoveryStatus(resultSet.getString(10).trim());
		record.setUserDefinedOptionBeingJournalled(resultSet.getString(11).trim());
		record.setApplication(resultSet.getString(12).trim());
		record.setAccount(resultSet.getString(13).trim());
		record.setAccountORCustomerIdentity(resultSet.getString(14).trim());
		record.setJournalReference(resultSet.getString(15).trim());
		record.setDateLastMaintained(resultSet.getInt(16));
		record.setWarningMessage1(resultSet.getString(17).trim());
		record.setWarningMessage2(resultSet.getString(18).trim());
		record.setWarningMessage3(resultSet.getString(19).trim());
		record.setWarningMessage4(resultSet.getString(20).trim());
		record.setWarningMessage5(resultSet.getString(21).trim());
		record.setWarningMessage6(resultSet.getString(22).trim());
		record.setErrorMessageFromRecovery(resultSet.getString(23).trim());
		record.setErrorMessageFromBackgroundTask(resultSet.getString(24).trim());
		record.setCoreSystemStatus(resultSet.getString(25).trim());
		record.setSupervisorUserId(resultSet.getString(26).trim());
		record.setSecurityMnemonic(resultSet.getString(27).trim());
		record.setPortfolioReference(resultSet.getString(28).trim());
		record.setDepotId(resultSet.getString(29).trim());
		record.setTransactionStatus(resultSet.getString(30).trim());
		record.setEnigmaIdentifier(resultSet.getString(31).trim());
		record.setEnigmaSubTransaction(resultSet.getInt(32));
		record.setLinkJobNumber(resultSet.getInt(33));
		record.setLinkTime(resultSet.getInt(34));
		record.setLinkSet(resultSet.getString(35).trim());
		record.setTransferStatus(resultSet.getString(36).trim());
		record.setMaintainedOrcancelledLater(resultSet.getString(37).trim());
		record.setApplyDuringExternalInput(resultSet.getString(38).trim());
		record.setApplyDuringRecovery(resultSet.getString(39).trim());
		record.setCreateDate(resultSet.getInt(40));
		record.setCcLinkTime(resultSet.getInt(41));
		record.setCommitIssuedEot(resultSet.getString(42).trim());
		record.setJobNumber(resultSet.getInt(43));
		record.setCcLinkSeqNo(resultSet.getInt(44));
		record.setApplicationId(resultSet.getString(45).trim());
		record.setTcpipAddress(resultSet.getString(46).trim());
		record.setInputReference(resultSet.getString(47).trim());
		record.setInputtingUserId(resultSet.getString(48).trim());
		record.setAuthorisingUserId(resultSet.getString(49).trim());

		return record;
	}
}