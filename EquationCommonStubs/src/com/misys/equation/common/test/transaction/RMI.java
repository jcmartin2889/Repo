package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseMaintain;

/**
 * Equation test cases for Maintain Principal Increase
 */
public class RMI extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RMI.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "G89MRR";
	String optionId = "RMI";

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
		transaction.setFieldValue("GZDLP", "Cq2"); // Deal type
		transaction.setFieldValue("GZDLR", "Cq2002"); // Deal reference
		transaction.setFieldValue("GZBRNM", "HEdD"); // Branch mnemonic
		transaction.setFieldValue("GZDTE", "1000105"); // Movement date
	}

	/**
	 * Setup an existing key fields
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZDLP", "CR1"); // Deal type
		transaction.setFieldValue("GZDLR", "CR1004"); // Deal reference
		transaction.setFieldValue("GZBRNM", "HEAD"); // Branch mnemonic
		transaction.setFieldValue("GZDTE", "1000109"); // Movement date
		transaction.setFieldValue("GZYPSF", "Y");
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZADJ", "150000"); // Amount larger than one transaction
		transaction.setFieldValue("GZNDA", "N"); // Narrative on deal account postings ?
		transaction.setFieldValue("GZCCY", "GBP"); // Currency mnemonic
		transaction.setFieldValue("GZSCCY", "GBP"); // Settlement currency mnemonic
		transaction.setFieldValue("GZABF", "9132"); // Settlement branch (4A)
		transaction.setFieldValue("GZANF", "234567"); // Settlement account basic number (6A)
		transaction.setFieldValue("GZASF", "001"); // Settlement account suffix
		transaction.setFieldValue("GZINC", "Y"); // Increase/decrease
	}

}