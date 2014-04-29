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
 * Equation test cases for Cancel Repayment Transaction
 */
public class TRC_Part1 extends EquationTestCaseCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TRC_Part1.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H34CRR";
	String optionId = "TRC";

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
	 * Setup a non-existing key fields
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDLP", "XXX"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "COA10"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "CUTE"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZDTE", "0990830"); // Repayment due date (7S,0)
		transaction.setFieldValue("GZPIF", "P"); // Repayment type; I=Interest, P=Principal, B=Both (1A)
		transaction.setFieldValue("GZVF1", "0990830"); // Principal repayment date (7S,0)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDLP", "COA"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "COA19"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "CUTE"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZDTE", "0990831"); // Repayment due date (7S,0)
		transaction.setFieldValue("GZPIF", "P"); // Repayment type; I=Interest, P=Principal, B=Both (1A)
		transaction.setFieldValue("GZVF1", "0990831"); // Principal repayment date (7S,0)
	}

	/*
	 * public void test00600Cancel_Validate_ExistingRecord() throws Exception { }
	 */
}
