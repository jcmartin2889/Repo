package com.misys.equation.common.test;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.IEquationStandardObject;

/**
 * Test cases for Fully functional function
 */
abstract public class EquationTestCaseFullyList extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationTestCaseFullyList.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	protected boolean retrieveBeforeCancel = false;
	protected boolean retrieveHeader = false;

	// ------------------------------------------------------------------------ Abstract methods
	abstract public void setupNonExistKeyFields(EquationStandardTransaction transaction);
	abstract public void setupKeyFieldsAfterAdd(EquationStandardTransaction transaction);
	abstract public void setupHeaderKeyFields(EquationStandardTransaction transaction);
	abstract public void setupKeyFieldsAfterMaint(EquationStandardTransaction transaction);
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

		// retrieve Header record?
		if (retrieveHeader)
		{
			setupHeaderKeyFields(transaction);
			session.retrieveEquationTransaction(transaction);
		}

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

		// retrieve Header record?
		if (retrieveHeader)
		{
			setupHeaderKeyFields(transaction);
			session.retrieveEquationTransaction(transaction);
		}

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
		transaction.setMode(IEquationStandardObject.FCT_RTV);
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
		System.out.println("test00500Maint_NonExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_MNT);
		setupNonExistKeyFields(transaction);
		setupMaintFields(transaction);
		applyTransaction(transaction, false);
	}

	/**
	 * Test: validate mode: non-existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void test00600Cancel_Validate_NonExistingRecord() throws Exception
	{
		System.out.println("test00600Cancel_Validate_NonExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_DEL);
		setupNonExistKeyFields(transaction);

		// retrieve before cancelling?
		if (retrieveBeforeCancel)
		{
			setupAddFields(transaction);
		}

		applyValidate(transaction, false);
	}

	/**
	 * Test: retrieval mode: non-existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void test00700Cancel_Retrieval_NonExistingRecord() throws Exception
	{
		System.out.println("test00700Cancel_Retrieval_NonExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_RTV);
		setupNonExistKeyFields(transaction);
		applyRetrieval(transaction, false);
	}

	/**
	 * Test: maintain mode: non-existing record<br>
	 * Expected result: Fail<br>
	 * 
	 * @throws Exception
	 */
	public void test00800Cancel_NonExistingRecord() throws Exception
	{
		System.out.println("test00800Cancel_NonExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_DEL);
		setupNonExistKeyFields(transaction);
		applyTransaction(transaction, false);
	}

	/**
	 * Test: add mode: new record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	public void test00900Add_NewRecord() throws Exception
	{
		System.out.println("test00900Add_NewRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_ADD);

		// retrieve Header record?
		if (retrieveHeader)
		{
			setupHeaderKeyFields(transaction);
			session.retrieveEquationTransaction(transaction);
		}

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
	public void test01100Add_DuplicateRecord() throws Exception
	{
		System.out.println("test01100Add_DuplicateRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_ADD);

		// retrieve Header record?
		if (retrieveHeader)
		{
			setupHeaderKeyFields(transaction);
			session.retrieveEquationTransaction(transaction);
		}

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
	public void test01200Add_Validate_ExistingRecord() throws Exception
	{
		System.out.println("test01200Add_Validate_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_ADD);

		// retrieve Header record?
		if (retrieveHeader)
		{
			setupHeaderKeyFields(transaction);
			session.retrieveEquationTransaction(transaction);
		}

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
	public void test01300Add_Retrieve_ExistingRecord() throws Exception
	{
		System.out.println("test01300Add_Retrieve_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_ADD);
		setupKeyFieldsAfterAdd(transaction);
		applyRetrieval(transaction, true);
	}

	/**
	 * Test: retrieval mode: existing record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	public void test01400Maint_Retrieval_ExistingRecord() throws Exception
	{
		System.out.println("test01400Maint_Retrieval_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_RTV);
		setupKeyFieldsAfterAdd(transaction);
		applyRetrieval(transaction, true);
	}

	/**
	 * Test: validate mode: existing record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	public void test01500Maint_Validate_ExistingRecord() throws Exception
	{
		System.out.println("test01500Maint_Validate_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_MNT);
		setupKeyFieldsAfterAdd(transaction);
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
	public void test01600Maint_ExistingRecord() throws Exception
	{
		System.out.println("test01600Maint_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_MNT);
		setupKeyFieldsAfterAdd(transaction);
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
	public void test016100Maint_RetrievalMaintain_ExistingRecord() throws Exception
	{
		System.out.println("test016100Maint_RetrievalMaintain_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_MNT);
		setupKeyFieldsAfterMaint(transaction);
		session.retrieveEquationTransaction(transaction);
		applyTransaction(transaction, true);
	}

	/**
	 * Test: retrieval mode: existing record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	public void test01700Cancel_Retrieval_ExistingRecord() throws Exception
	{
		System.out.println("test01700Cancel_Retrieval_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_RTV);
		setupKeyFieldsAfterMaint(transaction);
		applyRetrieval(transaction, true);
	}

	/**
	 * Test: validate mode: existing record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	public void test01800Cancel_Validate_ExistingRecord() throws Exception
	{
		System.out.println("test01800Cancel_Validate_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_DEL);
		setupKeyFieldsAfterMaint(transaction);

		// retrieve before cancelling?
		if (retrieveBeforeCancel)
		{
			session.retrieveEquationTransaction(transaction);
		}

		applyValidate(transaction, true);
	}

	/**
	 * Test: cancel mode: existing record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	public void test01900Cancel_ExistingRecord() throws Exception
	{
		System.out.println("test01900Cancel_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_DEL);
		setupKeyFieldsAfterMaint(transaction);

		// retrieve before cancelling?
		if (retrieveBeforeCancel)
		{
			session.retrieveEquationTransaction(transaction);
		}

		applyTransaction(transaction, true);
	}

	/**
	 * Test: validate mode: existing record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	public void test02000Cancel_Validate_ExistingRecord() throws Exception
	{
		System.out.println("test02000Cancel_Validate_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_DEL);
		setupKeyFieldsAfterMaint(transaction);

		// retrieve before cancelling?
		if (retrieveBeforeCancel)
		{
			setupAddFields(transaction);
		}

		applyValidate(transaction, false);
	}

}