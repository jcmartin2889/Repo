package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV53R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV53R.java 4438 2009-08-12 13:15:30Z weddelc1 $";
	public GWV53R()
	{
		// Set up a valid CCN
		validCCN = "000050000000000USDPHP";

		// Set up an invalid CCN
		invalidCCN = "000000000000000USDPHP";
	}
}
