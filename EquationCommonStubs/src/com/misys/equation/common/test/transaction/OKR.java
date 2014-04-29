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
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.test.EquationTestCaseAddOnce;

/**
 * Equation test cases for Commercial Paper Cash Payment
 */
public class OKR extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OKR.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	String programName = "W71ARR";
	String optionId = "OKR";

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
		transaction.setFieldValue("GZBPN", "05436677"); // Paper Number
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
	}
	/**
	 * Test: add mode: new record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	public void test00300Add_NewRecord() throws Exception
	{
		System.out.println("test00300Add_NewRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_ADD);
		setupNonExistKeyFields(transaction);
		setupAddFields(transaction);
		applyTransaction(transaction, true);
	}

	/**
	 * Test: add mode: duplicate record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void test00400Add_DuplicateRecord() throws Exception
	{
		System.out.println("test00400Add_DuplicateRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_ADD);
		setupNonExistKeyFields(transaction);
		setupAddFields(transaction);
		applyTransaction(transaction, false);
	}

}
