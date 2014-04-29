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

import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.Toolbox;

public class ARS_Suite
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ARS_Suite.java 4721 2009-09-15 11:14:17Z weddelc1 $";
	public static Test suite()
	{
		// test suite
		TestSuite suite = new TestSuite("Test for com.misys.equation.common.test.standard.transaction ARS_Suite");

		// Cheque Deposit
		String reference = createDepositReference();
		TestEnvironment.getTestEnvironment().putParameter("ARS_Suite", reference);
		suite.addTestSuite(AddTest_ACD.class); // Create a cheque deposit header
		suite.addTestSuite(MaintainTest_ACI.class); // Open deposit for processing
		suite.addTestSuite(AddTest_ACH.class); // Add/maintain/cancel single cheque item (repeat for each
		// item)
		suite.addTestSuite(MaintainTest_MDC.class); // Process/save deposit, create postings
		suite.addTestSuite(ARS_Authorise_Return_Referred_Cheque.class); // Authorise Referred Cheque
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
