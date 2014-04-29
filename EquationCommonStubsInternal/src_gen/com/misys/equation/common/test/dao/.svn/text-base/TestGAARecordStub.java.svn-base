package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGAARecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAARecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestGAARecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1270638835274l;

	public TestGAARecordStub()
	{

	}

	/**
	 * This is method is going to set part of the kFilLibName.<br>
	 * This method can be overwritten if another library needs to be used.<br>
	 * <code>kFilLibName=  kFilLibName + unitName</code>
	 */
	@Override
	public void setKLibName()
	{
		setKLibName("KGRPMNG");
	}

	/**
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getGAADao(session, dataModel);
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
	 */
	@Override
	public GAARecordDataModel getRecord()
	{
		GAARecordDataModel record = null;
		record = ((IGAARecordDao) dao).getGAARecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		GAARecordDataModel record = null;
		this.getMyModel().setSequenceId(9688888223L);
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getSequenceId(), record.getSequenceId());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		GAARecordDataModel record = (GAARecordDataModel) dataModel;
		assertEquals(getMyModel().getSequenceId(), record.getSequenceId());
		assertEquals(getMyModel().getRetrySequence(), record.getRetrySequence());
		assertEquals(getMyModel().getRetryUser(), record.getRetryUser());
		assertEquals(getMyModel().getApplyType(), record.getApplyType());
		assertEquals(getMyModel().getApplyStatus(), record.getApplyStatus());
		assertEquals(getMyModel().getApplicationMessages(), record.getApplicationMessages());
		assertEquals(getMyModel().getApplyTimestamp(), record.getApplyTimestamp());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGAARecordDao) dao).checkIfThisGAARecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GAARecordDataModel record = new GAARecordDataModel(9688888223L, 33);
		record.setRetryUser("String_TES");
		record.setApplyType("S");
		record.setApplyStatus("S");
		record.setApplicationMessages("String_TEST");
		record.setApplyTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));

		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public GAARecordDataModel getMyModel()
	{
		GAARecordDataModel record = null;

		if (dataModel instanceof GAARecordDataModel)
		{
			record = (GAARecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}