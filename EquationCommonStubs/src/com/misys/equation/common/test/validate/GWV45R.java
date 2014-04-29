package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV45R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV45R.java 3223 2009-04-03 14:15:22Z challip1 $";
	public GWV45R()
	{
		// Set up a valid CCN
		validCCN = "12345678.123                                                                                       12Y3";

		// Set up an invalid CCN
		invalidCCN = "123456789.123                                                                                      12Y3";
	}
}