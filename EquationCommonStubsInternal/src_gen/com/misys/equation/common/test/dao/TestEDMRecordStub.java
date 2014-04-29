package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IEDMRecordDao; 
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.EDMRecordDataModel ;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestEDMRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1285137724677l;
	
	public TestEDMRecordStub()
	{

	}
	
	/**
	 * This is method is going to set part of the kFilLibName.<br>
	 * This method can be overwritten if another library needs to be used.<br>
	 * <code>kFilLibName=  kFilLibName + unitName</code>
	 */
	public void setKLibName()
	{
		setKLibName("KWRKGP4");
	}
	
		/**	  
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	protected void setDao()
	{
		dao=daoFactory.getEDMDao(session, dataModel);
	}

	/**
	 * This method will test if the record has been already inserted in the dataBase.
	 */
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
	public EDMRecordDataModel getRecord()
	{
		EDMRecordDataModel record = null;
		record = ((IEDMRecordDao) dao).getEDMRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	public void updateRecord()
	{
		EDMRecordDataModel record = null;
		this.getMyModel().setStartTime("String_TEST");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getStartTime(), record. getStartTime());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		EDMRecordDataModel record = (EDMRecordDataModel) dataModel;		
		assertEquals( getMyModel().getStartTime(), record. getStartTime());
		assertEquals( getMyModel().getPhase(), record. getPhase());
		assertEquals( getMyModel().getProcess(), record. getProcess());
		assertEquals( getMyModel().getProcessDescription(), record. getProcessDescription());
		assertEquals( getMyModel().getProcessStatus(), record. getProcessStatus());
		assertEquals( getMyModel().getJobName(), record. getJobName());
		assertEquals( getMyModel().getJobNumber(), record. getJobNumber());
		assertEquals( getMyModel().getJobUser(), record. getJobUser());
		assertEquals( getMyModel().getType(), record. getType());
		assertEquals( getMyModel().getIdentifier(), record. getIdentifier());
		assertEquals( getMyModel().getProcessingDate(), record. getProcessingDate());
		assertEquals( getMyModel().getEndTime(), record. getEndTime());
		assertEquals( getMyModel().getSubStatusDescription(), record. getSubStatusDescription());
		assertEquals( getMyModel().getMessage(), record. getMessage());
		
	}
	
		@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IEDMRecordDao) dao).checkIfThisEDMRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		EDMRecordDataModel record = new EDMRecordDataModel();
		record.setStartTime("String_TEST");
		record.setPhase("Str");
		record.setProcess("String_TES");
		record.setProcessDescription("String_TEST");
		record.setProcessStatus("S");
		record.setJobName("String_TES");
		record.setJobNumber("String");
		record.setJobUser("String_TES");
		record.setType("S");
		record.setIdentifier("String_TES");
		record.setProcessingDate(1);
		record.setEndTime("String_TEST");
		record.setSubStatusDescription("String_TEST");
		record.setMessage("String_TEST");
		
		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * @return  a cast data model
	 */
	public EDMRecordDataModel getMyModel()
	{
		EDMRecordDataModel record = null;

		if (dataModel instanceof EDMRecordDataModel)
		{
			record = (EDMRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}