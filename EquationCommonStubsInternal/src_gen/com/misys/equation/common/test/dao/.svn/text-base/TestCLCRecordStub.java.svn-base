package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.ICLCRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.CLCRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestCLCRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1271749368871l;

	public TestCLCRecordStub()
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
		setKLibName("KGRPGP4");
	}

	/**
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getCLCRecordDao(session, dataModel);
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
	public CLCRecordDataModel getRecord()
	{
		CLCRecordDataModel record = null;
		record = ((ICLCRecordDao) dao).getCLCRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		CLCRecordDataModel record = null;
		this.getMyModel().setSynchId("St");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getSynchId(), record.getSynchId());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		CLCRecordDataModel record = (CLCRecordDataModel) dataModel;
		assertEquals(getMyModel().getSynchId(), record.getSynchId());
		assertEquals(getMyModel().getDescription(), record.getDescription());
		assertEquals(getMyModel().getSynchMasterBasicDetails(), record.getSynchMasterBasicDetails());
		assertEquals(getMyModel().getSynchMasterOtherDetails(), record.getSynchMasterOtherDetails());
		assertEquals(getMyModel().getSynchMasterFreeFormatDetails(), record.getSynchMasterFreeFormatDetails());
		assertEquals(getMyModel().getSynchMasterAddressDetails(), record.getSynchMasterAddressDetails());
		assertEquals(getMyModel().getSynchMasterExtendedDetails(), record.getSynchMasterExtendedDetails());
		assertEquals(getMyModel().getSynchMasterACDetails(), record.getSynchMasterACDetails());
		assertEquals(getMyModel().getSynchNonMasterBasicDetails(), record.getSynchNonMasterBasicDetails());
		assertEquals(getMyModel().getSynchNonMasterOtherDetails(), record.getSynchNonMasterOtherDetails());
		assertEquals(getMyModel().getSynchNonMasterFreeFormatDetails(), record.getSynchNonMasterFreeFormatDetails());
		assertEquals(getMyModel().getSynchNonMasterAddressDetails(), record.getSynchNonMasterAddressDetails());
		assertEquals(getMyModel().getSynchNonMasterExtendedDetails(), record.getSynchNonMasterExtendedDetails());
		assertEquals(getMyModel().getSynchNonMasterACDetails(), record.getSynchNonMasterACDetails());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((ICLCRecordDao) dao).checkIfThisCLCRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		CLCRecordDataModel record = new CLCRecordDataModel("St");
		record.setDescription("String_TEST");
		record.setSynchMasterBasicDetails("S");
		record.setSynchMasterOtherDetails("S");
		record.setSynchMasterFreeFormatDetails("S");
		record.setSynchMasterAddressDetails("S");
		record.setSynchMasterExtendedDetails("S");
		record.setSynchMasterACDetails("S");
		record.setSynchNonMasterBasicDetails("S");
		record.setSynchNonMasterOtherDetails("S");
		record.setSynchNonMasterFreeFormatDetails("S");
		record.setSynchNonMasterAddressDetails("S");
		record.setSynchNonMasterExtendedDetails("S");
		record.setSynchNonMasterACDetails("S");

		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public CLCRecordDataModel getMyModel()
	{
		CLCRecordDataModel record = null;

		if (dataModel instanceof CLCRecordDataModel)
		{
			record = (CLCRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}