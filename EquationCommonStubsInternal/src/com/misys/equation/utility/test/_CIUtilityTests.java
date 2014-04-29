/**
 * Copyright and all other intellectual property rights in this software, in any form, is vested in Misys International Banking
 * Systems Ltd ("Misys") or a related company.
 * 
 * This software may not be copied, amended, compiled, translated, or developed; or sold, leased, hired, rented, or disclosed to any
 * third party without the prior written consent of Misys.
 * 
 * Copyright Misys International Banking Systems Ltd, 1975 and later
 */

package com.misys.equation.utility.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class _CIUtilityTests extends TestSuite
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: _CIUtilityTests.java 13384 2012-05-11 21:29:57Z jonathan.perkins $";

	public static Test suite()
	{
		// test suite
		TestSuite suite = new TestSuite("Test CI ");

		suite.addTestSuite(Dajobctle.class);
		suite.addTestSuite(SortMapTest.class);
		suite.addTestSuite(KeyedArrayListTest.class);

		return suite;
	}
}
