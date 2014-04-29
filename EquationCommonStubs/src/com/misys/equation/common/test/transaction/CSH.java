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

import com.misys.equation.common.test.EquationTestCaseCancelCashier;

/**
 * Equation test cases for Maintain function
 */
public class CSH extends EquationTestCaseCancelCashier
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CSH.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		programName = "L50CRR";
		optionId = "CSH";
		transactionType = "CSD";
		addOptionId = "ACA";
		retrieveBeforeCancel = true;
	}

}