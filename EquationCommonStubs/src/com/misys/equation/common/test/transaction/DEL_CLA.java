package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseCancel;

/**
 * Equation test cases for Close Customer Account
 */
public class DEL_CLA extends EquationTestCaseCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DEL_CLA.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "I34CRR";
	String optionId = "CLA";

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
	 * Setup a non-existing key fields
	 */
	@Override
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB1", "0132"); // Account branch
		transaction.setFieldValue("GZAN1", "012008"); // Basic number
		transaction.setFieldValue("GZAS1", "059"); // Account suffix
		transaction.setFieldValue("GZDRT", "010"); // Dr code
		transaction.setFieldValue("GZCRT", "510"); // Cr code
		transaction.setFieldValue("GZPRTS", "Y"); // Statement required
		transaction.setFieldValue("GZCONR", "Y"); // Confirmation required
		transaction.setFieldValue("GZCHGE", "N"); // Recalculate charge
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAB1", "0132"); // Account branch
		transaction.setFieldValue("GZAN1", "012008"); // Basic number
		transaction.setFieldValue("GZAS1", "013"); // Account suffix
		transaction.setFieldValue("GZDRT", "010"); // Dr code
		transaction.setFieldValue("GZCRT", "510"); // Cr code
		transaction.setFieldValue("GZPRTS", "Y"); // Statement required
		transaction.setFieldValue("GZCONR", "Y"); // Confirmation required
		transaction.setFieldValue("GZCHGE", "N"); // Recalculate charge
	}

}