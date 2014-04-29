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

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.test.TestEnvironment;

// *************************************************************************************************
/**
 * 
 * 
 */
// *************************************************************************************************
public class AS
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AS.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
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
	private AS()
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
		AS test = new AS();
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
			EquationStandardListEnquiry accountSummaryEnquiry = new EquationStandardListEnquiry("H70DER", session);
			// Set some transaction fields up
			accountSummaryEnquiry.setFieldValue("HZAB", accountBranch);
			accountSummaryEnquiry.setFieldValue("HZAN", accountBasicNumber);
			accountSummaryEnquiry.setFieldValue("HZAS", accountSuffix);
			// loan = unit.validateEquationTransaction(loan);
			accountSummaryEnquiry = session.executeListEnquiry(accountSummaryEnquiry);
			System.out.println(accountSummaryEnquiry.toString());
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
}