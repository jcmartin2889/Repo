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
 * Equation test cases for Add Generic Term Deal
 */
public class AddTest_GDA extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AddTest_GDA.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "U36ARR";
	String optionId = "GDA";

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
		transaction.setFieldValue("GZPDLP", "GDT"); // Primary deal type (3A)
		transaction.setFieldValue("GZREF", "TEST18"); // Reference (10A)
		transaction.setFieldValue("GZBRNM", "ACC1"); // Branch mnemonic (4A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCUS", "ATLANT"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", "IND"); // Customer location (3A)
		transaction.setFieldValue("GZCLT", "N"); // Collateral required? (1A)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency (3A)
		transaction.setFieldValue("GZAMT", "1000"); // Current amount (15P,0)
		transaction.setFieldValue("GZOMT", "0"); // Original amount (15P,0)
		transaction.setFieldValue("GZINT", "0"); // Interest accrued (15P,0)
		transaction.setFieldValue("GZSDT", "1000106"); // Start date (7S,0)
		transaction.setFieldValue("GZMDT", "OPEN"); // Maturity date (7S,0)
		transaction.setFieldValue("GZCTRD", "991231"); // Contract date (7S,0)
		transaction.setFieldValue("GZPEG", "N"); // Pegged (1A)
		transaction.setFieldValue("GZPRD", "OPEN"); // Last rollover date (7S,0)
		transaction.setFieldValue("GZPCD", "OPEN"); // Last interest date (7S,0)
		transaction.setFieldValue("GZCPI", "N"); // Capitalise interest (1A)
		transaction.setFieldValue("GZACT", "G4"); // Account type (2A)
		// transaction.setFieldValue("GZASD", "003"); // Account suffix (3A)
		transaction.setFieldValue("GZDARC", "N"); // Archive?? (1A)
		transaction.setFieldValue("GZRCL", "N"); // Recalculate EIR? (1A)
	}

}
