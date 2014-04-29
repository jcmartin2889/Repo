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
import com.misys.equation.common.test.EquationTestCaseAddMaintain;

/**
 * Equation test cases for Add/Maintain Interest Profit Stats
 */
public class GPI extends EquationTestCaseAddMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GPI.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "U43FRR";
	String optionId = "GPI";

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
		transaction.setFieldValue("GZPDLP", "GDT"); // Deal type (3A)
		transaction.setFieldValue("GZBRNM", "ACC1"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZREF", "TEST18"); // User reference (10A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		// these can be considered key fields as they are required for maintenance
		transaction.setFieldValue("GZCUS", "ATLANT"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", "IND"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZINT", "10000"); // Ordinary amount in minor currency units (15P,0)
		transaction.setFieldValue("GZCS1", "0"); // Ordinary amount in minor currency units (15P,0)
		transaction.setFieldValue("GZCS2", "0"); // Ordinary amount in minor currency units (15P,0)
		transaction.setFieldValue("GZREA", "N"); // Yes/no indicator? (1A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZINT", "55500"); // Ordinary amount in minor currency units (15P,0)
		transaction.setFieldValue("GZREA", "Y"); // Yes/no indicator? (1A)
	}

}
