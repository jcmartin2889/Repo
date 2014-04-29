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

/**
 * @author weddelc1
 */
public class AddTest_GXA extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AddTest_GXA.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "U33ARR";
	String optionId = "GXA";

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
		transaction.setFieldValue("GZPDLP", "EQ4"); // Primary deal type (3A)
		transaction.setFieldValue("GZDLR1", "TEST6"); // Primary deal ref (13A)
		transaction.setFieldValue("GZBRNM", "ACC1"); // Branch mnemonic (4A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCUS", "ABRAHA"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", "ISA"); // Customer location (3A)
		transaction.setFieldValue("GZPRM", "Y"); // Primary deal entry? (1A)
		transaction.setFieldValue("GZCTRD", "991231"); // Contract date (7S,0)
		transaction.setFieldValue("GZPCCY", "USD"); // Purchase currency (3A)
		transaction.setFieldValue("GZPAM", "200000"); // Purchase amount (15P,0)
		transaction.setFieldValue("GZSCCY", "GBP"); // Sale currency (3A)
		transaction.setFieldValue("GZSAM", "400000"); // Sale amount (15P,0)
		transaction.setFieldValue("GZEXT", "2.00000000"); // Exchange rate (15P,9)
		transaction.setFieldValue("GZACT", "Y"); // Allow A/C type override? (1A)
		transaction.setFieldValue("GZSACT", "G2"); // Sale a/c type (2A)
		transaction.setFieldValue("GZPACT", "G2"); // Purchase a/c type (2A)
	}

}
