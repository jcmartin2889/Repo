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
 * Equation test cases for EESF Access Restriction
 */
public class RRA extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RRA.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	// String programName = "X19FRR";
	String programName = "B60FRR";
	String optionId = "RRA";

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
		transaction.setFieldValue("GZGRP", "NTEST1"); // User group
		transaction.setFieldValue("GZRNO", "00001"); // Rule sequence number
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZRUD", "Description"); // Rule description
		transaction.setFieldValue("GZAPT", "A"); // Apply to (A/C/B)
		transaction.setFieldValue("GZEOU", "E"); // Enquiry or update (E/U/B)
		transaction.setFieldValue("GZSLO", "AE"); // System locator
		transaction.setFieldValue("GZOPC", "I"); // Operation code
		transaction.setFieldValue("GZCV1", "VALUE"); // Comparison value 1
		transaction.setFieldValue("GZERM", "KSM0001"); // Error message
		transaction.setFieldValue("GZRNP", "N"); // Rule no. as parm 1 (Y/N)
		transaction.setFieldValue("GZSLP", "N"); // SL as parm 2 (Y/N)
		transaction.setFieldValue("GZV1P", "N"); // Value 1 as parm 3 (Y/N)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZRUD", "Changed Description"); // Rule description
	}

}
