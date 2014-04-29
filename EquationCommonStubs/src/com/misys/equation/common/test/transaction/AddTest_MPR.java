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
 * Equation test cases for Maintain function
 */
public class AddTest_MPR extends EquationTestCaseFullyAdd
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AddTest_MPR.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "M22FRR";
	String optionId = "MPR";

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
		transaction.setFieldValue("GZREF", "ISSUEPORT"); // Portfolio reference
		transaction.setFieldValue("GZSMN", "ISSUESEC"); // Security mnemonic
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAVP", "N"); // Average price
		transaction.setFieldValue("GZBBNS", "9132"); // Trade a/c - branch no. 2
		transaction.setFieldValue("GZBNOS", "234567"); // Trade a/c - bank no. 2
		transaction.setFieldValue("GZSFXS", "000"); // Trade account suffix 2
		transaction.setFieldValue("GZAMD", "SL"); // Amortisation method
	}

}
