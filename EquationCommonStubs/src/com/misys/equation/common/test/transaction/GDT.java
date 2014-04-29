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
 * Equation test cases for Add/Maintain Generic Deal Types
 */
public class GDT extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GDT.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "U30FRR";
	String optionId = "GDT";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		retrieveBeforeCancel = true;
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
		transaction.setFieldValue("GZDLP", "CHV"); // Deal type (3A)
		transaction.setFieldValue("GZPRM", "Y"); // Primary? (1A)
		transaction.setFieldValue("GZBDT", "T"); // Basic deal type (1A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDPD", "Joph Test"); // Deal type description (35A)
		transaction.setFieldValue("GZTDT", "D"); // Term deal type (1A)
		transaction.setFieldValue("GZFDA", "N"); // Force new deal a/c (1A)
		transaction.setFieldValue("GZACTO", "Y"); // Allow A/C type override? (1A)
		transaction.setFieldValue("GZEXP", "N"); // Exclude from positions? (1A)
		transaction.setFieldValue("GZEXL", "N"); // Exclude from limits? (1A)
		transaction.setFieldValue("GZNOT", "N"); // Notional amounts? (1A)
		transaction.setFieldValue("GZCLT", "N"); // Collateral required (1A)
		transaction.setFieldValue("GZACI", "N"); // Allow as collateral item? (1A)
		transaction.setFieldValue("GZDIR", "2"); // Deal input requirements (1A)
		transaction.setFieldValue("GZAGSC", "N"); // Auto generate strt cashflow? (1A)
		transaction.setFieldValue("GZAGMC", "N"); // Auto gen maturity cashflow? (1A)
		transaction.setFieldValue("GZAARC", "N"); // Auto archive? (1A)
		transaction.setFieldValue("GZASD", "N"); // Archive at start of day (1A)
		transaction.setFieldValue("GZEFS", "N"); // Extended funds summary? (1A)
		transaction.setFieldValue("GZACC", "N"); // Amortized cost calculation required? (1A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDPD", "Joph Test-Maintainance"); // Deal type description (35A)

	}

}
