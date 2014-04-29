package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseMaintain;

/**
 * Equation test cases for Maintain Cheque Clearing Date
 */
public class MCC extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MCC.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "T47MRR";
	String optionId = "MCC";

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
		transaction.setFieldValue("GZDREF", "054012310002"); // Deposit reference
		transaction.setFieldValue("GZITM", "1"); // Deposit item number
		transaction.setFieldValue("GZMOP", "3"); // Maintenance option
		transaction.setFieldValue("GZSCD", "1000117"); // Special clearing date
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDREF", "054312310002"); // Deposit reference (12A)
		transaction.setFieldValue("GZITM", "1"); // Deposit item number
		transaction.setFieldValue("GZMOP", "3"); // Maintenance option
		transaction.setFieldValue("GZSCD", "1000117"); // Special clearing date
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
	}

}
