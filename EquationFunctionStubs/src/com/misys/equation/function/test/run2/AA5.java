package com.misys.equation.function.test.run2;

import java.util.Calendar;

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
public class AA5 extends FunctionHandlerStubTestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AA5.java 7623 2010-06-02 13:49:04Z lima12 $";
	public AA5()
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
		AA5 test = new AA5();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			String id = "TEST_AA5";
			String option = "AA5";
			String date = Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT).replaceAll(" ", "").replaceAll(":",
							"");
			System.out.println("------------------------------- 1");
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");

			EqTimingTest.printStartTime(id, "");
			functionHandler.doNewTransaction(option, "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			functionData.chgFieldInputValue("TH_HZAB", "3000");
			functionData.chgFieldInputValue("TH_HZAN", "L00001");
			functionData.chgFieldInputValue("TH_HZAS", "001");
			EqTimingTest.printInterimTime(id, "retrieving....");
			functionHandler.applyRetrieveTransaction();
			EqTimingTest.printTime(id, "");

			int totalRows = functionData.getRepeatingDataManagers().get(0).totalRows();
			System.out.println("total records retrieve is : " + totalRows);
			// System.out.println(functionData.getRepeatingDataManagers().get(0).toString());
			FunctionToolboxStub.printMessages(functionHandler.rtvFunctionMessages().getMessages());

			// retrieve journal header
			return (totalRows > 300);
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

	public void testAA5()
	{
		AA5 stub = new AA5();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}