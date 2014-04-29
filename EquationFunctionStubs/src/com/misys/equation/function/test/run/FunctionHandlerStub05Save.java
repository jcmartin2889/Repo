package com.misys.equation.function.test.run;

import com.misys.equation.common.dao.beans.WERecordDataModel;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionSession;
import com.misys.equation.function.runtime.ScreenSetHandler;
import com.misys.equation.function.tools.SupervisorToolbox;

// Via Save and Restore
public class FunctionHandlerStub05Save extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub05Save.java 6793 2010-03-31 12:10:20Z deroset $";

	public FunctionHandlerStub05Save()
	{
		try
		{
			setUp();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters)
	{
		FunctionHandlerStub05Save test = new FunctionHandlerStub05Save();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			// create the function handler
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");

			// create the function
			functionHandler.doNewTransaction("ALZ", "");

			// get a handler to the function data
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			// setup the field values
			functionData.chgFieldInputValue("AB", "9132");
			functionData.chgFieldInputValue("AN", "234567");
			functionData.chgFieldInputValue("AS", "001");
			functionData.chgFieldInputValue("EAN", "1840KBWD870900840");
			functionData.chgFieldInputValue("TCD", "510");
			functionData.chgFieldInputValue("AMT", "123T");
			functionData.chgFieldInputValue("CCY", "GBP");
			functionData.chgFieldInputValue("BRNM", "LOND");
			functionData.chgFieldInputValue("DRF", "Save and restore");
			functionData.chgFieldInputValue("NR1", "API test NR1");
			functionData.chgFieldInputValue("NR2", "API test NR2");
			functionData.chgFieldInputValue("NR3", "API test NR3");
			functionData.chgFieldInputValue("NR4", "API test NR4");
			functionData.chgFieldInputValue("SRC", "XR");
			functionData.chgFieldInputValue("UC1", "XC1");
			functionData.chgFieldInputValue("UC2", "");
			functionData.chgFieldInputValue("FLD3B", "0991231CITY@@CH 0000001");
			functionData.chgFieldInputValue("FLD3C", "BBB9132120005100  0991231KBSL@@MM 0000080");

			// setup the database values
			functionData.chgFieldDbValue("PBR", "SAVE");
			functionData.chgFieldDbValue("NPE", "1");
			functionData.chgFieldDbValue("AC", "9132234567001");
			functionData.chgFieldDbValue("FRQ", "L01");
			functionData.chgFieldDbValue("YNO", "Y");
			functionData.chgFieldDbValue("YNO2", "N");
			functionData.chgFieldDbValue("RAT1", "123.45");
			functionData.chgFieldDbValue("RAT2", "1234.1234567");
			functionData.chgFieldDbValue("VFR", "1000103");
			functionData.chgFieldDbValue("DAT1", "1000101");
			functionData.chgFieldDbValue("DAT2", "1000102");

			functionData.chgFieldInputValue("DIGIT", "15");
			functionData.chgFieldInputValue("DECI", "0");
			functionData.chgFieldInputValue("XValid2", "110");
			functionData.chgFieldInputValue("XValid3", "170");
			functionData.chgFieldInputValue("XReg", "REGULAR SAVE/RESTORE");

			// save it
			functionHandler.save(WERecordDataModel.STAT_WIP, "XXXX2");

			// NOW RESTORE IT
			FunctionHandler fh = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			FunctionSession fs = functionHandler.getFhd().getFunctionSession();
			fh.restore(fs.getOptionId(), fs.getSessionId(), fs.getUserId(), fs.getTransactionId(), null,
							ScreenSetHandler.SCREENSET_DEFAULT);
			fh.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData().chgFieldInputValue("DRF", "Save Restore");
			fh.applyTransaction();

			// print it
			Toolbox.printList(fh.print());

			// any messages
			FunctionToolboxStub.printMessages(functionHandler.rtvFunctionMessages().getMessages());

			// retrieve journal header
			JournalHeader journalHeader = fh.getFhd().getJournalHeader();
			if (journalHeader != null)
			{
				System.out.println(journalHeader);
			}

			return (journalHeader != null);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (functionHandler != null)
			{
				if (functionHandler.getFhd().getFunctionSession() != null)
				{
					SupervisorToolbox.removeSession(functionHandler.getFhd().getFunctionSession(), functionHandler.getFhd()
									.getEquationUser().getEquationUnit());
				}
			}
			cleanUp();
		}
	}

	public void testStub05Save_001()
	{
		FunctionHandlerStub05Save stub = new FunctionHandlerStub05Save();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
