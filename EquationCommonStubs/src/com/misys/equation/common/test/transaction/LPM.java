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
import com.misys.equation.common.test.EquationTestCaseMaintainCancel;

/**
 * Equation test cases for Maintain Loan Pay-off
 */
public class LPM extends EquationTestCaseMaintainCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LPM.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "R55MRR";
	String optionId = "LPM";

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
	 * Setup an existing key fields only
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZLNP", "CAP"); // Loan type (3A)
		transaction.setFieldValue("GZLNR", "XXXXXXX"); // Loan reference (13A)
		transaction.setFieldValue("GZBRNM", "CUTE"); // Branch (4A)
		transaction.setFieldValue("GZCUS", "B00002"); // Customer mnemonic (6A)
	}

	/**
	 * Setup a non-existing key fields only
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZLNP", "CA1"); // Loan type (3A)
		transaction.setFieldValue("GZLNR", "CA1-004"); // Loan reference (13A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch (4A)
		transaction.setFieldValue("GZCUS", "550021"); // Customer mnemonic (6A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAS", "002"); // Account suffix (3A)
	}

}
