package com.misys.equation.common.test.validate;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class UTR71R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UTR71R.java 8505 2010-08-05 15:48:25Z CHALLIP1 $";
	public UTR71R()
	{
		// Set up a valid CCN
		validCCN = "000000000001000USDPHPY05430543LONG";

		// Set up an invalid CCN
		invalidCCN = "00000000001000GHGPHPY05430543OBOG";
	}
}
