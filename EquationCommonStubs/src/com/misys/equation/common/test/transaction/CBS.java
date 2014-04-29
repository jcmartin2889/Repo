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
public class CBS extends EquationTestCaseCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CBS.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "J73CRR";
	String optionId = "CBS";

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
		EquationStandardTransaction transaction = getEquationStandardTransaction(programName + optionId);
		transaction.setWorkStationId(WORKSTATIONID);
		return transaction;
	}

	// ------------------------------------------------------------------------ Field setups

	/**
	 * Setup a non-existing key fields
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBBN", "9999"); // Branch number
		transaction.setFieldValue("GZBNO", "012008"); // Customer number
		transaction.setFieldValue("GZSFX", "050"); // Account suffix
		transaction.setFieldValue("GZFIRN", "1"); // First serial number
		transaction.setFieldValue("GZSELN", "0"); // Selected serial number (16P,0)
		transaction.setFieldValue("GZSCRN", "2"); // Deletion type (1P,0)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBBN", "0000"); // Branch number
		transaction.setFieldValue("GZBNO", "000001"); // Customer number
		transaction.setFieldValue("GZSFX", "001"); // Account suffix
		transaction.setFieldValue("GZFIRN", "1"); // First serial number
		transaction.setFieldValue("GZSELN", "0"); // Selected serial number (16P,0)
		transaction.setFieldValue("GZSCRN", "2"); // Deletion type (1P,0)
	}

}
