package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IKAPRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.KAPRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestKAPRecordStub extends EquationTestCaseDao
{

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1258388076931l;

	public TestKAPRecordStub()
	{

	}

	/**
	 * This is method is going to set part of the kFilLibName.<br>
	 * This method can be overwritten if other lib nedds to used.<br>
	 * <code>kFilLibName=  kFilLibName + unitName</code>
	 */
	@Override
	public void setKLibName()
	{
		setKLibName("KGRP");
	}

	/**
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getKAPDao(session, dataModel);
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
	public KAPRecordDataModel getRecord()
	{

		KAPRecordDataModel record = null;
		record = ((IKAPRecordDao) dao).getKAPRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{

		KAPRecordDataModel record = null;
		this.getMyModel().setUserId("String_TES");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getUserId(), record.getUserId());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{

		KAPRecordDataModel record = (KAPRecordDataModel) dataModel;

		assertEquals(getMyModel().getUserId(), record.getUserId());
		assertEquals(getMyModel().getUnitMnemonic(), record.getUnitMnemonic());
		assertEquals(getMyModel().getSystem(), record.getSystem());
		assertEquals(getMyModel().getInitialProgram(), record.getInitialProgram());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IKAPRecordDao) dao).checkIfThisKAPRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		KAPRecordDataModel record = new KAPRecordDataModel("String_TES", "Str", "String_TES");
		record.setInitialProgram("String_TES");

		record.setLibrary(kFilLibName);
		this.dataModel = record;
		;
	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public KAPRecordDataModel getMyModel()
	{

		KAPRecordDataModel record = null;

		if (dataModel instanceof KAPRecordDataModel)
		{

			record = (KAPRecordDataModel) dataModel;

		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return record;
	}
}
