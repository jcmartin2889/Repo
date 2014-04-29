package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseAddOnce;

/**
 * Equation test cases for New Commitment
 */
public class NCM_CB1 extends EquationTestCaseAddOnce
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: NCM_CB1.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H09ARR";
	String optionId = "NCM";

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
		transaction.setFieldValue("GZCMR", "BIANZ"); // Commitment reference
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCMR", "BIANZ"); // Commitment reference
		transaction.setFieldValue("GZBRNM", "ACC1"); // Branch mnemonic
		transaction.setFieldValue("GZCUS", "CARL"); // Customer mnemonic
		transaction.setFieldValue("GZCLC", "CTY"); // Customer location
		transaction.setFieldValue("GZASC", "601"); // Commitment account suffix
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic
		transaction.setFieldValue("GZAMT", "20000"); // Original commitment amount
		transaction.setFieldValue("GZDTEA", "991231"); // Agreement date
		transaction.setFieldValue("GZDTEF", "991231"); // First drawdown date
		transaction.setFieldValue("GZDTEX", "9999999"); // Expiry date
		transaction.setFieldValue("GZDTER", "9999999"); // Final repayment date
		transaction.setFieldValue("GZYRV", "N"); // Revolving status
		transaction.setFieldValue("GZYMB", "N"); // Multi-branch
	}

}
