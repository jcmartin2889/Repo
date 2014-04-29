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
public class RDS extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RDS.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G36ARR";
	String optionId = "RDS";

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
		transaction.setFieldValue("GZDLP", "RT2"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "RDS-BACK-4-3"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTDT", "D"); // Term deal type; L=Loan, D=Deposit (1A)
		transaction.setFieldValue("GZAPP", "RD"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		transaction.setFieldValue("GZCUS", "AALEE"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", "K"); // Customer location (3A)
		transaction.setFieldValue("GZDLA", "100000"); // Deal amount (15P,0)
		transaction.setFieldValue("GZCCY", "USD"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZSDT", "0991231"); // Start date (7S,0)
		transaction.setFieldValue("GZMDT", "1001231"); // Maturity date (7S,0)
		transaction.setFieldValue("GZCTRD", "0991231"); // Contract date (7S,0)
		transaction.setFieldValue("GZPRC", "M2  "); // Period code (4A)
		transaction.setFieldValue("GZNRD", "1000131"); // Next rollover date (7S,0)
		transaction.setFieldValue("GZRDDY", "M"); // Date abbreviation (1A)
		transaction.setFieldValue("GZRNUM", "001"); // Period code number (3P,0)

		transaction.setFieldValue("GZRTM", "1"); // Margin rate (11P,7)
		transaction.setFieldValue("GZIDB", "0"); // Interest days basis (1A)
		transaction.setFieldValue("GZNCD", "1000131"); // Next interest date (7S,0)
		transaction.setFieldValue("GZCPI", "N"); // Capitalise interest? (1A)
		transaction.setFieldValue("GZPRR", "Y"); // Print renewal reminder? (1A)
		transaction.setFieldValue("GZPRS", "Y"); // Print renewal statement? (1A)
		//transaction.setFieldValue("GZASD", "001"); // Deal account suffix (3A)

		transaction.setFieldValue("GZYROL", "A"); // Are rollovers allowed for deals of this type? (1A)

		transaction.setFieldValue("GZNDA", "N"); // Narrative on deal account postings ? (1A)
		transaction.setFieldValue("GZCCYF", "USD"); // Funding settlement currency (3A)
		transaction.setFieldValue("GZNSTF", "BARUSD"); // Funding nostro mnemonic (6A)
		transaction.setFieldValue("GZXMF", "CC"); // Funding transfer method (2A)
		transaction.setFieldValue("GZCCYM", "USD"); // Maturity settlement currency (3A)
		transaction.setFieldValue("GZNSTM", "BARUSD"); // Maturity nostro mnemonic (6A)
		transaction.setFieldValue("GZXMM", "CC"); // Maturity transfer method (2A)
		transaction.setFieldValue("GZCCYF", "USD"); // Funding settlement currency (3A)
	}

}
