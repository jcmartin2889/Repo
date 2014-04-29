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
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.test.TestEnvironment;

// *************************************************************************************************
/**
 * 
 * 
 */
// *************************************************************************************************
public class RNW
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RNW.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	protected static EquationStandardSession session;
	protected static EquationUnit unit;
	protected static EquationUser user;
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private RNW()
	{
		try
		{
			unit = TestEnvironment.getTestEnvironment().getEquationUnit();
			user = TestEnvironment.getTestEnvironment().getEquationUser();
			session = user.getSession();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public static void main(String[] inputParameters)
	{
		RNW test = new RNW();
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
			EquationStandardTransaction transaction = new EquationStandardTransaction("B11MRRRNW", session);

			// Set the transaction type
			transaction.setMode("M");

			// 1 SCREEN
			transaction.setFieldValue("GZREF", "REF001"); // Customer mnemonic (6A)
			transaction.setFieldValue("GZAB", "0543"); // Customer location (3A)
			transaction.setFieldValue("GZAN", "234567"); // Customer full name (35A)
			transaction.setFieldValue("GZAS", "201"); // Arabic customer full name (35A)

			// Retrieve details
			session.retrieveEquationTransaction(transaction);
			System.out.println(transaction);

			// Apply the transaction
			transaction.setFieldValue("GZRVW", "N");
			session.applyEquationTransaction(transaction);

			// Commit
			session.commitTransactions();
			System.out.println("finished!");
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
}
