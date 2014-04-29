package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class DVR01R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DVR01R.java 4438 2009-08-12 13:15:30Z weddelc1 $";
	public DVR01R()
	{
		// Set up a valid CCN
		validCCN = "0000000001001";

		// Set up an invalid CCN
		invalidCCN = "INVALID";
	}
}
