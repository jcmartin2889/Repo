package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGALRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GALRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestGALRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1270638835430l;

	public TestGALRecordStub()
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
		dao = daoFactory.getGALDao(session, dataModel);
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
	public GALRecordDataModel getRecord()
	{
		GALRecordDataModel record = null;
		record = ((IGALRecordDao) dao).getGALRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		GALRecordDataModel record = null;
		this.getMyModel().setSequenceId(9688888223L);
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getSequenceId(), record.getSequenceId());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		GALRecordDataModel record = (GALRecordDataModel) dataModel;
		assertEquals(getMyModel().getSequenceId(), record.getSequenceId());
		assertEquals(getMyModel().getActionSequence(), record.getActionSequence());
		assertEquals(getMyModel().getToUnit(), record.getToUnit());
		assertEquals(getMyModel().getToServer(), record.getToServer());
		assertEquals(getMyModel().getUserId(), record.getUserId());
		assertEquals(getMyModel().getWorkstationId(), record.getWorkstationId());
		assertEquals(getMyModel().getDayInMonth(), record.getDayInMonth());
		assertEquals(getMyModel().getTimeHhmmss(), record.getTimeHhmmss());
		assertEquals(getMyModel().getSequenceNumber(), record.getSequenceNumber());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGALRecordDao) dao).checkIfThisGALRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GALRecordDataModel record = new GALRecordDataModel(9688888223L, 33);
		record.setToUnit("Str");
		record.setToServer("String_T");
		record.setUserId("Stri");
		record.setWorkstationId("Stri");
		record.setDayInMonth(1);
		record.setTimeHhmmss(1);
		record.setSequenceNumber(33);

		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public GALRecordDataModel getMyModel()
	{
		GALRecordDataModel record = null;

		if (dataModel instanceof GALRecordDataModel)
		{
			record = (GALRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}