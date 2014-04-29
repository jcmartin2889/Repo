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
 * Equation test cases for Add/Maintain FX Profit Stats
 */
public class GPX extends EquationTestCaseAddMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GPX.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "U42FRR";
	String optionId = "GPX";

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
		transaction.setFieldValue("GZPDLP", "EQ4"); // Deal type (3A)
		transaction.setFieldValue("GZBRNM", "ACC1"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZREF", "TEST6"); // User reference (10A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZINAM", "500000"); // Ordinary amount in minor currency units (15P,0)
		transaction.setFieldValue("GZCS1", "0"); // Ordinary amount in minor currency units (15P,0)
		transaction.setFieldValue("GZCS2", "0"); // Ordinary amount in minor currency units (15P,0)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZINAM", "250000"); // Ordinary amount in minor currency units (15P,0)
	}

}
