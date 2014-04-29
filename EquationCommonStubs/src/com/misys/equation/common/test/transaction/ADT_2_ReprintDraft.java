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
import com.misys.equation.common.test.EquationTestCaseAddCashier;
import com.misys.equation.common.test.TestEnvironment;

/**
 * Equation test cases for Maintain function
 */
public class ADT_2_ReprintDraft extends EquationTestCaseAddCashier
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ADT_2_ReprintDraft.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		programName = "L64ARR";
		optionId = "ADT";
		transactionType = "DRI";
	}
	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		super.setupExistKeyFields(transaction);
		String reference = TestEnvironment.getTestEnvironment().getParameter("ADR");
		if (reference != null)
		{
			transaction.setFieldValue("GZTRF", reference); // Transaction refer
		}
		else
		{
			transaction.setFieldValue("GZTRF", "Unknown"); // Transaction refer
		}
	}
	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		// transaction.setField("GZDNO2", "100"); // Original draft number (16A)
		transaction.setFieldValue("GZDNO", createCashierReference()); // New draft number (16A)
	}

}
