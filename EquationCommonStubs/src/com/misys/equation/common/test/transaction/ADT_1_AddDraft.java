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
import com.misys.equation.common.test.TestEnvironment;

/**
 * Equation test cases for Maintain function
 */
public class ADT_1_AddDraft extends EquationTestCaseAddCashier
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ADT_1_AddDraft.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		programName = "L62ARR";
		optionId = "ADR";
		transactionType = "DRI";
	}
	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDNO", TestEnvironment.getTestEnvironment().getParameter(optionId)); // Draft number (16A)

		transaction.setFieldValue("GZSPC", "387"); // SP code (3A)
		transaction.setFieldValue("GZCCY", "GBP"); // Draft currency mnemonic (3A)
		transaction.setFieldValue("GZCCY2", "USD"); // Exchange currency mnemonic (3A)
		transaction.setFieldValue("GZAMT", "10000"); // Draft amount (15P,0)
		transaction.setFieldValue("GZAMT2", "10000"); // Exchange amount (15P,0)
		transaction.setFieldValue("GZBEN", "Beneficiary"); // Beneficiary (35A)
		transaction.setFieldValue("GZAB", "0543"); // Nostro a/c branch number (4A)
		transaction.setFieldValue("GZAN", "012008"); // Nostro a/c basic number (6A)
		transaction.setFieldValue("GZAS", "001"); // Nostro a/c suffix (3A)
	}

}
