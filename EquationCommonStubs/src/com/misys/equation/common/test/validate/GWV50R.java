package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV50R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV50R.java 8505 2010-08-05 15:48:25Z CHALLIP1 $";
	public GWV50R()
	{
		// Set up a valid CCN
		validCCN = "000000000000111000000000000123000001100000000GBPUSD1000107";

		// Set up an invalid CCN
		invalidCCN = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	}
}
