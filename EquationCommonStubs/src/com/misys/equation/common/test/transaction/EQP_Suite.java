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
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.Toolbox;

public class EQP_Suite extends AbstractTestSuite
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQP_Suite.java 4967 2009-09-28 15:54:02Z challip1 $";
	public static Test suite()
	{
		// test suite
		TestSuite suite = new TestSuite("Test for Edit Queued Transaction Priority");

		// Transactions
		String reference = Toolbox.getTimeBasedReference13();
		TestEnvironment.getTestEnvironment().putParameter("ITA", reference);
		addTests(suite, AddTest_DQP_00020.class); // Define Queue Priority
		addTests(suite, AddTest_AQP_ITA.class); // Assign Queued Transaction Priorities
		addTests(suite, AddTest_ITA_for_EQP.class); // Add Inter Account Transfer (queued)
		addTests(suite, EQP.class); // Edit Queue Priority
		addTests(suite, CancelTest_TQM.class); // Transaction Queue Management (delete item from queue)
		addTests(suite, CancelTest_AQP_ITA.class); // Assign Queued Transaction Priorities
		addTests(suite, CancelTest_DQP_00020.class); // Define Queue Priority

		return suite;
	}
}
