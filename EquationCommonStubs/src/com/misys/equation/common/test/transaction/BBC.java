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
 * Equation test cases for Block/Unblock Bank Card
 */
public class BBC extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BBC.java 8213 2010-07-15 16:56:49Z CHALLIP1 $";
	String programName = "C60MRR";
	String optionId = "BBC";

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
	 * Setup a non-existing key fields
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCANO", "1111155555"); // Card number (20A)
		transaction.setFieldValue("GZCASQ", "1"); // Card sequence (1S,0)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCANO", "222222222233"); // Card number (20A)
		transaction.setFieldValue("GZCASQ", "1"); // Card sequence (1S,0)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "0000"); // Account branch number (4A)
		transaction.setFieldValue("GZAN", "000001"); // Account basic number (6A)
		transaction.setFieldValue("GZAS", "001"); // Account suffix (3A)
		transaction.setFieldValue("GZCATP", "CARL"); // Card type (4A)
		transaction.setFieldValue("GZBLDT", "1000105"); // Block date (7S,0)
		transaction.setFieldValue("GZLSDT", "1000105"); // Last status date (7S,0)
		transaction.setFieldValue("GZRPRQ", "N"); // Replacement required? (1A)
		// transaction.setFieldValue("GZUNBK", ""); // Unblock? (1A)
		transaction.setFieldValue("GZSTS", "N"); // Status (1A)
		transaction.setFieldValue("GZBLCD", "5"); // Block code (1A)
	}

	@Override
	public void test00700Maint_RetrievalMaintain_ExistingRecord() throws Exception
	{
		// Cannot maintain the same record twice in succession.
	}
}
