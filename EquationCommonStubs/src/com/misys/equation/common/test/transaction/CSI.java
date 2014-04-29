package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseCancel;
import com.misys.equation.common.test.TestEnvironment;

/**
 * Equation test cases for Cancel Sundry Item
 */
public class CSI extends EquationTestCaseCancel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CSI.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H15CRR";
	String optionId = "CSI";
	String addOptionId = "ASI";

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
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic
		transaction.setFieldValue("GZPBR", "JUNIT "); // Posting group id or user id
		transaction.setFieldValue("GZPSQ7", "99999"); // Posting sequence number
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic
		transaction.setFieldValue("GZPBR", "JUNT "); // Posting group id or user id
		String reference = TestEnvironment.getTestEnvironment().getParameter(addOptionId);
		if (reference != null)
		{
			transaction.setFieldValue("GZPSQ7", reference); // Transaction refer
		}
	}

}
