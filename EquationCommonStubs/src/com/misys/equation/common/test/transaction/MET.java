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
 * Equation test cases for Add/Maintain e-Banking Tran Type
 */
public class MET extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MET.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "D26FRR";
	String optionId = "MET";

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
		transaction.setFieldValue("GZTRTY", "EQ4"); // Transaction type
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTRDS", "Description"); // Description
		transaction.setFieldValue("GZWPCY", "GBP"); // We pay currency
		transaction.setFieldValue("GZWPTM", "AC"); // We pay transfer method
		transaction.setFieldValue("GZPTSC", "KBC"); // Payment type - same currency
		transaction.setFieldValue("GZPTXC", "KBC"); // Payment type - x-ccy
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTRDS", "Changed Description"); // Description
		transaction.setFieldValue("GZWPCY", "GBP"); // We pay currency
		transaction.setFieldValue("GZWPTM", "AC"); // We pay transfer method
		transaction.setFieldValue("GZPTSC", "KBC"); // Payment type - same currency
		transaction.setFieldValue("GZPTXC", "KBC"); // Payment type - x-ccy
	}

}
