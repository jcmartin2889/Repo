package com.misys.equation.function.test.run;

import java.util.Calendar;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionMessages;

// Via API
public class FunctionHandlerStub17ALU3Cancel extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub17ALU3Cancel.java 6793 2010-03-31 12:10:20Z deroset $";

	public FunctionHandlerStub17ALU3Cancel()
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
		FunctionHandlerStub17ALU3Cancel test = new FunctionHandlerStub17ALU3Cancel();
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
			boolean valid = functionHandler.doNewTransaction("ALU", "");

			if (!valid)
			{
				FunctionToolboxStub
								.printMessages(functionHandler.getFhd().getFunctionMsgManager().getOtherMessages().getMessages());
				FunctionToolboxStub.printMessages(functionHandler.rtvFunctionMessages().getMessages());
				return false;
			}

			// function data
			FunctionData functionData = functionHandler.rtvFunctionData();

			// keys
			functionData.chgFieldInputValue("AHLD", "ALU");
			int msgSev = functionHandler.applyRetrieveTransaction();

			String date = Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT).replaceAll(" ", "").replaceAll(":",
							"");
			date = date.substring(date.indexOf(".") + 1);

			if (msgSev < FunctionMessages.MSG_ERROR)
			{
				// details
				functionData.chgFieldInputValue("ADRF", "C" + date);
				functionData.chgFieldInputValue("ADSC", "C" + date);
				functionHandler.applyTransactionDelete();
			}

			// print it
			Toolbox.printList(functionHandler.print());

			// any messages
			FunctionToolboxStub.printMessages(functionHandler.rtvFunctionMessages().getMessages());

			// retrieve journal header
			System.out.println(date);
			JournalHeader journalHeader = functionHandler.getFhd().getJournalHeader();
			if (journalHeader != null)
			{
				System.out.println(journalHeader);
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

	public void testStub17ALU3Cancel()
	{
		FunctionHandlerStub17ALU3Cancel stub = new FunctionHandlerStub17ALU3Cancel();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}