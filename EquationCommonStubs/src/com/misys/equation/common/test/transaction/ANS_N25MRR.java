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
 * Equation test cases for Maintain function
 */
public class ANS_N25MRR extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ANS_N25MRR.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "N25MRR";
	String optionId = "ANS";

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
		transaction.setFieldValue("GZCUS", "ATLANT"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", "ZZZ"); // Customer location (3A)
		transaction.setFieldValue("GZGBRC", "LOND"); // Group branch (4A)
		transaction.setFieldValue("GZGDLP", "NNN"); // Group type (3A)
		transaction.setFieldValue("GZGDLR", "0007779999-GBP"); // Group reference (13A)
		transaction.setFieldValue("GZSDTE", "1000105"); // Start date (7S,0)
		transaction.setFieldValue("GZCCY1", "GBP"); // Currency 1 (group) (3A)
		transaction.setFieldValue("GZCCY2", ""); // Currency 2 (group) (3A)
		transaction.setFieldValue("GZSTYP", "A"); // Settlement type (1A)
		transaction.setFieldValue("GZSTS", "U"); // Settlement status (1A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCUS", "ATLANT"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", "IND"); // Customer location (3A)
		transaction.setFieldValue("GZGBRC", "LOND"); // Group branch (4A)
		transaction.setFieldValue("GZGDLP", "XNT"); // Group type (3A)
		transaction.setFieldValue("GZGDLR", "0001050022-GBP"); // Group reference (13A)
		transaction.setFieldValue("GZSDTE", "1000105"); // Start date (7S,0)
		transaction.setFieldValue("GZCCY1", "GBP"); // Currency 1 (group) (3A)
		transaction.setFieldValue("GZCCY2", ""); // Currency 2 (group) (3A)
		transaction.setFieldValue("GZSTYP", "A"); // Settlement type (1A)
		transaction.setFieldValue("GZSTS", "U"); // Settlement status (1A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZSINP", "2"); // Selection field (1A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Deal branch mnemonic (4A)
		transaction.setFieldValue("GZDLP", "FXF"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "CRG-FXF-21"); // Deal reference (13A)
		transaction.setFieldValue("GZCC12", "GBP"); // Currency 1 (deal) (3A)
		transaction.setFieldValue("GZAM12", "30000"); // Deal amount currency 1 (15P,0)
		transaction.setFieldValue("GZCC22", "USD"); // Currency 2 (deal) (3A)
		transaction.setFieldValue("GZAM22", "50000"); // Deal amount currency 2 (15P,0)
	}

}
