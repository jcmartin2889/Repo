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
import com.misys.equation.common.test.EquationTestCaseAddMore;

/**
 * Equation test cases for Maintain function
 */
public class OPT extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OPT.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G46MRR";
	String optionId = "OPT";

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
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBRNM", "LOND");
		transaction.setFieldValue("GZDLP", "FXO");
		transaction.setFieldValue("GZDLR", "XXXX");
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBRNM", "LOND");
		transaction.setFieldValue("GZDLP", "FXO");
		transaction.setFieldValue("GZDLR", "1002MNT");
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTPAM", "1000"); // Take-up purchase amount (15P,0)
		transaction.setFieldValue("GZTSAM", "2000"); // Take-up sale amount (15P,0)
		transaction.setFieldValue("GZCRA", "N"); // Cancel residual amount? (1A)
		transaction.setFieldValue("GZTPRD", "1000107"); // Take-up purchase date (7S,0)
		// transaction.setField("GZYPSF", "N"); // Exclude from positions? (1A)
		//transaction.setField("GZYSD", "Y"); // In currency pair positions (1A)

		//transaction.setFieldValue("GZPCCY", "SGD"); // Purchase currency (3A)
		//transaction.setFieldValue("GZSCCY", "CHF"); // Sale currency (3A)
		//transaction.setFieldValue("GZXPCY", "SGD"); // Pay settlement currency (3A)
		//transaction.setFieldValue("GZXRCY", "CHF"); // Receive settlement currency (3A)

		//transaction.setFieldValue("GZPNST", ""); // Sale nostro (6A)
		//transaction.setFieldValue("GZPABF", "9998"); // Sale settlement a/c branch (4A)
		//transaction.setFieldValue("GZPANF", "012844"); // Sale settlement a/c basic number (6A)
		//transaction.setFieldValue("GZPASF", "001"); // Sale settlement a/c suffix (3A)

		//transaction.setFieldValue("GZRNST", "BARUSD"); // Purchase nostro (6A)
		//transaction.setFieldValue("GZRABF", ""); // Purchase settlement a/c branch (4A)
		//transaction.setFieldValue("GZRANF", ""); // Purchase settlement a/c basic number (6A)
		//transaction.setFieldValue("GZRASF", ""); // Purchase settlement a/c suffix (3A)
	}

}
