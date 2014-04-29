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
import com.misys.equation.common.test.EquationTestCaseMaintain;

/**
 * Equation test cases for Reload Branch Calendars
 * 
 * Note: Retrieve mode is not supported.
 */
public class RDB extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RDB.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "C92MRR";
	String optionId = "C2R";


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
	 * Setup a non-existing key fields
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZREG", ""); // Region code (3A)
		transaction.setFieldValue("GZALL", "N"); // All branches? (1A) 
		//transaction.setFieldValue("GZMON", "Y"); // Calendar year 01 (2A)
		//transaction.setFieldValue("GZTUE", "N");
		//transaction.setFieldValue("GZWED", "Y");
		//transaction.setFieldValue("GZTHU", "Y");
		//transaction.setFieldValue("GZFRI", "Y");
		//transaction.setFieldValue("GZSAT", "Y");
		//transaction.setFieldValue("GZSUN", "Y");
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		//transaction.setFieldValue("GZIMG", "A");
		transaction.setFieldValue("GZLRD","N"); //LOCAL RUN DAY CALENDAR
		transaction.setFieldValue("GZALL", "N"); // All branches? (1A)
		transaction.setFieldValue("GZBRNM", "KBSL"); // Branch mnemonic (4A)
		//transaction.setFieldValue("GZCCY", ""); // Branch mnemonic (4A)
		//transaction.setFieldValue("GZREG", ""); // Region code (3A)
		transaction.setFieldValue("GZMON", "Y"); // Calendar year 01 (2A)
		transaction.setFieldValue("GZTUE", "N");
		transaction.setFieldValue("GZWED", "Y");
		transaction.setFieldValue("GZTHU", "Y");
		transaction.setFieldValue("GZFRI", "Y");
		transaction.setFieldValue("GZSAT", "Y");
		transaction.setFieldValue("GZSUN", "Y");
		
		// Retrieval is not supported

	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		//transaction.setFieldValue("GZBRNM", "ACC1"); // Branch mnemonic (4A)
		//transaction.setFieldValue("GZLRD","N"); //LOCAL RUN DAY CALENDAR
		//transaction.setFieldValue("GZALL", "N"); // All branches? (1A)
		transaction.setFieldValue("GZMON", "Y"); // Calendar year 01 (2A)
		transaction.setFieldValue("GZTUE", "Y");
		transaction.setFieldValue("GZWED", "Y");
		transaction.setFieldValue("GZTHU", "Y");
		transaction.setFieldValue("GZFRI", "Y");
		transaction.setFieldValue("GZSAT", "Y");
		transaction.setFieldValue("GZSUN", "Y");
		//transaction.setFieldValue("GZACAL", "R"); // All calendars? (1A)
		//transaction.setFieldValue("GZSUBA","NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNRRRRRRRRRRRNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");// Reload/Delete (199A)
	}
	
	/**
	 * Test: retrieval mode: existing record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	public void test00700Maint_RetrievalMaintain_ExistingRecord() throws Exception
	{
		System.out.println("test00700Maint_RetrievalMaintain_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_MNT);
		setupExistKeyFields(transaction);
		session.retrieveEquationTransaction(transaction);
		setupMaintFields(transaction);
		applyTransaction(transaction, true);
		currentTransaction = transaction;
	}

}
