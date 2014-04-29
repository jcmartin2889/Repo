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
 * Equation test cases for Add/Maintain Ccy Denominations
 */
public class AddTest_J10 extends EquationTestCaseFullyAdd
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AddTest_J10.java 4721 2009-09-15 11:14:17Z weddelc1 $";
	String programName = "I13FRR";
	String optionId = "J10";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
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
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZND01", "1"); // 1st note den. value
		transaction.setFieldValue("GZNS01", "002"); // 1st note a/c suffix
		transaction.setFieldValue("GZNX01", "Carl's Currency Denomination"); // 1st note den. desc.
		transaction.setFieldValue("GZNW01", "N"); // 1st note withdrawn
		transaction.setFieldValue("GZNW02", "N"); // 2nd note withdrawn
		transaction.setFieldValue("GZNW03", "N"); // 3rd note withdrawn
		transaction.setFieldValue("GZNW04", "N"); // 4th note withdrawn
		transaction.setFieldValue("GZNW05", "N"); // 5th note withdrawn
		transaction.setFieldValue("GZNW06", "N"); // 6th note withdrawn
		transaction.setFieldValue("GZNW07", "N"); // 7th note withdrawn
		transaction.setFieldValue("GZNW08", "N"); // 8th note withdrawn
		transaction.setFieldValue("GZNW09", "N"); // 9th note withdrawn
		transaction.setFieldValue("GZNW10", "N"); // 10th note withdrawn
		transaction.setFieldValue("GZNW11", "N"); // 11th note withdrawn
		transaction.setFieldValue("GZNW12", "N"); // 12th note withdrawn
		transaction.setFieldValue("GZNW13", "N"); // 13th note withdrawn
		transaction.setFieldValue("GZNW14", "N"); // 14th note withdrawn
		transaction.setFieldValue("GZNW15", "N"); // 15th note withdrawn
		transaction.setFieldValue("GZNW16", "N"); // 16th note withdrawn
		transaction.setFieldValue("GZNW17", "N"); // 17th note withdrawn
		transaction.setFieldValue("GZNW18", "N"); // 18th note withdrawn
		transaction.setFieldValue("GZNW19", "N"); // 19th note withdrawn
		transaction.setFieldValue("GZNW20", "N"); // 20th note withdrawn
		transaction.setFieldValue("GZNW21", "N"); // 21st note withdrawn
		transaction.setFieldValue("GZNW22", "N"); // 22nd note withdrawn
		transaction.setFieldValue("GZNW23", "N"); // 23rd note withdrawn
		transaction.setFieldValue("GZNW24", "N"); // 24th note withdrawn
		transaction.setFieldValue("GZNW25", "N"); // 25th note withdrawn
		transaction.setFieldValue("GZNW26", "N"); // 26th note withdrawn
		transaction.setFieldValue("GZNW27", "N"); // 27th note withdrawn
		transaction.setFieldValue("GZNW28", "N"); // 28th note withdrawn
		transaction.setFieldValue("GZCW01", "N"); // 1st coin withdrawn
		transaction.setFieldValue("GZCW02", "N"); // 2nd coin withdrawn
		transaction.setFieldValue("GZCW03", "N"); // 3rd coin withdrawn
		transaction.setFieldValue("GZCW04", "N"); // 4th coin withdrawn
		transaction.setFieldValue("GZCW05", "N"); // 5th coin withdrawn
		transaction.setFieldValue("GZCW06", "N"); // 6th coin withdrawn
		transaction.setFieldValue("GZCW07", "N"); // 7th coin withdrawn
		transaction.setFieldValue("GZCW08", "N"); // 8th coin withdrawn
		transaction.setFieldValue("GZCW09", "N"); // 9th coin withdrawn
		transaction.setFieldValue("GZCW10", "N"); // 10th coin withdrawn
		transaction.setFieldValue("GZCW11", "N"); // 11th coin withdrawn
		transaction.setFieldValue("GZCW12", "N"); // 12th coin withdrawn
		transaction.setFieldValue("GZCW13", "N"); // 13th coin withdrawn
		transaction.setFieldValue("GZCW14", "N"); // 14th coin withdrawn
		transaction.setFieldValue("GZCW15", "N"); // 15th coin withdrawn
		transaction.setFieldValue("GZCW16", "N"); // 16th coin withdrawn
		transaction.setFieldValue("GZCW17", "N"); // 17th coin withdrawn
		transaction.setFieldValue("GZCW18", "N"); // 18th coin withdrawn
		transaction.setFieldValue("GZCW19", "N"); // 19th coin withdrawn
		transaction.setFieldValue("GZCW20", "N"); // 20th coin withdrawn
		transaction.setFieldValue("GZCW21", "N"); // 21st coin withdrawn
		transaction.setFieldValue("GZCW22", "N"); // 22nd coin withdrawn
		transaction.setFieldValue("GZCW23", "N"); // 23rd coin withdrawn
		transaction.setFieldValue("GZCW24", "N"); // 24th coin withdrawn
		transaction.setFieldValue("GZCW25", "N"); // 25th coin withdrawn
		transaction.setFieldValue("GZCW26", "N"); // 26th coin withdrawn
		transaction.setFieldValue("GZCW27", "N"); // 27th coin withdrawn
		transaction.setFieldValue("GZCW28", "N"); // 28th coin withdrawn
	}

}
