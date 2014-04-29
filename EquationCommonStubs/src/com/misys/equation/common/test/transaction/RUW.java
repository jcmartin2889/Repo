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

/**
 * Equation test cases for Maintain function
 */
public class RUW extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RUW.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G44FRR";
	String optionId = "RUW";

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
		transaction.setFieldValue("GZREF", "RJL04"); // Withdrawal reference (10A)
		transaction.setFieldValue("GZFRQF", "Y"); // Allow change to frequency? (1A)
		transaction.setFieldValue("GZFRQM", "Y"); // Default frequency relative to start? (1A)
		transaction.setFieldValue("GZMNRE", "N"); // Issue error for minimum regular amount? (1A)
		transaction.setFieldValue("GZMXRE", "N"); // Issue error for maximum regular amount? (1A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDESC", "Third rule"); // Withdrawal description (35A)
		transaction.setFieldValue("GZFRQ", "A"); // Default payment frequency (3A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDESC", "Edited"); // Withdrawal description (35A)
		transaction.setFieldValue("GZFRQ", "S"); // Default payment frequency (3A)
	}

}
