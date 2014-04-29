package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IEQSRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.EQSRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestEQSRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1273647273946l;

	public TestEQSRecordStub()
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
		setKLibName("");
	}

	/**
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getEQSRecordDao(session, dataModel);
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
	public EQSRecordDataModel getRecord()
	{
		EQSRecordDataModel record = null;
		record = ((IEQSRecordDao) dao).getEQSRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		EQSRecordDataModel record = null;
		this.getMyModel().setUserId("String_TES");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getUserId(), record.getUserId());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		EQSRecordDataModel record = (EQSRecordDataModel) dataModel;
		assertEquals(getMyModel().getUserId(), record.getUserId());
		assertEquals(getMyModel().getAuthorisedUnit(), record.getAuthorisedUnit());
		assertEquals(getMyModel().getUnitServer(), record.getUnitServer());
		assertEquals(getMyModel().getInitialMenu(), record.getInitialMenu());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IEQSRecordDao) dao).checkIfThisEQSRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		EQSRecordDataModel record = new EQSRecordDataModel("String_TES", "Str", "String_T");
		record.setInitialMenu("String_TES");

		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public EQSRecordDataModel getMyModel()
	{
		EQSRecordDataModel record = null;

		if (dataModel instanceof EQSRecordDataModel)
		{
			record = (EQSRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}