package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV41R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV41R.java 4438 2009-08-12 13:15:30Z weddelc1 $";
	public GWV41R()
	{
		// TODO: Data
		// Set up a valid CCN
		validCCN = "OPICS";

		// Set up an invalid CCN
		invalidCCN = "1111111";
	}
}