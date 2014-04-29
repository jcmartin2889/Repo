package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGPARecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GPARecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestGPARecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1270638835852l;

	public TestGPARecordStub()
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
		dao = daoFactory.getGPADao(session, dataModel);
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
	public GPARecordDataModel getRecord()
	{
		GPARecordDataModel record = null;
		record = ((IGPARecordDao) dao).getGPARecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		GPARecordDataModel record = null;
		this.getMyModel().setSetIdentifier("String_TES");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getSetIdentifier(), record.getSetIdentifier());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		GPARecordDataModel record = (GPARecordDataModel) dataModel;
		assertEquals(getMyModel().getSetIdentifier(), record.getSetIdentifier());
		assertEquals(getMyModel().getRuleIdentifier(), record.getRuleIdentifier());
		assertEquals(getMyModel().getDisplayOrder(), record.getDisplayOrder());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGPARecordDao) dao).checkIfThisGPARecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GPARecordDataModel record = new GPARecordDataModel("String_TES", "String_TES");
		record.setDisplayOrder(33);

		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public GPARecordDataModel getMyModel()
	{
		GPARecordDataModel record = null;

		if (dataModel instanceof GPARecordDataModel)
		{
			record = (GPARecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}