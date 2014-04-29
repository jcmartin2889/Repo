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
 * Equation test cases for Add Guaranteed Fate Cheque
 */
public class ACF extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACF.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "F87ARR";
	String optionId = "ACF";

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
		transaction.setFieldValue("GZAB", "3210"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "100000"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "001"); // Account suffix (3A)
		transaction.setFieldValue("GZSER", "10001"); // Serial number (16P,0)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "3210"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "100000"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "001"); // Account suffix (3A)
		transaction.setFieldValue("GZSER", "100301"); // Serial number (16P,0)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAMT", "15000"); // Amount guaranteed/certified (15P,0)
		transaction.setFieldValue("GZACO", "FX"); // Account officer (3A)

	}

}
