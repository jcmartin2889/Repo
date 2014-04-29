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
public class MWC
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MWC.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	String inputBrnmStr = "LOND";
	String code = "XXX";
	String desc = "xxxxxDescription XXX";
	EquationStandardSession session;
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private MWC()
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
		MWC test = new MWC();
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
			EquationStandardTransaction mwc = new EquationStandardTransaction("A37FRRMWC", session);
			// Set the transaction type
			mwc.setMode("D");
			// Set the fields required for MWC
			mwc.setFieldValue("GZCODE", code);
			mwc.setFieldValue("GZRN", desc);
			session.addEquationTransaction(mwc);
			// Apply the transaction
			session.applyTransactions();
			session.commitTransactions();
			Toolbox.printMessages(mwc.getErrorList());
			Toolbox.printMessages(mwc.getWarningList());
			System.out.println("finished!");
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
}
