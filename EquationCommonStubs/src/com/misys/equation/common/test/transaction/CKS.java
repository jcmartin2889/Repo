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
 * Equation test cases for Cashier Buy/Sell Rates
 */
public class CKS extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CKS.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "J23FRR";
	String optionId = "CKS";

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
		transaction.setFieldValue("GZCCY", "USD"); // Currency mnemonic (3A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZEXRA", "1.5"); // Buy cash exchange rate (12S,7)
		transaction.setFieldValue("GZMDNA", "100"); // Buy cash min. denomination (7S,0)
		transaction.setFieldValue("GZCPCA", "1"); // Buy cash commission % (5S,3)
		transaction.setFieldValue("GZEXRB", "1.5"); // Buy T/C exchange rate (12S,7)
		transaction.setFieldValue("GZMDNB", "100"); // Buy T/C min. denomination (7S,0)
		transaction.setFieldValue("GZCPCB", "1"); // Buy T/C commission % (5S,3)
		transaction.setFieldValue("GZEXRC", "1.5"); // Sell cash exchange rate (12S,7)
		transaction.setFieldValue("GZMDNC", "100"); // Sell cash min. denomination (7S,0)
		transaction.setFieldValue("GZCPCC", "1"); // Sell cash commission % (5S,3)
		transaction.setFieldValue("GZEXRD", "1.5"); // Sell T/C exchange rate (12S,7)
		transaction.setFieldValue("GZMDND", "100"); // Sell T/C min. denomination (7S,0)
		transaction.setFieldValue("GZCPCD", "1"); // Sell T/C commission % (5S,3)
		transaction.setFieldValue("GZREI", "Y"); // Reciprocal indicator (1A)
		transaction.setFieldValue("GZANB", "054312008001"); // Buy T/C account number (13A)
		transaction.setFieldValue("GZANS", "054312008001"); // Sell T/C account number (13A)
		transaction.setFieldValue("GZREV", "1.5"); // Exchange rate (12P,7)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{

	}

}
