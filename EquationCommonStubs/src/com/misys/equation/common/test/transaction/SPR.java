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
public class SPR extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SPR.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G49FRR"; // Supervisor Posting Reconciliation
	String optionId = "SPR";

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
		/*
		 * In external input this function only 'reconciles' one posting at a time. So in practice, it must always be called two or
		 * more times to ensure that each group of reconciled postings have balanced amounts.
		 */
		transaction.setFieldValue("GZAB", "0892"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "123115"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "650"); // Account suffix (3A)
		transaction.setFieldValue("GZPOD", "0991231"); // Transaction date (7S,0)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZPBR", "@@CL"); // Posting group id or user id, and group level (5A)
		transaction.setFieldValue("GZPSQ7", "0000141"); // 7 Long posting sequence number (7P,0)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
	}

}
