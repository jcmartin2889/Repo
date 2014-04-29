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
public class PMB extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: PMB.java 5103 2009-10-08 14:57:09Z macdonp1 $";
	String programName = "G53ARR";
	String optionId = "PMB";

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
		transaction.setFieldValue("GZDLP", "BON"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "PAUL31"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDLP", "BO1"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "JC4-RAF-07"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCPI", "N"); // Capitalised bonus? (1A)
		transaction.setFieldValue("GZBBNB", "1000"); // Bonus account branch (4A)
		transaction.setFieldValue("GZCPNB", "500003"); // Bonus account basic number (6A)
		transaction.setFieldValue("GZCPSB", "006"); // Bonus account suffix (3A)
		transaction.setFieldValue("GZCCY", "GBP"); // Bonus currency (3A)
		transaction.setFieldValue("GZAMT", "2000"); // Adjustment amount (15P,0)
		transaction.setFieldValue("GZPOS", "DR"); // Adjustment type flag CR/DR (2A)
		transaction.setFieldValue("GZVAL", "0991231"); // Value from date (7S,0)
	}

}
