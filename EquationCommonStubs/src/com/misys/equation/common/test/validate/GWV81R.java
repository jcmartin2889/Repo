package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV81R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV81R.java 8505 2010-08-05 15:48:25Z CHALLIP1 $";
	public GWV81R()
	{
		// Set up a valid CCN
		validCCN = "USD/AA";

		// Set up an invalid CCN
		invalidCCN = "USD-AB";
	}
}
