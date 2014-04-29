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
public class CBI extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CBI.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "J72ARR";
	String optionId = "CBI";

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
		transaction.setFieldValue("GZABC", "0000"); // Branch Number
		transaction.setFieldValue("GZANC", "000001"); // Customer Number
		transaction.setFieldValue("GZASC", "001"); // Account Suffix
		transaction.setFieldValue("GZDISS", "991231"); // Date issued
		transaction.setFieldValue("GZFIRS", "1"); // First serial number
		transaction.setFieldValue("GZCBTC", "SPL"); // Cheque book type code
		transaction.setFieldValue("GZCHIB", "25"); // Number of cheques

	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCCD", "EN"); // Charge code
		transaction.setFieldValue("GZCHAR", "100"); // Charge for cheque book
		transaction.setFieldValue("GZTAXD", "50"); // Tax or duty on the charge

	}

}
