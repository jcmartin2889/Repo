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
import com.misys.equation.common.test.EquationTestCaseFully;

/**
 * Equation test cases for Maintain Account Other Field Access
 */
public class COA extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: COA.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "F74FRR";
	String optionId = "COA";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		retrieveBeforeCancel = false;
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
		transaction.setFieldValue("GZOID", "MAO"); // Unique option id assigned by a user to a function (3A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZFPR", "H37MMR"); // Program name (6A)
		transaction.setFieldValue("GZA01", "Y"); // Access field 1 (1A)
		transaction.setFieldValue("GZA02", "Y"); // Access field 2 (1A)
		transaction.setFieldValue("GZA03", "Y"); // Access field 3 (1A)
		transaction.setFieldValue("GZA04", "Y"); // Access field 4 (1A)
		transaction.setFieldValue("GZA05", "Y"); // Access field 5 (1A)
		transaction.setFieldValue("GZA06", "Y"); // Access field 6 (1A)
		transaction.setFieldValue("GZA07", "Y"); // Access field 7 (1A)
		transaction.setFieldValue("GZA08", "Y"); // Access field 8 (1A)
		transaction.setFieldValue("GZA09", "Y"); // Access field 9 (1A)
		transaction.setFieldValue("GZA10", "Y"); // Access field 10 (1A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZA10", "N"); // Access field 10 (1A)
	}

}
