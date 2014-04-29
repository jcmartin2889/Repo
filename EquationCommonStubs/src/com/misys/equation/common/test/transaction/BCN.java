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
import com.misys.equation.common.test.EquationTestCaseFully;

/**
 * Equation test cases for Maintain function
 */
public class BCN extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BCN.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "C12FRR";
	String optionId = "BCN";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		retrieveBeforeCancel = true;
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
	 * Setup an existing key fields only
	 */
	// public void setupExistKeyFields(EquationStandardTransaction transaction)
	// {
	// transaction.setField("GZAB", "0132"); // Account branch (4A)
	// transaction.setField("GZAN", "200000"); // Basic part of account number (6A)
	// transaction.setField("GZAS", "054"); // Account suffix (3A)
	// transaction.setField("GZCNAB", "GB"); // Borrowing country (2A)
	// }
	/**
	 * Setup a non-existing key fields only
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0132"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "200000"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "054"); // Account suffix (3A)
		transaction.setFieldValue("GZCNAB", "GB"); // Borrowing country (2A)
	}
	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZXDT", "9999999"); // Expiry date (7S,0)
		transaction.setFieldValue("GZCNAL", "AO"); // Lending country (2A)
		transaction.setFieldValue("GZAID", "ALPB"); // Approved by (4A)
		transaction.setFieldValue("GZDTE", "991231"); // Date set up (7S,0)
		transaction.setFieldValue("GZNR1", "BORROWED"); // Reason for borrowing - line 1 (35A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZXDT", "9999999"); // Expiry date (7S,0)
		transaction.setFieldValue("GZCNAL", "AO"); // Lending country (2A)
		transaction.setFieldValue("GZAID", "ALPB"); // Approved by (4A)
		transaction.setFieldValue("GZDTE", "991231"); // Date set up (7S,0)
		transaction.setFieldValue("GZNR1", "BORROWED - Maintained"); // Reason for borrowing - line 1 (35A)
	}
}