package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseFullyCancel;

/**
 * Equation test cases for Cashier System Branch Details
 */
public class CancelTest_MAT_Generic extends EquationTestCaseFullyCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CancelTest_MAT_Generic.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "I09FRR";
	String optionId = "MAT";

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
		transaction.setFieldValue("GZATP", "GT"); // Account type
		transaction.setFieldValue("GZAPP", "GD"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR,
		transaction.setFieldValue("GZACS", "T"); // Account structure
	}

}
