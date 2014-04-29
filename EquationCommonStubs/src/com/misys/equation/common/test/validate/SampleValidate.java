package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class SampleValidate extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SampleValidate.java 4438 2009-08-12 13:15:30Z weddelc1 $";
	public SampleValidate()
	{
		// Set up a valid CCN
		validCCN = "AAAAAAAAAAA";

		// Set up an invalid CCN
		invalidCCN = "OOPS";
	}
}
