package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseAddMore;

/**
 * Equation test cases for Add Principal Increase/Decrease
 */
public class API extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: API.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G89ARR";
	String optionId = "API";

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
		transaction.setFieldValue("GZDLP", "VLA"); // Deal type
		transaction.setFieldValue("GZDLR", "X"); // Deal reference
		transaction.setFieldValue("GZBRNM", "ACC1"); // Branch mnemonic
		transaction.setFieldValue("GZDTE", "1000105"); // Movement date
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDLP", "VLA"); // Deal type
		transaction.setFieldValue("GZDLR", "0"); // Deal reference
		transaction.setFieldValue("GZBRNM", "ACC1"); // Branch mnemonic
		transaction.setFieldValue("GZDTE", "1000105"); // Movement date
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZADJ", "100000"); // Amount larger than one transaction
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic
		transaction.setFieldValue("GZABF", "9132"); // Settlement branch
		transaction.setFieldValue("GZANF", "876300"); // Settlement account basic number
		transaction.setFieldValue("GZASF", "826"); // Settlement account suffix
		transaction.setFieldValue("GZINC", "Y"); // Increase/decrease
	}

}
