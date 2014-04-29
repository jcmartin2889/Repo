package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV29R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV29R.java 3070 2009-03-23 17:56:18Z challip1 $";
	public GWV29R()
	{
		// Set up a valid CCN
		validCCN = "GBP04 1234";

		// Set up an invalid CCN
		invalidCCN = "XXX";
	}
}
