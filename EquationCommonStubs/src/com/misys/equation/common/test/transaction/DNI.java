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
 * Equation test cases for Add/Maintain/Cancel Nostros
 */
public class DNI extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DNI.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
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
		transaction.setFieldValue("GZNST", "NOSTRO"); // Nostro mnemonic
		transaction.setFieldValue("GZNDS", "G04FRR Add/Maintain Test Changed"); // Nostro description
		transaction.setFieldValue("GZAB", "0543"); // Account branch
		transaction.setFieldValue("GZAN", "123107"); // Basic part of account number
		transaction.setFieldValue("GZAS", "001"); // Account suffix
		transaction.setFieldValue("GZXM", "BB"); // Pay transfer method
		transaction.setFieldValue("GZXMR", "CC"); // Receipt transfer method
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic
		transaction.setFieldValue("GZVST", "1"); // Nostro/vostro
		transaction.setFieldValue("GZEDI", "N"); // Enable for draft issuance?
		transaction.setFieldValue("GZCLS", "N"); // CLS Nostro?
		transaction.setFieldValue("GZCAR", "N"); // Bank cheque advice required?
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZNDS", "G04FRR Add/Maintain Test"); // Nostro description
		transaction.setFieldValue("GZAB", "0543"); // Account branch
		transaction.setFieldValue("GZAN", "123107"); // Basic part of account number
		transaction.setFieldValue("GZAS", "001"); // Account suffix
		transaction.setFieldValue("GZTG53", "Y"); // A/c at nostro inst on TAG 53
		transaction.setFieldValue("GZXM", "BB"); // Pay transfer method
		transaction.setFieldValue("GZXMR", "CC"); // Receipt transfer method
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic
		transaction.setFieldValue("GZVST", "1"); // Nostro/vostro
		transaction.setFieldValue("GZEDI", "N"); // Enable for draft issuance?
		transaction.setFieldValue("GZCLS", "N"); // CLS Nostro?
		transaction.setFieldValue("GZCAR", "N"); // Bank cheque advice required?
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
	}

}
