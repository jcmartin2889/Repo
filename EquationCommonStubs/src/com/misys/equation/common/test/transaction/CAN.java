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
 * Equation test cases for Maintain function
 */
public class CAN extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CAN.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	// Note this is the effectively identical to CTU
	String programName = "U14FRR";
	String optionId = "CAN";

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
		transaction.setFieldValue("GZDLP", "DLD"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "1002"); // Deal reference (13A)
		transaction.setFieldValue("GZDBNM", "LOND"); // Branch mnemonic (4A)
		/*
		 * For external input, one of the following sets of fields are also required as key fields: GZCLR - Collateral Reference
		 * GZCDLP / GZCDLR / GZCDBN - Collateral Deal GZCAB / GZCAN / GZCAS - Collateral Account
		 */
		transaction.setFieldValue("GZCLR", "CN146863 COLL REF"); // Collateral deal reference (13A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZASR", "1"); // Assignment rule (1A)
		/*
		 * If no value is entered for the percentage (i.e. Assignment Rule 1 or 2) then the percentage is stored on the database as
		 * '99.999'. This means instead of entering a zero or blank value for GZASP, you must enter '99.999'.
		 */
		transaction.setFieldValue("GZASP", "99.999"); // Assignment percentage (5P,3)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZASR", "3"); // Assignment rule (1A)
		// e.g. 25% is stored on the database as '0.250' (entered on screen as '25')
		transaction.setFieldValue("GZASP", "0.250"); // Assignment percentage (5P,3)
		/*
		 * If the Assignment Rule is '3' then Maximum Amount can not be entered, but it is stored on the database as
		 * '-999999999999999' (!). So for external input, if GZASR is '3' then GZMAX must be '-999999999999999' not zero or blanks.
		 */
		transaction.setFieldValue("GZMAX", "-999999999999999"); // Maximum Amount (15P,0)
	}

}
