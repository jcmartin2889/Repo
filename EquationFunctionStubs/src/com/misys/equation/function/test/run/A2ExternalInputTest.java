package com.misys.equation.function.test.run;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author weddelc1
 */
public class A2ExternalInputTest
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: A2ExternalInputTest.java 6793 2010-03-31 12:10:20Z deroset $";
	public static Test suite()
	{
		// test suite
		TestSuite suite = new TestSuite("External Input Testing");

		// do not perform clean up
		FunctionHandlerStubTestCase.cleanUp = false;

		suite.addTestSuite(FunctionHandlerStub12JAX1Add.class);
		suite.addTestSuite(FunctionHandlerStub12JAX2Maint.class);
		suite.addTestSuite(FunctionHandlerStub13RLX.class);
		suite.addTestSuite(FunctionHandlerStub14RL2.class);
		suite.addTestSuite(FunctionHandlerStub14RL2NoEFC.class);
		suite.addTestSuite(FunctionHandlerStub16COM.class);
		suite.addTestSuite(FunctionHandlerStub17ALU1Add.class);
		suite.addTestSuite(FunctionHandlerStub21MCY.class);
		suite.addTestSuite(FunctionHandlerStub22ASY.class);
		suite.addTestSuite(FunctionHandlerStub23WPD1Add.class);
		suite.addTestSuite(FunctionHandlerStub23WPD2Maint.class);
		suite.addTestSuite(FunctionHandlerStubZT1Add.class);

		// last test stub
		suite.addTestSuite(FunctionHandlerStub99.class);

		return suite;
	}

}
