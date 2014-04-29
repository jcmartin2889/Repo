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
 * Equation test cases for Add Retail Loan
 */
public class RLA extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RLA.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "N94ARR";
	String optionId = "RLA";

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
		transaction.setFieldValue("GZLNP", "CAP"); // Loan type
		transaction.setFieldValue("GZLNR", "TEST-99"); // Loan reference
		transaction.setFieldValue("GZBRNM", "ACC1"); // Branch mnemonic
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCUS", "AALEE"); // Customer
		transaction.setFieldValue("GZCLC", "K"); // Customer location
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic
		transaction.setFieldValue("GZDLA", "1000000"); // Loan amount
		transaction.setFieldValue("GZSDT", "991231"); // Start date
		transaction.setFieldValue("GZIDB", "3"); // Interest days basis
		transaction.setFieldValue("GZRAT", "15"); // Interest rate
		transaction.setFieldValue("GZCPI", "Y"); // Capitalise interest
		transaction.setFieldValue("GZRPYM", "2"); // Repayment method
		transaction.setFieldValue("GZSCHC", "2"); // Schedule recalculation control
		transaction.setFieldValue("GZSAP", "Y"); // Schedule advice
		transaction.setFieldValue("GZNPY", "013"); // Number of payments
		transaction.setFieldValue("GZRPQ", "V31"); // Repayment frequency
		transaction.setFieldValue("GZFDT", "1000131"); // First repayment date
		transaction.setFieldValue("GZAB", "0000"); // Receiving a/c branch
		transaction.setFieldValue("GZAN", "870100"); // Receiving a/c number
		transaction.setFieldValue("GZAS", "826"); // Receiving a/c suffix

	}

}
