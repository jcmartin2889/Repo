package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV13R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV13R.java 3245 2009-04-06 14:47:37Z challip1 $";
	public GWV13R()
	{
		// Set up a valid CCN
		validCCN = "V01";

		// Set up an invalid CCN
		invalidCCN = "W08";
	}
}
