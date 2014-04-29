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
import com.misys.equation.common.test.EquationTestCaseAddMore;
import com.misys.equation.common.test.TestEnvironment;

/**
 * Equation test cases for Maintain function
 */
public class ADO_for_CHA extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ADO_for_CHA.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "Q71FRR";
	String optionId = "ADO";

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
		transaction.setFieldValue("GZCPNC", "123078"); // Customer number (6A)
		transaction.setFieldValue("GZCUS", "BBI"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", ""); // Customer location (3A)
		transaction.setFieldValue("GZLNP", "RL1"); // Loan type
		transaction.setFieldValue("GZBRNM", "XOND"); // Branch mnemonic
		String reference = TestEnvironment.getTestEnvironment().getParameter("RLA_for_CHA");
		transaction.setFieldValue("GZLNR", reference); // Loan reference
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		  //transaction.setFieldValue("GZCUS", "AALEE"); // Customer mnemonic (6A)
		  //transaction.setFieldValue("GZCLC", "K"); // Customer location (3A)
		  //transaction.setFieldValue("GZREF", "TEST"); // Originators reference (20A)
		  //transaction.setFieldValue("GZLNP", "CAP"); // Loan type (3A)
		  //transaction.setFieldValue("GZLNR", "TEST-99"); // Loan reference (13A)
		  //transaction.setFieldValue("GZBRNM", "ACC1"); // Branch mnemonic (4A)
		
		  transaction.setFieldValue("GZCUS", "EFCTES"); // Customer mnemonic (6A)
		  transaction.setFieldValue("GZCLC", "T"); // Customer location (3A)
		  transaction.setFieldValue("GZREF", "COA25.I0000000020111"); // Originators reference (20A)
		  transaction.setFieldValue("GZLNP", "COA"); // Loan type (3A)
		  transaction.setFieldValue("GZLNR", "25.I000000002"); // Loan reference (13A)
		  transaction.setFieldValue("GZBRNM", "CITY"); // Branch mnemonic (4A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZPSC", "123456"); // Payers bank sort code (9A)
		transaction.setFieldValue("GZPAN", "0987654321"); // Payers a/c at bank (34A)
		transaction.setFieldValue("GZPNM", "Mr Madeup"); // Payers account name (35A)
		transaction.setFieldValue("GZSTS", "1"); // Instruction status (1A)
		transaction.setFieldValue("GZCNDT", "0"); // Cancellation date (7S,0)
		// transaction.setField("GZREZN", ""); // Reason for cancel/hold (3A)
		transaction.setFieldValue("GZXPDT", "0"); // Expiry date (7S,0)
		transaction.setFieldValue("GZSNDT", "0"); // Date sent to payers bank (7S,0)
		transaction.setFieldValue("GZRCDT", "0"); // Date received back (7S,0)
	}

}
