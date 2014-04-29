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

public class AUD_Suite extends AbstractTestSuite
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AUD_Suite.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	public static Test suite()
	{
		// test suite
		TestSuite suite = new TestSuite("Test for Add/Maintain Returned Unpaid DD");

		// Cashier Transactions
		addTests(suite, RLA_for_CHA.class); // Add Retail Loan
		addTests(suite, ADO_for_CHA.class); // Add Direct Debit Origination
		addTests(suite, CHA_NotPaid.class); // Add Direct Debit Claim History
		addTests(suite, AUD.class); // 

		return suite;
	}
}
