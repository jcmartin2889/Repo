package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IHARecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.HARecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test HAIRecord services.
 * 
 */
public class HARecord extends EquationTestCaseDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HARecord.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public HARecord()
	{
	}

	/**
	 * 
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getHADao(session, dataModel);
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
	public HARecordDataModel getRecord()
	{
		HARecordDataModel HARecord = null;
		HARecord = ((IHARecordDao) dao).getHARecord();
		assertDataModel(HARecord);
		return HARecord;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		HARecordDataModel HARecord = null;

		this.getMyModel().setCodeDescription("Updated");
		this.getMyModel().setMaintIllegal("X");
		dao.updateRecord();
		HARecord = getRecord();
		assertEquals(this.getMyModel().getCodeDescription(), HARecord.getCodeDescription());
		assertEquals(this.getMyModel().getMaintIllegal(), HARecord.getMaintIllegal());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		HARecordDataModel record = (HARecordDataModel) dataModel;

		assertEquals(getMyModel().getCodeDescription(), record.getCodeDescription());
		assertEquals(getMyModel().getDateLastMaintained(), record.getDateLastMaintained());
		assertEquals(getMyModel().getFileKey(), record.getFileKey());
		assertEquals(getMyModel().getLanguageCode(), record.getLanguageCode());
		assertEquals(getMyModel().getMaintIllegal(), record.getMaintIllegal());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IHARecordDao) dao).checkIfThisHARecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		HARecordDataModel record = new HARecordDataModel("XX", "SDXXX");
		record.setCodeDescription("test code descrption");
		record.setDateLastMaintained(991231);
		record.setMaintIllegal("X");
		record.setLibrary(kFilLibName);
		this.dataModel = record;

	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public HARecordDataModel getMyModel()
	{
		HARecordDataModel HARecordDataModel = null;

		if (dataModel instanceof HARecordDataModel)
		{
			HARecordDataModel = (HARecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return HARecordDataModel;
	}
}
