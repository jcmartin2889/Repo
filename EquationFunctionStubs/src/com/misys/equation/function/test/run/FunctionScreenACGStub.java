package com.misys.equation.function.test.run;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.ScreenSet;
import com.misys.equation.function.runtime.ScreenSetAC2;

// TESTING FOR JOURNAL FILE
public class FunctionScreenACGStub extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionScreenACGStub.java 6793 2010-03-31 12:10:20Z deroset $";
	EquationStandardSession session;

	public FunctionScreenACGStub()
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
		FunctionScreenACGStub test = new FunctionScreenACGStub();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;

		try
		{
			// create the function handler
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");

			// create the function
			functionHandler.doNewTransaction("ALZ", "");

			ScreenSetAC2 screen = new ScreenSetAC2(0, functionHandler.getFhd(), "ALZ", new ScreenSet());
			System.out.println("Done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cleanUp();
		}
	}
}
