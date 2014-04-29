package com.misys.equation.function.test.run;

import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.RepeatingDataManager;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.tools.SupervisorToolbox;

/**
 * FunctionHandler Stub 1
 * 
 * This is how to use the Function Handler with validate and update processing done separately
 * 
 */
public class FunctionHandlerStub29ZY7 extends FunctionHandlerStubTestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub29ZY7.java 5744 2010-01-06 12:19:19Z lima12 $";
	public FunctionHandlerStub29ZY7()
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
		FunctionHandlerStub29ZY7 test = new FunctionHandlerStub29ZY7();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			// Add XX4
			System.out.println("------------------------------- 1");
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			functionHandler.doNewTransaction("ZY7", "");
			Function function = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunction();
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();
			functionData.chgFieldDbValue("DLP", "FXF");
			functionData.chgFieldDbValue("DLR", "Y2FL101");
			functionData.chgFieldDbValue("BRNM", "CITY");
			RepeatingDataManager dataManager1 = functionData.getRepeatingDataManagers().get(0);
			functionHandler.applyRetrieveTransaction();

			// check that we have enough rows
			System.out.println("CONTENT OF FUNCTIONDATA");
			System.out.println(functionData);
			System.out.println("===========");
			FunctionToolboxStub.printMessages(functionHandler.rtvFunctionMessages().getMessages());
			assertTrue(functionHandler.rtvFunctionMessages().getMessages().size() == 0);
			assertTrue(dataManager1.totalRows() == 3);

			return (true);
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

	public void testStub29ZY7()
	{
		FunctionHandlerStub29ZY7 stub = new FunctionHandlerStub29ZY7();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}