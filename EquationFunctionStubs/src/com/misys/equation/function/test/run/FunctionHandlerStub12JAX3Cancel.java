package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;

// Via API
public class FunctionHandlerStub12JAX3Cancel extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub12JAX3Cancel.java 6793 2010-03-31 12:10:20Z deroset $";

	public FunctionHandlerStub12JAX3Cancel()
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
		FunctionHandlerStub12JAX3Cancel test = new FunctionHandlerStub12JAX3Cancel();
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
			functionData.chgFieldInputValue("PAS", "100");
			functionHandler.applyRetrieveTransaction();

			// if secondary customer is specified, then mode must be M
			boolean valid001 = false;
			boolean valid002 = false;
			boolean valid003 = false;
			if (functionData.rtvFieldDbValue("SCUS_1").equals("ABRAHA"))
			{
				valid001 = functionData.rtvFieldDbValue("wfFCT_1").equals("M");
				valid002 = functionData.rtvFieldDbValue("SREL_1").equals("GUA");
				valid003 = functionData.rtvFieldDbValue("SDUP_1").equals("N");
			}
			if (!valid001 && !valid002 && !valid003)
			{
				System.out.println("Failed to update/load record 1");
			}

			// if secondary customer is specified, then mode must be M
			boolean valid004 = false;
			boolean valid005 = false;
			boolean valid006 = false;
			if (functionData.rtvFieldDbValue("SCUS_4").equals("A00001"))
			{
				valid004 = functionData.rtvFieldDbValue("SCLC_4").equals("");
				valid005 = functionData.rtvFieldDbValue("SREL_4").equals("TAK");
				valid006 = functionData.rtvFieldDbValue("SDUP_4").equals("N");
			}
			if (!valid004 && !valid005 && !valid006)
			{
				System.out.println("Failed to update/load record 2");
			}

			System.out.println(functionData);

			// delete
			functionData.chgFieldDbValue("SINP_1", "D");
			functionData.chgFieldDbValue("SINP_2", "D");
			functionData.chgFieldDbValue("SINP_3", "D");
			functionData.chgFieldDbValue("SINP_4", "D");
			functionData.chgFieldDbValue("SINP_5", "D");

			functionHandler.applyTransaction();

			// output will be
			// ABRAHA 003 GUA N
			// ABROL VIK BEN Y
			// ALKH BEN Y
			// A00001 TAK N
			// ALLEN GIV Y

			Toolbox.printList(functionHandler.print());
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

			boolean valid = valid001 && valid002 && valid003 && valid004 && valid005 && valid006;
			return (journalHeader != null && valid);
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
		FunctionHandlerStub12JAX3Cancel stub = new FunctionHandlerStub12JAX3Cancel();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
