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
public class ACM extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACM.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "U51FRR";
	String optionId = "ACM";

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
		transaction.setFieldValue("GZECNM", "TEST"); // Event charge component mnemonic (6A)
		transaction.setFieldValue("GZEVNT", "AAH"); // Event mnemonic (6A)
		transaction.setFieldValue("GZCHAN", "EQ4"); // Channel code (6A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZECND", "EQ4 Test"); // Component description (30A)
		transaction.setFieldValue("GZCCD", "AD"); // Charge code (2A)
		transaction.setFieldValue("GZCOST", "0"); // Activity cost (15P,0)
		transaction.setFieldValue("GZCCAL", "2"); // Charge calculation (1A)
		transaction.setFieldValue("GZMRUL", "1"); // Minimum rule (1A)
		transaction.setFieldValue("GZCCY", "GBP"); // Charge currency (3A)
		transaction.setFieldValue("GZFCHG", "15000"); // Flat charge amount (15P,0)
		transaction.setFieldValue("GZPCNT", "0"); // Percentage (11P,7)
		transaction.setFieldValue("GZSPLT", "N"); // Split balance tiers? (1A)
		transaction.setFieldValue("GZNUMT", "N"); // Number tier (1A)
		transaction.setFieldValue("GZRNG1", "999999999999999"); // First range of amount (15P,0)
		transaction.setFieldValue("GZTCH1", "0"); // First tiered charge (15P,0)
		transaction.setFieldValue("GZPRT1", "0"); // First percentage (11P,7)
		transaction.setFieldValue("GZRNG2", "999999999999999"); // Second range of amount (15P,0)
		transaction.setFieldValue("GZTCH2", "0"); // Second tiered charge (15P,0)
		transaction.setFieldValue("GZRNG3", "999999999999999"); // Third range of amount (15P,0)
		transaction.setFieldValue("GZTCH3", "0"); // Third tiered charge (15P,0)
		transaction.setFieldValue("GZTCH3", "0"); // Third tiered charge (15P,0)
		transaction.setFieldValue("GZPRT3", "0"); // Third percentage (11P,7)
		transaction.setFieldValue("GZRNG4", "999999999999999"); // Fourth range of amount (15P,0)
		transaction.setFieldValue("GZTCH4", "0"); // Fourth tiered charge (15P,0)
		transaction.setFieldValue("GZTCH4", "0"); // Fourth tiered charge (15P,0)
		transaction.setFieldValue("GZPRT4", "0"); // Fourth percentage (11P,7)
		transaction.setFieldValue("GZSET", "N"); // Set on? (1A)
		transaction.setFieldValue("GZWAIV", "0"); // Not applicable or waive? (1A)
		transaction.setFieldValue("GZREGF", "N"); // Regular frequency charge? (1A)
		transaction.setFieldValue("GZPROJ", "N"); // Project? (1A)
		transaction.setFieldValue("GZTAXR", "0"); // Tax rate (5P,3)
		transaction.setFieldValue("GZPL1", "950500"); // First P&L Account (6A)
		transaction.setFieldValue("GZPRC1", "100.00"); // First split percentage (5P,2)
		transaction.setFieldValue("GZEXC1", "N"); // Exclude from P&L? (1A)
		transaction.setFieldValue("GZEXC2", "N"); // Exclude from P&L? (1A)
		transaction.setFieldValue("GZEXC3", "N"); // Exclude from P&L? (1A)
		transaction.setFieldValue("GZEXC4", "N"); // Exclude from P&L? (1A)
		transaction.setFieldValue("GZDCI", "DR"); // Dr/Cr (2A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZECND", "EQ4 Test 1"); // Component description (30A)
	}

}
