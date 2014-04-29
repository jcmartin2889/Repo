package com.misys.equation.function.test.run;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.function.context.BFEQCredentials;
import com.misys.equation.function.context.EquationFunctionContext;

// Via API
public class FunctionContextSessionStub
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationAPICacheStub.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	public FunctionContextSessionStub()
	{

	}

	public static void main(String[] inputParameters)
	{
		FunctionContextSessionStub test = new FunctionContextSessionStub();
		test.test();
	}

	public boolean test()
	{
		// Have a bash...
		try
		{

			boolean valid = true;
			System.setProperty("disable.bankfusion.features", "true");
			EquationUnit unit = TestEnvironment.getTestEnvironment().getEquationUnit();
			// EquationStandardSession session2 = TestEnvironment.getTestEnvironment().getStandardSession();
			// EquationSystem system = new EquationSystem("SLOUGH1", "equiadmin", "equiadmin");
			// EquationUnit unit = new EquationUnit(system, "X05", "equiadmin", "equiadmin", false);
			// EquationCommonContext.getContext().getEqSession("SLOUGH1", "X05", "equiadmin", "equiadmin", null,
			// InetAddress.getLocalHost().getHostAddress(), EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN,
			// EquationCommonContext.SESSION_OTHER_MODE);

			BFEQCredentials credentials = new BFEQCredentials("casadmin", "casdmin",
							EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, "");
			String sessionId = EquationFunctionContext.getContext().getEqSession("SLOUGH1", "EW9", credentials, "localhost",
							EquationCommonContext.SESSION_OTHER_MODE, "ESTHERTEST");

			EquationStandardSession session = EquationCommonContext.getContext().getEquationUser(sessionId).getSession();
			if (valid)
			{
				System.out.println("OK");
			}

			return valid;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}

	}

}
