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
 * Equation test cases for Maintain FX Currency Gap Limits
 */
public class CGL extends EquationTestCaseFullyList2
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CGL.java 11502 2011-07-25 10:18:46Z challip1 $";
	String programName = "N20FRR";
	String optionId = "CGL";

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
		transaction.setFieldValue("GZCCY", "ARA"); // Currency (3A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch (4A)
		transaction.setFieldValue("GZLED", "1010105"); // Expiry Date (7Z)
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
		transaction.setFieldValue("GZCCY", "ARA"); // Currency (3A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch (4A)
		transaction.setFieldValue("GZLED", "1010105"); // Expiry Date (7Z)

	}

	/**
	 * Setup key fields as they are AFTER ADD
	 */
	@Override
	public void setupKeyFieldsAfterAdd(EquationStandardGSListTransaction transaction)
	{
		transaction.setFieldValue("GZCCY", "ARA"); // Currency (3A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch (4A)
	}

	/**
	 * Setup key fields as they are AFTER MAINTAIN
	 */
	@Override
	public void setupKeyFieldsAfterMaint(EquationStandardGSListTransaction transaction)
	{
		transaction.setFieldValue("GZCCY", "ARA"); // Currency (3A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch (4A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardGSListTransaction transaction)
	{
		transaction.setFieldValue("GZXLCY", "USD"); // Currency (3A)
		transaction.clearList();
		transaction.addRow();
		transaction.setFieldValue("GSIMG", "A"); // Image (1A,0)
		transaction.setFieldValue("GSNBP", "2"); // Number of days overdue (3P,0)
		transaction.setFieldValue("GSTMP", "M"); // Time period (1A)
		transaction.setFieldValue("GSXGL", "12312435"); // Period gap limit (15P,0)
		transaction.setFieldValue("GSXCL", "654345354"); // Cumulative gap limit (15P,0)
		transaction.addRow();
		transaction.setFieldValue("GSIMG", "A"); // Image (1A,0)
		transaction.setFieldValue("GSNBP", "21"); // Number of days overdue (3P,0)
		transaction.setFieldValue("GSTMP", "D"); // Time period (1A)
		transaction.setFieldValue("GSXGL", "212121"); // Period gap limit (15P,0)
		transaction.setFieldValue("GSXCL", "212121"); // Cumulative gap limit (15P,0)
		transaction.addRow();

	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardGSListTransaction transaction)
	{
		// GS maintenance:
		transaction.addRow();
		transaction.setFieldValue("GSIMG", "A"); // Image (1A,0)
		transaction.setFieldValue("GSNBP", "1"); // Number of days overdue (3P,0)
		transaction.setFieldValue("GSTMP", "M"); // Time period (1A)
		transaction.setFieldValue("GSXGL", "666666"); // Period gap limit (15P,0)
		transaction.setFieldValue("GSXCL", "777777"); // Cumulative gap limit (15P,0)
	}

}
