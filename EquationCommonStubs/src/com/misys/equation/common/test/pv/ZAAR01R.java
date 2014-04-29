package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class ZAAR01R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ZAAR01R.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		// Taken from EQ4 unit, since no regression data is available.
		validCCN = "NTEST1    00001";
		invalidCCN = "NTEST1WILL00009";
	}
}
