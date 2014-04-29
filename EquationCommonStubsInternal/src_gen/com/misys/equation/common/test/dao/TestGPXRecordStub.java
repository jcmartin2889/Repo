package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGPXRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GPXRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestGPXRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1275048566719l;

	public TestGPXRecordStub()
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
		dao = daoFactory.getGPXRecordDao(session, dataModel);
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
	public GPXRecordDataModel getRecord()
	{
		GPXRecordDataModel record = null;
		record = ((IGPXRecordDao) dao).getGPXRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		GPXRecordDataModel record = null;
		this.getMyModel().setGroupMnemonic("Str");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getGroupMnemonic(), record.getGroupMnemonic());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		GPXRecordDataModel record = (GPXRecordDataModel) dataModel;
		assertEquals(getMyModel().getGroupMnemonic(), record.getGroupMnemonic());
		assertEquals(getMyModel().getUnitMnemonic(), record.getUnitMnemonic());
		assertEquals(getMyModel().getUnitSequence(), record.getUnitSequence());
		assertEquals(getMyModel().getSystemName(), record.getSystemName());
		assertEquals(getMyModel().getUnitType(), record.getUnitType());
		assertEquals(getMyModel().getUnitDescription(), record.getUnitDescription());
		assertEquals(getMyModel().getBranchNumber(), record.getBranchNumber());
		assertEquals(getMyModel().getBranchMnemonic(), record.getBranchMnemonic());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGPXRecordDao) dao).checkIfThisGPXRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GPXRecordDataModel record = new GPXRecordDataModel("Str", "Str", "String_T");
		record.setUnitSequence(1);
		record.setUnitType("String_TES");
		record.setUnitDescription("String_TEST");
		record.setBranchNumber("Stri");
		record.setBranchMnemonic("Stri");

		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public GPXRecordDataModel getMyModel()
	{
		GPXRecordDataModel record = null;

		if (dataModel instanceof GPXRecordDataModel)
		{
			record = (GPXRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}