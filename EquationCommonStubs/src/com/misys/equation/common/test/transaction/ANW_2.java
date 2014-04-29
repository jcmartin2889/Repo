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
import com.misys.equation.common.test.EquationTestCaseAddOnce;

/**
 * Equation test cases for Add Notice Withdrawal
 * 
 * this function requires that MSD is run first(with the same reference) if run as an API
 */
public class ANW_2 extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ANW_2.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "B11ARR";
	String optionId = "ANW";

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
		transaction.setFieldValue("GZREF", "REF005"); // Notice withdrawal reference
		transaction.setFieldValue("GZAB", "0543"); // Account branch
		transaction.setFieldValue("GZAN", "234567"); // Basic part of account number
		transaction.setFieldValue("GZAS", "201"); // Account suffix
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDCCY", "GBP"); // Debit currency
		transaction.setFieldValue("GZWTP", "1"); // Withdrawal type
		transaction.setFieldValue("GZNTC", "0991231"); // Date notice given
		transaction.setFieldValue("GZWDT", "1000104"); // Withdrawal date
		transaction.setFieldValue("GZAMR", "10000"); // Withdrawal amount requested
		transaction.setFieldValue("GZRVW", "Y"); // Review required
	}

}
