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
 * Equation test cases for Issue Currency to Banks
 */
public class J21 extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: J21.java 5723 2009-12-21 17:19:46Z challip1 $";
	String programName = "I14ARR";
	String optionId = "J21";

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
		transaction.setFieldValue("GZCCY", "ADP"); // Currency mnemonic
		transaction.setFieldValue("GZAB", "0000"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "000001"); // A/c basic number (6A)
		transaction.setFieldValue("GZAS", "023"); // Account suffix
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic
		transaction.setFieldValue("GZAB", "0000"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "500035"); // A/c basic number (6A)
		transaction.setFieldValue("GZAS", "101"); // Account suffix
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZIFNC", "ICTB"); // Input function
		transaction.setFieldValue("GZND01", "1"); // 1st note den. value
		transaction.setFieldValue("GZNQ01", "1"); // 1st note den. qty
		transaction.setFieldValue("GZNS01", "002"); // 1st note a/c suffix
	}

}
