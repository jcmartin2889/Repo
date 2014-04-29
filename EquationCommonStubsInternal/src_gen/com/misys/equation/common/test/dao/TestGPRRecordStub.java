package com.misys.equation.common.test.dao;

import com.misys.equation.common.dao.IGPRRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GPRRecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test $tablePrefixIRecord services.
 * 
 * @author deroset
 * 
 */
public class TestGPRRecordStub extends EquationTestCaseDao
{
	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1274173436578l;

	public TestGPRRecordStub()
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
		dao = daoFactory.getGPRDao(session, dataModel);
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
	public GPRRecordDataModel getRecord()
	{
		GPRRecordDataModel record = null;
		record = ((IGPRRecordDao) dao).getGPRRecord();
		assertDataModel(record);
		return record;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		GPRRecordDataModel record = null;
		this.getMyModel().setIdentifier("String_TES");
		dao.updateRecord();
		record = getRecord();
		assertEquals(this.getMyModel().getIdentifier(), record.getIdentifier());
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		GPRRecordDataModel record = (GPRRecordDataModel) dataModel;
		assertEquals(getMyModel().getIdentifier(), record.getIdentifier());
		assertEquals(getMyModel().getDescription(), record.getDescription());
		assertEquals(getMyModel().getMonitorOrAdHoc(), record.getMonitorOrAdHoc());
		assertEquals(getMyModel().getExportGroup(), record.getExportGroup());
		assertEquals(getMyModel().getExportType(), record.getExportType());
		assertEquals(getMyModel().getLinkedCustomers(), record.getLinkedCustomers());
		assertEquals(getMyModel().getMonitorEnabled(), record.getMonitorEnabled());
		assertEquals(getMyModel().getMonitorAdditions(), record.getMonitorAdditions());
		assertEquals(getMyModel().getMonitorMaintenance(), record.getMonitorMaintenance());
		assertEquals(getMyModel().getMonitorDeletions(), record.getMonitorDeletions());
		assertEquals(getMyModel().getMonitorAllUnits(), record.getMonitorAllUnits());
		assertEquals(getMyModel().getConditions(), record.getConditions());
		assertEquals(getMyModel().getAutomaticOrManualApply(), record.getAutomaticOrManualApply());
		assertEquals(getMyModel().getPropagateToAllUnits(), record.getPropagateToAllUnits());
		assertEquals(getMyModel().getIncludeAllFields(), record.getIncludeAllFields());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGPRRecordDao) dao).checkIfThisGPRRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		GPRRecordDataModel record = new GPRRecordDataModel();
		record.setIdentifier("String_TES");
		record.setDescription("String_TEST");
		record.setMonitorOrAdHoc("S");
		record.setExportGroup("St");
		record.setExportType("String_TES");
		record.setLinkedCustomers("S");
		record.setMonitorEnabled("S");
		record.setMonitorAdditions("S");
		record.setMonitorMaintenance("S");
		record.setMonitorDeletions("S");
		record.setMonitorAllUnits("S");
		record.setConditions("This value has not been defined!");
		record.setAutomaticOrManualApply("S");
		record.setPropagateToAllUnits("S");
		record.setIncludeAllFields("S");

		record.setLibrary(getKLibName());
		this.dataModel = record;
	}

	/**
	 * This method is going to return a cast data model.
	 * 
	 * @return a cast data model
	 */
	public GPRRecordDataModel getMyModel()
	{
		GPRRecordDataModel record = null;

		if (dataModel instanceof GPRRecordDataModel)
		{
			record = (GPRRecordDataModel) dataModel;
		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}
		return record;
	}
}