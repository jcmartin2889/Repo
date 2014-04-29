package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGCRecordDao; 
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GCRecordDataModel ;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestGCRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1276150829621l;
	
	public TestGCRecordStub()
	{

	}
	
	/**
	 * This is method is going to set part of the kFilLibName.<br>
	 * This method can be overwritten if another library needs to be used.<br>
	 * <code>kFilLibName=  kFilLibName + unitName</code>
	 */
	public void setKLibName()
	{
		setKLibName("KFIL");
	}
	
		/**	  
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	protected void setDao()
	{
		dao=daoFactory.getGCDao(session, dataModel);
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
	public GCRecordDataModel getRecord()
	{
		GCRecordDataModel record = null;
		record = ((IGCRecordDao) dao).getGCRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	public void updateRecord()
	{
		GCRecordDataModel record = null;
		this.getMyModel().setMenuId("Str");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getMenuId(), record. getMenuId());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		GCRecordDataModel record = (GCRecordDataModel) dataModel;		
		assertEquals( getMyModel().getMenuId(), record. getMenuId());
		assertEquals( getMyModel().getMenuTitle(), record. getMenuTitle());
		assertEquals( getMyModel().getC01(), record. getC01());
		assertEquals( getMyModel().getFid1(), record. getFid1());
		assertEquals( getMyModel().getFid2(), record. getFid2());
		assertEquals( getMyModel().getFid3(), record. getFid3());
		assertEquals( getMyModel().getFid4(), record. getFid4());
		assertEquals( getMyModel().getFid5(), record. getFid5());
		assertEquals( getMyModel().getFid6(), record. getFid6());
		assertEquals( getMyModel().getFid7(), record. getFid7());
		assertEquals( getMyModel().getFid8(), record. getFid8());
		assertEquals( getMyModel().getFid9(), record. getFid9());
		assertEquals( getMyModel().getDte(), record. getDte());
		assertEquals( getMyModel().getOptionMenuType(), record. getOptionMenuType());
		
	}
	
		@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGCRecordDao) dao).checkIfThisGCRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GCRecordDataModel record = new GCRecordDataModel();
		record.setMenuId("Str");
		record.setMenuTitle("String_TEST");
		record.setC01("S");
		record.setFid1("Str");
		record.setFid2("Str");
		record.setFid3("Str");
		record.setFid4("Str");
		record.setFid5("Str");
		record.setFid6("Str");
		record.setFid7("Str");
		record.setFid8("Str");
		record.setFid9("Str");
		record.setDte(1);
		record.setOptionMenuType("Str");
		
		record.setLibrary(kFilLibName);
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * @return  a cast data model
	 */
	public GCRecordDataModel getMyModel()
	{
		GCRecordDataModel record = null;

		if (dataModel instanceof GCRecordDataModel)
		{
			record = (GCRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}