package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseAddMaintain;

/**
 * Equation test cases for Add/Maintain Base Rate References
 */
public class ABR extends EquationTestCaseAddMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ABR.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "O06LRR";
	String optionId = "ABR";

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
		transaction.setFieldValue("GZCCYS", "PHP"); // Currency
		transaction.setFieldValue("GZDTE", "1000105"); // Value date
		transaction.setFieldValue("GZREF", "2"); // Base rate reference
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZRAT", "3"); // Base rate
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{

		transaction.setFieldValue("GZRAT", "4"); // Base rate
	}

}
