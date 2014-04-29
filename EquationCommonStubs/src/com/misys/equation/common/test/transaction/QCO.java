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
 * Equation test cases for Add Court Order
 */
public class QCO extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: QCO.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H18ARR";
	String optionId = "QCO";

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
		transaction.setFieldValue("GZTREF", "QCOTEST001"); // Transfer reference (16A)
		transaction.setFieldValue("GZAB1", "0543"); // Debit account branch (4A)
		transaction.setFieldValue("GZAN1", "123467"); // Debit account basic no (6A)
		transaction.setFieldValue("GZAS1", "086"); // Debit account suffix (3A)
		transaction.setFieldValue("GZAB2", "0543"); // Credit account branch (4A)
		transaction.setFieldValue("GZAN2", "123467"); // Credit account basic no (6A)
		transaction.setFieldValue("GZAS2", "087"); // Credit account suffix (3A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTCD1", "010"); // Debit transaction code (3A)
		transaction.setFieldValue("GZAMA1", "-100"); // Debit amount (15P,0)
		transaction.setFieldValue("GZVFR1", "1000105"); // Debit value date (7S,0)
		transaction.setFieldValue("GZDRF1", "JTEST DEBIT"); // Debit user's reference (16A)

		transaction.setFieldValue("GZTCD2", "510"); // Credit transaction code (3A)
		transaction.setFieldValue("GZAMA2", "100"); // Credit amount (15P,0)
		transaction.setFieldValue("GZVFR2", "1000105"); // Credit value date (7S,0)
		transaction.setFieldValue("GZDRF2", "JTEST CREDIT"); // Credit user's reference (16A)

		transaction.setFieldValue("GZBRNM", "LOND"); // Transfer branch (4A)
		transaction.setFieldValue("GZBRND", "LOND"); // Branch for whom the transaction is being done (4A)
		transaction.setFieldValue("GZPBR", "JUNT"); // Posting group id or user id, and group level (5A)
		transaction.setFieldValue("GZFONT", "N"); // Fontis transfer? (1A)

		transaction.setFieldValue("GZHOLD", "Y"); // Hold on queue (1A)
		transaction.setFieldValue("GZQEVM", "QCO"); // Events (6A)

		transaction.setFieldValue("GZIEA", "N"); // Inter a/c transfer with equivalent amounts flag (1A)
		transaction.setFieldValue("GZGDP", "N"); // Generic deal posting (1A)
		transaction.setFieldValue("GZCRT", "Y"); // Court Order (1A)
		transaction.setFieldValue("GZQTR2", "01"); // Queued again (2A)
		transaction.setFieldValue("GZQST2", "10010"); // Queue status 2 (5A)
		transaction.setFieldValue("GZQRS2", "00400"); // Reason for queuing 2 (5A)
	}

}
