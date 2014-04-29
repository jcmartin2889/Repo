package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.tools.SupervisorToolbox;

/**
 * FunctionHandler Stub 1
 * 
 * This is how to use the Function Handler with validate and update processing done separately
 * 
 */
public class FunctionHandlerStub16COM extends FunctionHandlerStubTestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub16COM.java 6793 2010-03-31 12:10:20Z deroset $";
	public FunctionHandlerStub16COM()
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
		FunctionHandlerStub16COM test = new FunctionHandlerStub16COM();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			System.out.println("------------------------------- 1");
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler.doNewTransaction("COM", "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			functionData.chgFieldInputValue("CMR", "TEST-COMMIT ");
			functionData.chgFieldInputValue("DLA", "10T");
			// functionData.chgFieldInputValue("CCY", "GBP");

			functionHandler.applyTransaction();
			FunctionToolboxStub.printMessages(functionHandler.rtvFunctionMessages().getMessages());

			// retrieve journal header
			JournalHeader journalHeader = functionHandler.getFhd().getJournalHeader();
			if (journalHeader != null)
			{
				System.out.println("Journal 1=" + journalHeader);
			}
			else
			{
				System.out.println("Journal 1=" + "ERROR");
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

	public void testStub16COM()
	{
		FunctionHandlerStub16COM stub = new FunctionHandlerStub16COM();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
