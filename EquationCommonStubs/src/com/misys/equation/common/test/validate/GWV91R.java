package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV91R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV91R.java 3124 2009-03-26 15:09:45Z challip1 $";
	public GWV91R()
	{
		// Set up a valid CCN
		validCCN = "1010228YMD";

		// Set up an invalid CCN
		invalidCCN = "1010229YMD";
	}
}
