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
 * Equation test cases for Maintain Warning Overrides
 */
public class MaintainTest_SOM extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MaintainTest_SOM.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "A10MRR";
	String optionId = "SOM";

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
		transaction.setFieldValue("GZMSG", "KSM0019"); // Message identifier
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAGXL", "20"); // Authority level
	}

	/**
	 * The purpose of MaintainTest_SOM.java is to change the authority level back to its original value, so disregard the following
	 * tests.
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