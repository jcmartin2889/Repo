package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IVP1RecordDao; 
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.VP1RecordDataModel ;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestVP1RecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1279602363307l;
	
	public TestVP1RecordStub()
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
		dao=daoFactory.getVP1Dao(session, dataModel);
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
	public VP1RecordDataModel getRecord()
	{
		VP1RecordDataModel record = null;
		record = ((IVP1RecordDao) dao).getVP1Record();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	public void updateRecord()
	{
		VP1RecordDataModel record = null;
		this.getMyModel().setProductCode("St");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getProductCode(), record. getProductCode());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		VP1RecordDataModel record = (VP1RecordDataModel) dataModel;		
		assertEquals( getMyModel().getProductCode(), record. getProductCode());
		assertEquals( getMyModel().getProductDescription(), record. getProductDescription());
		assertEquals( getMyModel().getMonthToDateVolume(), record. getMonthToDateVolume());
		assertEquals( getMyModel().getDaysProcThisMonth(), record. getDaysProcThisMonth());
		assertEquals( getMyModel().getJanuarysAverageVol(), record. getJanuarysAverageVol());
		assertEquals( getMyModel().getFebruarysAverageVl(), record. getFebruarysAverageVl());
		assertEquals( getMyModel().getMarchsAverageVol(), record. getMarchsAverageVol());
		assertEquals( getMyModel().getAprilsAverageVol(), record. getAprilsAverageVol());
		assertEquals( getMyModel().getMaysAverageVol(), record. getMaysAverageVol());
		assertEquals( getMyModel().getJunesAverageVol(), record. getJunesAverageVol());
		assertEquals( getMyModel().getJulysAverageVol(), record. getJulysAverageVol());
		assertEquals( getMyModel().getAugustsAverageVol(), record. getAugustsAverageVol());
		assertEquals( getMyModel().getSeptembersAverageV(), record. getSeptembersAverageV());
		assertEquals( getMyModel().getOctobersAverageVol(), record. getOctobersAverageVol());
		assertEquals( getMyModel().getNovembersAverageVl(), record. getNovembersAverageVl());
		assertEquals( getMyModel().getDecembersAverageVl(), record. getDecembersAverageVl());
		
	}
	
		@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IVP1RecordDao) dao).checkIfThisVP1RecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		VP1RecordDataModel record = new VP1RecordDataModel( "St");
		record.setProductDescription("String_TEST");
		record.setMonthToDateVolume(9688888223L);
		record.setDaysProcThisMonth(33);
		record.setJanuarysAverageVol(9688888223L);
		record.setFebruarysAverageVl(9688888223L);
		record.setMarchsAverageVol(9688888223L);
		record.setAprilsAverageVol(9688888223L);
		record.setMaysAverageVol(9688888223L);
		record.setJunesAverageVol(9688888223L);
		record.setJulysAverageVol(9688888223L);
		record.setAugustsAverageVol(9688888223L);
		record.setSeptembersAverageV(9688888223L);
		record.setOctobersAverageVol(9688888223L);
		record.setNovembersAverageVl(9688888223L);
		record.setDecembersAverageVl(9688888223L);
		
		record.setLibrary(kFilLibName);
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * @return  a cast data model
	 */
	public VP1RecordDataModel getMyModel()
	{
		VP1RecordDataModel record = null;

		if (dataModel instanceof VP1RecordDataModel)
		{
			record = (VP1RecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}