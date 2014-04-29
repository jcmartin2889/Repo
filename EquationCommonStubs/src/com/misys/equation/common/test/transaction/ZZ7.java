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
 * Equation test cases for Authorise Net Settlements - Details
 */
public class ZZ7 extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ZZ7.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "N25MRR";
	String optionId = "ZZ7";

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
		transaction.setFieldValue("GZGBRC", "LOND"); // Group branch (4A)
		transaction.setFieldValue("GZGDLP", "NNN"); // Group type (3A)
		transaction.setFieldValue("GZGDLR", "0007779999-GBP"); // Group reference (13A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZGBRC", "LOND"); // Group branch (4A)
		transaction.setFieldValue("GZGDLP", "XNT"); // Group type (3A)
		transaction.setFieldValue("GZGDLR", "0001100014GBP"); // Group reference (13A)
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
		transaction.setFieldValue("GZDLR", "2019"); // Deal reference (13A)
		transaction.setFieldValue("GZCC12", "GBP"); // Currency 1 (deal) (3A)
		transaction.setFieldValue("GZAM12", "30000"); // Deal amount currency 1 (15P,0)
		transaction.setFieldValue("GZCC22", "USD"); // Currency 2 (deal) (3A)
		transaction.setFieldValue("GZAM22", "50000"); // Deal amount currency 2 (15P,0)
		transaction.setFieldValue("GZFK17", "1"); // Flag for CF17 (1A)
	}

	// The following tests are not possible with this function:

	@Override
	public void test00700Maint_RetrievalMaintain_ExistingRecord() throws Exception
	{
	}

}
