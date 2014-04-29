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
 * Equation test cases for Group Reference (for AGI) function
 * 
 * Note: Posting Sequence number is not controllable
 * 
 */
public class RIN extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RIN.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "A91MRR";
	String optionId = "RIN";

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
		transaction.setFieldValue("GZAB", "9132"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "777777"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "222"); // Account suffix (3A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB", "9132"); // Account branch (4A)
		transaction.setFieldValue("GZAN", "120002"); // Basic part of account number (6A)
		transaction.setFieldValue("GZAS", "003"); // Account suffix (3A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{

		transaction.setFieldValue("GZTRM", "SW"); // Transfer method (2A)
		transaction.setFieldValue("GZIID", ""); // Instruction identifier (10A)
		transaction.setFieldValue("GZBCY", "PHP"); // Beneficiary currency (3A)
		transaction.setFieldValue("GZEXF", ""); // Exchange; 1=Spot, 2=User, 3=Retail (1A)
		transaction.setFieldValue("GZRRT", ""); // Retail rate type (3A)
		transaction.setFieldValue("GZCXM", "N"); // Charge from transfer method? (1A)
		transaction.setFieldValue("GZCCD", ""); // Charge code (2A)
		transaction.setFieldValue("GZCAM", ""); // Charge amount (15P,0)
		transaction.setFieldValue("GZCRM", "N"); // Deduct chg from remittance? (1A)
		transaction.setFieldValue("GZACT", "3"); // Action if no avail balance (2A)
		transaction.setFieldValue("GZMINA", "617250"); // Minimum net payment amount (15P,0)
		transaction.setFieldValue("GZHLD", "N"); // Hold? (1A)
		transaction.setFieldValue("GZRTY", "N"); // Retry failed clean payment? (1A)
	}

}
