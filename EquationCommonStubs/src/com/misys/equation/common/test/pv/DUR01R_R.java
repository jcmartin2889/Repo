package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCaseValidate;

public class DUR01R_R extends EquationTestCaseValidate
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DUR01R_R.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "321010000000100000000001001000000000000100100";
		invalidCCN = "321010000000100000000002001000000000000200100";
		decode = "R";
	}
}
