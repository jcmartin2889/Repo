package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV26R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV26R.java 3218 2009-04-03 10:55:34Z challip1 $";
	public GWV26R()
	{
		// Set up a valid CCN
		validCCN = "12345.1234567";

		// Set up an invalid CCN
		invalidCCN = "123456.123";
	}
}
