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
public class C8R01R5 extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: C8R01R5.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
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
			String decode = " ";
			String pvModule = "C8R01R";

			// Validate using EquationPVData
			System.out.println("Query on USD");
			EquationPVData equationPVData = new EquationPVData(unit.getPVMetaData(pvModule), session.getCcsid());
			equationPVData.setFieldValue("C8@CCY", "USD");
			EquationStandardValidation equationStandardValidation = new EquationStandardValidation(decode, pvModule,
							equationPVData, "N", "N");
			equationStandardValidation = session.executeValidate(equationStandardValidation);
			System.out.println("PVDATA: " + equationStandardValidation.getDataOutput());
			System.out.println("PVDATA: " + equationStandardValidation.getEquationPVData().getDataDsccn());
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			// Rollback any transactions for the unit
		}
	}
}
