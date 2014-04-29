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
public class AA7 extends FunctionHandlerStubTestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AA7.java 7623 2010-06-02 13:49:04Z lima12 $";
	public AA7()
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
		AA7 test = new AA7();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			String id = "TEST_AA7";
			String option = "AA7";
			String date = Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT).replaceAll(" ", "").replaceAll(":",
							"");
			System.out.println("------------------------------- 1");
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");

			EqTimingTest.printStartTime(id, "");
			functionHandler.doNewTransaction(option, "");
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
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

	public void testAA7()
	{
		AA7 stub = new AA7();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}