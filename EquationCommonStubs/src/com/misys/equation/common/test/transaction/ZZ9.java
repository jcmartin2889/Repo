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
import com.misys.equation.common.test.EquationTestCaseAddMaintain;

/**
 * Equation test cases for Add/Maintain Card Details
 */
public class ZZ9 extends EquationTestCaseAddMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ZZ9.java 4721 2009-09-15 11:14:17Z weddelc1 $";
	// This API only supports Add and Cancel. The key does include the Card Number and Card Sequence.
	// To maintain the Card, the sequence number should be incremented.
	// This API does not support delete.
	// Other APIs should be used for changing Card Status (e.g. BBC)
	String programName = "C30FRR";
	String optionId = "ZZ9";

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
		transaction.setFieldValue("GZAB", "0000"); // Account branch
		transaction.setFieldValue("GZAN", "000001"); // Account basic number
		transaction.setFieldValue("GZAS", "001"); // Account suffix
		transaction.setFieldValue("GZCATP", "CARD"); // Card type
		transaction.setFieldValue("GZCANO", "222222222233"); // Card number
		transaction.setFieldValue("GZCASQ", "1"); // Card sequence
		transaction.setFieldValue("GZBCGC", "N"); // Generate card numbers
	}
	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCNEW", "Y"); // Generate new card?
		transaction.setFieldValue("GZCMOD", "A"); // Mode - this must be 'A' when adding
		transaction.setFieldValue("GZCNM1", "TEST 1"); // Card name 1
	}
	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCMOD", "M"); // Mode - this must be 'M' when maintaining
		transaction.setFieldValue("GZCNM1", "TEST 2"); // Card name 1

	}
}
