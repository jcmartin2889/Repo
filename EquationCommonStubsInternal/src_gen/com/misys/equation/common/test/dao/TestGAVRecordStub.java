package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGAVRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAVRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestGAVRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1275040799082l;

	public TestGAVRecordStub()
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
		setKLibName("KGRPMNG");
	}

	/**
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getGAVDao(session, dataModel);
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
	public GAVRecordDataModel getRecord()
	{
		GAVRecordDataModel record = null;
		record = ((IGAVRecordDao) dao).getGAVRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		GAVRecordDataModel record = null;
		this.getMyModel().setSequenceId(9688888223L);
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getSequenceId(), record.getSequenceId());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		GAVRecordDataModel record = (GAVRecordDataModel) dataModel;
		assertEquals(getMyModel().getSequenceId(), record.getSequenceId());
		assertEquals(getMyModel().getSourceUnitCcsid(), record.getSourceUnitCcsid());
		assertEquals(getMyModel().getSourceUnit(), record.getSourceUnit());
		assertEquals(getMyModel().getSourceServerId(), record.getSourceServerId());
		assertEquals(getMyModel().getUserId(), record.getUserId());
		assertEquals(getMyModel().getWorkstationId(), record.getWorkstationId());
		assertEquals(getMyModel().getDayInMonth(), record.getDayInMonth());
		assertEquals(getMyModel().getTimeHhmmss(), record.getTimeHhmmss());
		assertEquals(getMyModel().getSequenceNumber(), record.getSequenceNumber());
		assertEquals(getMyModel().getType(), record.getType());
		assertEquals(getMyModel().getSourceGzImage(), record.getSourceGzImage());
		assertEquals(getMyModel().getSourceGsImage(), record.getSourceGsImage());
		assertEquals(getMyModel().getApiFormat(), record.getApiFormat());
		assertEquals(getMyModel().getPropagationSetId(), record.getPropagationSetId());
		assertEquals(getMyModel().getPropagationRuleId(), record.getPropagationRuleId());
		assertEquals(getMyModel().getConditions(), record.getConditions());
		assertEquals(getMyModel().getExcludedFields(), record.getExcludedFields());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGAVRecordDao) dao).checkIfThisGAVRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GAVRecordDataModel record = new GAVRecordDataModel(9688888223L);
		record.setSourceUnitCcsid(1);
		record.setSourceUnit("Str");
		record.setSourceServerId("String_T");
		record.setUserId("Stri");
		record.setWorkstationId("Stri");
		record.setDayInMonth(1);
		record.setTimeHhmmss(1);
		record.setSequenceNumber(33);
		record.setType("S");
		record.setSourceGzImage(new byte[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20});
		record.setSourceGsImage(new byte[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20});
		record.setApiFormat("String_TES");
		record.setPropagationSetId("String_TES");
		record.setPropagationRuleId("String_TES");
		record.setConditions("This value has not been defined!");
		record.setExcludedFields("This value has not been defined!");

		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public GAVRecordDataModel getMyModel()
	{
		GAVRecordDataModel record = null;

		if (dataModel instanceof GAVRecordDataModel)
		{
			record = (GAVRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}