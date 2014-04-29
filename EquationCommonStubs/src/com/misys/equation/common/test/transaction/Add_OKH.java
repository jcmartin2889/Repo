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
 * Equation test cases for Maintain Commercial Paper A/c
 */
public class Add_OKH extends EquationTestCaseFullyAdd
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: Add_OKH.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "W59FRR";
	String optionId = "OKH";

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
		transaction.setFieldValue("GZPAP", "AA"); // Paper Type (2A)
		transaction.setFieldValue("GZPOP", "AX"); // Purpose of Presentation (2A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZPIC", "SP131"); // Paper in Custody (6A)
		transaction.setFieldValue("GZCMA", "SP101"); // Correspondents Memo Account (6A)
		transaction.setFieldValue("GZMAT", "RS"); // Customer Memo A/C Type (2A)
		transaction.setFieldValue("GZMBR", "SP133"); // Memo - due to other branches (6A)
		transaction.setFieldValue("GZDTC", "010"); // dr transaction code (3A)
		transaction.setFieldValue("GZCTC", "510"); // cr transaction code (3A)
		transaction.setFieldValue("GZMCW", "SP135"); // Memo Contra Weightage a/c (6A)
		transaction.setFieldValue("GZMWT", "RS"); // Memo Cust Weightage a/c Type (2A)
		transaction.setFieldValue("GZDWT", "010"); // DR Weightage Trans code (3A)
		transaction.setFieldValue("GZCWT", "510"); // CR Weightage Trans Code (3A)
	}

}
