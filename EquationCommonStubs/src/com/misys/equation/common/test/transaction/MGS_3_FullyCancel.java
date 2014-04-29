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
import com.misys.equation.common.test.EquationTestCaseFullyCancel;

/**
 * Equation test cases for Cancel Generated Schedule Instruction function
 */
public class MGS_3_FullyCancel extends EquationTestCaseFullyCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MGS_3_FullyCancel.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "I18FRR";
	String optionId = "MGS";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		retrieveBeforeCancel = true;
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
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBRNM", "HEAD"); // Deal branch mnemonic (4A)
		transaction.setFieldValue("GZDLP", "CR3"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "0991231000006"); // Deal reference (13A)
		transaction.setFieldValue("GZDTES", "1000313"); // Schedule start date (7S,0)
	}

}