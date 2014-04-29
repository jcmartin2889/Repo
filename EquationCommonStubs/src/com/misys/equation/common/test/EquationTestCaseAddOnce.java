/**
 * Copyright and all other intellectual property rights in this software, in any form, is vested in Misys International Banking
 * Systems Ltd ("Misys") or a related company.
 * 
 * This software may not be copied, amended, compiled, translated, or developed; or sold, leased, hired, rented, or disclosed to any
 * third party without the prior written consent of Misys.
 * 
 * Copyright Misys International Banking Systems Ltd, 1975 and later
 */

package com.misys.equation.common.test;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.IEquationStandardObject;

/**
 * Test cases for Add function <br>
 * where the key fields are not dependent on other details<br>
 * 
 * Example are: OCA
 */
abstract public class EquationTestCaseAddOnce extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationTestCaseAddOnce.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	// ------------------------------------------------------------------------ Abstract methods
	abstract public void setupNonExistKeyFields(EquationStandardTransaction transaction);
	abstract public void setupAddFields(EquationStandardTransaction transaction);
	abstract public EquationStandardTransaction getTransaction() throws Exception;
	boolean isDelayExecuted = false;

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		
		if (!isDelayExecuted)
		{

			Thread.sleep(1500);
			isDelayExecuted = true;
		}
	}

	// ------------------------------------------------------------------------ Test cases

	/**
	 * Test: validate mode: non-existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void test00100Add_Validate_NonExistingRecord() throws Exception
	{
		System.out.println("test00100Add_Validate_NonExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_ADD);
		setupNonExistKeyFields(transaction);
		setupAddFields(transaction);
		applyValidate(transaction, true);
	}

	/**
	 * Test: validate mode: non-existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void xxxtest00200Add_Retrieve_NonExistingRecord() throws Exception
	{
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_ADD);
		setupNonExistKeyFields(transaction);
		setupAddFields(transaction);
		applyRetrieval(transaction, true);
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

	/**
	 * Test: validate mode: existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void test00500Add_Validate_ExistingRecord() throws Exception
	{
		System.out.println("test00500Add_Validate_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_ADD);
		setupNonExistKeyFields(transaction);
		setupAddFields(transaction);
		applyValidate(transaction, false);
	}

	/**
	 * Test: retrieve mode: existing record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	public void xxxtest00600Add_Retrieve_ExistingRecord() throws Exception
	{
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_ADD);
		setupNonExistKeyFields(transaction);
		applyRetrieval(transaction, true);
	}

}