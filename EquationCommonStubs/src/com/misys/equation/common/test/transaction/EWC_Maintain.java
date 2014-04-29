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
 * Equation test cases for the MAINTAIN part of the (Watch List) Check Customer Details function (see the EWC_AddMore.java class for
 * the ADD part). The AddMaintain and AddOnce classes are not appropriate for EWC because it is a non-standard function and sets @FCT
 * automatically in the function. For instance, if you do 2 'adds', the 2nd one will default to a 'maintain', and so will fail, so
 * the Add_DuplicateRecord test is N/A. Similarly with the Maint_NonExistingRecord test.
 */
public class EWC_Maintain extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EWC_Maintain.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "W11MRR";
	String optionId = "EWC";

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
	 * Setup a non-existing key fields NB. These are function key fields NOT WLC case key fields. So, this must be for a
	 * non-existing customer NOT an existing customer without an existing case.
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZRCUS", "XXXXXX"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZRCLC", "   "); // Customer location (3A)
		transaction.setFieldValue("GZRAB", ""); // Customer account branch (4A)
		transaction.setFieldValue("GZRAN", ""); // Customer account number (6A)
		transaction.setFieldValue("GZRAS", ""); // Customer account suffix (3A)
		transaction.setFieldValue("GZRADT", ""); // Address type (1A)
		transaction.setFieldValue("GZRCTR", "1"); // Check type required (1A)

		/**
		 * Although not actual key fields, these are needed here. Otherwise, case status will be incorrect, i.e. a no hit status
		 * after the last test.
		 */
		transaction.setFieldValue("GZRLFD", "Y"); // Load from database (1A)
		transaction.setFieldValue("GZRUPF", "Y"); // Update database if watch list unavailable? (1A)
	}

	/**
	 * Setup an existing key fields This will re-check (maintain) the WLC case raised for the customer created in the
	 * AddForWLC_ANC.java class.
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZRCUS", "MUGABE"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZRCLC", "001"); // Customer location (3A)
		transaction.setFieldValue("GZRAB", ""); // Customer account branch (4A)
		transaction.setFieldValue("GZRAN", ""); // Customer account number (6A)
		transaction.setFieldValue("GZRAS", ""); // Customer account suffix (3A)
		transaction.setFieldValue("GZRADT", ""); // Address type (1A)
		transaction.setFieldValue("GZRCTR", "1"); // Check type required (1A)

		/**
		 * Although not actual key fields, these are needed here. Otherwise, case status will be incorrect, i.e. a no hit status
		 * after the last test.
		 */
		transaction.setFieldValue("GZRLFD", "Y"); // Load from database (1A)
		transaction.setFieldValue("GZRUPF", "Y"); // Update database if watch list unavailable? (1A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{

	}

}