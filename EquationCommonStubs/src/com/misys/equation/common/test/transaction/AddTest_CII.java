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
import com.misys.equation.common.test.EquationTestCaseFullyAdd;

/**
 * Equation test cases for Maintain Additional Info Items
 */
public class AddTest_CII extends EquationTestCaseFullyAdd
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AddTest_CII.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "E09FRR";
	String optionId = "CII";

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
		transaction.setFieldValue("GZEQF", "AC"); // Equation file
		transaction.setFieldValue("GZCITM", "CARL"); // Item name
		transaction.setFieldValue("GZDTCO", "AL"); // Data type code
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDES", "Carl's Item"); // Item description
		transaction.setFieldValue("GZFLEN", "10"); // Screen field length
		transaction.setFieldValue("GZMOOP", "O"); // Mandatory or optional?
		transaction.setFieldValue("GZUNID", "N"); // Unique customer identifier
	}

}
