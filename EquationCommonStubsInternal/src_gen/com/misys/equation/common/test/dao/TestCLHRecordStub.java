package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.ICLHRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.CLHRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 */
public class TestCLHRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1287559303572l;

	public TestCLHRecordStub()
	{

	}

	/**
	 * This is method is going to set part of the kFilLibName.<br>
	 * This method can be overwritten if another library needs to be used.<br>
	 * <code>kFilLibName=  kFilLibName + unitName</code>
	 */
	public void setKLibName()
	{
		setKLibName(null);
	}

	/**
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	protected void setDao()
	{
		dao = daoFactory.getCLHRecordDao(session, dataModel);
	}

	/**
	 * This method will test if the record has been already inserted in the dataBase.
	 */
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
	public CLHRecordDataModel getRecord()
	{
		CLHRecordDataModel record = null;
		record = ((ICLHRecordDao) dao).getCLHRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	public void updateRecord()
	{
		CLHRecordDataModel record = null;
		this.getMyModel().setGlobalCustomerIdentifier("String_TEST");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getGlobalCustomerIdentifier(), record.getGlobalCustomerIdentifier());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		CLHRecordDataModel record = (CLHRecordDataModel) dataModel;
		assertEquals(getMyModel().getGlobalCustomerIdentifier(), record.getGlobalCustomerIdentifier());
		assertEquals(getMyModel().getGlobalCustomerName(), record.getGlobalCustomerName());
		assertEquals(getMyModel().getTimestamp(), record.getTimestamp());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((ICLHRecordDao) dao).checkIfThisCLHRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		CLHRecordDataModel record = new CLHRecordDataModel("String_TEST");
		record.setGlobalCustomerName("String_TEST");
		record.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));

		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public CLHRecordDataModel getMyModel()
	{
		CLHRecordDataModel record = null;

		if (dataModel instanceof CLHRecordDataModel)
		{
			record = (CLHRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}