package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseFully;

/**
 * Equation test cases for Add/Maintain Hold Rule
 */
public class AHO extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AHO.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "K80FRR";
	String optionId = "AHO";

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
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZAPP", "CP"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR,
		transaction.setFieldValue("GZHSET", "CARL"); // Hold set id
		transaction.setFieldValue("GZSQ", "1"); // Internal sequence number
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZHSTD", "Carl's Hold Rule"); // Hold rule description (35A)
		transaction.setFieldValue("GZACTN", "02"); // Action; 1=Debit,2=Credit (2A)
		transaction.setFieldValue("GZINO", "6"); // Internal hold reference (3P,0)
		transaction.setFieldValue("GZUPR", "Y"); // Use principal rate? (1A)
		transaction.setFieldValue("GZOFF", "N"); // Customer offset? (1A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZINO", "7"); // Internal hold reference (3P,0)
	}

}
