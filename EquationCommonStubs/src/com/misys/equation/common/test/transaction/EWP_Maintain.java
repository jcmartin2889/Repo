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
 * Equation test cases for the MAINTAIN part of the (Watch List) Check Clean Payments function (see the EWP_AddMore.java class for
 * the ADD part). The AddMaintain and AddOnce classes are not appropriate for EWP because it is a non-standard function and sets @FCT
 * automatically in the function. For instance, if you do 2 'adds', the 2nd one will default to a 'maintain', and so will fail, so
 * the Add_DuplicateRecord test is N/A. Similarly with the Maint_NonExistingRecord test.
 */
public class EWP_Maintain extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EWP_Maintain.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	String programName = "W13MRR";
	String optionId = "EWP";

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
	 * non-existing clean payment NOT an existing clean payment without an existing case.
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZPCLP", "XXXXXXXXXXXXXXXX"); // Clean payment (16A)
		transaction.setFieldValue("GZPEXT", ""); // External reference (16A)
		/**
		 * Although not actual key fields, these are needed here. Otherwise, case status will be incorrect, i.e. a no hit status
		 * after the last test.
		 */
		transaction.setFieldValue("GZPLFD", "Y"); // Load from database (1A)
		transaction.setFieldValue("GZPUPF", "Y"); // Update database if watch list unavailable? (1A)
		transaction.setFieldValue("GZPSES", "N"); // Suspect on external system (1A)
		transaction.setFieldValue("GZPSAU", "Y"); // Suppress authorisation (1A)
	}

	/**
	 * Setup an existing key fields This will re-check (maintain) the WLC case raised for the customer created in the
	 * AddForWLC_ANC.java class.
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZPCLP", "LOND00105X001999"); // Clean payment (16A)
		transaction.setFieldValue("GZPEXT", ""); // External reference (16A)
		/**
		 * Although not actual key fields, these are needed here. Otherwise, case status will be incorrect, i.e. a no hit status
		 * after the last test.
		 */
		transaction.setFieldValue("GZPLFD", "Y"); // Load from database (1A)
		transaction.setFieldValue("GZPUPF", "Y"); // Update database if watch list unavailable? (1A)
		transaction.setFieldValue("GZPSES", "N"); // Suspect on external system (1A)
		transaction.setFieldValue("GZPSAU", "Y"); // Suppress authorisation (1A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZPSWC", "N"); // Suppress watch list checking (1A)
		transaction.setFieldValue("GZPCEX", "N"); // Case exists (1A)
		transaction.setFieldValue("GZPWLC", "N"); // Watch list checked? (1A)
	}

}
