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

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseAddCashier;

/**
 * Equation test cases for Maintain function
 */
public class AAX extends EquationTestCaseAddCashier
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AAX.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		programName = "L59ARR";
		optionId = "AAX";
		transactionType = "XDP";
	}
	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0543"); // Customer a/c branch number (4A)
		transaction.setFieldValue("GZAN", "012008"); // Customer a/c basic number (6A)
		transaction.setFieldValue("GZAS", "001"); // Customer a/c suffix (3A)

		transaction.setFieldValue("GZSPC", "372"); // SP code (3A)
		transaction.setFieldValue("GZCCY", "USD"); // Deposit currency mnemonic (3A)
		transaction.setFieldValue("GZCCY2", "GBP"); // Exchange currency mnemonic (3A)
		transaction.setFieldValue("GZAMT", "15000"); // Deposit amount (15P,0)
		transaction.setFieldValue("GZAMT2", "10000"); // Exchange amount (15P,0)
	}

}
