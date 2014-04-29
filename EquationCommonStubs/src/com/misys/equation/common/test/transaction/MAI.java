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
 * Equation test cases for Maintain function
 */
public class MAI extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MAI.java 4721 2009-09-15 11:14:17Z weddelc1 $";
	String programName = "K87MRR";
	String optionId = "MAI";

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

	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{

	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBKNO", "111111111"); // Bank number (9A)
		transaction.setFieldValue("GZABD", "N"); // Account balance download? (1A)
		transaction.setFieldValue("GZRER", "N"); // Replace existing renewal records? (1A)
		transaction.setFieldValue("GZTRD", "Y"); // Transaction download? (1A)
	}
	@Override
	public void test00100Maint_Validate_NonExistingRecord() throws Exception
	{
		// This function has no key
	}
	@Override
	public void test00200Maint_Retrieval_NonExistingRecord() throws Exception
	{
		// This function has no key
	}
	@Override
	public void test00300Maint_NonExistingRecord() throws Exception
	{
		// This function has no key
	}

}