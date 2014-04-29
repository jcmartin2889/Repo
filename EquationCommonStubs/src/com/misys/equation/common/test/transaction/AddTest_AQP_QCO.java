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
import com.misys.equation.common.test.EquationTestCaseFullyAdd;

/**
 * Equation test cases for Define Queue Priority
 */
public class AddTest_AQP_QCO extends EquationTestCaseFullyAdd
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AddTest_AQP_QCO.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "Q31FRR";
	String optionId = "AQP";

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
	 * Setup a non-existing key fields only
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZEVM", "QCO"); // Event (6A)
		transaction.setFieldValue("GZTCD", "010"); // Transaction code (3A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDSC", "Inter Account Xfer"); // Description (20A)
		transaction.setFieldValue("GZPLC", "Y"); // Queue today local ccy (1A)
		transaction.setFieldValue("GZPFC", "Y"); // Queue today foreign ccy (1A)
		transaction.setFieldValue("GZTLC", "N"); // Queue if future dated - local ccy (1A)
		transaction.setFieldValue("GZTFR", "N"); // Queue if future dated - foreign ccy (1A)
		transaction.setFieldValue("GZQSC", "N"); // Queue if SC is on (1A)
		transaction.setFieldValue("GZPTY", "00020"); // Priority code (5A)
		transaction.setFieldValue("GZMPY", "000"); // Minor priority (3A)
		transaction.setFieldValue("GZDRN", "Y"); // Allow overdrawn? (1A)
		transaction.setFieldValue("GZCVR", "N"); // Allow customer override? (1A)
		transaction.setFieldValue("GZPAY", "N"); // Allow partial payment? (1A)
		transaction.setFieldValue("GZRET", "000"); // Days to retain archived (3A)
	}

}