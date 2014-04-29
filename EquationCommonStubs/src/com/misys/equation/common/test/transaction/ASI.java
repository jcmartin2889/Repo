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
import com.misys.equation.common.test.EquationTestCaseAddMore;
import com.misys.equation.common.test.TestEnvironment;

/**
 * Equation test cases for Maintain function
 */
public class ASI extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ASI.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H15ARR";
	String optionId = "ASI";

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
		transaction.setFieldValue("GZAB", "9999"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "999999"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "999"); // Account suffix (3A)
		transaction.setFieldValue("GZTCD", "XXX"); // Transaction code (3A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0543"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "123107"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "001"); // Account suffix (3A)
		transaction.setFieldValue("GZTCD", "510"); // Transaction code (3A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZAMA", "543200"); // Ordinary amount in minor currency units (15P,0)
		transaction.setFieldValue("GZVFR", "1000106"); // Value from date (7S,0)
		transaction.setFieldValue("GZDRF", "JAVA TEST"); // Users own reference for deals, reconciliation etc (16A)
		transaction.setFieldValue("GZNPE", "1"); // Number of posting entries (5P,0)
		transaction.setFieldValue("GZPBR", "JUNT"); // Posting group id or user id, and group level (5A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)

	}
	/**
	 * Test: add mode: new record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	@Override
	public void test00500Add_NewRecord() throws Exception
	{
		// getTransaction() returns a new transaction.
		// Parent method copied here to be able to retrieve specific return field from API call!
		super.test00500Add_NewRecord();
		TestEnvironment.getTestEnvironment().putParameter(optionId, currentTransaction.getFieldValue("GZPSQ7"));
	}
}
