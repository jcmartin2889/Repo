package com.misys.equation.function.test.run;

import java.util.Calendar;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;

// Via API
public class FunctionHandlerStub22ASY extends FunctionHandlerStubTestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub22ASY.java 6793 2010-03-31 12:10:20Z deroset $";
	public FunctionHandlerStub22ASY()
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
		FunctionHandlerStub22ASY test = new FunctionHandlerStub22ASY();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler2 = null;
		try
		{
			// Maintain
			System.out.println("--------------------------- ASY");
			functionHandler2 = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler2.doNewTransaction("ASY", "");

			FunctionData functionData2 = functionHandler2.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			String REF = Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT).replaceAll(" ", "").replaceAll(":",
							"");
			String REF2 = REF.substring(REF.length() - 16);

			if (externalInputTest)
			{
				REF2 = "9.07.08150519454";
			}

			functionData2.chgFieldInputValue("ASO_REF", REF2);
			functionData2.chgFieldInputValue("MSD_REF", REF2);
			functionHandler2.applyRetrieveTransaction();

			functionHandler2.applyTransaction();

			Toolbox.printList(functionHandler2.print());
			FunctionToolboxStub.printMessages(functionHandler2.rtvFunctionMessages().getMessages());

			JournalHeader journalHeader = functionHandler2.getFhd().getJournalHeader();
			System.out.println(REF2);
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

	public void testStub22ASY_001()
	{
		FunctionHandlerStub22ASY stub = new FunctionHandlerStub22ASY();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}