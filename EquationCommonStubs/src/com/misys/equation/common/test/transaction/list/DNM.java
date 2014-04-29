/**
 * Copyright and all other intellectual property rights in this software, in any form, is vested in Misys International Banking
 * Systems Ltd ("Misys") or a related company.
 * 
 * This software may not be copied, amended, compiled, translated, or developed; or sold, leased, hired, rented, or disclosed to any
 * third party without the prior written consent of Misys.
 * 
 * Copyright Misys International Banking Systems Ltd, 1975 and later
 */

package com.misys.equation.common.test.transaction.list;

import com.misys.equation.common.access.EquationStandardGSListTransaction;

/**
 * Equation test cases for Maintain Deposit Basic Rates
 */
public class DNM extends EquationTestCaseFullyList2
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DNM.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "J12FRR";
	String optionId = "DNM";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		retrieveBeforeCancel = true;
		/*
		 * If the list function has separate Header and Detail modes then set 'retrieveHeader = true', otherwise 'retrieveHeader =
		 * false'.
		 */
		retrieveHeader = false;
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
	public EquationStandardGSListTransaction getTransaction() throws Exception
	{
		EquationStandardGSListTransaction transaction = getEquationStandardTransaction(programName + optionId);
		transaction.setWorkStationId(WORKSTATIONID);
		return transaction;
	}

	// ------------------------------------------------------------------------ Field setups

	/**
	 * Setup a non-existing key fields only
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardGSListTransaction transaction)
	{
		transaction.setFieldValue("GZCCY", "LLL"); // Currency (3A)

	}

	/**
	 * Set up header key fields
	 * 
	 * If the function has separate Header and Detail modes (retrieveHeader = true) then populate the key fields to retrieve the
	 * Header record.
	 */
	@Override
	public void setupHeaderKeyFields(EquationStandardGSListTransaction transaction)
	{
		// TODO: The GZ has NO key fields; the GS has the deal
		transaction.setFieldValue("GZCCY", "LOND"); // Deal Branch (4A)
		transaction.setFieldValue("GZCCY", "FXF"); // Deal Prefix (3A)
		transaction.setFieldValue("GZCCY", "BETHFXF"); // Deal Reference (13A)
	}

	/**
	 * Setup key fields as they are AFTER ADD
	 */
	@Override
	public void setupKeyFieldsAfterAdd(EquationStandardGSListTransaction transaction)
	{
		transaction.setFieldValue("GZCCY", "ARA"); // Currency (3A)
	}

	/**
	 * Setup key fields as they are AFTER MAINTAIN
	 */
	@Override
	public void setupKeyFieldsAfterMaint(EquationStandardGSListTransaction transaction)
	{
		transaction.setFieldValue("GZDECD", "D"); // Header/Detail
		transaction.setFieldValue("GZUSID", "GONZ"); // User mnemonic
		transaction.setFieldValue("GZAPP", "CP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR,
		transaction.setFieldValue("GZPR2", "DOY"); // Old payment type (3A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardGSListTransaction transaction)
	{
		transaction.setFieldValue("GZAMT", "1100000"); // Limit amount (15P,0)
		transaction.setFieldValue("GZCCY", "GBP"); // Limit currency (3A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardGSListTransaction transaction)
	{
		transaction.setFieldValue("GZAMT", "1200000"); // Limit amount (15P,0)
	}

}
