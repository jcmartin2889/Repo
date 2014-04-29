package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class L6R10R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: L6R10R.java 8453 2010-07-30 16:41:34Z CHALLIP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "05431234670090543000105000001";
		invalidCCN = "10000000000000000000105000023";
		decode = "";
	}
}
