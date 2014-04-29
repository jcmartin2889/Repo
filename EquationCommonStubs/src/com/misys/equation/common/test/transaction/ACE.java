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
public class ACE extends EquationTestCaseAddCashier
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		programName = "L54ARR";
		optionId = "ACE";
		transactionType = "XEC";
	}
	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCCY", "GBP"); // Bought currency mnemonic (3A)
		transaction.setFieldValue("GZCCY2", "USD"); // Sold currency mnemonic (3A)

		transaction.setFieldValue("GZSPC", "244"); // SP code (3A)
		transaction.setFieldValue("GZAMT", "10000"); // Bought amount (15P,0)
		transaction.setFieldValue("GZAMT2", "15000"); // Sold amount (15P,0)
		transaction.setFieldValue("GZVFR", "0991231"); // Value from date (7S,0)
	}

}
