package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IACHRecordDao;
import com.misys.equation.common.dao.beans.ACHRecordDataModel;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test ACHIRecord services.
 * 
 * @author deroset
 * 
 */
public class ACHRecord extends EquationTestCaseDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACHRecord.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public ACHRecord()
	{

	}

	/**
	 * 
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getACHDao(session, dataModel);
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
	public ACHRecordDataModel getRecord()
	{

		ACHRecordDataModel ACHRecord = null;
		ACHRecord = ((IACHRecordDao) dao).getACHRecord();
		assertDataModel(ACHRecord);
		return ACHRecord;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{

		ACHRecordDataModel ACHRecord = null;

		this.getMyModel().setAchwid("2");
		dao.updateRecord();
		ACHRecord = getRecord();
		assertEquals(this.getMyModel().getAchwid(), ACHRecord.getAchwid());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{

		ACHRecordDataModel record = (ACHRecordDataModel) dataModel;

		assertEquals(getMyModel().getAchrfld(), record.getAchrfld());
		assertEquals(getMyModel().getAchiob(), record.getAchiob());
		assertEquals(getMyModel().getAchwid(), record.getAchwid());
	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IACHRecordDao) dao).checkIfThisACHRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		ACHRecordDataModel record = new ACHRecordDataModel("ACJ");

		record.setAchwid("T");
		record.setAchiob("W");

		record.setLibrary(kFilLibName);
		this.dataModel = record;

	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public ACHRecordDataModel getMyModel()
	{

		ACHRecordDataModel ACHRecordDataModel = null;

		if (dataModel instanceof ACHRecordDataModel)
		{

			ACHRecordDataModel = (ACHRecordDataModel) dataModel;

		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return ACHRecordDataModel;
	}
}
