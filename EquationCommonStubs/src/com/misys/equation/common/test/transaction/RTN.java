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
import com.misys.equation.common.test.EquationTestCaseAddOnce;

/**
 * Equation test cases for Add Repayment Transaction
 */
public class RTN extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RTN.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H34ARR";
	String optionId = "RTN";

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
		transaction.setFieldValue("GZDLP", "COA"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "COA18"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "CUTE"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZDTE", "0990831"); // Repayment due date (7S,0)
		transaction.setFieldValue("GZPIF", "B"); // Repayment type; I=Interest, P=Principal, B=Both (1A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCMB", "N"); // Combined flag (1A)

		transaction.setFieldValue("GZVF1", "0990831"); // Principal repayment date (7S,0)
		transaction.setFieldValue("GZAM1", "96953"); // Principal repayment amount (15P,0)
		transaction.setFieldValue("GZAB1", "3000"); // Principal settlement account branch (4A)
		transaction.setFieldValue("GZAN1", "Z00001"); // Principal settlement account basic number (6A)
		transaction.setFieldValue("GZAS1", "001"); // Principal settlement account suffix (3A)
		transaction.setFieldValue("GZCCY1", "GBP"); // Principal settlement currency (3A)

		transaction.setFieldValue("GZVF2", "0990831"); // Interest repayment date (7S,0)
		transaction.setFieldValue("GZAM2", "11000"); // Interest repayment amount (15P,0)
		transaction.setFieldValue("GZAB2", "3000"); // Interest settlement account branch (4A)
		transaction.setFieldValue("GZAN2", "Z00001"); // Interest settlement account basic number (6A)
		transaction.setFieldValue("GZAS2", "001"); // Interest settlement account suffix (3A)
		transaction.setFieldValue("GZCCY2", "GBP"); // Interest settlement currency (3A)

	}

}