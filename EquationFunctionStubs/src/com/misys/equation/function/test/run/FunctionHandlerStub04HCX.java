package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;

// Via API
public class FunctionHandlerStub04HCX extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub04HCX.java 6793 2010-03-31 12:10:20Z deroset $";

	public FunctionHandlerStub04HCX()
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
		FunctionHandlerStub04HCX test = new FunctionHandlerStub04HCX();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		FunctionHandler functionHandler2 = null;
		FunctionHandler functionHandler3 = null;
		try
		{
			// Add XX4
			System.out.println("--------------------------- 1");
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler.doNewTransaction("HCX", "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			functionData.chgFieldInputValue("HLD", "XX4");
			functionHandler.applyRetrieveTransaction();
			functionData.chgFieldInputValue("DSC", "Description of XX4");
			functionHandler.applyTransaction();
			Toolbox.printList(functionHandler.print());
			FunctionToolboxStub.printMessages(functionHandler.rtvFunctionMessages().getMessages());

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

			// retrieve journal header
			JournalHeader journalHeader1 = functionHandler.getFhd().getJournalHeader();
			if (journalHeader1 != null)
			{
				System.out.println("Journal 1=" + journalHeader1);
			}
			else
			{
				System.out.println("Journal 1=" + "ERROR");
			}

			JournalHeader journalHeader2 = functionHandler2.getFhd().getJournalHeader();
			if (journalHeader2 != null)
			{
				System.out.println("Journal 2=" + journalHeader2);
			}
			else
			{
				System.out.println("Journal 2=" + "ERROR");
			}

			JournalHeader journalHeader3 = functionHandler3.getFhd().getJournalHeader();
			if (journalHeader3 != null)
			{
				System.out.println("Journal 3=" + journalHeader3);
			}
			else
			{
				System.out.println("Journal 3=" + "ERROR");
			}

			return (journalHeader1 != null && journalHeader2 != null && journalHeader3 != null);
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

	public void testStub04HCX_001()
	{
		FunctionHandlerStub04HCX stub = new FunctionHandlerStub04HCX();
		boolean success = stub.test();
		assertEquals(true, success);

	}

}
