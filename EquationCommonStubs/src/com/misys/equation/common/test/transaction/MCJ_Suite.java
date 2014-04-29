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

public class MCJ_Suite extends AbstractTestSuite
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MCJ_Suite.java 4967 2009-09-28 15:54:02Z challip1 $";
	public static Test suite()
	{
		// test suite
		TestSuite suite = new TestSuite("Test for Customer Type Interface Information");

		// addTests(suite, AddTest_MGB_MCJ_1.class); // Add/Maintain Interface Groups
		// addTests(suite, AddTest_MGB_MCJ_2.class); // Add/Maintain Interface Groups
		// addTests(suite, AddTest_MGB_MCJ_3.class); // Add/Maintain Interface Groups
		// addTests(suite, AddTest_MGB_MCJ_4.class); // Add/Maintain Interface Groups
		addTests(suite, AddTest_MGD_MCJ_1.class); // Add/Maintain Interface Codes
		addTests(suite, AddTest_MGD_MCJ_2.class); // Add/Maintain Interface Codes
		addTests(suite, AddTest_MGD_MCJ_3.class); // Add/Maintain Interface Codes
		addTests(suite, AddTest_MGD_MCJ_4.class); // Add/Maintain Interface Codes
		addTests(suite, AddTest_MGD_MCJ_5.class); // Add/Maintain Interface Codes
		addTests(suite, AddTest_MGD_MCJ_6.class); // Add/Maintain Interface Codes
		addTests(suite, MCJ.class); // Customer Type Interface Information
		addTests(suite, CancelTest_MGD_MCJ_1.class); // Add/Maintain Interface Codes
		addTests(suite, CancelTest_MGD_MCJ_2.class); // Add/Maintain Interface Codes
		addTests(suite, CancelTest_MGD_MCJ_3.class); // Add/Maintain Interface Codes
		addTests(suite, CancelTest_MGD_MCJ_4.class); // Add/Maintain Interface Codes
		addTests(suite, CancelTest_MGD_MCJ_5.class); // Add/Maintain Interface Codes
		addTests(suite, CancelTest_MGD_MCJ_6.class); // Add/Maintain Interface Codes
		// addTests(suite, CancelTest_MGB_MCJ_1.class); // Add/Maintain Interface Groups
		// addTests(suite, CancelTest_MGB_MCJ_2.class); // Add/Maintain Interface Groups
		// addTests(suite, CancelTest_MGB_MCJ_3.class); // Add/Maintain Interface Groups
		// addTests(suite, CancelTest_MGB_MCJ_4.class); // Add/Maintain Interface Groups

		return suite;
	}

}