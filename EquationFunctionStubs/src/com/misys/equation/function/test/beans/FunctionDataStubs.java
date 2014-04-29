package com.misys.equation.function.test.beans;

import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionInfo;
import com.misys.equation.function.test.run.FunctionHandlerStubTestCase;

// Text the context string loading
public class FunctionDataStubs extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionDataStubs.java 5244 2009-11-02 10:48:00Z lima12 $";

	public FunctionDataStubs()
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
		FunctionDataStubs test = new FunctionDataStubs();
		test.test();
	}

	private void test()
	{
		FunctionHandler functionHandler = null;
		// Have a bash...
		try
		{
			// create the function handler
			FunctionInfo functionInfo = new FunctionInfo("SESSIONID", "");
			functionHandler = new FunctionHandler(user, functionInfo);
			functionHandler.rtvEquationSession();

			// create the function
			functionHandler.doNewTransaction("ALZ", "");

			// get the function
			Function function = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunction();

			// get the function data
			FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

			// load the context
			String context = "A:NR1=narrative 1 1:C:D:DRF=reference";
			functionData.loadFieldDataFromContext(function, context, true);

			// print the function data
			System.out.println(functionData);

			String s = functionData.cvtToXML();
			System.out.println(s);
			FunctionData data = new FunctionData();
			data.loadFieldDataFromXML(s);

			System.out.println("Done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}