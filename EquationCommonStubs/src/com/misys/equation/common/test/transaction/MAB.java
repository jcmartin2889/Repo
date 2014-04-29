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
 * Equation test cases for Maintain Customer A/C Basic Details
 */
public class MAB extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MAB.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H39MRR";
	String optionId = "MAB";

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
		// This is how an account number looks GZAB GZAN GZAS
		transaction.setFieldValue("GZAB", "9898"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "898989"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "989"); // Account suffix (3A)
		transaction.setFieldValue("GZINT", "N"); // Internal account? (1A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		// This is how an account number looks GZAB GZAN GZAS
		transaction.setFieldValue("GZAB", "0543"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "123107"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "002"); // Account suffix (3A)
		transaction.setFieldValue("GZINT", "N"); // Internal account? (1A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZSHN", "Brands London M"); // Account short name (15A)
		transaction.setFieldValue("GZACO", "AC"); // Responsibility code (3A)
		transaction.setFieldValue("GZCNAR", "GB"); // Risk country (2A)
		transaction.setFieldValue("GZCNAP", "GB"); // Parent country (2A)
		transaction.setFieldValue("GZOAD", "0991002"); // Date account opened (7S,0)
		transaction.setFieldValue("GZCLT", "N"); // Collateral required (1A)
		transaction.setFieldValue("GZCNAI", ""); // Internal risk country

	}

}
