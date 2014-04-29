package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IACNRecordDao;
import com.misys.equation.common.dao.beans.ACNRecordDataModel;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test ACNIRecord services.
 * 
 * @author deroset
 * 
 */
public class ACNRecord extends EquationTestCaseDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACNRecord.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public ACNRecord()
	{

	}

	/**
	 * 
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getACNDao(session, dataModel);
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
	public ACNRecordDataModel getRecord()
	{

		ACNRecordDataModel ACNRecord = null;
		ACNRecord = ((IACNRecordDao) dao).getACNRecord();
		assertDataModel(ACNRecord);
		return ACNRecord;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{

		ACNRecordDataModel ACNRecord = null;

		this.getMyModel().setProgram("TESTZ1");
		dao.updateRecord();
		ACNRecord = getRecord();
		assertEquals(this.getMyModel().getClassName(), ACNRecord.getClassName());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{

		ACNRecordDataModel record = (ACNRecordDataModel) dataModel;

		assertEquals(getMyModel().getProgram(), record.getProgram());
		assertEquals(getMyModel().getClassName(), record.getClassName());
	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IACNRecordDao) dao).checkIfThisACNRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		ACNRecordDataModel record = new ACNRecordDataModel();

		record.setProgram("TESTZ1");
		record.setClassName("com.test.TestClass");

		record.setLibrary(kFilLibName);
		this.dataModel = record;

	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public ACNRecordDataModel getMyModel()
	{

		ACNRecordDataModel ACNRecordDataModel = null;

		if (dataModel instanceof ACNRecordDataModel)
		{

			ACNRecordDataModel = (ACNRecordDataModel) dataModel;

		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return ACNRecordDataModel;
	}
}
