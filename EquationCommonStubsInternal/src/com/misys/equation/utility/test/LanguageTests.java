package com.misys.equation.utility.test;

import java.util.Properties;

import junit.framework.TestCase;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.EquationLogger;

/**
 * Test class to investigate conversion round tripping
 * 
 * @author perkinj1
 * 
 */
public class LanguageTests extends TestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LanguageTests.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	/**
	 * Logger for this class
	 */
	private static final EquationLogger LOG = new EquationLogger(TestEnvironment.class);

	/**
	 * 
	 */
	public void test()
	{
		EquationUser eqUser = TestEnvironment.getTestEnvironment().getEquationUser();
		Properties properties = EquationCommonContext.getContext().getEquationDesktopLanguageResources();
		String x = EquationCommonContext.getContext().getLanguageResource(eqUser, "XYZ");
		System.out.println(x);
	}

}