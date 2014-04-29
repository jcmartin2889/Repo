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
public class RCE
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RCE.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	String inputBrnmStr = "LOND";
	EquationStandardSession session;
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private RCE()
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
		RCE test = new RCE();
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
			EquationStandardListEnquiry loanRepaymentCalculatorEnquiry = new EquationStandardListEnquiry("I31DER", session);
			// Set some transaction fields up

			loanRepaymentCalculatorEnquiry.setFieldValue("HZDLP", "CD1");
			loanRepaymentCalculatorEnquiry.setFieldValue("HZBRNM", "LOND");
			loanRepaymentCalculatorEnquiry.setFieldValue("HZCCY", "GBP");
			loanRepaymentCalculatorEnquiry.setFieldValue("HZDLA", " 000000000500000");
			loanRepaymentCalculatorEnquiry.setFieldValue("HZSDTE", "1000101");
			loanRepaymentCalculatorEnquiry.setFieldValue("HZIDB", "3");
			loanRepaymentCalculatorEnquiry.setFieldValue("HZRAT", " 00150000000");
			loanRepaymentCalculatorEnquiry.setFieldValue("HZCRL", "N");
			loanRepaymentCalculatorEnquiry.setFieldValue("HZMDT", "1000601");
			loanRepaymentCalculatorEnquiry.setFieldValue("HZNCD", "1000201");
			loanRepaymentCalculatorEnquiry.setFieldValue("HZRFRQ", "V01");
			loanRepaymentCalculatorEnquiry.setFieldValue("HZNDT", "1000601");
			loanRepaymentCalculatorEnquiry.setFieldValue("HZNPY", "1");
			loanRepaymentCalculatorEnquiry.setFieldValue("HZDIF", "Y");
			loanRepaymentCalculatorEnquiry.setFieldValue("HZMODE", "0");
			loanRepaymentCalculatorEnquiry = session.executeIncrementalListEnquiry(loanRepaymentCalculatorEnquiry);
			System.out.println(loanRepaymentCalculatorEnquiry);
			while (!loanRepaymentCalculatorEnquiry.isComplete())
			{
				loanRepaymentCalculatorEnquiry.setFieldValue("HZMODE", "4");
				loanRepaymentCalculatorEnquiry = session.executeIncrementalListEnquiry(loanRepaymentCalculatorEnquiry);
				System.out.println(loanRepaymentCalculatorEnquiry);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
