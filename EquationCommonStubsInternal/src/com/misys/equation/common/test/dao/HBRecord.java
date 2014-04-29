package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IHBRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.HBRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test HBIRecord services.
 * 
 */
public class HBRecord extends EquationTestCaseDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HBRecord.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public HBRecord()
	{
	}

	/**
	 * 
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getHBDao(session, dataModel);
	}

	/**
	 * This method will test if the record HBs been already inserted in the dataBase.
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
	 * The obtained result should be the same tHBn the preset in the dao data-model.
	 * 
	 */
	@Override
	public HBRecordDataModel getRecord()
	{
		HBRecordDataModel HBRecord = null;
		HBRecord = ((IHBRecordDao) dao).getHBRecord();
		assertDataModel(HBRecord);
		return HBRecord;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		HBRecordDataModel HBRecord = null;

		this.getMyModel().setCodeDescription("Updated");
		this.getMyModel().setDateLastMaintained(10);
		dao.updateRecord();
		HBRecord = getRecord();
		assertEquals(this.getMyModel().getCodeDescription(), HBRecord.getCodeDescription());
		assertEquals(this.getMyModel().getDateLastMaintained(), HBRecord.getDateLastMaintained());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		HBRecordDataModel record = (HBRecordDataModel) dataModel;

		assertEquals(getMyModel().getCodeDescription(), record.getCodeDescription());
		assertEquals(getMyModel().getDateLastMaintained(), record.getDateLastMaintained());
		assertEquals(getMyModel().getFileKey(), record.getFileKey());
		assertEquals(getMyModel().getLanguageCode(), record.getLanguageCode());
		assertEquals(getMyModel().getDateLastMaintained(), record.getDateLastMaintained());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IHBRecordDao) dao).checkIfThisHBRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		HBRecordDataModel record = new HBRecordDataModel("XX", "AAA", "YYY");
		record.setCodeDescription("test code descrption");
		record.setDateLastMaintained(991231);
		record.setLibrary(kFilLibName);
		this.dataModel = record;

	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public HBRecordDataModel getMyModel()
	{
		HBRecordDataModel HBRecordDataModel = null;

		if (dataModel instanceof HBRecordDataModel)
		{
			HBRecordDataModel = (HBRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return HBRecordDataModel;
	}
}
