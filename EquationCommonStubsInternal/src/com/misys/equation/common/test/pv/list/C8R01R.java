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
package com.misys.equation.common.test.pv.list;

import com.misys.equation.common.access.EquationPVData;
import com.misys.equation.common.access.EquationStandardValidation;
import com.misys.equation.common.test.EquationTestCase;

// *************************************************************************************************
/**
 * 
 * 
 */
// *************************************************************************************************
public class C8R01R extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: C8R01R.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	// Input branch mnemonic
	String inputBrnmStr = "0000";
	String decode = " ";
	String pvModule = "C8R01R";
	String input = "USD";
	long startTime = System.currentTimeMillis();
	long currentTime = startTime;
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
			// Validate passing DSCCN
			System.out.println("Test 1 via DSCCN");
			EquationStandardValidation equationStandardValidation = new EquationStandardValidation(decode, pvModule, input, "N",
							"N");
			equationStandardValidation = session.executeValidate(equationStandardValidation);
			System.out.println("PVDATA: " + equationStandardValidation.getDataOutput());

			// Validate using EquationPVData
			System.out.println("Test 2 via Equation PV Data");
			EquationPVData equationPVData = new EquationPVData(session.getUnit().getPVMetaData(pvModule), session.getCcsid());
			equationPVData.setFieldValue("C8@CCY", "JPY");
			equationStandardValidation = new EquationStandardValidation(decode, pvModule, equationPVData, "N", "N");
			equationStandardValidation = session.executeValidate(equationStandardValidation);
			System.out.println("PVDATA: " + equationStandardValidation.getDataOutput());
			System.out.println("PVDATA: " + equationStandardValidation.getEquationPVData().getDataDsccn());

			// Validate using EquationPVData
			System.out.println("Test 3 via Equation PV Data (DSCCN)");
			equationPVData = new EquationPVData(session.getUnit().getPVMetaData(pvModule), session.getCcsid());
			equationPVData.setDataDsccn("GBP");
			equationStandardValidation = new EquationStandardValidation(decode, pvModule, equationPVData, "N", "N");
			equationStandardValidation = session.executeValidate(equationStandardValidation);
			System.out.println("PVDATA: " + equationStandardValidation.getEquationPVData().getDataDsccn());

			// Validate passing DSCCN
			System.out.println("Test 4 via DSCCN");
			decode = " ";
			pvModule = "GWV30R";
			input = "GBP01512355";
			equationStandardValidation = new EquationStandardValidation(decode, pvModule, input, "N", "N");
			equationStandardValidation = session.executeValidate(equationStandardValidation);
			System.out.println("PVDATA: " + equationStandardValidation.getDataOutput());

		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
}
