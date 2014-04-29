package com.misys.equation.common.test;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.IEquationStandardObject;

/**
 * Test cases for Add function <br>
 * where the key screen contains non-key fields
 * 
 * Example are: RLA
 */
abstract public class EquationTestCaseAddCombi extends EquationTestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationTestCaseAddCombi.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	// ------------------------------------------------------------------------ Abstract methods
	abstract public void setupNonExistKeyFields(EquationStandardTransaction transaction);
	abstract public void setupAddFields(EquationStandardTransaction transaction);
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
	public void test00100Add_Validate_NonExistingRecord() throws Exception
	{
		System.out.println("test00100Add_Validate_NonExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_ADD);
		setupNonExistKeyFields(transaction);
		setupAddFields(transaction);
		applyValidate(transaction, false);
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
		applyRetrieval(transaction, false);
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
	 * Test: validate mode: existing record<br>
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
