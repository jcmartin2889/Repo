package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;

// Via API
public class FunctionHandlerStub04HCX2Maint extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub04HCX2Maint.java 6793 2010-03-31 12:10:20Z deroset $";

	public FunctionHandlerStub04HCX2Maint()
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
		FunctionHandlerStub04HCX2Maint test = new FunctionHandlerStub04HCX2Maint();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler2 = null;
		try
		{
			// Maintain XX4
			System.out.println("--------------------------- 2");
			functionHandler2 = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler2.doNewTransaction("HCX", "");
			FunctionData functionData2 = functionHandler2.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			functionData2.chgFieldInputValue("HLD", "XX4");
			functionHandler2.applyRetrieveTransaction();

			functionData2.chgFieldInputValue("DSC", "Description of XX4 amended");
			functionHandler2.applyTransaction();

			Toolbox.printList(functionHandler2.print());
			FunctionToolboxStub.printMessages(functionHandler2.rtvFunctionMessages().getMessages());

			JournalHeader journalHeader = functionHandler2.getFhd().getJournalHeader();
			if (journalHeader != null)
			{
				System.out.println("Journal 2=" + journalHeader);
			}
			else
			{
				System.out.println("Journal 2=" + "ERROR");
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

	public void testStub04HCX2Maint_001()
	{
		FunctionHandlerStub04HCX2Maint stub = new FunctionHandlerStub04HCX2Maint();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
