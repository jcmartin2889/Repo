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

public class ADR_CSD_Suite
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ADR_CSD_Suite.java 4721 2009-09-15 11:14:17Z weddelc1 $";
	public static Test suite()
	{
		// test suite
		TestSuite suite = new TestSuite("Test for com.misys.equation.common.test.standard.transaction ADR/CSD");

		// Cashier Transactions
		suite.addTestSuite(ADR.class); // Add CS draft
		suite.addTestSuite(CSD.class); // Cancel CS draft

		return suite;
	}
}
