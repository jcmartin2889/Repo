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
 * Equation test cases for Settlement Instructions related to Add Standing Order
 * 
 * Settlement Instructions API must be used before the Standing Order API Refer to ASO_2_StandingOrder for an example.
 */
public class ASO_1_Settlement extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ASO_1_Settlement.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "K42FRR";
	String optionId = "MSD";

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
		// GZOID does not appear on the screen, but is needed for the java program
		transaction.setFieldValue("GZOID", "ASO"); // Menu option id
		transaction.setFieldValue("GZAB", "0000"); // Instruction a/c branch
		transaction.setFieldValue("GZAN", "000001"); // Instruction a/c number
		transaction.setFieldValue("GZAS", "001"); // Instruction a/c suffix
		transaction.setFieldValue("GZPYT", "SO "); // Settlement type
		transaction.setFieldValue("GZREF", "CP1REF01        "); // Settlement reference
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{

		transaction.setFieldValue("GZCFG", "N"); // Continuation flag (1A)

		transaction.setFieldValue("GZABR", "N"); // Abbreviated instructions? (1A)
		transaction.setFieldValue("GZCTC", "510"); // Credit transaction code (3A)
		transaction.setFieldValue("GZCCY", "GBP"); // Credit currency mnemonic (3A)
		transaction.setFieldValue("GZDTC", "010"); // Debit transaction code (3A)
		transaction.setFieldValue("GZDCY", "USD"); // Debit currency mnemonic (3A)

		transaction.setFieldValue("GZTRM1", "AC"); // Receive transfer method (2A)
		transaction.setFieldValue("GZAB2", "0132"); // Pay a/c branch (4A)
		transaction.setFieldValue("GZAN2", "012008"); // Pay a/c number (6A)
		transaction.setFieldValue("GZAS2", "050"); // Pay a/c suffix (3A)

	}

}
