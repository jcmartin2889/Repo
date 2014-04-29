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
 * Equation test cases for Internal Account Special Conditions
 */
public class MaintainTest_ISC extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MaintainTest_ISC.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H35MRR";
	String optionId = "ISC";

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
		transaction.setFieldValue("GZAB", "0000"); // Account branch
		transaction.setFieldValue("GZAN", "800008"); // Basic part of account number
		transaction.setFieldValue("GZAS", "001"); // Account suffix
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction
						.setFieldValue(
										"GZSCM",
										"NNNNNYYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN"); // Special
		// condition
		// maintained?
		transaction.setFieldValue("GZINT", "Y"); // Internal account?
	}

	/**
	 * The purpose of MaintainTest_ISC.java is to activate "CALCULATE DEBIT INT" and "CALCULATE CREDIT INT", to be used by MIX (Post
	 * Manual Interest - Internal A/C) later on so the following tests are not needed.
	 */
	@Override
	public void test00100Maint_Validate_NonExistingRecord() throws Exception
	{
	}
	@Override
	public void test00200Maint_Retrieval_NonExistingRecord() throws Exception
	{
	}
	@Override
	public void test00300Maint_NonExistingRecord() throws Exception
	{
	}
	@Override
	public void test00400Maint_Retrieval_ExistingRecord() throws Exception
	{
	}
	@Override
	public void test00500Maint_Validate_ExistingRecord() throws Exception
	{
	}
	@Override
	public void test00700Maint_RetrievalMaintain_ExistingRecord() throws Exception
	{
	}
}
