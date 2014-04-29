package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;

// Via API
public class FunctionHandlerStub23WPD1Add extends FunctionHandlerStubTestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub23WPD1Add.java 6793 2010-03-31 12:10:20Z deroset $";
	public FunctionHandlerStub23WPD1Add()
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
		FunctionHandlerStub23WPD1Add test = new FunctionHandlerStub23WPD1Add();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler2 = null;
		try
		{
			// Maintain
			System.out.println("--------------------------- WPD");
			functionHandler2 = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler2.doNewTransaction("WPD", "");

			FunctionData functionData2 = functionHandler2.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			functionData2.chgFieldInputValue("WPD_PRDCDE", "TEST");
			functionHandler2.applyRetrieveTransaction();

			functionData2.chgFieldInputValue("WPD_PRDDSC", "this is desc");
			functionData2.chgFieldInputValue("WPD_PRDACC", "this is acc");
			functionHandler2.applyTransaction();

			Toolbox.printList(functionHandler2.print());
			FunctionToolboxStub.printMessages(functionHandler2.rtvFunctionMessages().getMessages());

			JournalHeader journalHeader = functionHandler2.getFhd().getJournalHeader();
			if (journalHeader != null)
			{
				System.out.println(journalHeader);
			}
			else
			{
				System.out.println("ERROR");
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
			cleanUp();
		}
	}

	public void testStub23WPD_001()
	{
		FunctionHandlerStub23WPD1Add stub = new FunctionHandlerStub23WPD1Add();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
