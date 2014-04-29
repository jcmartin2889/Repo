package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseAddMore;

/**
 * Equation test cases for Add Cheque Deposit - Cust CR
 */
public class ACD extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACD.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "J49ARR";
	String optionId = "ACD";

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
		transaction.setFieldValue("GZ9DRF", "913012310024"); // Deposit reference
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZ9CAB", "0000"); // Customer credit branch number (4A)
		transaction.setFieldValue("GZ9CAN", "002015"); // Customer credit customer number (6A)
		transaction.setFieldValue("GZ9CAS", "001"); // Customer credit suffix (3A)
		transaction.setFieldValue("GZ9TCD", "515"); // Customer credit trancode (3A)
		transaction.setFieldValue("GZ9AMT", "100000"); // Deposit amount (15P,0)
		transaction.setFieldValue("GZ9CCY", "PHP"); // Deposit currency (3A)
		transaction.setFieldValue("GZ9ITM", "1"); // Number of items in deposit (3P,0)
	}

}
