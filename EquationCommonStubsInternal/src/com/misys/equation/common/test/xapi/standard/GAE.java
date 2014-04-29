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
public class GAE
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GAE.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";

	EquationStandardSession session;
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private GAE()
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
		GAE test = new GAE();
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
			EquationStandardListEnquiry listEnquiry = new EquationStandardListEnquiry("T69DER", session);
			// listEnquiry.setFieldValue("HZAID", "AAE"); // API Identifier (10A)
			listEnquiry.setFieldValue("HZDOC", "N"); // Retrieve documentation (1A)
			listEnquiry.setFieldValue("HZROOT", "H15A"); // Function root (5A)
			// listEnquiry.setFieldValue("HZFID", "AAE"); // Function ID (3A)
			// listEnquiry.setFieldValue("HZATYP", "003"); // API Type (3A)

			session.executeListEnquiry(listEnquiry);

			System.out.println(listEnquiry);
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
}
