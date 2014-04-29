package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class NEV01R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: NEV01R.java 8505 2010-08-05 15:48:25Z CHALLIP1 $";
	public NEV01R()
	{
		// Set up a valid CCN
		validCCN = "US031113110815110";

		// Set up an invalid CCN
		invalidCCN = "US03000 3110815666";
	}
}
