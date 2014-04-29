package com.misys.equation.function.test.beans;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test Suite to run individual Bean tests
 */
public class TestBeansSuite
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestBeansSuite.java 13001 2012-03-16 08:40:07Z jonathan.perkins $";

	public static Test suite()
	{
		// Create Test Suite
		TestSuite suite = new TestSuite("Bean Tests");

		suite.addTestSuite(FieldSetTest.class);
		suite.addTestSuite(FunctionBeanTest.class);
		suite.addTestSuite(InputFieldBeanTest.class);
		suite.addTestSuite(LayoutBeanStub.class);

		return suite;
	}
}
