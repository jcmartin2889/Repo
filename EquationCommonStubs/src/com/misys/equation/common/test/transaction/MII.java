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
 * Equation test cases for Maintain Internal Account Interest (Like MIC)
 */
public class MII extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MII.java 5723 2009-12-21 17:19:46Z challip1 $";
	String programName = "H73MRR";
	String optionId = "MII";

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
		transaction.setFieldValue("GZAB", "9898"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "898989"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "898"); // Account suffix (3A)
		transaction.setFieldValue("GZINT", "Y"); // Internal account flag (1A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0000"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "800008"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "001"); // Account suffix (3A)
		transaction.setFieldValue("GZINT", "Y"); // Internal account flag (1A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZIFQD", "V31"); // Debit interest frequency (3A)
		transaction.setFieldValue("GZNCDD", "1000205"); // Next interest cycle date - debit (7S,0)

		transaction.setFieldValue("GZIFQC", "V31"); // Credit interest frequency (3A)
		transaction.setFieldValue("GZNCDC", "1000205"); // Next interest cycle date - credit (7S,0)

	}

}