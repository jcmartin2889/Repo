package com.misys.equation.function.test.run2;

import java.util.Calendar;

import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.utilities.EqTimingTest;
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
public class AZ1 extends FunctionHandlerStubTestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AZ1.java 7544 2010-05-28 09:47:29Z lima12 $";
	public AZ1()
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
		AZ1 test = new AZ1();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			String id = "TEST_AZ1";
			String option = "AZ1";
			Calendar cal = Calendar.getInstance();

			String date = String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + String.valueOf(cal.getTimeInMillis());
			System.out.println("------------------------------- 1");
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");

			EqTimingTest.printStartTime(id, "");
			functionHandler.doNewTransaction(option, "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			EqTimingTest.printInterimTime(id, "retrieving....");
			functionHandler.applyRetrieveTransaction();
			functionData.chgFieldInputValue("DLR", date);

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

	public void testAZ1()
	{
		AZ1 stub = new AZ1();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
