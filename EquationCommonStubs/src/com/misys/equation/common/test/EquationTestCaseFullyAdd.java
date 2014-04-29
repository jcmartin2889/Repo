package com.misys.equation.common.test;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.IEquationStandardObject;

/**
 * Test cases for Fully functional function
 */
abstract public class EquationTestCaseFullyAdd extends EquationTestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationTestCaseFullyAdd.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
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
	 * Test: add mode: new record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	public void testAdd_NewRecord() throws Exception
	{
		System.out.println("testAdd_NewRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_ADD);
		setupNonExistKeyFields(transaction);
		setupAddFields(transaction);
		applyTransaction(transaction, true);
	}

}
