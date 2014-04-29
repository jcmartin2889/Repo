package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.ISessionRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.SessionRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test SessionIRecord services.
 * 
 * @author deroset
 * 
 */
public class SessionRecord extends EquationTestCaseDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SessionRecord.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";

	public SessionRecord()
	{

	}

	/**
	 * 
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getSessionDao(session, dataModel);
	}

	/**
	 * This method will test if the record has been already inserted in the dataBase.
	 */
	@Override
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
	 * 
	 */
	@Override
	public SessionRecordDataModel getRecord()
	{

		SessionRecordDataModel SessionRecord = null;
		SessionRecord = ((ISessionRecordDao) dao).getSessionRecord();
		assertDataModel(SessionRecord);
		return SessionRecord;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{

		SessionRecordDataModel SessionRecord = null;

		this.getMyModel().setWarningMessages("Updated");
		dao.updateRecord();
		SessionRecord = getRecord();
		assertEquals(this.getMyModel().getWarningMessages(), SessionRecord.getWarningMessages());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{

		SessionRecordDataModel record = (SessionRecordDataModel) dataModel;
		assertEquals(getMyModel().getUserId(), record.getUserId());
		assertEquals(getMyModel().getOptionId(), record.getOptionId());
		assertEquals(getMyModel().getSessionId(), record.getSessionId());
		assertEquals(getMyModel().getTransactionId(), record.getTransactionId());
		assertEquals(getMyModel().getFunctionData(), record.getFunctionData());
		assertEquals(getMyModel().getFunctionCRMData(), record.getFunctionCRMData());
		assertEquals(getMyModel().getFunctionEFCData(), record.getFunctionEFCData());
		assertEquals(getMyModel().getFunctionEFC2Data(), record.getFunctionEFC2Data());
		assertEquals(getMyModel().getFunctionMessages(), record.getFunctionMessages());
		assertEquals(getMyModel().getWarningMessages(), record.getWarningMessages());
	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((ISessionRecordDao) dao).checkIfThisSessionRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		SessionRecordDataModel record = new SessionRecordDataModel("ALZ", "SESSIONID", "TRANSACTIONID", "USER", "X",
						"function data", "function message", "CRM data", "EFC data", "EFC 2 data", "warning messages",
						"before image");

		record.setLibrary(kFilLibName);
		this.dataModel = record;

	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public SessionRecordDataModel getMyModel()
	{

		SessionRecordDataModel SessionRecordDataModel = null;

		if (dataModel instanceof SessionRecordDataModel)
		{

			SessionRecordDataModel = (SessionRecordDataModel) dataModel;

		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return SessionRecordDataModel;
	}
}
