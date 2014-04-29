package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IDH0RecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.DH0RecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestDH0RecordStub extends EquationTestCaseDao
{

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1263561056662l;

	public TestDH0RecordStub()
	{

	}

	/**
	 * This is method is going to set part of the kFilLibName.<br>
	 * This method can be overwritten if other lib nedds to used.<br>
	 * <code>kFilLibName=  kFilLibName + unitName</code>
	 */
	@Override
	public void setKLibName()
	{
		setKLibName("KFIL");
	}

	/**
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getDH0Dao(session, dataModel);
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
	public DH0RecordDataModel getRecord()
	{

		DH0RecordDataModel record = null;
		record = ((IDH0RecordDao) dao).getDH0Record();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{

		DH0RecordDataModel record = null;
		this.getMyModel().setAnalysisCode("St");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getAnalysisCode(), record.getAnalysisCode());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{

		DH0RecordDataModel record = (DH0RecordDataModel) dataModel;

		assertEquals(getMyModel().getAnalysisCode(), record.getAnalysisCode());
		assertEquals(getMyModel().getAccountMnemonic(), record.getAccountMnemonic());
		assertEquals(getMyModel().getAcType(), record.getAcType());
		assertEquals(getMyModel().getCustomerNumber(), record.getCustomerNumber());
		assertEquals(getMyModel().getCustomerType(), record.getCustomerType());
		assertEquals(getMyModel().getTcd(), record.getTcd());
		assertEquals(getMyModel().getDefaultInternalAcNoDescription(), record.getDefaultInternalAcNoDescription());
		assertEquals(getMyModel().getDhdlm(), record.getDhdlm());
		assertEquals(getMyModel().getTcdrt(), record.getTcdrt());
		assertEquals(getMyModel().getShortName(), record.getShortName());
		assertEquals(getMyModel().getUpdateLevelNo(), record.getUpdateLevelNo());
		assertEquals(getMyModel().getPostContraTran(), record.getPostContraTran());
		assertEquals(getMyModel().getAccountTypeDescription(), record.getAccountTypeDescription());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IDH0RecordDao) dao).checkIfThisDH0RecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		DH0RecordDataModel record = new DH0RecordDataModel("String");
		record.setAnalysisCode("St");
		record.setAcType("St");
		record.setCustomerNumber("String");
		record.setCustomerType("St");
		record.setTcd("Str");
		record.setDefaultInternalAcNoDescription("String_TEST");
		record.setDhdlm(1);
		record.setTcdrt("Str");
		record.setShortName("Str");
		record.setUpdateLevelNo(2);
		record.setPostContraTran("S");
		record.setAccountTypeDescription("String_TEST");

		record.setLibrary(kFilLibName);
		this.dataModel = record;
		;
	}
	// 870100
	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public DH0RecordDataModel getMyModel()
	{

		DH0RecordDataModel record = null;

		if (dataModel instanceof DH0RecordDataModel)
		{

			record = (DH0RecordDataModel) dataModel;

		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return record;
	}
}
