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
import com.misys.equation.common.test.EquationTestCaseAddCancel;

/**
 * Equation test cases for Maintain function
 */
public class CPA extends EquationTestCaseAddCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CPA.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "X37FRR";
	String optionId = "CPA";

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
	 * Setup a non-existing key fields only
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCGID", "9998"); // Charge id (4A)
		transaction.setFieldValue("GZSQN", "9998"); // Selection sequence number (4A)
		transaction.setFieldValue("GZCUS", "ACCS  "); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", "DTA"); // Customer location (3A)
		transaction.setFieldValue("GZAB", "9132"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "234567"); // Account number (6A)
		transaction.setFieldValue("GZAS", "002"); // Account suffix (3A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCAB", "3000"); // Charge account branch (4A)
		transaction.setFieldValue("GZCAN", "234567"); // Charge account number (6A)
		transaction.setFieldValue("GZCAS", "010"); // Charge account suffix (3A)
		transaction.setFieldValue("GZACT", "Y"); // Active/inactive flag (1A)
	}

}
