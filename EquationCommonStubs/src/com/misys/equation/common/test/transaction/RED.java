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
import com.misys.equation.common.test.TestEnvironment;

/**
 * Equation test cases for Returned Cheque Debtor Id
 */
public class RED extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RED.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "X31FRR";
	String optionId = "RED";
	String addOptionId = "MDC";

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
		String reference = TestEnvironment.getTestEnvironment().getParameter(addOptionId);
		if (reference != null)
		{
			transaction.setFieldValue("GZDRF", reference); // Off-line deposit reference (12A)
		}
		else
		{
			transaction.setFieldValue("GZDRF", "Unknown"); // Transaction refer
		}
		transaction.setFieldValue("GZITM", "001"); // Cheque deposit item (3P,0)

	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDBI", "DEBTOR1"); // Debtor id (8A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDBI", "DEBTOR2"); // Debtor id (8A)
	}

}
