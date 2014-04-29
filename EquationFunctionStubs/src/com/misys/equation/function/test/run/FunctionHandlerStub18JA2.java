package com.misys.equation.function.test.run;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;

// Via API
public class FunctionHandlerStub18JA2 extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub18JA2.java 6793 2010-03-31 12:10:20Z deroset $";
	public FunctionHandlerStub18JA2()
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
		FunctionHandlerStub18JA2 test = new FunctionHandlerStub18JA2();
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
			functionHandler.doNewTransaction("JA2", "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			functionData.chgFieldInputValue("PAB", "0000");
			functionData.chgFieldInputValue("PAN", "500035");
			functionData.chgFieldInputValue("PAS", "100");
			functionHandler.applyRetrieveTransaction();

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

			// add
			functionHandler.applyTransaction();

			// maintain some
			functionHandler.getFhd().getScreenSetHandler().setCurScreenSet(0);
			functionHandler.applyRetrieveTransaction();
			functionData.chgFieldDbValue("SCUS_1", "ACCS");
			functionData.chgFieldDbValue("SCLC_1", "DTA");
			functionData.chgFieldDbValue("SREL_1", "BEN");
			functionData.chgFieldDbValue("SDUP_1", "Y");
			functionHandler.applyTransaction();

			// delete
			functionHandler.getFhd().getScreenSetHandler().setCurScreenSet(0);
			functionHandler.applyRetrieveTransaction();
			functionData.chgFieldDbValue("SINP_1", "D");
			functionData.chgFieldDbValue("SINP_2", "D");
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

	public void testStub18JA2()
	{
		FunctionHandlerStub18JA2 stub = new FunctionHandlerStub18JA2();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
