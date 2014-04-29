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
public class FSI extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FSI.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "I10ARR";
	String optionId = "FSI";

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
		transaction.setFieldValue("GZSDLP", "MSS"); // Deal type 1st leg (3A)
		transaction.setFieldValue("GZDLR", "TST8"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZSCUS", "ABROL"); // Customer mnemonic 1st leg (6A)
		transaction.setFieldValue("GZSCLC", "003"); // Customer location 1st leg (3A)
		transaction.setFieldValue("GZSPCY", "USD"); // Purchase currency 1st leg (3A)
		transaction.setFieldValue("GZFPCY", "USD"); // Purchase currency 2nd leg (3A)
		transaction.setFieldValue("GZSPAM", "100000"); // Purchase amount 1st leg (15P,0)
		transaction.setFieldValue("GZFPAM", "100000"); // Purchase amount 2nd leg (15P,0)
		transaction.setFieldValue("GZSSCY", "USD"); // Sale currency 1st leg (3A)
		transaction.setFieldValue("GZFSCY", "USD"); // Sale currency 2nd leg (3A)
		transaction.setFieldValue("GZSSAM", "200000"); // Sale amount 1st leg (15P,0)
		transaction.setFieldValue("GZFSAM", "200000"); // Sale amount 2nd leg (15P,0)
		transaction.setFieldValue("GZSEXT", ".5000000"); // Exchange rate 1st leg (15P,9)
		transaction.setFieldValue("GZFEXT", ".5000000"); // Exchange rate 2nd leg (15P,9)
		transaction.setFieldValue("GZSPDT", "1000105"); // Purchase date 1st leg (7S,0)
		transaction.setFieldValue("GZFPDT", "1000105"); // Purchase date 2nd leg (7S,0)
		transaction.setFieldValue("GZSSDT", "1000105"); // Sale date 1st leg (7S,0)
		transaction.setFieldValue("GZFSDT", "1000105"); // Sale date 2nd leg (7S,0)
		transaction.setFieldValue("GZCTRD", "991231"); // Contract date (7S,0)
		transaction.setFieldValue("GZSPXC", "USD"); // Spot pay settlement currency (3A)
		transaction.setFieldValue("GZSRXC", "USD"); // Spot receive settlement currency (3A)
		transaction.setFieldValue("GZFPXC", "USD"); // Forward pay settlement currency (3A)
		transaction.setFieldValue("GZFRXC", "USD"); // Forward receive settlement currency (3A)
		transaction.setFieldValue("GZSRNS", "AB1USD"); // Purchase nostro 1st leg (6A)
		transaction.setFieldValue("GZFRNS", "AB1USD"); // Purchase nostro 2nd leg (6A)
		transaction.setFieldValue("GZFPNS", "AB1USD"); // Sale nostro 2nd leg (6A)
		transaction.setFieldValue("GZSPNS", "AB1USD"); // Sale nostro 1st leg (6A)
	}

}
