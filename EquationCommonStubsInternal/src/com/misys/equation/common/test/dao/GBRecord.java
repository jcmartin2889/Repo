package com.misys.equation.common.test.dao;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.dao.IGBRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GBRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test GBIRecord services.
 * 
 * @author deroset
 * 
 */
public class GBRecord extends EquationTestCaseDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GBRecord.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public GBRecord()
	{

	}

	/**
	 * 
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getGBDao(session, dataModel);
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
	public GBRecordDataModel getRecord()
	{

		GBRecordDataModel GBRecord = null;
		GBRecord = ((IGBRecordDao) dao).getGBRecord();
		assertDataModel(GBRecord);
		return GBRecord;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{

		GBRecordDataModel GBRecord = null;

		this.getMyModel().setProgramName("Updated");
		this.getMyModel().setGbwm1("F");
		dao.updateRecord();
		GBRecord = getRecord();
		assertEquals(this.getMyModel().getProgramName(), GBRecord.getProgramName());
		assertEquals(this.getMyModel().getGbwm1(), GBRecord.getGbwm1());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{

		GBRecordDataModel record = (GBRecordDataModel) dataModel;

		assertEquals(getMyModel().getOptionId(), record.getOptionId());
		assertEquals(getMyModel().getProgramName(), record.getProgramName());
		assertEquals(getMyModel().getProgramTitle(), record.getProgramTitle());
		assertEquals(getMyModel().getMandatoryNextReq(), record.getMandatoryNextReq());
		assertEquals(getMyModel().getDefEntryData(), record.getDefEntryData());
		assertEquals(getMyModel().getUserFuncKey1(), record.getUserFuncKey1());
		assertEquals(getMyModel().getUserFuncKey2(), record.getUserFuncKey2());
		assertEquals(getMyModel().getUserFuncKey3(), record.getUserFuncKey3());
		assertEquals(getMyModel().getUserFuncKey4(), record.getUserFuncKey4());
		assertEquals(getMyModel().getPcMnem(), record.getPcMnem());
		assertEquals(getMyModel().getOptionIdGA(), record.getOptionIdGA());
		assertEquals(getMyModel().getRepeatProcessing(), record.getRepeatProcessing());
		assertEquals(getMyModel().getActionBarMenu(), record.getActionBarMenu());
		assertEquals(getMyModel().getOptionType(), record.getOptionType());
		assertEquals(getMyModel().getExtendedInput(), record.getExtendedInput());
		assertEquals(getMyModel().getApplication(), record.getApplication());
		assertEquals(getMyModel().getGbwm1(), record.getGbwm1());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGBRecordDao) dao).checkIfThisGBRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GBRecordDataModel record = new GBRecordDataModel("ALZ", EquationStandardTransaction.EDF_PGM, "Program title", "ED", "ALZ");
		record.setGbwm1("T");
		record.setLibrary(kFilLibName);
		this.dataModel = record;

	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public GBRecordDataModel getMyModel()
	{

		GBRecordDataModel GBRecordDataModel = null;

		if (dataModel instanceof GBRecordDataModel)
		{

			GBRecordDataModel = (GBRecordDataModel) dataModel;

		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return GBRecordDataModel;
	}
}
