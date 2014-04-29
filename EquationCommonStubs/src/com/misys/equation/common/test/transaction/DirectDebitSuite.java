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

public class DirectDebitSuite extends AbstractTestSuite
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DirectDebitSuite.java 4967 2009-09-28 15:54:02Z challip1 $";
	public static Test suite()
	{
		// test suite
		TestSuite suite = new TestSuite("Test for Direct Debits PDD/INR/RDD");

		/*
		 * First create a Direct Debit Mandate This has status 'held' so that the subsequent PDDs that use it are not automatically
		 * authorised.
		 */
		addTests(suite, AMD_add.class); // Add Direct Debit Mandate

		/*
		 * Pay Direct Debit in Add mode using the DD mandate from above
		 */
		addTests(suite, PDD_add.class); // Pay Direct Debit

		/*
		 * Pay Direct Debit in Maintain mode Uses the Internal Reference from Add PDD above. From the green screen this is done by
		 * drilling down from option INR, but in external input it is down via PDD.
		 */
		addTests(suite, PDD_maintain.class); // Pay Direct Debit

		/*
		 * Authorise the Direct Debit using INR Uses the Internal Reference from Add PDD above.
		 */
		addTests(suite, INR.class); // Inward Payment Referral

		/*
		 * 2nd Pay Direct Debit in Add mode
		 */
		addTests(suite, PDD_add.class); // Pay Direct Debit

		/*
		 * Return Direct Debit and Direct Debit Returned Advices Uses the Internal Reference from 2nd Add PDD above.
		 */
		addTests(suite, RDD.class); // Return Direct Debit
		addTests(suite, DDA_Account.class); // Direct Debit Returned Advices

		/*
		 * Maintain and Cancel the Direct Debit Mandate
		 */
		addTests(suite, AMD_maintain_cancel.class); // Maintain/Cancel Direct Debit Mandate

		return suite;
	}
}
