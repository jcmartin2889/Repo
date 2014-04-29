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
import com.misys.equation.common.test.EquationTestCaseFully;

/**
 * Equation test cases for Add/Maintain Generic Deal Cashflow function
 */
public class GCM extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GCM.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "U38LRR";
	String optionId = "GCM";

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
	 * Setup a non-existing key fields only
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBRNM", "ACC1"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZDLP", "EQ4"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "TEST6"); // Deal reference (13A)

		//transaction.setFieldValue("GSSEQ2", "001"); // Deal Seq (3A)
		//transaction.setFieldValue("GSDTP", "EQ4"); // Deal type (3A)
		//transaction.setFieldValue("GSCTP", "I"); // Cash type (2A)
		//transaction.setFieldValue("GSDTE", "1000105"); // Date (6A)
		//transaction.setFieldValue("GSDTEZ", "050100"); // Date (6A)

	}

	
	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		//transaction.setFieldValue("GSCSQ", "000"); // Cash seq (3A)
		//transaction.setFieldValue("GSARC", "N"); // Archive (1A)
		//transaction.setFieldValue("GSCCY", "GBP"); // Currency (3A)
		//transaction.setFieldValue("GSAMTZ", "1234"); // Amount (20A)
		//transaction.setFieldValue("GSPRF", "R"); // Pay or receive (1A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		//transaction.setFieldValue("GSAMTZ", "4321"); // Amount (20A)
	}

}
