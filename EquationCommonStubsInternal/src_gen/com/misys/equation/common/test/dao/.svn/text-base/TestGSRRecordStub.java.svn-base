package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGSRRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GSRRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 */
public class TestGSRRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1287559304182l;

	public TestGSRRecordStub()
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
		setKLibName(null);
	}

	/**
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getGSRDao(session, dataModel);
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
	public GSRRecordDataModel getRecord()
	{
		GSRRecordDataModel record = null;
		record = ((IGSRRecordDao) dao).getGSRRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		GSRRecordDataModel record = null;
		getMyModel().setSearchedBy("String_TES");
		dao.updateRecord();
		record = getRecord();
		assertEquals(getMyModel().getSearchedBy(), record.getSearchedBy());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		GSRRecordDataModel record = (GSRRecordDataModel) dataModel;
		assertEquals(getMyModel().getSearchedBy(), record.getSearchedBy());
		assertEquals(getMyModel().getSearchedInSystem(), record.getSearchedInSystem());
		assertEquals(getMyModel().getSearchedInUnit(), record.getSearchedInUnit());
		assertEquals(getMyModel().getSearchType(), record.getSearchType());
		assertEquals(getMyModel().getSearchIdentifier(), record.getSearchIdentifier());
		assertEquals(getMyModel().getTimestamp(), record.getTimestamp());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		return ((IGSRRecordDao) dao).checkIfThisGSRRecordIsInTheDB();
	}

	@Override
	protected void setDataModel()
	{
		GSRRecordDataModel record = new GSRRecordDataModel(1);
		record.setSearchedBy("String_TES");
		record.setSearchedInSystem("String_T");
		record.setSearchedInUnit("Str");
		record.setSearchType("String_TES");
		record.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));

		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public GSRRecordDataModel getMyModel()
	{
		GSRRecordDataModel record = null;

		if (dataModel instanceof GSRRecordDataModel)
		{
			record = (GSRRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}