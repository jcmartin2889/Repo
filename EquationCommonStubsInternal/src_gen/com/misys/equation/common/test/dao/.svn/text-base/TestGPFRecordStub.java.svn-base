package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGPFRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GPFRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestGPFRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1270638836086l;

	public TestGPFRecordStub()
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
		dao = daoFactory.getGPFDao(session, dataModel);
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
	public GPFRecordDataModel getRecord()
	{
		GPFRecordDataModel record = null;
		record = ((IGPFRecordDao) dao).getGPFRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		GPFRecordDataModel record = null;
		this.getMyModel().setRuleId("String_TES");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getRuleId(), record.getRuleId());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		GPFRecordDataModel record = (GPFRecordDataModel) dataModel;
		assertEquals(getMyModel().getRuleId(), record.getRuleId());
		assertEquals(getMyModel().getField(), record.getField());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGPFRecordDao) dao).checkIfThisGPFRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GPFRecordDataModel record = new GPFRecordDataModel("String_TES", "String_TES");

		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public GPFRecordDataModel getMyModel()
	{
		GPFRecordDataModel record = null;

		if (dataModel instanceof GPFRecordDataModel)
		{
			record = (GPFRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}