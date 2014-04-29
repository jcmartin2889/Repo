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

/**
 * Equation test cases for Maintain function
 */
public class MCM extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MCM.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H09MRR";
	String optionId = "MCM";

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
		transaction.setFieldValue("GZCMR", "NONEXIST"); // Commitment reference (13A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCMR", "JCOMM"); // Commitment reference (13A)

	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDTEF", "0991231"); // First drawdown date (7S,0)
		transaction.setFieldValue("GZDTEX", "1050101"); // Expiry date (7S,0)
		transaction.setFieldValue("GZDTER", "9999999"); // Final repayment date (7S,0)
		transaction.setFieldValue("GZABF", "9998"); // Fee account branch (4A)
		transaction.setFieldValue("GZANF", "872300"); // Fee account basic number (6A)
		transaction.setFieldValue("GZASF", "826"); // Fee account suffix (3A)
		transaction.setFieldValue("GZYRV", "Y"); // Revolving status (1A)
		transaction.setFieldValue("GZYMB", "Y"); // Multi-branch (1A)
	}

}
