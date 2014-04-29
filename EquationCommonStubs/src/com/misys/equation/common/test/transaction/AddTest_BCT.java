package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseFullyAdd;

/**
 * Equation test cases for Add/Maintain Card Type
 */
public class AddTest_BCT extends EquationTestCaseFullyAdd
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AddTest_BCT.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "C35FRR";
	String optionId = "BCT";

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
		transaction.setFieldValue("GZCATP", "CARD"); // Card type
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCTDS", "ZZ9 Type"); // Card type description
		transaction.setFieldValue("GZCACL", "1"); // Card class
		transaction.setFieldValue("GZCLCD", "1"); // Card limit group
		transaction.setFieldValue("GZACLC", "1"); // Authorising centre limit code
		transaction.setFieldValue("GZCALN", "12"); // Card number length
		transaction.setFieldValue("GZCFMT", "999999999999"); // Card number format
		transaction.setFieldValue("GZFXDT", "0"); // Number of fixed digits
		transaction.setFieldValue("GZCSTN", "200000000000"); // Card first number
		transaction.setFieldValue("GZCEDN", "299999999999"); // Card end number
		transaction.setFieldValue("GZMXC", "10"); // Maximum no for account
		transaction.setFieldValue("GZCRW", "60D"); // Card renewal
		transaction.setFieldValue("GZCRP", "12"); // Card retention period
	}

}
