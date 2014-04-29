package com.misys.equation.common.test.transaction.list;

import com.misys.equation.common.access.EquationStandardGSListTransaction;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.test.EquationTestCase;

/**
 * Test cases for Fully functional function
 */
abstract public class EquationTestCaseFullyList3 extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationTestCaseFullyList3.java 11502 2011-07-25 10:18:46Z challip1 $";
	protected boolean retrieveBeforeCancel = false;
	protected boolean retrieveHeader = false;

	// ------------------------------------------------------------------------ Abstract methods
	abstract public void setupNonExistKeyFields(EquationStandardGSListTransaction transaction);
	abstract public void setupKeyFieldsAfterAdd(EquationStandardGSListTransaction transaction);
	abstract public void setupHeaderKeyFields(EquationStandardGSListTransaction transaction);
	abstract public void setupKeyFieldsAfterMaint(EquationStandardGSListTransaction transaction);
	abstract public void setupAddFields(EquationStandardGSListTransaction transaction);
	abstract public void setupMaintFields(EquationStandardGSListTransaction transaction);
	abstract public EquationStandardGSListTransaction getTransaction() throws Exception;

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
	 * Return the Equation Standard Transaction
	 * 
	 * @param identifier
	 *            - session identifier
	 * 
	 * @return the Equation Standard Transaction
	 * 
	 * @throws Exception
	 */
	@Override
	protected EquationStandardGSListTransaction getEquationStandardTransaction(String identifier) throws Exception
	{
		return new EquationStandardGSListTransaction(identifier, session);
	}

	// /**
	// * Test: validate mode: non-existing record<br>
	// * Expected result: Fail<br>
	// *
	// * @throws Exception
	// */
	// public void test00100Add_Validate_NonExistingRecord() throws Exception
	// {
	// System.out.println("test00100Add_Validate_NonExistingRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_ADD);
	//
	// // retrieve Header record?
	// if (retrieveHeader)
	// {
	// setupHeaderKeyFields(transaction);
	// session.retrieveEquationTransaction(transaction);
	// }
	//
	// setupNonExistKeyFields(transaction);
	// setupAddFields(transaction);
	// applyValidate(transaction, true);
	// }
	//
	// /**
	// * Test: validate mode: non-existing record<br>
	// * Expected result: Fail<br>
	// *
	// * @throws Exception
	// */
	// public void test00200Add_Retrieve_NonExistingRecord() throws Exception
	// {
	// System.out.println("test00200Add_Retrieve_NonExistingRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_ADD);
	//
	// // retrieve Header record?
	// if (retrieveHeader)
	// {
	// setupHeaderKeyFields(transaction);
	// session.retrieveEquationTransaction(transaction);
	// }
	//
	// setupNonExistKeyFields(transaction);
	// applyRetrieval(transaction, false);
	// }
	//
	// /**
	// * Test: validate mode: non-existing record<br>
	// * Expected result: Fail<br>
	// *
	// * @throws Exception
	// */
	// public void test00300Maint_Validate_NonExistingRecord() throws Exception
	// {
	// System.out.println("test00300Maint_Validate_NonExistingRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_MNT);
	// setupNonExistKeyFields(transaction);
	// setupMaintFields(transaction);
	// applyValidate(transaction, false);
	// }
	//
	// /**
	// * Test: retrieval mode: non-existing record<br>
	// * Expected result: Fail<br>
	// *
	// * @throws Exception
	// */
	public void test00400Maint_Retrieval_NonExistingRecord() throws Exception
	{
	System.out.println("test00400Maint_Retrieval_ExistingRecord()");
	EquationStandardGSListTransaction transaction = getTransaction();
	transaction.setMode(EquationStandardTransaction.FCT_RTV);
	setupNonExistKeyFields(transaction);
	applyRetrieval(transaction, true);
	}
	//
	// /**
	// * Test: maintain mode: non-existing record<br>
	// * Expected result: Fail<br>
	// *
	// * @throws Exception
	// */
	// public void test00500Maint_NonExistingRecord() throws Exception
	// {
	// System.out.println("test00500Maint_NonExistingRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_MNT);
	// setupNonExistKeyFields(transaction);
	// setupMaintFields(transaction);
	// applyTransaction(transaction, false);
	// }
	//
	// /**
	// * Test: validate mode: non-existing record<br>
	// * Expected result: Fail<br>
	// *
	// * @throws Exception
	// */
	// public void test00600Cancel_Validate_NonExistingRecord() throws Exception
	// {
	// System.out.println("test00600Cancel_Validate_NonExistingRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_DEL);
	// setupNonExistKeyFields(transaction);
	//
	// // retrieve before cancelling?
	// if (retrieveBeforeCancel)
	// {
	// setupAddFields(transaction);
	// }
	//
	// applyValidate(transaction, false);
	// }
	//
	// /**
	// * Test: retrieval mode: non-existing record<br>
	// * Expected result: Fail<br>
	// *
	// * @throws Exception
	// */
	// public void test00700Cancel_Retrieval_NonExistingRecord() throws Exception
	// {
	// System.out.println("test00700Cancel_Retrieval_NonExistingRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_RTV);
	// setupNonExistKeyFields(transaction);
	// applyRetrieval(transaction, false);
	// }
	//
	// /**
	// * Test: maintain mode: non-existing record<br>
	// * Expected result: Fail<br>
	// *
	// * @throws Exception
	// */
	// public void test00800Cancel_NonExistingRecord() throws Exception
	// {
	// System.out.println("test00800Cancel_NonExistingRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_DEL);
	// setupNonExistKeyFields(transaction);
	// applyTransaction(transaction, false);
	// }
	//
	// /**
	// * Test: add mode: new record<br>
	// * Expected result: Success<br>
	// *
	// * @throws Exception
	// */
	// public void test00900Add_NewRecord() throws Exception
	// {
	// System.out.println("test00900Add_NewRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_ADD);
	//
	// // retrieve Header record?
	// if (retrieveHeader)
	// {
	// setupHeaderKeyFields(transaction);
	// session.retrieveEquationTransaction(transaction);
	// }
	//
	// setupNonExistKeyFields(transaction);
	// setupAddFields(transaction);
	// applyTransaction(transaction, true);
	// }
	//
	// /**
	// * Test: add mode: duplicate record<br>
	// * Expected result: Fail<br>
	// *
	// * @throws Exception
	// */
	// public void test01100Add_DuplicateRecord() throws Exception
	// {
	// System.out.println("test01100Add_DuplicateRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_ADD);
	//
	// // retrieve Header record?
	// if (retrieveHeader)
	// {
	// setupHeaderKeyFields(transaction);
	// session.retrieveEquationTransaction(transaction);
	// }
	//
	// setupNonExistKeyFields(transaction);
	// setupAddFields(transaction);
	// applyTransaction(transaction, false);
	// }
	//
	// /**
	// * Test: validate mode: existing record<br>
	// * Expected result: Fail<br>
	// *
	// * @throws Exception
	// */
	// public void test01200Add_Validate_ExistingRecord() throws Exception
	// {
	// System.out.println("test01200Add_Validate_ExistingRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_ADD);
	//
	// // retrieve Header record?
	// if (retrieveHeader)
	// {
	// setupHeaderKeyFields(transaction);
	// session.retrieveEquationTransaction(transaction);
	// }
	//
	// setupNonExistKeyFields(transaction);
	// setupAddFields(transaction);
	// applyValidate(transaction, false);
	// }
	//
	// /**
	// * Test: validate mode: existing record<br>
	// * Expected result: Success<br>
	// *
	// * @throws Exception
	// */
	// public void test01300Add_Retrieve_ExistingRecord() throws Exception
	// {
	// System.out.println("test01300Add_Retrieve_ExistingRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_ADD);
	// setupKeyFieldsAfterAdd(transaction);
	// applyRetrieval(transaction, true);
	// }

	/**
	 * Test: add mode: new record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */

	// /**
	// * Test: validate mode: existing record<br>
	// * Expected result: Success<br>
	// *
	// * @throws Exception
	// */
	// public void test01500Maint_Validate_ExistingRecord() throws Exception
	// {
	// System.out.println("test01500Maint_Validate_ExistingRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_MNT);
	// setupKeyFieldsAfterAdd(transaction);
	// session.retrieveEquationTransaction(transaction);
	// setupMaintFields(transaction);
	// applyValidate(transaction, true);
	// }

	// /**
	// * Test: maintain mode: existing record<br>
	// * Expected result: Success<br>
	// *
	// * @throws Exception
	// */
	// public void test01600Maint_ExistingRecord() throws Exception
	// {
	// System.out.println("test01600Maint_ExistingRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_MNT);
	// setupKeyFieldsAfterAdd(transaction);
	// session.retrieveEquationTransaction(transaction);
	// setupMaintFields(transaction);
	// applyTransaction(transaction, true);
	// }
	//
	// /**
	// * Test: retrieval and maintain: existing record<br>
	// * Expected result: Success<br>
	// *
	// * @throws Exception
	// */
	// public void test016100Maint_RetrievalMaintain_ExistingRecord() throws Exception
	// {
	// System.out.println("test016100Maint_RetrievalMaintain_ExistingRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_MNT);
	// setupKeyFieldsAfterMaint(transaction);
	// session.retrieveEquationTransaction(transaction);
	// applyTransaction(transaction, true);
	// }
	//
	// /**
	// * Test: retrieval mode: existing record<br>
	// * Expected result: Success<br>
	// *
	// * @throws Exception
	// */
	// public void test01700Cancel_Retrieval_ExistingRecord() throws Exception
	// {
	// System.out.println("test01700Cancel_Retrieval_ExistingRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_RTV);
	// setupKeyFieldsAfterMaint(transaction);
	// applyRetrieval(transaction, true);
	// }
	//
	// /**
	// * Test: validate mode: existing record<br>
	// * Expected result: Success<br>
	// *
	// * @throws Exception
	// */
	// public void test01800Cancel_Validate_ExistingRecord() throws Exception
	// {
	// System.out.println("test01800Cancel_Validate_ExistingRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_DEL);
	// setupKeyFieldsAfterMaint(transaction);
	//
	// // retrieve before cancelling?
	// if (retrieveBeforeCancel)
	// {
	// session.retrieveEquationTransaction(transaction);
	// }
	//
	// applyValidate(transaction, true);
	// }
	//
	// /**
	// * Test: cancel mode: existing record<br>
	// * Expected result: Success<br>
	// *
	// * @throws Exception
	// */
	// public void test01900Cancel_ExistingRecord() throws Exception
	// {
	// System.out.println("test01900Cancel_ExistingRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_DEL);
	// setupKeyFieldsAfterMaint(transaction);
	//
	// // retrieve before cancelling?
	// if (retrieveBeforeCancel)
	// {
	// session.retrieveEquationTransaction(transaction);
	// }
	//
	// applyTransaction(transaction, true);
	// }
	//
	// /**
	// * Test: validate mode: existing record<br>
	// * Expected result: Success<br>
	// *
	// * @throws Exception
	// */
	// public void test02000Cancel_Validate_ExistingRecord() throws Exception
	// {
	// System.out.println("test02000Cancel_Validate_ExistingRecord()");
	// EquationStandardListTransaction transaction = getTransaction();
	// transaction.setMode(EquationStandardTransaction.FCT_DEL);
	// setupKeyFieldsAfterMaint(transaction);
	//
	// // retrieve before cancelling?
	// if (retrieveBeforeCancel)
	// {
	// setupAddFields(transaction);
	// }
	//
	// applyValidate(transaction, false);
	// }

}
