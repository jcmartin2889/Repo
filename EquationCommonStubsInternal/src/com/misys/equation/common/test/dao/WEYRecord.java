package com.misys.equation.common.test.dao;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.dao.IWEYRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.WEYRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.test.EquationTestCaseDao;

/**
 * This is a unit-test which will test SessionIRecord services.
 * 
 * @author deroset
 * 
 */
public class WEYRecord extends EquationTestCaseDao
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WEYRecord.java 14974 2012-11-26 18:36:11Z williae1 $";
	public WEYRecord()
	{
	}

	/**
	 * 
	 * This method is going to retrieve the Dao from the bean repository and set it as local attribute.
	 */
	@Override
	protected void setDao()
	{
		dao = daoFactory.getWEYDao(session, dataModel);
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
	public WEYRecordDataModel getRecord()
	{
		WEYRecordDataModel WEYRecord = null;
		WEYRecord = ((IWEYRecordDao) dao).getWEYRecord();
		assertDataModel(WEYRecord);
		return WEYRecord;
	}

	/**
	 * This method will test the update dao's service.
	 */
	@Override
	public void updateRecord()
	{
		WEYRecordDataModel WEYRecord = null;

		this.getMyModel().setLoadType("LoadTypeUpdated");
		this.getMyModel().setGsOffset(9999);
		dao.updateRecord();
		WEYRecord = getRecord();
		assertEquals(this.getMyModel().getLoadId(), WEYRecord.getLoadId());
		assertEquals(this.getMyModel().getLoadType(), WEYRecord.getLoadType());
		assertEquals(this.getMyModel().getGsOffset(), WEYRecord.getGsOffset());

		try
		{
			if (EquationCommonContext.getContext().getEquationUnit(this.session.getSessionIdentifier()).isWEYPFBdtaInstalled())
			{
				assertEquals(this.getMyModel().getBeforeImageEQ(), WEYRecord.getBeforeImageEQ());
			}
		}
		catch (EQException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void assertDataModel(AbsRecord dataModel)
	{
		WEYRecordDataModel record = (WEYRecordDataModel) dataModel;
		assertEquals(getMyModel().getUserId(), record.getUserId());
		assertEquals(getMyModel().getOptionId(), record.getOptionId());
		assertEquals(getMyModel().getSessionId(), record.getSessionId());
		assertEquals(getMyModel().getTransactionId(), record.getTransactionId());
		assertEquals(getMyModel().getLoadId(), record.getLoadId());
		assertEquals(getMyModel().getLoadType(), record.getLoadType());
		assertEquals(getMyModel().getGsOffset(), record.getGsOffset());
		// assertEquals(getMyModel().getBeforeImage(), record.getBeforeImage());
		try
		{
			if (EquationCommonContext.getContext().getEquationUnit(this.session.getSessionIdentifier()).isWEYPFBdtaInstalled())
			{
				assertEquals(getMyModel().getBeforeImageEQ(), record.getBeforeImageEQ());
			}
		}
		catch (EQException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected boolean checkIfThisRecordIsInTheDB()
	{
		boolean result;
		result = ((IWEYRecordDao) dao).checkIfThisWEYRecordIsInTheDB();
		return result;
	}

	@Override
	protected void setDataModel()
	{
		byte[] data = new byte[10];
		data[0] = 0x10;
		data[1] = 0x11;
		data[2] = 0x12;
		data[3] = 0x13;
		data[4] = 0x14;
		data[5] = 0x15;
		data[6] = 0x16;
		data[7] = 0x17;
		data[8] = 0x18;
		data[9] = 0x19;
		WEYRecordDataModel record = new WEYRecordDataModel("ALZ", session.getSessionIdentifier(), "TRANSACTIONID", session
						.getUserId(), "LOADID", "TYPE", 100, data, "");

		record.setLibrary(kFilLibName);
		this.dataModel = record;

	}

	/**
	 * This method is going to return a casted data model.
	 * 
	 * @return a casted data model
	 */
	public WEYRecordDataModel getMyModel()
	{

		WEYRecordDataModel WEYRecordDataModel = null;

		if (dataModel instanceof WEYRecordDataModel)
		{

			WEYRecordDataModel = (WEYRecordDataModel) dataModel;

		}
		else
		{
			throw new IllegalArgumentException("The set data-model does not correspond to the expected one.");
		}

		return WEYRecordDataModel;
	}
}
