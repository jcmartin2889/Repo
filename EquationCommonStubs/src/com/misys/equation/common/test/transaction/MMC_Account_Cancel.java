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
import com.misys.equation.common.test.EquationTestCaseCancel;

/**
 * Equation test cases for Maintain function
 */
public class MMC_Account_Cancel extends EquationTestCaseCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MMC_Account_Cancel.java 8426 2010-07-29 17:32:49Z CHALLIP1 $";
	String programName = "C62MRR";
	String optionId = "MMC";
	
	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		retrieveBeforeCancel = true;
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
		EquationStandardTransaction transaction = new EquationStandardTransaction("C62MRRMMC", session);

		transaction.setWorkStationId(WORKSTATIONID);
		return transaction;
	}

	// ------------------------------------------------------------------------ Field setups

	/**
	 * Setup an existing key fields only
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		// Common GZ/GS fields
		String branch = "0132"; // branch
		String basic = "012008"; // basic number
		String suffix = "020"; // suffix
		String date = "1000204"; // date

		// Key screen fields
		transaction.setFieldValue("GZAB", branch); // Account branch
		transaction.setFieldValue("GZAN", basic); // Basic part of account number
		transaction.setFieldValue("GZAS", suffix); // Account suffix
		transaction.setFieldValue("GSVAL", date); // Value from date

		transaction.setFieldValue("GZFLAG", "M"); // Language code (2A)
		transaction.setFieldValue("GZQEVM", "MMC"); // Language code (2A)
		transaction.setFieldValue("GSQEVM", "MMC"); // Language code (2A)

		transaction.setFieldValue("GSIMG", "A");

		transaction.setFieldValue("GSFSQNA", "1");
		transaction.setFieldValue("GSCHGC", "AD");
		transaction.setFieldValue("GSTYP", "1");

	}
	/**
	 * Setup a non-existing key fields only
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		// Common GZ/GS fields
		String branch = "0132"; // branch
		String basic = "012008"; // basic number
		String suffix = "999"; // suffix
		String date = "1000131"; // date

		// Key screen fields
		transaction.setFieldValue("GZAB", branch); // Account branch
		transaction.setFieldValue("GZAN", basic); // Basic part of account number
		transaction.setFieldValue("GZAS", suffix); // Account suffix
		transaction.setFieldValue("GSVAL", date); // Value from date

		transaction.setFieldValue("GZFLAG", "M"); // Language code (2A)
		transaction.setFieldValue("GZQEVM", "MMC"); // Language code (2A)
		transaction.setFieldValue("GSQEVM", "MMC"); // Language code (2A)

		transaction.setFieldValue("GSIMG", "A");

		transaction.setFieldValue("GSFSQN", "3");
		transaction.setFieldValue("GSFSQNA", "3");
		transaction.setFieldValue("GSCHGC", "AC");
		transaction.setFieldValue("GSTYP", "4");

	}
}
