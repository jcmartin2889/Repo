package com.misys.equation.function.test.pv;

import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.test.run.FunctionHandlerStubTestCase;
import com.misys.equation.function.test.run.FunctionToolboxStub;

/**
 * FunctionHandler Stub 2
 * 
 * This is how to use the Function Handler validation each screen separately
 * 
 */
public class HTMLToolboxStub extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HTMLToolboxStub.java 6793 2010-03-31 12:10:20Z deroset $";

	public HTMLToolboxStub()
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
		HTMLToolboxStub test = new HTMLToolboxStub();
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

			// Create the function depending on the option taken
			functionHandler.process("ALZ", "", null, null);

			// functionHandler.getScreenSetHandler().nextScreen();
			String html = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetHTML();
			System.out.println(html);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
