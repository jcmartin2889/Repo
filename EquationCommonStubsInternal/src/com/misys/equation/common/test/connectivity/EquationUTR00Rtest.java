package com.misys.equation.common.test.connectivity;

import junit.framework.TestCase;

import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.test.TestEnvironment;

public class EquationUTR00Rtest extends TestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationUTR00Rtest.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	/**
	 * This test is going to evaluate the database connection.
	 */
	public void testEquationPoolConnection() throws EQException
	{

		EquationUnit unit = TestEnvironment.getTestEnvironment().getEquationUnit();

		for (int i = 0; i < 10000; i++)
		{
			boolean test = unit.isEnhancementInstalled("K510");
			assertTrue(test);
			System.out.println(i + ":" + test);
		}

	}
}
