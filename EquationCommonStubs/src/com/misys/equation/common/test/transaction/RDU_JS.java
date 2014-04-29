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

/*
 * Equation test cases for Add Retail Deposit
 */
public class RDU_JS extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RDU_JS.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G36ARR";
	String optionId = "RDU";

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
		transaction.setFieldValue("GZDLP", "RDX"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "RD1479D2"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTDT", "D"); // Term deal type; L=Loan, D=Deposit (1A)
		transaction.setFieldValue("GZAPP", "MM"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		transaction.setFieldValue("GZCUS", "ABRAHA"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", "ISA"); // Customer location (3A)
		transaction.setFieldValue("GZDLA", "100000000"); // Deal amount (15P,0)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZSDT", "0991231"); // Start date (7S,0)
		transaction.setFieldValue("GZMDT", "1021231"); // Maturity date (7S,0)
		transaction.setFieldValue("GZCTRD", "0991231"); // Contract date (7S,0)
		transaction.setFieldValue("GZPRC", "P8  "); // Period code (4A)
		transaction.setFieldValue("GZNRD", "1021231"); // Next rollover date (7S,0)
		transaction.setFieldValue("GZRDDY", "Y"); // Date abbreviation (1A)
		transaction.setFieldValue("GZRNUM", "003"); // Period code number (3P,0)

		transaction.setFieldValue("GZBRR", "10"); // Base rate code (2A)
		transaction.setFieldValue("GZPEG", "Y"); // Interest rate pegged? (1A)
		transaction.setFieldValue("GZIDB", "0"); // Interest days basis (1A)

		transaction.setFieldValue("GZCPI", "N"); // Capitalise interest? (1A)
		transaction.setFieldValue("GZCR1", "10"); // Tax code 1 (2A)
		transaction.setFieldValue("GZCR2", "20"); // Tax code 2 (2A)

		transaction.setFieldValue("GZPRR", "Y"); // Print renewal reminder? (1A)
		transaction.setFieldValue("GZPRS", "Y"); // Print renewal statement? (1A)
		// transaction.setField("GZASD", "101"); // Deal account suffix (3A)

		transaction.setFieldValue("GZYROL", "A"); // Are rollovers allowed for deals of this type? (1A)

	}

}
