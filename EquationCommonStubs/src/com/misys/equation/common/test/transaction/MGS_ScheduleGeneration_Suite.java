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

public class MGS_ScheduleGeneration_Suite extends AbstractTestSuite
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MGS_ScheduleGeneration_Suite.java 4967 2009-09-28 15:54:02Z challip1 $";
	public static Test suite()
	{
		// test suite
		TestSuite suite = new TestSuite("Test for MGS Generated Schedules");

		// Cashier Transactions
		addTests(suite, MGS_1_FullyAdd.class); // Add Generated Schedule Instruction
		addTests(suite, MGS_2_I19M.class); // ACalculate Generated Schedule
		addTests(suite, MGS_3_FullyCancel.class); // Cancel Generated Schedule Instruction
		return suite;
	}
}
