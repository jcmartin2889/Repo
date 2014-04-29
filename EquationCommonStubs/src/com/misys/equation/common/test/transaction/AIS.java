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
import com.misys.equation.common.test.EquationTestCaseAddOnce;

/**
 * @author weddelc1
 */
public class AIS extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AIS.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G37ARR";
	String optionId = "AIS";

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
		transaction.setFieldValue("GZDLP", "SWF"); // Forward deal type (3A)
		transaction.setFieldValue("GZDLR", "CRG-SWF-12"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZSCCY", "USD"); // Source currency (3A)
		transaction.setFieldValue("GZSSAM", "700000"); // Source spot amount (15P,0)
		transaction.setFieldValue("GZDCCY", "EUR"); // Derived currency (3A)
		transaction.setFieldValue("GZSEXT", "1.18"); // Spot exchange rate (15P,9)
		transaction.setFieldValue("GZSPTD", "1000105"); // Spot date (7S,0)
		transaction.setFieldValue("GZFRWD", "1000107"); // Forward date (7S,0)
		transaction.setFieldValue("GZFEXT", "1.25"); // Forward exchange rate (15P,9)
		transaction.setFieldValue("GZCIN", "S"); // Cost of funds indicator; S-Source, D-Derived (1A)
		transaction.setFieldValue("GZUSID", "OBOG"); // User identifier (4A)
	}

}
