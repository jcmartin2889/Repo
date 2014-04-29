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
import com.misys.equation.common.test.EquationTestCaseFully;

/**
 * Equation test cases for Add/Maintain Limit Reservations
 */
public class ALR extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ALR.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "C18FRR";
	String optionId = "ALR";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		retrieveBeforeCancel = false;
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
		transaction.setFieldValue("GZBRNM", "LOND"); // Deal branch mnemonic (4A)
		transaction.setFieldValue("GZDLP", "CDD"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "EQ4TEST2"); // Deal reference (13A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCUS", "NTHSEA"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", "OIL"); // Customer location (3A)
		transaction.setFieldValue("GZAMT", "1000000"); // Reserved amount (15P,0)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZVFR", "0991231"); // Value from date (7S,0)
		transaction.setFieldValue("GZMDT", "1001130"); // Maturity date (7S,0)
		transaction.setFieldValue("GZXDT", "1001231"); // Expiry date (7S,0)
		transaction.setFieldValue("GZYIR", "N"); // Include in risk? (1A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCUS", "NTHSEA"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", "OIL"); // Customer location (3A)
		transaction.setFieldValue("GZAMT", "1000000"); // Reserved amount (15P,0)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZVFR", "0991231"); // Value from date (7S,0)
		transaction.setFieldValue("GZMDT", "1001130"); // Maturity date (7S,0)
		transaction.setFieldValue("GZXDT", "1001231"); // Expiry date (7S,0)
		transaction.setFieldValue("GZYIR", "Y"); // Include in risk? (1A)
	}

}
