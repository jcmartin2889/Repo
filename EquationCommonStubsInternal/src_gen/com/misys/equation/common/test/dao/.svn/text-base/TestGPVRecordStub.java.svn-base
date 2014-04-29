package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGPVRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GPVRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestGPVRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1270638836477l;

	public TestGPVRecordStub()
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
		dao = daoFactory.getGPVDao(session, dataModel);
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
	public GPVRecordDataModel getRecord()
	{
		GPVRecordDataModel record = null;
		record = ((IGPVRecordDao) dao).getGPVRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		GPVRecordDataModel record = null;
		this.getMyModel().setSetId("String_TES");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getSetId(), record.getSetId());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		GPVRecordDataModel record = (GPVRecordDataModel) dataModel;
		assertEquals(getMyModel().getSetId(), record.getSetId());
		assertEquals(getMyModel().getUnitType(), record.getUnitType());
		assertEquals(getMyModel().getServerId(), record.getServerId());
		assertEquals(getMyModel().getUnitMnemonic(), record.getUnitMnemonic());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGPVRecordDao) dao).checkIfThisGPVRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GPVRecordDataModel record = new GPVRecordDataModel("String_TES", "S", "String_T", "Str");

		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public GPVRecordDataModel getMyModel()
	{
		GPVRecordDataModel record = null;

		if (dataModel instanceof GPVRecordDataModel)
		{
			record = (GPVRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}