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
 * Equation test cases for Maintain Collection Status
 */
public class OKQ extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OKQ.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "W25MRR";
	String optionId = "OKQ";

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
		transaction.setFieldValue("GZBPN", "054355558888"); // Paper Number (12A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBPN", "054388889999"); // Paper Number (12A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZMDTE", "1000105"); // Maturity Date (7S,0)
		transaction.setFieldValue("GZBAB", ""); // A/c branch (4A)
		transaction.setFieldValue("GZBAN", ""); // A/c number (6A)
		transaction.setFieldValue("GZBAS", ""); // A/c suffix (3A)
		transaction.setFieldValue("GZMOS", "AX"); // Method of Settlement (2A)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency (3A)
		transaction.setFieldValue("GZAMT", "410000"); // Amount (15P,0)
		transaction.setFieldValue("GZVDTE", "1000106"); // Value Date (7S,0)
		transaction.setFieldValue("GZSTS", "EE"); // Status (2A)
		transaction.setFieldValue("GZNAMT", "300"); // Net Amount (15P,0)
	}
	/**
	 * Cannot Maintain the same record
	 */
	@Override
	public void test00700Maint_RetrievalMaintain_ExistingRecord() throws Exception
	{
	}
}
