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
import com.misys.equation.common.test.EquationTestCaseAddMore;

/**
 * Equation test cases for Maintain function
 */
public class CHQ extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CHQ.java 4721 2009-09-15 11:14:17Z weddelc1 $";
	String programName = "H15ARR";
	String optionId = "CHQ";

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
		transaction.setFieldValue("GZAB", "9999"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "999999"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "999"); // Account suffix (3A)
		transaction.setFieldValue("GZTCD", "999"); // Transaction code (3A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0543"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "001462"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "001"); // Account suffix (3A)
		transaction.setFieldValue("GZTCD", "209"); // Transaction code (3A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZPBR", "IPS2"); // Posting group id or user id, and group level (5A)
		transaction.setFieldValue("GZVFR", "1000106"); // Value from date (7S,0)
		transaction.setFieldValue("GZBRND", "LOND"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZDRF", "0014620010"); // Users own reference for deals, reconciliation etc (16A)
		transaction.setFieldValue("GZAMA", "1500-"); // Ordinary amount in minor currency units (15P,0)
		transaction.setFieldValue("GZCCY", "PHP"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZNPE", "1"); // Number of posting entries (5P,0)
		transaction.setFieldValue("GZNR1", "STOP"); // Narrative line 1 (35A)
		transaction.setFieldValue("GZRFR", "N"); // Referred item? (1A)
		transaction.setFieldValue("GZAUT", "Y"); // Authorised item? (1A)
		transaction.setFieldValue("GZSSI", "N"); // Special item? (1A)
		transaction.setFieldValue("GZTTP", "C"); // Transaction type (1A)
		transaction.setFieldValue("GZHSRL", "0014620010"); // Serial "number" forming the key to a stop order (16A)
		transaction.setFieldValue("GZHAMT", "1500-"); // Stop order amount (15P,0)
		transaction.setFieldValue("GZAUTC", "MASSEYR1"); // Referred item authorisation code (12A)
		transaction.setFieldValue("GZCED", "2"); // Currency edit field (1A)
		transaction.setFieldValue("GZCHQ", "N"); // Cheque item? (1A)
		transaction.setFieldValue("GZDRFN", "0"); // Cheque serial number (16P,0)
		transaction.setFieldValue("GZTCCY", "PHP"); // Transaction currency (3A)
		transaction.setFieldValue("GZTAMA", "1500"); // Transaction amount (15P,0)
		transaction.setFieldValue("GZHCCY", "PHP"); // Stop order currency (3A)
		transaction.setFieldValue("GZPSQ7", "0000182"); // 7 Long posting sequence number (7P,0)
	}

}
