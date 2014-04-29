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

/*
 * Equation test cases for Add Retail Deposit
 */
public class AddTest_RDU_2 extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AddTest_RDU_2.java 5063 2009-10-06 16:03:45Z macdonp1 $";
	String programName = "G36ARR";
	String optionId = "RDU";

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
	  transaction.setFieldValue("GZDLP", "BO1"); // Deal type (3A)
	  transaction.setFieldValue("GZDLR", "JC4-RAF-07"); // Deal reference (13A)
	  transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
	 }

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTDT", "D"); // Term deal type; L=Loan, D=Deposit (1A)
		transaction.setFieldValue("GZAPP", "MM"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		transaction.setFieldValue("GZCUS", "BBI"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", ""); // Customer location (3A)
		transaction.setFieldValue("GZDLA", "500000"); // Deal amount (15P,0)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZSDT", "0991231"); // Start date (7S,0)
		transaction.setFieldValue("GZMDT", "1001229"); // Maturity date (7S,0)
		transaction.setFieldValue("GZCTRD", "0991231"); // Contract date (7S,0)

		transaction.setFieldValue("GZRAT", "8.975"); // Interest rate (11P,7)
		transaction.setFieldValue("GZIDB", "0"); // Interest days basis (1A)
		transaction.setFieldValue("GZNRD", "1001229"); // Next rollover date (7S,0)

		transaction.setFieldValue("GZYSSI", "N"); // Deal has standard settlement instructions? (1A)

		transaction.setFieldValue("GZPRC", "P8  "); // Period code (4A)
		transaction.setFieldValue("GZYROL", "A"); // Are rollovers allowed for deals of this type? (1A)
		transaction.setFieldValue("GZPRR", "Y"); // Print renewal reminder? (1A)
		transaction.setFieldValue("GZPRS", "Y"); // Print renewal statement? (1A)

		transaction.setFieldValue("GZRPYM", "2"); // Repayment method (1A)

		transaction.setFieldValue("GZBFRQ", "L31"); // Bonus frequency (3A)
		transaction.setFieldValue("GZBDAT", "9999999"); // Bonus date (7S,0)
		transaction.setFieldValue("GZBRAT", "5.0000000"); // Bonus rate (11P,7)
		transaction.setFieldValue("GZFFRQ", "R31"); // Funding frequency (3A)
		transaction.setFieldValue("GZFDAT", "1000630"); // Funding date (7S,0)
		transaction.setFieldValue("GZFAMT", "500000"); // Funding amount (15P,0)
		transaction.setFieldValue("GZFMET", "2"); // Method (1A)

		transaction.setFieldValue("GZSAV", "Y"); // Savings rules? (1A)
		transaction.setFieldValue("GZSMP", "N"); // Simple deposit? (1A)
		transaction.setFieldValue("GZBON", "Y"); // Bonus rules? (1A)
		transaction.setFieldValue("GZFND", "Y"); // Funding rules? (1A)
		transaction.setFieldValue("GZWDL", "N"); // Withdrawal rules? (1A)
		transaction.setFieldValue("GZDRF", "N"); // Defer tax to mat? (1A)
		transaction.setFieldValue("GZTAXM", "1"); // Tax method (1A)
		transaction.setFieldValue("GZTADT", "1"); // Tax advice type (1A)
		transaction.setFieldValue("GZUTRM", "N"); // Unknown term? (1A)
	}

}
