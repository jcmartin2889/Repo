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
public class ALW extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ALW.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "R59ARR";
	String optionId = "ALW";

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
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZLNP", "XXX"); // Deal type (3A)
		transaction.setFieldValue("GZLNR", "DOESNOTEXIST"); // Deal reference (13A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZLNP", "CAL"); // Deal type (3A)
		transaction.setFieldValue("GZLNR", "CN139492"); // Deal reference (13A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZWAI","10");
		transaction.setFieldValue("GZADP", "1000.00");
		transaction.setFieldValue("GZWAP", "1000"); // Provision amount (15P,0)
		transaction.setFieldValue("GZWDT", "1000105"); // Date raised (7S,0)
		transaction.setFieldValue("GZEPA", "0"); // Early payment method (1A)
	}

}
