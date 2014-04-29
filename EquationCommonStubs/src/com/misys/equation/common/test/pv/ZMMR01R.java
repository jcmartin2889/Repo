package com.misys.equation.common.test.pv;

import com.misys.equation.common.test.EquationTestCasePVMetaData;

public class ZMMR01R extends EquationTestCasePVMetaData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ZMMR01R.java 8453 2010-07-30 16:41:34Z CHALLIP1 $";
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		validCCN = "ABRAHAISACHR10008";
		invalidCCN = "198988";
	}
}
