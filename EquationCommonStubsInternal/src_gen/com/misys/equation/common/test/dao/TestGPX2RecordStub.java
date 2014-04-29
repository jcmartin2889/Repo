package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGPX2RecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GPX2RecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestGPX2RecordStub extends EquationTestCaseDao
{

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1258630647060l;

	public TestGPX2RecordStub()
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
		dao = daoFactory.getGPX2Dao(session, dataModel);
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
	public GPX2RecordDataModel getRecord()
	{

		GPX2RecordDataModel record = null;
		record = ((IGPX2RecordDao) dao).getGPX2Record();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{

		GPX2RecordDataModel record = null;
		this.getMyModel().setGroupMnemonic("Str");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getGroupMnemonic(), record.getGroupMnemonic());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{

		GPX2RecordDataModel record = (GPX2RecordDataModel) dataModel;

		assertEquals(getMyModel().getGroupMnemonic(), record.getGroupMnemonic());
		assertEquals(getMyModel().getUnitMnemonic(), record.getUnitMnemonic());
		assertEquals(getMyModel().getUnitSequence(), record.getUnitSequence());
		assertEquals(getMyModel().getSystemName(), record.getSystemName());
		assertEquals(getMyModel().getUnitType(), record.getUnitType());
		assertEquals(getMyModel().getBranchNumber(), record.getBranchNumber());
		assertEquals(getMyModel().getBranchMnemonic(), record.getBranchMnemonic());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGPX2RecordDao) dao).checkIfThisGPX2RecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GPX2RecordDataModel record = new GPX2RecordDataModel("Stri");
		record.setGroupMnemonic("Str");
		record.setUnitMnemonic("Str");
		record.setUnitSequence(1);
		record.setSystemName("String_TES");
		record.setUnitType("String_TES");
		record.setBranchMnemonic("Stri");

		record.setLibrary(kFilLibName);
		this.dataModel = record;
		;
	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public GPX2RecordDataModel getMyModel()
	{

		GPX2RecordDataModel record = null;

		if (dataModel instanceof GPX2RecordDataModel)
		{

			record = (GPX2RecordDataModel) dataModel;

		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return record;
	}
}
