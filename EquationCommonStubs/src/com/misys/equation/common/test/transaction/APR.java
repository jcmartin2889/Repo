package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseFully;

/**
 * Equation test cases for Add/Maintain Posting Rule
 */
public class APR extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: APR.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "K78FRR";
	String optionId = "APR";

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
		transaction.setFieldValue("GZPCF", "P"); // Rule instruction type; P=Posting,C=Charge
		transaction.setFieldValue("GZAPP", "CP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR,
		transaction.setFieldValue("GZPSET", "CARL"); // Posting set id
		transaction.setFieldValue("GZSQ", "1"); // Internal sequence number
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZPSTD", "Carl's Posting Rule"); // Posting rule description
		transaction.setFieldValue("GZACTN", "01"); // Action; 1=Debit,2=Credit
		transaction.setFieldValue("GZCTG", "N"); // Contingent?
		transaction.setFieldValue("GZINT", "N"); // Internal?
		transaction.setFieldValue("GZACRF", "01"); // A/c reference
		transaction.setFieldValue("GZCCY", "PHP"); // Currency
		transaction.setFieldValue("GZTCD", "FD1"); // Transaction code
		transaction.setFieldValue("GZAMT", "100000"); // Amount
		transaction.setFieldValue("GZUPR", "Y"); // Use principal rate?
		transaction.setFieldValue("GZOFF", "N"); // Customer offset?
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZTCD", "FD2"); // Transaction code
	}

}
