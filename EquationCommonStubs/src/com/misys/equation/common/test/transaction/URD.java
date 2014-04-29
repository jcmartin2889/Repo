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
 * Equation test cases for Maintain function
 */
public class URD extends EquationTestCaseAddMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: URD.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "R04FRR";
	String optionId = "URD";

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
		transaction.setFieldValue("GZRID", "RJL506"); // Report identifier (10A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZRPD", "test"); // Report description (35A)
		transaction.setFieldValue("GZPGM1", "A24ARPR"); // Program name (10A)
		transaction.setFieldValue("GZPFL", "A24RPP"); // Printer file name (10A)
		transaction.setFieldValue("GZYDAY", "N"); // Can the report be run during DAY? (1A)
		transaction.setFieldValue("GZYEDR", "Y"); // Can the report be run during End of Day reports? (1A)
		transaction.setFieldValue("GZYEDU", "N"); // Can the report be run during update phases? (1A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZRPD", "Equation"); // Report description (35A)
		transaction.setFieldValue("GZPGM1", "A53ARPR"); // Program name (10A)
		transaction.setFieldValue("GZYDAY", "Y"); // Can the report be run during DAY? (1A)
		transaction.setFieldValue("GZYEDU", "Y"); // Can the report be run during update phases? (1A)
	}

}
