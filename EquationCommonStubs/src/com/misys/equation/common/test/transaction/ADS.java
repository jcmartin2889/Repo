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
 * Equation test cases for Add Money Market Deal with Settlements
 */
public class ADS extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ADS.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G36ARR";
	String optionId = "ADS";

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
		transaction.setFieldValue("GZDLP", "CII"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "TEST"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTDT", "D"); // Term deal type; L=Loan, D=Deposit (1A)
		transaction.setFieldValue("GZAPP", "CL"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		transaction.setFieldValue("GZCUS", "RLR2"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", "004"); // Customer location (3A)
		transaction.setFieldValue("GZCMR", "RLR2-350204-1"); // Commitment reference (13A)
		transaction.setFieldValue("GZCLT", "N"); // Collateral required? (1A)
		transaction.setFieldValue("GZCCY", "BHD"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZDLA", "1000"); // Deal amount (15P,0)
		transaction.setFieldValue("GZSDT", "1000107"); // Start date (7S,0)
		transaction.setFieldValue("GZMDT", "1000808"); // Maturity date (7S,0)
		transaction.setFieldValue("GZCTRD", "1000105"); // Contract date (7S,0)

		transaction.setFieldValue("GZRPYM", "2"); // Repayment method (1A)
		transaction.setFieldValue("GZRAT", "10"); // Actual rate (11P,7)
		transaction.setFieldValue("GZIDB", "0"); // Interest days basis (1A)
		transaction.setFieldValue("GZASD", "300"); // Deal account suffix (3A)
		transaction.setFieldValue("GZNSFD", "1001229"); // Next loan statementdate (7S,0)

		transaction.setFieldValue("GZNDA", "N"); // Narrative on deal account postings ? (1A)

		transaction.setFieldValue("GZCCYF", "GBP"); // Funding settlement currency (3A)
		transaction.setFieldValue("GZNSTF", "GBP P "); // Funding nostro mnemonic (6A)
		transaction.setFieldValue("GZXMF", "SW"); // Funding transfer method (2A)
		transaction.setFieldValue("GZCCYM", "GBP"); // Maturity settlement currency (3A)
		transaction.setFieldValue("GZNSTM", "GBP P "); // Maturity nostro mnemonic (6A)
		transaction.setFieldValue("GZXMM", "SW"); // Maturity transfer method (2A)
	}

}