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
 * Equation test cases for Maintain function Authorise Commercial Paper Items (F18=Authorise follow-on from OKN)
 */
public class OLD extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OLD.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	String programName = "W28DRR";
	String optionId = "OLD";

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
		//transaction.setFieldValue("GZFLDN", "NONEXST2"); // Folder No (8A)
		//transaction.setFieldValue("GZRVAU", "AU"); // Mode (2A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZFLDN", "10000009"); // Folder No (8A)
		transaction.setFieldValue("GZRVAU", "RV"); // Mode (2A)
		transaction.setFieldValue("GZUITM", "01"); // Items Utilised (2P,0)
		transaction.setFieldValue("GZUTOT", "5000"); // Total Utilised (15P,0)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{

	}
	/**
	 * Test: retrieval mode: existing record<br>
	 * Expected result: Success<br>
	 * 
	 * @throws Exception
	 */
	@Override
	public void test00700Maint_RetrievalMaintain_ExistingRecord() throws Exception
	{
		// Not a valid test because once authorised, you cannot
		// authorise again.
	}
	
	public void test00200Maint_Retrieval_NonExistingRecord() throws Exception
	{
		System.out.println("test00200Maint_Retrieval_NonExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_MNT);
		setupNonExistKeyFields(transaction);
		applyRetrieval(transaction, false);
		currentTransaction = transaction;
	}
	
	public void test00300Maint_NonExistingRecord() throws Exception
	{
		System.out.println("test00300Maint_NonExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_MNT);
		setupNonExistKeyFields(transaction);
		setupMaintFields(transaction);
		applyTransaction(transaction, false);
		currentTransaction = transaction;
	}
	
	public void test00100Maint_Validate_NonExistingRecord() throws Exception
	{
		System.out.println("test00100Maint_Validate_NonExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_MNT);
		setupNonExistKeyFields(transaction);
		setupMaintFields(transaction);
		applyValidate(transaction, false);
		currentTransaction = transaction;
	}
	
	public void test00600Maint_ExistingRecord() throws Exception
	{
		System.out.println("test00600Maint_ExistingRecord()");
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_MNT);
		setupExistKeyFields(transaction);
		session.retrieveEquationTransaction(transaction);
		setupMaintFields(transaction);
		applyTransaction(transaction, true);
		currentTransaction = transaction;
	}

}
