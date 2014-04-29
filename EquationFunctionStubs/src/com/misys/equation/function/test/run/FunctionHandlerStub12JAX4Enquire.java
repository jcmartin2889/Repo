package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;

// Via API
public class FunctionHandlerStub12JAX4Enquire extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub12JAX4Enquire.java 6793 2010-03-31 12:10:20Z deroset $";

	public FunctionHandlerStub12JAX4Enquire()
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
		FunctionHandlerStub12JAX4Enquire test = new FunctionHandlerStub12JAX4Enquire();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			// Add XX4
			System.out.println("--------------------------- 1");
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler.doNewTransaction("JAX", "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			functionData.chgFieldInputValue("PAB", "0000");
			functionData.chgFieldInputValue("PAN", "500035");
			functionData.chgFieldInputValue("PAS", "101");
			functionHandler.applyRetrieveTransaction();

			functionHandler.applyTransaction();

			FunctionToolboxStub.printMessages(functionHandler.rtvFunctionMessages().getMessages());

			boolean valid = false;
			for (int i = 0; i < functionHandler.rtvFunctionMessages().getMessages().size(); i++)
			{
				if (functionHandler.rtvFunctionMessages().getMessages().get(i).getEqMessage().getMessageID().equals("KSM7370"))
				{
					valid = true;
					break;
				}
			}

			// retrieve journal header
			JournalHeader journalHeader = functionHandler.getFhd().getJournalHeader();
			if (journalHeader != null)
			{
				System.out.println(journalHeader);
			}
			return (valid);
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

	public void testStub12JAX1Add()
	{
		FunctionHandlerStub12JAX4Enquire stub = new FunctionHandlerStub12JAX4Enquire();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
