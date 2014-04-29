package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IAPVRecordDao; 
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.APVRecordDataModel ;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestAPVRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1283761830787l;
	
	public TestAPVRecordStub()
	{

	}
	
	/**
	 * This is method is going to set part of the kFilLibName.<br>
	 * This method can be overwritten if another library needs to be used.<br>
	 * <code>kFilLibName=  kFilLibName + unitName</code>
	 */
	public void setKLibName()
	{
		setKLibName("KGRPMNG");
	}
	
		/**	  
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	protected void setDao()
	{
		dao=daoFactory.getAPVDao(session, dataModel);
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
	public APVRecordDataModel getRecord()
	{
		APVRecordDataModel record = null;
		record = ((IAPVRecordDao) dao).getAPVRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	public void updateRecord()
	{
		APVRecordDataModel record = null;
		this.getMyModel().setApiReference("String_TES");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getApiReference(), record. getApiReference());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		APVRecordDataModel record = (APVRecordDataModel) dataModel;		
		assertEquals( getMyModel().getApiReference(), record. getApiReference());
		assertEquals( getMyModel().getApiFileName(), record. getApiFileName());
		assertEquals( getMyModel().getEquationApiLevel(), record. getEquationApiLevel());
		assertEquals( getMyModel().getApiProgram(), record. getApiProgram());
		assertEquals( getMyModel().getApiKeyStart(), record. getApiKeyStart());
		assertEquals( getMyModel().getApiKeyLength(), record. getApiKeyLength());
		assertEquals( getMyModel().getProgramRoot(), record. getProgramRoot());
		assertEquals( getMyModel().getApiDescription(), record. getApiDescription());
		assertEquals( getMyModel().getConditions(), record. getConditions());
		assertEquals( getMyModel().getType(), record. getType());
		
	}
	
		@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IAPVRecordDao) dao).checkIfThisAPVRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		APVRecordDataModel record = new APVRecordDataModel();
		record.setApiReference("String_TES");
		record.setApiFileName("String_TES");
		record.setEquationApiLevel("String_TES");
		record.setApiProgram("String_TES");
		record.setApiKeyStart("Strin");
		record.setApiKeyLength("Strin");
		record.setProgramRoot("String_TES");
		record.setApiDescription("String_TEST");
		record.setConditions("String_TEST");
		record.setType("St");
		
		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * @return  a cast data model
	 */
	public APVRecordDataModel getMyModel()
	{
		APVRecordDataModel record = null;

		if (dataModel instanceof APVRecordDataModel)
		{
			record = (APVRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}