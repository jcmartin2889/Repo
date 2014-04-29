package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV31R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV31R.java 4438 2009-08-12 13:15:30Z weddelc1 $";
	public GWV31R()
	{
		// Set up a valid CCN
		validCCN = "T";

		// Set up an invalid CCN
		invalidCCN = "H";
	}
}
