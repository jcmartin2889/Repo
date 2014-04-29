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
import com.misys.equation.common.test.EquationTestCaseAddOnce;
import com.misys.equation.common.test.TestEnvironment;

/**
 * @author weddelc1
 */
public class CHA extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CHA.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "Q89ARR";
	String optionId = "CHA";

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
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZLNP", "RL1"); // Loan type (3A)
		String reference = TestEnvironment.getTestEnvironment().getParameter("RLA");
		transaction.setFieldValue("GZLNR", "RL1-DOY2"); // Loan reference (13A)
		transaction.setFieldValue("GZREF", "RL1-DOY2"); // Originators reference (20A)
		transaction.setFieldValue("GZCDTE", "991122"); // Repayment due date (7S,0)
	 }

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCLTP", "P"); // Claim type (1A)
		transaction.setFieldValue("GZPDDT", "1000104"); // Date paid (7S,0)
	}

}
