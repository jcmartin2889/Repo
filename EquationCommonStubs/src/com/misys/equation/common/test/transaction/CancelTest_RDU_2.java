package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseFullyCancel;

/**
 * Equation test cases for Cashier System Branch Details
 */
public class CancelTest_RDU_2 extends EquationTestCaseFullyCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CancelTest_RDU_2.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G36CRR";
	String optionId = "CMM";

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
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDLP", "BO1"); // Deal type (3A)
		transaction.setFieldValue("GZDLR", "EQUATION4"); // Deal reference (13A)
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
	}

}