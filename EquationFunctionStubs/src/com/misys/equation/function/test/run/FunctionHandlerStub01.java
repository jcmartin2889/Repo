package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionMessages;

/**
 * FunctionHandler Stub 1
 * 
 * This is how to use the Function Handler with validate and update processing done separately
 * 
 */
public class FunctionHandlerStub01 extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub01.java 6793 2010-03-31 12:10:20Z deroset $";

	public FunctionHandlerStub01()
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
		FunctionHandlerStub01 test = new FunctionHandlerStub01();
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
			functionHandler.getFhd().getFunctionInfo().setWorkStationId("STB1");

			// create the function
			functionHandler.doNewTransaction("ALZ", "");

			// Get a handler to the function data
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			// This is how you setup the field given its input value (as if entered through the desktop)
			functionData.chgFieldInputValue("AB", "9132");
			functionData.chgFieldInputValue("AN", "234567");
			functionData.chgFieldInputValue("AS", "001");
			functionData.chgFieldInputValue("EAN", "1840KBWD870900840");
			functionData.chgFieldInputValue("TCD", "510");
			functionData.chgFieldInputValue("AMT", "15T");
			functionData.chgFieldInputValue("CCY", "GBP");
			functionData.chgFieldInputValue("BRNM", "LOND");
			functionData.chgFieldInputValue("DRF", "via STUB1");
			functionData.chgFieldInputValue("NR1", "test NR1");
			functionData.chgFieldInputValue("NR2", "test NR2");
			functionData.chgFieldInputValue("NR3", "Atest NR3");
			functionData.chgFieldInputValue("NR4", "test NR4");
			functionData.chgFieldInputValue("SRC", "SR");
			functionData.chgFieldInputValue("UC1", "UC1");
			functionData.chgFieldInputValue("UC2", "UC2");

			functionData.chgFieldInputValue("DIGIT", "15");
			functionData.chgFieldInputValue("DECI", "0");
			functionData.chgFieldInputValue("XValid2", "110");
			functionData.chgFieldInputValue("XValid3", "170");
			functionData.chgFieldInputValue("XReg", "REGULAR STUB 1");
			functionData.chgFieldInputValue("XMask", "02033205082");
			functionData.chgFieldInputValue("FLD3B", "0991231CITY@@CH 0000001");
			functionData.chgFieldInputValue("FLD3C", "BBB9132120005100  0991231KBSL@@MM 0000080");

			// This is how you setup the field given its database value
			functionData.chgFieldDbValue("PBR", "STB1");
			functionData.chgFieldDbValue("NPE", "1");
			functionData.chgFieldDbValue("AC", "9132234567001");
			functionData.chgFieldDbValue("FRQ", "L01");
			functionData.chgFieldDbValue("YNO", "Y");
			functionData.chgFieldDbValue("YNO2", "N");
			functionData.chgFieldDbValue("RAT1", "123.45123");
			functionData.chgFieldDbValue("RAT2", "5678.9012345");
			functionData.chgFieldDbValue("VFR", "1000501");

			// validate all screens
			System.err.println("=============================== VALIDATE");
			int msgSev = functionHandler.validate();

			// print the field values
			// EqBeanFactory eqBeanFactory = EqBeanFactory.getEqBeanFactory();
			// String xml = eqBeanFactory.serialiseBeanAsXML(functionData);
			// System.out.println(xml);

			// any message during validation
			if (msgSev != FunctionMessages.MSG_NONE)
			{
				System.out.println("Messages during validation: START");
				FunctionToolboxStub.printMessages(functionHandler.rtvFunctionMessages().getMessages());
				System.out.println("Messages during validation: END");
			}

			// No major error
			System.err.println("");
			System.err.println("=============================== UPDATE");
			if (msgSev != FunctionMessages.MSG_ERROR)
			{
				functionHandler.update(true);
				System.out.println("Messages during update: START");
				FunctionToolboxStub.printMessages(functionHandler.rtvFunctionMessages().getMessages());
				System.out.println("Messages during update: END");
			}
			else
			{
				System.err.println("No update - error exists during validation");
			}

			// Print the transaction
			Toolbox.printList(functionHandler.print());

			// Print debug info
			System.out.println(functionHandler.getFhd().getFunctionDebugInfo());

			// Print the journal key
			JournalHeader journalHeader = functionHandler.getFhd().getJournalHeader();
			if (journalHeader != null)
			{
				System.out.println(journalHeader);
			}

			return (journalHeader != null);
		}
		catch (Exception e)
		{
			System.out.println(functionHandler.getFhd().getFunctionDebugInfo());
			e.printStackTrace();
			return false;
		}
		finally
		{
			cleanUp();
		}
	}

	public void testStub01_001()
	{
		FunctionHandlerStub01 stub = new FunctionHandlerStub01();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
