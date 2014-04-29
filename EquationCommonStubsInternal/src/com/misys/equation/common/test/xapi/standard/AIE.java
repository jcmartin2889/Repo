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
 * @author Paul Challis
 */
// *************************************************************************************************
public class AIE
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AIE.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	String inputBrnmStr = "0543";
	EquationStandardSession session;
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private AIE()
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
		AIE test = new AIE();
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
			EquationStandardEnquiry accountInterestEnquiry = new EquationStandardEnquiry("H73EER", session);
			// Set some transaction fields up
			accountInterestEnquiry.setFieldValue("HZAB", "3210");
			accountInterestEnquiry.setFieldValue("HZAN", "100000");
			accountInterestEnquiry.setFieldValue("HZAS", "001");
			// loan = unit.validateEquationTransaction(loan);
			accountInterestEnquiry = session.executeEnquiry(accountInterestEnquiry);
			System.out.println(accountInterestEnquiry.toString());
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
}
