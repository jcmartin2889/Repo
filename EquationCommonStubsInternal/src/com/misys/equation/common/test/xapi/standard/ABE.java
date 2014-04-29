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

import com.misys.equation.common.access.EquationStandardEnquiry;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.test.TestEnvironment;

// *************************************************************************************************
/**
 * 
 * 
 */
// *************************************************************************************************
public class ABE
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ABE.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	String inputBrnmStr = "0001";
	String accountBranch = "0543";
	String accountBasicNumber = "012826";
	String accountSuffix = "002";
	EquationStandardSession session;
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private ABE()
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
		ABE test = new ABE();
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
			// Get us an empty customer transaction class
			EquationStandardEnquiry accountBasicEnquiry = new EquationStandardEnquiry("H69EER", session);
			// Set some transaction fields up
			accountBasicEnquiry.setFieldValue("HZAB", accountBranch);
			accountBasicEnquiry.setFieldValue("HZAN", accountBasicNumber);
			accountBasicEnquiry.setFieldValue("HZAS", accountSuffix);
			// loan = unit.validateEquationTransaction(loan);
			accountBasicEnquiry = session.executeEnquiry(accountBasicEnquiry);
			System.out.println(accountBasicEnquiry.toString());
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
}
