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
 * Equation test cases for Add/Maintain Yield Curve
 */
public class AMC extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AMC.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "Q40LRR";
	String optionId = "AMC";

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
		transaction.setFieldValue("GZCCY", "ADP"); // Currency
		transaction.setFieldValue("GZCRV", "AAA"); // Curve mnemonic
		transaction.setFieldValue("GZSQN", "1"); // Sequence

		transaction.setFieldValue("GZDES", "Desc"); // Curve description
		transaction.setFieldValue("GZCRT", "D"); // Curve type (1A)
		transaction.setFieldValue("GZIPM", "S"); // Interpolation menthod
		transaction.setFieldValue("GZVDB0", "N"); // Valid for days basis 0?
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZVDB0", "Y"); // Valid for days basis 0?
		transaction.setFieldValue("GZVDB1", "Y"); // Valid for days basis 1?
		transaction.setFieldValue("GZVDB2", "Y"); // Valid for days basis 2?
		transaction.setFieldValue("GZVDB3", "Y"); // Valid for days basis 3?
		transaction.setFieldValue("GZVDB4", "Y"); // Valid for days basis 4?
		transaction.setFieldValue("GZVDB5", "Y"); // Valid for days basis 5?
		transaction.setFieldValue("GZVDB6", "Y"); // Valid for days basis 6?
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
	}

}
