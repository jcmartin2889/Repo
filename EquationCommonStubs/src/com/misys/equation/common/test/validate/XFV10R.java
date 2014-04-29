package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class XFV10R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: XFV10R.java 4438 2009-08-12 13:15:30Z weddelc1 $";
	public XFV10R()
	{
		// Set up a valid CCN
		validCCN = "CO001COLLATERALREF001";

		// Set up an invalid CCN
		invalidCCN = "XX001XXXXXXXXXXXXXXX";
	}
}
