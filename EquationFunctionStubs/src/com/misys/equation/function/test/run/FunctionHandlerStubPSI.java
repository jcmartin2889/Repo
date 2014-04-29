package com.misys.equation.function.test.run;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;

/**
 * FunctionHandler Stub 1
 * 
 * This is how to use the Function Handler with validate and update processing done separately
 * 
 */
public class FunctionHandlerStubPSI
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStubPSI.java 16593 2013-06-24 15:32:19Z perkinj1 $";
	EquationStandardSession session;
	EquationUnit unit;
	EquationUser user;

	public FunctionHandlerStubPSI()
	{
		try
		{
			session = TestEnvironment.getTestEnvironment().getStandardSession();
			unit = TestEnvironment.getTestEnvironment().getEquationUnit();
			user = new EquationUser(unit, "EQUIADMIN", "EQUIADMIN", null);
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters)
	{
		FunctionHandlerStubPSI test = new FunctionHandlerStubPSI();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		FunctionHandler functionHandler = null;
		try
		{
			long startTime = System.nanoTime();
			// create the function handler
			functionHandler = FunctionToolboxStub.getFunctionHandler(user, "SESSIONID", "");
			// Set up the api type
			functionHandler.doNewTransaction("PSI", "");

			for (int x = 0; x < 100; x++)
			{
				// create the function
				// Get a handler to the function data
				FunctionData functionData = functionHandler.getFhd().getScreenSetHandler().rtvScrnSetCurrent().getFunctionData();

				// This is how you setup the field given its input value (as if entered through the desktop)
				functionData.chgFieldInputValue("PSI_AB", "0543"); // Account branch (4A)
				functionData.chgFieldInputValue("PSI_AN", "123107"); // Basic part of account number (6A)
				functionData.chgFieldInputValue("PSI_AS", "001"); // Account suffix (3A)
				functionData.chgFieldInputValue("PSI_TCD", "510"); // Transaction code (3A)
				functionData.chgFieldInputValue("PSI_CCY", "GBP"); // Currency mnemonic (3A)
				functionData.chgFieldInputValue("PSI_AMA", "543200"); // Ordinary amount in minor currency units (15P,0)
				functionData.chgFieldInputValue("PSI_VFR", "1000106"); // Value from date (7S,0)
				functionData.chgFieldInputValue("PSI_DRF", "JavaTest" + x); // Users own reference for deals, reconciliation etc
				functionData.chgFieldInputValue("PSI_NR1", "TEST NARRATIVE1");
				functionData.chgFieldInputValue("PSI_NR2", "TEST NARRATIVE2");
				functionData.chgFieldInputValue("PSI_NPE", "1"); // Number of posting entries (5P,0)
				functionData.chgFieldInputValue("PSI_PBR", "JUNT"); // Posting group id or user id, and group level (5A)
				functionData.chgFieldInputValue("PSI_BRNM", "LOND"); // Branch mnemonic (4A)
				int sev = functionHandler.applyTransaction();
				System.out.println("Max Severity : " + sev);
			}
			System.err.println("Duration : " + ((System.nanoTime() - startTime) / 1000000) + "ms");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
