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
public class ADD extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ADD.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "A31FRR";
	String optionId = "ADD";

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
		transaction.setFieldValue("GZDLP", "FDD"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "2010MOD"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZLDDT", "CDD"); // Linked deal type (3A)
		transaction.setFieldValue("GZLDDR", "Y2CL101"); // Linked deal reference (13A)
		transaction.setFieldValue("GZLDBR", "CITY"); // Linked branch mnemonic (4A)
		transaction.setFieldValue("GZSPI", "Test"); // Instruction group description (35A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZLDDT", "DDD"); // Linked deal type (3A)
		transaction.setFieldValue("GZLDDR", "202"); // Linked deal reference (13A)
		transaction.setFieldValue("GZLDBR", "LOND"); // Linked branch mnemonic (4A)
		transaction.setFieldValue("GZSPI", "Test (M)"); // Instruction group description (35A)
	}

}
