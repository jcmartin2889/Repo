package com.misys.equation.common.test;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.IEquationStandardObject;

/**
 * Test cases for Cancel function
 */
abstract public class EquationTestCaseCancel extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationTestCaseCancel.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	protected boolean retrieveBeforeCancel = false;

	// ------------------------------------------------------------------------ Abstract methods
	abstract public void setupExistKeyFields(EquationStandardTransaction transaction);
	abstract public void setupNonExistKeyFields(EquationStandardTransaction transaction);
	abstract public EquationStandardTransaction getTransaction() throws Exception;

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
	}

	// ------------------------------------------------------------------------ Test cases

	/**
	 * Test: validate mode: non-existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void test00100Cancel_Validate_NonExistingRecord() throws Exception
	{
		System.out.println("test00100Cancel_Validate_NonExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_DEL);
		setupNonExistKeyFields(transaction);

		// retrieve before cancelling?
		if (retrieveBeforeCancel)
		{
			session.retrieveEquationTransaction(transaction);
		}

		applyValidate(transaction, false);
	}

	/**
	 * Test: retrieval mode: non-existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void test00200Cancel_Retrieval_NonExistingRecord() throws Exception
	{
		System.out.println("test00200Cancel_Retrieval_NonExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_DEL);
		setupNonExistKeyFields(transaction);
		applyRetrieval(transaction, false);
	}

	/**
	 * Test: maintain mode: non-existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void test00300Cancel_NonExistingRecord() throws Exception
	{
		System.out.println("test00300Cancel_NonExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_DEL);
		setupNonExistKeyFields(transaction);
		applyTransaction(transaction, false);
	}

	/**
	 * Test: retrieval mode: existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void test00400Cancel_Retrieval_ExistingRecord() throws Exception
	{
		System.out.println("test00400Cancel_Retrieval_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_DEL);
		setupExistKeyFields(transaction);
		applyRetrieval(transaction, true);
	}

	/**
	 * Test: validate mode: existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void test00500Cancel_Validate_ExistingRecord() throws Exception
	{
		System.out.println("test00500Cancel_Validate_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_DEL);
		setupExistKeyFields(transaction);

		// retrieve before cancelling?
		if (retrieveBeforeCancel)
		{
			session.retrieveEquationTransaction(transaction);
		}

		applyValidate(transaction, true);
	}

	/**
	 * Test: maintain mode: non-existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void test00600Cancel_ExistingRecord() throws Exception
	{
		System.out.println("test00600Cancel_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode("B");
		setupExistKeyFields(transaction);

		// retrieve before cancelling?
		if (retrieveBeforeCancel)
		{
			session.retrieveEquationTransaction(transaction);
		}

		transaction.setMode(IEquationStandardObject.FCT_DEL);
		applyTransaction(transaction, true);
	}

	/**
	 * Test: validate mode: existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void test00600Cancel_Validate_ExistingRecord() throws Exception
	{
		System.out.println("test00600Cancel_Validate_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_DEL);
		setupExistKeyFields(transaction);

		// retrieve before cancelling?
		if (retrieveBeforeCancel)
		{
			session.retrieveEquationTransaction(transaction);
		}

		applyValidate(transaction, false);
	}

}
