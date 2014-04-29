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
import com.misys.equation.common.test.EquationTestCaseFullyList;

/**
 * Equation test cases for functions that have a list (subfile) mode where records can be maintained individually and the key fields
 * change after the Add and/or Maintain.
 * 
 */
public class RAT_List extends EquationTestCaseFullyList
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RAT_List.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "K51LRR";
	String optionId = "RAT";

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
		retrieveHeader = true;
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
		transaction.setFieldValue("GZTYP", "D"); // Header/Detail (1A)
		transaction.setFieldValue("GZAPP", "CP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		transaction.setFieldValue("GZPR", "4CN"); // Product type (3A)
		transaction.setFieldValue("GZCTP", "  "); // Customer type (2A)
		transaction.setFieldValue("GZAMT2", "1500000"); // New payment amount range (15P,0)
	}

	/**
	 * Set up header key fields
	 * 
	 * If the function has separate Header and Detail modes (retrieveHeader = true) then populate the key fields to retrieve the
	 * Header record.
	 */
	@Override
	public void setupHeaderKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTYP", "H"); // Header/Detail (1A)
		transaction.setFieldValue("GZAPP", "CP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		transaction.setFieldValue("GZPR", "4CN"); // Product type (3A)
		transaction.setFieldValue("GZCTP", "  "); // Customer type (2A)
	}

	/**
	 * Setup key fields as they are AFTER ADD
	 * 
	 * (Note that both Old and New amounts are populated.)
	 */
	@Override
	public void setupKeyFieldsAfterAdd(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTYP", "D"); // Header/Detail (1A)
		transaction.setFieldValue("GZAPP", "CP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		transaction.setFieldValue("GZPR", "4CN"); // Product type (3A)
		transaction.setFieldValue("GZCTP", "  "); // Customer type (2A)
		transaction.setFieldValue("GZAMT1", "1500000"); // Old payment amount range (15P,0)
		transaction.setFieldValue("GZAMT2", "1500000"); // New payment amount range (15P,0)
	}

	/**
	 * Setup key fields as they are AFTER MAINTAIN
	 * 
	 * (Note that both Old and New amounts are populated.)
	 */
	@Override
	public void setupKeyFieldsAfterMaint(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTYP", "D"); // Header/Detail (1A)
		transaction.setFieldValue("GZAPP", "CP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
		transaction.setFieldValue("GZPR", "4CN"); // Product type (3A)
		transaction.setFieldValue("GZCTP", "  "); // Customer type (2A)
		transaction.setFieldValue("GZAMT1", "1600000"); // Old payment amount range (15P,0)
		transaction.setFieldValue("GZAMT2", "1600000"); // New payment amount range (15P,0)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZNOA", "1"); // No. of authorisers (1P,0)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAMT2", "1600000"); // New payment amount range (15P,0)
		transaction.setFieldValue("GZNOA", "2"); // No. of authorisers (1P,0)
	}
}
