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
import com.misys.equation.common.access.EquationStandardValidation;
import com.misys.equation.common.test.TestEnvironment;

// *************************************************************************************************
/**
 * 
 * 
 */
// *************************************************************************************************
public class GWV94R
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV94R.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EquationStandardSession session;
	// Input branch mnemonic
	String inputBrnmStr = "0000";
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
	// *********************************************************************************************
	/**
	 * 
	 */
	// *********************************************************************************************
	private GWV94R()
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
		GWV94R test = new GWV94R();
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
			// P/V module
			String pvModule = "GWV94R";
			// Validate date 010100
			String decode = " ";
			String input = "010100";
			EquationStandardValidation pv = new EquationStandardValidation(decode, pvModule, input, "N", "N");
			pv = session.executeValidate(pv);
			System.out.println("PVDATA: " + pv.getDataOutput());
			// Validate date 5D
			decode = " ";
			input = "5D";
			pv = new EquationStandardValidation(decode, pvModule, input, "N", "N");
			pv = session.executeValidate(pv);
			System.out.println("PVDATA: " + pv.getDataOutput());
			// Validate date S
			decode = " ";
			input = "S";
			pv = new EquationStandardValidation(decode, pvModule, input, "N", "N");
			pv = session.executeValidate(pv);
			System.out.println("PVDATA: " + pv.getDataOutput());
			// Validate date 1Y
			decode = " ";
			input = "1Y";
			pv = new EquationStandardValidation(decode, pvModule, input, "N", "N");
			pv = session.executeValidate(pv);
			System.out.println("PVDATA: " + pv.getDataOutput());
			// Validate date x10200
			decode = " ";
			input = "x10200";
			pv = new EquationStandardValidation(decode, pvModule, input, "N", "N");
			pv = session.executeValidate(pv);
			System.out.println("PVDATA: " + pv.getDataOutput());
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
}
