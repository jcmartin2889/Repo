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
import com.misys.equation.common.test.EquationTestCaseMaintainCancel;

/**
 * Equation test cases for Maintain function
 */
public class AMD_maintain_cancel extends EquationTestCaseMaintainCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AMD_maintain_cancel.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "D20FRR";
	String optionId = "AMD";
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
	 * Setup an existing key fields only
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0543"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "131313"); // Account number (6A)
		transaction.setFieldValue("GZAS", "107"); // Account suffix (3A)
		transaction.setFieldValue("GZREF", "0543000105000001"); // Internal reference (16A)

		//String reference = TestEnvironment.getTestEnvironment().getParameter("AMD_add");
		//if (reference != null)
		//{
			//transaction.setFieldValue("GZREF", reference); // Internal reference (16A)
		//}
	}

	/**
	 * Setup a non-existing key fields only
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0000"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "000000"); // Account number (6A)
		transaction.setFieldValue("GZAS", "000"); // Account suffix (3A)
		transaction.setFieldValue("GZREF", "0000000000000000"); // Internal reference (16A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZONAM", "Name"); // Originator's name (20A)
		transaction.setFieldValue("GZOID", "Identifier"); // Originator's identifier (10A)
		transaction.setFieldValue("GZOREF", "CRG-251"); // DD originator's reference (20A)
		transaction.setFieldValue("GZSTAT", "0"); // Mandate status (1A)
		transaction.setFieldValue("GZMT", "DG"); // Mandate type (2A)
	}
}
