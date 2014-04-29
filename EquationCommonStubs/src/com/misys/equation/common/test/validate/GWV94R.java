package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV94R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV94R.java 3224 2009-04-03 14:25:30Z challip1 $";
	public GWV94R()
	{
		// Set up a valid CCN
		validCCN = "010108";

		// Set up an invalid CCN
		invalidCCN = "290207";
	}
}
