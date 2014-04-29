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
 * Equation test cases for Sundry Postings (TI) function - in the future
 */
public class SFM_Future_Posting extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SFM_Future_Posting.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H97FRR";
	String optionId = "SFM";

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
		transaction.setFieldValue("GZBRNP", "LOND"); // Users input branch (4A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Deal branch mnemonic (4A)
		transaction.setFieldValue("GZDLP", "FDD"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "RMM2"); // Deal reference (13A)
		transaction.setFieldValue("GZNS3", "1"); // Sequence number (3P,0)
		transaction.setFieldValue("GZDTE", "1010105"); // Value date (7S,0)
		transaction.setFieldValue("GZAPP", "TI"); // Application (2A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0543"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "012008"); // Account basic number (6A)
		transaction.setFieldValue("GZAS", "001"); // Account suffix (3A)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZAMA", "10000"); // Unsigned amount (15P,0)
		transaction.setFieldValue("GZNEGP", "D"); // Debit or Credit Transaction (D or C) (1A)
		transaction.setFieldValue("GZTCD", "510"); // Transaction code (3A)
		transaction.setFieldValue("GZPRF", "P"); // Pay or receive flag; P=Pay or R=Receive (1A)
		transaction.setFieldValue("GZYREC", "N"); // Posting to be reconciled? (1A)
		transaction.setFieldValue("GZVTE", "N"); // Valid for funding or repayment? (1A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0543"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "012008"); // Account basic number (6A)
		transaction.setFieldValue("GZAS", "001"); // Account suffix (3A)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZAMA", "10000"); // Unsigned amount (15P,0)
		transaction.setFieldValue("GZNEGP", "D"); // Debit or Credit Transaction (D or C) (1A)
		transaction.setFieldValue("GZTCD", "510"); // Transaction code (3A)
		transaction.setFieldValue("GZPRF", "P"); // Pay or receive flag; P=Pay or R=Receive (1A)
		transaction.setFieldValue("GZYREC", "N"); // Posting to be reconciled? (1A)
		transaction.setFieldValue("GZVTE", "N"); // Valid for funding or repayment? (1A)
	}

	@Override
	public void test01500Maint_Validate_ExistingRecord() throws Exception
	{
		// cannot test a future dated maintenance
	}

	@Override
	public void test01600Maint_ExistingRecord() throws Exception
	{
		// cannot test a future dated maintenance
	}

	@Override
	public void test01610Maint_RetrievalMaintain_ExistingRecord() throws Exception
	{
		// cannot test a future dated maintenance
	}

}
