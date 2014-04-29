package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV28R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV28R.java 4438 2009-08-12 13:15:30Z weddelc1 $";
	public GWV28R()
	{
		// Set up a valid CCN
		validCCN = "10.1";
		// This validate module allows the setting of flags in positions 125-128 - See Global Modules manual for details

		// Set up an invalid CCN
		invalidCCN = "999999.9";
	}
}
