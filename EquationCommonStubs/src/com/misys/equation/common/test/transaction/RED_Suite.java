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

import java.util.Calendar;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.misys.equation.common.test.AbstractTestSuite;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.Toolbox;

public class RED_Suite extends AbstractTestSuite
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RED_Suite.java 4967 2009-09-28 15:54:02Z challip1 $";
	public static Test suite()
	{
		// test suite
		TestSuite suite = new TestSuite("Test for Returned Cheque Debtor Id RED_Suite");

		// Cheque Deposit
		String reference = createDepositReference();
		TestEnvironment.getTestEnvironment().putParameter("RED_Suite", reference);
		addTests(suite, AddTest_OKF_RED1.class); // Create Debtor
		addTests(suite, AddTest_OKF_RED2.class); // Create Debtor
		addTests(suite, AddTest_ACD_RED.class); // Create a cheque deposit header
		addTests(suite, MaintainTest_ACI_RED.class); // Open deposit for processing
		addTests(suite, AddTest_ACH_RED.class); // Add/maintain/cancel single cheque item (repeat for each
		// item)
		addTests(suite, MaintainTest_MDC_RED.class); // Process/save deposit, create postings
		addTests(suite, RED.class); // Authorise Referred Cheque
		addTests(suite, CancelTest_OKF_RED1.class); // Delete Debtor
		addTests(suite, CancelTest_OKF_RED2.class); // Delete Debtor
		return suite;
	}
	public static String createDepositReference()
	{
		// Create String of YYMMDDHHMMSS

		Calendar cal = Calendar.getInstance();
		int YYYY = cal.get(Calendar.YEAR);
		int MM = cal.get(Calendar.MONTH) + 1;
		int DD = cal.get(Calendar.DAY_OF_MONTH);
		int YYYYMMDD = (YYYY * 10000) + (MM * 100) + DD;
		String timeStamp = (new Integer(YYYYMMDD).toString().substring(2));
		timeStamp = timeStamp + new Integer(Toolbox.getTimeDBFormat(cal)).toString();
		return timeStamp;
	}
}
