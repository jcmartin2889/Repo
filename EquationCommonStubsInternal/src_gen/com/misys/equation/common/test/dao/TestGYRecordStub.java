package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGYRecordDao; 
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GYRecordDataModel ;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestGYRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1278481552213l;
	
	public TestGYRecordStub()
	{

	}
	
	/**
	 * This is method is going to set part of the kFilLibName.<br>
	 * This method can be overwritten if another library needs to be used.<br>
	 * <code>kFilLibName=  kFilLibName + unitName</code>
	 */
	public void setKLibName()
	{
		setKLibName("");
	}
	
		/**	  
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	protected void setDao()
	{
		dao=daoFactory.getGYDao(session, dataModel);
	}

	/**
	 * This method will test if the record has been already inserted in the dataBase.
	 */
	public void isRecord(boolean assertValue)
	{
		boolean result;

		result = checkIfThisRecordIsInTheDB();
		if (assertValue)
		{
			assertTrue(result);
		}
		else
		{
			assertFalse(result);
		}
	}
		/**
	 * This method is going to check the getRecord dao's service.<br>
	 * The obtained result should be the same than the preset in the dao data-model.
	 */
	public GYRecordDataModel getRecord()
	{
		GYRecordDataModel record = null;
		record = ((IGYRecordDao) dao).getGYRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	public void updateRecord()
	{
		GYRecordDataModel record = null;
		this.getMyModel().setWorkstationId("Stri");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getWorkstationId(), record. getWorkstationId());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		GYRecordDataModel record = (GYRecordDataModel) dataModel;		
		assertEquals( getMyModel().getWorkstationId(), record. getWorkstationId());
		assertEquals( getMyModel().getUserId(), record. getUserId());
		assertEquals( getMyModel().getDayInMonth(), record. getDayInMonth());
		assertEquals( getMyModel().getTimeHhmmss(), record. getTimeHhmmss());
		assertEquals( getMyModel().getSequenceNumber(), record. getSequenceNumber());
		assertEquals( getMyModel().getProgramRoot(), record. getProgramRoot());
		assertEquals( getMyModel().getJournalTransType(), record. getJournalTransType());
		assertEquals( getMyModel().getBranchMnem(), record. getBranchMnem());
		assertEquals( getMyModel().getPrintedFlag(), record. getPrintedFlag());
		assertEquals( getMyModel().getRecoveryStatus(), record. getRecoveryStatus());
		assertEquals( getMyModel().getUserDefinedOptionBeingJournalled(), record. getUserDefinedOptionBeingJournalled());
		assertEquals( getMyModel().getApplication(), record. getApplication());
		assertEquals( getMyModel().getAccount(), record. getAccount());
		assertEquals( getMyModel().getAccountORCustomerIdentity(), record. getAccountORCustomerIdentity());
		assertEquals( getMyModel().getJournalReference(), record. getJournalReference());
		assertEquals( getMyModel().getDateLastMaintained(), record. getDateLastMaintained());
		assertEquals( getMyModel().getWarningMessage1(), record. getWarningMessage1());
		assertEquals( getMyModel().getWarningMessage2(), record. getWarningMessage2());
		assertEquals( getMyModel().getWarningMessage3(), record. getWarningMessage3());
		assertEquals( getMyModel().getWarningMessage4(), record. getWarningMessage4());
		assertEquals( getMyModel().getWarningMessage5(), record. getWarningMessage5());
		assertEquals( getMyModel().getWarningMessage6(), record. getWarningMessage6());
		assertEquals( getMyModel().getErrorMessageFromRecovery(), record. getErrorMessageFromRecovery());
		assertEquals( getMyModel().getErrorMessageFromBackgroundTask(), record. getErrorMessageFromBackgroundTask());
		assertEquals( getMyModel().getCoreSystemStatus(), record. getCoreSystemStatus());
		assertEquals( getMyModel().getSupervisorUserId(), record. getSupervisorUserId());
		assertEquals( getMyModel().getSecurityMnemonic(), record. getSecurityMnemonic());
		assertEquals( getMyModel().getPortfolioReference(), record. getPortfolioReference());
		assertEquals( getMyModel().getDepotId(), record. getDepotId());
		assertEquals( getMyModel().getTransactionStatus(), record. getTransactionStatus());
		assertEquals( getMyModel().getEnigmaIdentifier(), record. getEnigmaIdentifier());
		assertEquals( getMyModel().getEnigmaSubTransaction(), record. getEnigmaSubTransaction());
		assertEquals( getMyModel().getLinkJobNumber(), record. getLinkJobNumber());
		assertEquals( getMyModel().getLinkTime(), record. getLinkTime());
		assertEquals( getMyModel().getLinkSet(), record. getLinkSet());
		assertEquals( getMyModel().getTransferStatus(), record. getTransferStatus());
		assertEquals( getMyModel().getMaintainedOrcancelledLater(), record. getMaintainedOrcancelledLater());
		assertEquals( getMyModel().getApplyDuringExternalInput(), record. getApplyDuringExternalInput());
		assertEquals( getMyModel().getApplyDuringRecovery(), record. getApplyDuringRecovery());
		assertEquals( getMyModel().getCreateDate(), record. getCreateDate());
		assertEquals( getMyModel().getCcLinkTime(), record. getCcLinkTime());
		assertEquals( getMyModel().getCommitIssuedEot(), record. getCommitIssuedEot());
		assertEquals( getMyModel().getJobNumber(), record. getJobNumber());
		assertEquals( getMyModel().getCcLinkSeqNo(), record. getCcLinkSeqNo());
		assertEquals( getMyModel().getApplicationId(), record. getApplicationId());
		assertEquals( getMyModel().getTcpipAddress(), record. getTcpipAddress());
		assertEquals( getMyModel().getInputReference(), record. getInputReference());
		assertEquals( getMyModel().getInputtingUserId(), record. getInputtingUserId());
		assertEquals( getMyModel().getAuthorisingUserId(), record. getAuthorisingUserId());
		
	}
	
		@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGYRecordDao) dao).checkIfThisGYRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GYRecordDataModel record = new GYRecordDataModel( 1, 1, 33);
		record.setWorkstationId("Stri");
		record.setUserId("Stri");
		record.setProgramRoot("Stri");
		record.setJournalTransType("S");
		record.setBranchMnem("Stri");
		record.setPrintedFlag("S");
		record.setRecoveryStatus("S");
		record.setUserDefinedOptionBeingJournalled("Str");
		record.setApplication("St");
		record.setAccount("String_TEST");
		record.setAccountORCustomerIdentity("String_TEST");
		record.setJournalReference("String_TEST");
		record.setDateLastMaintained(1);
		record.setWarningMessage1("String_TEST");
		record.setWarningMessage2("String_TEST");
		record.setWarningMessage3("String_TEST");
		record.setWarningMessage4("String_TEST");
		record.setWarningMessage5("String_TEST");
		record.setWarningMessage6("String_TEST");
		record.setErrorMessageFromRecovery("String_TEST");
		record.setErrorMessageFromBackgroundTask("String_TEST");
		record.setCoreSystemStatus("S");
		record.setSupervisorUserId("Stri");
		record.setSecurityMnemonic("String_TEST");
		record.setPortfolioReference("String_TEST");
		record.setDepotId("Str");
		record.setTransactionStatus("S");
		record.setEnigmaIdentifier("Stri");
		record.setEnigmaSubTransaction(33);
		record.setLinkJobNumber(1);
		record.setLinkTime(1);
		record.setLinkSet("S");
		record.setTransferStatus("S");
		record.setMaintainedOrcancelledLater("S");
		record.setApplyDuringExternalInput("S");
		record.setApplyDuringRecovery("S");
		record.setCreateDate(1);
		record.setCcLinkTime(1);
		record.setCommitIssuedEot("S");
		record.setJobNumber(1);
		record.setCcLinkSeqNo(33);
		record.setApplicationId("String_TES");
		record.setTcpipAddress("String_TEST");
		record.setInputReference("String_TEST");
		record.setInputtingUserId("String_TES");
		record.setAuthorisingUserId("String_TES");
		
		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * @return  a cast data model
	 */
	public GYRecordDataModel getMyModel()
	{
		GYRecordDataModel record = null;

		if (dataModel instanceof GYRecordDataModel)
		{
			record = (GYRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}