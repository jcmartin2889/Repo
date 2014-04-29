package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GFV71R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GFV71R.java 4438 2009-08-12 13:15:30Z weddelc1 $";
	public GFV71R()
	{
		// Set up a valid CCN
		validCCN = "012008";

		// Set up an invalid CCN
		invalidCCN = "INVALID";
	}
}
