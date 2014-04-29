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
import com.misys.equation.common.test.EquationTestCaseCancel;

/**
 * Equation test cases for Cancel Repayment
 */
public class CPY extends EquationTestCaseCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CPY.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "D34CRR";
	String optionId = "CPY";

	// This function is used for the cancellation of automatic penalty payments
	// the cancellation of other payments should use the appropriate payment function

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		retrieveBeforeCancel = true;
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
		transaction.setFieldValue("GZLNP", "CAP"); // Loan type (3A)
		transaction.setFieldValue("GZLNR", "CAP4"); // Loan reference (13A)
		transaction.setFieldValue("GZBRNM", "CUTE"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZVDT", "1000105"); // Payment value date (7S,0)
		transaction.setFieldValue("GZDDT", "1000105"); // Payment value date (7S,0)
		// transaction.setFieldValue("GZNS3M", "1"); // Related sequence number (3A)
		// transaction.setFieldValue("GZMVT", "I"); // Movement type (1A)
		// transaction.setFieldValue("GZMVTS", "P"); // Movement sub-type (1A)
	}

	/**
	 * Setup an existing key fields - need all details
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZLNP", "CR2"); // Loan type (3A)
		transaction.setFieldValue("GZLNR", "CR2-100"); // Loan reference (13A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZVDT", "0991231"); // Payment value date (7S,0)
		transaction.setFieldValue("GZDDT", "0991231"); // Payment value date (7S,0)
		transaction.setFieldValue("GZNS3M", "900"); // Related sequence number (3A)
		transaction.setFieldValue("GZMVT", "I"); // Movement type (1A)
		transaction.setFieldValue("GZMVTS", "P"); // Movement sub-type (1A)
	}

}
