package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV64R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV64R.java 4438 2009-08-12 13:15:30Z weddelc1 $";
	public GWV64R()
	{
		// Set up a valid CCN
		validCCN = "0543123467001";

		// Set up an invalid CCN
		invalidCCN = "GFDGDFGER700R";
	}
}
