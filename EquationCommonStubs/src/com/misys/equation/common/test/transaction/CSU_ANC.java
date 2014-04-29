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
 * Equation test cases for Add New Customer Basic Details
 * 
 * Note: To Add / Maintain Customer Extended Details (German Features) use ANX (V30F) function after ANC
 */
public class CSU_ANC extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CSU_ANC.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G01ARR";
	String optionId = "ANC";

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
		transaction.setFieldValue("GZCUS", "ELWCSU"); // Customer mnemonic
		transaction.setFieldValue("GZCLC", "001"); // customer location
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCTP", "EA"); // type
		transaction.setFieldValue("GZCPNC", "100231"); // customer number
		transaction.setFieldValue("GZCUN", "Full name"); // full name
		transaction.setFieldValue("GZDAS", "SHORT"); // short name
		transaction.setFieldValue("GZBRNM", "LOND"); // Default branch
		transaction.setFieldValue("GZCRB1", "00"); // Tax reference 1
		transaction.setFieldValue("GZCRB2", "00"); // Tax reference 2
		transaction.setFieldValue("GZCS", "Y"); // Consolidated Statements 1
	}

}
