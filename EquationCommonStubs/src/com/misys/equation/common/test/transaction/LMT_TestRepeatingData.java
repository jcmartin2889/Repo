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
 * Equation test cases for Add/Maintain Limits
 */
public class LMT_TestRepeatingData extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LMT_TestRepeatingData.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "C02LRR";
	String optionId = "LMT";

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
		transaction.setFieldValue("GZGRP", "HKOCN"); // Group

	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZLSTR", "GROUP"); // Limit structure
		transaction.setFieldValue("GZYLCP", "Y"); // Check limits?
		transaction.setFieldValue("GZYRIT", "N"); // Report in 000s?
		transaction.setFieldValue("GZCF1", "Narrative line 1"); // Narrative
		transaction.setFieldValue("GZCF2", "Narrative line 2"); // Narrative
		transaction.setFieldValue("GZCF3", "Narrative line 3"); // Narrative
		transaction
						.setFieldValue("GZLC",
										"LS001LS011LS012LS013LG011LS014LG012LS015LS016LG013LG015LS117LS017LS018LG016LS019LS021LG017LS040LS041LG034LS398LG999"); // 150
		// Limit
		// categories
		transaction
						.setFieldValue(
										"GZLE",
										"10101051010105101010510101051010105101010510101051010105101010510101051010105101010510101051010105101010510101051010105101010510101051010105101010510101051010105"); // 150
		// Limit
		// expiry
		// dates
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCF1", "Narrative line 1 maint"); // Narrative
		transaction.setFieldValue("GZCF2", "Narrative line 2 maint"); // Narrative
		transaction.setFieldValue("GZCF3", "Narrative line 3 maint"); // Narrative
		transaction
						.setFieldValue("GZLC",
										"LS001LS011LS012LS013LG011LS014LG012LS015LS016LG013LG015LS117LS017LS018LG016LS019LS021LG017LS040LS041LG034LS398LG999"); // 150
		// Limit
		// categories
		transaction
						.setFieldValue(
										"GZLE",
										"10101061010106101010610101051010105101010510101051010105101010510101051010105101010510101051010105101010510101051010105101010510101051010105101010510101051010105"); // 150
		// Limit
		// expiry
		// dates

	}

}
