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
 * @author weddelc1
 */
public class RHA extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RHA.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "X48ARR";
	String optionId = "RHA";

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
		transaction.setFieldValue("GZBRNM", "CUTE"); // Loan branch (4A)
		transaction.setFieldValue("GZLNP", "COD"); // Loan type (3A)
		transaction.setFieldValue("GZLNR", "COA10"); // Loan reference (13A)
	}
	/**
	 * Setup a non-existing key fields only
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBRNM", "CUTE"); // Loan branch (4A)
		transaction.setFieldValue("GZLNP", "COA"); // Loan type (3A)
		transaction.setFieldValue("GZLNR", "COA13"); // Loan reference (13A)
		transaction.setFieldValue("GZSDT", "1000229"); // Start date (7S,0)
		transaction.setFieldValue("GZEDT", "1000331"); // End date (7S,0)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCCD", "AD"); // Charge code (2A)
		transaction.setFieldValue("GZCHA", "1000"); // Charge amount (15P,0)

	}

}
