package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.ILU1RecordDao; 
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.LU1RecordDataModel ;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestLU1RecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1280744205054l;
	
	public TestLU1RecordStub()
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
		dao=daoFactory.getLU1Dao(session, dataModel);
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
	public LU1RecordDataModel getRecord()
	{
		LU1RecordDataModel record = null;
		record = ((ILU1RecordDao) dao).getLU1Record();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	public void updateRecord()
	{
		LU1RecordDataModel record = null;
		this.getMyModel().setBranchNumber("Stri");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getBranchNumber(), record. getBranchNumber());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		LU1RecordDataModel record = (LU1RecordDataModel) dataModel;		
		assertEquals( getMyModel().getBranchNumber(), record. getBranchNumber());
		assertEquals( getMyModel().getCycleStatus(), record. getCycleStatus());
		assertEquals( getMyModel().getLinkStatus(), record. getLinkStatus());
		assertEquals( getMyModel().getOperationalStatus(), record. getOperationalStatus());
		assertEquals( getMyModel().getDownloadStatus(), record. getDownloadStatus());
		assertEquals( getMyModel().getCycleStatusOverridden(), record. getCycleStatusOverridden());
		assertEquals( getMyModel().getCashierVersion(), record. getCashierVersion());
		
	}
	
		@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((ILU1RecordDao) dao).checkIfThisLU1RecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		LU1RecordDataModel record = new LU1RecordDataModel( "Stri");
		record.setCycleStatus("S");
		record.setLinkStatus("S");
		record.setOperationalStatus("S");
		record.setDownloadStatus("S");
		record.setCycleStatusOverridden("S");
		record.setCashierVersion("Stri");
		
		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * @return  a cast data model
	 */
	public LU1RecordDataModel getMyModel()
	{
		LU1RecordDataModel record = null;

		if (dataModel instanceof LU1RecordDataModel)
		{
			record = (LU1RecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}