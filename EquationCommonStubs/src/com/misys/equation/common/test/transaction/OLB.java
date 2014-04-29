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
 * Equation test cases for Maintain function Maintain Commercial Paper Items (F18=Log follow-on from OKL)
 */
public class OLB extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OLB.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	String programName = "W28DRR";
	String optionId = "OLB";

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
		transaction.setFieldValue("GZFLDN", "10000008"); // Folder No (8A)
		//transaction.setFieldValue("GZRVAU", "AU"); // Mode (2A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZFLDN", "20000009"); // Folder No (8A)
		transaction.setFieldValue("GZRVAU", "LG"); // Mode (2A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{

	}

	/**
	 * Test: retrieval mode: existing record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	@Override
	public void test00700Maint_RetrievalMaintain_ExistingRecord() throws Exception
	{
		
		// Not a valid test because it is not possible to 'Log'
		// > 1 time. Otherwise you will get KSM6646 Invalid status error.
	}
	
	@Override
	public void test00600Maint_ExistingRecord() throws Exception
	{
		
		// Not a valid test because it is not possible to 'Log'
		// > 1 time. Otherwise you will get KSM6646 Invalid status error.
	}
	
	@Override
	public void test00200Maint_Retrieval_NonExistingRecord() throws Exception
	{

	}
	
	@Override
	public void test00100Maint_Validate_NonExistingRecord() throws Exception
	{

	}
}
