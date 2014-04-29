package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class FBR01R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	// public static final String _revision= "$Id: FBR01R.java 4438 2009-08-12 13:15:30Z weddelc1 $";
	public FBR01R()
	{
		// Set up a valid CCN
		validCCN = "SPECIAL";

		// Set up an invalid CCN
		invalidCCN = "1S";
	}
}
