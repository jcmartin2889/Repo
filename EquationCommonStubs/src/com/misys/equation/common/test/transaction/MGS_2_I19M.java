/**
sdf * Copyright and all other intellectual property rights in this software, in any form, is vested in Misys International Banking
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
 * Equation test cases for Calculate Generated Schedule function
 */
public class MGS_2_I19M extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MGS_2_I19M.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "I19MRR";
	String optionId = "MGS";

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
		transaction.setFieldValue("GZDLP", "CR3"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "0991231000099"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "HEAD"); // Branch mnemonic (4A)
		// transaction.setField("GZRFR", "1010109"); // EIR recalculation value date (7S,0)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDLP", "CR3"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "0991231000004"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "HEAD"); // Branch mnemonic (4A)
		// transaction.setField("GZRFR", "1000109"); // EIR recalculation value date (7S,0)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{

	}

}
