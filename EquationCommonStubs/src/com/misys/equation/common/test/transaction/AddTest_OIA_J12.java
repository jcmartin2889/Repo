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
 * Equation test cases for Open Internal Account
 */
public class AddTest_OIA_J12 extends EquationTestCaseFullyAdd
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AddTest_OIA_J12.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H38ARR";
	String optionId = "OIA";

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
		transaction.setFieldValue("GZAB", "2000"); // Account branch
		transaction.setFieldValue("GZAN", "921100"); // Basic part of account number
		transaction.setFieldValue("GZAS", "002"); // Account suffix
		transaction.setFieldValue("GZINT", "Y"); // Internal account?
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCTP", "HA"); // Customer type
		transaction.setFieldValue("GZACT", "TY"); // Account type
		transaction.setFieldValue("GZSHN", "@NS-GBP"); // Account short name
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZOAD", "1000105"); // Date account opened
		transaction.setFieldValue("GZACD", "II"); // Analysis code
	}

}
