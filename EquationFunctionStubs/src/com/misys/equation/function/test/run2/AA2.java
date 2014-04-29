package com.misys.equation.function.test.run2;

import java.util.Calendar;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.EqTimingTest;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.test.run.FunctionHandlerStubTestCase;
import com.misys.equation.function.test.run.FunctionToolboxStub;
import com.misys.equation.function.tools.SupervisorToolbox;

/**
 * FunctionHandler Stub 1
 * 
 * This is how to use the Function Handler with validate and update processing done separately
 * 
 */
public class AA2 extends FunctionHandlerStubTestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AA2.java 7544 2010-05-28 09:47:29Z lima12 $";
	public AA2()
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
		AA2 test = new AA2();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			String id = "TEST_AA2";
			String option = "AA2";
			String date = Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT).replaceAll(" ", "").replaceAll(":",
							"");
			System.out.println("------------------------------- 1");
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");

			EqTimingTest.printStartTime(id, "");
			functionHandler.doNewTransaction(option, "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			functionData.chgFieldInputValue("ASI_AB", "9132");
			functionData.chgFieldInputValue("ASI_AN", "234567");
			functionData.chgFieldInputValue("ASI_AS", "001");
			functionData.chgFieldInputValue("ASI_TCD", "510");
			EqTimingTest.printInterimTime(id, "retrieving....");
			functionHandler.applyRetrieveTransaction();
			functionData.chgFieldInputValue("ASI_TCCY", "GBP");
			functionData.chgFieldInputValue("ASI_TAMA", "1T");
			functionData.chgFieldInputValue("ASI_DRF", "Test-Stub");
			functionData.chgFieldInputValue("ASI_NR1", date);

			EqTimingTest.printInterimTime(id, "apply....");
			functionHandler.applyTransaction();
			EqTimingTest.printTime(id, "");
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

	public void testAA2()
	{
		AA2 stub = new AA2();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
