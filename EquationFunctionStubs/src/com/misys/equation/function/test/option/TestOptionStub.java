package com.misys.equation.function.test.option;

import junit.framework.TestCase;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionHandlerStubTestCase;

public class TestOptionStub extends TestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestOptionStub.java 17139 2013-08-29 16:00:56Z whittap1 $";
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(FunctionHandlerStubTestCase.class);

	protected EquationStandardSession session;
	protected EquationUnit unit;
	protected static EquationUser user;

	// Location of the workspace
	public static String workSpace = "C:\\MYDATA\\workspace\\EquationDevelopment030000\\EquationFunctionStubs\\bin\\";
	public static boolean printXML = false;

	@Override
	protected void setUp() throws Exception
	{
		LOG.info("entering setUp()");
		if (session == null)
		{
			unit = TestEnvironment.getTestEnvironment().getEquationUnit();
			user = TestEnvironment.getTestEnvironment().getEquationUser();
			session = user.getSession();
		}
		super.setUp();
		LOG.info("exiting setUp()");
	}

	protected void printStartStatus(String className)
	{
		System.out.println("Generating.... " + className);
	}

	protected void printCompleteStatus(FunctionGenerator fg, String className, boolean printXML) throws Exception
	{
		System.out.println("Generated: " + className);
		if (printXML)
		{
			String serviceXML = fg.getService();
			String layoutXML = fg.getLayout();
			System.out.println(serviceXML);
			System.out.println(layoutXML);
		}
	}

}
