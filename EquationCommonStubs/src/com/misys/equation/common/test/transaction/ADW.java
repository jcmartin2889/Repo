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
 * Equation test cases for Add Drawdown
 */
public class ADW extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ADW.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G36ARR";
	String optionId = "ADW";

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
		transaction.setFieldValue("GZDLP", "CDQ"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "CRG-CDQ-00004"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTDT", "D"); // Term deal type; L=Loan, D=Deposit (1A)
		transaction.setFieldValue("GZAPP", "CL"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		transaction.setFieldValue("GZCUS", "GLOBAL"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", ""); // Customer location (3A)
		transaction.setFieldValue("GZCMR", "CMD001"); // Commitment reference (13A)
		transaction.setFieldValue("GZDLA", "1200000"); // Deal amount (15P,0)
		transaction.setFieldValue("GZCCY", "USD"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZCLT", "N"); // Collateral required? (1A)
		transaction.setFieldValue("GZSDT", "1000107"); // Start date (7S,0)
		transaction.setFieldValue("GZMDT", "1000108"); // Maturity date (7S,0)
		transaction.setFieldValue("GZCTRD", "1000105"); // Contract date (7S,0)

		transaction.setFieldValue("GZRPYM", "2"); // Repayment method (1A)
		transaction.setFieldValue("GZDDC", "N"); // Separate DD claims? (1A)
		transaction.setFieldValue("GZRAT", "1"); // Actual rate (11P,7)
		transaction.setFieldValue("GZIDB", "0"); // Interest days basis (1A)

	}

}
