package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;

/**
 * FunctionHandler Stub 1
 * 
 * This is how to use the Function Handler with validate and update processing done separately
 * 
 */
public class FunctionHandlerStub11Report extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub11Report.java 6793 2010-03-31 12:10:20Z deroset $";

	public FunctionHandlerStub11Report()
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
		FunctionHandlerStub11Report test = new FunctionHandlerStub11Report();
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
			functionHandler.doNewTransaction("RPT", "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			functionData.chgFieldInputValue("RPTM", "P51AALL");
			functionData.chgFieldInputValue("RPTS", "");
			functionHandler.applyTransaction();

			FunctionToolboxStub.printMessages(functionHandler.rtvFunctionMessages().getMessages());
			System.out.println(functionHandler.getFhd().getScreenSetHandler().rtvScreenSetMain().getFunctionData());

			JournalHeader journalHeader = functionHandler.getFhd().getJournalHeader();
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

	public void testStub11Report_001()
	{
		FunctionHandlerStub11Report stub = new FunctionHandlerStub11Report();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
