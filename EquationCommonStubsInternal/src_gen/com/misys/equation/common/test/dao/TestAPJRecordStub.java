package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IAPJRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.APJRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestAPJRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1273209982635l;

	public TestAPJRecordStub()
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
		dao = daoFactory.getAPJDao(session, dataModel);
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
	public APJRecordDataModel getRecord()
	{
		APJRecordDataModel record = null;
		record = ((IAPJRecordDao) dao).getAPJRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		APJRecordDataModel record = null;
		this.getMyModel().setApiReference("String_TES");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getApiReference(), record.getApiReference());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		APJRecordDataModel record = (APJRecordDataModel) dataModel;
		assertEquals(getMyModel().getApiReference(), record.getApiReference());
		assertEquals(getMyModel().getApiFileName(), record.getApiFileName());
		assertEquals(getMyModel().getEquationApiLevel(), record.getEquationApiLevel());
		assertEquals(getMyModel().getApiFieldSequence(), record.getApiFieldSequence());
		assertEquals(getMyModel().getApiFieldName(), record.getApiFieldName());
		assertEquals(getMyModel().getApiFieldDescripton(), record.getApiFieldDescripton());
		assertEquals(getMyModel().getApiFieldType(), record.getApiFieldType());
		assertEquals(getMyModel().getApiFieldStart(), record.getApiFieldStart());
		assertEquals(getMyModel().getApiFieldEnd(), record.getApiFieldEnd());
		assertEquals(getMyModel().getApiFieldLength(), record.getApiFieldLength());
		assertEquals(getMyModel().getApiFieldIntegers(), record.getApiFieldIntegers());
		assertEquals(getMyModel().getApiFieldFractions(), record.getApiFieldFractions());
		assertEquals(getMyModel().getDbFieldName(), record.getDbFieldName());
		assertEquals(getMyModel().getDbFieldDescription(), record.getDbFieldDescription());
		assertEquals(getMyModel().getDbFieldType(), record.getDbFieldType());
		assertEquals(getMyModel().getDbFieldStart(), record.getDbFieldStart());
		assertEquals(getMyModel().getDbFieldEnd(), record.getDbFieldEnd());
		assertEquals(getMyModel().getDbFieldLength(), record.getDbFieldLength());
		assertEquals(getMyModel().getDbFieldIntegers(), record.getDbFieldIntegers());
		assertEquals(getMyModel().getDbFieldFractions(), record.getDbFieldFractions());
		assertEquals(getMyModel().getDbFileName(), record.getDbFileName());
		assertEquals(getMyModel().getRetrievalInstance(), record.getRetrievalInstance());
		assertEquals(getMyModel().getControlType(), record.getControlType());
		assertEquals(getMyModel().getSubControlType(), record.getSubControlType());
		assertEquals(getMyModel().getConversionProgram(), record.getConversionProgram());
		assertEquals(getMyModel().getMapParameter(), record.getMapParameter());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IAPJRecordDao) dao).checkIfThisAPJRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		APJRecordDataModel record = new APJRecordDataModel("String_TES", "String_TES", "Strin");
		record.setEquationApiLevel("String_TES");
		record.setApiFieldName("String_TES");
		record.setApiFieldDescripton("String_TEST");
		record.setApiFieldType("S");
		record.setApiFieldStart("Strin");
		record.setApiFieldEnd("Strin");
		record.setApiFieldLength("Strin");
		record.setApiFieldIntegers("St");
		record.setApiFieldFractions("St");
		record.setDbFieldName("String_TES");
		record.setDbFieldDescription("String_TEST");
		record.setDbFieldType("S");
		record.setDbFieldStart("Strin");
		record.setDbFieldEnd("Strin");
		record.setDbFieldLength("Strin");
		record.setDbFieldIntegers("St");
		record.setDbFieldFractions("St");
		record.setDbFileName("String_TES");
		record.setRetrievalInstance("St");
		record.setControlType("Str");
		record.setSubControlType("String_TES");
		record.setConversionProgram("String_TES");
		record.setMapParameter("S");

		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public APJRecordDataModel getMyModel()
	{
		APJRecordDataModel record = null;

		if (dataModel instanceof APJRecordDataModel)
		{
			record = (APJRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}