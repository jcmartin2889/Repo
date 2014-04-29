package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV09R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV09R.java 4438 2009-08-12 13:15:30Z weddelc1 $";
	public GWV09R()
	{
		// Set up a valid CCN
		validCCN = "XKWRKEQX    GWF53PR   0991231";

		// TODO: Can't get this one to work!

		// Set up an invalid CCN
		invalidCCN = "XKWRKEQX   FGWC01PR   0991231";
	}
}
