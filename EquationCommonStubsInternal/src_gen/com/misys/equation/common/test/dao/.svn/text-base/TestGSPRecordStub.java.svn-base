package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGSPRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GSPRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 */
public class TestGSPRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1275040799738l;

	public TestGSPRecordStub()
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
		setKLibName(null);
	}

	/**
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getGSPRecordDao(session, dataModel);
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
	public GSPRecordDataModel getRecord()
	{
		GSPRecordDataModel record = null;
		record = ((IGSPRecordDao) dao).getGSPRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		GSPRecordDataModel record = null;
		this.getMyModel().setSearchIdentifier(1);
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getSearchIdentifier(), record.getSearchIdentifier());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		GSPRecordDataModel record = (GSPRecordDataModel) dataModel;
		assertEquals(getMyModel().getSearchIdentifier(), record.getSearchIdentifier());
		assertEquals(getMyModel().getSearchProperties(), record.getSearchProperties());
		assertEquals(getMyModel().getSearchValue(), record.getSearchValue());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGSPRecordDao) dao).checkIfThisGSPRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GSPRecordDataModel record = new GSPRecordDataModel(1, "String_TES", "This value has not been defined!");

		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public GSPRecordDataModel getMyModel()
	{
		GSPRecordDataModel record = null;

		if (dataModel instanceof GSPRecordDataModel)
		{
			record = (GSPRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}