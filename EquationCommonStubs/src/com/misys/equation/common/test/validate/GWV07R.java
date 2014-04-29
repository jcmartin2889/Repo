package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV07R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV07R.java 3218 2009-04-03 10:55:34Z challip1 $";
	public GWV07R()
	{
		// TODO: Data not present in unit (NOT FOUND).

		// Set up a valid CCN
		validCCN = "060";

		// Set up an invalid CCN
		invalidCCN = "111";
	}
}
