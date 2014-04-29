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
 * Equation test cases for Maintain Repayment Transaction
 */
public class TRM extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TRM.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H34MRR";
	String optionId = "TRM";

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
		transaction.setFieldValue("GZDLP", "XXX"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "NONEXIST"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "XXXX"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZDTE", "0990831"); // Repayment due date (7S,0)
		transaction.setFieldValue("GZPIF", "P"); // Repayment type; I=Interest, P=Principal, B=Both (1A)

	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDLP", "COA"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "COA18"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "CUTE"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZDTE", "0990831"); // Repayment due date (7S,0)
		transaction.setFieldValue("GZMVTS", "R");
		transaction.setFieldValue("GZPIF", "P"); // Repayment type; I=Interest, P=Principal, B=Both (1A)
		transaction.setFieldValue("GZBVF", "0990831"); // Principal repayment date (7S,0)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{

		
		//transaction.setFieldValue("GZAM1", "96000"); // Principal repayment amount (15P,0)
		//transaction.setFieldValue("GZAB1", "3000"); // Principal settlement account branch (4A)
		//transaction.setFieldValue("GZAN1", "L00001"); // Principal settlement account basic number (6A)
		//transaction.setFieldValue("GZAS1", "001"); // Principal settlement account suffix (3A)
		//transaction.setFieldValue("GZCCY1", "GBP"); // Principal settlement currency (3A)
		//transaction.setFieldValue("GZD11", "Maintained"); // Principal details line 1 (35A)
	}

}
