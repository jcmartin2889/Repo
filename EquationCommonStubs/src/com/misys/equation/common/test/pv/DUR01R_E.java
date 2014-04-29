package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class DUR01R_E extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DUR01R_E.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "32101000000010000000000100001";
		invalidCCN = "32101000000010000000000200002";
		decode = "E";
	}
}
