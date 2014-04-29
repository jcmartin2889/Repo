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
public class HPC
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HPC.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	String inputBrnmStr = "LOND";
	EquationStandardSession session;
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private HPC()
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
		HPC test = new HPC();
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
			EquationStandardListEnquiry chargeEnquiry = new EquationStandardListEnquiry("U64DER", session);

			// setup the fields for the enquiry API
			chargeEnquiry.setFieldValue("HZAB", ""); // Account branch (4A)
			chargeEnquiry.setFieldValue("HZAN", ""); // Basic part of account number (6A)
			chargeEnquiry.setFieldValue("HZAS", ""); // Account suffix (3A)
			chargeEnquiry.setFieldValue("HZBRNM", ""); // Branch mnemonic (4A)
			chargeEnquiry.setFieldValue("HZDLP", ""); // Deal type (3A)
			chargeEnquiry.setFieldValue("HZDLR", ""); // Deal reference (13A)
			chargeEnquiry.setFieldValue("HZCMR", "TEST-COMMIT"); // Commitment reference (13A)
			chargeEnquiry.setFieldValue("HZCCY", "GBP"); // Currency mnemonic (3A)
			chargeEnquiry.setFieldValue("HZDTP", ""); // Branch mnemonic (4A)
			chargeEnquiry.setFieldValue("HZATP", ""); // Deal type (3A)
			chargeEnquiry.setFieldValue("HZEVNT", "COM"); // Event mnemonic (6A)
			chargeEnquiry.setFieldValue("HZSAM1", ""); // Charge amount

			// execute
			session.executeListEnquiry(chargeEnquiry);

		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
