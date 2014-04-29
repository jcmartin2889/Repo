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
 * Equation test cases for Maintain Customer Basic Details
 */
public class MCD extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MCD.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G01MRR";
	String optionId = "MCD";

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
		transaction.setFieldValue("GZCLC", "PS1"); // Customer location (3A)
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCUS", "ATLANT"); // Customer mnemonic (6A)
		transaction.setFieldValue("GZCLC", "PS"); // Customer location (3A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCUN", "ATLANTIC INDUSTRIAL PARIS"); // Customer full name (35A)
		transaction.setFieldValue("GZCUNA", ""); // Arabic customer full name (35A)
		transaction.setFieldValue("GZDAS", "ATLANTIC PARIS"); // Default account short name (15A)
		transaction.setFieldValue("GZDASA", ""); // Arabic account short name (15A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
		transaction.setFieldValue("GZGRP", "ATLEUR"); // Group name (6A)
		transaction.setFieldValue("GZCA2", "ML"); // Sundry reference code (2A)
		transaction.setFieldValue("GZCTP", "AB"); // Customer type (2A)
		transaction.setFieldValue("GZCNAP", "GB"); // Parent country (2A)
		transaction.setFieldValue("GZCNAL", "FR"); // Residence country (2A)
		transaction.setFieldValue("GZCRB1", "00"); // Tax code 1 (2A)
		transaction.setFieldValue("GZCRB2", "00"); // Tax code 2 (2A)
		transaction.setFieldValue("GZCS", "Y"); // Consolidated statements (1A)

		transaction.setFieldValue("GZCPNC", "123633"); // Customer's basic number
		transaction.setFieldValue("GZCUBD", "N"); // Blocked for deal input? --
		transaction.setFieldValue("GZCRF", ""); // Customer reference
		transaction.setFieldValue("GZCNAR", "FR");// Risk country --
		transaction.setFieldValue("GZCNAI", "FR"); // Internal risk country

	}

}
