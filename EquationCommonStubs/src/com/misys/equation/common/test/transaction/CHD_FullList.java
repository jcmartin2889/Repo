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
import com.misys.equation.common.test.EquationTestCaseFullyList;

/**
 * Equation test cases for functions that have a list (subfile) mode where records can be maintained individually and the key fields
 * change after the Add and/or Maintain.
 * 
 */
public class CHD_FullList extends EquationTestCaseFullyList
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CHD_FullList.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "X28LRR";
	String optionId = "CHD";

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
		transaction.setFieldValue("GZRRC", "DDC"); // Return reason code (3A)
		transaction.setFieldValue("GZCHQS", "5"); // Up to (2S,0)
		transaction.setFieldValue("GZCHQP", "5"); // Up to (2S,0)

	}

	/**
	 * Set up header key fields
	 * 
	 * If the function has separate Header and Detail modes (retrieveHeader = true) then populate the key fields to retrieve the
	 * Header record.
	 */
	@Override
	public void setupHeaderKeyFields(EquationStandardTransaction transaction)
	{

	}

	/**
	 * Setup key fields as they are AFTER ADD
	 * 
	 * Note that where the value of a key field can be maintained by the function, there is likely to be both an Old and New version
	 * of the field on the GZ file. If so, they should both be populated here with the current value.
	 */
	@Override
	public void setupKeyFieldsAfterAdd(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZRRC", "DDC"); // Return reason code (3A)
		transaction.setFieldValue("GZCHQP", "5"); // Up to (2S,0)
		transaction.setFieldValue("GZCHQS", "5"); // Up to (2S,0)

	}

	/**
	 * Setup key fields as they are AFTER MAINTAIN
	 * 
	 * Note that where the value of a key field can be maintained by the function, there is likely to be both an Old and New version
	 * of the field on the GZ file. If so, they should both be populated here with the current value.
	 */
	@Override
	public void setupKeyFieldsAfterMaint(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZRRC", "DDC"); // Return reason code (3A)
		transaction.setFieldValue("GZCHQS", "6"); // Up to (2S,0)
		transaction.setFieldValue("GZCHQP", "6"); // Previous up to range (2S,0)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCCD", "B1"); // Charge code (2A)
		transaction.setFieldValue("GZAMT", "5000"); // Charge amount (15P,0)
		transaction.setFieldValue("GZWMN", "KSM2020"); // Warning message (7A)
		transaction.setFieldValue("GZFLG", "N"); // Stop chqbook issue? (1A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCHQP", "5"); // Previous up to range (2S,0)
		transaction.setFieldValue("GZCHQS", "6"); // Up to (2S,0)
		transaction.setFieldValue("GZAMT", "7500"); // Charge amount (15P,0)
		transaction.setFieldValue("GZFLG", "Y"); // Stop chqbook issue? (1A)
	}

}
