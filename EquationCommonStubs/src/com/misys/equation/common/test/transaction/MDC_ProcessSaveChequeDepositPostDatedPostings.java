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
import com.misys.equation.common.test.TestEnvironment;

/**
 * Equation test cases for Maintain Cheque Deposit and Items
 */
public class MDC_ProcessSaveChequeDepositPostDatedPostings extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MDC_ProcessSaveChequeDepositPostDatedPostings.java 8426 2010-07-29 17:32:49Z CHALLIP1 $";
	String programName = "J49MRR";
	String optionId = "MDC"; //  
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
		transaction.setFieldValue("GZ9OLR", "X"); // Off-line deposit reference (12A)
		transaction.setFieldValue("GZ9MOD", "P"); // Process mode (1A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		String reference = TestEnvironment.getTestEnvironment().getParameter("Cheque_Deposit_Post_Dated_Suite");
		if (reference != null)
		{
			transaction.setFieldValue("GZ9OLR",reference); // Off-line deposit reference (12A)
		}
		else
		{
			transaction.setFieldValue("GZ9OLR", "054301050002"); // Transaction refer
			
		}
		////transaction.setFieldValue("GZ9CAB", "9132");         
		//transaction.setFieldValue("GZ9CAN", "666666");         
		//transaction.setFieldValue("GZ9CAS", "005");
		transaction.setFieldValue("GZ9MOD", "P"); // Process mode (1A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZ9MNT", "Y"); // Maintain deposit details? (1A)
		transaction.setFieldValue("GZ9ITM", "1"); // Number of items in deposit (3P,0)
		transaction.setFieldValue("GZ9AMT", "100000"); // Deposit amount (15P,0)
		transaction.setFieldValue("GZ9TCD", "515"); // Customer credit trancode (3A)
	}
	/**
	 * Test: retrieval mode: existing record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	@Override
	public void test00400Maint_Retrieval_ExistingRecord() throws Exception
	{
		// getTransaction() returns a new transaction.
		// Parent method copied here to be able to retrieve specific return field from API call!
		super.test00400Maint_Retrieval_ExistingRecord();
		TestEnvironment.getTestEnvironment().putParameter(optionId, currentTransaction.getFieldValue("GZ9DRF"));
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
		// This API closes an earlier opened Cheque Deposit.
		// This method cannot be used because the Cheque Deposit needs to be reopened
		// via another API before more processing can occur on the Cheque Deposit.
	}
}
