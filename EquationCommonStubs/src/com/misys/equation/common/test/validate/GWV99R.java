package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV99R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV99R.java 4438 2009-08-12 13:15:30Z weddelc1 $";
	public GWV99R()
	{
		// TODO: Depends on user/wsid authority to access account.

		// Set up a valid CCN
		validCCN = "123456";

		// Set up an invalid CCN
		invalidCCN = "123467001                                                                       "
						+ "                                              G";
	}
}
