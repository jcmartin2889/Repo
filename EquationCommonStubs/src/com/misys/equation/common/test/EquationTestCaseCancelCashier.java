package com.misys.equation.common.test;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.IEquationStandardObject;

/**
 * Test cases for Cancel function <br>
 * where the function has a generated reference in the key.
 * 
 * Cashier functions have a key made up of Branch, Type and a Reference. The Reference is made up of the date and a sequence
 * CYYMMDDnnnnnn. The Branch, Type and Reference must be unique. This key is used for the add and related cancel.
 * 
 * Cashier Key existence / non-existence methods have been added to this class. Reference Number retrieval from TestEnvironment
 * singleton has been added to this class.
 */
abstract public class EquationTestCaseCancelCashier extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationTestCaseCancelCashier.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	public String optionId;
	public String programName;
	public String transactionType;
	public String addOptionId;

	protected boolean retrieveBeforeCancel = false;

	// ------------------------------------------------------------------------ Abstract methods
	// abstract public void setupExistKeyFields(EquationStandardTransaction transaction);
	// abstract public void setupNonExistKeyFields(EquationStandardTransaction transaction);
	// abstract public EquationStandardTransaction getTransaction() throws Exception;

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
	/**
	 * Setup a non-existing key fields only
	 */
	public final void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZIBRM", "HEAD"); // Cashier branch mnemonic (4A)
		transaction.setFieldValue("GZIBBN", "1000"); // Cashier branch number (4A)
		transaction.setFieldValue("GZTTP", transactionType); // Transaction type (3A)
		transaction.setFieldValue("GZTRF", "1080101999999"); // Transaction reference (13A)

	}
	/**
	 * Setup an existing key fields
	 */
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZIBRM", "HEAD"); // Cashier branch mnemonic (4A)
		transaction.setFieldValue("GZIBBN", "1000"); // Cashier branch number (4A)
		transaction.setFieldValue("GZTTP", transactionType); // Transaction type (3A)
		String reference = TestEnvironment.getTestEnvironment().getParameter(addOptionId);
		if (reference != null)
		{
			transaction.setFieldValue("GZTRF", reference); // Transaction refer
		}
		else
		{
			transaction.setFieldValue("GZTRF", "Unknown"); // Transaction refer
		}
	}
	/**
	 * Return a transaction
	 * 
	 * @return a transaction
	 * 
	 * @throws Exception
	 */
	public final EquationStandardTransaction getTransaction() throws Exception
	{
		EquationStandardTransaction transaction = getEquationStandardTransaction(programName + optionId);
		transaction.setWorkStationId(WORKSTATIONID);
		return transaction;
	}
}
