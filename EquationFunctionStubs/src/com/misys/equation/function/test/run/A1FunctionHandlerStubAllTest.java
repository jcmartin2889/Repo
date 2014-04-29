package com.misys.equation.function.test.run;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author weddelc1
 */
public class A1FunctionHandlerStubAllTest
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: A1FunctionHandlerStubAllTest.java 6793 2010-03-31 12:10:20Z deroset $";
	public static Test suite()
	{
		// test suite
		TestSuite suite = new TestSuite("Test Stubs");

		// do not perform clean up
		FunctionHandlerStubTestCase.cleanUp = false;

		suite.addTestSuite(FunctionHandlerStub01.class);
		suite.addTestSuite(FunctionHandlerStub02.class);
		suite.addTestSuite(FunctionHandlerStub03.class);
		suite.addTestSuite(FunctionHandlerStub03NoEFC.class);
		suite.addTestSuite(FunctionHandlerStub03NoOverride.class);
		suite.addTestSuite(FunctionHandlerStub04HCX.class);
		suite.addTestSuite(FunctionHandlerStub04HCX1Add.class);
		suite.addTestSuite(FunctionHandlerStub04HCX2Maint.class);
		suite.addTestSuite(FunctionHandlerStub04HCX3Cancel.class);
		suite.addTestSuite(FunctionHandlerStub04HCX4SAdd.class);
		suite.addTestSuite(FunctionHandlerStub04HCX5SMaint.class);
		suite.addTestSuite(FunctionHandlerStub04HCX6SCancel.class);
		suite.addTestSuite(FunctionHandlerStub05Save.class);
		suite.addTestSuite(FunctionHandlerStub06API1.class);
		suite.addTestSuite(FunctionHandlerStub07API2.class);
		suite.addTestSuite(FunctionHandlerStub08ALY.class);
		suite.addTestSuite(FunctionHandlerStub08ALY1Add.class);
		suite.addTestSuite(FunctionHandlerStub08ALY2Maint.class);
		suite.addTestSuite(FunctionHandlerStub08ALY3Cancel.class);
		suite.addTestSuite(FunctionHandlerStub08ALY4SAdd.class);
		suite.addTestSuite(FunctionHandlerStub08ALY5SMaint.class);
		suite.addTestSuite(FunctionHandlerStub08ALY6SCancel.class);
		suite.addTestSuite(FunctionHandlerStub08ALY7Add.class);
		suite.addTestSuite(FunctionHandlerStub09SuperId.class);
		suite.addTestSuite(FunctionHandlerStub10Enquire.class);
		suite.addTestSuite(FunctionHandlerStub11Report.class);
		suite.addTestSuite(FunctionHandlerStub12JAX1Add.class);
		suite.addTestSuite(FunctionHandlerStub12JAX2Maint.class);
		suite.addTestSuite(FunctionHandlerStub12JAX3Cancel.class);
		suite.addTestSuite(FunctionHandlerStub12JAX4Enquire.class);
		suite.addTestSuite(FunctionHandlerStub13RLX.class);
		suite.addTestSuite(FunctionHandlerStub14RL2.class);
		suite.addTestSuite(FunctionHandlerStub14RL2NoEFC.class);
		suite.addTestSuite(FunctionHandlerStub15ALW.class);
		suite.addTestSuite(FunctionHandlerStub16COM.class);
		suite.addTestSuite(FunctionHandlerStub17ALU1Add.class);
		suite.addTestSuite(FunctionHandlerStub17ALU2Maint.class);
		suite.addTestSuite(FunctionHandlerStub17ALU3Cancel.class);
		suite.addTestSuite(FunctionHandlerStub21MCY.class);
		suite.addTestSuite(FunctionHandlerStub22ASY.class);
		suite.addTestSuite(FunctionHandlerStub23WPD1Add.class);
		suite.addTestSuite(FunctionHandlerStub23WPD2Maint.class);
		suite.addTestSuite(FunctionHandlerStub23WPD3Cancel.class);
		suite.addTestSuite(FunctionHandlerStubZT1Add.class);
		suite.addTestSuite(FunctionHandlerStub24Refer.class);
		suite.addTestSuite(FunctionHandlerStub25ZY1.class);
		suite.addTestSuite(FunctionHandlerStub26ZY2.class);
		suite.addTestSuite(FunctionHandlerStub27ZY4.class);
		suite.addTestSuite(FunctionHandlerStub28ZY6.class);
		suite.addTestSuite(FunctionHandlerStub29ZY7.class);
		suite.addTestSuite(FunctionPrinterStub.class);
		suite.addTestSuite(FunctionPrinterStub2.class);
		suite.addTestSuite(EquationAPICacheStub.class);

		// last test stub
		suite.addTestSuite(FunctionHandlerStub99.class);

		return suite;
	}

}
