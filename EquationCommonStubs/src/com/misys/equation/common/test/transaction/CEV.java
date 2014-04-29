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
 * Equation test cases for Cancel External Event
 */
public class CEV extends EquationTestCaseCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CEV.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "U66CRR";
	String optionId = "CEV";

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
		transaction.setFieldValue("GZAB", "0543"); // Branch Number (4A)
		transaction.setFieldValue("GZAN", "012041"); // Account Basic Number (6A)
		transaction.setFieldValue("GZAS", "000"); // Account Suffix (3A)
		transaction.setFieldValue("GZDTE", "991231"); // Date (7S,0)
		transaction.setFieldValue("GZSQN", "1"); // Sqn number (16P,0)
		transaction.setFieldValue("GZAMT", "10000"); // Amount calculated on (15P,0)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency (3A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0543"); // Branch Number (4A)
		transaction.setFieldValue("GZAN", "012041"); // Account Basic Number (6A)
		transaction.setFieldValue("GZAS", "001"); // Account Suffix (3A)
		transaction.setFieldValue("GZDTE", "991231"); // Date (7S,0)
		transaction.setFieldValue("GZSQN", "1"); // Sqn number (16P,0)
		transaction.setFieldValue("GZAMT", "10000"); // Amount calculated on (15P,0)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency (3A)
	}

}
