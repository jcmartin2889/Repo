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
public class MNR extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MNR.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "B10FRR";
	String optionId = "MNR";

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
		transaction.setFieldValue("GZACT", "NS"); // Account type (2A)
		transaction.setFieldValue("GZCCY", "USD"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZEDT", "1000105"); // Effective date (7S,0)
		transaction.setFieldValue("GZCTP","EA"); //Customer Type
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZNPD", "1"); // Notice period (days) (3S,0)
		transaction.setFieldValue("GZNBT", "1"); // Non business days treatment (1A)
		transaction.setFieldValue("GZCHM", "1"); // Penalty charge method (1A)
		transaction.setFieldValue("GZCALP", "1"); // Penalty calculation (1A)
		transaction.setFieldValue("GZFWM", "1"); // Free withdrawal method (1A)
		transaction.setFieldValue("GZCCD", "AC"); // Charge code (2A)
		transaction.setFieldValue("GZCFQ", "V01"); // Calendar reset frequency (3A)
		transaction.setFieldValue("GZFDT", "1000101"); // Reset date (7S,0)
		transaction.setFieldValue("GZCHG", "Y"); // Apply changes today? (1A)
		transaction.setFieldValue("GZTXT", "N"); // Product desc exist? (1A)
		transaction.setFieldValue("GZINT", "Y"); // Interest withdrawals allowed (1A)
		transaction.setFieldValue("GZIN31", "N"); // Include 31st? (1A)
		transaction.setFieldValue("GZFBEF", "Y"); // Free before interest (1A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZNPD", "100"); // Notice period (days) (3S,0)
		transaction.setFieldValue("GZNBT", "1"); // Non business days treatment (1A)
		transaction.setFieldValue("GZCHM", "1"); // Penalty charge method (1A)
		transaction.setFieldValue("GZCALP", "1"); // Penalty calculation (1A)
		transaction.setFieldValue("GZFWM", "1"); // Free withdrawal method (1A)
		transaction.setFieldValue("GZCCD", "AC"); // Charge code (2A)
		transaction.setFieldValue("GZCFQ", "V01"); // Calendar reset frequency (3A)
		transaction.setFieldValue("GZFDT", "1000101"); // Reset date (7S,0)
		transaction.setFieldValue("GZCHG", "Y"); // Apply changes today? (1A)
		transaction.setFieldValue("GZTXT", "N"); // Product desc exist? (1A)
		transaction.setFieldValue("GZINT", "Y"); // Interest withdrawals allowed (1A)
		transaction.setFieldValue("GZIN31", "N"); // Include 31st? (1A)
		transaction.setFieldValue("GZFBEF", "Y"); // Free before interest (1A)
	}

}
