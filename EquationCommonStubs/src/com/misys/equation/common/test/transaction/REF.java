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
 * Equation test cases for EESF Rule Exceptions
 */
public class REF extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: REF.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "B61FRR";
	String optionId = "REF";

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
		transaction.setFieldValue("GZGRP", "NTEST1"); // User group (6A)
		transaction.setFieldValue("GZUSR", ""); // User id (4A)
		transaction.setFieldValue("GZRNO", "00001"); // Rule sequence number (5S,0)
		transaction.setFieldValue("GZGRPE", "NTEST1"); // Exception for user group (6A)
		transaction.setFieldValue("GZUSRE", ""); // Exception for user id (4A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZEXD", "Description"); // Rule exception description (25A)
		transaction.setFieldValue("GZSLO", "AE"); // System locator (3A)
		transaction.setFieldValue("GZOPC", "I"); // Operation code (1A)
		transaction.setFieldValue("GZCV1", "VALUE"); // Comparison value 1 (35A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZEXD", "Changed Description"); // Rule exception description (25A)
		transaction.setFieldValue("GZRM1", "Line one"); // Remarks line 1 (35A)
		transaction.setFieldValue("GZRM2", "Line two"); // Remarks line 2 (35A)
		transaction.setFieldValue("GZRM3", "Line three"); // Remarks line 3 (35A)
		transaction.setFieldValue("GZRM4", "Line four"); // Remarks line 4 (35A)
	}

}
