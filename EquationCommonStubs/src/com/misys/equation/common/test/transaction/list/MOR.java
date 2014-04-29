/**
 * Copyright and all other intellectual property rights in this software, in any form, is vested in Misys International Banking
 * Systems Ltd ("Misys") or a related company.
 * 
 * This software may not be copied, amended, compiled, translated, or developed; or sold, leased, hired, rented, or disclosed to any
 * third party without the prior written consent of Misys.
 * 
 * Copyright Misys International Banking Systems Ltd, 1975 and later
 */

package com.misys.equation.common.test.transaction.list;

import com.misys.equation.common.access.EquationStandardGSListTransaction;

/**
 * Equation test cases for Maintain bid/offer rates
 */
public class MOR extends EquationTestCaseFullyList2
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MOR.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "N46LRR";
	String optionId = "MOR";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		retrieveBeforeCancel = true;
		/*
		 * If the list function has separate Header and Detail modes then set 'retrieveHeader = true', otherwise 'retrieveHeader =
		 * false'.
		 */
		retrieveHeader = false;
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
	public EquationStandardGSListTransaction getTransaction() throws Exception
	{
		EquationStandardGSListTransaction transaction = getEquationStandardTransaction(programName + optionId);
		transaction.setWorkStationId(WORKSTATIONID);
		return transaction;
	}

	// ------------------------------------------------------------------------ Field setups

	/**
	 * Setup a non-existing key fields only
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardGSListTransaction transaction)
	{
		transaction.setFieldValue("GZCCY", "ARA"); // Currency (3A)

	}

	/**
	 * Set up header key fields
	 * 
	 * If the function has separate Header and Detail modes (retrieveHeader = true) then populate the key fields to retrieve the
	 * Header record.
	 */
	@Override
	public void setupHeaderKeyFields(EquationStandardGSListTransaction transaction)
	{
		transaction.setFieldValue("GZCCY", "GBP"); // Currency (3A)
	}

	/**
	 * Setup key fields as they are AFTER ADD
	 */
	@Override
	public void setupKeyFieldsAfterAdd(EquationStandardGSListTransaction transaction)
	{
		transaction.setFieldValue("GZCCY", "GBP"); // Currency (3A)
	}

	/**
	 * Setup key fields as they are AFTER MAINTAIN
	 */
	@Override
	public void setupKeyFieldsAfterMaint(EquationStandardGSListTransaction transaction)
	{
		transaction.setFieldValue("GZCCY", "GBP"); // Currency (3A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardGSListTransaction transaction)
	{
		transaction.setFieldValue("GZCCY", "GBP"); // Currency (3A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardGSListTransaction transaction)
	{
		assertTrue(transaction.moveToRow(2));
		transaction.setFieldValue("GSIMG", "A"); // Image (1A,0)
		// transaction.setFieldValue("GSPER", "003"); // Period (3A)
		// transaction.setFieldValue("GSPERC", "M"); // Period Code (1A)
		transaction.setFieldValue("GSBRAT", "10.888"); // Bid rate (11P,7)
		// transaction.setFieldValue("GSORAT", "10.888"); // Offer rate (11P,7)
	}

}
