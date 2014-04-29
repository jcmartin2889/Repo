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
 * Equation test cases for Maintain Customer A/C Other Details
 */
public class MAO extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MAO.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H37MRR";
	String optionId = "MAO";

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
		transaction.setFieldValue("GZAB", "8989"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "989898"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "898"); // Account suffix (3A)
		// transaction.setFieldValue("GZCUS", "GGGGGG"); // Customer mnemonic (6A)
		// transaction.setFieldValue("GZCLC", "LLL"); // Customer location (3A)
		transaction.setFieldValue("GZINT", "N"); // Internal account (1A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0543"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "123107"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "001"); // Account suffix (3A)
		// transaction.setFieldValue("GZCUS", "BRANDS"); // Customer mnemonic (6A)
		// transaction.setFieldValue("GZCLC", "LON"); // Customer location (3A)
		transaction.setFieldValue("GZINT", "N"); // Internal account (1A)

	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZC1R", "BB"); // Customers C1 rating (2A)
		transaction.setFieldValue("GZC2R", "B1"); // Customers C2 rating (2A)
		transaction.setFieldValue("GZC3R", "L1"); // Customers C3 rating (2A)
		transaction.setFieldValue("GZC4R", "AJ"); // Customers C4 rating (2A)
		transaction.setFieldValue("GZC5R", "NK"); // Customers C5 rating (2A)
		transaction.setFieldValue("GZP1R", "024"); // Customers P1 rating (3A)
		transaction.setFieldValue("GZP2R", "001"); // Customers P2 rating (3A)
		transaction.setFieldValue("GZP3R", "001"); // Customers P3 rating (3A)
		transaction.setFieldValue("GZP4R", "040"); // Customers P4 rating (3A)
		transaction.setFieldValue("GZP5R", "760"); // Customers P5 rating (3A)
	}

}
