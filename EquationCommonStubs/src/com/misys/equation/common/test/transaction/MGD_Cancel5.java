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
 * Equation test cases for Add/Maintain Interface Codes
 */
public class MGD_Cancel5 extends EquationTestCaseFullyCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MGD_Cancel5.java 5723 2009-12-21 17:19:46Z challip1 $";
	String programName = "B81FRR";
	String optionId = "MGD";

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
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTGR", "GSART"); // Interface Group
		transaction.setFieldValue("GZCDE", "TRANSACT"); // Interface Code
	}

}