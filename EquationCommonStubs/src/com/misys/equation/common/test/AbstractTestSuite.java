package com.misys.equation.common.test;

import junit.framework.TestSuite;

/**
 * This class is intended to be used as a base class for TestSuite implementations to abstract the addition of the Tests for a
 * class, and to provide an implementation that adds Tests in a determined (String sort order) sequence. For example, instead of
 * writing code such as:
 * 
 * <pre>
 * suite.addTestSuite(MCD.class);
 * </pre>
 * 
 * you would call the addTests method with the following code:
 * 
 * <pre>
 * addTests(suite, MCD.class);
 * </pre>
 */
public abstract class AbstractTestSuite
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractTestSuite.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	/**
	 * Adds the test methods in the class to the TestSuite.
	 * <p>
	 * This uses the OrderedTestSuite to add the tests in the specified class in alphabetical order
	 * 
	 * @param suite
	 *            The parent TestSuite to add the tests to
	 * @param clazz
	 *            The Test class
	 */
	protected static void addTests(TestSuite suite, Class clazz)
	{
		OrderedTestSuite classSuite = new OrderedTestSuite(clazz);
		suite.addTest(classSuite);
	}
}
