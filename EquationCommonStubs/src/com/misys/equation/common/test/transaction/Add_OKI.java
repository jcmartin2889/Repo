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
 * Equation test cases for Maintain Paper Settlement A/c
 */
public class Add_OKI extends EquationTestCaseFullyAdd
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: Add_OKI.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "W62FRR";
	String optionId = "OKI";

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
		transaction.setFieldValue("GZPAP", "AA"); // Paper Type (2A)
		transaction.setFieldValue("GZPOP", "AX"); // Purpose of Presentation (2A)
		transaction.setFieldValue("GZCUS", "ACCS"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", "DTA"); // Customer location (3A)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic (3A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZMAB", "0543"); // Account branch (4A)
		transaction.setFieldValue("GZMAN", "123467"); // Basic part of account number (6A)
		transaction.setFieldValue("GZMAS", "001"); // Account suffix (3A)
		transaction.setFieldValue("GZDTC", "010"); // Transaction code (3A)
		transaction.setFieldValue("GZCTC", "510"); // Transaction code (3A)
	}

}