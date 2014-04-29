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
public class CEA_2_DraftEncashment extends EquationTestCaseAddCashier
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CEA_2_DraftEncashment.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		programName = "L67ARR";
		optionId = "CEA";
		transactionType = "DEN";
	}
	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		String reference = TestEnvironment.getTestEnvironment().getParameter("ADR");
		if (reference != null)
		{
			transaction.setFieldValue("GZDNO", "114040895949"); // Draft number (16A)
		}
		else
		{
			transaction.setFieldValue("GZDNO", "1140404165643"); // Draft number (16A)
		}

		transaction.setFieldValue("GZSPC", "326"); // SP code (3A)
		transaction.setFieldValue("GZCCY", "GBP"); // Cheque/draft currency (3A)
		transaction.setFieldValue("GZCCY2", "USD"); // Credit currency mnemonic (3A)
		transaction.setFieldValue("GZAMT", "10000"); // Cheque/draft amount (15P,0)
		transaction.setFieldValue("GZAMT2", "10000"); // Exchange amount (15P,0)

	}

}
