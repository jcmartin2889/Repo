package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class IIV10R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: IIV10R.java 4438 2009-08-12 13:15:30Z weddelc1 $";
	public IIV10R()
	{
		// Set up a valid CCN
		validCCN = "99981231230030000000000000020000000000020000";

		// Set up an invalid CCN
		invalidCCN = "99981231230030000000000000020000000000032222";
	}
}
