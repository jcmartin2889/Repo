package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IIPVRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.IPVRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test IPVRecord services.
 * 
 */
public class IPVRecord extends EquationTestCaseDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: IPVRecord.java 17760 2014-01-10 15:49:34Z lima12 $";
	public IPVRecord()
	{
	}

	/**
	 * 
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{

		dao = daoFactory.getIPVDao(session, dataModel);
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
	public IPVRecordDataModel getRecord()
	{
		IPVRecordDataModel IPVRecord = null;
		IPVRecord = ((IIPVRecordDao) dao).getIPVRecord();
		assertDataModel(IPVRecord);
		return IPVRecord;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		this.getMyModel().setCcLinkSeqNo(91);
		this.getMyModel().setCcLinkTime(991122);
		this.getMyModel().setExpiryDate(991201);
		this.getMyModel().setJobNumber(911111);
		this.getMyModel().setResponse(new byte[] { 40, 50 });
		dao.updateRecord();

		IPVRecordDataModel IPVRecord = null;
		IPVRecord = getRecord();
		assertEquals(this.getMyModel().getCcLinkSeqNo(), IPVRecord.getCcLinkSeqNo());
		assertEquals(this.getMyModel().getCcLinkTime(), IPVRecord.getCcLinkTime());
		assertEquals(this.getMyModel().getProcessedDate(), IPVRecord.getProcessedDate());
		assertEquals(this.getMyModel().getExpiryDate(), IPVRecord.getExpiryDate());
		assertEquals(this.getMyModel().getJobNumber(), IPVRecord.getJobNumber());
		assertEquals(this.getMyModel().getResponse()[0], 40);
		assertEquals(this.getMyModel().getResponse()[1], 50);
	}

	/**
	 * This method will test the add dao's service.
	 */
	@Override
	public void insertRecord()
	{
		dao.insertRecord();
	}

	/**
	 * This method will test the delete dao's service.
	 */
	@Override
	public void deleteRecord()
	{
		dao.deleteRecord();
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		IPVRecordDataModel IPVRecord = (IPVRecordDataModel) dataModel;

		assertEquals(getMyModel().getReferenceId(), IPVRecord.getReferenceId());
		assertEquals(getMyModel().getApplicationId(), IPVRecord.getApplicationId());
		assertEquals(getMyModel().getSequence(), IPVRecord.getSequence());
		assertEquals(getMyModel().getCcLinkSeqNo(), IPVRecord.getCcLinkSeqNo());
		assertEquals(getMyModel().getCcLinkTime(), IPVRecord.getCcLinkTime());
		assertEquals(getMyModel().getCreateDate(), IPVRecord.getCreateDate());
		assertEquals(getMyModel().getProcessedDate(), IPVRecord.getProcessedDate());
		assertEquals(getMyModel().getExpiryDate(), IPVRecord.getExpiryDate());
		assertEquals(getMyModel().getJobNumber(), IPVRecord.getJobNumber());
		assertEquals(getMyModel().getOptionId(), IPVRecord.getOptionId());
		assertEquals(this.getMyModel().getResponse()[0], IPVRecord.getResponse()[0]);
		assertEquals(this.getMyModel().getResponse()[1], IPVRecord.getResponse()[1]);
		assertEquals(getMyModel().getUserId(), IPVRecord.getUserId());
	}
	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IIPVRecordDao) dao).checkIfThisIPVRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		IPVRecordDataModel record = new IPVRecordDataModel("ReferenceId", "LO", 2);
		record.setCcLinkSeqNo(6);
		record.setCcLinkTime(123456);
		record.setCreateDate(1000101);
		record.setProcessedDate(2001231);
		record.setExpiryDate(1111010);
		record.setJobNumber(567890);
		record.setOptionId("ABC");
		record.setResponse(new byte[] { 41, 51 });
		record.setUserId("USID");
		record.setLibrary(kFilLibName);
		this.dataModel = record;
	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public IPVRecordDataModel getMyModel()
	{
		IPVRecordDataModel IPVRecordDataModel = null;

		if (dataModel instanceof IPVRecordDataModel)
		{
			IPVRecordDataModel = (IPVRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return IPVRecordDataModel;
	}
}
