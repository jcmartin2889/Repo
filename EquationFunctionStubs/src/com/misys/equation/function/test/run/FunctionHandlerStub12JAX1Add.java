package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;

// Via API
public class FunctionHandlerStub12JAX1Add extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub12JAX1Add.java 6793 2010-03-31 12:10:20Z deroset $";

	public FunctionHandlerStub12JAX1Add()
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
		FunctionHandlerStub12JAX1Add test = new FunctionHandlerStub12JAX1Add();
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
			if (functionData.rtvFieldDbValue("SCUS_1").equals(""))
			{
				valid001 = functionData.rtvFieldDbValue("wfFCT_1").equals("");
				valid002 = functionData.rtvFieldDbValue("SREL_1").equals("");
				valid003 = functionData.rtvFieldDbValue("SCLC_1").equals("");
			}
			else
			{
				valid001 = functionData.rtvFieldDbValue("wfFCT_1").equals("M");
				valid002 = !functionData.rtvFieldDbValue("SREL_1").equals("");
				valid003 = !functionData.rtvFieldDbValue("SCLC_1").equals("");
			}
			if (!valid001)
			{
				System.out.println("Incorrect retrieval mode 001: work field value adaptor");
			}
			if (!valid002)
			{
				System.out.println("Incorrect retrieval mode 002: input field value adaptor");
			}
			if (!valid003)
			{
				System.out.println("Incorrect retrieval mode 003: input field mapping");
			}

			// if secondary customer is specified, then mode must be M
			boolean valid004 = false;
			boolean valid005 = false;
			boolean valid006 = false;
			if (functionData.rtvFieldDbValue("SCUS_10").equals(""))
			{
				valid004 = functionData.rtvFieldDbValue("wfFCT_10").equals("");
				valid005 = functionData.rtvFieldDbValue("SREL_10").equals("");
				valid006 = functionData.rtvFieldDbValue("SCLC_10").equals("");
			}
			else
			{
				valid004 = functionData.rtvFieldDbValue("wfFCT_10").equals("M");
				valid005 = !functionData.rtvFieldDbValue("SREL_10").equals("");
				valid006 = !functionData.rtvFieldDbValue("SCLC_10").equals("");
			}
			if (!valid004)
			{
				System.out.println("Incorrect retrieval mode 004: work field value adaptor");
			}
			if (!valid005)
			{
				System.out.println("Incorrect retrieval mode 005: input field value adaptor");
			}
			if (!valid006)
			{
				System.out.println("Incorrect retrieval mode 006: input field mapping");
			}

			// System.out.println(functionData);

			// narratives
			functionData.chgFieldDbValue("NR1", "narrative 1");
			functionData.chgFieldDbValue("NR2", "narrative 2");
			functionData.chgFieldDbValue("NR3", "narrative 3");
			functionData.chgFieldDbValue("NR4", "narrative 4");

			// add some secondary customers
			functionData.chgFieldDbValue("SCUS_1", "ABRAHA");
			functionData.chgFieldDbValue("SCLC_1", "002");
			functionData.chgFieldDbValue("SREL_1", "BEN");
			functionData.chgFieldDbValue("SDUP_1", "Y");

			// add some secondary customers
			functionData.chgFieldDbValue("SCUS_2", "ABRAHA");
			functionData.chgFieldDbValue("SCLC_2", "003");
			functionData.chgFieldDbValue("SREL_2", "GUA");
			functionData.chgFieldDbValue("SDUP_2", "N");

			// add some secondary customers
			functionData.chgFieldDbValue("SCUS_3", "ACCS");
			functionData.chgFieldDbValue("SCLC_3", "DTA");
			functionData.chgFieldDbValue("SREL_3", "GUA");
			functionData.chgFieldDbValue("SDUP_3", "N");

			// add some secondary customers
			functionData.chgFieldDbValue("SCUS_5", "ALKH");
			functionData.chgFieldDbValue("SCLC_5", "");
			functionData.chgFieldDbValue("SREL_5", "BEN");
			functionData.chgFieldDbValue("SDUP_5", "Y");

			// add some secondary customers
			functionData.chgFieldDbValue("SCUS_6", "A00001");
			functionData.chgFieldDbValue("SCLC_6", "");
			functionData.chgFieldDbValue("SREL_6", "JNT");
			functionData.chgFieldDbValue("SDUP_6", "Y");

			functionHandler.applyTransaction();

			// output will be
			// ABRAHA 002 BEN Y
			// ABRAHA 003 GUA N
			// ACCS DTA GUA N
			// ALKH BEN Y
			// A00001 JNT Y

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
		FunctionHandlerStub12JAX1Add stub = new FunctionHandlerStub12JAX1Add();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
