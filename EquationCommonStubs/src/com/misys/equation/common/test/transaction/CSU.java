package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseMaintain;

/**
 * Equation test cases for Consolidated Customer Stmt set up
 */
public class CSU extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CSU.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "D14MRR";
	String optionId = "CSU";

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
		transaction.setFieldValue("GZCUS", "ELWCSU"); // Customer mnemonic
		transaction.setFieldValue("GZCLC", "001"); // Customer location
		transaction.setFieldValue("GZCAB", "0543"); // Account branch
		transaction.setFieldValue("GZCPNC", "100231"); // Customer's basic number
		transaction.setFieldValue("GZCAS", "002"); // Account suffix
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCUS", "DINA1"); // Customer mnemonic
		transaction.setFieldValue("GZCLC", ""); // Customer location
		transaction.setFieldValue("GZCAB", "0132"); // Account branch
		transaction.setFieldValue("GZCPNC", "666666"); // Customer's basic number
		transaction.setFieldValue("GZCAS", "100"); // Account suffix
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAIK2", "Y"); // Suppress summary
		transaction.setFieldValue("GZAIK3", "Y"); // Suppress detailed
	}

}
