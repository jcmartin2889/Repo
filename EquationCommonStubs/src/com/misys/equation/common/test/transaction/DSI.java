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
 * Equation test cases for Maintain Deal Settlement Details
 */
public class DSI extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DSI.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G40MRR";
	String optionId = "DSI";

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
		transaction.setFieldValue("GZDLP", "FDD"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "TEST-NONE"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZBOC", "C"); // Bank or Customer (1A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDLP", "AAA"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "ALKDJFLKD"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZBOC", "C"); // Bank or Customer (1A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCC1", "AD"); // Funding charge code 1 (2A)
		transaction.setFieldValue("GZCA1", "350"); // Funding charge amount 1 (15P,0)
		transaction.setFieldValue("GZSCYM", "GBP"); // Maturity settlement currency (3A)
		transaction.setFieldValue("GZNSTM", "GBP P"); // Maturity nostro mnemonic (6A)
		transaction.setFieldValue("GZSCYF", "GBP"); // Funding settlement currency (3A)
		transaction.setFieldValue("GZNSTF", "GBP P"); // Funding nostro mnemonic (6A)

	}

}
