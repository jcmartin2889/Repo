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
import com.misys.equation.common.test.TestEnvironment;

/**
 * Equation test cases for Return Direct Debit
 */
public class RDD extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RDD.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "D23MRR";
	String optionId = "RDD";

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
		transaction.setFieldValue("GZAB", "0000"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "000000"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "000"); // Account suffix (3A)
		transaction.setFieldValue("GZONAM", "Name"); // Originator's name (20A)
		transaction.setFieldValue("GZOID", "Identifier"); // Originator's identifier (10A)
		transaction.setFieldValue("GZOREF", "CRG-XX"); // DD originator's reference (20A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		//transaction.setFieldValue("GZRRC","EXP");
		transaction.setFieldValue("GZAB", "0543"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "131313"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "107"); // Account suffix (3A)
		transaction.setFieldValue("GZONAM", "PLDT"); // Originator's name (20A)
		transaction.setFieldValue("GZOID", "PLDT"); // Originator's identifier (10A)
		transaction.setFieldValue("GZOREF", "PLDT001"); // DD originator's reference (20A)
		transaction.setFieldValue("GZREF", "0543000105000008"); // Internal reference


		String reference = TestEnvironment.getTestEnvironment().getParameter("PDD");
		if (reference != null)
		{
			transaction.setFieldValue("GZREF", reference); // Internal reference
		}
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZRRC", "ADM"); // Reason for return code (3A)
	}

	/*
	 * This test returns false not true This is because once the DD has been returned its payment status is changed on file L6PF,
	 * and this means that the validation pgm returns 'KSM2010 Direct Debit mandate does not exist'. i.e. once a DD has been
	 * returned it can't be returned again.
	 */
	@Override
	public void test00700Maint_RetrievalMaintain_ExistingRecord() throws Exception
	{
		EquationStandardTransaction transaction = getTransaction();
		transaction.setMode(IEquationStandardObject.FCT_MNT);
		setupExistKeyFields(transaction);
		session.retrieveEquationTransaction(transaction);
		applyTransaction(transaction, false);
		currentTransaction = transaction;
	}
}
