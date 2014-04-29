package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseAddMore;

/**
 * Equation test cases for Add Principal Increase
 */
public class RPI extends EquationTestCaseAddMore
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RPI.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G89ARR";
	String optionId = "RPI";

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
		transaction.setFieldValue("GZDLP", "CAP"); // Deal type
		transaction.setFieldValue("GZDLR", "X"); // Deal reference
		transaction.setFieldValue("GZBRNM", "ACC1"); // Branch mnemonic
		transaction.setFieldValue("GZDTE", "991231"); // Movement date
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDLP", "CBB"); // Deal type
		transaction.setFieldValue("GZDLR", "CBB-001"); // Deal reference
		transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic
		transaction.setFieldValue("GZDTE", "1000131"); // Movement date
	}

	/**
	 * Setup the mandatory fields
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZADJ", "100000"); // Amount larger than one transaction
		transaction.setFieldValue("GZNDA", "N"); // Narrative on deal account postings ?
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic
		transaction.setFieldValue("GZSCCY", "GBP"); // Settlement currency mnemonic
		transaction.setFieldValue("GZABF", "9132"); // Settlement branch (4A)
		transaction.setFieldValue("GZANF", "234567"); // Settlement account basic number (6A)
		transaction.setFieldValue("GZASF", "001"); // Settlement account suffix
		transaction.setFieldValue("GZINC", "Y"); // Increase/decrease
	}

}