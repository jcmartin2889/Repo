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
import com.misys.equation.common.test.EquationTestCaseAddCancel;

/**
 * Equation test cases for Maintain function
 */
public class CFW_AddCancel extends EquationTestCaseAddCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CFW_AddCancel.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "K43FRR";
	String optionId = "CFW";

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
		transaction.setFieldValue("GZPYRF", "LOND00105H000009"); // Payment reference (16A)
		transaction.setFieldValue("GZBRMH", "LOND"); // Senders branch (4A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZFMT", "0187C54312345000000000000000000000000000000051000 "
						+ "0000000000054312345300000102000000034125   aop ben"
						+ "e name and address                                "
						+ "                                   00             "); // Formatted message (1002A)
	}
}