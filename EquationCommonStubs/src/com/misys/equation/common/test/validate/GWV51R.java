package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class GWV51R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GWV51R.java 8505 2010-08-05 15:48:25Z CHALLIP1 $";
	public GWV51R()
	{

		// TODO:
		// Set up a valid CCN
		validCCN = "MSNBRANDS             N1000131";

		// Set up an invalid CCN
		invalidCCN = "XXXXXXXXXXXXXXXXXXXXXXX1000231";
	}
}
