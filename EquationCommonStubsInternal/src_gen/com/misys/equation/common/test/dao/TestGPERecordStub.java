package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGPERecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GPERecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestGPERecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1270638835977l;

	public TestGPERecordStub()
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
		dao = daoFactory.getGPEDao(session, dataModel);
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
	public GPERecordDataModel getRecord()
	{
		GPERecordDataModel record = null;
		record = ((IGPERecordDao) dao).getGPERecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		GPERecordDataModel record = null;
		this.getMyModel().setIdentifier("String_TES");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getIdentifier(), record.getIdentifier());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		GPERecordDataModel record = (GPERecordDataModel) dataModel;
		assertEquals(getMyModel().getIdentifier(), record.getIdentifier());
		assertEquals(getMyModel().getDescription(), record.getDescription());
		assertEquals(getMyModel().getAutomaticOrManualApply(), record.getAutomaticOrManualApply());
		assertEquals(getMyModel().getPropagateToAllUnits(), record.getPropagateToAllUnits());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGPERecordDao) dao).checkIfThisGPERecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GPERecordDataModel record = new GPERecordDataModel("String_TES");
		record.setDescription("String_TEST");
		record.setAutomaticOrManualApply("S");
		record.setPropagateToAllUnits("S");

		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public GPERecordDataModel getMyModel()
	{
		GPERecordDataModel record = null;

		if (dataModel instanceof GPERecordDataModel)
		{
			record = (GPERecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}