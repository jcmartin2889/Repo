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
 * Equation test cases for Add/Maint Overdraft Limit Charges
 */
public class ODL_char_format extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ODL_char_format.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "B26FRR";
	String optionId = "ODL";

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
		transaction.setFieldValue("GZCUS", "ABRAHA");
		transaction.setFieldValue("GZCLC", "ISA");
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCCD", "BR"); // Corporate issuance/renewal charge code (2A)
		transaction.setFieldValue("GZCPC", "10.0000"); // Corporate issuance/renewal percentage (6S,4)

		transaction.setFieldValue("GZCNC", "AC"); // Corporate normal charge code (2A)
		transaction.setFieldValue("GZCNAE", "1150"); // Corporate normal charge amount (15,0)

		transaction.setFieldValue("GZCEC", "AD"); // Corporate excess charge code (2A)
		transaction.setFieldValue("GZCEAE", "1250"); // Corporate excess charge amount (15,0)

		// Individual or Corporate needs to be set based on customer
		// transaction.setFieldValue("GZICD", "A1"); // Individual issuance/renewal charge code (2A)
		// transaction.setFieldValue("GZIPC", "1.0010"); // Individual issuance/renewal percentage (6S,4)

		// transaction.setFieldValue("GZINC", "A3"); // Individual normal charge code (2A)
		// transaction.setFieldValue("GZINAE", "1450"); // Individual normal charge amount (15,0)

		// transaction.setFieldValue("GZIEC", "A4"); // Individual excess charge code (2A)
		// transaction.setFieldValue("GZIEAE", "1550"); // Individual excess charge amount (15,0)

		transaction.setFieldValue("GZMES", "Y"); // Issue a warning message flag (1A)
		transaction.setFieldValue("GZDAY", "10"); // Days to produce renewal letter (2,0)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		// Code and number must be supplied in pairs
		transaction.setFieldValue("GZCCD", "AC"); // Corporate issuance/renewal charge code (2A)
		transaction.setFieldValue("GZCPC", "15.0000"); // Corporate issuance/renewal percentage (6S,4)
	}

}
