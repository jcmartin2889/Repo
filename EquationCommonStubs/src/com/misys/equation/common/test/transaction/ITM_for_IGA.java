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
import com.misys.equation.common.test.EquationTestCaseMaintainCancel;

/**
 * Equation test cases for Maintain function
 */
public class ITM_for_IGA extends EquationTestCaseMaintainCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ITM_for_IGA.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H18FRR";
	String optionId = "ITM";

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
	 * Setup an existing key fields only
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTREF", "IGATEST002"); // Transfer reference (16A)
		transaction.setFieldValue("GZAB1", "0543"); // Debit account branch (4A)
		transaction.setFieldValue("GZAN1", "123467"); // Debit account basic no (6A)
		transaction.setFieldValue("GZAS1", "086"); // Debit account suffix (3A)
		transaction.setFieldValue("GZAB2", "0543"); // Credit account branch (4A)
		transaction.setFieldValue("GZAN2", "123467"); // Credit account basic no (6A)
		transaction.setFieldValue("GZAS2", "085"); // Credit account suffix (3A)

	}

	/**
	 * Setup a non-existing key fields only
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTREF", "NOTEXIST"); // Transfer reference (16A)
		transaction.setFieldValue("GZAB1", "0543"); // Debit account branch (4A)
		transaction.setFieldValue("GZAN1", "123467"); // Debit account basic no (6A)
		transaction.setFieldValue("GZAS1", "086"); // Debit account suffix (3A)
		transaction.setFieldValue("GZAB2", "0000"); // Credit account branch (4A)
		transaction.setFieldValue("GZAN2", "000001"); // Credit account basic no (6A)
		transaction.setFieldValue("GZAS2", "600"); // Credit account suffix (3A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTCD1", "010"); // Debit transaction code (3A)
		transaction.setFieldValue("GZAMA1", "-200"); // Debit amount (15P,0)
		transaction.setFieldValue("GZVFR1", "0991231"); // Debit value date (7S,0)
		transaction.setFieldValue("GZDRF1", "JTEST DEBIT - M2"); // Debit user's reference (16A)

		transaction.setFieldValue("GZTCD2", "510"); // Credit transaction code (3A)
		transaction.setFieldValue("GZAMA2", "200"); // Credit amount (15P,0)
		transaction.setFieldValue("GZVFR2", "0991231"); // Credit value date (7S,0)
		transaction.setFieldValue("GZDRF2", "JTEST CREDIT - M2"); // Credit user's reference (16A)

		transaction.setFieldValue("GZBRNM", "LOND"); // Transfer branch (4A)
		transaction.setFieldValue("GZBRND", "LOND"); // Branch for whom the transaction is being done (4A)
		transaction.setFieldValue("GZPBR", "EQUI"); // Posting group id or user id, and group level (5A)
		transaction.setFieldValue("GZFONT", "N"); // Fontis transfer? (1A)
	}

}
