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
public class MSG extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MSG.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "D15FRR";
	String optionId = "MSG";

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
		transaction.setFieldValue("GZCTP", "AA"); // Customer type (2A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZMM1", "A"); // Message line 01 (66A)
		transaction.setFieldValue("GZMM2", "B"); // Message line 02 (66A)
		transaction.setFieldValue("GZMM3", ""); // Message line 03 (66A)
		transaction.setFieldValue("GZMM4", ""); // Message line 04 (66A)
		transaction.setFieldValue("GZMM5", ""); // Message line 05 (66A)
		transaction.setFieldValue("GZMM6", ""); // Message line 06 (66A)
		transaction.setFieldValue("GZMM7", ""); // Message line 07 (66A)
		transaction.setFieldValue("GZMM8", ""); // Message line 08 (66A)
		transaction.setFieldValue("GZMM9", ""); // Message line 09 (66A)
		transaction.setFieldValue("GZMM10", ""); // Message line 10 (66A)
		transaction.setFieldValue("GZMM11", ""); // Message line 11 (66A)
		transaction.setFieldValue("GZMM12", ""); // Message line 12 (66A)
		transaction.setFieldValue("GZMM13", ""); // Message line 13 (66A)
		transaction.setFieldValue("GZMM14", ""); // Message line 14 (66A)
		transaction.setFieldValue("GZMM15", ""); // Message line 15 (66A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZMM1", "D"); // Message line 01 (66A)
		transaction.setFieldValue("GZMM2", "C"); // Message line 02 (66A)
		transaction.setFieldValue("GZMM3", ""); // Message line 03 (66A)
		transaction.setFieldValue("GZMM4", ""); // Message line 04 (66A)
		transaction.setFieldValue("GZMM5", ""); // Message line 05 (66A)
		transaction.setFieldValue("GZMM6", ""); // Message line 06 (66A)
		transaction.setFieldValue("GZMM7", ""); // Message line 07 (66A)
		transaction.setFieldValue("GZMM8", ""); // Message line 08 (66A)
		transaction.setFieldValue("GZMM9", ""); // Message line 09 (66A)
		transaction.setFieldValue("GZMM10", ""); // Message line 10 (66A)
		transaction.setFieldValue("GZMM11", ""); // Message line 11 (66A)
		transaction.setFieldValue("GZMM12", ""); // Message line 12 (66A)
		transaction.setFieldValue("GZMM13", ""); // Message line 13 (66A)
		transaction.setFieldValue("GZMM14", ""); // Message line 14 (66A)
		transaction.setFieldValue("GZMM15", ""); // Message line 15 (66A)
	}

}
