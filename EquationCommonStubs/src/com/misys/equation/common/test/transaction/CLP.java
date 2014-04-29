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
 * Equation test cases for Maintain function
 */
public class CLP extends EquationTestCaseCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CLP.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "D04CRR";
	String optionId = "CLP";

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
		transaction.setFieldValue("GZLNP", "XXX"); // Loan type (3A)
		transaction.setFieldValue("GZLNR", "XXXX"); // Loan reference (13A)
		transaction.setFieldValue("GZBRNM", "XXXX"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZVDT", "0991231"); // Payment value date (7S,0)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZNS3", "001"); // Sequence (3P,0)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZLNP", "XCE"); // Loan type (3A)
		transaction.setFieldValue("GZLNR", "XCE-047"); // Loan reference (13A)
		transaction.setFieldValue("GZBRNM", "RLR2"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZVDT", "1000105"); // Payment value date (7S,0)

	}

}
