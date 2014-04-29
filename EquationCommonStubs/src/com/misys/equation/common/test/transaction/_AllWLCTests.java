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

public class _AllWLCTests extends AbstractTestSuite
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: _AllWLCTests.java 8426 2010-07-29 17:32:49Z CHALLIP1 $";
	public static Test suite()
	{
		// test suite
		TestSuite suite = new TestSuite("Test for All Watch List Checking (WLC)functions");

		// WLC Transactions

		// NB. These tests are dependent on someone correctly
		// installing and configuring K473C and successfully
		// starting the OFAC-Agent Servers via KMENUH.
		// For the EWC, EWD and EWP tests to work correctly, the
		// following major processing characteristics must be
		// configured thus:
		// FWCAW Customer address WLC N
		// FWDLW Deal WLC N
		// FWCPW Clean payment WLC N
		// FMBPS Block payment maintenance status 50
		// Once WLC cases have been raised, these tests cannot
		// be repeated.

		// Due Diligence (EWU) tests. There are 2 ways of populating
		// the GZW301 journal file - a 2000 length character
		// string (EWU_APItext.class) and 10 x 50 length strings
		// (EWU_Line1.class):
		// REMOVED - NEED OFAC Agents!
		// addTests(suite, EWU_APItext.class);// Due Diligence
		// addTests(suite, EWU_Line1.class);// Due Diligence

		// Check Customer Details (EWC) function:
		// The AddMaintain java class is unsuitable due to @FCT
		// anomalies. Instead, do separate tests to add/maintain.
		// First add a new dodgy customer (no WLC case should be
		// added at this point with FWCAW MPC = N)...
		addTests(suite, AddForWLC_ANC.class);// Add new customer
		// ...then Check that customer, which should raise a case...
		addTests(suite, EWC_AddMore.class);// Check Customer Details
		// ...now use MCD to change the customer name to a good
		// name, i.e. from Robert Mugabe to John Doe...
		addTests(suite, MCDForWLC_MCD.class); // Change name
		// ...now Check the customer again, which should change
		// the case status from 40 to 03=Rechecked.No match...
		addTests(suite, EWC_Maintain.class);// Check Customer Details

		// Check Deal Settlements (EWD) function:
		// The AddMaintain java class is unsuitable due to @FCT
		// anomalies. Instead, do separate add/maintain tests:
		// First add a new deal with dodgy settlement details
		// (no WLC case should be added at this point with
		// FWDLW MPC = N)...
		addTests(suite, AddForWLC_AMS.class);// Add new MM deal
		// ...then check that deal and add the WLC case
		addTests(suite, EWD_AddMore.class);// Check Deal Settlement
		// ...now use DSI to change the dodgy beneficiary to a
		// good one, i.e. from OSAMA BIN LADEN to JOHN DOE
		// (FMBPS MPC must be 50 for this to work)...
		addTests(suite, DSI_ForWLC.class); // change beneficiary
		// ...now Check the deal again, which should change
		// the case status from 40 to 03=Rechecked.No match...
		addTests(suite, EWD_Maintain.class);// Check Deal Settlement

		// Check Clean Payments (EWP) function:
		// Can not be tested here because AOP is not available in
		// _AllTests.
		// Separate tests are available in EWP_AddMore.class
		// and EWD_Maintain.class.
		// Create a clean payment via WMENU1 with 'dodgy' details,
		// such as a beneficiary of 'Osama Bin Laden'. Note
		// the CP reference and update EWP_AddMore.class and
		// EWP_Maintain.class with it prior to testing with them.
		addTests(suite, AOP_WLC.class);// Check Deal Settlement
		addTests(suite, EWP_AddMore.class);// Check Deal Settlement
		addTests(suite, EWP_Maintain.class);// Check Deal Settlement

		// ***End of Watch List Checking (K473C) tests***

		return suite;
	}
}
