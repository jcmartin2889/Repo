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
 * Equation test cases for Add/Maint Generic Validation Rules
 */
public class GVR extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GVR.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "B27FRR";
	String optionId = "GVR";

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
		transaction.setFieldValue("GZVRNO", "123456"); // Validation rule number (6A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZRUD", "Test001"); // Rule description (25A)
		transaction.setFieldValue("GZMOD", "*ALL"); // Module (6A)
		transaction.setFieldValue("GZDCOD", "SL55"); // Data code (5A)
		transaction.setFieldValue("GZDOPR", "N"); // Data operation (1A)
		transaction.setFieldValue("GZCV1", "100"); // Comparison value 1 (35A)
		transaction.setFieldValue("GZDERM", "KSM1003"); // Debit error message (7A)
		transaction.setFieldValue("GZCERM", "KSM1001"); // Credit error message (7A)
		transaction.setFieldValue("GZMERM", "KSM1002"); // Maintenance error message (7A)

	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZRUD", "Maintain"); // Rule description (25A)
	}

}
