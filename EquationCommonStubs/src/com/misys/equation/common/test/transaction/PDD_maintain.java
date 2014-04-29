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
import com.misys.equation.common.test.TestEnvironment;

/**
 * Equation test cases for Maintain function
 */
public class PDD_maintain extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: PDD_maintain.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "D21FRR";
	String optionId = "PDD";

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
		transaction.setFieldValue("GZAB", "0000"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "000000"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "000"); // Account suffix (3A)
		transaction.setFieldValue("GZONAM", "Name"); // Originator's name (20A)
		transaction.setFieldValue("GZOID", "Identifier"); // Originator's identifier (10A)
		transaction.setFieldValue("GZOREF", "CRG-XX"); // DD originator's reference (20A)
		transaction.setFieldValue("GZPCDE", "999"); // Payment code (3A)
		transaction.setFieldValue("GZREF", "0000000000000000"); // Internal reference (16A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0543"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "123467"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "009"); // Account suffix (3A)
		transaction.setFieldValue("GZONAM", "Name"); // Originator's name (20A)
		transaction.setFieldValue("GZOID", "Identifier"); // Originator's identifier (10A)
		transaction.setFieldValue("GZOREF", "CRG-25"); // DD originator's reference (20A)
		transaction.setFieldValue("GZPCDE", "001"); // Payment code (3A)

		String reference = TestEnvironment.getTestEnvironment().getParameter(optionId);
		if (reference != null)
		{
			transaction.setFieldValue("GZREF", reference); // Internal reference (16A)
		}
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTAMT", "15000"); // Transaction amount (15P,0)
		transaction.setFieldValue("GZAAMT", "15000"); // Account/posting amount (15P,0)
		transaction.setFieldValue("GZVDTE", "1000106"); // Value from date
	}

}
