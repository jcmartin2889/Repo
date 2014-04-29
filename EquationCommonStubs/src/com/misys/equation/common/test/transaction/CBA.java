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
 * Equation test cases for Maintain Account Basic Field Access
 */
public class CBA extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CBA.java 4721 2009-09-15 11:14:17Z weddelc1 $";
	String programName = "F72FRR";
	String optionId = "CBA";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
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
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZOID", "MAB"); // Unique option id assigned by a user to a function (3A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZNA1", "Address 1");
		transaction.setFieldValue("GZA01", "Y"); // Access field 1 (1A)
		transaction.setFieldValue("GZA02", "Y"); // Access field 2 (1A)
		transaction.setFieldValue("GZA03", "Y"); // Access field 3 (1A)
		transaction.setFieldValue("GZA04", "Y"); // Access field 4 (1A)
		transaction.setFieldValue("GZA06", "Y"); // Access field 6 (1A)
		transaction.setFieldValue("GZA07", "Y"); // Access field 7 (1A)
		transaction.setFieldValue("GZA08", "Y"); // Access field 8 (1A)
		transaction.setFieldValue("GZA09", "Y"); // Access field 9 (1A)
		transaction.setFieldValue("GZA10", "Y"); // Access field 10 (1A)
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
		transaction.setFieldValue("GZA23", "Y"); // Access field 23 (1A)
		transaction.setFieldValue("GZA26", "Y"); // Access field 26 (1A)
		transaction.setFieldValue("GZA29", "Y"); // Access field 29 (1A)
		transaction.setFieldValue("GZA30", "Y"); // Access field 30 (1A)
		transaction.setFieldValue("GZA31", "Y"); // Access field 31 (1A)
		transaction.setFieldValue("GZA32", "Y"); // Access field 32 (1A)
		transaction.setFieldValue("GZA35", "Y"); // Access field 35 (1A)
		transaction.setFieldValue("GZA36", "Y"); // Access field 36 (1A)
		transaction.setFieldValue("GZA38", "Y"); // Access field 38 (1A)
		transaction.setFieldValue("GZA40", "Y"); // Access field 40 (1A)
		transaction.setFieldValue("GZA41", "Y"); // Access field 41 (1A)
		transaction.setFieldValue("GZFPR", "H39HMR"); // Program name (6A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZNA1", "Address 1");
		transaction.setFieldValue("GZA01", "N"); // Access field 1 (1A)
		transaction.setFieldValue("GZA02", "N"); // Access field 2 (1A)
		transaction.setFieldValue("GZA03", "N"); // Access field 3 (1A)
		transaction.setFieldValue("GZA04", "N"); // Access field 4 (1A)
		transaction.setFieldValue("GZA06", "N"); // Access field 6 (1A)
		transaction.setFieldValue("GZA07", "N"); // Access field 7 (1A)
		transaction.setFieldValue("GZA08", "N"); // Access field 8 (1A)
		transaction.setFieldValue("GZA09", "N"); // Access field 9 (1A)
		transaction.setFieldValue("GZA10", "N"); // Access field 10 (1A)
		transaction.setFieldValue("GZA11", "N"); // Access field 11 (1A)
		transaction.setFieldValue("GZA12", "N"); // Access field 12 (1A)
		transaction.setFieldValue("GZA13", "N"); // Access field 13 (1A)
		transaction.setFieldValue("GZA14", "N"); // Access field 14 (1A)
		transaction.setFieldValue("GZA15", "N"); // Access field 15 (1A)
		transaction.setFieldValue("GZA16", "N"); // Access field 16 (1A)
	}

}
