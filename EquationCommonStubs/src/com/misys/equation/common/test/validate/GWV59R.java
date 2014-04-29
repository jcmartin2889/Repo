package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV59R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV59R.java 4438 2009-08-12 13:15:30Z weddelc1 $";
	public GWV59R()
	{
		// Set up a valid CCN
		validCCN = "FREE NARRATIVE";

		// Set up an invalid CCN
		invalidCCN = ":FSDFSD";
	}
}
