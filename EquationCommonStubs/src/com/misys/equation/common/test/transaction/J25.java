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
 * Equation test cases for Cheque Encashment by Staff
 */
public class J25 extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: J25.java 8426 2010-07-29 17:32:49Z CHALLIP1 $";
	String programName = "I14ARR";
	String optionId = "J25";

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
		transaction.setFieldValue("GZCCY", "GOP"); // Currency mnemonic
		transaction.setFieldValue("GZAB", "0543"); // Account branch
		transaction.setFieldValue("GZAN", "000001"); // A/c basic number
		transaction.setFieldValue("GZAS", "601"); // Account suffix
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic
		transaction.setFieldValue("GZAB", "0543"); // Account branch
		transaction.setFieldValue("GZAN", "234567"); // A/c basic number
		transaction.setFieldValue("GZAS", "100"); // Account suffix
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZIFNC", "CEBS"); // Input function
		transaction.setFieldValue("GZND01", "1"); // 1st note den. value
		transaction.setFieldValue("GZNQ01", "1"); // 1st note den. qty
		transaction.setFieldValue("GZNS01", "002"); // 1st note a/c suffix
	}

}
