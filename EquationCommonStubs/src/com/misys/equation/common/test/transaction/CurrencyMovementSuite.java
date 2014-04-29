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

public class CurrencyMovementSuite extends AbstractTestSuite
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CurrencyMovementSuite.java 8426 2010-07-29 17:32:49Z CHALLIP1 $";
	public static Test suite()
	{
		// test suite
		TestSuite suite = new TestSuite("Test for Currency movements");
		addTests(suite, J10.class); // Add/Maintain Ccy Denominations

		// Currency Movement
		// 
		// Internal accounts should be opened in preparation for the Jnn functions
		suite.addTest(OIA_Suite.suite()); // Add Internal Accounts for Jnn
		// Once opened, make sure that the following is less than or equal to zero:
		// SCBAL + SCSUMD + SCSUMC + SCSUMA - SCSUM1 - SCSUM2 + ZDAMT
		// where ZDAMT is the amount entered (in Jnn later on) and the rest coming from SCPF.
		// In order to test the Jnn functions properly,
		// I had to use upddta to set SCSUMD to -100 (or -10000 in a certain case)
		//

		// THESE WILL ALL WORK IF THE CONTINGENT ACCOUNTS ARE CREDITED AND DEBITED
		// CORRECTLY - this is not complete so Tests removed!
		
		// Currency Movement
		addTests(suite, AddTest_J10.class); // Add/Maintain Ccy Denominations
		addTests(suite, J11.class); // Receive Currency into Main Store
		addTests(suite, J12.class); // Transfer Ccy from Store to Stock
		addTests(suite, J13.class); // Tfr Ccy from Stock to Mut Stock
		addTests(suite, AddTest_J11_1.class); // Receive Currency into Main Store
		addTests(suite, AddTest_J12_1.class); // Transfer Ccy from Store to Stock
		addTests(suite, J14.class); // Tfr Notes from Stk to Part Mut Stk
		addTests(suite, J15.class); // Tfr Notes from Mut Stk to Punched
		addTests(suite, AddTest_J11_1.class); // Receive Currency into Main Store
		addTests(suite, AddTest_J12_1.class); // Transfer Ccy from Store to Stock
		addTests(suite, J16.class); // Tfr Notes from Part Mut to Punched
		// addTests(suite, J17.class); // Replace Mutilated Coins
		addTests(suite, J18.class); // Process Comp Coins from Store
		addTests(suite, J19.class); // Process Comp Coins from Stock
		// addTests(suite, J20.class); // Process Comp Coins from Mut Coins
		addTests(suite, J21.class); // Issue Currency to Banks
		addTests(suite, J22.class); // Receive Currency from Banks
		// addTests(suite, J23.class); // Receive Part Mut Notes from Banks
		// addTests(suite, J24.class); // Receive Mutilated Ccy from Banks
		// addTests(suite, J26.class); // Exchange Currency
		addTests(suite, J27.class); // Destroy Notes from Punched Stock
		// addTests(suite, J28.class); // Destroy Notes from Part Mut Stock
		addTests(suite, J29.class); // Destroy Coins from Compensatable
		addTests(suite, J30.class); // Destroy Currency from Mut Stock
		// addTests(suite, J31.class); // Destroy Currency from Store
		addTests(suite, J32.class); // Destroy Currency from Stock
		// addTests(suite, J33.class); // Issue Currency to Third Party
		// addTests(suite, J34.class); // Issue Part Mut Notes to Third Party
		// addTests(suite, J35.class); // Issue Mut Notes to Third Party
		// addTests(suite, J36.class); // Receive Ccy from Third Party
		// addTests(suite, J37.class); // Rcv Part Mut Notes from Third Party
		// addTests(suite, J38.class); // Rcv Mut Notes from Third Party
		addTests(suite, J39.class); // Transfer Currency to Stock
		addTests(suite, J40.class); // Transfer Currency to Store
		addTests(suite, J41.class); // Transfer Currency to Mut Stock
		addTests(suite, J42.class); // Transfer Notes to Part Mut Stock
		addTests(suite, J43.class); // Tfr Coins to Compensatable Stock
		// addTests(suite, J44.class); // Transfer Currency from Stock
		// addTests(suite, J45.class); // Transfer Currency from Store
		// addTests(suite, J46.class); // Transfer Currency from Mut Stock
		// addTests(suite, J47.class); // Transfer Notes from Part Mut Stock
		// addTests(suite, J48.class); // Tfr Coins from Compensatable Stock

		// addTests(suite, J25.class); // Cheque Encashment by Staff

		return suite;
	}
}
