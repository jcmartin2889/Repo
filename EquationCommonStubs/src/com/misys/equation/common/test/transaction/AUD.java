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
public class AUD extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AUD.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "Q91FRR";
	String optionId = "AUD";

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
		transaction.setFieldValue("GZCPNC", "350003"); // Customer mnemonic (6A)
		//transaction.setFieldValue("GZCLC", ""); // Customer location (3A)
		transaction.setFieldValue("GZBRNM", "RLRL"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZLNP", "XG2"); // Loan type (3A)
		//String reference = TestEnvironment.getTestEnvironment().getParameter("RLA");
		transaction.setFieldValue("GZLNR", "MAN1-BACKFUT"); // Loan reference (13A)
		transaction.setFieldValue("GZREF", "XG2MAN1-BACKFUT 01"); // Originators reference (20A)
		transaction.setFieldValue("GZCDTE", "990630"); // Repayment due date (7S,0)
		transaction.setFieldValue("GZCLTP", "P"); // Claim type (1A)
		transaction.setFieldValue("GZAMT", "1000"); // Repayment amount (15P,0)
		/*
		 * Sequence number may be required if more than one DD matches all of the rest of the key fields
		 */
		// transaction.setField("GZSEQN", ""); // Sequence number (2P,0)
	}
	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZRTDT", "1000105"); // Date returned (7S,0)
		transaction.setFieldValue("GZREZN", "WAF"); // Reason for return (3A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZRTDT", "1000105"); // Date returned (7S,0)
		transaction.setFieldValue("GZREZN", "WAF"); // Reason for return (3A)
		transaction.setFieldValue("GZRTR", "N"); // Retry flag (1A)
		transaction.setFieldValue("GZSUS", "N"); // Suspend flag (1A)
		// transaction.setField("GZCCDE", "AD"); // Charge code (2A)
		// transaction.setField("GZCAMT", "500"); // Charge amount (15P,0)
		// transaction.setField("GZCAB", "0543"); // Charge receivable a/c branch (4A)
		// transaction.setField("GZCAN", "123107"); // Charge receivable a/c number (6A)
		// transaction.setField("GZCAS", "001"); // Charge receivable a/c suffix (3A)
		transaction.setFieldValue("GZCANC", "N"); // Cancelled? (1A)
	}

}
