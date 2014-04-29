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
 * @author Paul Macdona
 */
public class MTM_ extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MTM_.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private EquationStandardTransaction transaction;
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		// Get a new transaction for Add/Maintain Transfer Method
		transaction = getEquationStandardTransaction("S43FRRMTM");
	}
	public void testAdd() throws Exception
	{
		// Set the transaction type
		transaction.setMode("A");
		// Set the transaction fields
		transaction.setFieldValue("GZXM", "TC"); // Transfer method
		transaction.setFieldValue("GZXMD", "Test Case"); // Transfer method description
		transaction.setFieldValue("GZEXF", "1"); // Spot/user/retail rate
		transaction.setFieldValue("GZNSF1", "N"); // Mail document
		transaction.setFieldValue("GZNSF2", "N"); // Telex
		transaction.setFieldValue("GZNSF3", "N"); // Telex via SWIFT
		transaction.setFieldValue("GZNSF4", "N"); // SWIFT format
		transaction.setFieldValue("GZNSF5", "N"); // Facsimile
		transaction.setFieldValue("GZNSF6", "N"); // No document required
		transaction.setFieldValue("GZASI", "N"); // Abbreviated settlement instructions
		transaction.setFieldValue("GZVXD", "N"); // Valid for EXIMBILLS?
		// See if it works
		assertTestStandardTransaction(transaction, true);
	}
	public void testMaintain() throws Exception
	{
		// Set the transaction type
		transaction.setMode("M");
		// Set the transaction fields
		transaction.setFieldValue("GZXM", "TC"); // Transfer method
		transaction.setFieldValue("GZXMD", "Maintained Test Case"); // Transfer method description
		transaction.setFieldValue("GZEXF", "1"); // Spot/user/retail rate
		transaction.setFieldValue("GZNSF1", "N"); // Mail document
		transaction.setFieldValue("GZNSF2", "N"); // Telex
		transaction.setFieldValue("GZNSF3", "N"); // Telex via SWIFT
		transaction.setFieldValue("GZNSF4", "N"); // SWIFT format
		transaction.setFieldValue("GZNSF5", "N"); // Facsimile
		transaction.setFieldValue("GZNSF6", "N"); // No document required
		transaction.setFieldValue("GZASI", "N"); // Abbreviated settlement instructions
		transaction.setFieldValue("GZVXD", "N"); // Valid for EXIMBILLS?
		// See if it works
		assertTestStandardTransaction(transaction, true);
	}
	public void testDelete() throws Exception
	{
		// Set the transaction type
		transaction.setMode("D");
		// Set the transaction fields
		transaction.setFieldValue("GZXM", "TC"); // Transfer method
		// See if it works
		assertTestStandardTransaction(transaction, true);
	}
}
