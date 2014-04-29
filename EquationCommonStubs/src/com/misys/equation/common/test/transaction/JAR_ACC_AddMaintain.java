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
import com.misys.equation.common.test.EquationTestCaseAddMaintain;

/**
 * Equation test cases for Maintain function
 * 
 * NOTE: Use C54LRR / JAR for maintaining customer relations as follows. To maintain the narrative fields use C59FRR / JAR (see
 * JAR_Narrative.java)
 * 
 */
public class JAR_ACC_AddMaintain extends EquationTestCaseAddMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: JAR_ACC_AddMaintain.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "C54LRR";
	String optionId = "JAR";

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
	 * Setup a non-existing key fields only
	 * 
	 * Note when accessing this function via the API, the 'key fields' are the Joint Account, Secondary Customer and the
	 * Relationship Code, as below.
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0543"); // Joint account branch (4A)
		transaction.setFieldValue("GZAN", "123467"); // Joint account basic number (6A)
		transaction.setFieldValue("GZAS", "008"); // Joint account suffix (3A)
		transaction.setFieldValue("GZSCUS", "BBI   "); // Secondary customer mnemonic (6A)
		transaction.setFieldValue("GZSCLC", "LON"); // Secondary customer location (3A)
		transaction.setFieldValue("GZREL", "BEN"); // Relationship code (3A)
		transaction.setFieldValue("GZCUSH", "BBI   "); // Previous customer mnemonic (6A)
		transaction.setFieldValue("GZCLCH", "LON"); // Previous customer location (3A)
		transaction.setFieldValue("GZRELH", "BEN"); // Relationship code (3A)

	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDUPS", "N"); // Duplicate statements required? (1A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 * 
	 * You can maintain the Secondary Customer or Relationship Code even though these are 'key fields'. You must populate the
	 * previous customer, relationship and duplicate statement fields.
	 * 
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZSCUS", "BBI   "); // Secondary customer mnemonic (6A)
		transaction.setFieldValue("GZSCLC", "LON"); // Secondary customer location (3A)
		transaction.setFieldValue("GZREL", "DIV"); // Relationship code (3A)
		transaction.setFieldValue("GZDUPS", "Y"); // Duplicate statements required? (1A)
		transaction.setFieldValue("GZCUSH", "BBI   "); // Previous customer mnemonic (6A)
		transaction.setFieldValue("GZCLCH", "LON"); // Previous customer location (3A)
		transaction.setFieldValue("GZRELH", "BEN"); // Relationship code (3A)
		transaction.setFieldValue("GZDUPH", "N"); // Duplicate statements required? (1A)
	}

	@Override
	public void test01300Maint_RetrievalMaintain_ExistingRecord() throws Exception
	{
		/*
		 * Because the key fields are maintained by this function it would be necessary to supply the maintained fields before
		 * performing this retrieve.
		 * 
		 * e.g. transaction.setFieldValue("GZREL", "DIV"); // Relationship code (3A) transaction.setFieldValue("GZDUPS", "Y"); //
		 * Duplicate statements required? (1A)
		 */
	}

}
