/**
 * Copyright and all other intellectual property rights in this software, in any form, is vested in Misys International Banking
 * Systems Ltd ("Misys") or a related company.
 * 
 * This software may not be copied, amended, compiled, translated, or developed; or sold, leased, hired, rented, or disclosed to any
 * third party without the prior written consent of Misys.
 * 
 * Copyright Misys International Banking Systems Ltd, 1975 and later
 */

package com.misys.equation.common.test.transaction;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.misys.equation.common.test.AbstractTestSuite;

public class OIA_Suite extends AbstractTestSuite
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OIA_Suite.java 4967 2009-09-28 15:54:02Z challip1 $";
	public static Test suite()
	{
		// test suite
		TestSuite suite = new TestSuite("Test for OIA_Suite for Currency movements");

		// Open Internal Accounts
		addTests(suite, AddTest_OIA_J12.class); // for J12 and J31
		addTests(suite, AddTest_OIA_J13.class); // for J13, J14, J21 and J32
		addTests(suite, AddTest_OIA_J15.class); // for J15, J30 and J35
		addTests(suite, AddTest_OIA_J16.class); // for J16, J28 and J34
		addTests(suite, AddTest_OIA_J17.class); // for J17
		addTests(suite, AddTest_OIA_J18.class); // for J18
		addTests(suite, AddTest_OIA_J19.class); // for J19
		addTests(suite, AddTest_OIA_J20.class); // for J20
		addTests(suite, AddTest_OIA_J22.class); // for J22, J23, J24 and J26
		addTests(suite, AddTest_OIA_J25.class); // for J25
		addTests(suite, AddTest_OIA_J27.class); // for J27 and J33
		addTests(suite, AddTest_OIA_J29.class); // for J29
		addTests(suite, AddTest_OIA_J36.class); // for J36, J37 and J38
		addTests(suite, AddTest_OIA_J44.class); // for J44
		addTests(suite, AddTest_OIA_J45.class); // for J45
		addTests(suite, AddTest_OIA_J46.class); // for J46
		addTests(suite, AddTest_OIA_J47.class); // for J47
		addTests(suite, AddTest_OIA_J48.class); // for J48

		return suite;
	}
}
