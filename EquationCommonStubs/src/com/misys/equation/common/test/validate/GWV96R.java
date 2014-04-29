package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV96R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV96R.java 3251 2009-04-06 17:16:07Z challip1 $";
	public GWV96R()
	{
		// Set up a valid CCN
		validCCN = "1000101";

		// Set up an invalid CCN
		invalidCCN = "       290203";
	}
}
