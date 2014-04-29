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
public class CBY
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CBY.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";

	protected static EquationStandardSession session;
	protected static EquationUnit unit;
	protected static EquationUser user;
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private CBY()
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
		CBY test = new CBY();
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
			EquationStandardTransaction transaction = new EquationStandardTransaction("G01MRRMCD", session);

			// Set the transaction type
			transaction.setMode("M");

			// 1 SCREEN
			transaction.setFieldValue("GZCUS", "ABRAHA"); // Customer mnemonic (6A)
			transaction.setFieldValue("GZCLC", "ISA"); // Customer location (3A)

			// Apply the transaction
			session.retrieveEquationTransaction(transaction);
			// session.applyTransactions();

			// Commit
			session.commitTransactions();
			session.rollbackTransactions();
			System.out.println("finished!");
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
}