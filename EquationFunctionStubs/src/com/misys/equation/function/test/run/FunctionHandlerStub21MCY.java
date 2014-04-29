package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;

// Via API
public class FunctionHandlerStub21MCY extends FunctionHandlerStubTestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub21MCY.java 6793 2010-03-31 12:10:20Z deroset $";
	public FunctionHandlerStub21MCY()
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
		FunctionHandlerStub21MCY test = new FunctionHandlerStub21MCY();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler2 = null;
		try
		{
			// Maintain
			System.out.println("--------------------------- MCY");
			functionHandler2 = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler2.doNewTransaction("MCY", "");

			FunctionData functionData2 = functionHandler2.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			functionData2.chgFieldInputValue("CMA_CUS", "ABRAHA");
			functionData2.chgFieldInputValue("CMA_CLC", "ISA");
			functionHandler2.applyRetrieveTransaction();

			functionData2.chgFieldInputValue("MCD_CNAL", "US");
			functionData2.chgFieldInputValue("MCD_CTP", "CA");
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

	public void testStub21MCY_001()
	{
		FunctionHandlerStub21MCY stub = new FunctionHandlerStub21MCY();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}