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
 * Equation test cases for Load Branch Calendars
 */

public class LBC extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LBC.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "C91ARR";
	String optionId = "C2L";

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
		transaction.setFieldValue("GZLRD","N"); //LOCAL RUN DAY CALENDAR
		//transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZREG", ""); // Region code (3A)
		transaction.setFieldValue("GZALL", "N"); // All branches? (1A)
		transaction.setFieldValue("GZCCY", "USD"); // Currency mnemonic (3A)
		transaction.setFieldValue("GZDLRD","N");
		
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZMON", "Y"); // Calendar year 01 (2A)
		transaction.setFieldValue("GZTUE", "Y");
		transaction.setFieldValue("GZWED", "Y");
		transaction.setFieldValue("GZTHU", "Y");
		transaction.setFieldValue("GZFRI", "Y");
		transaction.setFieldValue("GZSAT", "Y");
		transaction.setFieldValue("GZSUN", "Y");
		//transaction.setFieldValue("GZYR02", "98"); // Calendar year 02 (2A)
		//transaction.setFieldValue("GZYR03", "00"); // Calendar year 03 (2A)
	}
	/**
	 * These tests make no sense for this function
	 */
	@Override
	public void test00400Add_DuplicateRecord() throws Exception
	{
	}
	@Override
	public void test00500Add_Validate_ExistingRecord() throws Exception
	{
	}

}
