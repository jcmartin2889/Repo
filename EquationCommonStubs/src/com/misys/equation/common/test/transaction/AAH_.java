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
 * @author perkinj1
 */
public class AAH_ extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AAH_.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardTransaction transaction;
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		/*
		 * Get an empty transaction class you'll need to enter the name of the transaction concatenated with the WMENU1 option id.
		 */
		transaction = getEquationStandardTransaction("J40ARRAAH");
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
		transaction.setFieldValue("GZBBN", "0543"); // account branch
		transaction.setFieldValue("GZBNO", "123467"); // account basic
		transaction.setFieldValue("GZSFX", "001"); // account suffix
		transaction.setFieldValue("GZEXD", "1010601"); // Expiry Date
		transaction.setFieldValue("GZAMTH", "10000"); // Amount
		transaction.setFieldValue("GZACO", "ADM"); // Department Code/Account Officer
		transaction.setFieldValue("GZHRC", "GCF"); // Hold reason code
		/*
		 * See if it works
		 */
		assertTestStandardTransaction(transaction, true);
	}
}
