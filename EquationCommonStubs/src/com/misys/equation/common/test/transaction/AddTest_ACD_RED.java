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
import com.misys.equation.common.test.TestEnvironment;

/**
 * Equation test cases for Add Cheque Deposit - Cust CR
 */
public class AddTest_ACD_RED extends EquationTestCaseFullyAdd
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AddTest_ACD_RED.java 8426 2010-07-29 17:32:49Z CHALLIP1 $";
	String programName = "J49ARR";
	String optionId = "ACD";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		Thread.sleep(1000);
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
		String reference = TestEnvironment.getTestEnvironment().getParameter("RED_Suite");
		if (reference != null)
		{
			transaction.setFieldValue("GZ9OLR", reference); // Off-line deposit reference (12A)
		}
		else
		{
			transaction.setFieldValue("GZ9OLR", "Unknown"); // Transaction refer
		}
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZ9CAB", "0543"); // Customer credit branch number (4A)
		transaction.setFieldValue("GZ9CAN", "012008"); // Customer credit customer number (6A)
		transaction.setFieldValue("GZ9CAS", "001"); // Customer credit suffix (3A)
		transaction.setFieldValue("GZ9TCD", "515"); // Customer credit trancode (3A)
		transaction.setFieldValue("GZ9AMT", "10000"); // Deposit amount (15P,0)
		transaction.setFieldValue("GZ9CCY", "GBP"); // Deposit currency (3A)
		transaction.setFieldValue("GZ9ITM", "1"); // Number of items in deposit (3P,0)
		transaction.setFieldValue("GZ9PDD", "N"); // Post dated deposit? (1A)
	}

}