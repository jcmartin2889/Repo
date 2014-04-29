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
import com.misys.equation.common.test.EquationTestCaseAddCancel;

/**
 * Equation test cases for Add/Maintain Group User Linkage
 */
public class GUB extends EquationTestCaseAddCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GUB.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	// String programName = "X24MRR";
	String programName = "B63MRR";
	String optionId = "GUB";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		retrieveBeforeCancel = false;
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
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZGRP", "NTEST1"); // Group name
		transaction.setFieldValue("GZBRNM", "ACC1"); // Branch mnemonic
		transaction.setFieldValue("GZBBN", "9132"); // Branch number
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZGRP", "NTEST1"); // Group name
		transaction.setFieldValue("GZBRNM", "ACC1"); // Branch mnemonic
		transaction.setFieldValue("GZBBN", "9132"); // Branch number
	}

	/**
	 * Skip the following invalid tests
	 */
	@Override
	public void test00200Add_Retrieve_NonExistingRecord() throws Exception
	{
	}
	@Override
	public void test00300Cancel_Validate_NonExistingRecord() throws Exception
	{
	}
	@Override
	public void test00400Cancel_Retrieval_NonExistingRecord() throws Exception
	{
	}
	@Override
	public void test00500Cancel_NonExistingRecord() throws Exception
	{
	}
	@Override
	public void test00700Add_DuplicateRecord() throws Exception
	{
	}
	@Override
	public void test00800Add_Validate_ExistingRecord() throws Exception
	{
	}
	@Override
	public void test01300Cancel_Validate_ExistingRecord() throws Exception
	{
	}
}
