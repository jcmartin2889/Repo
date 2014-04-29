package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGAFRecordDao; 
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAFRecordDataModel ;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestGAFRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1282102630231l;
	
	public TestGAFRecordStub()
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
		dao=daoFactory.getGAFDao(session, dataModel);
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
	public GAFRecordDataModel getRecord()
	{
		GAFRecordDataModel record = null;
		record = ((IGAFRecordDao) dao).getGAFRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	public void updateRecord()
	{
		GAFRecordDataModel record = null;
		this.getMyModel().setSequenceId(9688888223L);
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getSequenceId(), record. getSequenceId());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		GAFRecordDataModel record = (GAFRecordDataModel) dataModel;		
		assertEquals( getMyModel().getSequenceId(), record. getSequenceId());
		assertEquals( getMyModel().getFieldName(), record. getFieldName());
		assertEquals( getMyModel().getIncludeAllFields(), record. getIncludeAllFields());
		
	}
	
		@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGAFRecordDao) dao).checkIfThisGAFRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GAFRecordDataModel record = new GAFRecordDataModel();
		record.setSequenceId(9688888223L);
		record.setFieldName("String_TES");
		record.setIncludeAllFields("S");
		
		record.setLibrary(kFilLibName);
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * @return  a cast data model
	 */
	public GAFRecordDataModel getMyModel()
	{
		GAFRecordDataModel record = null;

		if (dataModel instanceof GAFRecordDataModel)
		{
			record = (GAFRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}