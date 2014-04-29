package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionMessage;

// Via API
public class FunctionHandlerStub09SuperId extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub09SuperId.java 6793 2010-03-31 12:10:20Z deroset $";

	public FunctionHandlerStub09SuperId()
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
		FunctionHandlerStub09SuperId test = new FunctionHandlerStub09SuperId();
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
			functionHandler.getFhd().getFunctionInfo().setGenerateWarningInfo(true);

			// create the function
			functionHandler.doNewTransaction("ALZ", "");

			// get a handler to the function data
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			// supervisor
			functionData.chgFieldInputValue(FunctionData.FLD_SUPERID, "LIMA");

			// setup the field values
			functionData.chgFieldInputValue("AB", "9132");
			functionData.chgFieldInputValue("AN", "234567");
			functionData.chgFieldInputValue("AS", "001");
			functionData.chgFieldInputValue("EAN", "1840KBWD870900840");
			functionData.chgFieldInputValue("TCD", "510");
			functionData.chgFieldInputValue("AMT", "150T");
			functionData.chgFieldInputValue("CCY", "GBP");
			functionData.chgFieldInputValue("BRNM", "LOND");
			functionData.chgFieldInputValue("DRF", "SUBMIT TO SUPER");
			functionData.chgFieldInputValue("NR1", "API test NR1");
			functionData.chgFieldInputValue("NR2", "API test NR2");
			functionData.chgFieldInputValue("NR3", "API test NR3");
			functionData.chgFieldInputValue("NR4", "API test NR4");
			functionData.chgFieldInputValue("SRC", "XR");
			functionData.chgFieldInputValue("UC1", "XC1");
			functionData.chgFieldInputValue("UC2", "");
			functionData.chgFieldInputValue("CTP", "AA");
			functionData.chgFieldInputValue("C1", "ZZ");

			functionData.chgFieldInputValue("DIGIT", "15");
			functionData.chgFieldInputValue("DECI", "0");
			functionData.chgFieldInputValue("XValid2", "110");
			functionData.chgFieldInputValue("XValid3", "170");
			functionData.chgFieldInputValue("XReg", "REGULAR 3");
			functionData.chgFieldInputValue("XMask", "02033205082");
			functionData.chgFieldInputValue("FLD3B", "0991231CITY@@CH 0000001");
			functionData.chgFieldInputValue("FLD3C", "BBB9132120005100  0991231KBSL@@MM 0000080");

			// setup the database values
			functionData.chgFieldDbValue("PBR", "STB3");
			functionData.chgFieldDbValue("NPE", "1");
			functionData.chgFieldDbValue("AC", "9132234567001");
			functionData.chgFieldDbValue("FRQ", "L01");
			functionData.chgFieldDbValue("YNO", "Y");
			functionData.chgFieldDbValue("YNO2", "N");
			functionData.chgFieldDbValue("RAT1", "123.45");
			functionData.chgFieldDbValue("RAT2", "1234.567890");
			functionData.chgFieldDbValue("VFR", "1000501");

			// get the function data for the EFC
			functionHandler.getFhd().getScreenSetHandler().setCurScreenSet(2);
			functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			functionData.chgFieldDbValue("GSFRQ_1", "V13");
			functionData.chgFieldDbValue("GSFRQ_2", "V13");
			functionData.chgFieldDbValue("GSFRQ_3", "V13");

			// revert back to the main function
			functionHandler.getFhd().getScreenSetHandler().setCurScreenSet(0);

			// apply transaction
			functionHandler.applyTransaction();

			// print it
			Toolbox.printList(functionHandler.print());

			// any messages
			FunctionToolboxStub.printMessages(functionHandler.rtvFunctionMessages().getMessages());

			// retrieve journal header
			JournalHeader journalHeader = functionHandler.getFhd().getJournalHeader();
			if (journalHeader != null)
			{
				System.out.println(journalHeader);
			}

			// message should contain KSM7363
			FunctionMessage fm = functionHandler.rtvFunctionMessages().getMessages().get(
							functionHandler.rtvFunctionMessages().getMessages().size() - 1);

			return (journalHeader == null && fm.getEqMessage().getDsepms().startsWith("KSM7363"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			cleanUp();
		}
	}

	public void testStub09SuperId()
	{
		FunctionHandlerStub09SuperId stub = new FunctionHandlerStub09SuperId();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
