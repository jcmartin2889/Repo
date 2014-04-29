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
import com.misys.equation.common.test.EquationTestCaseFullyAdd;

/**
 * Equation test cases for Maintain function Maintain Commercial Paper Group
 */
public class Add_OKL extends EquationTestCaseFullyAdd
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: Add_OKL.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	String programName = "W14FRR";
	String optionId = "OKL";

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
		transaction.setFieldValue("GZFLDN", "20000009"); // Folder Number (8A)
		transaction.setFieldValue("GZLOG", "Y"); // Logged? (1A)
		transaction.setFieldValue("GZRVW", "N"); // Reviewed? (1A)
		transaction.setFieldValue("GZAUT", "N"); // Authorised? (1A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch Mnemonic (4A)
		transaction.setFieldValue("GZPAP", "AA"); // Paper Type (2A)
		transaction.setFieldValue("GZPOP", "AX"); // Purpose of Presentation (2A)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency (3A)
		transaction.setFieldValue("GZCUS", "ACCS"); // Customer Mnemonic (6A)
		transaction.setFieldValue("GZCLC", "DTA"); // Customer Location (3A)
		transaction.setFieldValue("GZNUM", "01"); // Items Available (2P,0)
		transaction.setFieldValue("GZTOT", "5000"); // Total Available (15P,0)
	}

}
