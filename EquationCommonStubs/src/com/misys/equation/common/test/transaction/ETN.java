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
public class ETN extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ETN.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "U68FRR";
	String optionId = "ETN";

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

		transaction.setFieldValue("GZTCD", "510"); // Transaction code (3A)
		transaction.setFieldValue("GZAB", "3210"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "889000"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "826"); // Account suffix (3A)
		transaction.setFieldValue("GZPOD", "0991231"); // Posting date (7S,0)
		transaction.setFieldValue("GZBRNM", "LOND"); // Input branch (4A)
		transaction.setFieldValue("GZPBR", "@@CQ "); // Posting group id or user id, and group level (5A)
		transaction.setFieldValue("GZPSQ7", "0000002"); // 7 Long posting sequence number (7P,0)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZN01", "Mary had a little lamb"); // Extended transaction narrative line 1 (35A)
		transaction.setFieldValue("GZN02", "Its fleece was white as snow"); // Extended transaction narrative line 2 (35A)
		transaction.setFieldValue("GZN03", "And everywhere that Mary went"); // Extended transaction narrative line 3 (35A)
		transaction.setFieldValue("GZN04", "etc, etc"); // Extended transaction narrative line 4 (35A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZN01", "Mary had a sabre-toothed tiger"); // Extended transaction narrative line 1 (35A)
		transaction.setFieldValue("GZN10", "To be or not to be?"); // Extended transaction narrative line 10 (35A)
		transaction.setFieldValue("GZN11", "That is the question"); // Extended transaction narrative line 11 (35A)
		transaction.setFieldValue("GZN12", "Whether it is nobler in the mind"); // Extended transaction narrative line 12 (35A)
		transaction.setFieldValue("GZN13", "to suffer the slings and arrows..."); // Extended transaction narrative line 13 (35A)
	}

}
