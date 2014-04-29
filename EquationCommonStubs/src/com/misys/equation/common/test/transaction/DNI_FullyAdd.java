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
import com.misys.equation.common.test.EquationTestCaseFullyAdd;

/**
 * Equation test cases for Maintain function
 */
public class DNI_FullyAdd extends EquationTestCaseFullyAdd
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DNI_FullyAdd.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G04FRR";
	String optionId = "DNI";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
	}

	// ------------------------------------------------------------------------ Helper methods
	/**
	 * Return a transaction
	 * 
	 * @return a transaction
	 * 
	 * @throws Exception
	 */
	@Override
	public EquationStandardTransaction getTransaction() throws Exception
	{
		EquationStandardTransaction transaction = getEquationStandardTransaction(programName + optionId);
		transaction.setWorkStationId(WORKSTATIONID);
		return transaction;
	}

	// ------------------------------------------------------------------------ Field setups

	/**
	 * Setup a non-existing key fields only
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZNST", "DNINS1"); // Nostro mnemonic (6A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZNDS", "G04FRR Add/Maintain Test"); // Nostro description (35A)
		transaction.setFieldValue("GZAB", "9998"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "012833"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "033"); // Account suffix (3A)
		transaction.setFieldValue("GZTG53", "Y"); // A/c at nostro inst on TAG 53 (1A)
		transaction.setFieldValue("GZXM", "BB"); // Pay transfer method (2A)
		transaction.setFieldValue("GZXMR", "CC"); // Receipt transfer method (2A)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZVST", "1"); // Nostro/vostro (1A)
		transaction.setFieldValue("GZEDI", "N"); // Enable for draft issuance? (1A)
		transaction.setFieldValue("GZCLS", "N"); // CLS Nostro? (1A)
		transaction.setFieldValue("GZCAR", "N"); // Bank cheque advice required? (1A)
	}

}
