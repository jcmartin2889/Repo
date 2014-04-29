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
import com.misys.equation.common.test.EquationTestCase;

/**
 * @author weddelc1
 */
public class PMC_Account extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: PMC_Account.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardTransaction transaction;
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		/*
		 * Get an empty transaction class you'll need to enter the name of the transaction concatenated with the WMENU1 option id.
		 */
		transaction = getEquationStandardTransaction("C62ARRPMC");
	}
	public void testAdd() throws Exception
	{
		/*
		 * Set the transaction type
		 */
		transaction.setMode("A");
		/*
		 * Set the transaction fields
		 */
		/*
		 * Set the transaction fields
		 */
		// **************************************************************
		// Common GZ/GS fields
		String branch = "0132"; // branch
		String basic = "012008"; // basic number
		String suffix = "020"; // suffix
		String date = "1000131"; // date

		// Key screen fields
		transaction.setFieldValue("GZAB", branch); // Account branch
		transaction.setFieldValue("GZAN", basic); // Basic part of account number
		transaction.setFieldValue("GZAS", suffix); // Account suffix
		transaction.setFieldValue("GZVAL", date); // Value from date

		// Detail screen fields
		transaction.setFieldValue("GZCCCY", "ITL"); // Curremcy mnemonic
		transaction.setFieldValue("GZQEVM", "PMC"); // Events
		transaction.setFieldValue("GZUSER", "ACC1"); // User default branch
		transaction.setFieldValue("GZFLAG", "P"); // Transaction flag

		// Row 0 - set key fields for row to retrieve
		int row = 0;
		transaction.setFieldValue("GSIMG", "A"); // Journal image
		transaction.setFieldValue("GSCHGC", "AC"); // Charge code
		transaction.setFieldValue("GSSDT", "1000131"); // Start date
		transaction.setFieldValue("GSEND", "1000430"); // End date
		transaction.setFieldValue("GSCHA", "1234"); // Charge amount
		transaction.setFieldValue("GSFRQ", "V31"); // Frequency code
		transaction.setFieldValue("GSFAB", "0132"); // Funding account branch
		transaction.setFieldValue("GSFAN", "012008"); // Funding account number
		transaction.setFieldValue("GSFAS", "020"); // Funding account suffix
		transaction.setFieldValue("GSFTF", "N"); // Force to fee?
		transaction.setFieldValue("GSNP1", "Post manual charges (SKAC) ELW"); // Posting narrative 1

		transaction.setFieldValue("GSQEVM", "PMC"); // Events
		transaction.setFieldValue("GSECHC", "ITL"); // Entered charge currency
		transaction.setFieldValue("GSENC", "1234"); // Entered charge amount
		transaction.setFieldValue("GSTYP", "4"); // Action type
		transaction.setFieldValue("GSVAL", "1000131"); // Value from date
		// ********************************************************************

		/*
		 * See if it works
		 */
		assertTestStandardTransaction(transaction, true);
	}
}
