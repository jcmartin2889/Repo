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
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author Paul Challis
 */
public class MTR_ extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MTR_.java 4721 2009-09-15 11:14:17Z weddelc1 $";
	private EquationStandardTransaction transaction;
	public void setUp() throws Exception
	{
		super.setUp();
		/*
		 * Get an empty transaction class you'll need to enter the name of the transaction concatenated with the WMENU1 option id.
		 */
		transaction = getEquationStandardTransaction("C89FRRMTR");
	}
	public void testAdd() throws Exception
	{
		/*
		 * Set the transaction type
		 */
		transaction.setMode("A");
		/*
		 * Set the transaction fields
		 */
		transaction.setFieldValue("GZTCD", "011");// Transaction code
		transaction.setFieldValue("GZTCN", "DEBIT - 1");// Transaction code name
		transaction.setFieldValue("GZDCI", "D");// Debit/Credit
		transaction.setFieldValue("GZTCM", "DR1");// Transaction code Mnemonic
		transaction.setFieldValue("GZTC1", "001");// Suffix range 1 low
		transaction.setFieldValue("GZTC2", "998");// Suffix range 1 high
		transaction.setFieldValue("GZTC3", "000");// Suffix range 2 low
		transaction.setFieldValue("GZTC4", "000");// Suffix range 2 high
		transaction.setFieldValue("GZTCDR", "510");// Reversal trans code
		transaction.setFieldValue("GZTCA", "00");// Default int. days local
		transaction.setFieldValue("GZTCC", "07");// Default int. days other
		transaction.setFieldValue("GZTAP", "N");// Transaction advice
		transaction.setFieldValue("GZPMA", "Y");// Valid for movement a/c
		transaction.setFieldValue("GZPTA", "N");// Valid for term accounts
		transaction.setFieldValue("GZPEA", "N");// Valid for exchange a/c
		transaction.setFieldValue("GZPCA", "N");// Valid for contingent a/c
		transaction.setFieldValue("GZPIA", "Y");// Valid for internal a/c
		transaction.setFieldValue("GZRVT", "N");// Reversal trans code
		transaction.setFieldValue("GZDDB", "N");// Add default days for Dr
		transaction.setFieldValue("GZPTN", "Y");// Print trans code name
		transaction.setFieldValue("GZSUD", "N");// Suppress update DLM
		transaction.setFieldValue("GZSTR", "N");// Turnover stats reversal
		transaction.setFieldValue("GZDFA", "N");// Debit if funds available
		transaction.setFieldValue("GZBTR", "N");// Bulking allowed
		transaction.setFieldValue("GZCTC", "N");// Stopped Cheque
		transaction.setFieldValue("GZMID", "N");// Multi-item deposit
		transaction.setFieldValue("GZVXD", "N");// Valid for EXIMBILLS deals
		/*
		 * See if it works
		 */
		assertTestStandardTransaction(transaction, true);
	}
	public void testMaintain() throws Exception
	{
		/*
		 * Set the transaction type
		 */
		transaction.setMode("M");
		/*
		 * Set the transaction fields
		 */
		transaction.setFieldValue("GZTCD", "011");// Transaction code
		transaction.setFieldValue("GZTCN", "DEBIT - Maintained");// Transaction code name
		transaction.setFieldValue("GZDCI", "D");// Debit/Credit
		transaction.setFieldValue("GZTCM", "DR1");// Transaction code Mnemonic
		transaction.setFieldValue("GZTC1", "001");// Suffix range 1 low
		transaction.setFieldValue("GZTC2", "998");// Suffix range 1 high
		transaction.setFieldValue("GZTC3", "000");// Suffix range 2 low
		transaction.setFieldValue("GZTC4", "000");// Suffix range 2 high
		transaction.setFieldValue("GZTCDR", "510");// Reversal trans code
		transaction.setFieldValue("GZTCA", "00");// Default int. days local
		transaction.setFieldValue("GZTCC", "07");// Default int. days other
		transaction.setFieldValue("GZTAP", "N");// Transaction advice
		transaction.setFieldValue("GZPMA", "Y");// Valid for movement a/c
		transaction.setFieldValue("GZPTA", "N");// Valid for term accounts
		transaction.setFieldValue("GZPEA", "N");// Valid for exchange a/c
		transaction.setFieldValue("GZPCA", "N");// Valid for contingent a/c
		transaction.setFieldValue("GZPIA", "Y");// Valid for internal a/c
		transaction.setFieldValue("GZRVT", "N");// Reversal trans code
		transaction.setFieldValue("GZDDB", "N");// Add default days for Dr
		transaction.setFieldValue("GZPTN", "Y");// Print trans code name
		transaction.setFieldValue("GZSUD", "N");// Suppress update DLM
		transaction.setFieldValue("GZSTR", "N");// Turnover stats reversal
		transaction.setFieldValue("GZDFA", "N");// Debit if funds available
		transaction.setFieldValue("GZBTR", "N");// Bulking allowed
		transaction.setFieldValue("GZCTC", "N");// Stopped Cheque
		transaction.setFieldValue("GZMID", "N");// Multi-item deposit
		transaction.setFieldValue("GZVXD", "N");// Valid for EXIMBILLS deals
		/*
		 * See if it works
		 */
		assertTestStandardTransaction(transaction, true);
	}
	public void testCancel() throws Exception
	{
		/*
		 * Set the transaction type
		 */
		transaction.setMode("D");
		/*
		 * Set the transaction fields
		 */
		transaction.setFieldValue("GZTCD", "011");// Transaction code
		transaction.setFieldValue("GZTCN", "DEBIT - Maintained");// Transaction code name
		transaction.setFieldValue("GZDCI", "D");// Debit/Credit
		transaction.setFieldValue("GZTCM", "DR1");// Transaction code Mnemonic
		transaction.setFieldValue("GZTC1", "001");// Suffix range 1 low
		transaction.setFieldValue("GZTC2", "998");// Suffix range 1 high
		transaction.setFieldValue("GZTC3", "000");// Suffix range 2 low
		transaction.setFieldValue("GZTC4", "000");// Suffix range 2 high
		transaction.setFieldValue("GZTCDR", "510");// Reversal trans code
		transaction.setFieldValue("GZTCA", "00");// Default int. days local
		transaction.setFieldValue("GZTCC", "07");// Default int. days other
		transaction.setFieldValue("GZTAP", "N");// Transaction advice
		transaction.setFieldValue("GZPMA", "Y");// Valid for movement a/c
		transaction.setFieldValue("GZPTA", "N");// Valid for term accounts
		transaction.setFieldValue("GZPEA", "N");// Valid for exchange a/c
		transaction.setFieldValue("GZPCA", "N");// Valid for contingent a/c
		transaction.setFieldValue("GZPIA", "Y");// Valid for internal a/c
		transaction.setFieldValue("GZRVT", "N");// Reversal trans code
		transaction.setFieldValue("GZDDB", "N");// Add default days for Dr
		transaction.setFieldValue("GZPTN", "Y");// Print trans code name
		transaction.setFieldValue("GZSUD", "N");// Suppress update DLM
		transaction.setFieldValue("GZSTR", "N");// Turnover stats reversal
		transaction.setFieldValue("GZDFA", "N");// Debit if funds available
		transaction.setFieldValue("GZBTR", "N");// Bulking allowed
		transaction.setFieldValue("GZCTC", "N");// Stopped Cheque
		transaction.setFieldValue("GZMID", "N");// Multi-item deposit
		transaction.setFieldValue("GZVXD", "N");// Valid for EXIMBILLS deals
		/*
		 * See if it works
		 */
		assertTestStandardTransaction(transaction, true);
	}
}
