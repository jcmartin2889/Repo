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
import com.misys.equation.common.test.EquationTestCaseMaintain;

/**
 * Equation test cases for Reporting Variables
 */
public class BGV extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BGV.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	String programName = "A16MRR";
	String optionId = "BGV";

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
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAMA", "15000"); // Capital base
		transaction.setFieldValue("GZAMAD", "200000000000000"); // Deposit base
		transaction.setFieldValue("GZCCY", "GBP"); // Reporting currency
		transaction.setFieldValue("GZMRL1", "0003"); // Deals that are deposits
		transaction.setFieldValue("GZMRL2", "0004"); // Accounts that are deposits
		transaction.setFieldValue("GZMRL3", "0001"); // Debit accounts that are exposures
		transaction.setFieldValue("GZMRL4", "0002"); // Credit accounts that are exposures
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAMA", "100000000000000"); // Capital base
		transaction.setFieldValue("GZCCY", "GBP"); // Reporting currency
		transaction.setFieldValue("GZPEC1", "1"); // % of capital base to report periodically (5P,2)
		transaction.setFieldValue("GZPEC2", "2"); // % of capital base to report daily (5P,2)
		transaction.setFieldValue("GZPEC3", "3"); // % of deposit base to report periodically (5P,2)
		transaction.setFieldValue("GZPEC4", "4"); // % of deposit base to report daily
	}

	/**
	 * Skip the following invalid tests.
	 */
	@Override
	public void test00100Maint_Validate_NonExistingRecord() throws Exception
	{
	}
	@Override
	public void test00200Maint_Retrieval_NonExistingRecord() throws Exception
	{
	}
	@Override
	public void test00300Maint_NonExistingRecord() throws Exception
	{
	}
	@Override
	public void test00400Maint_Retrieval_ExistingRecord() throws Exception
	{
	}
	@Override
	public void test00700Maint_RetrievalMaintain_ExistingRecord() throws Exception
	{
	}
}
