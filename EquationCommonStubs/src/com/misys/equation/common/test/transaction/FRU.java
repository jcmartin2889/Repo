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
 * Equation test cases for Add/Maintain Funding Rules
 */
public class FRU extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FRU.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G43FRR";
	String optionId = "FRU";

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
		transaction.setFieldValue("GZREF", "RJL456"); // Funding reference (10A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDESC", "1234567890"); // Funding description (35A)
		transaction.setFieldValue("GZFRQ", "A"); // Funding frequency (3A)
		transaction.setFieldValue("GZFRQF", "Y"); // Allow change to frequency? (1A)
		transaction.setFieldValue("GZFRQM", "Y"); // Default frequency relative to start? (1A)
		transaction.setFieldValue("GZERSF", "N"); // End date relative to start (1A)
		transaction.setFieldValue("GZMNRE", "N"); // Issue error for minimum regular amount? (1A)
		transaction.setFieldValue("GZMXRE", "N"); // Issue error for maximum regular amount? (1A)
		transaction.setFieldValue("GZTCDF", "BBB"); // Transaction code - deal (3A)
		transaction.setFieldValue("GZTCDI", "WD1"); // Transaction code - fund (3A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDESC", "1234567890"); // Funding description (35A)
		transaction.setFieldValue("GZFRQ", "A"); // Funding frequency (3A)
		transaction.setFieldValue("GZFRQF", "Y"); // Allow change to frequency? (1A)
		transaction.setFieldValue("GZFRQM", "Y"); // Default frequency relative to start? (1A)
		transaction.setFieldValue("GZERSF", "N"); // End date relative to start (1A)
		transaction.setFieldValue("GZMNRE", "N"); // Issue error for minimum regular amount? (1A)
		transaction.setFieldValue("GZMXRE", "N"); // Issue error for maximum regular amount? (1A)
		transaction.setFieldValue("GZTCDF", "WC2"); // Transaction code - deal (3A)
		transaction.setFieldValue("GZTCDI", "PD3"); // Transaction code - fund (3A)
	}

}
