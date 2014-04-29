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
import com.misys.equation.common.test.EquationTestCaseMaintain;
import com.misys.equation.common.test.TestEnvironment;

/**
 * Equation test cases for Maintain Chequebook Requests
 */
public class MCB extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MCB.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "J71MRR";
	String optionId = "MCB";
	String addOptionId = "CBR";

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
	 * Setup a non-existing key fields
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBBN", "0000"); // Branch number
		transaction.setFieldValue("GZBNO", "000001"); // Customer number
		transaction.setFieldValue("GZSFX", "001"); // Account suffix
		transaction.setFieldValue("GZSEQN", "999"); // Sequence number (3P,0)

	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBBN", "0000"); // Branch number
		transaction.setFieldValue("GZBNO", "000001"); // Customer number
		transaction.setFieldValue("GZSFX", "001"); // Account suffix
		// transaction.setField("GZSEQN", "1"); // Sequence number (3P,0)
		String reference = TestEnvironment.getTestEnvironment().getParameter(addOptionId);
		if (reference != null)
		{
			transaction.setFieldValue("GZSEQN", reference); // Transaction refer
		}
		else
		{
			transaction.setFieldValue("GZSEQN", "Unknown"); // Transaction refer
		}
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZSTAT", "B"); // Status (1A)
		transaction.setFieldValue("GZDREC", "1000105"); // Date received from printers (7S,0)
	}

}
