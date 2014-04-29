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
public class TH
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TH.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	String inputBrnmStr = "0001";
	String accountBranch = "0001";
	String accountBasicNumber = "100001";
	String accountSuffix = "001";
	EquationStandardSession session;
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private TH()
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
		TH test = new TH();
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
			EquationStandardListEnquiry transactionHistoryEnquiry = new EquationStandardListEnquiry("H71DER", session);
			// Set some transaction fields up
			transactionHistoryEnquiry.setFieldValue("HZAB", accountBranch);
			transactionHistoryEnquiry.setFieldValue("HZAN", accountBasicNumber);
			transactionHistoryEnquiry.setFieldValue("HZAS", accountSuffix);
			// loan = unit.validateEquationTransaction(loan);
			transactionHistoryEnquiry = session.executeListEnquiry(transactionHistoryEnquiry);
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
}