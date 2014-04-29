package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV61R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.

	public GWV61R()
	{
		// Set up a valid CCN
		validCCN = "P3  LOND00105H000006";

		// Set up an invalid CCN
		invalidCCN = "P3  XXXX00103H000020";
	}
}
