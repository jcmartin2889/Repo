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
 * Equation test cases for Maintain Withdrawal Statistics
 */
public class MWS extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MWS.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "B14MRR";
	String optionId = "MWS";

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
		transaction.setFieldValue("GZAB", "0432"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "700004"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "000"); // Account suffix (3A)
		transaction.setFieldValue("GZDTE", "991231"); // Input date (7S,0)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0543"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "444444"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "000"); // Account suffix (3A)
		transaction.setFieldValue("GZDTE", "1000105"); // Input date (7S,0)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZMAN", "1000103"); // Manual override
	}

}
