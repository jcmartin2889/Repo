package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGAURecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAURecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 */
public class TestGAURecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1287559303729l;

	public TestGAURecordStub()
	{

	}

	/**
	 * This is method is going to set part of the kFilLibName.<br>
	 * This method can be overwritten if another library needs to be used.<br>
	 * <code>kFilLibName=  kFilLibName + unitName</code>
	 */
	public void setKLibName()
	{
		setKLibName(null);
	}

	/**
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	protected void setDao()
	{
		dao = daoFactory.getGAUDao(session, dataModel);
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
	public GAURecordDataModel getRecord()
	{
		GAURecordDataModel record = null;
		record = ((IGAURecordDao) dao).getGAURecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	public void updateRecord()
	{
		GAURecordDataModel record = null;
		this.getMyModel().setSequenceId(9688888223L);
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getSequenceId(), record.getSequenceId());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		GAURecordDataModel record = (GAURecordDataModel) dataModel;
		assertEquals(getMyModel().getSequenceId(), record.getSequenceId());
		assertEquals(getMyModel().getSessionId(), record.getSessionId());
		assertEquals(getMyModel().getUser(), record.getUser());
		assertEquals(getMyModel().getAuditTimestamp(), record.getAuditTimestamp());
		assertEquals(getMyModel().getSource(), record.getSource());
		assertEquals(getMyModel().getSourceUnit(), record.getSourceUnit());
		assertEquals(getMyModel().getSourceServer(), record.getSourceServer());
		assertEquals(getMyModel().getProcessingDate(), record.getProcessingDate());
		assertEquals(getMyModel().getUnitDefaultBranch(), record.getUnitDefaultBranch());
		assertEquals(getMyModel().getOptionId(), record.getOptionId());
		assertEquals(getMyModel().getReferenceDetailsType(), record.getReferenceDetailsType());
		assertEquals(getMyModel().getReferenceDetails(), record.getReferenceDetails());
		assertEquals(getMyModel().getStatus(), record.getStatus());
		assertEquals(getMyModel().getAcknowledgedStatus(), record.getAcknowledgedStatus());
		assertEquals(getMyModel().getToUnit(), record.getToUnit());
		assertEquals(getMyModel().getToServer(), record.getToServer());
		assertEquals(getMyModel().getPropDataSequenceNumber(), record.getPropDataSequenceNumber());
		assertEquals(getMyModel().getUnitDataSequenceNumber(), record.getUnitDataSequenceNumber());
		assertEquals(getMyModel().getGlobalLayerStatus(), record.getGlobalLayerStatus());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGAURecordDao) dao).checkIfThisGAURecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GAURecordDataModel record = new GAURecordDataModel(9688888223L);
		record.setSessionId("String_TEST");
		record.setUser("String_TES");
		record.setAuditTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
		record.setSource("S");
		record.setSourceUnit("Str");
		record.setSourceServer("String_T");
		record.setProcessingDate(1);
		record.setUnitDefaultBranch("Stri");
		record.setOptionId("Str");
		record.setReferenceDetailsType("String_TES");
		record.setReferenceDetails("String_TEST");
		record.setStatus("S");
		record.setAcknowledgedStatus("S");
		record.setToUnit("Str");
		record.setToServer("String_T");
		record.setPropDataSequenceNumber(9688888223L);
		record.setUnitDataSequenceNumber(9688888223L);
		record.setGlobalLayerStatus("S");

		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public GAURecordDataModel getMyModel()
	{
		GAURecordDataModel record = null;

		if (dataModel instanceof GAURecordDataModel)
		{
			record = (GAURecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}