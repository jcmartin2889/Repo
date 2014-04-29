package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGAWRecordDao; 
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAWRecordDataModel ;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestGAWRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1280458525520l;
	
	public TestGAWRecordStub()
	{

	}
	
	/**
	 * This is method is going to set part of the kFilLibName.<br>
	 * This method can be overwritten if another library needs to be used.<br>
	 * <code>kFilLibName=  kFilLibName + unitName</code>
	 */
	public void setKLibName()
	{
		setKLibName("KFILGP4");
	}
	
		/**	  
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	protected void setDao()
	{
		dao=daoFactory.getGAWDao(session, dataModel);
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
	public GAWRecordDataModel getRecord()
	{
		GAWRecordDataModel record = null;
		record = ((IGAWRecordDao) dao).getGAWRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	public void updateRecord()
	{
		GAWRecordDataModel record = null;
		this.getMyModel().setRecordType("Stri");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getRecordType(), record. getRecordType());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		GAWRecordDataModel record = (GAWRecordDataModel) dataModel;		
		assertEquals( getMyModel().getRecordType(), record. getRecordType());
		assertEquals( getMyModel().getSequenceId(), record. getSequenceId());
		assertEquals( getMyModel().getUserId(), record. getUserId());
		assertEquals( getMyModel().getWorkstationId(), record. getWorkstationId());
		assertEquals( getMyModel().getDayInMonth(), record. getDayInMonth());
		assertEquals( getMyModel().getTimeHhmmss(), record. getTimeHhmmss());
		assertEquals( getMyModel().getSequenceNumber(), record. getSequenceNumber());
		
	}
	
		@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGAWRecordDao) dao).checkIfThisGAWRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GAWRecordDataModel record = new GAWRecordDataModel();
		record.setRecordType("Stri");
		record.setSequenceId(9688888223L);
		record.setUserId("Stri");
		record.setWorkstationId("Stri");
		record.setDayInMonth(1);
		record.setTimeHhmmss(1);
		record.setSequenceNumber(33);
		
		record.setLibrary(kFilLibName);
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * @return  a cast data model
	 */
	public GAWRecordDataModel getMyModel()
	{
		GAWRecordDataModel record = null;

		if (dataModel instanceof GAWRecordDataModel)
		{
			record = (GAWRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}