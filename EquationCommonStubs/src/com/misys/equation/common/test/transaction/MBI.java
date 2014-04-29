/**
 * Copyright and all other intellectual property rights in this software, in any form, is vested in Misys International Banking
 * Systems Ltd ("Misys") or a related company.
 * 
 * This software may not be copied, amended, compiled, translated, or developed; or sold, leased, hired, rented, or disclosed to any
 * third party without the prior written consent of Misys.
 * 
 * Copyright Misys International Banking Systems Ltd, 1975 and later
 */

package com.misys.equation.common.test.transaction;

import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCaseFully;

/**
 * Equation test cases for Maintain Bonus Increments
 */
public class MBI extends EquationTestCaseFully
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MBI.java 4721 2009-09-15 11:14:17Z weddelc1 $";
	String programName = "G45FRR";
	String optionId = "MBI";

	// ------------------------------------------------------------------------ JUNIT's overloaded methods
	/**
	 * Setup
	 */
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
	public void setupNonExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZINC", "02"); // Increment type (2A)
		transaction.setFieldValue("GZDLP", "RE3"); // Deal Type (3A)
		transaction.setFieldValue("GZCCY", "GBP"); // Currency (3A)
		transaction.setFieldValue("GZVFR", "991231"); // Value from date (7S,0)
	}

	/**
	 * Setup the mandatory fields (add mode)
	 */
	public void setupAddFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZIFQ", "1"); // Increment frequency (1A)
		transaction.setFieldValue("GZCIN", "0.5000000"); // Constant increment percentage (11P,7)
		transaction.setFieldValue("GZICAM", "0"); // Constant increment amount (15P,0)
		transaction.setFieldValue("GZPR01", "1"); // Period 1 (2S,0)
		transaction.setFieldValue("GZIP01", "0"); // Increment percentage 1 (11P,7)
		transaction.setFieldValue("GZIA01", "0"); // Increment amount 1 (15P,0)
		transaction.setFieldValue("GZPR02", "2"); // Period 2 (2S,0)
		transaction.setFieldValue("GZIP02", "0"); // Increment percentage 2 (11P,7)
		transaction.setFieldValue("GZIA02", "0"); // Increment amount 2 (15P,0)
		transaction.setFieldValue("GZPR03", "2"); // Period 3 (2S,0)
		transaction.setFieldValue("GZIP03", "0"); // Increment percentage 3 (11P,7)
		transaction.setFieldValue("GZIA03", "0"); // Increment amount 3 (15P,0)
		transaction.setFieldValue("GZPR04", "4"); // Period 4 (2S,0)
		transaction.setFieldValue("GZIP04", "0"); // Increment percentage 4 (11P,7)
		transaction.setFieldValue("GZIA04", "0"); // Increment amount 4 (15P,0)
		transaction.setFieldValue("GZPR05", "5"); // Period 5 (2S,0)
		transaction.setFieldValue("GZIP05", "0"); // Increment percentage 5 (11P,7)
		transaction.setFieldValue("GZIA05", "0"); // Increment amount 5 (15P,0)
		transaction.setFieldValue("GZPR06", "6"); // Period 6 (2S,0)
		transaction.setFieldValue("GZIP06", "0"); // Increment percentage 6 (11P,7)
		transaction.setFieldValue("GZIA06", "0"); // Increment amount 6 (15P,0)
		transaction.setFieldValue("GZPR07", "7"); // Period 7 (2S,0)
		transaction.setFieldValue("GZIP07", "0"); // Increment percentage 7 (11P,7)
		transaction.setFieldValue("GZIA07", "0"); // Increment amount 7 (15P,0)
		transaction.setFieldValue("GZPR08", "8"); // Period 8 (2S,0)
		transaction.setFieldValue("GZIP08", "0"); // Increment percentage 8 (11P,7)
		transaction.setFieldValue("GZIA08", "0"); // Increment amount 8 (15P,0)
		transaction.setFieldValue("GZPR09", "9"); // Period 9 (2S,0)
		transaction.setFieldValue("GZIP09", "0"); // Increment percentage 9 (11P,7)
		transaction.setFieldValue("GZIA09", "0"); // Increment amount 9 (15P,0)
		transaction.setFieldValue("GZPR10", "10"); // Period 10 (2S,0)
		transaction.setFieldValue("GZIP10", "0"); // Increment percentage 10 (11P,7)
		transaction.setFieldValue("GZIA10", "0"); // Increment amount 10 (15P,0)
		transaction.setFieldValue("GZPR11", "11"); // Period 11 (2S,0)
		transaction.setFieldValue("GZIP11", "0"); // Increment percentage 11 (11P,7)
		transaction.setFieldValue("GZIA11", "0"); // Increment amount 11 (15P,0)
		transaction.setFieldValue("GZPR12", "12"); // Period 12 (2S,0)
		transaction.setFieldValue("GZIP12", "0"); // Increment percentage 12 (11P,7)
		transaction.setFieldValue("GZIA12", "0"); // Increment amount 12 (15P,0)
		transaction.setFieldValue("GZPR13", "13"); // Period 13 (2S,0)
		transaction.setFieldValue("GZIP13", "0"); // Increment percentage 13 (11P,7)
		transaction.setFieldValue("GZIA13", "0"); // Increment amount 13 (15P,0)
		transaction.setFieldValue("GZPR14", "14"); // Period 14 (2S,0)
		transaction.setFieldValue("GZIP14", "0"); // Increment percentage 14 (11P,7)
		transaction.setFieldValue("GZIA14", "0"); // Increment amount 14 (15P,0)
		transaction.setFieldValue("GZPR15", "15"); // Period 15 (2S,0)
		transaction.setFieldValue("GZIP15", "0"); // Increment percentage 15 (11P,7)
		transaction.setFieldValue("GZIA15", "0"); // Increment amount 15 (15P,0)
		transaction.setFieldValue("GZPR16", "16"); // Period 16 (2S,0)
		transaction.setFieldValue("GZIP16", "0"); // Increment percentage 16 (11P,7)
		transaction.setFieldValue("GZIA16", "0"); // Increment amount 16 (15P,0)
		transaction.setFieldValue("GZPR17", "17"); // Period 17 (2S,0)
		transaction.setFieldValue("GZIP17", "0"); // Increment percentage 17 (11P,7)
		transaction.setFieldValue("GZIA17", "0"); // Increment amount 17 (15P,0)
		transaction.setFieldValue("GZPR18", "18"); // Period 18 (2S,0)
		transaction.setFieldValue("GZIP18", "0"); // Increment percentage 18 (11P,7)
		transaction.setFieldValue("GZIA18", "0"); // Increment amount 18 (15P,0)
		transaction.setFieldValue("GZPR19", "19"); // Period 19 (2S,0)
		transaction.setFieldValue("GZIP19", "0"); // Increment percentage 19 (11P,7)
		transaction.setFieldValue("GZIA19", "0"); // Increment amount 19 (15P,0)
		transaction.setFieldValue("GZPR20", "20"); // Period 20 (2S,0)
		transaction.setFieldValue("GZIP20", "0"); // Increment percentage 20 (11P,7)
		transaction.setFieldValue("GZIA20", "0"); // Increment amount 20 (15P,0)
		transaction.setFieldValue("GZPR21", "21"); // Period 21 (2S,0)
		transaction.setFieldValue("GZIP21", "0"); // Increment percentage 21 (11P,7)
		transaction.setFieldValue("GZIA21", "0"); // Increment amount 21 (15P,0)
		transaction.setFieldValue("GZPR22", "22"); // Period 22 (2S,0)
		transaction.setFieldValue("GZIP22", "0"); // Increment percentage 22 (11P,7)
		transaction.setFieldValue("GZIA22", "0"); // Increment amount 22 (15P,0)
		transaction.setFieldValue("GZPR23", "23"); // Period 23 (2S,0)
		transaction.setFieldValue("GZIP23", "0"); // Increment percentage 23 (11P,7)
		transaction.setFieldValue("GZIA23", "0"); // Increment amount 23 (15P,0)
		transaction.setFieldValue("GZPR24", "24"); // Period 24 (2S,0)
		transaction.setFieldValue("GZIP24", "0"); // Increment percentage 24 (11P,7)
		transaction.setFieldValue("GZIA24", "0"); // Increment amount 24 (15P,0)
		transaction.setFieldValue("GZPR25", "25"); // Period 25 (2S,0)
		transaction.setFieldValue("GZIP25", "0"); // Increment percentage 25 (11P,7)
		transaction.setFieldValue("GZIA25", "0"); // Increment amount 25 (15P,0)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCIN", "0.7500000"); // Constant increment percentage (11P,7)
	}

}
