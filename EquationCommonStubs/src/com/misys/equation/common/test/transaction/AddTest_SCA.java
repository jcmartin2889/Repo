package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseFullyAdd;

/**
 * Equation test cases for Cashier System Branch Details
 */
public class AddTest_SCA extends EquationTestCaseFullyAdd
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AddTest_SCA.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "I02FRR";
	String optionId = "SCA";

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
		transaction.setFieldValue("GZATP", "AZ"); // Account type (2A)
		transaction.setFieldValue("GZSTC", "44"); // Status code (2A)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDES", "TEST"); // Status code description (20A)
		transaction.setFieldValue("GZPRDE", "000D"); // Excess period (4A)
		transaction.setFieldValue("GZNAST", "N"); // Into non-accrual (Y/N)? (1A)
		transaction.setFieldValue("GZUSST", "N"); // User status (1A)
		transaction.setFieldValue("GZPCAL", "0"); // Penalty calculation method (1A)
	}

}
