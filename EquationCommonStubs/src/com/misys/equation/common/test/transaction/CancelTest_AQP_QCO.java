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
import com.misys.equation.common.test.EquationTestCaseFullyCancel;

/**
 * Equation test cases for Maintain function
 */
public class CancelTest_AQP_QCO extends EquationTestCaseFullyCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CancelTest_AQP_QCO.java 8426 2010-07-29 17:32:49Z CHALLIP1 $";
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
	 * Setup a non-existing key fields only
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZEVM", "QCO"); // Event (6A)
		transaction.setFieldValue("GZTCD", "010"); // Transaction code (3A)
		transaction.setFieldValue("GZPTY", "00020"); // Priority code (5A)

		// transaction.setField("GZEVM", ""); // Event (6A)
		// transaction.setField("GZTCD", ""); // Transaction code (3A)
		// transaction.setField("GZCHG", ""); // Charge event (6A)
		// transaction.setField("GZCPG", ""); // Charge priority group (1A)
		// transaction.setField("GZDSC", ""); // Description (20A)
		// transaction.setField("GZPLC", ""); // Queue today local ccy (1A)
		// transaction.setField("GZPFC", ""); // Queue today foreign ccy (1A)
		// transaction.setField("GZTLC", ""); // Queue if future dated - local ccy (1A)
		// transaction.setField("GZTFR", ""); // Queue if future dated - foreign ccy (1A)
		// transaction.setField("GZQSC", ""); // Queue if SC is on (1A)
		// transaction.setField("GZPTY", ""); // Priority code (5A)
		// transaction.setField("GZMPY", ""); // Minor priority (3A)
		// transaction.setField("GZDRN", ""); // Allow overdrawn? (1A)
		// transaction.setField("GZDRY", ""); // Days to retry (3A)
		// transaction.setField("GZMRY", ""); // Months to retry (2A)
		// transaction.setField("GZCVR", ""); // Allow customer override? (1A)
		// transaction.setField("GZPAY", ""); // Allow partial payment? (1A)
		// transaction.setField("GZRET", ""); // Days to retain archived (3A)

	}

}
