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
import com.misys.equation.common.test.EquationTestCaseCancel;

/**
 * Equation test cases for Maintain function
 */
public class CRE extends EquationTestCaseCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CRE.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H33CRR";
	String optionId = "CRE";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		retrieveBeforeCancel = true;
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
		transaction.setFieldValue("GZBRNM", "XXXX"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZDLP", "YYY"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "ZZZZZZZZZZZZZ"); // Deal reference (13A)
		transaction.setFieldValue("GZNSR", "1"); // Number of scheduled repayments (3P,0)
		transaction.setFieldValue("GZD001", "1000110"); // Scheduled date 2 (7S,0)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZDLP", "CDQ"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "CRG-CDQ-00003"); // Deal reference (13A)
		transaction.setFieldValue("GZNSR", "3"); // Number of scheduled repayments (3P,0)
		transaction.setFieldValue("GZD001", "1000110"); // Scheduled date 2 (7S,0)
		transaction.setFieldValue("GZD002", "1000210"); // Scheduled date 2 (7S,0)
		transaction.setFieldValue("GZD003", "1000310"); // Scheduled date 2 (7S,0)
	}

}
