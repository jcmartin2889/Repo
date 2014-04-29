package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IOCRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.OCRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test OCIRecord services.
 * 
 * @author deroset
 * 
 */
public class OCRecord extends EquationTestCaseDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OCRecord.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public OCRecord()
	{

	}

	/**
	 * 
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getOCDao(session, dataModel);
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
	public OCRecordDataModel getRecord()
	{

		OCRecordDataModel OCRecord = null;
		OCRecord = ((IOCRecordDao) dao).getOCRecord();
		assertDataModel(OCRecord);
		return OCRecord;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{

		OCRecordDataModel OCRecord = null;

		this.getMyModel().setUserName("Program title Updated");
		dao.updateRecord();
		OCRecord = getRecord();
		assertEquals(this.getMyModel().getUserName(), OCRecord.getUserName());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{

		OCRecordDataModel OCRecord = (OCRecordDataModel) dataModel;

		assertEquals(getMyModel().getUserId(), OCRecord.getUserId());
		assertEquals(getMyModel().getOptionId(), OCRecord.getOptionId());
		assertEquals(getMyModel().getBranch(), OCRecord.getBranch());
		assertEquals(getMyModel().getLanguage(), OCRecord.getLanguage());
		assertEquals(getMyModel().getLimitAuth(), OCRecord.getLimitAuth());
		assertEquals(getMyModel().getUserName(), OCRecord.getUserName());
		assertEquals(getMyModel().getBranchNo(), OCRecord.getBranchNo());
		assertEquals(getMyModel().getPhoneNo(), OCRecord.getPhoneNo());
		assertEquals(getMyModel().getExtNo(), OCRecord.getExtNo());
		assertEquals(getMyModel().getAvailCode(), OCRecord.getAvailCode());
		assertEquals(getMyModel().getUserClass(), OCRecord.getUserClass());
		assertEquals(getMyModel().getAuthAnyBranch(), OCRecord.getAuthAnyBranch());
		assertEquals(getMyModel().getAuthClass(), OCRecord.getAuthClass());
		assertEquals(getMyModel().getAuthLevel(), OCRecord.getAuthLevel());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IOCRecordDao) dao).checkIfThisOCRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		OCRecordDataModel record = new OCRecordDataModel("XXX");

		record.setAuthAnyBranch("Y");
		record.setAuthClass("CLA");
		record.setAuthLevel("LVL");
		record.setAvailCode("AV");
		record.setBranch("BRNM");
		record.setBranchNo("BRNO");
		record.setLibrary(kFilLibName);
		this.dataModel = record;

	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public OCRecordDataModel getMyModel()
	{

		OCRecordDataModel OCRecordDataModel = null;

		if (dataModel instanceof OCRecordDataModel)
		{

			OCRecordDataModel = (OCRecordDataModel) dataModel;

		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return OCRecordDataModel;
	}
}
