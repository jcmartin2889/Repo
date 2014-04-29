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
 * Equation test cases for Maintain Manual Cashflow
 */
public class MMW_Add extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MMW_Add.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "B40FRR";
	String optionId = "MMW";

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
		transaction.setFieldValue("GZBRNM", "XXXX"); // Deal branch mnemonic (4A)
		transaction.setFieldValue("GZDLP", "YYY"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "SPLAT1"); // Deal reference (13A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBRNM", "LOND"); // Deal branch mnemonic (4A)
		transaction.setFieldValue("GZDLP", "GDT"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "GDA103"); // Deal reference (13A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCDT", "1000305"); // Database date; cyymmdd (7S,0)
		transaction.setFieldValue("GZAMT", "10000"); // Amount larger than one transaction (15P,0)
		transaction.setFieldValue("GZCTP", "I"); // Movement type; I=Interest, P=Principal (1A)
		transaction.setFieldValue("GZUCT", "UA"); // User def. cashflow type (2A)
		transaction.setFieldValue("GZYNPG", "N"); // Postings generated? (1A)
		// transaction.setFieldValue("GZNS3", "001"); // Sequence number (3P,0) - sequence may be required
	}

}
