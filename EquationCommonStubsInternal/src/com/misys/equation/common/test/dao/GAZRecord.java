package com.misys.equation.common.test.dao;

import java.util.Arrays;

import com.misys.equation.common.dao.IGAZRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test GAZIRecord services.
 * 
 * @author deroset
 * 
 */
public class GAZRecord extends EquationTestCaseDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GAZRecord.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public GAZRecord()
	{

	}

	/**
	 * 
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getGAZDao(session, dataModel);
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
	public GAZRecordDataModel getRecord()
	{

		GAZRecordDataModel GAZRecord = null;
		GAZRecord = ((IGAZRecordDao) dao).getGAZRecord();
		assertDataModel(GAZRecord);
		return GAZRecord;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{

		GAZRecordDataModel GAZRecord = null;

		this.getMyModel().setClassName("Updated");
		dao.updateRecord();
		GAZRecord = getRecord();
		assertEquals(this.getMyModel().getClassName(), GAZRecord.getClassName());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{

		boolean result = false;
		GAZRecordDataModel record = (GAZRecordDataModel) dataModel;

		assertEquals(getMyModel().getOptionId(), record.getOptionId());
		assertEquals(getMyModel().getFieldId(), record.getFieldId());
		assertEquals(getMyModel().getPvId(), record.getPvId());
		assertEquals(getMyModel().getType(), record.getType());
		assertEquals(getMyModel().getClassName(), record.getClassName());
		result = Arrays.equals(getMyModel().getClassByte(), record.getClassByte());
		assertEquals(true, result);
	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGAZRecordDao) dao).checkIfThisGAZRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{

		byte[] classByte = new byte[512];
		GAZRecordDataModel record = new GAZRecordDataModel("ALZ", "AB", "PV", "T", "classname", classByte);

		record.setLibrary(kFilLibName);
		this.dataModel = record;

	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public GAZRecordDataModel getMyModel()
	{

		GAZRecordDataModel GAZRecordDataModel = null;

		if (dataModel instanceof GAZRecordDataModel)
		{

			GAZRecordDataModel = (GAZRecordDataModel) dataModel;

		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return GAZRecordDataModel;
	}
}
