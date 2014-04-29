package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseFullyAdd;

/**
 * Equation test cases for Add/Maintain Generic Deal Types
 */
public class AddTest_GDT_Parent extends EquationTestCaseFullyAdd
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AddTest_GDT_Parent.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
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
		transaction.setFieldValue("GZDLP", "PAR"); // Deal type (3A)
		transaction.setFieldValue("GZPRM", "Y"); // Primary? (1A)
		transaction.setFieldValue("GZBDT", "P"); // Basic deal type (1A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDPD", "EQ4 Test"); // Deal type description (35A)
		transaction.setFieldValue("GZACI", "N"); // Allow as collateral item? (1A)
		transaction.setFieldValue("GZACTO", "Y"); // Allow A/C type override? (1A)
		transaction.setFieldValue("GZDIR", "3"); // Deal input requirements (1A)
		transaction.setFieldValue("GZASD", "Y"); // Archive at start of day (1A)
		transaction.setFieldValue("GZFPA", "Y"); // Force new Purchase a/c (1A)
		transaction.setFieldValue("GZEXP", "Y"); // Exclude from positions? (1A)
		transaction.setFieldValue("GZEXL", "Y"); // Exclude from limits? (1A)
		transaction.setFieldValue("GZAGMC", "Y"); // Auto maturity cashflow (1A)
		transaction.setFieldValue("GZAARC", "Y"); // Auto archive deal (1A)
		transaction.setFieldValue("GZRET", "00"); // Deal retention (2A)
	}

}
