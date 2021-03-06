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

/**
 * Equation test cases for Maintain function
 */
public class S15 extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: S15.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "S15ARR";
	String optionId = "S15";

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
		transaction.setFieldValue("GZSRID", "1"); // Transaction ID (10A)
		transaction.setFieldValue("GZSMOD", "01"); // Print Mode (2A)
		transaction.setFieldValue("GZSAB", "0132"); // Account branch (4A)
		transaction.setFieldValue("GZSAN", "100000"); // Basic part of account number (6A)
		transaction.setFieldValue("GZSAS", "052"); // Account suffix (3A) }
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZSRID", "1"); // Transaction ID (10A)
		transaction.setFieldValue("GZSMOD", "01"); // Print Mode (2A)
		transaction.setFieldValue("GZSAB", "0132"); // Account branch (4A)
		transaction.setFieldValue("GZSAN", "100000"); // Basic part of account number (6A)
		transaction.setFieldValue("GZSAS", "053"); // Account suffix (3A) }
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
	}

}
