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
public class RAT_Header_Del extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RAT_Header_Del.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "K51LRR";
	String optionId = "RAT";

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
		transaction.setFieldValue("GZTYP", "H"); // Header/Detail (1A)
		transaction.setFieldValue("GZAPP", "CP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		transaction.setFieldValue("GZPR", "4CN"); // Product type (3A)
		transaction.setFieldValue("GZCTP", "CA"); // Customer type (2A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTYP", "H"); // Header/Detail (1A)
		transaction.setFieldValue("GZAPP", "CP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		transaction.setFieldValue("GZPR", "4CN"); // Product type (3A)
		transaction.setFieldValue("GZCTP", "  "); // Customer type (2A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCCY", "USD"); // Tier currency (3A)
	}

}
