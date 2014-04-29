package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV90R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV90R.java 4438 2009-08-12 13:15:30Z weddelc1 $";
	public GWV90R()
	{
		// Set up a valid CCN
		validCCN = "1031201YMDDMY";

		// Set up an invalid CCN
		invalidCCN = "1011301YMDYMD";
	}
}
