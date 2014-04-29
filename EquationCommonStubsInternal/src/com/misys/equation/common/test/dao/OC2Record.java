package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IOC2RecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.OC2RecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;
import com.misys.equation.common.utilities.ApplicationContextManager;

/**
 * This is a unit-test which will test OC2IRecord services.
 * 
 * @author deroset
 * 
 */
public class OC2Record extends EquationTestCaseDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OC2Record.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public OC2Record()
	{

	}

	/**
	 * 
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getOC2Dao(session, dataModel);
	}

	/**
	 * This method is going to setup the test environment.<br>
	 * ApplicationContextManager, Session, EqUnit, Data-model and library are goimng to be set.
	 */
	@Override
	public void setUp() throws Exception
	{

		applicationContextManager = ApplicationContextManager.getInstance();
		setUpTestingEnvironment();
		setKLibName();
		setKFilLibName();
		setDataModel();
		setDao();

	}
	/**
	 * This test in going to test all RecordDao services add, get, update and delete.
	 * <ul>
	 * <li>2)Check if this record was added.</li>
	 * </ul>
	 */
	@Override
	public void testRecordDaoServices()
	{

		try
		{
			isRecord(true);
			getRecord();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			if (LOG.isErrorEnabled())
			{
				LOG.error("There was a problem when the testRecordDaoServices() was executed ", exception);
			}
		}

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
	public OC2RecordDataModel getRecord()
	{

		OC2RecordDataModel OC2Record = null;
		OC2Record = ((IOC2RecordDao) dao).getOC2Record();
		assertDataModel(OC2Record);
		return OC2Record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{

		OC2RecordDataModel OC2Record = null;

		this.getMyModel().setPwdDate(2);
		dao.updateRecord();
		OC2Record = getRecord();
		assertEquals(this.getMyModel().getPwdDate(), OC2Record.getPwdDate());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{

		OC2RecordDataModel record = (OC2RecordDataModel) dataModel;
		assertEquals(getMyModel().getUserId(), record.getUserId());
	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IOC2RecordDao) dao).checkIfThisOC2RecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		OC2RecordDataModel record = new OC2RecordDataModel("EQ3R");
		record.setPassword(new byte[] {});
		record.setPwdDate(1);

		record.setLibrary(kFilLibName);
		this.dataModel = record;

	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public OC2RecordDataModel getMyModel()
	{

		OC2RecordDataModel OC2RecordDataModel = null;

		if (dataModel instanceof OC2RecordDataModel)
		{

			OC2RecordDataModel = (OC2RecordDataModel) dataModel;

		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return OC2RecordDataModel;
	}
}
