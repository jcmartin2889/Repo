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
public class ACG_1 extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACG_1.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardTransaction transaction;
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		/*
		 * Get an empty transaction class you'll need to enter the name of the transaction concatenated with the WMENU1 option id.
		 */
		transaction = getEquationStandardTransaction("C62ARRACG", 1000);
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
		transaction.setFieldValue("GZDLP", "BUF");// Deal type
		transaction.setFieldValue("GZDLR", "001");// Deal reference
		transaction.setFieldValue("GZDBRM", "HEAD");// Deal branch
		transaction.setFieldValue("GZVAL", "1000204");// Value date
		transaction.setFieldValue("GZUSER", "LIMA");// User id
		transaction.setFieldValue("GZEVNM", "RLA");// Event mnemonic
		transaction.setFieldValue("GZDDTD", "991231");//
		transaction.setFieldValue("GZESQN", "0");//
		transaction.setFieldValue("GZDCHG", "N");// Default charge
		transaction.setFieldValue("GZFLAG", "A");// transaction type flag
		transaction.setGSFieldValue("GSECNM", "RLA1");// Event mnemonic
		transaction.setGSFieldValue("GSCHGC", "AD");// Charge code
		transaction.setGSFieldValue("GSCHA", "92000");// Charge amount
		transaction.setGSFieldValue("GSSDT", "1000204");// Start date
		transaction.setGSFieldValue("GSEND", "1000204");// End date
		transaction.setGSFieldValue("GSFRQ", "V01");// frequency
		transaction.setGSFieldValue("GSFAB", "9132");// funding account branch
		transaction.setGSFieldValue("GSFAN", "234567");// funding account basic number
		transaction.setGSFieldValue("GSFAS", "001");// funding account suffix
		transaction.setGSFieldValue("GSCAP", "N");// Capitalised charge
		transaction.setGSFieldValue("GSFTF", "N");// Force to fee
		/*
		 * See if it works
		 */
		assertTestStandardTransaction(transaction, true);
	}
}
