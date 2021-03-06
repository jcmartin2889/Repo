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
 * Equation test cases for Post Manual Interest - Deal
 */
public class MID extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MID.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H74ARR";
	String optionId = "MID";

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
		transaction.setFieldValue("GZDLP", "XXX"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "NONEXIST"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "XYXY"); // Branch mnemonic (4A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDLP", "CLD"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "Y2C2102"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "CITY"); // Branch mnemonic (4A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCPI", "N"); // Capitalise interest? (1A)
		transaction.setFieldValue("GZBBNI", "0132"); // Interest account branch (4A)
		transaction.setFieldValue("GZCPNI", "876500"); // Interest account basic number (6A)
		transaction.setFieldValue("GZCPSI", "250"); // Interest account suffix (3A)
		transaction.setFieldValue("GZAMT", "1234"); // Adjustment amount (15P,0)
		transaction.setFieldValue("GZCCY", "FRF"); // Currency mnemonic (3A)

		transaction.setFieldValue("GZPOS", "CR"); // Adjustment type flag CR/DR (2A)
		transaction.setFieldValue("GZVAL", "0991231"); // Value from date (7S,0)
	}

}
