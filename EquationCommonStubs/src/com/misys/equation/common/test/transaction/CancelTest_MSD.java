package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseFullyCancel;

/**
 * Equation test cases for Cashier System Branch Details
 */
public class CancelTest_MSD extends EquationTestCaseFullyCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CancelTest_MSD.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "K42FRR";
	String optionId = "MSD";

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
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZOID", "CUW"); // Menu option id
		transaction.setFieldValue("GZREF", "TEST-06"); // Settlement reference
		transaction.setFieldValue("GZPYT", "NSA"); // Settlement type
		transaction.setFieldValue("GZAB", "3210"); // Instruction a/c branch
		transaction.setFieldValue("GZAN", "137012"); // Instruction a/c number
		transaction.setFieldValue("GZAS", "002"); // Instruction a/c suffix
	}

}
