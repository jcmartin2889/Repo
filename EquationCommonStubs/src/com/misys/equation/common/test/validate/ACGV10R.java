package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class ACGV10R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.

	public ACGV10R()
	{
		// Set up a valid CCN
		validCCN = "E38FI1PRODQDDSSRC   ACER10D   E38390PRODQDDSSRC";

		// Set up an invalid CCN
		invalidCCN = "EXXFI1PRODQDDSSRC   ACER10D   P1479X    QDDSSRC";
	}
}
