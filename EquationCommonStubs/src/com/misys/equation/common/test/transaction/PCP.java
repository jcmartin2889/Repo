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
import com.misys.equation.common.test.EquationTestCaseMaintain;

/**
 * Equation test cases for Retry/Process Clean Payments
 */
public class PCP extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: PCP.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	String programName = "H08MRR";
	String optionId = "PCP";

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
	 * Setup a non-existing key fields
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZREF", ""); // Reference (16A)
		transaction.setFieldValue("GZAB", ""); // Account branch (4A)
		transaction.setFieldValue("GZAN", ""); // Account number (6A)
		transaction.setFieldValue("GZAS", ""); // Account suffix (3A)
		transaction.setFieldValue("GZBBN", ""); // Branch number (4A)
		transaction.setFieldValue("GZABBN", ""); // All branch (1A)
		transaction.setFieldValue("GZACO", ""); // Account officer (3A)
		transaction.setFieldValue("GZPTYP", "AAA"); // Payment type (3A)
		transaction.setFieldValue("GZNRTY", ""); // No. of days to retry (4A)
		transaction.setFieldValue("GZCPFR", "Y"); // Retry failed payments (1A)
		transaction.setFieldValue("GZCPH", "Y"); // Retry held payments (1A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZREF", ""); // Reference (16A)
		transaction.setFieldValue("GZAB", ""); // Account branch (4A)
		transaction.setFieldValue("GZAN", ""); // Account number (6A)
		transaction.setFieldValue("GZAS", ""); // Account suffix (3A)
		transaction.setFieldValue("GZBBN", ""); // Branch number (4A)
		transaction.setFieldValue("GZABBN", "N"); // All branch (1A)
		transaction.setFieldValue("GZACO", ""); // Account officer (3A)
		transaction.setFieldValue("GZPTYP", ""); // Payment type (3A)
		transaction.setFieldValue("GZNRTY", ""); // No. of days to retry (4A)
		transaction.setFieldValue("GZCPFR", "Y"); // Retry failed payments (1A)
		transaction.setFieldValue("GZCPH", "Y"); // Retry held payments (1A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
	}

}
