package com.misys.equation.common.test.dao;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.dao.IGARecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GARecordDataModel;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test GARecord services.
 * 
 * @author deroset
 * 
 */
public class GARecord extends EquationTestCaseDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GARecord.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public GARecord()
	{

	}

	/**
	 * This method is going to check the getRecord dao's service.<br>
	 * The obtained result should be the same than the preset in the dao data-model.
	 * 
	 */
	@Override
	public GARecordDataModel getRecord()
	{

		GARecordDataModel gaRecordDataModel = null;
		gaRecordDataModel = ((IGARecordDao) dao).getGARecord();
		assertDataModel(gaRecordDataModel);

		return gaRecordDataModel;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{

		GARecordDataModel gaRecordDataModel = null;

		getMyModel().setProgramTitle("Program title Updated");
		dao.updateRecord();
		gaRecordDataModel = getRecord();
		assertEquals(getMyModel().getProgramTitle(), gaRecordDataModel.getProgramTitle());
	}

	/**
	 * This method will test the add dao's service.
	 */
	@Override
	public void insertRecord()
	{

		dao.insertRecord();
	}

	/**
	 * This method will test the delete dao's service.
	 */
	@Override
	public void deleteRecord()
	{

		dao.deleteRecord();
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		GARecordDataModel gaRecordDataModel = (GARecordDataModel) dataModel;
		GARecordDataModel myDataModel = getMyModel();

		assertEquals(myDataModel.getOptionId(), gaRecordDataModel.getOptionId());
		assertEquals(myDataModel.getProgramName(), gaRecordDataModel.getProgramName());
		assertEquals(myDataModel.getProgramTitle(), gaRecordDataModel.getProgramTitle());
		assertEquals(myDataModel.getUserDefinedKeysAllowed(), gaRecordDataModel.getUserDefinedKeysAllowed());
		assertEquals(myDataModel.getUserDefinedAndPromptable(), gaRecordDataModel.getUserDefinedAndPromptable());
		assertEquals(myDataModel.getPcProgramName(), gaRecordDataModel.getPcProgramName());
		assertEquals(myDataModel.getExtendedInput(), gaRecordDataModel.getExtendedInput());
		assertEquals(myDataModel.getApplication(), gaRecordDataModel.getApplication());

	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IGARecordDao) dao).checkIfThisGARecordIsInTheDB();

		return result;
	}

	/**
	 * 
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getGADao(session, dataModel);
	}

	@Override
	protected void setDataModel()
	{
		dataModel = new GARecordDataModel("ALZ", EquationStandardTransaction.EDF_PGM, "Program title :)", "ED");
		dataModel.setLibrary(kFilLibName);
	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public GARecordDataModel getMyModel()
	{

		GARecordDataModel gARecordDataModel = null;

		if (dataModel instanceof GARecordDataModel)
		{
			gARecordDataModel = (GARecordDataModel) dataModel;

		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return gARecordDataModel;
	}
}
