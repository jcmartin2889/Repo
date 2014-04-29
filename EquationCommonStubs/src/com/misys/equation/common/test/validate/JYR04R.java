package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class JYR04R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: JYR04R.java 4438 2009-08-12 13:15:30Z weddelc1 $";
	public JYR04R()
	{
		// Set up a valid CCN
		validCCN = "054301050059002USD0991231";

		// Set up an invalid CCN
		invalidCCN = "054301050059002GBP0991231";
	}
}
