package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;

// Via API
public class FunctionHandlerStub04HCX3Cancel extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub04HCX3Cancel.java 6793 2010-03-31 12:10:20Z deroset $";

	public FunctionHandlerStub04HCX3Cancel()
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
		FunctionHandlerStub04HCX3Cancel test = new FunctionHandlerStub04HCX3Cancel();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler3 = null;
		try
		{
			// Cancel XX4
			System.out.println("--------------------------- 3");
			functionHandler3 = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler3.doNewTransaction("HCX", "");
			FunctionData functionData3 = functionHandler3.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			functionData3.chgFieldInputValue("HLD", "XX4");
			functionHandler3.applyRetrieveTransaction();
			functionData3.chgFieldInputValue("DSC", "Description of XX4");
			functionHandler3.applyTransactionDelete();
			Toolbox.printList(functionHandler3.print());
			FunctionToolboxStub.printMessages(functionHandler3.rtvFunctionMessages().getMessages());

			JournalHeader journalHeader = functionHandler3.getFhd().getJournalHeader();
			if (journalHeader != null)
			{
				System.out.println("Journal 3=" + journalHeader);
			}
			else
			{
				System.out.println("Journal 3=" + "ERROR");
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

	public void testStub04HCX3Cancel_001()
	{
		FunctionHandlerStub04HCX3Cancel stub = new FunctionHandlerStub04HCX3Cancel();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
