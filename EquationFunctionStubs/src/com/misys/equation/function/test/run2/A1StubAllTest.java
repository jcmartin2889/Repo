package com.misys.equation.function.test.run2;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.misys.equation.function.test.run.FunctionHandlerStub99;
import com.misys.equation.function.test.run.FunctionHandlerStubTestCase;

/**
 * @author weddelc1
 */
public class A1StubAllTest
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: A1StubAllTest.java 7544 2010-05-28 09:47:29Z lima12 $";
	public static Test suite()
	{
		// test suite
		TestSuite suite = new TestSuite("Test Stubs");

		// do not perform clean up
		FunctionHandlerStubTestCase.cleanUp = false;

		suite.addTestSuite(AA1.class);
		suite.addTestSuite(AA1.class);
		suite.addTestSuite(AA1.class);
		suite.addTestSuite(AA1.class);
		suite.addTestSuite(AA1.class);

		suite.addTestSuite(AA2.class);
		suite.addTestSuite(AA2.class);
		suite.addTestSuite(AA2.class);
		suite.addTestSuite(AA2.class);
		suite.addTestSuite(AA2.class);

		suite.addTestSuite(AA3.class);
		suite.addTestSuite(AA3.class);
		suite.addTestSuite(AA3.class);
		suite.addTestSuite(AA3.class);
		suite.addTestSuite(AA3.class);

		suite.addTestSuite(AA4.class);
		suite.addTestSuite(AA4.class);
		suite.addTestSuite(AA4.class);
		suite.addTestSuite(AA4.class);
		suite.addTestSuite(AA4.class);

		suite.addTestSuite(AA5.class);
		suite.addTestSuite(AA5.class);
		suite.addTestSuite(AA5.class);
		suite.addTestSuite(AA5.class);
		suite.addTestSuite(AA5.class);

		suite.addTestSuite(AA6.class);
		suite.addTestSuite(AA6.class);
		suite.addTestSuite(AA6.class);
		suite.addTestSuite(AA6.class);
		suite.addTestSuite(AA6.class);

		suite.addTestSuite(AA7.class);
		suite.addTestSuite(AA7.class);
		suite.addTestSuite(AA7.class);
		suite.addTestSuite(AA7.class);
		suite.addTestSuite(AA7.class);

		suite.addTestSuite(AA8.class);
		suite.addTestSuite(AA8.class);
		suite.addTestSuite(AA8.class);
		suite.addTestSuite(AA8.class);
		suite.addTestSuite(AA8.class);

		suite.addTestSuite(AZ1.class);
		suite.addTestSuite(AZ1.class);
		suite.addTestSuite(AZ1.class);
		suite.addTestSuite(AZ1.class);
		suite.addTestSuite(AZ1.class);

		suite.addTestSuite(AZ2.class);
		suite.addTestSuite(AZ2.class);
		suite.addTestSuite(AZ2.class);
		suite.addTestSuite(AZ2.class);
		suite.addTestSuite(AZ2.class);

		// last test stub
		suite.addTestSuite(FunctionHandlerStub99.class);

		return suite;
	}

}
