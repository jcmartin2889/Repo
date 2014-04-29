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
 * Equation test cases for Maintain Almonde Field Mapping
 */
public class MSD_2_Maintain extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MSD_2_Maintain.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "K42FRR";
	String optionId = "MSD";

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
		transaction.setFieldValue("GZOID", "MSD"); // Menu option id
		transaction.setFieldValue("GZAB", "0000"); // Instruction a/c branch
		transaction.setFieldValue("GZAN", "500003"); // Instruction a/c number
		transaction.setFieldValue("GZAS", "006"); // Instruction a/c suffix
		transaction.setFieldValue("GZPYT", "SO"); // Settlement type
		transaction.setFieldValue("GZREF", "TEST-MSD22"); // Settlement reference
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZOID", "MSD"); // Menu option id
		transaction.setFieldValue("GZAB", "1000"); // Instruction a/c branch
		transaction.setFieldValue("GZAN", "500003"); // Instruction a/c number
		transaction.setFieldValue("GZAS", "006"); // Instruction a/c suffix
		transaction.setFieldValue("GZPYT", "SO"); // Settlement type
		transaction.setFieldValue("GZREF", "TEST-MSD"); // Settlement reference
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTRM1", "SW"); // Receive transfer method
		transaction.setFieldValue("GZAB2", "0132"); // Pay a/c branch (4A)
		transaction.setFieldValue("GZAN2", "012008"); // Pay a/c number (6A)
		transaction.setFieldValue("GZAS2", "050"); // Pay a/c suffix (3A)
	}

}
