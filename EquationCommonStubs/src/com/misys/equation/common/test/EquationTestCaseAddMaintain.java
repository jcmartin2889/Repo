package com.misys.equation.common.test;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.IEquationStandardObject;

/**
 * Test cases for Fully functional function
 */
abstract public class EquationTestCaseAddMaintain extends EquationTestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationTestCaseAddMaintain.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	// ------------------------------------------------------------------------ Abstract methods
	abstract public void setupNonExistKeyFields(EquationStandardTransaction transaction);
	abstract public void setupAddFields(EquationStandardTransaction transaction);
	abstract public void setupMaintFields(EquationStandardTransaction transaction);
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
		applyValidate(transaction, true);
	}

	/**
	 * Test: validate mode: non-existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void test00200Add_Retrieve_NonExistingRecord() throws Exception
	{
		System.out.println("test00200Add_Retrieve_NonExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_ADD);
		setupNonExistKeyFields(transaction);
		applyRetrieval(transaction, false);
	}

	/**
	 * Test: validate mode: non-existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void test00300Maint_Validate_NonExistingRecord() throws Exception
	{
		System.out.println("test00300Maint_Validate_NonExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_MNT);
		setupNonExistKeyFields(transaction);
		setupMaintFields(transaction);
		applyValidate(transaction, false);
	}

	/**
	 * Test: retrieval mode: non-existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void test00400Maint_Retrieval_NonExistingRecord() throws Exception
	{
		System.out.println("test00400Maint_Retrieval_NonExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_MNT);
		setupNonExistKeyFields(transaction);
		applyRetrieval(transaction, false);
	}

	/**
	 * Test: maintain mode: non-existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void test00500Maint_NonExistingRecord() throws Exception
	{
		System.out.println("test00500Maint_NonExistingRecord())");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_MNT);
		setupNonExistKeyFields(transaction);
		setupMaintFields(transaction);
		applyTransaction(transaction, false);
	}

	/**
	 * Test: add mode: new record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	public void test00600Add_NewRecord() throws Exception
	{
		System.out.println("test00600Add_NewRecord()");
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
	public void test00700Add_DuplicateRecord() throws Exception
	{
		System.out.println("test00700Add_DuplicateRecord()");
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
	public void test00800Add_Validate_ExistingRecord() throws Exception
	{
		System.out.println("test00800Add_Validate_ExistingRecord()");
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
	public void test00900Add_Retrieve_ExistingRecord() throws Exception
	{
		System.out.println("test00900Add_Retrieve_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_ADD);
		setupNonExistKeyFields(transaction);
		applyRetrieval(transaction, true);
	}

	/**
	 * Test: retrieval mode: existing record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	public void test01000Maint_Retrieval_ExistingRecord() throws Exception
	{
		System.out.println("test00100Maint_Retrieval_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_MNT);
		setupNonExistKeyFields(transaction);
		applyRetrieval(transaction, true);
	}

	/**
	 * Test: validate mode: existing record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	public void test01100Maint_Validate_ExistingRecord() throws Exception
	{
		System.out.println("test01100Maint_Validate_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_MNT);
		setupNonExistKeyFields(transaction);
		session.retrieveEquationTransaction(transaction);
		setupMaintFields(transaction);
		applyValidate(transaction, true);
	}

	/**
	 * Test: maintain mode: existing record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	public void test01200Maint_ExistingRecord() throws Exception
	{
		System.out.println("test01200Maint_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_MNT);
		setupNonExistKeyFields(transaction);
		session.retrieveEquationTransaction(transaction);
		setupMaintFields(transaction);
		applyTransaction(transaction, true);
	}

	/**
	 * Test: retrieval and maintain: existing record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	public void test01300Maint_RetrievalMaintain_ExistingRecord() throws Exception
	{
		System.out.println("test01300Maint_RetrievalMaintain_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_MNT);
		setupNonExistKeyFields(transaction);
		session.retrieveEquationTransaction(transaction);
		applyTransaction(transaction, true);
	}
}