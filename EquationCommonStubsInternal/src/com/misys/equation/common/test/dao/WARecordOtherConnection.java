package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IWARecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.WARecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test WAIRecord services.
 * 
 * @author deroset
 * 
 */
public class WARecordOtherConnection extends EquationTestCaseDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WARecordOtherConnection.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	/**
	 * 
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getWADao(session, dataModel);
	}

	@Override
	public void testRecordDaoServices()
	{

		// dao.deleteRecord();
		// dao.commit();
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
	public WARecordDataModel getRecord()
	{

		WARecordDataModel WARecord = null;
		WARecord = ((IWARecordDao) dao).getWARecord();
		assertDataModel(WARecord);
		return WARecord;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{

		WARecordDataModel WARecord = null;

		this.getMyModel().setText("Updated");
		dao.updateRecord();
		WARecord = getRecord();
		assertEquals(this.getMyModel().getText(), WARecord.getText());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{

		WARecordDataModel record = (WARecordDataModel) dataModel;
		assertEquals(getMyModel().getId(), record.getId());
		assertEquals(getMyModel().getText(), record.getText());
		assertEquals(getMyModel().getSev(), record.getSev());
	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IWARecordDao) dao).checkIfThisWARecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		WARecordDataModel record = new WARecordDataModel("ID", "MESSAGE", "L");
		record.setLibrary(kFilLibName);
		this.dataModel = record;

	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public WARecordDataModel getMyModel()
	{

		WARecordDataModel WARecordDataModel = null;

		if (dataModel instanceof WARecordDataModel)
		{

			WARecordDataModel = (WARecordDataModel) dataModel;

		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return WARecordDataModel;
	}

	public void testTX()
	{

		try
		{
			this.getMyModel().setText("UHHHH ");
			// ((IWARecordDao) dao).saveTest();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
