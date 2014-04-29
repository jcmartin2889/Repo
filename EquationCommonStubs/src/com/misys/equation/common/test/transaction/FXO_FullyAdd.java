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
import com.misys.equation.common.test.EquationTestCaseFullyAdd;

/**
 * Equation test cases for Maintain function
 */
public class FXO_FullyAdd extends EquationTestCaseFullyAdd
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FXO_FullyAdd.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "I12LRR";
	String optionId = "FXO";

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
		transaction.setFieldValue("GZBRNM", "CITY"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZDLP", "FXF"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "Y2FL201"); // Deal reference (13A)
		transaction.setFieldValue("GZEDT", "1000229"); // Option period end date (7S,0)

	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZSDT", "1000229"); // Option period start date (7S,0)
		transaction.setFieldValue("GZEXR", "2.111111"); // Exchange rate (13P,7)

	}

}
