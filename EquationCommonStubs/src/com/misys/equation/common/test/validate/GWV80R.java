package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV80R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV80R.java 4438 2009-08-12 13:15:30Z weddelc1 $";
	public GWV80R()
	{
		// Set up a valid CCN
		validCCN = "7D";

		// Set up an invalid CCN
		invalidCCN = "23";
	}
}
