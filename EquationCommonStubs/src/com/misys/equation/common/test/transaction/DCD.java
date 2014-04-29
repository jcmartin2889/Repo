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
 * Equation test cases for Delete Cheque Book Request
 */
public class DCD extends EquationTestCaseCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DCD.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "J49CRR";
	String optionId = "DCD";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		retrieveBeforeCancel = false;
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
		transaction.setFieldValue("GZ9DRF", "913012310024"); // Deposit reference
		transaction.setFieldValue("GZ9CAB", "0000"); // Customer credit branch number
		transaction.setFieldValue("GZ9CAN", "002015"); // Customer credit customer number
		transaction.setFieldValue("GZ9CAS", "001"); // Customer credit suffix
		transaction.setFieldValue("GZ9DEL", "F"); // Full or partial delete?

	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZ9DRF", "054301050001"); // Deposit reference
		transaction.setFieldValue("GZ9CAB", "9132"); // Customer credit branch number
		transaction.setFieldValue("GZ9CAN", "666666"); // Customer credit customer number
		transaction.setFieldValue("GZ9CAS", "005"); // Customer credit suffix
		transaction.setFieldValue("GZ9DEL", "F"); // Full or partial delete?
	}

}
