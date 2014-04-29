/*
 * This sample code is provided by Misys for illustrative purposes only.
 * 
 * These examples have not been thoroughly tested under all conditions.
 * 
 * Misys, therefore, cannot guarantee or imply reliability, serviceability, or function of these programs.
 * 
 * All programs contained herein are provided to you "AS IS" without any warranties of any kind. The implied warranties of
 * non-infringement, merchantability and fitness for a particular purpose are expressly disclaimed.
 */
package com.misys.equation.common.test.xapi.standard;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.Toolbox;

// *************************************************************************************************
/**
 * 
 * 
 */
// *************************************************************************************************
public class CPY
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CPY.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";

	EquationStandardSession session;
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private CPY()
	{
		try
		{
			session = TestEnvironment.getTestEnvironment().getStandardSession();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public static void main(String[] inputParameters)
	{
		CPY test = new CPY();
		test.test();
	}
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	public void test()
	{
		// Have a bash...
		try
		{
			// Get a new transaction for a sundry item
			EquationStandardTransaction transaction = new EquationStandardTransaction("D34CRRCPY", session);

			// Set the transaction type
			transaction.setMode("A");

			// Set the fields
			transaction.setFieldValue("GZLNP", "CR2"); // Loan type (3A)
			transaction.setFieldValue("GZLNR", "CR2-100"); // Loan reference (13A)
			transaction.setFieldValue("GZBRNM", "LOND"); // Branch mnemonic (4A)
			transaction.setFieldValue("GZVDT", "0991231"); // Payment value date (7S,0)
			transaction.setFieldValue("GZDDT", "0991231"); // Payment value date (7S,0)
			transaction.setFieldValue("GZNS3M", "900"); // Related sequence number (3A)
			transaction.setFieldValue("GZMVT", "I"); // Movement type (1A)
			transaction.setFieldValue("GZMVTS", "P"); // Movement sub-type (1A)

			session.retrieveEquationTransaction(transaction);
			session.validateEquationTransaction(transaction);
			session.applyEquationTransaction(transaction);
			System.out.println(transaction);

			Toolbox.printMessages(transaction.getErrorList());
			Toolbox.printMessages(transaction.getWarningList());
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
}
