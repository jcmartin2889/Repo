package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseAddMore;
import com.misys.equation.common.test.TestEnvironment;

/**
 * Equation test cases for Add Generic Sundry Item
 */
public class AGS extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AGS.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H15ARR";
	String optionId = "AGS";

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
		transaction.setFieldValue("GZAB", "9999"); // Account branch
		transaction.setFieldValue("GZAN", "002017"); // Basic part of account number
		transaction.setFieldValue("GZAS", "002"); // Account suffix
		transaction.setFieldValue("GZTCD", "010"); // Transaction code3
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0543"); // Account branch
		transaction.setFieldValue("GZAN", "123467"); // Basic part of account number
		transaction.setFieldValue("GZAS", "009"); // Account suffix
		transaction.setFieldValue("GZTCD", "020"); // Transaction code
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic
		transaction.setFieldValue("GZPBR", "JUNT"); // Posting group id or user id, and group level
		transaction.setFieldValue("GZTCCY", "GBP"); // Transaction currency
		transaction.setFieldValue("GZTAMA", "22000"); // Transaction amount
		transaction.setFieldValue("GZNPE", "1"); // Number of posting entries
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
		// getTransaction() returns a new transaction.
		// Parent method copied here to be able to retrieve specific return field from API call!
		super.test00500Add_NewRecord();
		TestEnvironment.getTestEnvironment().putParameter(optionId, currentTransaction.getFieldValue("GZPSQ7"));
	}

}
