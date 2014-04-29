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
 * Equation test cases for Maintain function Add/Maintain Commercial Paper Item
 */
public class Add_OKO extends EquationTestCaseFullyAdd
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: Add_OKO.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	String programName = "W29FRR";
	String optionId = "OKO";

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
		transaction.setFieldValue("GZBPN", "05436677"); // Bank Paper Number (12A)
		transaction.setFieldValue("GZFLDN", "20000009"); // Folder Number (8A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch Mnemonic (4A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBLLN", "1234"); // Bill Number (8A)
		transaction.setFieldValue("GZAMT", "5000"); // Amount (15P,0)
		transaction.setFieldValue("GZMDTE", "1000105"); // Maturity Date (7S,0)
		transaction.setFieldValue("GZMOS", "AS"); // Method of settlement (2A)
		transaction.setFieldValue("GZDAB", "9132"); // Drawer Branch (4A)
		transaction.setFieldValue("GZDAN", "234567"); // Drawer basic number (6A)
		transaction.setFieldValue("GZDAS", "007"); // Drawer Suffix (3A)
		transaction.setFieldValue("GZDCDE", "JANE03"); // Drawer/Debtor Code (8A)
	}

}
