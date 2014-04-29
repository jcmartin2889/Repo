package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IQZRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.QZRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test QZIRecord services.
 * 
 * @author deroset
 * 
 */
public class QZRecord extends EquationTestCaseDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: QZRecord.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public QZRecord()
	{

	}

	/**
	 * 
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{

		dao = daoFactory.getQZDao(session, dataModel);
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
	 * @return a <code>QZRecordDataModel</code>
	 */
	@Override
	public QZRecordDataModel getRecord()
	{
		QZRecordDataModel QZRecord = null;
		QZRecord = ((IQZRecordDao) dao).getQZRecordByQZFID();
		assertDataModel(QZRecord);
		return QZRecord;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{

		QZRecordDataModel QZRecord = null;

		this.getMyModel().setDataarea("UT");
		dao.updateRecord();
		QZRecord = getRecord();
		assertEquals(this.getMyModel().getDataarea(), QZRecord.getDataarea());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		QZRecordDataModel record = null;

		if (dataModel == null)
		{
			fail("The dataModel is null, it is likely that getRecord() return null ");
		}

		record = (QZRecordDataModel) dataModel;
		assertEquals(getMyModel().getFieldName(), record.getFieldName());
		assertEquals(getMyModel().getFieldPosition(), record.getFieldPosition());
		assertEquals(getMyModel().getFieldLength(), record.getFieldLength());
		assertEquals(getMyModel().getDataarea(), record.getDataarea());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IQZRecordDao) dao).checkIfThisQZRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		QZRecordDataModel record = new QZRecordDataModel("DCRO");
		record.setFieldLength(3);
		record.setFieldPosition(3);
		record.setFieldName("txt");
		record.setLibrary(kFilLibName);
		this.dataModel = record;

	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public QZRecordDataModel getMyModel()
	{

		QZRecordDataModel QZRecordDataModel = null;

		if (dataModel instanceof QZRecordDataModel)
		{

			QZRecordDataModel = (QZRecordDataModel) dataModel;

		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return QZRecordDataModel;
	}
}
