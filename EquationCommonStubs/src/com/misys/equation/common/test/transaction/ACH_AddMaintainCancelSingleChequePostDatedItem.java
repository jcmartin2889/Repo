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
import com.misys.equation.common.test.EquationTestCaseAddOnce;
import com.misys.equation.common.test.TestEnvironment;

/**
 * Equation test cases for Add Cheque Deposit and Items
 */
public class ACH_AddMaintainCancelSingleChequePostDatedItem extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACH_AddMaintainCancelSingleChequePostDatedItem.java 8426 2010-07-29 17:32:49Z CHALLIP1 $";
	String programName = "J61FRR";
	String optionId = "ACH";

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
		String reference = TestEnvironment.getTestEnvironment().getParameter("Cheque_Deposit_Post_Dated_Suite");
		if (reference != null)
		{
			transaction.setFieldValue("GZ1OLR", reference); // Off-line deposit reference (12A)
		}
		else
		{
			transaction.setFieldValue("GZ1OLR", "054301050003"); // Transaction refer
		}
		transaction.setFieldValue("GZ1ITM", "1"); // Cheque item number (3P,0)
	}
	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZ1MNT", "Y"); // Maintain cheque details? (1A)
		transaction.setFieldValue("GZ1CS", "N"); // Processing cashier deposit? (1A)
		transaction.setFieldValue("GZ1PRC", "P"); // Process mode (1A)
		transaction.setFieldValue("GZ1TYP", "L"); // Cheque type (1A)
		transaction.setFieldValue("GZ1SRL", "1"); // Cheque serial number (16A)
		transaction.setFieldValue("GZ1AMT", "100000"); // Cheque amount (15P,0)
		transaction.setFieldValue("GZ1DAB", "0000"); // Drawn on account branch number (4A)
		transaction.setFieldValue("GZ1DAN", "500035"); // Drawn on account customer number (6A)
		transaction.setFieldValue("GZ1DAS", "101"); // Drawn on account suffix (3A)
		transaction.setFieldValue("GZ1RFR", "N"); // Referred? (1A)
		transaction.setFieldValue("GZ1AM1", "10000"); // Credit amount (15P,0)
		transaction.setFieldValue("GZ1CC1", "GBP"); // Credit currency (3A)
		transaction.setFieldValue("GZ1IBB", "ATLANT");
		transaction.setFieldValue("GZ1BBL","IND");

		transaction.setFieldValue("GZ1PDD", "Y"); // Post dated? (1A)
		transaction.setFieldValue("GZ1CDD", "1081231"); // Cheque due date (7S,0)
		transaction.setFieldValue("GZ1RFH", "C"); // Reason for holding cheque (1A)

	}
	/**
	 * Test: validate mode: existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	@Override
	public void test00500Add_Validate_ExistingRecord() throws Exception
	{
		// Do not do this test. Duplicate checking only done in update module.
		// Generated Item Number supplied in the key.
	}

}
