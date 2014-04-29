package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;

// Via API
public class FunctionHandlerMAB extends FunctionHandlerStubTestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerMAB.java 6793 2010-03-31 12:10:20Z deroset $";
	public FunctionHandlerMAB()
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
		FunctionHandlerMAB test = new FunctionHandlerMAB();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler2 = null;
		try
		{
			// Maintain
			System.out.println("--------------------------- MA1");
			functionHandler2 = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler2.doNewTransaction("MA1", "");

			FunctionData functionData2 = functionHandler2.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			functionData2.chgFieldInputValue("MAB_AB", "0000");
			functionData2.chgFieldInputValue("MAB_AN", "0000001");
			functionData2.chgFieldInputValue("MAB_AS", "001");
			functionHandler2.applyRetrieveTransaction();

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

}
