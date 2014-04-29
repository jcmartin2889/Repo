package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class DUR01R_B extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DUR01R_B.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "3210100000001";
		invalidCCN = "3210100000002";
		decode = "B";
	}
}
