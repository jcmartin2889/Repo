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
import com.misys.equation.common.test.TestEnvironment;

/**
 * Equation test cases for Pay Direct Debit
 */
public class AMD_add extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AMD_add.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "D20FRR";
	String optionId = "AMD";

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
		transaction.setFieldValue("GZAB", "0000"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "000000"); // Account number (6A)
		transaction.setFieldValue("GZAS", "000"); // Account suffix (3A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0132"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "012008"); // Account number (6A)
		transaction.setFieldValue("GZAS", "050"); // Account suffix (3A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZONAM", "Name"); // Originator's name (20A)
		transaction.setFieldValue("GZOID", "Identifier"); // Originator's identifier (10A)
		transaction.setFieldValue("GZOREF", "CRG-25"); // DD originator's reference (20A)
		transaction.setFieldValue("GZSTAT", "1"); // Mandate status (1A)
		transaction.setFieldValue("GZMT", "DG"); // Mandate type (2A)
	}

	/**
	 * Test: add mode: new record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	@Override
	public void test00500Add_NewRecord() throws Exception
	{
		// Save the Internal Reference
		super.test00500Add_NewRecord();
		TestEnvironment.getTestEnvironment().removeParameter(optionId);
		TestEnvironment.getTestEnvironment().putParameter(optionId, currentTransaction.getFieldValue("GZREF"));
	}
}
