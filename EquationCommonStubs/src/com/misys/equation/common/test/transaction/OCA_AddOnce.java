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
 * @author weddelc1
 */
public class OCA_AddOnce extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OCA_AddOnce.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H38ARR";
	String optionId = "OCA";

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
		transaction.setFieldValue("GZAB", "9132"); // Branch Number
		transaction.setFieldValue("GZAN", "234567"); // Account Basic Number
		transaction.setFieldValue("GZAS", "034"); // Account Suffix
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCCY", "GBP"); // Account Currency
		transaction.setFieldValue("GZACT", "CA"); // Account Type
		transaction.setFieldValue("GZSHN", "Test Account"); // Short Name
		transaction.setFieldValue("GZOAD", "1000105"); // Account Open Date
	}

}
