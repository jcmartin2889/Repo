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
public class ACG5
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ACG5.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	String inputBrnmStr = "0000";
	String Dealtype = "";
	String Dealref = "";
	String Dealbranch = "";
	String Commitmentref = "XXX";
	String Accountbranch = "";
	String Accountnumber = "";
	String Accountsuffix = "";
	String GZUSER = "LOND";
	String GZEVNM = "NCM";
	String TranFlag = "U";
	String Eventmnem = "NCM002";

	EquationStandardSession session;
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private ACG5()
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
		ACG5 test = new ACG5();
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
			EquationStandardTransaction eventCharge = new EquationStandardTransaction("C62ARRACG", session, 1000);

			// Set the transaction type
			eventCharge.setMode("A");
			eventCharge.setFieldValue("GZDLP", Dealtype);
			eventCharge.setFieldValue("GZDLR", Dealref);
			eventCharge.setFieldValue("GZDBRM", Dealbranch);
			eventCharge.setFieldValue("GZCMR", Commitmentref);
			eventCharge.setFieldValue("GZAB", Accountbranch);
			eventCharge.setFieldValue("GZAN", Accountnumber);
			eventCharge.setFieldValue("GZAS", Accountsuffix);
			eventCharge.setFieldValue("GZEVNM", GZEVNM);
			eventCharge.setGSFieldValue("GSECNM", Eventmnem);
			eventCharge.setFieldValue("GZFLAG", TranFlag);

			eventCharge.setGSFieldValue("GSCCYC", "USD");
			// eventCharge.setFieldValue("GZATP", "CA"); // test for variations

			// retrieve transaction
			session.retrieveEquationTransaction(eventCharge);
			// session.validateEquationTransaction(eventCharge);

			// print result
			System.out.println(eventCharge);
			Toolbox.printMessages(eventCharge.getErrorList());
			Toolbox.printMessages(eventCharge.getWarningList());
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
}