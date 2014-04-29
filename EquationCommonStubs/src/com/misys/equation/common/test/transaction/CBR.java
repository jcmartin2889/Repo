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
 * Equation test cases for Cheque Book Request
 */
public class CBR extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CBR.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "J71ARR";
	String optionId = "CBR";

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
		transaction.setFieldValue("GZBBN", "0000"); // Branch number
		transaction.setFieldValue("GZBNO", "000001"); // Customer number
		transaction.setFieldValue("GZSFX", "999"); // Account suffix
		// Transaction sequence must be calculated by API caller
		transaction.setFieldValue("GZSEQN", "1"); // Sequence number (3P,0)

	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBBN", "0000"); // Branch number
		transaction.setFieldValue("GZBNO", "000001"); // Customer number
		transaction.setFieldValue("GZSFX", "001"); // Account suffix
		// Transaction sequence must be calculated by API caller
		transaction.setFieldValue("GZSEQN", "1"); // Sequence number (3P,0)

	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCBTC", "SPL"); // Cheque book type code
		transaction.setFieldValue("GZSTAT", "P"); // Status
		transaction.setFieldValue("GZCCD", "EN"); // Charge code
		transaction.setFieldValue("GZCHAR", "100"); // Charge for cheque book
		transaction.setFieldValue("GZTAXD", "50"); // Tax or duty on the charge
		transaction.setFieldValue("GZCORQ", "N"); // Confirmation required flag?
		transaction.setFieldValue("GZCARQ", "N"); // Collection advice required flag

		/* The following are hidden fields required to be processed. */
		transaction.setFieldValue("GZSOUR", "B"); // Source of cheque book request

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
		TestEnvironment.getTestEnvironment().putParameter(optionId, currentTransaction.getFieldValue("GZSEQN"));
	}
}
