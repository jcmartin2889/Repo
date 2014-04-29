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
 * Equation test cases for Maintain function
 */
public class BSC extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BSC.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "B90FRR";
	String optionId = "BSC";

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
		transaction.setFieldValue("GZSORT", "RJL1"); // Sort code (30A)
		transaction.setFieldValue("GZSUBC", "456"); // Sub code (10A)
		transaction.setFieldValue("GZPRIM", "Y"); // Primary record (1A)
		transaction.setFieldValue("GZBKNM", "Bang Ko"); // Bank name (35A)
		transaction.setFieldValue("GZBSHN", "BnK"); // Bank short name (20A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDSA", "Y"); // Direct settlement allowed? (1A)
		transaction.setFieldValue("GZBLOC", "Chi Na"); // Bank location (35A)
		transaction.setFieldValue("GZPZIP", "1600"); // Post code (10A)
		transaction.setFieldValue("GZSWB", "RYAN"); // SWIFT bank id (4A)
		transaction.setFieldValue("GZCNA", "RJ"); // SWIFT country code (2A)
		transaction.setFieldValue("GZSWL", "HA"); // SWIFT location (2A)
		transaction.setFieldValue("GZSWBR", "RJL"); // SWIFT branch id (3A)
		transaction.setFieldValue("GZUS1", "12345678901234567890123456789012345");
		transaction.setFieldValue("GZUS2", "12345678901234567890123456789012345");
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDSA", "N"); // Direct settlement allowed? (1A)
		transaction.setFieldValue("GZBLOC", "NA Chi"); // Bank location (35A)
		transaction.setFieldValue("GZPZIP", "1600"); // Post code (10A)
		transaction.setFieldValue("GZSWB", "NAYR"); // SWIFT bank id (4A)
		transaction.setFieldValue("GZCNA", "JR"); // SWIFT country code (2A)
		transaction.setFieldValue("GZSWL", "AH"); // SWIFT location (2A)
		transaction.setFieldValue("GZSWBR", "LJR"); // SWIFT branch id (3A)
	}

}
