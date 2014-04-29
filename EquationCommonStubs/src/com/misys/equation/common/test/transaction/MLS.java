package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseFully;

/**
 * Equation test cases for Translate System Dictionary
 */
public class MLS extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MLS.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "H58LRR";
	String optionId = "MLS";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		retrieveBeforeCancel = true;
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
		transaction.setFieldValue("GZLNM", "ES"); // Language code
		transaction.setFieldValue("GZCFK", "SNM33"); // System dictionary identifier
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	@Override
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZSDX", "Networkio"); // System dictionary text
		/*
		 * One, and only one, of the following flags must be 'Y'
		 */
		transaction.setFieldValue("GZFLG1", "Y"); // Text is for an SM or SN parameter? (1A)
		transaction.setFieldValue("GZFLG2", "N"); // Text is for a ZP parameter? (1A)
		transaction.setFieldValue("GZFLG3", "N"); // Text is for an SD/UD/VD parameter? (1A)
		transaction.setFieldValue("GZFLG4", "N"); // Text is for a ZT parameter? (1A)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZSDX", "Networkio Magnifico"); // System dictionary text
		/*
		 * One, and only one, of the following flags must be 'Y'
		 */
		transaction.setFieldValue("GZFLG1", "Y"); // Text is for an SM or SN parameter? (1A)
		transaction.setFieldValue("GZFLG2", "N"); // Text is for a ZP parameter? (1A)
		transaction.setFieldValue("GZFLG3", "N"); // Text is for an SD/UD/VD parameter? (1A)
		transaction.setFieldValue("GZFLG4", "N"); // Text is for a ZT parameter? (1A)
	}

}
