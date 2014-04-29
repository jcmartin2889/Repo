package com.misys.equation.common.test.dao;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.common.dao.ICLDRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.CLDRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestCLDRecordConcurrenceStub extends EquationTestCaseDao
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestCLDRecordConcurrenceStub.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1275045708076l;
	private final List<TestCLDRecordConcurrenceThread> testCLDRecordConcurrenceThreads = new ArrayList<TestCLDRecordConcurrenceThread>();

	public TestCLDRecordConcurrenceStub()
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
		setKLibName("");
	}

	/**
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getCLDRecordDao(session, dataModel);
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
	public CLDRecordDataModel getRecord()
	{
		CLDRecordDataModel record = null;
		record = ((ICLDRecordDao) dao).getCLDRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		CLDRecordDataModel record = null;
		getMyModel().setGlobalCustomerId("String_TEST");
		dao.updateRecord();
		record = getRecord();
		assertEquals(getMyModel().getGlobalCustomerId(), record.getGlobalCustomerId());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		CLDRecordDataModel record = (CLDRecordDataModel) dataModel;
		assertEquals(getMyModel().getGlobalCustomerId(), record.getGlobalCustomerId());
		assertEquals(getMyModel().getSequenceNumber(), record.getSequenceNumber());
		assertEquals(getMyModel().getSystemName(), record.getSystemName());
		assertEquals(getMyModel().getCustomerOwningUnit(), record.getCustomerOwningUnit());
		assertEquals(getMyModel().getCustomerNumber(), record.getCustomerNumber());
		assertEquals(getMyModel().isMasterFlag(), record.isMasterFlag());
		assertEquals(getMyModel().getSyncID(), record.getSyncID());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((ICLDRecordDao) dao).checkIfThisCLDRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		CLDRecordDataModel record = new CLDRecordDataModel("String_TEST", 33);
		record.setSystemName("String_TES");
		record.setCustomerOwningUnit("Str");
		record.setCustomerNumber("String");
		record.setMasterFlag(true);
		record.setSyncID("St");

		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public CLDRecordDataModel getMyModel()
	{
		CLDRecordDataModel record = null;

		if (dataModel instanceof CLDRecordDataModel)
		{
			record = (CLDRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}

	/**
	 * This test in going to test all RecordDao services add, get, update and delete.
	 * <ul>
	 * <li>1)Add a new record.</li>
	 * <li>2)Check if this record was added.</li>
	 * <li>3)Get this record and evaluate if it is equals to the previous added.</li>
	 * <li>4)Modify this record and test it.</li>
	 * <li>5)Delete this record and test it.</li>
	 * </ul>
	 */
	@Override
	public void testRecordDaoServices()
	{

		setThreads();

		try
		{

			session.startEquationTransaction();
			insertRecord();
			session.commitTransaction();
			session.endEquationTransaction();

			updates();

			session.startEquationTransaction();
			deleteRecord();
			session.commitTransaction();
			session.endEquationTransaction();

		}
		catch (Exception exception)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("There was a problem when the testRecordDaoServices() was executed ", exception);
			}
		}
	}

	private void updates()
	{
		for (TestCLDRecordConcurrenceThread testCLDRecordConcurrenceThread : testCLDRecordConcurrenceThreads)
		{
			new Thread(testCLDRecordConcurrenceThread).start();
		}
	}

	private void setThreads()
	{
		TestCLDRecordConcurrenceThread testCLDRecordConcurrenceThread = new TestCLDRecordConcurrenceThread();

		testCLDRecordConcurrenceThread.setDao(dao);
		testCLDRecordConcurrenceThread.setSession(session);
		testCLDRecordConcurrenceThread.setCustomValue("TEST1");
		testCLDRecordConcurrenceThreads.add(testCLDRecordConcurrenceThread);

		testCLDRecordConcurrenceThread = new TestCLDRecordConcurrenceThread();

		testCLDRecordConcurrenceThread.setDao(dao);
		testCLDRecordConcurrenceThread.setSession(session);
		testCLDRecordConcurrenceThread.setCustomValue("TEST2");
		testCLDRecordConcurrenceThreads.add(testCLDRecordConcurrenceThread);

		testCLDRecordConcurrenceThread = new TestCLDRecordConcurrenceThread();

		testCLDRecordConcurrenceThread.setDao(dao);
		testCLDRecordConcurrenceThread.setSession(session);
		testCLDRecordConcurrenceThread.setCustomValue("TEST3");
		testCLDRecordConcurrenceThreads.add(testCLDRecordConcurrenceThread);

	}

}
