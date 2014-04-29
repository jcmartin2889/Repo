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
public class MTP extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MTP.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "A94FRR";
	String optionId = "MTP";

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
		transaction.setFieldValue("GZAPIQ", "ANOTHER QUEUE 0001 "); // API queue (48A)
		// transaction.setFieldValue("GZDES", ""); // API Description (35A)
		// transaction.setFieldValue("GZUID", ""); // Unique Identifier (3A)
		transaction.setFieldValue("GZDES", "Desc1"); // API Description (35A)
		transaction.setFieldValue("GZUID", "AQ1"); // Unique Identifier (3A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		// transaction.setFieldValue("GZAPIQ", ""); // API queue (48A)
		transaction.setFieldValue("GZDES", "Desc1"); // API Description (35A)
		transaction.setFieldValue("GZUID", "AQ1"); // Unique Identifier (3A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		// transaction.setFieldValue("GZAPIQ", ""); // API queue (48A)
		transaction.setFieldValue("GZDES", "Desc2"); // API Description (35A)
		transaction.setFieldValue("GZUID", "AQ2"); // Unique Identifier (3A)
	}

}
