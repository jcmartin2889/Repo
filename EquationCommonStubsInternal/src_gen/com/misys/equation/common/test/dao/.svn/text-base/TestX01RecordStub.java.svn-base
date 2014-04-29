package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IX01RecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.X01RecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestX01RecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1284999546729l;

	public TestX01RecordStub()
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
		setKLibName("KFILGP4");
	}

	/**
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getX01Dao(session, dataModel);
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
	public X01RecordDataModel getRecord()
	{
		X01RecordDataModel record = null;
		record = ((IX01RecordDao) dao).getX01Record();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		X01RecordDataModel record = null;
		getMyModel().setRedundant("Strin");
		dao.updateRecord();
		record = getRecord();
		assertEquals(getMyModel().getRedundant(), record.getRedundant());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		X01RecordDataModel record = (X01RecordDataModel) dataModel;
		assertEquals(getMyModel().getRedundant(), record.getRedundant());
		assertEquals(getMyModel().getTransferDate(), record.getTransferDate());
		assertEquals(getMyModel().getDebitAccountBranch(), record.getDebitAccountBranch());
		assertEquals(getMyModel().getDebitAccountBasic(), record.getDebitAccountBasic());
		assertEquals(getMyModel().getDebitAccountSuffix(), record.getDebitAccountSuffix());
		assertEquals(getMyModel().getCreditAccountBranch(), record.getCreditAccountBranch());
		assertEquals(getMyModel().getCreditAccountBasic(), record.getCreditAccountBasic());
		assertEquals(getMyModel().getCreditAccountSuffix(), record.getCreditAccountSuffix());
		assertEquals(getMyModel().getPosting1Date(), record.getPosting1Date());
		assertEquals(getMyModel().getPosting1Branch(), record.getPosting1Branch());
		assertEquals(getMyModel().getPosting1BatchId(), record.getPosting1BatchId());
		assertEquals(getMyModel().getPosting1Sequence(), record.getPosting1Sequence());
		assertEquals(getMyModel().getPosting2Date(), record.getPosting2Date());
		assertEquals(getMyModel().getPosting2Branch(), record.getPosting2Branch());
		assertEquals(getMyModel().getPosting2BatchId(), record.getPosting2BatchId());
		assertEquals(getMyModel().getPosting2Sequence(), record.getPosting2Sequence());
		assertEquals(getMyModel().getPosting3Date(), record.getPosting3Date());
		assertEquals(getMyModel().getPosting3Branch(), record.getPosting3Branch());
		assertEquals(getMyModel().getPosting3BatchId(), record.getPosting3BatchId());
		assertEquals(getMyModel().getPosting3Sequence(), record.getPosting3Sequence());
		assertEquals(getMyModel().getPosting4Date(), record.getPosting4Date());
		assertEquals(getMyModel().getPosting4Branch(), record.getPosting4Branch());
		assertEquals(getMyModel().getPosting4BatchId(), record.getPosting4BatchId());
		assertEquals(getMyModel().getPosting4Sequence(), record.getPosting4Sequence());
		assertEquals(getMyModel().getFontisTransfer(), record.getFontisTransfer());
		assertEquals(getMyModel().getStandingOrderTransfer(), record.getStandingOrderTransfer());
		assertEquals(getMyModel().getTransferReference(), record.getTransferReference());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IX01RecordDao) dao).checkIfThisX01RecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		X01RecordDataModel record = new X01RecordDataModel(1, "Stri", "String", "Str", "Stri", "String", "Str", "S", "String_TEST");
		record.setRedundant("Strin");
		record.setPosting1Date(1);
		record.setPosting1Branch("Stri");
		record.setPosting1BatchId("Strin");
		record.setPosting1Sequence(33);
		record.setPosting2Date(1);
		record.setPosting2Branch("Stri");
		record.setPosting2BatchId("Strin");
		record.setPosting2Sequence(33);
		record.setPosting3Date(1);
		record.setPosting3Branch("Stri");
		record.setPosting3BatchId("Strin");
		record.setPosting3Sequence(33);
		record.setPosting4Date(1);
		record.setPosting4Branch("Stri");
		record.setPosting4BatchId("Strin");
		record.setPosting4Sequence(33);
		record.setStandingOrderTransfer("S");

		record.setLibrary(kFilLibName);
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public X01RecordDataModel getMyModel()
	{
		X01RecordDataModel record = null;

		if (dataModel instanceof X01RecordDataModel)
		{
			record = (X01RecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}