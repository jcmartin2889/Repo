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
import com.misys.equation.common.test.EquationTestCaseMaintain;

/**
 * Equation test cases for Customer Number Characteristics
 */
public class ANO extends EquationTestCaseMaintain
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ANO.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	String programName = "I52BRR";
	String optionId = "ANO";

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
		transaction.setFieldValue("GZCNCP", "qq"); // Customer number character position (2S,0)
	}

	/**
	 * Setup a existing key fields only
	 */
	@Override
	public void setupExistKeyFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZCNCP", "2"); // Customer number character position (2S,0)
	}

	/**
	 * Setup the mandatory fields (maintain mode)
	 */
	@Override
	public void setupMaintFields(EquationStandardTransaction transaction)
	{
		transaction.setFieldValue("GZV01", "1"); // 1st valid/def cust no. char (1A)
		transaction.setFieldValue("GZV02", "2"); // 2nd valid/def cust no. char (1A)
		transaction.setFieldValue("GZV03", "3"); // 3rd valid/def cust no. char (1A)
		transaction.setFieldValue("GZV04", "4"); // 4th valid/def cust no. char (1A)
		transaction.setFieldValue("GZV05", "5"); // 5th valid/def cust no. char (1A)
		transaction.setFieldValue("GZV06", "6"); // 6th valid/def cust no. char (1A)
		transaction.setFieldValue("GZV07", "7"); // 7th valid/def cust no. char (1A)
		transaction.setFieldValue("GZV08", "8"); // 8th valid/def cust no. char (1A)
		transaction.setFieldValue("GZV09", "9"); // 9th valid/def cust no. char (1A)
		transaction.setFieldValue("GZV10", "0"); // 10th valid/def cust no. char (1A)
		//transaction.setFieldValue("GZV11", "D"); // 11th valid/def cust no. char (1A)
	}

}
