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
 * Equation test cases for Maintain function
 */
public class ABO extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ABO.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H13ARR";
	String optionId = "ABO";

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
		transaction.setFieldValue("GZAB", "0132"); // Balance order branch (4A)
		transaction.setFieldValue("GZAN", "200000"); // Balance order account number (6A)
		transaction.setFieldValue("GZAS", "001"); // Balance order suffix (3A)
		transaction.setFieldValue("GZBOT", "L"); // Balance order type; D,L,R,S (1A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0132"); // Balance order branch (4A)
		transaction.setFieldValue("GZAN", "012008"); // Balance order account number (6A)
		transaction.setFieldValue("GZAS", "001"); // Balance order suffix (3A)
		transaction.setFieldValue("GZBOT", "D"); // Balance order type; D,L,R,S (1A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZFRQ", "V15"); // Frequency code (3A)
		transaction.setFieldValue("GZMIN", "100000"); // Minimum balance/target available balance (15P,0)
		transaction.setFieldValue("GZAB1", "0132"); // Funding a/c 1 branch (4A)
		transaction.setFieldValue("GZAN1", "100000"); // Funding a/c 1 account number (6A)
		transaction.setFieldValue("GZAS1", "001"); // Funding a/c 1 suffix (3A)
		transaction.setFieldValue("GZEXF1", "1"); // Funding a/c 1 spot/user/retail rate (1A)

	}

}
