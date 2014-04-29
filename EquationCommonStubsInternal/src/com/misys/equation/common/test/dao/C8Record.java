package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IC8RecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.C8RecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test C8IRecord services.
 * 
 */
public class C8Record extends EquationTestCaseDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: C8Record.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public C8Record()
	{
	}

	/**
	 * 
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getC8Dao(session, dataModel);
	}

	/**
	 * This method will test if the record C8s been already inserted in the dataBase.
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
	 * The obtained result should be the same tC8n the preset in the dao data-model.
	 * 
	 */
	@Override
	public C8RecordDataModel getRecord()
	{
		C8RecordDataModel C8Record = null;
		C8Record = ((IC8RecordDao) dao).getC8Record();
		assertDataModel(C8Record);
		return C8Record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		C8RecordDataModel C8Record = null;

		this.getMyModel().setCurrencyName("Updated");
		this.getMyModel().setSwiftCode("X");
		dao.updateRecord();
		C8Record = getRecord();
		assertEquals(this.getMyModel().getCurrencyName(), C8Record.getCurrencyName());
		assertEquals(this.getMyModel().getSwiftCode(), C8Record.getSwiftCode());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		C8RecordDataModel record = (C8RecordDataModel) dataModel;

		assertEquals(getMyModel().getCurrencyMnem(), record.getCurrencyMnem());
		assertEquals(getMyModel().getCurrencyName(), record.getCurrencyName());
		assertEquals(getMyModel().getCurrencyNum(), record.getCurrencyNum());
		assertEquals(getMyModel().getEditField(), record.getEditField());
		assertEquals(getMyModel().getSwiftCode(), record.getSwiftCode());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IC8RecordDao) dao).checkIfThisC8RecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		C8RecordDataModel record = new C8RecordDataModel("XXX");
		record.setCurrencyName("test code descrption");
		record.setCurrencyNum("123");
		record.setSwiftCode("123");
		record.setEditField("1");
		record.setLibrary(kFilLibName);
		this.dataModel = record;

	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public C8RecordDataModel getMyModel()
	{
		C8RecordDataModel C8RecordDataModel = null;

		if (dataModel instanceof C8RecordDataModel)
		{
			C8RecordDataModel = (C8RecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return C8RecordDataModel;
	}
}
