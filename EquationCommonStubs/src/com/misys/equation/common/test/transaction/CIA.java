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
 * Equation test cases for Maintain A/c Interest Field Access
 */
public class CIA extends EquationTestCaseFully

{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CIA.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "F73FRR";
	String optionId = "CIA";

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
		transaction.setFieldValue("GZOID", "MIC"); // Unique option id assigned by a user to a function (3A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZFPR", "H73MMR"); // Program name (6A)
		transaction.setFieldValue("GZA01", "N"); // Access field 1 (1A)
		transaction.setFieldValue("GZA03", "Y"); // Access field 3 (1A)
		transaction.setFieldValue("GZA05", "Y"); // Access field 5 (1A)
		transaction.setFieldValue("GZA06", "Y"); // Access field 6 (1A)
		transaction.setFieldValue("GZA07", "Y"); // Access field 7 (1A)
		transaction.setFieldValue("GZA08", "Y"); // Access field 8 (1A)
		transaction.setFieldValue("GZA09", "Y"); // Access field 9 (1A)
		transaction.setFieldValue("GZA11", "Y"); // Access field 11 (1A)
		transaction.setFieldValue("GZA12", "Y"); // Access field 12 (1A)
		transaction.setFieldValue("GZA13", "Y"); // Access field 13 (1A)
		transaction.setFieldValue("GZA14", "Y"); // Access field 14 (1A)
		transaction.setFieldValue("GZA15", "Y"); // Access field 15 (1A)
		transaction.setFieldValue("GZA16", "Y"); // Access field 16 (1A)
		transaction.setFieldValue("GZA17", "Y"); // Access field 17 (1A)
		transaction.setFieldValue("GZA18", "Y"); // Access field 18 (1A)
		transaction.setFieldValue("GZA19", "Y"); // Access field 19 (1A)
		transaction.setFieldValue("GZA20", "Y"); // Access field 20 (1A)
		transaction.setFieldValue("GZA21", "Y"); // Access field 21 (1A)
		transaction.setFieldValue("GZA22", "Y"); // Access field 22 (1A)
		transaction.setFieldValue("GZA23", "Y"); // Access field 23 (1A)
		transaction.setFieldValue("GZA24", "Y"); // Access field 24 (1A)
		transaction.setFieldValue("GZA25", "Y"); // Access field 25 (1A)
		transaction.setFieldValue("GZA26", "Y"); // Access field 26 (1A)
		transaction.setFieldValue("GZA27", "Y"); // Access field 27 (1A)
		transaction.setFieldValue("GZA28", "Y"); // Access field 28 (1A)
		transaction.setFieldValue("GZA29", "Y"); // Access field 29 (1A)
		transaction.setFieldValue("GZA30", "Y"); // Access field 30 (1A)
		transaction.setFieldValue("GZA31", "Y"); // Access field 30 (1A)
		transaction.setFieldValue("GZA37", "Y"); // Add overdraft confirmation (1A)
		transaction.setFieldValue("GZA39", "Y"); // Tax method (1A)
		transaction.setFieldValue("GZA40", "Y"); // Amend overdraft confirmation (1A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZA01", "Y"); // Access field 1 (1A)

	}

}
