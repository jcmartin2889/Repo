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
import com.misys.equation.common.test.EquationTestCaseMaintain;

/**
 * Equation test cases for Add/Maintain/Cancel Nostros
 */
public class DNI_Part2 extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DNI_Part2.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G04FRR";
	String optionId = "DNI";

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
	 * Setup a non-existing key fields
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZNST", "TESTT"); // Nostro mnemonic
	}

	/**
	 * Setup an existing key fields - all fields are required for this function
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZNST", "EQ4TST"); // Nostro mnemonic
		// transaction.setFieldValue("GZAB", "0000"); // Account branch
		// transaction.setFieldValue("GZAN", "870100"); // Basic part of account number
		// transaction.setFieldValue("GZAS", "048"); // Account suffix
		// transaction.setFieldValue("GZXM", "BB"); // Pay transfer method
		// transaction.setFieldValue("GZXMR", "CC"); // Receipt transfer method
		// transaction.setFieldValue("GZCCY", "BHD"); // Currency mnemonic
		// transaction.setFieldValue("GZVST", "1"); // Nostro/vostro
		// transaction.setFieldValue("GZEDI", "N"); // Enable for draft issuance?
		// transaction.setFieldValue("GZCLS", "N"); // CLS Nostro?
		// transaction.setFieldValue("GZCAR", "N"); // Bank cheque advice required?
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZNDS", "G04FRR Add/Maintain Test Changed"); // Nostro description
		transaction.setFieldValue("GZAB", "0000"); // Account branch
		transaction.setFieldValue("GZAN", "870100"); // Basic part of account number
		transaction.setFieldValue("GZAS", "826"); // Account suffix
		transaction.setFieldValue("GZXM", "BB"); // Pay transfer method
		transaction.setFieldValue("GZXMR", "CC"); // Receipt transfer method
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic
		transaction.setFieldValue("GZVST", "1"); // Nostro/vostro
		transaction.setFieldValue("GZEDI", "N"); // Enable for draft issuance?
		transaction.setFieldValue("GZCLS", "N"); // CLS Nostro?
		transaction.setFieldValue("GZCAR", "N"); // Bank cheque advice required?
	}

	/**
	 * This cannot be done as for a maintain all the details must be passed
	 */
	@Override
	public void test00700Maint_RetrievalMaintain_ExistingRecord() throws Exception
	{
	}
}