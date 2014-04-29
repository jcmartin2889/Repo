package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGLURecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GLURecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestGLURecordStub extends EquationTestCaseDao
{

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1258127204856l;

	public TestGLURecordStub()
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
		dao = daoFactory.getGLUDao(session, dataModel);
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
	public GLURecordDataModel getRecord()
	{

		GLURecordDataModel record = null;
		record = ((IGLURecordDao) dao).getGLURecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{

		GLURecordDataModel record = null;
		this.getMyModel().setUnitMnemonic("Str");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getUnitMnemonic(), record.getUnitMnemonic());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{

		GLURecordDataModel record = (GLURecordDataModel) dataModel;

		assertEquals(getMyModel().getUnitMnemonic(), record.getUnitMnemonic());
		assertEquals(getMyModel().getSystemID(), record.getSystemID());
		assertEquals(getMyModel().getUnitDescription(), record.getUnitDescription());
		assertEquals(getMyModel().getEquationLevel(), record.getEquationLevel());
		assertEquals(getMyModel().getDesktopSupported(), record.getDesktopSupported());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGLURecordDao) dao).checkIfThisGLURecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GLURecordDataModel record = new GLURecordDataModel("Str", "String_TES");
		record.setUnitMnemonic("Str");
		record.setSystemID("String_TES");
		record.setUnitDescription("String_TEST");
		record.setEquationLevel("String_TES");
		record.setDesktopSupported("S");

		record.setLibrary(kFilLibName);
		this.dataModel = record;
		;
	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public GLURecordDataModel getMyModel()
	{

		GLURecordDataModel record = null;

		if (dataModel instanceof GLURecordDataModel)
		{

			record = (GLURecordDataModel) dataModel;

		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return record;
	}
}
