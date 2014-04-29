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
 * @author weddelc1
 */
public class NCM extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: NCM.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H09ARR";
	String optionId = "NCM";

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
		transaction.setFieldValue("GZCMR", "JCOMM"); // Commitment reference (13A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCUS", "SOUTH"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", ""); // Customer location (3A)
		transaction.setFieldValue("GZBRNM", "KBSL"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZASC", "603"); // Commitment account suffix (3A)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZAMT", "10000000"); // Original commitment amount (15P,0)
		transaction.setFieldValue("GZDTEA", "0991231"); // Agreement date (7S,0)
		transaction.setFieldValue("GZDTEF", "0991231"); // First drawdown date (7S,0)
		transaction.setFieldValue("GZDTEX", "9999999"); // Expiry date (7S,0)
		transaction.setFieldValue("GZDTER", "9999999"); // Final repayment date (7S,0)
		transaction.setFieldValue("GZYRV", "N"); // Revolving status (1A)
		transaction.setFieldValue("GZYMB", "Y"); // Multi-branch (1A)

	}

}
