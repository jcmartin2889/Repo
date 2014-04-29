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
 * Equation test cases for Maintain Money Market Deal
 */
public class AddTest_MMM extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AddTest_MMM.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G36MRR";
	String optionId = "MMM";

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
		transaction.setFieldValue("GZDLP", "FDD"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "POGI77"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDLP", "FDD"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "JOPH007"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZSDT", "0991231"); // Start date (7S,0)
		transaction.setFieldValue("GZMDT", "1001231"); // Maturity date (7S,0)
		transaction.setFieldValue("GZCTRD", "0991231"); // Contract date (7S,0)
		transaction.setFieldValue("GZRAT", "15"); // Actual rate (11P,7)
		// transaction.setField("GZPEN", "2"); // Penalty rate (11P,7)
		transaction.setFieldValue("GZYPSF", "N"); // Exclude from positions? (1A)
		transaction.setFieldValue("GZII22", "N"); // Send advices before interest posting date (1A)
		transaction.setFieldValue("GZII23", "N"); // Print interest statement today (1A)
		transaction.setFieldValue("GZII27", "N"); // Print interest statement on cycle date? (1A)
		transaction.setFieldValue("GZII53", "N"); // Minimum monthly balance interest? (1A)
		transaction.setFieldValue("GZIID0", "N"); // Minimum monthly balance allows days of grace? (1A)
		transaction.setFieldValue("GZIIF3", "N"); // Interest to due date? (1A)
	}
}
